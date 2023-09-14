package firstproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.dao.AdminCreateDAO;
import firstproject.dao.AdminReadDAO;
import firstproject.util.EnterUtil;
import firstproject.util.GapUtil;
import firstproject.util.NullCheckUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class AdminCreateService {

	private static AdminCreateService instance = null;

	private AdminCreateService() {
	}

	public static AdminCreateService getInstance() {
		if (instance == null)
			instance = new AdminCreateService();
		return instance;
	}

	AdminCreateDAO adminCreateDAO = AdminCreateDAO.getInstance();
	AdminReadDAO adminReadDAO = AdminReadDAO.getInstance();
	GapUtil gapUtil = GapUtil.getInstance();

	public int storeCreate() {
		String CodeStr = ""; // 機羹囀萄
		String StoNm = ""; // 機羹貲
		String StoAdd = ""; // 褒薯機羹輿模 : daejeonRandGu
		long MinOrder = 0; // 譆模輿僥旎擋
		String DeliYn = ""; // 寡殖陛棟嶸鼠
		String PackYn = ""; // ん濰陛棟嶸鼠
		String CloseYn = ""; // 衙濰螃Ъ嶸鼠
		String CateCode = ""; // 蘋纔堅葬囀萄
		String createStore = ""; // 陛啪 儅撩

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("⑷營 陛啪陛 渠瞪惜羲衛 橫替 掘縑 嬪纂п 氈蝗棲梱?");
			System.out.println("1. 渠湯掘");
			System.out.println("2. 翕掘");
			System.out.println("3. 憮掘");
			System.out.println("4. 嶸撩掘");
			System.out.println("5. 醞掘");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int GuSelect = ScanUtil.nextIntegerLine();
			if (GuSelect == 1) {
				CodeStr = "AG";
				break;
			} else if (GuSelect == 2) {
				CodeStr = "DG";
				break;
			} else if (GuSelect == 3) {
				CodeStr = "SG";
				break;
			} else if (GuSelect == 4) {
				CodeStr = "YG";
				break;
			} else if (GuSelect == 5) {
				CodeStr = "JG";
				break;
			} else {
				System.out.println("螢夥煎 殮溘п 輿撮蹂.");
			}
		}

		String daejeonRandGu = "渠瞪惜羲衛 ";
		if (CodeStr.equals("AG")) {
			daejeonRandGu += "渠湯掘 ";
		} else if (CodeStr.equals("DG")) {
			daejeonRandGu += "翕掘 ";
		} else if (CodeStr.equals("SG")) {
			daejeonRandGu += "憮掘 ";
		} else if (CodeStr.equals("YG")) {
			daejeonRandGu += "嶸撩掘 ";
		} else if (CodeStr.equals("JG")) {
			daejeonRandGu += "醞掘 ";
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("⑷營 陛啪陛 橫替 蘋纔堅葬 機謙縑 樓ж褊棲梱?");
			System.out.println("1. и衝");
			System.out.println("2. 曄衝");
			System.out.println("3. 醞衝");
			System.out.println("4. 橾衝");
			System.out.println("5. 蛤盪お");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int CateSelect = ScanUtil.nextIntegerLine();
			if (CateSelect == 1) {
				CodeStr += "HS";
				CateCode = "HS";
				break;
			} else if (CateSelect == 2) {
				CodeStr += "YS";
				CateCode = "YS";
				break;
			} else if (CateSelect == 3) {
				CodeStr += "CS";
				CateCode = "CS";
				break;
			} else if (CateSelect == 4) {
				CodeStr += "JS";
				CateCode = "JS";
				break;
			} else if (CateSelect == 5) {
				CodeStr += "DS";
				CateCode = "DS";
				break;
			} else {
				System.out.println("螢夥煎 殮溘п 輿撮蹂.");
			}
		}

//		System.out.println("CodeStr : " + CodeStr);
		CodeStr += "%";
		List<Object> cdStr = new ArrayList<>();
		cdStr.add(CodeStr);
//		System.out.println("cdStr.get(0) : " + cdStr.get(0));
		List<Map<String, Object>> searchStoreCode = adminCreateDAO.searchStoreCode(cdStr);
		if (NullCheckUtil.isEmpty(searchStoreCode)) {
			CodeStr += "01";
			CodeStr = CodeStr.replace("%", "");
		} else {
//			System.out.println(searchStoreCode.toString());
			CodeStr += searchStoreCode.get(0).get("STOCODE").toString();
			CodeStr = CodeStr.replace("%", "");
//			System.out.println(CodeStr);
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("機羹貲擊 殮溘п 輿撮蹂.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			StoNm = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationStoreNM(StoNm))
				break;
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("機羹 釭該雖 輿模蒂 殮溘п 輿撮蹂.");
			System.out.println();
			System.out.println("殮溘 ⑽衝 : 褐輿模 晦遽");
			System.out.println("蕨衛 >> XX煎 00廓望 00");
			System.out.println();
			System.out.println("機濰 ⑷營 嬪纂 : " + daejeonRandGu);
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			StoAdd = ScanUtil.nextLine();
			if (ValidationUtil.validationStoreAddress(StoAdd)) {
				daejeonRandGu += StoAdd; // 褒薯 機羹輿模
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("譆模輿僥旎擋擊 殮溘п 輿撮蹂. (0 ~ 9999999999)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			MinOrder = ScanUtil.nextLong();
			if (ValidationUtil.validationMinOrder(MinOrder))
				break;
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("寡殖 陛棟 м棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			DeliYn = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(DeliYn)) {
				DeliYn = DeliYn.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("ん濰 陛棟 м棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			PackYn = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(PackYn)) {
				PackYn = PackYn.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("衙濰檜 螃Ъ 腎歷蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			CloseYn = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(CloseYn)) {
				CloseYn = CloseYn.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("陛啪蒂 蛔煙ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			createStore = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(createStore))
				break;
		}

		if (createStore.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("陛啪 蛔煙擊 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_STORE_MANAGEMENT;
		} else if (createStore.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(CodeStr);
			param.add(StoNm);
			param.add(daejeonRandGu);
			param.add(MinOrder);
			param.add(DeliYn);
			param.add(PackYn);
			param.add(CloseYn);
			param.add(CateCode);
			int isSuccess = adminCreateDAO.createStore(param);
			if (isSuccess > 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("陛啪 蛔煙檜 諫猿腎歷蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(">> 陛啪 蛔煙 褒ぬ! <<");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuCreate() {
		String menuCode = ""; // 詭景囀萄
		String menuNm = ""; // 詭景貲
		long menuPrice = 0; // 詭景陛問
		int remainQty = 0; // 濤罹熱榆
		String stoCode = ""; // 機羹囀萄

		String createMenu = ""; // 詭景 儅撩

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("橫替 陛啪縑憮 詭景蒂 蛔煙ж褊棲梱?");
		System.out.println("1. 機羹囀萄煎 蛔煙");
		System.out.println("2. 機羹貲戲煎 機羹囀萄 匐儀");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print(" >> ");
		int storeChoi = ScanUtil.nextIntegerLine();
		if (storeChoi == 1) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 機羹囀萄 殮溘");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				stoCode = ScanUtil.nextLine();
				if (ValidationUtil.validationCode(stoCode)) {
					menuCode = stoCode.substring(2, 4); // 蘋纔堅葬囀萄
					break;
				}
			}
		} else if (storeChoi == 2) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 機羹貲擊 殮溘п 輿撮蹂.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			String storeName = ScanUtil.nextLine();
			List<Object> searchStoreName = new ArrayList<>();
			searchStoreName.add(storeName);
			List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoreName);
			if (NullCheckUtil.isEmpty(storeInfo)) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("蛔煙脹 機羹陛 橈蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else {
				List<Map<String, Object>> storeALL = adminReadDAO.storeALL();
				int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
				int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
				int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("機羹囀萄    機羹貲         機羹輿模");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				for (Map<String, Object> item : storeInfo) {
					int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
					int storeNmLen = gapUtil.gapCnt("STONM");
					int storeAddLen = gapUtil.gapCnt("STOADD");

					StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")), maxStoCodeLen,
							storeStoCodeLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen, storeNmLen);
					StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
							storeAddLen);
					System.out.printf("%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s\n", memSTOCODE,
							memNM, memADD);
				}
			}
			EnterUtil.enterNext(2);
			return View.ADMIN_MENU_MANAGEMENT;
		}

		List<Object> searchStoNm = new ArrayList<>();
		searchStoNm.add(stoCode);
		Map<String, Object> stoName = adminCreateDAO.searchStoreName(searchStoNm);
		String stoNameStr = stoName.get("STONM").toString();

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("[ " + stoNameStr + " ] 陛啪縑 詭景蒂 蛔煙м棲棻.");
			System.out.println();
			System.out.println("鼠蝦 謙盟曖 詭景蒂 蛔煙ж衛啊蝗棲梱?");
			System.out.println();
			System.out.println("1. 渠ル詭景");
			System.out.println("2. 諒旋詭景");
			System.out.println("3. 撮お詭景");
			System.out.println("4. 餌檜萄詭景");
			System.out.println("5. 擠猿詭景");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int GuSelect = ScanUtil.nextIntegerLine();
			if (GuSelect == 1) {
				menuCode += "REP";
				break;
			} else if (GuSelect == 2) {
				menuCode += "SIG";
				break;
			} else if (GuSelect == 3) {
				menuCode += "SET";
				break;
			} else if (GuSelect == 4) {
				menuCode += "SID";
				break;
			} else if (GuSelect == 5) {
				menuCode += "BEV";
				break;
			} else {
				System.out.println("螢夥煎 殮溘п 輿撮蹂.");
			}
		}

		menuCode += "%";
		List<Object> cdStr = new ArrayList<>();
		cdStr.add(menuCode);
		List<Map<String, Object>> searchMenuCode = adminCreateDAO.searchMenuCode(cdStr);
		if (NullCheckUtil.isEmpty(searchMenuCode)) {
			menuCode += "01";
			menuCode = menuCode.replace("%", "");
		} else {
			menuCode += searchMenuCode.get(0).get("MENUCODE").toString();
			menuCode = menuCode.replace("%", "");
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景貲擊 殮溘п 輿撮蹂. (и旋虜 殮溘 陛棟, 嗥橫噙晦 ъ辨)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			menuNm = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationMenuName(menuNm))
				break;
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景陛問擊 殮溘п 輿撮蹂. (0 ~ 9999999999)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			menuPrice = ScanUtil.nextLong();
			System.out.println();
			if (ValidationUtil.validationPrice(menuPrice))
				break;
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景 熱榆擊 殮溘п 輿撮蹂. (0 ~ 999999999)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			remainQty = ScanUtil.nextInt();
			System.out.println();
			if (ValidationUtil.validationMenuRemainQty(remainQty))
				break;
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景蒂 蛔煙ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			createMenu = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(createMenu))
				break;
		}

		if (createMenu.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景 蛔煙擊 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_MENU_MANAGEMENT;
		} else if (createMenu.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(menuCode);
			param.add(menuNm);
			param.add(menuPrice);
			param.add(remainQty);
			param.add(stoCode);
			int isSuccess = adminCreateDAO.createMenu(param);
			if (isSuccess > 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("詭景 蛔煙檜 諫猿腎歷蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(">> 詭景 蛔煙 褒ぬ! <<");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int riderCreate() {
		String riderCode = ""; // 塭檜渦囀萄
		String AbseYN = ""; // 睡營嶸鼠
		long deliCost = 0; // 寡殖綠
		String stoCode = ""; // 機羹囀萄

		String createRider = ""; // 塭檜渦 儅撩

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("橫替 陛啪縑 塭檜渦煎 蛔煙ж褊棲梱?");
		System.out.println("1. 機羹囀萄煎 殮溘");
		System.out.println("2. 機羹貲 匐儀");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print(" >> ");
		int storeChoi = ScanUtil.nextIntegerLine();
		if (storeChoi == 1) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 機羹囀萄 殮溘");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				stoCode = ScanUtil.nextLine();
				if (ValidationUtil.validationCode(stoCode))
					break;
			}
		} else if (storeChoi == 2) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 機羹貲擊 殮溘п 輿撮蹂.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			String storeName = ScanUtil.nextLine();
			List<Object> searchStoreName = new ArrayList<>();
			searchStoreName.add(storeName);
			List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoreName);
			if (NullCheckUtil.isEmpty(storeInfo)) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("蛔煙脹 機羹陛 橈蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else {
				List<Map<String, Object>> storeALL = adminReadDAO.storeALL();
				int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
				int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
				int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("機羹囀萄    機羹貲         機羹輿模");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				for (Map<String, Object> item : storeInfo) {
					int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
					int storeNmLen = gapUtil.gapCnt("STONM");
					int storeAddLen = gapUtil.gapCnt("STOADD");

					StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")), maxStoCodeLen,
							storeStoCodeLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen, storeNmLen);
					StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
							storeAddLen);
					System.out.printf("%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s\n", memSTOCODE,
							memNM, memADD);
				}
			}
			EnterUtil.enterNext(2);
			return View.ADMIN_RIDER_MANAGEMENT;
		}

		List<Object> searchStoNm = new ArrayList<>();
		searchStoNm.add(stoCode);
		Map<String, Object> stoName = adminCreateDAO.searchStoreName(searchStoNm);
		String stoNameStr = stoName.get("STONM").toString();

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("[ " + stoNameStr + " ] 陛啪縑 塭檜渦蒂 蛔煙м棲棻.");
			System.out.println();
			System.out.println("⑷營 塭檜渦陛 渠瞪惜羲衛 橫替 掘縑 嬪纂п 氈蝗棲梱?");
			System.out.println("1. 渠湯掘");
			System.out.println("2. 翕掘");
			System.out.println("3. 憮掘");
			System.out.println("4. 嶸撩掘");
			System.out.println("5. 醞掘");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int GuSelect = ScanUtil.nextIntegerLine();
			if (GuSelect == 1) {
				riderCode = "AG";
				break;
			} else if (GuSelect == 2) {
				riderCode = "DG";
				break;
			} else if (GuSelect == 3) {
				riderCode = "SG";
				break;
			} else if (GuSelect == 4) {
				riderCode = "YG";
				break;
			} else if (GuSelect == 5) {
				riderCode = "JG";
				break;
			} else {
				System.out.println("螢夥煎 殮溘п 輿撮蹂.");
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("⑷營 斬鼠ж衛朝 機羹諦曖 啗擒 ⑽鷓陛 橫飩啪 腎褊棲梱?");
			System.out.println();
			System.out.println("1. 瞪樓啗擒");
			System.out.println("2. 諼輿啗擒");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int contractSelect = ScanUtil.nextIntegerLine();
			if (contractSelect == 1) {
				riderCode += "CON";
				break;
			} else if (contractSelect == 2) {
				riderCode += "OUT";
				break;
			} else {
				System.out.println("螢夥煎 殮溘п 輿撮蹂.");
			}
		}

		riderCode += "%";
		List<Object> cdStr = new ArrayList<>();
		cdStr.add(riderCode);
		List<Map<String, Object>> searchRiderCode = adminCreateDAO.searchRiderCode(cdStr);
		if (NullCheckUtil.isEmpty(searchRiderCode)) {
			riderCode += "01";
			riderCode = riderCode.replace("%", "");
		} else {
			riderCode += searchRiderCode.get(0).get("RIDCODE").toString();
			riderCode = riderCode.replace("%", "");
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("⑷營 寡殖檜 陛棟ж褊棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			AbseYN = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(AbseYN)) {
				AbseYN = AbseYN.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("寡殖綠蒂 殮溘п 輿撮蹂. (0 ~ 9999999999)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			deliCost = ScanUtil.nextLong();
			System.out.println();
			if (ValidationUtil.validationPrice(deliCost))
				break;
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("塭檜渦蒂 蛔煙ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			createRider = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(createRider))
				break;
		}

		if (createRider.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("塭檜渦 蛔煙擊 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_MENU_MANAGEMENT;
		} else if (createRider.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(riderCode);
			param.add(AbseYN);
			param.add(deliCost);
			param.add(stoCode);
			int isSuccess = adminCreateDAO.createRider(param);
			if (isSuccess > 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("塭檜渦 蛔煙檜 諫猿腎歷蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(">> 塭檜渦 蛔煙 褒ぬ! <<");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
