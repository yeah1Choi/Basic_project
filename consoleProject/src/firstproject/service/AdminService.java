package firstproject.service;

import java.util.Map;

import firstproject.controller.Controller;
import firstproject.util.EnterUtil;
import firstproject.util.GapUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class AdminService {

	private static AdminService instance = null;

	private AdminService() {
	}

	public static AdminService getInstance() {
		if (instance == null)
			instance = new AdminService();
		return instance;
	}

	AdminReadService adminReadService = AdminReadService.getInstance();
	AdminCreateService adminCreateService = AdminCreateService.getInstance();
	AdminDeleteService adminDeleteService = AdminDeleteService.getInstance();
	AdminUpdateService adminUpdateService = AdminUpdateService.getInstance();

	GapUtil gapUtil = GapUtil.getInstance();

	// ������ Ȩ
	public int home() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" ������ ȭ��");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. ȸ������ ����");
		System.out.println(" 2. ���� ����Ʈ ����");
		System.out.println(" 3. �޴� ����");
		System.out.println(" 4. �ֹ����� ����");
		System.out.println(" 5. ���̴� ����");
		System.out.println(" 0. �α׾ƿ�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print("�Է� >> ");

		int choice = ScanUtil.nextIntegerLine();
		switch (choice) {
		case 1:
			return View.ADMIN_MEM_MANAGEMENT;
		case 2:
			return View.ADMIN_STORE_MANAGEMENT;
		case 3:
			return View.ADMIN_MENU_MANAGEMENT;
		case 4:
			return View.ADMIN_ORDERHISTORY;
		case 5:
			return View.ADMIN_RIDER_MANAGEMENT;
		case 0:
			return View.ADMIN_LOGOUT;
		default:
			System.out.println("�߸��� �����Դϴ�.");
			return View.ADMIN_HOME;
		}
	}

	// ȸ������ ���� �޼���
	public int memManage() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" ȸ������ ����");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. ȸ������ ��ȸ");
		System.out.println(" 2. ȸ������ ����");
		System.out.println(" 3. ȸ������ ����");
		System.out.println(" 0. ���� �޴�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminReadService.memRead();
		case 2:
			return adminUpdateService.memUpdate();
		case 3:
			return adminDeleteService.memDelete();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * �߸��� �����Դϴ�.");
			System.out.println();
			return View.ADMIN_MEM_MANAGEMENT;
		}
	}

	// ���� ����Ʈ ���� �޼���
	public int storeManage() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" ���� ����Ʈ ����");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. ���� ����Ʈ ���");
		System.out.println(" 2. ���� ����Ʈ ��ȸ");
		System.out.println(" 3. ���� ����Ʈ ����");
		System.out.println(" 4. ���� ����Ʈ ����");
		System.out.println(" 0. ���� �޴�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminCreateService.storeCreate();
		case 2:
			return adminReadService.storeRead();
		case 3:
			return adminUpdateService.storeUpdate();
		case 4:
			return adminDeleteService.storeDelete();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * �߸��� �����Դϴ�.");
			System.out.println();
			return View.ADMIN_STORE_MANAGEMENT;
		}
	}

	// �޴� ����
	public int menuManage() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" �޴� ����");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. �޴� ���");
		System.out.println(" 2. �޴� ��ȸ");
		System.out.println(" 3. �޴� ����");
		System.out.println(" 4. �޴� ����");
		System.out.println(" 0. ���� �޴�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminCreateService.menuCreate();
		case 2:
			return adminReadService.menuRead();
		case 3:
			return adminUpdateService.menuUpdate();
		case 4:
			return adminDeleteService.menuDelete();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * �߸��� �����Դϴ�.");
			System.out.println();
			return View.ADMIN_MENU_MANAGEMENT;
		}
	}

	// �ֹ����� ����
	public int orderhistoManage() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" �ֹ����� ����");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. �ֹ����� ��ȸ");
		System.out.println(" 0. ���� �޴�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminReadService.orderhistoRead();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * �߸��� �����Դϴ�.");
			System.out.println();
			return View.ADMIN_ORDERHISTORY;
		}
	}

	// ���̴� ����
	public int riderManage() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" ���̴� ����");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. ���̴� ���");
		System.out.println(" 2. ���̴� ��ȸ");
		System.out.println(" 3. ���̴� ����");
		System.out.println(" 4. ���̴� ����");
		System.out.println(" 0. ���� �޴�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminCreateService.riderCreate();
		case 2:
			return adminReadService.riderRead();
		case 3:
			return adminUpdateService.riderUpdate();
		case 4:
			return adminDeleteService.riderDelete();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * �߸��� �����Դϴ�.");
			System.out.println();
			return View.ADMIN_RIDER_MANAGEMENT;
		}
	}

	// ������ �α׾ƿ�
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
			EnterUtil.enterNext(1);

			// ���� �ʱ�ȭ
//			System.out.println(Controller.sessionStorage.toString());
			Controller.sessionStorage.put("login", null);
//			Controller.sessionStorage.remove("login");
			Controller.sessionStorage.put("loginInfo", null);
//			Controller.sessionStorage.remove("loginInfo");
//			System.out.println(Controller.sessionStorage.toString());

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * ������ ȭ������ ���ư��ϴ�.");
			return View.ADMIN_HOME;
		} else {
			System.out.println(" >> �ùٸ� �Է��� �ƴմϴ�. �ٽ� �Է��ϼ���.");
			return View.ADMIN_LOGOUT;
		}
	}

}
