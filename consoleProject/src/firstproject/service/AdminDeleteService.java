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
		String memIDstr = ""; // ȸ�� ���̵�
		String deleteMem = ""; // ��� ����

		List<Map<String, Object>> memALL = adminReadDAO.memALL();

		if (NullCheckUtil.isEmpty(memALL)) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ϵ� ȸ���� �����ϴ�!");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
		} else {
			int maxIdLen = gapUtil.gapFullCnt(memALL, "MEMID");
			int maxNmLen = gapUtil.gapFullCnt(memALL, "MEMNM");
			int maxPwLen = gapUtil.gapFullCnt(memALL, "MEMPW");
			int maxAddLen = gapUtil.gapFullCnt(memALL, "MEMADD");
			int maxLvLen = gapUtil.gapFullCnt(memALL, "MEMLV");
			int maxBalLen = gapUtil.gapFullCnt(memALL, "BALANCE");
			int maxTelLen = gapUtil.gapFullCnt(memALL, "MEMTEL");

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("ȸ�� ����");
			System.out.println("1. ȸ�����̵� �Է�");
			System.out.println("2. ȸ���� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int memChoice = ScanUtil.nextIntegerLine();
			if (memChoice == 1) {
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("�����Ͻ� ȸ�� ���̵� �Է��� �ּ���.");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.print(" >> ");
					memIDstr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationID(memIDstr))
						break;
				}
			} else if (memChoice == 2) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �˻��Ͻ� ȸ�� �̸��� �Է��� �ּ���.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				String memNMstr = ScanUtil.nextLine();
				List<Object> searchNM = new ArrayList<>();
				searchNM.add(memNMstr);
				List<Map<String, Object>> memberInfo = adminReadDAO.memSearchFromNm(searchNM);
				if (NullCheckUtil.isEmpty(memberInfo)) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("��ϵ� ȸ���� �����ϴ�!");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
				} else {
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
					System.out.println(
							" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" [ " + memIDstr + " ] ȸ���� �����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			deleteMem = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteMem))
				break;
		}

		if (deleteMem.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("ȸ�� ������ ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
			return View.ADMIN_MEM_MANAGEMENT;
		} else if (deleteMem.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(memIDstr);
			Map<String, Object> memLvStr = adminDeleteDAO.searchMemLv(param);
			int memLv = Integer.parseInt(memLvStr.get("MEMLV").toString());
			if (memLv == 9) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("������ ������ ������ �� �����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				int isSuccess = adminDeleteDAO.deleteMember(param);
				if (isSuccess > 0) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("ȸ�� ������ �Ϸ�Ǿ����ϴ�!");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					EnterUtil.enterNext(2);
				} else {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println(">> ȸ�� ���� ����! <<");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					EnterUtil.enterNext(2);
				}
			}
		}

		return View.ADMIN_MEM_MANAGEMENT;
	}

	public int storeDelete() {
		String storeCodeStr = ""; // ��ü �ڵ�
		String deleteStore = ""; // ��ü ����

		List<Map<String, Object>> storeALL = adminReadDAO.storeALL();

		if (NullCheckUtil.isEmpty(storeALL)) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ϵ� ��ü�� �����ϴ�!");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
		} else {
			int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
			int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
			int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
			int maxMinLen = gapUtil.gapFullCnt(storeALL, "MINORDER");
			int maxPackLen = gapUtil.gapFullCnt(storeALL, "PACKYN");
			int maxCloseLen = gapUtil.gapFullCnt(storeALL, "CLOSEYN");
			int maxCateLen = gapUtil.gapFullCnt(storeALL, "CATECODE");
			int maxDeliLen = gapUtil.gapFullCnt(storeALL, "DELIYN");

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ü ����");
			System.out.println("1. ��ü�ڵ� �Է�");
			System.out.println("2. ��ü�� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int storeChoice = ScanUtil.nextIntegerLine();
			if (storeChoice == 1) {
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("�����Ͻ� ��ü�ڵ带 �Է��� �ּ���.");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.print(" >> ");
					storeCodeStr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationCode(storeCodeStr))
						break;
				}
			} else if (storeChoice == 2) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �˻��Ͻ� ��ü���� �Է��� �ּ���.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				String storeNMstr = ScanUtil.nextLine();
				List<Object> searchStoName = new ArrayList<>();
				searchStoName.add(storeNMstr);
				List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoName);
				if (NullCheckUtil.isEmpty(storeInfo)) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("��ϵ� ��ü�� �����ϴ�!");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
				} else {
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
					System.out.println("��ü�ڵ�    ��ü��         ��ü�ּ�     �ּ��ֹ��ݾ�     ���尡��    �������     �ֹ����ɿ���     ī�װ��ڵ�");
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" [ " + stoNameStr + " ] ��ü�� �����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			deleteStore = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteStore))
				break;
		}

		if (deleteStore.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ü ������ ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
			return View.ADMIN_STORE_MANAGEMENT;
		} else if (deleteStore.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(storeCodeStr);
			int isSuccess = adminDeleteDAO.deleteStore(param);
			if (isSuccess > 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("��ü ������ �Ϸ�Ǿ����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(">> ��ü ���� ����! <<");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuDelete() {
		String menuCodeStr = ""; // �޴� �ڵ�
		String deleteMenu = ""; // �޴� ����

		List<Map<String, Object>> menuALL = adminReadDAO.menuALL();

		if (NullCheckUtil.isEmpty(menuALL)) {
			System.out.println("��ϵ� �޴��� �����ϴ�!");
		} else {
			int maxMenuCodeLen = gapUtil.gapFullCnt(menuALL, "MENUCODE");
			int maxNmLen = gapUtil.gapFullCnt(menuALL, "MENUNM");
			int maxPriceLen = gapUtil.gapFullCnt(menuALL, "MENUPRICE");
			int maxQtyLen = gapUtil.gapFullCnt(menuALL, "REMAINQTY");
			int maxStoCodeLen = gapUtil.gapFullCnt(menuALL, "STOCODE");

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴� ����");
			System.out.println("1. �޴��ڵ� �Է�");
			System.out.println("2. �޴��� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int menuChoice = ScanUtil.nextIntegerLine();
			if (menuChoice == 1) {
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("�����Ͻ� �޴��ڵ带 �Է��� �ּ���.");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.print(" >> ");
					menuCodeStr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationCode(menuCodeStr))
						break;
				}
			} else if (menuChoice == 2) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �˻��Ͻ� �޴����� �Է��� �ּ���.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				String storeMenuNameStr = ScanUtil.nextLine();
				List<Object> searchMenuName = new ArrayList<>();
				searchMenuName.add(storeMenuNameStr);
				List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromMenuName(searchMenuName);
				if (NullCheckUtil.isEmpty(storeInfo)) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("��ϵ� �޴��� �����ϴ�!");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
				} else {
					System.out.println(" ����������������������������������������������������������������������������������������������������");
					System.out.println("�޴��ڵ�        �޴���                                         ����     ���   ��ü�ڵ�");
					System.out.println(" ����������������������������������������������������������������������������������������������������");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" [ " + menuNameStr + " ] �޴��� �����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			deleteMenu = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteMenu))
				break;
		}

		if (deleteMenu.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("�޴� ������ ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
			return View.ADMIN_MENU_MANAGEMENT;
		} else if (deleteMenu.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(menuCodeStr);
			int isSuccess = adminDeleteDAO.deleteMenu(param);
			if (isSuccess > 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("�޴� ������ �Ϸ�Ǿ����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(">> �޴� ���� ����! <<");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int riderDelete() {
		String riderCodeStr = ""; // ���̴� �ڵ�
		String deleteRider = ""; // ���̴� ����

		List<Map<String, Object>> riderALL = adminReadDAO.riderALL();
		List<Map<String, Object>> storeALL = adminReadDAO.storeALL();

		if (NullCheckUtil.isEmpty(riderALL)) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ϵ� ���̴��� �����ϴ�!");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
		} else if (NullCheckUtil.isEmpty(storeALL)) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ϵ� ���԰� �����ϴ�!");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
		} else {
			int maxRideCodeLen = gapUtil.gapFullCnt(riderALL, "RIDCODE");
			int maxStoNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
			int maxStoAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���̴� ����");
			System.out.println("1. ���̴��ڵ� �Է�");
			System.out.println("2. ��ü�� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int menuChoice = ScanUtil.nextIntegerLine();
			if (menuChoice == 1) {
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("�����Ͻ� ���̴��ڵ带 �Է��� �ּ���.");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.print(" >> ");
					riderCodeStr = ScanUtil.nextLine();
					System.out.println();
					if (ValidationUtil.validationCode(riderCodeStr))
						break;
				}
			} else if (menuChoice == 2) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �˻��Ͻ� ��ü���� �Է��� �ּ���.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				String storeNameStr = ScanUtil.nextLine();
				List<Object> searchStoreName = new ArrayList<>();
				searchStoreName.add(storeNameStr);
				List<Map<String, Object>> storeInfo = adminDeleteDAO.riderSearchFromStoreName(searchStoreName);
				if (NullCheckUtil.isEmpty(storeInfo)) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.println("��ϵ� ���̴��� �����ϴ�!");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
				} else {
					System.out.println(" ��������������������������������������������������");
					System.out.println("���̴��ڵ�   ��ü��         ��ü�ּ�");
					System.out.println(" ��������������������������������������������������");
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
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" [ " + riderCodeStr + " ] ���̴��� �����Ͻðڽ��ϱ�? (y / n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			deleteRider = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(deleteRider))
				break;
		}

		if (deleteRider.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("���̴� ������ ����մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
			return View.ADMIN_RIDER_MANAGEMENT;
		} else if (deleteRider.equals("y")) {
			List<Object> param = new ArrayList<>();
			param.add(riderCodeStr);
			int isSuccess = adminDeleteDAO.deleteRider(param);
			if (isSuccess > 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("���̴� ������ �Ϸ�Ǿ����ϴ�!");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(">> ���̴� ���� ����! <<");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
