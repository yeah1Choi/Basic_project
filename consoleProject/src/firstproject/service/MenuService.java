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
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("     [ À½½ÄÁ¡ ¼±ÅÃ ]");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("  ** À½½ÄÁ¡ ÀÌ¸§À» Á¤È®È÷ ÀÔ·ÂÇØÁÖ¼¼¿ä **");
		System.out.print("   À½½ÄÁ¡ ÀÌ¸§ ÀÔ·Â  >> ");
		storeName = ScanUtil.nextLine();

		List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);

		if (menuList == null || menuList.isEmpty()) {
			System.out.println(" ¡Ø ÇØ´ç À½½ÄÁ¡¿¡ ´ëÇÑ ¸Þ´º Á¤º¸°¡ ¾ø½À´Ï´Ù.");
			return View.MENU;
		}

		minTotalPrice = menuList.get(0).get("MINORDER") != null
				? Integer.parseInt(String.valueOf(menuList.get(0).get("MINORDER")))
				: 0;

		boolean storeFound = false;

		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" [" + storeName + "]       * ÃÖ¼ÒÁÖ¹®±Ý¾×: " + minTotalPrice + "¿¡ ¸Â°Ô ´ã¾ÆÁÖ¼¼¿ä");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" ¸Þ´º ÀÌ¸§\t\t¸Þ´º °¡°Ý\t\tÀÜ¿© ¼ö·®");

		for (Map<String, Object> menu : menuList) {
			if (menu.get("STONM").equals(storeName)) {
				storeFound = true;
				System.out.print(" " + menu.get("MENUNM"));
				System.out.print("\t\t" + menu.get("MENUPRICE") + "¿ø");
				System.out.print("\t\t" + menu.get("REMAINQTY") + "°³");
				System.out.println();
			} else {
				System.out.println(" ¡Ø Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
		}

		// 1. °¡°Ô ¼±ÅÃ > ±× °¡°ÔÀÇ ´ëÀü½Ã ±¸º° ÄÚµå¸¦ °¡Á®¿Â´Ù. substring½á¼­
		String storeGu = menuList.get(0).get("STOCODE").toString();
		storeGu = storeGu.substring(0, 2); // °¡°Ô ±âÁØ ´ëÀü½Ã ±¸ºÐ ÄÚµå.

		orderCode += storeGu;
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd"); // Æ÷¸Ë Á¤ÀÇ
		String formatedNow = now.format(formatter); // Æ÷¸Ë Àû¿ë
		// System.out.println(formatedNow); // °á°ú Ãâ·Â : 210617/
		orderCode += formatedNow;
		// System.out.println("orderCode : " + orderCode);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy/MM/dd"); // Æ÷¸Ë Á¤ÀÇ
		formatedNow2 = now.format(formatter2); // Æ÷¸Ë Àû¿ë
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

		// 2. »ç¿ëÀÚ Á¤º¸ Áß¿¡ MEMADD Äõ¸®¸¦ ½á¼­ ´ëÀü½Ã ±¸ Á¤º¸¸¦ °¡Á®¿Í¾ß ÇÑ´Ù. (O)
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		String memAdd = loginInfo.get("MEMADD").toString();
		String[] addArray = memAdd.split(" ");

		memAdd = addArray[1]; // »ç¿ëÀÚ ±âÁØ ´ëÀü½Ã ±¸¸¸ ³ª¿Â°Å
		Controller.sessionStorage.put("memAdd", memAdd);

		if (memAdd.equals("´ë´ö±¸")) {
			memAdd = "AG";
		} else if (memAdd.equals("µ¿±¸")) {
			memAdd = "DG";
		} else if (memAdd.equals("¼­±¸")) {
			memAdd = "SG";
		} else if (memAdd.equals("À¯¼º±¸")) {
			memAdd = "YG";
		} else if (memAdd.equals("Áß±¸")) {
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
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("   1. ¸Þ´º¼±ÅÃ   2. Àå¹Ù±¸´Ï(°áÁ¦È­¸é)");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.print("   ¹øÈ£ÀÔ·Â >> ");
		int se = ScanUtil.nextInt();

		while (true) {
			switch (se) {
			case 1:
				list = new HashMap<String, Object>();
				listInsert = new HashMap<String, Object>();
				System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println(" ** ¸Þ´º ÀÌ¸§À» Á¤È®È÷ ÀÔ·ÂÇØÁÖ¼¼¿ä **");

				System.out.print("   ¸Þ´º ¼±ÅÃ >> ");
				String menuName = ScanUtil.nextLine();

				int quantity = 0;

				while (true) {
					System.out.print("   ¼ö·® ÀÔ·Â >> ");
					String quantityStr = ScanUtil.nextLine();

					if (quantityStr.matches("^[0-9]*$") && quantityStr != null) {
						quantity = Integer.parseInt(quantityStr);
						break;
					} else {
						System.out.println(" ¡Ø Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù. ¼ýÀÚ·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
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
								System.out.println(" Ç°ÀýµÈ ¸Þ´ºÀÔ´Ï´Ù.");
								System.out.println("   1. ¸Þ´º¼±ÅÃÀ¸·Î µ¹¾Æ°¡±â  2. ¹è´ÞÆ÷Àå¼±ÅÃÀ¸·Î µ¹¾Æ°¡±â");
								int rechoice = ScanUtil.nextInt();
								if (rechoice == 2) {
									return View.ORDER_HOME;
								}
							}
						}
						System.out.println(" ÃÑ °áÁ¦ ±Ý¾×: " + resultprices);
					}
				} else {
					System.out.println(" ¡Ø ÇØ´ç ¸Þ´º¿¡ ´ëÇÑ ¸Þ´º Á¤º¸°¡ ¾ø½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
					System.out.println();
				}
				break;

			case 2:
				if (resultprices == 0) {
					System.out.println("  ** Àå¹Ù±¸´Ï°¡ ºñ¾ú½À´Ï´Ù. ¹è´Þ/Æ÷Àå ¼±ÅÃÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù. **");
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
				System.out.println(" ¡Ø Àß¸øÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				return View.MENU_DETAIL;
			}

			while (true) {
				System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("  * ¸Þ´º¸¦ Ãß°¡ÇÏ½Ã°Ú½À´Ï±î?");
				System.out.println("   1. ¸Þ´ºÃß°¡      2. °áÁ¦È­¸éÀ¸·Î ÀÌµ¿");
				System.out.print("  ¼ýÀÚÀÔ·Â >> ");
				int as = ScanUtil.nextInt();

				if (as == 1) {
					break;
				} else if (as == 2) {
					if (minTotalPrice > resultprices) { // ÃÖ¼ÒÁÖ¹®±Ý¾× >= ÃÑÁÖ¹®±Ý¾×
						System.out.println(" ¡Ø ´ãÀ¸½Å ÃÑ ±Ý¾×ÀÌ ÃÖ¼Ò ÁÖ¹®±Ý¾×º¸´Ù Àû½À´Ï´Ù.");
						System.out.println(" ¡Ø Á¶±Ý ´õ ´ã¾ÆÁÖ¼¼¿ä ^^");
						break;
					} else {
						System.out.println(" °¨»çÇÕ´Ï´Ù. ¿£ÅÍ(Enter)¸¦ ´©¸£½Ã¸é Àå¹Ù±¸´Ï·Î ÀÌµ¿ÇÕ´Ï´Ù.");
						EnterUtil.enterNext(2);
						return View.CART;
					}
				} else {
					System.out.println(" ¡Ø Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä");
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

		// »ç¿ëÀÚÀÇ Á¤º¸ Áß ÀÜ°íÁ¤º¸ °¡Á®¿À±â
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		Map<String, Object> balanceOne = selectDAO.BalanceOne(userID);
		BigDecimal balanceBigDecimal = (BigDecimal) balanceOne.get("BALANCE");

		long balance = balanceBigDecimal.longValue();

		if (Controller.sessionStorage.get("deliOrTake").toString() == "deliYN") {
//			  System.out.println("¹è´Þ È­¸é");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [ Àå¹Ù±¸´Ï ]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     * ´ãÀº ¸Þ´º¸®½ºÆ® *");
			System.out.println("  ¸Þ´º¸í\t\t¼ö·®\t\t´Ü°¡\t\t¸Þ´ºÃÑ±Ý¾×");
			for (int j = 0; j < orderList.size(); j++) {
				System.out.println("  " + (String) orderList.get(j).get("menuName") + "\t\t"
						+ (int) orderList.get(j).get("orderQty") + "°³" + "\t\t"
						+ (int) orderList.get(j).get("menuPrice") + "¿ø" + "\t\t"
						+ ((int) orderList.get(j).get("orderQty")) * ((int) orderList.get(j).get("menuPrice")) + "¿ø");
			}
			List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);
			String storeCode = menuList.get(0).get("STOCODE").toString();
			Map<String, Object> deliCost = selectDAO.getRiderDeliCost(storeCode);
			BigDecimal deliCostBigDecimal = (BigDecimal) deliCost.get("DELICOST");
			int delicost = deliCostBigDecimal.intValue();
			System.out.println("  -----------------------------------");
			System.out.println("   ¹è´Þºñ = " + deliCost.get("DELICOST").toString() + "¿ø");
			System.out.println("  -----------------------------------");
			resultprices += delicost;
			System.out.println("   °áÁ¦ ÃÑ±Ý¾× = " + resultprices + "¿ø");
			System.out.println("  -----------------------------------");
			System.out.println("   »ç¿ë °¡´ÉÇÑ ÀÜ¾× : " + balance + "¿ø");
			System.out.println("  -----------------------------------");
			System.out.println("   1. ÃÖÁ¾°áÁ¦");
			System.out.println("   2. Æ÷ÀÎÆ®ÃæÀüÇÏ±â");
			System.out.println("   3. ¸Þ´º Ã³À½ºÎÅÍ ´Ù½Ã ´ã±â");
			System.out.println("   4. ½Ä´ç Ã³À½ºÎÅÍ ´Ù½Ã °í¸£±â");
			System.out.println("   9. ·Î±×¾Æ¿ô");
			System.out.println("  -----------------------------------");
			System.out.print("  ¹øÈ£ÀÔ·Â >> ");

			while (true) {
				switch (ScanUtil.nextInt()) {
				case 1:
					if (balance >= resultprices) {
						// µ·ÀÌ ÃÑ°áÁ¦±Ý¾×¿¡ ÃæºÐ
						// È­¸éÀüÈ¯ => ÃÖÁ¾ÃÖÁ¾°áÁ¦È­¸é(ÁÖ¹®¿Ï·áÈ®ÀÎ, ÀÜ°í, ...)
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

						System.out.println(" ** °áÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù **");
						System.out.println(" ¿£ÅÍ¸¦ ´©¸£¸é ÁÖ¹®¿Ï·áÆäÀÌÁö·Î °©´Ï´Ù.");
						orderCode = "";
						EnterUtil.enterNext(2);
						return View.RECEIPT;
					} else {
						System.out.println(" ¡Ø ÀÜ¾×ÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
						while (true) {
							System.out.println(" 1. Æ÷ÀÎÆ® ÃæÀüÇÏ±â   2. Ãë¼Ò(Àå¹Ù±¸´ÏÈ­¸éÀ¸·Î ³Ñ¾î°¨)");
							System.out.println("  ¹øÈ£ÀÔ·Â >> ");
							switch (ScanUtil.nextInt()) {
							case 1:
								return View.POINT;
							case 2:
								return View.CART;
							default:
								System.out.println(" ¡Ø Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù. ´Ù½ÃÀÔ·ÂÇÏ¼¼¿ä");
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

			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     [ Àå¹Ù±¸´Ï ]");
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("     * ´ãÀº ¸Þ´º¸®½ºÆ® *");
			System.out.println("  ¸Þ´º¸í\t\t¼ö·®\t\t´Ü°¡\t\t¸Þ´ºÃÑ±Ý¾×");
			for (int j = 0; j < orderList.size(); j++) {
				System.out.println("  " + (String) orderList.get(j).get("menuName") + "\t\t"
						+ (int) orderList.get(j).get("orderQty") + "°³" + "\t\t"
						+ (int) orderList.get(j).get("menuPrice") + "¿ø" + "\t\t"
						+ ((int) orderList.get(j).get("orderQty")) * ((int) orderList.get(j).get("menuPrice")) + "¿ø");
			}
			System.out.println("  -----------------------------------");
			System.out.println("   °áÁ¦ ÃÑ±Ý¾× = " + resultprices + "¿ø");
			System.out.println("  -----------------------------------");
			System.out.println("   »ç¿ë °¡´ÉÇÑ ÀÜ¾× : " + balance + "¿ø");
			System.out.println("  -----------------------------------");
			System.out.println("   1. ÃÖÁ¾°áÁ¦");
			System.out.println("   2. Æ÷ÀÎÆ®ÃæÀüÇÏ±â");
			System.out.println("   3. ¸Þ´º Ã³À½ºÎÅÍ ´Ù½Ã ´ã±â");
			System.out.println("   4. ½Ä´ç Ã³À½ºÎÅÍ ´Ù½Ã °í¸£±â");
			System.out.println("   9. ·Î±×¾Æ¿ô");
			System.out.println("  -----------------------------------");
			System.out.print("  ¹øÈ£ÀÔ·Â >> ");

			while (true) {
				switch (ScanUtil.nextInt()) {
				case 1:
					if (balance >= resultprices) {
						// µ·ÀÌ ÃÑ°áÁ¦±Ý¾×¿¡ ÃæºÐ
						// È­¸éÀüÈ¯ => ÃÖÁ¾ÃÖÁ¾°áÁ¦È­¸é(ÁÖ¹®¿Ï·áÈ®ÀÎ, ÀÜ°í, ...)
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

						System.out.println(" ** °áÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù **");
						System.out.println(" ¿£ÅÍ¸¦ ´©¸£¸é ÁÖ¹®¿Ï·áÆäÀÌÁö·Î °©´Ï´Ù.");
						orderCode = "";
						EnterUtil.enterNext(2);
						return View.RECEIPT;
					} else {
						System.out.println(" ¡Ø ÀÜ¾×ÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
						while (true) {
							System.out.println(" 1. Æ÷ÀÎÆ® ÃæÀüÇÏ±â   2. Ãë¼Ò(Àå¹Ù±¸´ÏÈ­¸éÀ¸·Î ³Ñ¾î°¨)");
							System.out.println("  ¹øÈ£ÀÔ·Â >> ");
							switch (ScanUtil.nextInt()) {
							case 1:
								return View.POINT;
							case 2:
								return View.CART;
							default:
								System.out.println(" ¡Ø Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù. ´Ù½ÃÀÔ·ÂÇÏ¼¼¿ä");
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

	// ¸¶Áö¸·È­¸é: ¿µ¼öÁõÃ³·³ ÁÖ¹®È®ÀÎ°ú Á¤º¸°¡ ¶ä
	public int receipt() {

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		List<Map<String, Object>> orderMap = memberDAO.orderCode(userID);
		String orderCode = orderMap.get(0).get("ORDERCODE").toString();

		List<Map<String, Object>> orderMap2 = memberDAO.orderList(userID, orderCode);
		String orderEta = orderMap2.get(0).get("ORDERETA").toString();

		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("     [ ÁÖ¹® ¿Ï·á È®ÀÎ ÆäÀÌÁö ]");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println(" ÁÖ¹®¹øÈ£ : " + orderCode);
		System.out.println("  -----------------------------------");
		System.out.println(" " + orderEta + " ÈÄ ÁÖ¹® µµÂø ¿¹Á¤ÀÔ´Ï´Ù ~ !");
		System.out.println("  -----------------------------------");
		System.out.println(" ÁÖ¹®À½½ÄÁ¡¸í\t\tÁÖ¹®¸Þ´º\t\tÁÖ¹®¼ö·®\t¸Þ´º°¡°Ý");
		for (Map<String, Object> orderInfo : orderMap2) {
			BigDecimal totalPriceBigDecimal = (BigDecimal) orderInfo.get("TOTALPRICE");
			int totalPrice = totalPriceBigDecimal.intValue();
			System.out.println("  ¢º" + orderInfo.get("STONM").toString() + "\t\t" + orderInfo.get("MENUNM").toString()
					+ "\t\t" + orderInfo.get("ORDERQTY").toString() + "\t" + totalPrice + "¿ø");
		}
		if (Controller.sessionStorage.get("deliOrTake").toString() == "deliYN") {
			List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);
			String storeCode = menuList.get(0).get("STOCODE").toString();
			Map<String, Object> deliCost = selectDAO.getRiderDeliCost(storeCode);
			System.out.println("  -----------------------------------");
			System.out.println("   ¹è´Þºñ = " + deliCost.get("DELICOST").toString() + "¿ø");
		}
		System.out.println("  -----------------------------------");
		System.out.println(" ÃÑ °áÁ¦±Ý¾× :           " + resultprices + "¿ø");
		System.out.println(" ³²Àº Æ÷ÀÎÆ® :           " + resultbalance + "¿ø");
		System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		orderList = new ArrayList<Map<String, Object>>();
		resultprices = 0;
		orderListInsert = new ArrayList<Map<String, Object>>();

		while (true) {
			System.out.println(" 1. Ãß°¡ÁÖ¹®ÇÏ±â          2. ·Î±×¾Æ¿ôÇÏ±â         3.ÇÁ·Î±×·¥Á¾·áÇÏ±â");
			System.out.print("ÀÔ·Â >> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				return View.ORDER_HOME;
			case 2:
				return View.ORDER_LOGOUT;
			case 3:
				return View.SYSTEM_EXIT;
			default:
				System.out.println(" ¡Ø Àß¸øµÈ Á¢±ÙÀÔ´Ï´Ù. ´Ù½ÃÀÔ·ÂÇÏ¼¼¿ä");
			}
		}
	}

}