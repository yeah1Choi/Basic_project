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

	// °ü¸®ÀÚ È¨
	public int home() {
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" °ü¸®ÀÚ È­¸é");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" 1. È¸¿øÁ¤º¸ °ü¸®");
		System.out.println(" 2. °¡°Ô ¸®½ºÆ® °ü¸®");
		System.out.println(" 3. ¸Þ´º °ü¸®");
		System.out.println(" 4. ÁÖ¹®³»¿ª °ü¸®");
		System.out.println(" 5. ¶óÀÌ´õ °ü¸®");
		System.out.println(" 0. ·Î±×¾Æ¿ô");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.print("ÀÔ·Â >> ");

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
			System.out.println("Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù.");
			return View.ADMIN_HOME;
		}
	}

	// È¸¿øÁ¤º¸ °ü¸® ¸Þ¼­µå
	public int memManage() {
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" È¸¿øÁ¤º¸ °ü¸®");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" 1. È¸¿øÁ¤º¸ Á¶È¸");
		System.out.println(" 2. È¸¿øÁ¤º¸ ¼öÁ¤");
		System.out.println(" 3. È¸¿øÁ¤º¸ »èÁ¦");
		System.out.println(" 0. ÀÌÀü ¸Þ´º");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
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
			System.out.println(" * Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù.");
			System.out.println();
			return View.ADMIN_MEM_MANAGEMENT;
		}
	}

	// °¡°Ô ¸®½ºÆ® °ü¸® ¸Þ¼­µå
	public int storeManage() {
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" °¡°Ô ¸®½ºÆ® °ü¸®");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" 1. °¡°Ô ¸®½ºÆ® µî·Ï");
		System.out.println(" 2. °¡°Ô ¸®½ºÆ® Á¶È¸");
		System.out.println(" 3. °¡°Ô ¸®½ºÆ® ¼öÁ¤");
		System.out.println(" 4. °¡°Ô ¸®½ºÆ® »èÁ¦");
		System.out.println(" 0. ÀÌÀü ¸Þ´º");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
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
			System.out.println(" * Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù.");
			System.out.println();
			return View.ADMIN_STORE_MANAGEMENT;
		}
	}

	// ¸Þ´º °ü¸®
	public int menuManage() {
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" ¸Þ´º °ü¸®");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" 1. ¸Þ´º µî·Ï");
		System.out.println(" 2. ¸Þ´º Á¶È¸");
		System.out.println(" 3. ¸Þ´º ¼öÁ¤");
		System.out.println(" 4. ¸Þ´º »èÁ¦");
		System.out.println(" 0. ÀÌÀü ¸Þ´º");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
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
			System.out.println(" * Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù.");
			System.out.println();
			return View.ADMIN_MENU_MANAGEMENT;
		}
	}

	// ÁÖ¹®³»¿ª °ü¸®
	public int orderhistoManage() {
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" ÁÖ¹®³»¿ª °ü¸®");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" 1. ÁÖ¹®³»¿ª Á¶È¸");
		System.out.println(" 0. ÀÌÀü ¸Þ´º");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.print(" >> ");

		switch (ScanUtil.nextIntegerLine()) {
		case 1:
			return adminReadService.orderhistoRead();
		case 0:
			return View.ADMIN_HOME;
		default:
			System.out.println();
			System.out.println(" * Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù.");
			System.out.println();
			return View.ADMIN_ORDERHISTORY;
		}
	}

	// ¶óÀÌ´õ °ü¸®
	public int riderManage() {
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" ¶óÀÌ´õ °ü¸®");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" 1. ¶óÀÌ´õ µî·Ï");
		System.out.println(" 2. ¶óÀÌ´õ Á¶È¸");
		System.out.println(" 3. ¶óÀÌ´õ ¼öÁ¤");
		System.out.println(" 4. ¶óÀÌ´õ »èÁ¦");
		System.out.println(" 0. ÀÌÀü ¸Þ´º");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
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
			System.out.println(" * Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù.");
			System.out.println();
			return View.ADMIN_RIDER_MANAGEMENT;
		}
	}

	// °ü¸®ÀÚ ·Î±×¾Æ¿ô
	public int logout() {
		String choice = "";

		while (true) {
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" ·Î±×¾Æ¿ô");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println();
			System.out.println(" * ·Î±×¾Æ¿ô ÇÏ½Ã°Ú½À´Ï±î? ( y / n )");
			System.out.println();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.print(" >> ");
			choice = ScanUtil.nextLine();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			if (ValidationUtil.validationYN(choice))
				break;
		}

		if (choice.equals("y")) {
			Object loginInfoObj = Controller.sessionStorage.get("loginInfo");
			Map<String, Object> loginInfo = (Map<String, Object>) loginInfoObj;
			System.out.println("");
			System.out.println("  [ " + loginInfo.get("MEMNM") + " ] ´Ô ·Î±×¾Æ¿ô µÇ¾ú½À´Ï´Ù.");
			System.out.println("");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("");
			System.out.println(" << ÀÌ¿ëÇØ ÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù >> ");
			System.out.println();
			EnterUtil.enterNext(1);

			// ¼¼¼Ç ÃÊ±âÈ­
//			System.out.println(Controller.sessionStorage.toString());
			Controller.sessionStorage.put("login", null);
//			Controller.sessionStorage.remove("login");
			Controller.sessionStorage.put("loginInfo", null);
//			Controller.sessionStorage.remove("loginInfo");
//			System.out.println(Controller.sessionStorage.toString());

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * °ü¸®ÀÚ È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			return View.ADMIN_HOME;
		} else {
			System.out.println(" >> ¿Ã¹Ù¸¥ ÀÔ·ÂÀÌ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			return View.ADMIN_LOGOUT;
		}
	}

}
