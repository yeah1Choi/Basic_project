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
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [ ������ ���� ]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("  ** ������ �̸��� ��Ȯ�� �Է����ּ��� **");
		System.out.print("   ������ �̸� �Է�  >> ");
		storeName = ScanUtil.nextLine();

		List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);

		if (menuList == null || menuList.isEmpty()) {
			System.out.println(" �� �ش� �������� ���� �޴� ������ �����ϴ�.");
			return View.MENU;
		}

		minTotalPrice = menuList.get(0).get("MINORDER") != null
				? Integer.parseInt(String.valueOf(menuList.get(0).get("MINORDER")))
				: 0;

		boolean storeFound = false;

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" [" + storeName + "]       * �ּ��ֹ��ݾ�: " + minTotalPrice + "�� �°� ����ּ���");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" �޴� �̸�\t\t�޴� ����\t\t�ܿ� ����");

		for (Map<String, Object> menu : menuList) {
			if (menu.get("STONM").equals(storeName)) {
				storeFound = true;
				System.out.print(" " + menu.get("MENUNM"));
				System.out.print("\t\t" + menu.get("MENUPRICE") + "��");
				System.out.print("\t\t" + menu.get("REMAINQTY") + "��");
				System.out.println();
			} else {
				System.out.println(" �� �߸��� �����Դϴ�. �ٽ� �Է����ּ���.");
			}
		}

		// 1. ���� ���� > �� ������ ������ ���� �ڵ带 �����´�. substring�Ἥ
		String storeGu = menuList.get(0).get("STOCODE").toString();
		storeGu = storeGu.substring(0, 2); // ���� ���� ������ ���� �ڵ�.

		orderCode += storeGu;
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd"); // ���� ����
		String formatedNow = now.format(formatter); // ���� ����
		// System.out.println(formatedNow); // ��� ��� : 210617/
		orderCode += formatedNow;
		// System.out.println("orderCode : " + orderCode);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy/MM/dd"); // ���� ����
		formatedNow2 = now.format(formatter2); // ���� ����
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

		// 2. ����� ���� �߿� MEMADD ������ �Ἥ ������ �� ������ �����;� �Ѵ�. (O)
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		String memAdd = loginInfo.get("MEMADD").toString();
		String[] addArray = memAdd.split(" ");

		memAdd = addArray[1]; // ����� ���� ������ ���� ���°�
		Controller.sessionStorage.put("memAdd", memAdd);

		if (memAdd.equals("�����")) {
			memAdd = "AG";
		} else if (memAdd.equals("����")) {
			memAdd = "DG";
		} else if (memAdd.equals("����")) {
			memAdd = "SG";
		} else if (memAdd.equals("������")) {
			memAdd = "YG";
		} else if (memAdd.equals("�߱�")) {
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
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("   1. �޴�����   2. ��ٱ���(����ȭ��)");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print("   ��ȣ�Է� >> ");
		int se = ScanUtil.nextInt();

		while (true) {
			switch (se) {
			case 1:
				list = new HashMap<String, Object>();
				listInsert = new HashMap<String, Object>();
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ** �޴� �̸��� ��Ȯ�� �Է����ּ��� **");

				System.out.print("   �޴� ���� >> ");
				String menuName = ScanUtil.nextLine();

				int quantity = 0;

				while (true) {
					System.out.print("   ���� �Է� >> ");
					String quantityStr = ScanUtil.nextLine();

					if (quantityStr.matches("^[0-9]*$") && quantityStr != null) {
						quantity = Integer.parseInt(quantityStr);
						break;
					} else {
						System.out.println(" �� �߸��� �Է��Դϴ�. ���ڷ� �Է����ּ���.");
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
								System.out.println(" ǰ���� �޴��Դϴ�.");
								System.out.println("   1. �޴��������� ���ư���  2. ������弱������ ���ư���");
								int rechoice = ScanUtil.nextInt();
								if (rechoice == 2) {
									return View.ORDER_HOME;
								}
							}
						}
						System.out.println(" �� ���� �ݾ�: " + resultprices);
					}
				} else {
					System.out.println(" �� �ش� �޴��� ���� �޴� ������ �����ϴ�. �ٽ� �Է����ּ���.");
					System.out.println();
				}
				break;

			case 2:
				if (resultprices == 0) {
					System.out.println("  ** ��ٱ��ϰ� ������ϴ�. ���/���� ����ȭ������ ���ư��ϴ�. **");
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
				System.out.println(" �� �߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				return View.MENU_DETAIL;
			}

			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("  * �޴��� �߰��Ͻðڽ��ϱ�?");
				System.out.println("   1. �޴��߰�      2. ����ȭ������ �̵�");
				System.out.print("  �����Է� >> ");
				int as = ScanUtil.nextInt();

				if (as == 1) {
					break;
				} else if (as == 2) {
					if (minTotalPrice > resultprices) { // �ּ��ֹ��ݾ� >= ���ֹ��ݾ�
						System.out.println(" �� ������ �� �ݾ��� �ּ� �ֹ��ݾ׺��� �����ϴ�.");
						System.out.println(" �� ���� �� ����ּ��� ^^");
						break;
					} else {
						System.out.println(" �����մϴ�. ����(Enter)�� �����ø� ��ٱ��Ϸ� �̵��մϴ�.");
						EnterUtil.enterNext(2);
						return View.CART;
					}
				} else {
					System.out.println(" �� �߸��� �����Դϴ�. �ٽ� �Է��ϼ���");
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

		// ������� ���� �� �ܰ����� ��������
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		Map<String, Object> balanceOne = selectDAO.BalanceOne(userID);
		BigDecimal balanceBigDecimal = (BigDecimal) balanceOne.get("BALANCE");

		long balance = balanceBigDecimal.longValue();

		if (Controller.sessionStorage.get("deliOrTake").toString() == "deliYN") {
//			  System.out.println("��� ȭ��");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [ ��ٱ��� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     * ���� �޴�����Ʈ *");
			System.out.println("  �޴���\t\t����\t\t�ܰ�\t\t�޴��ѱݾ�");
			for (int j = 0; j < orderList.size(); j++) {
				System.out.println("  " + (String) orderList.get(j).get("menuName") + "\t\t"
						+ (int) orderList.get(j).get("orderQty") + "��" + "\t\t"
						+ (int) orderList.get(j).get("menuPrice") + "��" + "\t\t"
						+ ((int) orderList.get(j).get("orderQty")) * ((int) orderList.get(j).get("menuPrice")) + "��");
			}
			List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);
			String storeCode = menuList.get(0).get("STOCODE").toString();
			Map<String, Object> deliCost = selectDAO.getRiderDeliCost(storeCode);
			BigDecimal deliCostBigDecimal = (BigDecimal) deliCost.get("DELICOST");
			int delicost = deliCostBigDecimal.intValue();
			System.out.println("  -----------------------------------");
			System.out.println("   ��޺� = " + deliCost.get("DELICOST").toString() + "��");
			System.out.println("  -----------------------------------");
			resultprices += delicost;
			System.out.println("   ���� �ѱݾ� = " + resultprices + "��");
			System.out.println("  -----------------------------------");
			System.out.println("   ��� ������ �ܾ� : " + balance + "��");
			System.out.println("  -----------------------------------");
			System.out.println("   1. ��������");
			System.out.println("   2. ����Ʈ�����ϱ�");
			System.out.println("   3. �޴� ó������ �ٽ� ���");
			System.out.println("   4. �Ĵ� ó������ �ٽ� ����");
			System.out.println("   9. �α׾ƿ�");
			System.out.println("  -----------------------------------");
			System.out.print("  ��ȣ�Է� >> ");

			while (true) {
				switch (ScanUtil.nextInt()) {
				case 1:
					if (balance >= resultprices) {
						// ���� �Ѱ����ݾ׿� ���
						// ȭ����ȯ => ������������ȭ��(�ֹ��Ϸ�Ȯ��, �ܰ�, ...)
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

						System.out.println(" ** ������ �Ϸ�Ǿ����ϴ� **");
						System.out.println(" ���͸� ������ �ֹ��Ϸ��������� ���ϴ�.");
						orderCode = "";
						EnterUtil.enterNext(2);
						return View.RECEIPT;
					} else {
						System.out.println(" �� �ܾ��� �����մϴ�.");
						while (true) {
							System.out.println(" 1. ����Ʈ �����ϱ�   2. ���(��ٱ���ȭ������ �Ѿ)");
							System.out.println("  ��ȣ�Է� >> ");
							switch (ScanUtil.nextInt()) {
							case 1:
								return View.POINT;
							case 2:
								return View.CART;
							default:
								System.out.println(" �� �߸��� �����Դϴ�. �ٽ��Է��ϼ���");
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

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [ ��ٱ��� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     * ���� �޴�����Ʈ *");
			System.out.println("  �޴���\t\t����\t\t�ܰ�\t\t�޴��ѱݾ�");
			for (int j = 0; j < orderList.size(); j++) {
				System.out.println("  " + (String) orderList.get(j).get("menuName") + "\t\t"
						+ (int) orderList.get(j).get("orderQty") + "��" + "\t\t"
						+ (int) orderList.get(j).get("menuPrice") + "��" + "\t\t"
						+ ((int) orderList.get(j).get("orderQty")) * ((int) orderList.get(j).get("menuPrice")) + "��");
			}
			System.out.println("  -----------------------------------");
			System.out.println("   ���� �ѱݾ� = " + resultprices + "��");
			System.out.println("  -----------------------------------");
			System.out.println("   ��� ������ �ܾ� : " + balance + "��");
			System.out.println("  -----------------------------------");
			System.out.println("   1. ��������");
			System.out.println("   2. ����Ʈ�����ϱ�");
			System.out.println("   3. �޴� ó������ �ٽ� ���");
			System.out.println("   4. �Ĵ� ó������ �ٽ� ����");
			System.out.println("   9. �α׾ƿ�");
			System.out.println("  -----------------------------------");
			System.out.print("  ��ȣ�Է� >> ");

			while (true) {
				switch (ScanUtil.nextInt()) {
				case 1:
					if (balance >= resultprices) {
						// ���� �Ѱ����ݾ׿� ���
						// ȭ����ȯ => ������������ȭ��(�ֹ��Ϸ�Ȯ��, �ܰ�, ...)
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

						System.out.println(" ** ������ �Ϸ�Ǿ����ϴ� **");
						System.out.println(" ���͸� ������ �ֹ��Ϸ��������� ���ϴ�.");
						orderCode = "";
						EnterUtil.enterNext(2);
						return View.RECEIPT;
					} else {
						System.out.println(" �� �ܾ��� �����մϴ�.");
						while (true) {
							System.out.println(" 1. ����Ʈ �����ϱ�   2. ���(��ٱ���ȭ������ �Ѿ)");
							System.out.println("  ��ȣ�Է� >> ");
							switch (ScanUtil.nextInt()) {
							case 1:
								return View.POINT;
							case 2:
								return View.CART;
							default:
								System.out.println(" �� �߸��� �����Դϴ�. �ٽ��Է��ϼ���");
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

	// ������ȭ��: ������ó�� �ֹ�Ȯ�ΰ� ������ ��
	public int receipt() {

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		List<Map<String, Object>> orderMap = memberDAO.orderCode(userID);
		String orderCode = orderMap.get(0).get("ORDERCODE").toString();

		List<Map<String, Object>> orderMap2 = memberDAO.orderList(userID, orderCode);
		String orderEta = orderMap2.get(0).get("ORDERETA").toString();

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [ �ֹ� �Ϸ� Ȯ�� ������ ]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" �ֹ���ȣ : " + orderCode);
		System.out.println("  -----------------------------------");
		System.out.println(" " + orderEta + " �� �ֹ� ���� �����Դϴ� ~ !");
		System.out.println("  -----------------------------------");
		System.out.println(" �ֹ���������\t\t�ֹ��޴�\t\t�ֹ�����\t�޴�����");
		for (Map<String, Object> orderInfo : orderMap2) {
			BigDecimal totalPriceBigDecimal = (BigDecimal) orderInfo.get("TOTALPRICE");
			int totalPrice = totalPriceBigDecimal.intValue();
			System.out.println("  ��" + orderInfo.get("STONM").toString() + "\t\t" + orderInfo.get("MENUNM").toString()
					+ "\t\t" + orderInfo.get("ORDERQTY").toString() + "\t" + totalPrice + "��");
		}
		if (Controller.sessionStorage.get("deliOrTake").toString() == "deliYN") {
			List<Map<String, Object>> menuList = selectDAO.getMenuList(storeName);
			String storeCode = menuList.get(0).get("STOCODE").toString();
			Map<String, Object> deliCost = selectDAO.getRiderDeliCost(storeCode);
			System.out.println("  -----------------------------------");
			System.out.println("   ��޺� = " + deliCost.get("DELICOST").toString() + "��");
		}
		System.out.println("  -----------------------------------");
		System.out.println(" �� �����ݾ� :           " + resultprices + "��");
		System.out.println(" ���� ����Ʈ :           " + resultbalance + "��");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		orderList = new ArrayList<Map<String, Object>>();
		resultprices = 0;
		orderListInsert = new ArrayList<Map<String, Object>>();

		while (true) {
			System.out.println(" 1. �߰��ֹ��ϱ�          2. �α׾ƿ��ϱ�         3.���α׷������ϱ�");
			System.out.print("�Է� >> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				return View.ORDER_HOME;
			case 2:
				return View.ORDER_LOGOUT;
			case 3:
				return View.SYSTEM_EXIT;
			default:
				System.out.println(" �� �߸��� �����Դϴ�. �ٽ��Է��ϼ���");
			}
		}
	}

}