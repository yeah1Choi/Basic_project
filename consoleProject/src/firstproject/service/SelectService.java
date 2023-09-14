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

	// ORDER_HOME���� ������
//	public int takeout() { 
//
//		System.out.println("--- �ֹ� ��� ���� ---");
//		System.out.println("   1. ���");
//		System.out.println("   2. ����");
//		System.out.println("------------------");
//		System.out.println("   0. ����������");
//
//		System.out.println();
//		System.out.print("�Է� >> ");
//		choice = ScanUtil.nextInt();
//		if (choice == 1) {
//			return category(View.CATEGORY_DELI);
//		} else if (choice == 2) {
//			return category(View.CATEGORY_TAKE);
//		} else if (choice == 0) {
//			return category(View.USERPAGE);
//		} else {
//			System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
//			return View.TAKEOUT;
//		}
//
//	}

	// ȸ�� �α׾ƿ�
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

			// ���� �ʱ�ȭ
			Controller.sessionStorage.put("login", null);
			Controller.sessionStorage.put("loginInfo", null);

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * ����� ȭ������ ���ư��ϴ�.");
			return View.ORDER_HOME;
		} else {
			System.out.println(" >> �ùٸ� �Է��� �ƴմϴ�. �ٽ� �Է��ϼ���.");
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

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [ī�װ� ����]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     1. �ѽ�            2. ���                     3. �߽�");
			System.out.println("     4. �Ͻ�            5. ī��-����Ʈ          9. ����Ȩ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" �Է� >> ");

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
				System.out.println(" �� �߸��� �Է��Դϴ�. ���/���� ���� ȭ������ �Ѿ�ϴ�.");
				return View.ORDER_HOME;
			}

		case View.CATEGORY_TAKE:
			takeYN = "Y";
			Controller.sessionStorage.put("deliOrTake", null);
			Controller.sessionStorage.put("deliOrTake", "takeYN");
//			System.out.println(Controller.sessionStorage.toString());

			System.out.println();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [ī�װ� ����]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     1. �ѽ�      2. ���                3. �߽�");
			System.out.println("     4. �Ͻ�      5. ī��-����Ʈ     9. ����Ȩ");
			System.out.println();
			System.out.print(" ��ȣ �Է� >> ");

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
				System.out.println(" �� �߸��� �Է��Դϴ�. ���/���� ���� ȭ������ �Ѿ�ϴ�.");
				return View.ORDER_HOME;
			}
		}
		return deliOrTake;
	}

	public int korean() {
		if (deliYN == "Y") {
			List<Map<String, Object>> koreandeli = selectDAO.koreandelilist();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [�ѽ� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			// �ѽ� ����Ʈ��(����([1])\t�������̸�\t�ּұݾ��ֹ�\t��ް�������\t���尡������)
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [�ѽ� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [��� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [��� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [�߽� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [�߽� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [�Ͻ� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [�Ͻ� ������]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [����Ʈ/ī��]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("     [����Ʈ/ī��]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������\t�������\t�ּ��ֹ��ݾ�\t��ް�������\t���尡������\t�����ּ�");
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
