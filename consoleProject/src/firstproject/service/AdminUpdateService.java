package firstproject.service;

import java.util.ArrayList;
import java.util.List;

import firstproject.dao.AdminUpdateDAO;
import firstproject.util.EnterUtil;
import firstproject.util.GapUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class AdminUpdateService {

	private static AdminUpdateService instance = null;

	private AdminUpdateService() {
	}

	public static AdminUpdateService getInstance() {
		if (instance == null)
			instance = new AdminUpdateService();
		return instance;
	}

	AdminUpdateDAO adminUpdateDAO = AdminUpdateDAO.getInstance();
	GapUtil gapUtil = GapUtil.getInstance();

	public int memUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE MEMBER SET ";
		int flag = 0; // ���� ���� Ȯ��
		String yesOrNot = ""; // y, n �Է�

		String memId = ""; // ȸ�����̵�
		String memNm = ""; // ȸ���̸�
		String memPw = ""; // ȸ����й�ȣ
		String memAdd = ""; // ȸ���ּ�
		String memTel = ""; // ȸ������ó
		long balance = 0; // �ܿ��ݾ�
		int memlv = 0; // ȸ������

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������ ȸ�� ���̵� �Է��ϼ���. ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			memId = ScanUtil.nextLine();
			if (ValidationUtil.validationID(memId)) {
				param.add(memId);
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ȸ���� �Է� [ �ѱ� �Է� ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				memNm = ScanUtil.nextLine();
				if (ValidationUtil.validationName(memNm)) {
					sql += "MEMNM = '" + memNm + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ�� ��й�ȣ�� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ��й�ȣ �Է� [ ���������� �ʼ� / 6���� �̻� �Է� ] ");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				memPw = ScanUtil.nextLine();
				if (ValidationUtil.validationPW(memPw)) {
					sql += "MEMPW = '" + memPw + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ�� �ּҸ� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ �ּ� �Է� [ �ѱۤ����� �Է� ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				memAdd = ScanUtil.nextLine();
				if (ValidationUtil.validationAddress(memAdd)) {
					sql += "MEMADD = '" + memAdd + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ�� ����ó�� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ȸ�� ����ó �Է�[ 0XX-XXXX-XXXX �������� �Է� ] ");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				memTel = ScanUtil.nextLine();
				if (ValidationUtil.validationTEL(memTel)) {
					sql += "MEMTEL = '" + memTel + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �ܿ��ݾ��� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ �ܿ��ݾ� �Է�[ ���ڴ� 10�ڸ� ���Ϸ� �Է� ] ");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				balance = ScanUtil.nextLongLine();
				if (ValidationUtil.validationBalance(balance)) {
					sql += "BALANCE = " + balance + ", ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ�� ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("ȸ�� ���� (0 ~ 9) ");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				memlv = ScanUtil.nextIntegerLine();
				if (ValidationUtil.validationMemLv(memlv)) {
					sql += "MEMLV = " + memlv + ", ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE MEMID = ? ";

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ�� ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ȸ�� ������ �����߽��ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ȸ�� ���� ���� �������� �̵��մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_MEM_MANAGEMENT;
	}

	public int storeUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE STORE SET ";
		int flag = 0; // ���� ���� Ȯ��
		String yesOrNot = ""; // y, n �Է�

		String storeCode = ""; // ��ü�ڵ�
		String storeNm = ""; // ��ü��
		String storeAdd = ""; // ��ü�ּ�
		long minOrder = 0; // �ּ��ֹ��ݾ�
		String deliYn = ""; // ��ް�������
		String packYn = ""; // ���尡������
		String closeYn = ""; // �����������

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������ ��ü�ڵ带 �Է��ϼ���. ( ex. AGHS01 ) ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			storeCode = ScanUtil.nextLine();
			if (ValidationUtil.validationCode(storeCode)) {
				param.add(storeCode);
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ��ü���� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ��ü�� �Է� [ �ѱ�, ����, ���� �Է� ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				storeNm = ScanUtil.nextLine();
				if (ValidationUtil.validationStoreNM(storeNm)) {
					sql += "STONM = '" + storeNm + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ��ü�ּҸ� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ��ü�ּ� �Է� [ �ѱ�, ����, ���� �Է� ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				storeAdd = ScanUtil.nextLine();
				if (ValidationUtil.validationStoreAddress(storeAdd)) {
					sql += "STOADD = '" + storeAdd + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �ּ��ֹ��ݾ��� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ �ּ��ֹ��ݾ� �Է� [ 0 ~ 9999999999 ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				minOrder = ScanUtil.nextLongLine();
				if (ValidationUtil.validationMinOrder(minOrder)) {
					sql += "MINORDER = '" + minOrder + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ��ް��������� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ��� ���� : Y / ��� �Ұ� : N �� �Է��ϼ���. ���� �Ұ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				deliYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(deliYn)) {
					sql += "DELIYN = '" + deliYn + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ���尡�������� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ���� ���� : Y / ���� �Ұ� : N �� �Է��ϼ���. ���� �Ұ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				packYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(packYn)) {
					sql += "PACKYN = '" + packYn + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������������� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ���� ���� : Y / ���� �ݱ� : N �� �Է��ϼ���. ���� �Ұ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				closeYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(closeYn)) {
					sql += "CLOSEYN = '" + closeYn + "', ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE STOCODE = ? ";

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ���� ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ���� ������ �Ϸ�Ǿ����ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ���� ������ �����߽��ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ���� ���� ���� �������� �̵��մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE MENU SET ";
		int flag = 0; // ���� ���� Ȯ��
		String yesOrNot = ""; // y, n �Է�

		String menuCode = ""; // �޴��ڵ�
		String menuNm = ""; // �޴���
		long menuPrice = 0; // �޴�����
		int remainQty = 0; // �ܿ�����

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������ �޴��ڵ带 �Է��ϼ���. ( ex. YGSIG112 ) ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			menuCode = ScanUtil.nextLine();
			if (ValidationUtil.validationCode(menuCode)) {
				param.add(menuCode);
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �޴����� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ �޴��� �Է� [ �ѱ�, ���� �Է� ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				menuNm = ScanUtil.nextLine();
				if (ValidationUtil.validationMenuName(menuNm)) {
					sql += "MENUNM = '" + menuNm + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �޴������� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ �޴����� �Է� [ 0 ~ 9999999999 ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				menuPrice = ScanUtil.nextLongLine();
				if (ValidationUtil.validationPrice(menuPrice)) {
					sql += "MENUPRICE = '" + menuPrice + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �޴������� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ �޴����� �Է� [ 0 ~ 999999999 ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				remainQty = ScanUtil.nextIntegerLine();
				if (ValidationUtil.validationMenuRemainQty(remainQty)) {
					sql += "REMAINQTY = '" + remainQty + "', ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE MENUCODE = ? ";

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �޴� ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �޴� ������ �Ϸ�Ǿ����ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �޴� ������ �����߽��ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �޴� ���� ���� �������� �̵��մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int riderUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE RIDER SET ";
		int flag = 0; // ���� ���� Ȯ��
		String yesOrNot = ""; // y, n �Է�

		String riderCode = ""; // ���̴��ڵ�
		String abseYn = ""; // ��������
		long deliCost = 0; // ��޺�
		String stoCode = ""; // ��ü�ڵ�

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ������ ���̴��ڵ带 �Է��ϼ���. ( ex. YGOUT44 ) ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			riderCode = ScanUtil.nextLine();
			if (ValidationUtil.validationCode(riderCode)) {
				param.add(riderCode);
				break;
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ���̴� ���� ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ���̴� Ȱ�� ���� : Y / ���̴� Ȱ�� �Ұ��� : N �� �Է��ϼ���. ���� �Ұ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				abseYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(abseYn)) {
					sql += "ABSEYN = '" + abseYn + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ��޺� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ��޺� �Է� [ 0 ~ 9999999999 ]");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				deliCost = ScanUtil.nextLongLine();
				if (ValidationUtil.validationPrice(deliCost)) {
					sql += "DELICOST = '" + deliCost + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" �� �ŷ� ��ü�� �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" * ������ ��ü�ڵ� �Է� ( ex. AGHS01 )");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				stoCode = ScanUtil.nextLine();
				if (ValidationUtil.validationCode(stoCode)) {
					sql += "STOCODE = '" + stoCode + "', ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE RIDCODE = ? ";

		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ���̴� ������ �����Ͻðڽ��ϱ�? (y/n)");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ���̴� ������ �Ϸ�Ǿ����ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" ���̴� ������ �����߽��ϴ�.");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" ���̴� ���� ���� �������� �̵��մϴ�.");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
