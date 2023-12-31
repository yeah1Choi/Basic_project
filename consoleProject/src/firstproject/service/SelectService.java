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

	// ORDER_HOME戲煎 鱔мл
//	public int takeout() { 
//
//		System.out.println("--- 輿僥 寞衝 摹鷗 ---");
//		System.out.println("   1. 寡殖");
//		System.out.println("   2. ん濰");
//		System.out.println("------------------");
//		System.out.println("   0. 葆檜む檜雖");
//
//		System.out.println();
//		System.out.print("殮溘 >> ");
//		choice = ScanUtil.nextInt();
//		if (choice == 1) {
//			return category(View.CATEGORY_DELI);
//		} else if (choice == 2) {
//			return category(View.CATEGORY_TAKE);
//		} else if (choice == 0) {
//			return category(View.USERPAGE);
//		} else {
//			System.out.println("澀跤脹 殮溘殮棲棻. 棻衛 殮溘ж撮蹂.");
//			return View.TAKEOUT;
//		}
//
//	}

	// �蛾� 煎斜嬴醒
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

			// 撮暮 蟾晦��
			Controller.sessionStorage.put("login", null);
			Controller.sessionStorage.put("loginInfo", null);

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * 餌辨濠 �飛橉虞� 給嬴骨棲棻.");
			return View.ORDER_HOME;
		} else {
			System.out.println(" >> 螢夥艇 殮溘檜 嬴椎棲棻. 棻衛 殮溘ж撮蹂.");
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

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [蘋纔堅葬 摹鷗]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     1. и衝            2. 曄衝                     3. 醞衝");
			System.out.println("     4. 橾衝            5. 蘋む-蛤盪お          9. 詭檣��");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" 殮溘 >> ");

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
				System.out.println(" ≦ 澀跤脹 殮溘殮棲棻. 寡殖/ん濰 摹鷗 �飛橉虞� 剩橫骨棲棻.");
				return View.ORDER_HOME;
			}

		case View.CATEGORY_TAKE:
			takeYN = "Y";
			Controller.sessionStorage.put("deliOrTake", null);
			Controller.sessionStorage.put("deliOrTake", "takeYN");
//			System.out.println(Controller.sessionStorage.toString());

			System.out.println();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [蘋纔堅葬 摹鷗]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     1. и衝      2. 曄衝                3. 醞衝");
			System.out.println("     4. 橾衝      5. 蘋む-蛤盪お     9. 詭檣��");
			System.out.println();
			System.out.print(" 廓�� 殮溘 >> ");

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
				System.out.println(" ≦ 澀跤脹 殮溘殮棲棻. 寡殖/ん濰 摹鷗 �飛橉虞� 剩橫骨棲棻.");
				return View.ORDER_HOME;
			}
		}
		return deliOrTake;
	}

	public int korean() {
		if (deliYN == "Y") {
			List<Map<String, Object>> koreandeli = selectDAO.koreandelilist();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [и衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			// и衝 葬蝶お菟(牖廓([1])\t擠衝薄檜葷\t譆模旎擋輿僥\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠)
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [и衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [曄衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [曄衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [醞衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [醞衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [橾衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [橾衝 擠衝薄]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [蛤盪お/蘋む]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [蛤盪お/蘋む]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 擠衝薄\t衙濰螃Ъ\t譆模輿僥旎擋\t寡殖陛棟嶸鼠\tん濰陛棟嶸鼠\t衙濰輿模");
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
