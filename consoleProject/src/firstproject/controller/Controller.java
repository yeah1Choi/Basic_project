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

	// 세션 정보 객체 생성
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
	
	// 프로그램 시작 메서드
	private void start() {
		// 세션 정보 초기화
		sessionStorage.put("login", false); // 로그인 안 됨.
		sessionStorage.put("loginInfo", null); // 로그인 정보 없음.
		
		int view = View.HOME;
		while(true) {
			switch(view) {
			// 홈
			case View.HOME: view = homeService.home(); break;
			
			// 회원
			case View.MEMBER_LOGIN: view = memberService.login(); break;
			case View.MEMBER_SIGNUP: view = memberService.signUp(); break;
			case View.MEMBER_ID_PW_FIND: view = memberService.find(); break;
			
			// 관리자
			case View.ADMIN_HOME: view = adminService.home(); break;
			case View.ADMIN_MEM_MANAGEMENT: view = adminService.memManage(); break;
			case View.ADMIN_STORE_MANAGEMENT: view = adminService.storeManage(); break;
			case View.ADMIN_MENU_MANAGEMENT: view = adminService.menuManage(); break;
			case View.ADMIN_ORDERHISTORY: view = adminService.orderhistoManage(); break;
			case View.ADMIN_RIDER_MANAGEMENT: view = adminService.riderManage(); break;
			case View.ADMIN_LOGOUT: view = adminService.logout(); break;
			
			// 회원 주문 화면
//			case View.ORDER_HOME: view = memberOrderService.home(); break;
			case View.ORDER_HOME: view = memberOrderService.orderhome(); break;
			case View.ORDER_LOGOUT: view = memberOrderService.logout(); break;
			
			// 배달 프로그램 실행
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
			
			// 회원서비스
			case View.CART: view = menuService.cartInfo(); break;
			case View.POINT: view = memberService.pointDeposit(); break;
			case View.RECEIPT: view = menuService.receipt(); break;
			case View.USERPAGE: view = memberService.userPage(); break;
			
			// 시스템 종료
			case View.SYSTEM_EXIT: System.out.println("프로그램을 종료합니다."); System.exit(0);
			default : System.out.println("다시 입력해주세요."); view = homeService.home(); break;
			}
		}
	}
	
	// 관리자 검증 메서드, 레벨 : 9
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
	
	// 일반회원 검증 메서드, 레벨 : 1
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
