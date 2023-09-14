package firstproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import firstproject.controller.Controller;
import firstproject.dao.SelectDAO;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class MemberOrderService {

	private static MemberOrderService instance = null;

	private MemberOrderService() {
	}

	public static MemberOrderService getInstance() {
		if (instance == null)
			instance = new MemberOrderService();
		return instance;
	}

	SelectDAO selectDAO = SelectDAO.getInstance();

//	// �α��� �� �ֹ� ȭ��
//	public int home() {
//		System.out.println(" ������������������������������������������������������������������������������������������������������");
//		System.out.println(" �ֹ� ȭ��");
//		System.out.println(" ������������������������������������������������������������������������������������������������������");
//		System.out.println("1. ���");
//		System.out.println("2. ����");
//		System.out.println("0. �α׾ƿ�");
//		System.out.print("�Է� >> ");
//		int choice = ScanUtil.nextIntegerLine();
//		switch(choice) {
//		case 1 : return View.CATEGORY_DELI;
//		case 2 : return View.CATEGORY_TAKE;
//		case 0 : return View.ORDER_LOGOUT;
//		default : 
//			System.out.println("�߸��� �����Դϴ�.");
//			return View.ORDER_HOME;
//		}
//	}

	// �α��� �� �ֹ� ȭ��
	public int orderhome() {

		Controller.sessionStorage.put("ordercode", null); // �ֹ��ڵ� ����(O)

		// 1. ���� ���� > �� ���Կ� ����� ���̴� ����(���̴� �ڵ�, ���� ����) �߿� ��޺� �����´�.
		Controller.sessionStorage.put("delicost", null); // ��޺� ����

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		String memAdd = loginInfo.get("MEMADD").toString();
		String[] addArray = memAdd.split(" ");
		// System.out.println(addArray[1]);
		memAdd = addArray[1];
		Controller.sessionStorage.put("memAdd", memAdd); // ����� �ּ� ����(O)

		LocalDate now = LocalDate.now(); // ���� ��¥ ���ϱ�
		Controller.sessionStorage.put("orderdate", now); // �ֹ���¥ ����(O)

		Controller.sessionStorage.put("ordereta", null); // ��޿���ð� ���� (O)

		Controller.sessionStorage.put("deliOrTake", null); // ���/���� ����
//		System.out.println(Controller.sessionStorage.toString());

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     �ֹ� ȭ��");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("   1. ���");
		System.out.println("   2. ����");
		System.out.println("   9. ����������");
		System.out.println("   0. �α׾ƿ�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print("   �Է� >> ");
		int choice = ScanUtil.nextIntegerLine();
		switch (choice) {
		case 1:
			// ����ֹ��� �����ּҸ� ����� �����ϰ� �ϱ�
//				Object obj1 = Controller.sessionStorage.get("loginInfo");
//				Map<String, Object> userInfo = (Map<String, Object>) obj1;

			String userID = (String) loginInfo.get("MEMID");

			List<Map<String, Object>> daejeonOnly = selectDAO.daejeonList(userID);

			if (daejeonOnly == null) {
				System.out.println(" �� �˼��մϴ�. �����ֹ��� �ֹ������մϴ�.");
				System.out.println("�ֹ� �ּҰ� '����'�ÿ��� ��� �ֹ��� �����մϴ�. �ٽ� �ֹ����ּ���");
				return View.ORDER_HOME;
			} else {
				return View.CATEGORY_DELI;
			}
		case 2:
			return View.CATEGORY_TAKE;
		case 9:
			return View.USERPAGE;
		case 0:
			return View.ORDER_LOGOUT;
		default:
			System.out.println(" �� �߸��� �����Դϴ�.");
			return View.ORDER_HOME;
		}
	}

	// ȸ�� �α׾ƿ�
	public int logout() {
		String choice = "";

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �α׾ƿ�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println();
			System.out.println(" * �α׾ƿ� �Ͻðڽ��ϱ�? ( y / n )");
			System.out.println();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			choice = ScanUtil.nextLine();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			if (ValidationUtil.validationYN(choice))
				break;
		}

		if (choice.equals("y")) {
			Object loginInfoObj = Controller.sessionStorage.get("loginInfo");
			Map<String, Object> loginInfo = (Map<String, Object>) loginInfoObj;
			System.out.println("");
			System.out.println("  [ " + loginInfo.get("MEMNM") + " ] �� �α׾ƿ� �Ǿ����ϴ�.");
			System.out.println("");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("");
			System.out.println(" << �̿��� �ּż� �����մϴ� >> ");
			System.out.println();

			// ���� �ʱ�ȭ
			Controller.sessionStorage.put("login", null);
			Controller.sessionStorage.put("loginInfo", null);
			Controller.sessionStorage.remove("ordercode");
			Controller.sessionStorage.remove("delicost");
			Controller.sessionStorage.remove("memAdd");
			Controller.sessionStorage.remove("orderdate");
			Controller.sessionStorage.remove("ordereta");
			Controller.sessionStorage.remove("deliOrTake");

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * ����� ȭ������ ���ư��ϴ�.");
			return View.ORDER_HOME;
		} else {
			System.out.println(" >> �ùٸ� �Է��� �ƴմϴ�. �ٽ� �Է��ϼ���.");
			return View.ORDER_LOGOUT;
		}
	}

}
