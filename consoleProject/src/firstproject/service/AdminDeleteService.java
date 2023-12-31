package firstproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.dao.AdminCreateDAO;
import firstproject.dao.AdminDeleteDAO;
import firstproject.dao.AdminReadDAO;
import firstproject.util.EnterUtil;
import firstproject.util.GapUtil;
import firstproject.util.NullCheckUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class AdminDeleteService {

	private static AdminDeleteService instance = null;

	private AdminDeleteService() {
	}

	public static AdminDeleteService getInstance() {
		if (instance == null)
			instance = new AdminDeleteService();
		return instance;
	}

	AdminDeleteDAO adminDeleteDAO = AdminDeleteDAO.getInstance();
	AdminReadDAO adminReadDAO = AdminReadDAO.getInstance();
	AdminCreateDAO adminCreateDAO = AdminCreateDAO.getInstance();
	GapUtil gapUtil = GapUtil.getInstance();

	public int memDelete() {
		String memIDstr = ""; // �蛾� 嬴檜蛤
		String deleteMem = ""; // 詹幗 餉薯

		List<Map<String, Object>> memALL = adminReadDAO.memALL();

		if (NullCheckUtil.isEmpty(memALL)) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("蛔煙脹 �蛾衋� 橈蝗棲棻!");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		} else {
			int maxIdLen = gapUtil.gapFullCnt(memALL, "MEMID");
			int maxNmLen = gapUtil.gapFullCnt(memALL, "MEMNM");
			int maxPwLen = gapUtil.gapFullCnt(memALL, "MEMPW");
			int maxAddLen = gapUtil.gapFullCnt(memALL, "MEMADD");
			int maxLvLen = gapUtil.gapFullCnt(memALL, "MEMLV");
			int maxBalLen = gapUtil.gapFullCnt(memALL, "BALANCE");
			int maxTelLen = gapUtil.gapFullCnt(memALL, "MEMTEL");

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("�蛾� 餉薯");
			System.out.println("1. �蛾蠷ぜ拑� 殮溘");
			System.out.println("2. �蛾籪� 匐儀");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int memChoice = ScanUtil.nextIntegerLine();
			if (memChoice == 1) {
				while (true) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("餉薯ж褒 �蛾� 嬴檜蛤蒂 殮溘п 輿撮蹂.");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.print(" >> ");
					memIDstr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationID(memIDstr))
						break;
				}
			} else if (memChoice == 2) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 匐儀ж褒 �蛾� 檜葷擊 殮溘п 輿撮蹂.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				String memNMstr = ScanUtil.nextLine();
				List<Object> searchNM = new ArrayList<>();
				searchNM.add(memNMstr);
				List<Map<String, Object>> memberInfo = adminReadDAO.memSearchFromNm(searchNM);
				if (NullCheckUtil.isEmpty(memberInfo)) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("蛔煙脹 �蛾衋� 橈蝗棲棻!");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				} else {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println(
							" 嬴檜蛤              檜葷       綠塵廓��            輿模                                        濤擋     �蛾禶劃�    瞪�食醽�");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					for (Map<String, Object> item : memberInfo) {
						int memIDLen = gapUtil.gapCnt(item, "MEMID");
						int memNMLen = gapUtil.gapCnt(item, "MEMNM");
						int memPWLen = gapUtil.gapCnt(item, "MEMPW");
						int memADDLen = gapUtil.gapCnt(item, "MEMADD");
						int memLVLen = gapUtil.gapCnt(item, "MEMLV");
						int memBALLen = gapUtil.gapCnt(item, "BALANCE");
						int memTELLen = gapUtil.gapCnt(item, "MEMTEL");

						StringBuilder memID = gapUtil.gapFullSpace(String.valueOf(item.get("MEMID")), maxIdLen,
								memIDLen);
						StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")), maxNmLen,
								memNMLen);
						StringBuilder memPW = gapUtil.gapFullSpace(String.valueOf(item.get("MEMPW")), maxPwLen,
								memPWLen);
						StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")), maxAddLen,
								memADDLen);
						StringBuilder memLV = gapUtil.gapFullSpace(String.valueOf(item.get("MEMLV")), maxLvLen,
								memLVLen);
						StringBuilder memBAL = gapUtil.gapFullSpace(String.valueOf(item.get("BALANCE")), maxBalLen,
								memBALLen);
						StringBuilder memTEL = gapUtil.gapFullSpace(String.valueOf(item.get("MEMTEL")), maxTelLen,
								memTELLen);

						System.out.printf(
								"%-" + maxIdLen + "s%-" + maxNmLen + "s%-" + maxPwLen + "s%-" + maxAddLen + "s%-"
										+ maxBalLen + "s%-" + maxLvLen + "s%-" + maxTelLen + "s\n",
								memID, memNM, memPW, memADD, memBAL, memLV, memTEL);
					}
				}
				EnterUtil.enterNext(2);
				return View.ADMIN_MEM_MANAGEMENT;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" [ " + memIDstr + " ] �蛾衋� 餉薯ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			deleteMem = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteMem))
				break;
		}

		if (deleteMem.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("�蛾� 餉薯蒂 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_MEM_MANAGEMENT;
		} else if (deleteMem.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(memIDstr);
			Map<String, Object> memLvStr = adminDeleteDAO.searchMemLv(param);
			int memLv = Integer.parseInt(memLvStr.get("MEMLV").toString());
			if (memLv == 9) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("婦葬濠 啗薑擎 餉薯й 熱 橈蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				int isSuccess = adminDeleteDAO.deleteMember(param);
				if (isSuccess > 0) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("�蛾� 餉薯陛 諫猿腎歷蝗棲棻!");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					EnterUtil.enterNext(2);
				} else {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println(">> �蛾� 餉薯 褒ぬ! <<");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					EnterUtil.enterNext(2);
				}
			}
		}

		return View.ADMIN_MEM_MANAGEMENT;
	}

	public int storeDelete() {
		String storeCodeStr = ""; // 機羹 囀萄
		String deleteStore = ""; // 機羹 餉薯

		List<Map<String, Object>> storeALL = adminReadDAO.storeALL();

		if (NullCheckUtil.isEmpty(storeALL)) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("蛔煙脹 機羹陛 橈蝗棲棻!");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		} else {
			int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
			int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
			int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
			int maxMinLen = gapUtil.gapFullCnt(storeALL, "MINORDER");
			int maxPackLen = gapUtil.gapFullCnt(storeALL, "PACKYN");
			int maxCloseLen = gapUtil.gapFullCnt(storeALL, "CLOSEYN");
			int maxCateLen = gapUtil.gapFullCnt(storeALL, "CATECODE");
			int maxDeliLen = gapUtil.gapFullCnt(storeALL, "DELIYN");

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("機羹 餉薯");
			System.out.println("1. 機羹囀萄 殮溘");
			System.out.println("2. 機羹貲 匐儀");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int storeChoice = ScanUtil.nextIntegerLine();
			if (storeChoice == 1) {
				while (true) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("餉薯ж褒 機羹囀萄蒂 殮溘п 輿撮蹂.");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.print(" >> ");
					storeCodeStr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationCode(storeCodeStr))
						break;
				}
			} else if (storeChoice == 2) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 匐儀ж褒 機羹貲擊 殮溘п 輿撮蹂.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				String storeNMstr = ScanUtil.nextLine();
				List<Object> searchStoName = new ArrayList<>();
				searchStoName.add(storeNMstr);
				List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoName);
				if (NullCheckUtil.isEmpty(storeInfo)) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("蛔煙脹 機羹陛 橈蝗棲棻!");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				} else {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("機羹囀萄    機羹貲         機羹輿模     譆模輿僥旎擋     ん濰陛棟    衙濰螃Ъ     輿僥陛棟罹睡     蘋纔堅葬囀萄");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					for (Map<String, Object> item : storeInfo) {
						int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
						int storeNmLen = gapUtil.gapCnt("STONM");
						int storeAddLen = gapUtil.gapCnt("STOADD");
						int storeMinLen = gapUtil.gapCnt("MINORDER");
						int storePackLen = gapUtil.gapCnt("PACKYN");
						int storeCloseLen = gapUtil.gapCnt("CLOSEYN");
						int storeCateLen = gapUtil.gapCnt("CATECODE");
						int storeDeliLen = gapUtil.gapCnt("DELIYN");

						StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")),
								maxStoCodeLen, storeStoCodeLen);
						StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen,
								storeNmLen);
						StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
								storeAddLen);
						StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(item.get("MINORDER")), maxMinLen,
								storeMinLen);
						StringBuilder memPACK = gapUtil.gapFullSpace(String.valueOf(item.get("PACKYN")), maxPackLen,
								storePackLen);
						StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(item.get("CLOSEYN")), maxCloseLen,
								storeCloseLen);
						StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATECODE")), maxCateLen,
								storeCateLen);
						StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(item.get("DELIYN")), maxDeliLen,
								storeDeliLen);
						System.out.printf(
								"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen + "s%-"
										+ maxPackLen + "s%-" + maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxCateLen
										+ "s\n",
								memSTOCODE, memNM, memADD, memMIN, memPACK, memCLOSE, memDELI, memCATE);
					}
				}
				EnterUtil.enterNext(2);
				return View.ADMIN_STORE_MANAGEMENT;
			}
		}

		List<Object> searchStoNm = new ArrayList<>();
		searchStoNm.add(storeCodeStr);
		Map<String, Object> stoName = adminCreateDAO.searchStoreName(searchStoNm);
		String stoNameStr = stoName.get("STONM").toString();

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" [ " + stoNameStr + " ] 機羹蒂 餉薯ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			deleteStore = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteStore))
				break;
		}

		if (deleteStore.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("機羹 餉薯蒂 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_STORE_MANAGEMENT;
		} else if (deleteStore.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(storeCodeStr);
			int isSuccess = adminDeleteDAO.deleteStore(param);
			if (isSuccess > 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("機羹 餉薯陛 諫猿腎歷蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(">> 機羹 餉薯 褒ぬ! <<");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuDelete() {
		String menuCodeStr = ""; // 詭景 囀萄
		String deleteMenu = ""; // 詭景 餉薯

		List<Map<String, Object>> menuALL = adminReadDAO.menuALL();

		if (NullCheckUtil.isEmpty(menuALL)) {
			System.out.println("蛔煙脹 詭景陛 橈蝗棲棻!");
		} else {
			int maxMenuCodeLen = gapUtil.gapFullCnt(menuALL, "MENUCODE");
			int maxNmLen = gapUtil.gapFullCnt(menuALL, "MENUNM");
			int maxPriceLen = gapUtil.gapFullCnt(menuALL, "MENUPRICE");
			int maxQtyLen = gapUtil.gapFullCnt(menuALL, "REMAINQTY");
			int maxStoCodeLen = gapUtil.gapFullCnt(menuALL, "STOCODE");

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景 餉薯");
			System.out.println("1. 詭景囀萄 殮溘");
			System.out.println("2. 詭景貲 匐儀");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int menuChoice = ScanUtil.nextIntegerLine();
			if (menuChoice == 1) {
				while (true) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("餉薯ж褒 詭景囀萄蒂 殮溘п 輿撮蹂.");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.print(" >> ");
					menuCodeStr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationCode(menuCodeStr))
						break;
				}
			} else if (menuChoice == 2) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 匐儀ж褒 詭景貲擊 殮溘п 輿撮蹂.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				String storeMenuNameStr = ScanUtil.nextLine();
				List<Object> searchMenuName = new ArrayList<>();
				searchMenuName.add(storeMenuNameStr);
				List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromMenuName(searchMenuName);
				if (NullCheckUtil.isEmpty(storeInfo)) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("蛔煙脹 詭景陛 橈蝗棲棻!");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				} else {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("詭景囀萄        詭景貲                                         陛問     營堅   機羹囀萄");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					for (Map<String, Object> item : storeInfo) {
						int memMenuCodeLen = gapUtil.gapCnt(item, "MENUCODE");
						int memNMLen = gapUtil.gapCnt(item, "MENUNM");
						int memPRICELen = gapUtil.gapCnt(item, "MENUPRICE");
						int memQTYLen = gapUtil.gapCnt(item, "REMAINQTY");
						int memStoCodeLen = gapUtil.gapCnt(item, "STOCODE");

						StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(item.get("MENUCODE")),
								maxMenuCodeLen, memMenuCodeLen);
						StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")), maxNmLen,
								memNMLen);
						StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(item.get("MENUPRICE")),
								maxPriceLen, memPRICELen);
						StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(item.get("REMAINQTY")), maxQtyLen,
								memQTYLen);
						StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")),
								maxStoCodeLen, memStoCodeLen);
						System.out.printf(
								"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
										+ "s%" + maxStoCodeLen + "s\n",
								memMenuCode, memNM, memPRICE, memQTY, memStoCode);
					}
				}
				EnterUtil.enterNext(2);
				return View.ADMIN_MENU_MANAGEMENT;
			}
		}

		List<Object> searchMenuName = new ArrayList<>();
		searchMenuName.add(menuCodeStr);
		Map<String, Object> menuName = adminDeleteDAO.searchMenuName(searchMenuName);
		String menuNameStr = menuName.get("MENUNM").toString();

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" [ " + menuNameStr + " ] 詭景蒂 餉薯ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			deleteMenu = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteMenu))
				break;
		}

		if (deleteMenu.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("詭景 餉薯蒂 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_MENU_MANAGEMENT;
		} else if (deleteMenu.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(menuCodeStr);
			int isSuccess = adminDeleteDAO.deleteMenu(param);
			if (isSuccess > 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("詭景 餉薯陛 諫猿腎歷蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(">> 詭景 餉薯 褒ぬ! <<");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int riderDelete() {
		String riderCodeStr = ""; // 塭檜渦 囀萄
		String deleteRider = ""; // 塭檜渦 餉薯

		List<Map<String, Object>> riderALL = adminReadDAO.riderALL();
		List<Map<String, Object>> storeALL = adminReadDAO.storeALL();

		if (NullCheckUtil.isEmpty(riderALL)) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("蛔煙脹 塭檜渦陛 橈蝗棲棻!");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		} else if (NullCheckUtil.isEmpty(storeALL)) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("蛔煙脹 陛啪陛 橈蝗棲棻!");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		} else {
			int maxRideCodeLen = gapUtil.gapFullCnt(riderALL, "RIDCODE");
			int maxStoNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
			int maxStoAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("塭檜渦 餉薯");
			System.out.println("1. 塭檜渦囀萄 殮溘");
			System.out.println("2. 機羹貲 匐儀");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int menuChoice = ScanUtil.nextIntegerLine();
			if (menuChoice == 1) {
				while (true) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("餉薯ж褒 塭檜渦囀萄蒂 殮溘п 輿撮蹂.");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.print(" >> ");
					riderCodeStr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationCode(riderCodeStr))
						break;
				}
			} else if (menuChoice == 2) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 匐儀ж褒 機羹貲擊 殮溘п 輿撮蹂.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				String storeNameStr = ScanUtil.nextLine();
				List<Object> searchStoreName = new ArrayList<>();
				searchStoreName.add(storeNameStr);
				List<Map<String, Object>> storeInfo = adminDeleteDAO.riderSearchFromStoreName(searchStoreName);
				if (NullCheckUtil.isEmpty(storeInfo)) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("蛔煙脹 塭檜渦陛 橈蝗棲棻!");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				} else {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println("塭檜渦囀萄   機羹貲         機羹輿模");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式");
					for (Map<String, Object> item : storeInfo) {
						int memRideCodeLen = gapUtil.gapCnt(item, "RIDCODE");
						int memStoreNmLen = gapUtil.gapCnt(item, "STONM");
						int memStoreAddLen = gapUtil.gapCnt(item, "STOADD");

						StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(item.get("RIDCODE")),
								maxRideCodeLen, memRideCodeLen);
						StringBuilder memStoreNm = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxStoNmLen,
								memStoreNmLen);
						StringBuilder memStoreAdd = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")),
								maxStoAddLen, memStoreAddLen);
						System.out.printf("%-" + maxRideCodeLen + "s%-" + maxStoNmLen + "s%-" + maxStoAddLen + "s\n",
								memRideCode, memStoreNm, memStoreAdd);

					}
				}
				EnterUtil.enterNext(2);
				return View.ADMIN_RIDER_MANAGEMENT;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" [ " + riderCodeStr + " ] 塭檜渦蒂 餉薯ж衛啊蝗棲梱? (y / n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			deleteRider = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteRider))
				break;
		}

		if (deleteRider.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("塭檜渦 餉薯蒂 鏃模м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
			return View.ADMIN_RIDER_MANAGEMENT;
		} else if (deleteRider.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(riderCodeStr);
			int isSuccess = adminDeleteDAO.deleteRider(param);
			if (isSuccess > 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("塭檜渦 餉薯陛 諫猿腎歷蝗棲棻!");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(">> 塭檜渦 餉薯 褒ぬ! <<");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
