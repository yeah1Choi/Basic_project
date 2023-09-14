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
		String CodeStr = ""; // ��ü�ڵ�
		String StoNm = ""; // ��ü��
		String StoAdd = ""; // ������ü�ּ� : daejeonRandGu
		long MinOrder = 0; // �ּ��ֹ��ݾ�
		String DeliYn = ""; // ��ް�������
		String PackYn = ""; // ���尡������
		String CloseYn = ""; // �����������
		String CateCode = ""; // ī�װ��ڵ�
		String createStore = ""; // ���� ����

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���� ���԰� ���������� ��� ���� ��ġ�� �ֽ��ϱ�?");
			System.out.println("1. �����");
			System.out.println("2. ����");
			System.out.println("3. ����");
			System.out.println("4. ������");
			System.out.println("5. �߱�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println("�ùٷ� �Է��� �ּ���.");
			}
		}

		String daejeonRandGu = "���������� ";
		if (CodeStr.equals("AG")) {
			daejeonRandGu += "����� ";
		} else if (CodeStr.equals("DG")) {
			daejeonRandGu += "���� ";
		} else if (CodeStr.equals("SG")) {
			daejeonRandGu += "���� ";
		} else if (CodeStr.equals("YG")) {
			daejeonRandGu += "������ ";
		} else if (CodeStr.equals("JG")) {
			daejeonRandGu += "�߱� ";
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���� ���԰� ��� ī�װ� ������ ���Ͻʴϱ�?");
			System.out.println("1. �ѽ�");
			System.out.println("2. ���");
			System.out.println("3. �߽�");
			System.out.println("4. �Ͻ�");
			System.out.println("5. ����Ʈ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println("�ùٷ� �Է��� �ּ���.");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ü���� �Է��� �ּ���.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			StoNm = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationStoreNM(StoNm))
				break;
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ü ������ �ּҸ� �Է��� �ּ���.");
			System.out.println();
			System.out.println("�Է� ���� : ���ּ� ����");
			System.out.println("���� >> XX�� 00���� 00");
			System.out.println();
			System.out.println("���� ���� ��ġ : " + daejeonRandGu);
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			StoAdd = ScanUtil.nextLine();
			if (ValidationUtil.validationStoreAddress(StoAdd)) {
				daejeonRandGu += StoAdd; // ���� ��ü�ּ�
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�ּ��ֹ��ݾ��� �Է��� �ּ���. (0 ~ 9999999999)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			MinOrder = ScanUtil.nextLong();
			if (ValidationUtil.validationMinOrder(MinOrder))
				break;
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��� ���� �մϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			DeliYn = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(DeliYn)) {
				DeliYn = DeliYn.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���� ���� �մϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			PackYn = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(PackYn)) {
				PackYn = PackYn.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("������ ���� �Ǿ����ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			CloseYn = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(CloseYn)) {
				CloseYn = CloseYn.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���Ը� ����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			createStore = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(createStore))
				break;
		}

		if (createStore.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���� ����� ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("���� ����� �Ϸ�Ǿ����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(">> ���� ��� ����! <<");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuCreate() {
		String menuCode = ""; // �޴��ڵ�
		String menuNm = ""; // �޴���
		long menuPrice = 0; // �޴�����
		int remainQty = 0; // �ܿ�����
		String stoCode = ""; // ��ü�ڵ�

		String createMenu = ""; // �޴� ����

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("��� ���Կ��� �޴��� ����Ͻʴϱ�?");
		System.out.println("1. ��ü�ڵ�� ���");
		System.out.println("2. ��ü������ ��ü�ڵ� �˻�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");
		int storeChoi = ScanUtil.nextIntegerLine();
		if (storeChoi == 1) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ��ü�ڵ� �Է�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				stoCode = ScanUtil.nextLine();
				if (ValidationUtil.validationCode(stoCode)) {
					menuCode = stoCode.substring(2, 4); // ī�װ��ڵ�
					break;
				}
			}
		} else if (storeChoi == 2) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ��ü���� �Է��� �ּ���.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			String storeName = ScanUtil.nextLine();
			List<Object> searchStoreName = new ArrayList<>();
			searchStoreName.add(storeName);
			List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoreName);
			if (NullCheckUtil.isEmpty(storeInfo)) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("��ϵ� ��ü�� �����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
			} else {
				List<Map<String, Object>> storeALL = adminReadDAO.storeALL();
				int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
				int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
				int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println("��ü�ڵ�    ��ü��         ��ü�ּ�");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("[ " + stoNameStr + " ] ���Կ� �޴��� ����մϴ�.");
			System.out.println();
			System.out.println("���� ������ �޴��� ����Ͻðڽ��ϱ�?");
			System.out.println();
			System.out.println("1. ��ǥ�޴�");
			System.out.println("2. �̱۸޴�");
			System.out.println("3. ��Ʈ�޴�");
			System.out.println("4. ���̵�޴�");
			System.out.println("5. ����޴�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println("�ùٷ� �Է��� �ּ���.");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴����� �Է��� �ּ���. (�ѱ۸� �Է� ����, ���� ���)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			menuNm = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationMenuName(menuNm))
				break;
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴������� �Է��� �ּ���. (0 ~ 9999999999)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			menuPrice = ScanUtil.nextLong();
			System.out.println();
			if (ValidationUtil.validationPrice(menuPrice))
				break;
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴� ������ �Է��� �ּ���. (0 ~ 999999999)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			remainQty = ScanUtil.nextInt();
			System.out.println();
			if (ValidationUtil.validationMenuRemainQty(remainQty))
				break;
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴��� ����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			createMenu = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(createMenu))
				break;
		}

		if (createMenu.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴� ����� ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("�޴� ����� �Ϸ�Ǿ����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(">> �޴� ��� ����! <<");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int riderCreate() {
		String riderCode = ""; // ���̴��ڵ�
		String AbseYN = ""; // ��������
		long deliCost = 0; // ��޺�
		String stoCode = ""; // ��ü�ڵ�

		String createRider = ""; // ���̴� ����

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("��� ���Կ� ���̴��� ����Ͻʴϱ�?");
		System.out.println("1. ��ü�ڵ�� �Է�");
		System.out.println("2. ��ü�� �˻�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print(" >> ");
		int storeChoi = ScanUtil.nextIntegerLine();
		if (storeChoi == 1) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ��ü�ڵ� �Է�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				stoCode = ScanUtil.nextLine();
				if (ValidationUtil.validationCode(stoCode))
					break;
			}
		} else if (storeChoi == 2) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ��ü���� �Է��� �ּ���.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			String storeName = ScanUtil.nextLine();
			List<Object> searchStoreName = new ArrayList<>();
			searchStoreName.add(storeName);
			List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoreName);
			if (NullCheckUtil.isEmpty(storeInfo)) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("��ϵ� ��ü�� �����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
			} else {
				List<Map<String, Object>> storeALL = adminReadDAO.storeALL();
				int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
				int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
				int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println("��ü�ڵ�    ��ü��         ��ü�ּ�");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("[ " + stoNameStr + " ] ���Կ� ���̴��� ����մϴ�.");
			System.out.println();
			System.out.println("���� ���̴��� ���������� ��� ���� ��ġ�� �ֽ��ϱ�?");
			System.out.println("1. �����");
			System.out.println("2. ����");
			System.out.println("3. ����");
			System.out.println("4. ������");
			System.out.println("5. �߱�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println("�ùٷ� �Է��� �ּ���.");
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���� �ٹ��Ͻô� ��ü���� ��� ���°� ��� �ǽʴϱ�?");
			System.out.println();
			System.out.println("1. ���Ӱ��");
			System.out.println("2. ���ְ��");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int contractSelect = ScanUtil.nextIntegerLine();
			if (contractSelect == 1) {
				riderCode += "CON";
				break;
			} else if (contractSelect == 2) {
				riderCode += "OUT";
				break;
			} else {
				System.out.println("�ùٷ� �Է��� �ּ���.");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���� ����� �����Ͻʴϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			AbseYN = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(AbseYN)) {
				AbseYN = AbseYN.toUpperCase();
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��޺� �Է��� �ּ���. (0 ~ 9999999999)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			deliCost = ScanUtil.nextLong();
			System.out.println();
			if (ValidationUtil.validationPrice(deliCost))
				break;
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���̴��� ����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			createRider = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(createRider))
				break;
		}

		if (createRider.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���̴� ����� ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
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
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("���̴� ����� �Ϸ�Ǿ����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(">> ���̴� ��� ����! <<");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
