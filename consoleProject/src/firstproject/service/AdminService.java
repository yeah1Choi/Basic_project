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

	// 婦葬濠 ��
	public int home() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 婦葬濠 �飛�");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 1. �蛾讔內� 婦葬");
		System.out.println(" 2. 陛啪 葬蝶お 婦葬");
		System.out.println(" 3. 詭景 婦葬");
		System.out.println(" 4. 輿僥頂羲 婦葬");
		System.out.println(" 5. 塭檜渦 婦葬");
		System.out.println(" 0. 煎斜嬴醒");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print("殮溘 >> ");

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
			System.out.println("澀跤脹 蕾斬殮棲棻.");
			return View.ADMIN_HOME;
		}
	}

	// �蛾讔內� 婦葬 詭憮萄
	public int memManage() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" �蛾讔內� 婦葬");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 1. �蛾讔內� 褻��");
		System.out.println(" 2. �蛾讔內� 熱薑");
		System.out.println(" 3. �蛾讔內� 餉薯");
		System.out.println(" 0. 檜瞪 詭景");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
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
			System.out.println(" * 澀跤脹 蕾斬殮棲棻.");
			System.out.println();
			return View.ADMIN_MEM_MANAGEMENT;
		}
	}

	// 陛啪 葬蝶お 婦葬 詭憮萄
	public int storeManage() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 陛啪 葬蝶お 婦葬");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 1. 陛啪 葬蝶お 蛔煙");
		System.out.println(" 2. 陛啪 葬蝶お 褻��");
		System.out.println(" 3. 陛啪 葬蝶お 熱薑");
		System.out.println(" 4. 陛啪 葬蝶お 餉薯");
		System.out.println(" 0. 檜瞪 詭景");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
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
			System.out.println(" * 澀跤脹 蕾斬殮棲棻.");
			System.out.println();
			return View.ADMIN_STORE_MANAGEMENT;
		}
	}

	// 詭景 婦葬
	public int menuManage() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 詭景 婦葬");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 1. 詭景 蛔煙");
		System.out.println(" 2. 詭景 褻��");
		System.out.println(" 3. 詭景 熱薑");
		System.out.println(" 4. 詭景 餉薯");
		System.out.println(" 0. 檜瞪 詭景");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
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
			System.out.println(" * 澀跤脹 蕾斬殮棲棻.");
			System.out.println();
			return View.ADMIN_MENU_MANAGEMENT;
		}
	}

	// 輿僥頂羲 婦葬
	public int orderhistoManage() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 輿僥頂羲 婦葬");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 1. 輿僥頂羲 褻��");
		System.out.println(" 0. 檜瞪 詭景");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminReadService.orderhistoRead();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * 澀跤脹 蕾斬殮棲棻.");
			System.out.println();
			return View.ADMIN_ORDERHISTORY;
		}
	}

	// 塭檜渦 婦葬
	public int riderManage() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 塭檜渦 婦葬");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 1. 塭檜渦 蛔煙");
		System.out.println(" 2. 塭檜渦 褻��");
		System.out.println(" 3. 塭檜渦 熱薑");
		System.out.println(" 4. 塭檜渦 餉薯");
		System.out.println(" 0. 檜瞪 詭景");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
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
			System.out.println(" * 澀跤脹 蕾斬殮棲棻.");
			System.out.println();
			return View.ADMIN_RIDER_MANAGEMENT;
		}
	}

	// 婦葬濠 煎斜嬴醒
	public int logout() {
		String choice = "";

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 煎斜嬴醒");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println();
			System.out.println(" * 煎斜嬴醒 ж衛啊蝗棲梱? ( y / n )");
			System.out.println();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			choice = ScanUtil.nextLine();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			if (ValidationUtil.validationYN(choice))
				break;
		}

		if (choice.equals("y")) {
			Object loginInfoObj = Controller.sessionStorage.get("loginInfo");
			Map<String, Object> loginInfo = (Map<String, Object>) loginInfoObj;
			System.out.println("");
			System.out.println("  [ " + loginInfo.get("MEMNM") + " ] 椒 煎斜嬴醒 腎歷蝗棲棻.");
			System.out.println("");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("");
			System.out.println(" << 檜辨п 輿敷憮 馬餌м棲棻 >> ");
			System.out.println();
			EnterUtil.enterNext(1);

			// 撮暮 蟾晦��
//			System.out.println(Controller.sessionStorage.toString());
			Controller.sessionStorage.put("login", null);
//			Controller.sessionStorage.remove("login");
			Controller.sessionStorage.put("loginInfo", null);
//			Controller.sessionStorage.remove("loginInfo");
//			System.out.println(Controller.sessionStorage.toString());

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * 婦葬濠 �飛橉虞� 給嬴骨棲棻.");
			return View.ADMIN_HOME;
		} else {
			System.out.println(" >> 螢夥艇 殮溘檜 嬴椎棲棻. 棻衛 殮溘ж撮蹂.");
			return View.ADMIN_LOGOUT;
		}
	}

}
