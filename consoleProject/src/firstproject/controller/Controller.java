package firstproject.controller;

import java.util.HashMap;
import java.util.Map;

import firstproject.dao.SelectDAO;
import firstproject.service.AdminService;
import firstproject.service.HomeService;
import firstproject.service.MemberOrderService;
import firstproject.service.MemberService;
import firstproject.service.MenuService;
import firstproject.service.SelectService;
import firstproject.util.View;

public class Controller {

	// ���� ���� ��ü ����
	public static Map<String, Object> sessionStorage = new HashMap<String, Object>();
	
	HomeService homeService = HomeService.getInstance();
	MemberService memberService = MemberService.getInstance();
	AdminService adminService = AdminService.getInstance();
	MemberOrderService memberOrderService = MemberOrderService.getInstance();
	
	SelectService selectService = SelectService.getInstance();
	MenuService menuService = MenuService.getInstance();
	SelectDAO selectDAO = SelectDAO.getInstance();
	
	public static void main(String[] args) {
		
		new Controller().start();
		
	}
	
	// ���α׷� ���� �޼���
	private void start() {
		// ���� ���� �ʱ�ȭ
		sessionStorage.put("login", false); // �α��� �� ��.
		sessionStorage.put("loginInfo", null); // �α��� ���� ����.
		
		int view = View.HOME;
		while(true) {
			switch(view) {
			// Ȩ
			case View.HOME: view = homeService.home(); break;
			
			// ȸ��
			case View.MEMBER_LOGIN: view = memberService.login(); break;
			case View.MEMBER_SIGNUP: view = memberService.signUp(); break;
			case View.MEMBER_ID_PW_FIND: view = memberService.find(); break;
			
			// ������
			case View.ADMIN_HOME: view = adminService.home(); break;
			case View.ADMIN_MEM_MANAGEMENT: view = adminService.memManage(); break;
			case View.ADMIN_STORE_MANAGEMENT: view = adminService.storeManage(); break;
			case View.ADMIN_MENU_MANAGEMENT: view = adminService.menuManage(); break;
			case View.ADMIN_ORDERHISTORY: view = adminService.orderhistoManage(); break;
			case View.ADMIN_RIDER_MANAGEMENT: view = adminService.riderManage(); break;
			case View.ADMIN_LOGOUT: view = adminService.logout(); break;
			
			// ȸ�� �ֹ� ȭ��
//			case View.ORDER_HOME: view = memberOrderService.home(); break;
			case View.ORDER_HOME: view = memberOrderService.orderhome(); break;
			case View.ORDER_LOGOUT: view = memberOrderService.logout(); break;
			
			// ��� ���α׷� ����
//			case View.ORDER_TAKEOUT: view = selectService.takeout(); break;
			
			case View.CATEGORY_DELI: view = selectService.category(view); break;
			case View.CATEGORY_TAKE: view = selectService.category(view); break;
			
			case View.CATEGORY_KOREAN: view = selectService.korean(); break;
			case View.CATEGORY_WESTERN: view = selectService.western(); break;
			case View.CATEGORY_CHINESE: view = selectService.chinese(); break;
			case View.CATEGORY_JAPANESE: view = selectService.japanese(); break;
			case View.CATEGORY_DESSERT: view = selectService.dessert(); break;
			
			case View.MENU: view = menuService.storeInfo(); break;
			case View.MENU_DETAIL: view = menuService.menuInfo(); break;
			
			// ȸ������
			case View.CART: view = menuService.cartInfo(); break;
			case View.POINT: view = memberService.pointDeposit(); break;
			case View.RECEIPT: view = menuService.receipt(); break;
			case View.USERPAGE: view = memberService.userPage(); break;
			
			// �ý��� ����
			case View.SYSTEM_EXIT: System.out.println("���α׷��� �����մϴ�."); System.exit(0);
			default : System.out.println("�ٽ� �Է����ּ���."); view = homeService.home(); break;
			}
		}
	}
	
	// ������ ���� �޼���, ���� : 9
	public static boolean isAdmin() {
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		int isAdmin = Integer.parseInt(loginInfo.get("MEMLV").toString());
		if (isAdmin == 9) {
			return true;
		} else {
			return false;
		}
	}
	
	// �Ϲ�ȸ�� ���� �޼���, ���� : 1
	public static boolean isMember() {
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		int isMember = Integer.parseInt(loginInfo.get("MEMLV").toString());
		if (isMember == 1) {
			return true;
		} else {
			return false;
		}
	}

}
