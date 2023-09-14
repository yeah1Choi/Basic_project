package firstproject.service;

import java.util.List;
import java.util.Map;

import firstproject.controller.Controller;
import firstproject.dao.SelectDAO;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class SelectService {

	private static SelectService instance = null;

	private SelectService() {
	}

	public static SelectService getInstance() {
		if (instance == null)
			instance = new SelectService();
		return instance;
	}

	SelectDAO selectDAO = SelectDAO.getInstance();
	int choice = 0;
	String deliYN = null;
	String takeYN = null;

	// ORDER_HOMEÀ¸·Î ÅëÇÕÇÔ
//	public int takeout() { 
//
//		System.out.println("--- ÁÖ¹® ¹æ½Ä ¼±ÅÃ ---");
//		System.out.println("   1. ¹è´Þ");
//		System.out.println("   2. Æ÷Àå");
//		System.out.println("------------------");
//		System.out.println("   0. ¸¶ÀÌÆäÀÌÁö");
//
//		System.out.println();
//		System.out.print("ÀÔ·Â >> ");
//		choice = ScanUtil.nextInt();
//		if (choice == 1) {
//			return category(View.CATEGORY_DELI);
//		} else if (choice == 2) {
//			return category(View.CATEGORY_TAKE);
//		} else if (choice == 0) {
//			return category(View.USERPAGE);
//		} else {
//			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
//			return View.TAKEOUT;
//		}
//
//	}

	// È¸¿ø ·Î±×¾Æ¿ô
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

			// ¼¼¼Ç ÃÊ±âÈ­
			Controller.sessionStorage.put("login", null);
			Controller.sessionStorage.put("loginInfo", null);

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * »ç¿ëÀÚ È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			return View.ORDER_HOME;
		} else {
			System.out.println(" >> ¿Ã¹Ù¸¥ ÀÔ·ÂÀÌ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			return View.ORDER_LOGOUT;
		}
	}

	public int category(int deliOrTake) {
		switch (deliOrTake) {
		case View.CATEGORY_DELI:
			deliYN = "Y";
			Controller.sessionStorage.put("deliOrTake", null);
			Controller.sessionStorage.put("deliOrTake", "deliYN");
//			System.out.println(Controller.sessionStorage.toString());

			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [Ä«Å×°í¸® ¼±ÅÃ]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     1. ÇÑ½Ä            2. ¾ç½Ä                     3. Áß½Ä");
			System.out.println("     4. ÀÏ½Ä            5. Ä«Æä-µðÀúÆ®          9. ¸ÞÀÎÈ¨");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.print(" ÀÔ·Â >> ");

			switch (ScanUtil.nextInt()) {
			case 1:
				return View.CATEGORY_KOREAN;
			case 2:
				return View.CATEGORY_WESTERN;
			case 3:
				return View.CATEGORY_CHINESE;
			case 4:
				return View.CATEGORY_JAPANESE;
			case 5:
				return View.CATEGORY_DESSERT;
			case 9:
				return View.ORDER_HOME;
			default:
				System.out.println(" ¡Ø Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù. ¹è´Þ/Æ÷Àå ¼±ÅÃ È­¸éÀ¸·Î ³Ñ¾î°©´Ï´Ù.");
				return View.ORDER_HOME;
			}

		case View.CATEGORY_TAKE:
			takeYN = "Y";
			Controller.sessionStorage.put("deliOrTake", null);
			Controller.sessionStorage.put("deliOrTake", "takeYN");
//			System.out.println(Controller.sessionStorage.toString());

			System.out.println();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [Ä«Å×°í¸® ¼±ÅÃ]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     1. ÇÑ½Ä      2. ¾ç½Ä                3. Áß½Ä");
			System.out.println("     4. ÀÏ½Ä      5. Ä«Æä-µðÀúÆ®     9. ¸ÞÀÎÈ¨");
			System.out.println();
			System.out.print(" ¹øÈ£ ÀÔ·Â >> ");

			switch (ScanUtil.nextInt()) {
			case 1:
				return View.CATEGORY_KOREAN;
			case 2:
				return View.CATEGORY_WESTERN;
			case 3:
				return View.CATEGORY_CHINESE;
			case 4:
				return View.CATEGORY_JAPANESE;
			case 5:
				return View.CATEGORY_DESSERT;
			case 9:
				return View.ORDER_HOME;
			default:
				System.out.println(" ¡Ø Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù. ¹è´Þ/Æ÷Àå ¼±ÅÃ È­¸éÀ¸·Î ³Ñ¾î°©´Ï´Ù.");
				return View.ORDER_HOME;
			}
		}
		return deliOrTake;
	}

	public int korean() {
		if (deliYN == "Y") {
			List<Map<String, Object>> koreandeli = selectDAO.koreandelilist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [ÇÑ½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			// ÇÑ½Ä ¸®½ºÆ®µé(¼ø¹ø([1])\tÀ½½ÄÁ¡ÀÌ¸§\tÃÖ¼Ò±Ý¾×ÁÖ¹®\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«)
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : koreandeli) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			deliYN = null;
		}
		if (takeYN == "Y") {

			List<Map<String, Object>> koreantake = selectDAO.koreantakelist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [ÇÑ½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : koreantake) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			takeYN = null;
		}
		return View.MENU;

	}

	public int western() {
		if (deliYN == "Y") {
			List<Map<String, Object>> westerndeli = selectDAO.westerndelilist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [¾ç½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : westerndeli) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			deliYN = null;
		}
		if (takeYN == "Y") {

			List<Map<String, Object>> westerntake = selectDAO.westerntakelist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [¾ç½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : westerntake) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			takeYN = null;
		}
		return View.MENU;

	}

	public int chinese() {
		if (deliYN == "Y") {
			List<Map<String, Object>> chinesedeli = selectDAO.chinesedelilist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [Áß½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : chinesedeli) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			deliYN = null;
		}
		if (takeYN == "Y") {
			List<Map<String, Object>> chinesetake = selectDAO.chinesetakelist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [Áß½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : chinesetake) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			takeYN = null;
		}
		return View.MENU;

	}

	public int japanese() {
		if (deliYN == "Y") {
			List<Map<String, Object>> japanesedeli = selectDAO.japanesedelilist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [ÀÏ½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : japanesedeli) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			deliYN = null;
		}
		if (takeYN == "Y") {

			List<Map<String, Object>> japanesetake = selectDAO.japanesetakelist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [ÀÏ½Ä À½½ÄÁ¡]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : japanesetake) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			takeYN = null;
		}
		return View.MENU;

	}

	public int dessert() {
		if (deliYN == "Y") {
			List<Map<String, Object>> dessertdeli = selectDAO.dessertdelilist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [µðÀúÆ®/Ä«Æä]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : dessertdeli) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			deliYN = null;
		}
		if (takeYN == "Y") {

			List<Map<String, Object>> desserttake = selectDAO.desserttakelist();
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [µðÀúÆ®/Ä«Æä]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println(" À½½ÄÁ¡\t¸ÅÀå¿ÀÇÂ\tÃÖ¼ÒÁÖ¹®±Ý¾×\t¹è´Þ°¡´ÉÀ¯¹«\tÆ÷Àå°¡´ÉÀ¯¹«\t¸ÅÀåÁÖ¼Ò");
			for (Map<String, Object> item : desserttake) {
				System.out.printf(" %-14s", item.get("STONM"));
				System.out.printf("%-7s", item.get("CLOSEYN"));
				System.out.printf("%-9s", item.get("MINORDER"));
				System.out.printf("%-9s", item.get("DELIYN"));
				System.out.printf("%-9s", item.get("PACKYN"));
				System.out.printf("%-9s", item.get("STOADD"));
				System.out.println();
			}
			takeYN = null;
		}
		return View.MENU;

	}
}
