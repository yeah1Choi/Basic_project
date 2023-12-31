package firstproject.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import firstproject.controller.Controller;
import firstproject.dao.MemberDAO;
import firstproject.dao.SelectDAO;
import firstproject.util.EnterUtil;
import firstproject.util.NullCheckUtil;
import firstproject.util.ScanUtil;
import firstproject.util.View;

public class MenuService {

	private static MenuService instance = null;

	private MenuService() {
	}

	public static MenuService getInstance() {
		if (instance == null)
			instance = new MenuService();
		return instance;
	}

	MemberDAO memberDAO = MemberDAO.getInstance();
	SelectDAO selectDAO = SelectDAO.getInstance();

	private String storeName;
	private List<String> menuNames = new ArrayList<>();
//   private String menuName;
//   private int remainqty;
//   private int quantity;
//   private int menuprice;
	private int resultprices;
	private int minTotalPrice;
	String orderCode = "";
	String formatedNow2 = "";
	String menucode = "";
	int quantity;
	int orderEta;

	private List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> orderListInsert = new ArrayList<Map<String, Object>>();
	Map<String, Object> list = new HashMap<String, Object>();
	Map<String, Object> listInsert = new HashMap<String, Object>();

//	private List<Map<String, Object>> orderList = null;
//	private List<Map<String, Object>> orderListInsert = null;
//	Map<String, Object> list = null;
//	Map<String, Object> listInsert = null;

	public int storeInfo() {

		System.out.println();
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [ 擠衝薄 摹鷗 ]");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("  ** 擠衝薄 檜葷擊 薑�旅� 殮溘п輿撮蹂 **");
		System.out.print("   擠衝薄 檜葷 殮溘  >> ");
		storeName = ScanUtil.nextLine();

		List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);

		if (menuList == null || menuList.isEmpty()) {
			System.out.println(" ≦ п渡 擠衝薄縑 渠и 詭景 薑爾陛 橈蝗棲棻.");
			return View.MENU;
		}

		minTotalPrice = menuList.get(0).get("MINORDER") != null
				? Integer.parseInt(String.valueOf(menuList.get(0).get("MINORDER")))
				: 0;

		boolean storeFound = false;

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" [" + storeName + "]       * 譆模輿僥旎擋: " + minTotalPrice + "縑 蜃啪 氬嬴輿撮蹂");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 詭景 檜葷\t\t詭景 陛問\t\t濤罹 熱榆");

		for (Map<String, Object> menu : menuList) {
			if (menu.get("STONM").equals(storeName)) {
				storeFound = true;
				System.out.print(" " + menu.get("MENUNM"));
				System.out.print("\t\t" + menu.get("MENUPRICE") + "錳");
				System.out.print("\t\t" + menu.get("REMAINQTY") + "偃");
				System.out.println();
			} else {
				System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛 殮溘п輿撮蹂.");
			}
		}

		// 1. 陛啪 摹鷗 > 斜 陛啪曖 渠瞪衛 掘滌 囀萄蒂 陛螳螞棻. substring賦憮
		String storeGu = menuList.get(0).get("STOCODE").toString();
		storeGu = storeGu.substring(0, 2); // 陛啪 晦遽 渠瞪衛 掘碟 囀萄.

		orderCode += storeGu;
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd"); // ん裝 薑曖
		String formatedNow = now.format(formatter); // ん裝 瞳辨
		// System.out.println(formatedNow); // 唸婁 轎溘 : 210617/
		orderCode += formatedNow;
		// System.out.println("orderCode : " + orderCode);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy/MM/dd"); // ん裝 薑曖
		formatedNow2 = now.format(formatter2); // ん裝 瞳辨
		// System.out.println(formatedNow2);

		orderCode += "%";
		List<Object> odStr = new ArrayList<>();
		odStr.add(orderCode);
		List<Map<String, Object>> searchOrderCode = memberDAO.searchOrderCode(odStr);
		if (NullCheckUtil.isEmpty(searchOrderCode)) {
			orderCode += "0001";
			orderCode = orderCode.replace("%", "");
		} else {
			orderCode += searchOrderCode.get(0).get("ORDERCODE").toString();
			orderCode = orderCode.replace("%", "");
		}

		Controller.sessionStorage.put("ordercode", null);
		Controller.sessionStorage.put("ordercode", orderCode);

		// 2. 餌辨濠 薑爾 醞縑 MEMADD 蘭葬蒂 賦憮 渠瞪衛 掘 薑爾蒂 陛螳諦撿 и棻. (O)
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		String memAdd = loginInfo.get("MEMADD").toString();
		String[] addArray = memAdd.split(" ");

		memAdd = addArray[1]; // 餌辨濠 晦遽 渠瞪衛 掘虜 釭螞剪
		Controller.sessionStorage.put("memAdd", memAdd);

		if (memAdd.equals("渠湯掘")) {
			memAdd = "AG";
		} else if (memAdd.equals("翕掘")) {
			memAdd = "DG";
		} else if (memAdd.equals("憮掘")) {
			memAdd = "SG";
		} else if (memAdd.equals("嶸撩掘")) {
			memAdd = "YG";
		} else if (memAdd.equals("醞掘")) {
			memAdd = "JG";
		}

		orderEta = 0;
		if (memAdd.equals(storeGu)) {
			orderEta = 15;
		} else {
			if (memAdd.equals("AG") && (storeGu.equals("YG") || storeGu.equals("DG"))) {
				orderEta = 30;
			} else if (memAdd.equals("YG") && (storeGu.equals("AG") || storeGu.equals("SG"))) {
				orderEta = 30;
			} else if (memAdd.equals("SG") && (storeGu.equals("YG") || storeGu.equals("JG"))) {
				orderEta = 30;
			} else if (memAdd.equals("JG") && (storeGu.equals("SG") || storeGu.equals("DG"))) {
				orderEta = 30;
			} else if (memAdd.equals("DG") && (storeGu.equals("JG") || storeGu.equals("AG"))) {
				orderEta = 30;
			} else if (memAdd.equals("AG") && (storeGu.equals("SG") || storeGu.equals("JG"))) {
				orderEta = 45;
			} else if (memAdd.equals("YG") && (storeGu.equals("JG") || storeGu.equals("DG"))) {
				orderEta = 45;
			} else if (memAdd.equals("SG") && (storeGu.equals("AG") || storeGu.equals("DG"))) {
				orderEta = 45;
			} else if (memAdd.equals("JG") && (storeGu.equals("YG") || storeGu.equals("AG"))) {
				orderEta = 45;
			} else if (memAdd.equals("DG") && (storeGu.equals("YG") || storeGu.equals("SG"))) {
				orderEta = 45;
			}
		}

		Controller.sessionStorage.put("ordereta", orderEta);
		// System.out.println(Controller.sessionStorage.toString());

		if (storeFound) {
			return View.MENU_DETAIL;
		} else {
			return View.MENU;
		}

	}

	public int menuInfo() {
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		System.out.println();
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("   1. 詭景摹鷗   2. 濰夥掘棲(唸薯�飛�)");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print("   廓��殮溘 >> ");
		int se = ScanUtil.nextInt();

		while (true) {
			switch (se) {
			case 1:
				list = new HashMap<String, Object>();
				listInsert = new HashMap<String, Object>();
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" ** 詭景 檜葷擊 薑�旅� 殮溘п輿撮蹂 **");

				System.out.print("   詭景 摹鷗 >> ");
				String menuName = ScanUtil.nextLine();

				int quantity = 0;

				while (true) {
					System.out.print("   熱榆 殮溘 >> ");
					String quantityStr = ScanUtil.nextLine();

					if (quantityStr.matches("^[0-9]*$") && quantityStr != null) {
						quantity = Integer.parseInt(quantityStr);
						break;
					} else {
						System.out.println(" ≦ 澀跤脹 殮溘殮棲棻. 璋濠煎 殮溘п輿撮蹂.");
					}
				}

				Map<String, Object> menudetailList = selectDAO.getMenuDetailList(menuName, storeName);

				if (menudetailList != null && !menudetailList.isEmpty()) {
					System.out.println();

					if (menudetailList.get("MENUNM").equals(menuName)) {
						BigDecimal remainQtyBigDecimal = (BigDecimal) menudetailList.get("REMAINQTY");
						int remainqty = remainQtyBigDecimal.intValue();

						BigDecimal menupriceBigDecimal = (BigDecimal) menudetailList.get("MENUPRICE");
						int menuprice = menupriceBigDecimal.intValue();

						if (remainQtyBigDecimal != null) {
							if (remainQtyBigDecimal.compareTo(BigDecimal.valueOf(quantity)) >= 0) {
								int sumprice = menuprice * quantity;
								resultprices += sumprice;
								menucode = menudetailList.get("MENUCODE").toString();
								// System.out.println(formatedNow2);
								list.put("menuName", menuName);
								list.put("remainQty", remainqty);
								list.put("orderQty", quantity);
								list.put("storeName", storeName);
								list.put("menuPrice", menuprice);
								list.put("sumPrice", sumprice);
//								orderList = new ArrayList<Map<String, Object>>();
								orderList.add(list);

								listInsert.put("orderCode", orderCode);
								listInsert.put("userID", userID);
								listInsert.put("menuCode", menucode);
								listInsert.put("orderQty", quantity);
								listInsert.put("sysdate", formatedNow2);
								listInsert.put("orderEta", orderEta);
//								orderListInsert = new ArrayList<Map<String, Object>>();
								orderListInsert.add(listInsert);
							} else {
								System.out.println(" ヶ瞰脹 詭景殮棲棻.");
								System.out.println("   1. 詭景摹鷗戲煎 給嬴陛晦  2. 寡殖ん濰摹鷗戲煎 給嬴陛晦");
								int rechoice = ScanUtil.nextInt();
								if (rechoice == 2) {
									return View.ORDER_HOME;
								}
							}
						}
						System.out.println(" 識 唸薯 旎擋: " + resultprices);
					}
				} else {
					System.out.println(" ≦ п渡 詭景縑 渠и 詭景 薑爾陛 橈蝗棲棻. 棻衛 殮溘п輿撮蹂.");
					System.out.println();
				}
				break;

			case 2:
				if (resultprices == 0) {
					System.out.println("  ** 濰夥掘棲陛 綠歷蝗棲棻. 寡殖/ん濰 摹鷗�飛橉虞� 給嬴骨棲棻. **");
					System.out.println();
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.ORDER_HOME;
				} else {
					return View.CART;
				}

			default:
				System.out.println(" ≦ 澀跤殮溘ж樟蝗棲棻. 棻衛 殮溘п輿撮蹂.");
				return View.MENU_DETAIL;
			}

			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("  * 詭景蒂 蹺陛ж衛啊蝗棲梱?");
				System.out.println("   1. 詭景蹺陛      2. 唸薯�飛橉虞� 檜翕");
				System.out.print("  璋濠殮溘 >> ");
				int as = ScanUtil.nextInt();

				if (as == 1) {
					break;
				} else if (as == 2) {
					if (minTotalPrice > resultprices) { // 譆模輿僥旎擋 >= 識輿僥旎擋
						System.out.println(" ≦ 氬戲褐 識 旎擋檜 譆模 輿僥旎擋爾棻 瞳蝗棲棻.");
						System.out.println(" ≦ 褻旎 渦 氬嬴輿撮蹂 ^^");
						break;
					} else {
						System.out.println(" 馬餌м棲棻. 縛攪(Enter)蒂 援腦衛賊 濰夥掘棲煎 檜翕м棲棻.");
						EnterUtil.enterNext(2);
						return View.CART;
					}
				} else {
					System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛 殮溘ж撮蹂");
					return View.MENU_DETAIL;
				}
			}
		}
	}

	long resultbalance = 0;

	public int cartInfo() {
//		  System.out.println(Controller.sessionStorage.toString());
		List<Object> param1 = new ArrayList<>();
		List<Object> param2 = new ArrayList<>();

		// 餌辨濠曖 薑爾 醞 濤堅薑爾 陛螳螃晦
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		Map<String, Object> balanceOne = selectDAO.BalanceOne(userID);
		BigDecimal balanceBigDecimal = (BigDecimal) balanceOne.get("BALANCE");

		long balance = balanceBigDecimal.longValue();

		if (Controller.sessionStorage.get("deliOrTake").toString() == "deliYN") {
//			  System.out.println("寡殖 �飛�");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [ 濰夥掘棲 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     * 氬擎 詭景葬蝶お *");
			System.out.println("  詭景貲\t\t熱榆\t\t欽陛\t\t詭景識旎擋");
			for (int j = 0; j < orderList.size(); j++) {
				System.out.println("  " + (String) orderList.get(j).get("menuName") + "\t\t"
						+ (int) orderList.get(j).get("orderQty") + "偃" + "\t\t"
						+ (int) orderList.get(j).get("menuPrice") + "錳" + "\t\t"
						+ ((int) orderList.get(j).get("orderQty")) * ((int) orderList.get(j).get("menuPrice")) + "錳");
			}
			List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);
			String storeCode = menuList.get(0).get("STOCODE").toString();
			Map<String, Object> deliCost = selectDAO.getRiderDeliCost(storeCode);
			BigDecimal deliCostBigDecimal = (BigDecimal) deliCost.get("DELICOST");
			int delicost = deliCostBigDecimal.intValue();
			System.out.println("  -----------------------------------");
			System.out.println("   寡殖綠 = " + deliCost.get("DELICOST").toString() + "錳");
			System.out.println("  -----------------------------------");
			resultprices += delicost;
			System.out.println("   唸薯 識旎擋 = " + resultprices + "錳");
			System.out.println("  -----------------------------------");
			System.out.println("   餌辨 陛棟и 濤擋 : " + balance + "錳");
			System.out.println("  -----------------------------------");
			System.out.println("   1. 譆謙唸薯");
			System.out.println("   2. ん檣お醱瞪ж晦");
			System.out.println("   3. 詭景 籀擠睡攪 棻衛 氬晦");
			System.out.println("   4. 衝渡 籀擠睡攪 棻衛 堅腦晦");
			System.out.println("   9. 煎斜嬴醒");
			System.out.println("  -----------------------------------");
			System.out.print("  廓��殮溘 >> ");

			while (true) {
				switch (ScanUtil.nextInt()) {
				case 1:
					if (balance >= resultprices) {
						// 絲檜 識唸薯旎擋縑 醱碟
						// �飛橉��� => 譆謙譆謙唸薯�飛�(輿僥諫猿�挫�, 濤堅, ...)
						resultbalance = balance - resultprices;

						List<Object> param = new ArrayList<>();
						param.add(resultbalance);
						param.add(userID);
						selectDAO.getPriceUpdate(param);

						for (int j = 0; j < orderList.size(); j++) {
							param1 = new ArrayList<>();
							int resultqty = (int) orderList.get(j).get("remainQty")
									- (int) orderList.get(j).get("orderQty");
							param1.add(resultqty);
							param1.add((String) orderList.get(j).get("menuName"));
							param1.add((String) orderList.get(j).get("storeName"));
							selectDAO.getRemainUpdate(param1);
						}

//		               System.out.println("deliOrTake : " + Controller.sessionStorage.get("deliOrTake"));
						for (int k = 0; k < orderListInsert.size(); k++) {
							param2 = new ArrayList<>();
							param2.add((String) orderListInsert.get(k).get("orderCode"));
							param2.add((String) orderListInsert.get(k).get("userID"));
							param2.add((String) orderListInsert.get(k).get("menuCode"));
							param2.add((int) orderListInsert.get(k).get("orderQty"));
							param2.add((String) orderListInsert.get(k).get("sysdate"));
							param2.add((int) orderListInsert.get(k).get("orderEta"));
							param2.add(Controller.sessionStorage.get("deliOrTake"));
							selectDAO.getOrderHistoInsert(param2);
						}

						System.out.println(" ** 唸薯陛 諫猿腎歷蝗棲棻 **");
						System.out.println(" 縛攪蒂 援腦賊 輿僥諫猿む檜雖煎 骨棲棻.");
						orderCode = "";
						EnterUtil.enterNext(2);
						return View.RECEIPT;
					} else {
						System.out.println(" ≦ 濤擋檜 睡褶м棲棻.");
						while (true) {
							System.out.println(" 1. ん檣お 醱瞪ж晦   2. 鏃模(濰夥掘棲�飛橉虞� 剩橫馬)");
							System.out.println("  廓��殮溘 >> ");
							switch (ScanUtil.nextInt()) {
							case 1:
								return View.POINT;
							case 2:
								return View.CART;
							default:
								System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛殮溘ж撮蹂");
								break;
							}
						}
					}

				case 2:
					return View.POINT;
				case 3:
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.MENU_DETAIL;
				case 4:
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.MENU;
				case 9:
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.ORDER_LOGOUT;
				}
			}
		} else if (Controller.sessionStorage.get("deliOrTake").toString() == "takeYN") {

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     [ 濰夥掘棲 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("     * 氬擎 詭景葬蝶お *");
			System.out.println("  詭景貲\t\t熱榆\t\t欽陛\t\t詭景識旎擋");
			for (int j = 0; j < orderList.size(); j++) {
				System.out.println("  " + (String) orderList.get(j).get("menuName") + "\t\t"
						+ (int) orderList.get(j).get("orderQty") + "偃" + "\t\t"
						+ (int) orderList.get(j).get("menuPrice") + "錳" + "\t\t"
						+ ((int) orderList.get(j).get("orderQty")) * ((int) orderList.get(j).get("menuPrice")) + "錳");
			}
			System.out.println("  -----------------------------------");
			System.out.println("   唸薯 識旎擋 = " + resultprices + "錳");
			System.out.println("  -----------------------------------");
			System.out.println("   餌辨 陛棟и 濤擋 : " + balance + "錳");
			System.out.println("  -----------------------------------");
			System.out.println("   1. 譆謙唸薯");
			System.out.println("   2. ん檣お醱瞪ж晦");
			System.out.println("   3. 詭景 籀擠睡攪 棻衛 氬晦");
			System.out.println("   4. 衝渡 籀擠睡攪 棻衛 堅腦晦");
			System.out.println("   9. 煎斜嬴醒");
			System.out.println("  -----------------------------------");
			System.out.print("  廓��殮溘 >> ");

			while (true) {
				switch (ScanUtil.nextInt()) {
				case 1:
					if (balance >= resultprices) {
						// 絲檜 識唸薯旎擋縑 醱碟
						// �飛橉��� => 譆謙譆謙唸薯�飛�(輿僥諫猿�挫�, 濤堅, ...)
						resultbalance = balance - resultprices;

						List<Object> param = new ArrayList<>();
						param.add(resultbalance);
						param.add(userID);
						selectDAO.getPriceUpdate(param);

						for (int j = 0; j < orderList.size(); j++) {
							param1 = new ArrayList<>();
							int resultqty = (int) orderList.get(j).get("remainQty")
									- (int) orderList.get(j).get("orderQty");
							param1.add(resultqty);
							param1.add((String) orderList.get(j).get("menuName"));
							param1.add((String) orderList.get(j).get("storeName"));
							selectDAO.getRemainUpdate(param1);
						}

//		               System.out.println("deliOrTake : " + Controller.sessionStorage.get("deliOrTake"));
						for (int k = 0; k < orderListInsert.size(); k++) {
							param2 = new ArrayList<>();
							param2.add((String) orderListInsert.get(k).get("orderCode"));
							param2.add((String) orderListInsert.get(k).get("userID"));
							param2.add((String) orderListInsert.get(k).get("menuCode"));
							param2.add((int) orderListInsert.get(k).get("orderQty"));
							param2.add((String) orderListInsert.get(k).get("sysdate"));
							param2.add((int) orderListInsert.get(k).get("orderEta"));
							param2.add(Controller.sessionStorage.get("deliOrTake"));
							selectDAO.getOrderHistoInsert(param2);
						}

						System.out.println(" ** 唸薯陛 諫猿腎歷蝗棲棻 **");
						System.out.println(" 縛攪蒂 援腦賊 輿僥諫猿む檜雖煎 骨棲棻.");
						orderCode = "";
						EnterUtil.enterNext(2);
						return View.RECEIPT;
					} else {
						System.out.println(" ≦ 濤擋檜 睡褶м棲棻.");
						while (true) {
							System.out.println(" 1. ん檣お 醱瞪ж晦   2. 鏃模(濰夥掘棲�飛橉虞� 剩橫馬)");
							System.out.println("  廓��殮溘 >> ");
							switch (ScanUtil.nextInt()) {
							case 1:
								return View.POINT;
							case 2:
								return View.CART;
							default:
								System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛殮溘ж撮蹂");
								break;
							}
						}
					}

				case 2:
					return View.POINT;
				case 3:
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.MENU_DETAIL;
				case 4:
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.MENU;
				case 9:
					orderList = new ArrayList<Map<String, Object>>();
					resultprices = 0;
					orderListInsert = new ArrayList<Map<String, Object>>();
					orderCode = "";
					return View.ORDER_LOGOUT;
				}
			}
		}
		return 0;
	}

	// 葆雖虞�飛�: 艙熱隸籀歲 輿僥�挫帡� 薑爾陛 嗡
	public int receipt() {

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		List<Map<String, Object>> orderMap = memberDAO.orderCode(userID);
		String orderCode = orderMap.get(0).get("ORDERCODE").toString();

		List<Map<String, Object>> orderMap2 = memberDAO.orderList(userID, orderCode);
		String orderEta = orderMap2.get(0).get("ORDERETA").toString();

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [ 輿僥 諫猿 �挫� む檜雖 ]");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" 輿僥廓�� : " + orderCode);
		System.out.println("  -----------------------------------");
		System.out.println(" " + orderEta + " �� 輿僥 紫雜 蕨薑殮棲棻 ~ !");
		System.out.println("  -----------------------------------");
		System.out.println(" 輿僥擠衝薄貲\t\t輿僥詭景\t\t輿僥熱榆\t詭景陛問");
		for (Map<String, Object> orderInfo : orderMap2) {
			BigDecimal totalPriceBigDecimal = (BigDecimal) orderInfo.get("TOTALPRICE");
			int totalPrice = totalPriceBigDecimal.intValue();
			System.out.println("  Ⅱ" + orderInfo.get("STONM").toString() + "\t\t" + orderInfo.get("MENUNM").toString()
					+ "\t\t" + orderInfo.get("ORDERQTY").toString() + "\t" + totalPrice + "錳");
		}
		if (Controller.sessionStorage.get("deliOrTake").toString() == "deliYN") {
			List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);
			String storeCode = menuList.get(0).get("STOCODE").toString();
			Map<String, Object> deliCost = selectDAO.getRiderDeliCost(storeCode);
			System.out.println("  -----------------------------------");
			System.out.println("   寡殖綠 = " + deliCost.get("DELICOST").toString() + "錳");
		}
		System.out.println("  -----------------------------------");
		System.out.println(" 識 唸薯旎擋 :           " + resultprices + "錳");
		System.out.println(" 陴擎 ん檣お :           " + resultbalance + "錳");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		orderList = new ArrayList<Map<String, Object>>();
		resultprices = 0;
		orderListInsert = new ArrayList<Map<String, Object>>();

		while (true) {
			System.out.println(" 1. 蹺陛輿僥ж晦          2. 煎斜嬴醒ж晦         3.Щ煎斜極謙猿ж晦");
			System.out.print("殮溘 >> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				return View.ORDER_HOME;
			case 2:
				return View.ORDER_LOGOUT;
			case 3:
				return View.SYSTEM_EXIT;
			default:
				System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛殮溘ж撮蹂");
			}
		}
	}

}