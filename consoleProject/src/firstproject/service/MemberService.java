package firstproject.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.controller.Controller;
import firstproject.dao.MemberDAO;
import firstproject.dao.SelectDAO;
import firstproject.util.EnterUtil;
import firstproject.util.NullCheckUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class MemberService {

	private static MemberService instance = null;

	private MemberService() {
	}

	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberService();
		return instance;
	}

	MemberDAO memberDAO = MemberDAO.getInstance();
	SelectDAO selectDAO = SelectDAO.getInstance();
	ValidationUtil validationUtil = ValidationUtil.getInstance();

	String str = "";

	public int login() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     �α��� ȭ��");
		System.out.println(" ������������������������������������������������������������������������������������������������������");

		System.out.print("   ���̵� >> ");
		String memID = ScanUtil.nextLine();
		List<Object> searchId = new ArrayList<>();
		searchId.add(memID);

		System.out.print("   ��й�ȣ >> ");
		String memPW = ScanUtil.nextLine();
		List<Object> searchPw = new ArrayList<>();
		searchPw.add(memPW);

		Map<String, Object> memberInfoId = memberDAO.loginId(searchId);
		Map<String, Object> memberInfoPw = memberDAO.loginPw(searchPw);

		if (memberInfoId == null) {
			System.out.println(" �� ��ġ�ϴ� ���̵� �����ϴ�.");
			EnterUtil.enterNext(1);
			return View.HOME;
		} else if (memberInfoPw == null) {
			System.out.println(" �� ��ġ�ϴ� ��й�ȣ�� �����ϴ�.");
			EnterUtil.enterNext(1);
			return View.HOME;
		} else {
			List<Object> param = new ArrayList<>();
			param.add(memID);
			param.add(memPW);
			Map<String, Object> memberInfo = memberDAO.login(param);

			// �α����� ȸ���� ���� ���� ���� �ֱ�
			Controller.sessionStorage.put("login", true);
			Controller.sessionStorage.put("loginInfo", memberInfo);

			if (Controller.isAdmin()) {
//				str = "[������]�� ȯ���մϴ�!";
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				str = "   [ " + memberInfo.get("MEMNM").toString() + " ]�� ȯ���մϴ�!";
				System.out.println(str);
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
				return View.ADMIN_HOME;
			} else if (Controller.isMember()) {
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				str = "   [ " + memberInfo.get("MEMNM").toString() + " ]�� ȯ���մϴ�!";
				System.out.println(str);
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				EnterUtil.enterNext(2);
				return View.ORDER_HOME;
			}
		}
		return 0;
	}

	// ȸ������ �޼���
	public int signUp() {
		String memID = ""; // ȸ�� ���̵�
		String memPW = ""; // ȸ�� ��й�ȣ
		String memNM = ""; // ȸ�� �̸�
		String memADD = ""; // ȸ�� �ּ�
		String memTEL = ""; // ȸ�� ����ó
		long balance = 0; // �ܿ��ݾ�
		int memLv = 1; // ȸ�� ����

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [ȸ������]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println();

		while (true) {
			System.out.println(" * ���̵� �Է� [ ���������� �ʼ� / 6���� �̻� �Է� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			memID = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationID(memID))
				break;
		}

		while (true) {
			System.out.println(" * ��й�ȣ �Է� [ ���������� �ʼ� / 6���� �̻� �Է� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			memPW = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationPW(memPW))
				break;
		}

		while (true) {
			System.out.println(" * �̸� �Է� [ �ѱ� �Է� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			memNM = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationName(memNM))
				break;
		}

		while (true) {
			System.out.println(" * �ּ� �Է� [ �ѱۤ����� �Է� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			memADD = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationAddress(memADD))
				break;
		}

		while (true) {
			System.out.println(" * ����ó �Է�[ 0XX-XXXX-XXXX �������� �Է� ]");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			memTEL = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationTEL(memTEL))
				break;
		}

		while (true) {
			System.out.println(" * �ݾ��� �����Ͻðڽ��ϱ�? (y/n) ");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			String chargeYN = ScanUtil.nextLine();
			if (chargeYN.equals("y")) {
				System.out.println(" * �ݾ� �Է�[ ���ڴ� 10�ڸ� �̸����� �Է� ] ");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				balance = ScanUtil.nextLong();
				System.out.println();
			} else {
				balance = 0;
			}
			if (ValidationUtil.validationBalance(balance))
				break;
		}

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" * �Է��Ͻ� ������ ȸ������ �Ͻðڽ��ϱ�? (y/n)");
		System.out.print("   >> ");
		if (ScanUtil.nextLine().equals("y")) {
			Map<String, Object> result = memberDAO.isValID(memID);
			if (result != null) {
				System.out.println(" �� �̹� ��ϵ� ���̵� �Դϴ�!");
			} else {
				List<Object> param = new ArrayList<>();
				param.add(memID);
				param.add(memNM);
				param.add(memPW);
				param.add(memADD);
				param.add(memLv);
				param.add(balance);
				param.add(memTEL);
				int isSuccess = memberDAO.signUp(param);
				if (isSuccess > 0) {
					str = "[" + memID + "]�� ������ ȯ���մϴ�!";
					System.out.println(str);
				} else
					System.out.println(" �� ȸ������ ����!");
			}
		} else {
			System.out.println(" * ȸ�������� ����մϴ�.");
		}

		EnterUtil.enterNext(1);
		return View.HOME;
	}

	// ���̵�/��й�ȣ ã�� �޼���
	public int find() {
		boolean result = false;

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [ ���̵� / ��й�ȣ ã�� ]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println();
		System.out.println(" 1. ���̵� ã��");
		System.out.println(" 2. ��й�ȣ ã��");
		System.out.println(" 0. ���� �޴�");
		System.out.println();
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.print("   >> ");

		switch (ScanUtil.nextInt()) {
		case 1:
			result = findID();
			break;
		case 2:
			result = findPW();
			break;
		case 0:
			return View.HOME;
		default:
			System.out.println();
			System.out.println(" �� �߸��� �����Դϴ�.");
			System.out.println();
			return View.MEMBER_ID_PW_FIND;
		}

		if (result) {
			return View.HOME;
		} else {
			return View.MEMBER_ID_PW_FIND;
		}

	}

	// ���̵� ã�� �޼���
	private boolean findID() {
		boolean result = true;
		String userNM = "";
		String userTEL = "";

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [���̵� ã��]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println();

		while (true) {
			System.out.println(" * �̸��� �Է��ϼ���.");
			System.out.println();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			userNM = ScanUtil.nextLine();
			if (ValidationUtil.validationName(userNM))
				break;
		}

		while (true) {
			System.out.println(" * ��ȭ��ȣ�� �Է��ϼ���.");
			System.out.println();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			userTEL = ScanUtil.nextLine();
			if (ValidationUtil.validationTEL(userTEL))
				break;
		}

		List<Object> param = new ArrayList<>();
		param.add(userNM);
		param.add(userTEL);
		Map<String, Object> selectMem = memberDAO.findID(param);

		if (selectMem == null) {
			System.out.println(" �� �Է��� �̸��� ��ȭ��ȣ�� ���� ȸ�������� �����ϴ�.");
			result = false;
		} else {
			str = " �ش� ������ ��ȸ�� ���̵�� [" + selectMem.get("MEMID") + "] �Դϴ�.";
			System.out.println(str);
			result = true;
		}

		EnterUtil.enterNext(1);
		return result;
	}

	// ��й�ȣ ã�� �޼���
	private boolean findPW() {
		boolean result = true;
		String userID = "";
		String userTEL = "";

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [��й�ȣ ã��]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println();

		while (true) {
			System.out.println(" * ȸ�� ID�� �Է��ϼ���.");
			System.out.println();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			userID = ScanUtil.nextLine();
			if (ValidationUtil.validationID(userID))
				break;
		}

		while (true) {
			System.out.println(" * ��ȭ��ȣ�� �Է��ϼ���.");
			System.out.println();
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print("   >> ");
			userTEL = ScanUtil.nextLine();
			if (ValidationUtil.validationTEL(userTEL))
				break;
		}

		List<Object> param = new ArrayList<>();
		param.add(userID);
		param.add(userTEL);
		Map<String, Object> selectMem = memberDAO.findPW(param);

		if (selectMem == null) {
			System.out.println(" �� �Է��� ȸ��ID�� ��ȭ��ȣ�� ���� ȸ�������� �����ϴ�.");
			result = false;
		} else {
			str = " �ش� ������ ��ȸ�� ��й�ȣ�� [" + selectMem.get("MEMPW") + "] �Դϴ�.";
			System.out.println(str);
			result = true;
		}

		EnterUtil.enterNext(1);
		return result;
	}

	// ����Ʈ ���� ���� �޼ҵ�
	public int pointDeposit() {

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;

		String userID = (String) userInfo.get("MEMID");
		String userNM = (String) userInfo.get("MEMNM");
		String userPW = (String) userInfo.get("MEMPW");

		Map<String, Object> balanceOne = selectDAO.BalanceOne(userID);
		BigDecimal balanceBigDecimal = (BigDecimal) balanceOne.get("BALANCE");
		long balance = balanceBigDecimal.longValue();

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     [����Ʈ ����]");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("   [" + userID + "]���� ���� ��밡���� �ܰ� :");
		System.out.println("    ** " + balance + "�� **");

		while (true) {
			System.out.println(" 1. �����ϱ�");
			System.out.println(" 2. �ڷΰ���");
			System.out.print("   �Է�  >> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				System.out.println(" ��й�ȣ�� �Է��� �������ֽʽÿ�.");
				System.out.print(" ��й�ȣ �Է� >> ");
				String matchPW = ScanUtil.nextLine();

				if (matchPW.equals(userPW)) {
					System.out.println(" [" + userNM + "]�� �����մϴ�.");
					System.out.println(" �󸶸� �����Ͻðڽ��ϱ�?");
					System.out.print(" �ݾ� [ ���ڴ� 10�ڸ� �̸����� �Է� ]  >> ");

					long addPoint = ScanUtil.nextLong();

					while (true) {
						if (addPoint > 999999999) {
							System.out.println(" ���ǿ� �°� �ٽ� �Է��ϼ���.");
							return View.POINT;
						} else {
							System.out.println(" " + addPoint + "���� �����մϴ�.");
							balance = balance + addPoint;
							List<Object> param = new ArrayList<>();
							param.add(balance);
							param.add(userID);
							memberDAO.depositPoint(param);
							break;
						}
					}

					System.out.println(" ���͸� ������ ��ٱ��Ϸ� ���ư��ϴ�.");
					System.out.println(" ��ٱ��Ͽ��� ������ �Ϸ����ּ���.");
					EnterUtil.enterNext(2);
					return View.CART;

				} else {
					System.out.println(" �� Ʋ�� ��й�ȣ �Դϴ�. �ٽ� �Է����ּ���.");
					break;
				}
			case 2:
				return View.ORDER_HOME;

			default:
				System.out.println(" �� �߸��� �����Դϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}

	// ���������� : �ֹ�����������
	public int userPage() {

		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("     ** ���� ������ **");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("    ** �ֹ���������Ʈ : **");

		// �α��ε� ������ ������ �������κ��� ������ - �� �߿� ������ ���� ���̵������� ��
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		// ���̵������� �̿��� �ֹ��ڵ带 ������
		List<Map<String, Object>> orderCodeList = memberDAO.orderCodeSearch(userID);
//		System.out.println("orderCodeList : " + orderCodeList);

		// �ֹ����� �� ���� ó��
		if (NullCheckUtil.isEmpty(orderCodeList)) {
			System.out.println(" �� �ֹ��Ͻ� ������ �����ϴ�.");
			System.out.println(" ***************************");
		} else {

			// ����¡ ���
			int pageNumber = 1; // ���� ������ ��ȣ
			int pageSize = 5; // �������� ǥ���� ������ ��

			int startIndex = (pageNumber - 1) * pageSize; // ���� �ε���
			int endIndex = Math.min(startIndex + pageSize, orderCodeList.size()); // �� �ε���

			int pageRowCnt = orderCodeList.size(); // �ο� ����
			int totalPageSize = pageRowCnt / pageSize; // �� ������ ����

			if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
				totalPageSize++;
			}

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" 1. ������ ��� ����");
			System.out.println(" 2. �ֹ��ڵ� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // ����¡ ó��
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �������� �� ���� �����͸� ���ðڽ��ϱ�?");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, orderCodeList.size());
				totalPageSize = pageRowCnt / pageSize; // �� ������ ����
				if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
					totalPageSize++;
				}
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �ֹ� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				for (int i = startIndex; i < endIndex; i++) {
					long sum = 0;
					long countSum = 0;
					long countDeilSum = 0;
					// ���̵������� �̿��� �ֹ����� ���̺� ������
					Map<String, Object> orderCode = orderCodeList.get(i);
					List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
							String.valueOf(orderCode.get("ORDERCODE")));
					// �ֹ�Ƚ���� ���� ĭ���� ���� �ֹ����� ������
					System.out.println(" ==========================================================================");
					System.out.println(" �ֹ��ڵ� : " + orderInfoList.get(0).get("ORDERCODE")); // ��¥���� ������ ����ϱ�
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
					System.out.println(" �ֹ���¥ : " + orderInfoList.get(0).get("ORDERDATE"));
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
					for (Map<String, Object> orderItem : orderInfoList) {
						System.out.print(" �޴��� : " + orderItem.get("MENUNM") + " | "); // �ߺ�
						System.out.print(" �ֹ����� : " + orderItem.get("ORDERQTY") + "��" + " | "); // �ߺ�
						System.out.println(" �޴��� �ݾ� : " + orderItem.get("TOTALPRICE") + "��"); // �ߺ�
						countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
						sum += countSum;
					}
					if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
						countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
						sum += countDeilSum;
						System.out.println(" ��޺� : " + orderInfoList.get(0).get("DELICOST") + "��");
						System.out.println(" ���/���� : ���");
					} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
						System.out.println(" ���/���� : ����");
					}
					System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
					System.out.println(" �� �ݾ� : " + sum + "��");
					System.out.println(" ��޿���ð� : " + orderInfoList.get(0).get("ORDERETA") + "��");
					System.out.println(" �ֹ��ڸ� : " + orderInfoList.get(0).get("MEMNM"));
					System.out.println(" �ֹ����ּ� : " + orderInfoList.get(0).get("MEMADD"));
					System.out.println(" �������� : " + orderInfoList.get(0).get("SELYN"));
					System.out.println(" ==========================================================================");
				}
				while (true) {
					System.out.println(" 1. ���ϴ� ������ ����");
					System.out.println(" 0. ���� ȭ��");
					System.out.println(" ������������������������������������������������������������������������������������������������������");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println(" �� �������� ���ðڽ��ϱ�?");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� �ֹ� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println("�ش� �������� ������ �����ϴ�.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, orderCodeList.size());
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� �ֹ� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							for (int i = startIndex; i < endIndex; i++) {
								long sum = 0;
								long countSum = 0;
								long countDeilSum = 0;
								// ���̵������� �̿��� �ֹ����� ���̺� ������
								Map<String, Object> orderCode = orderCodeList.get(i);
								List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
										String.valueOf(orderCode.get("ORDERCODE")));
								// �ֹ�Ƚ���� ���� ĭ���� ���� �ֹ����� ������
								System.out.println(
										" ==========================================================================");
								System.out.println(" �ֹ��ڵ� : " + orderInfoList.get(0).get("ORDERCODE")); // ��¥���� ������ ����ϱ�
								System.out.println(
										" ����������������������������������������������������������������������������������������������������������������������������������������������������");
								System.out.println(" �ֹ���¥ : " + orderInfoList.get(0).get("ORDERDATE"));
								System.out.println(
										" ����������������������������������������������������������������������������������������������������������������������������������������������������");
								for (Map<String, Object> orderItem : orderInfoList) {
									System.out.print(" �޴��� : " + orderItem.get("MENUNM") + " | "); // �ߺ�
									System.out.print(" �ֹ����� : " + orderItem.get("ORDERQTY") + "��" + " | "); // �ߺ�
									System.out.println(" �޴��� �ݾ� : " + orderItem.get("TOTALPRICE") + "��"); // �ߺ�
									countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
									sum += countSum;
								}
								if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
									countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
									sum += countDeilSum;
									System.out.println(" ��޺� : " + orderInfoList.get(0).get("DELICOST") + "��");
									System.out.println(" ���/���� : ���");
								} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
									System.out.println(" ���/���� : ����");
								}
								System.out.println(
										" ����������������������������������������������������������������������������������������������������������������������������������������������������");
								System.out.println(" �� �ݾ� : " + sum + "��");
								System.out.println(" ��޿���ð� : " + orderInfoList.get(0).get("ORDERETA") + "��");
								System.out.println(" �ֹ��ڸ� : " + orderInfoList.get(0).get("MEMNM"));
								System.out.println(" �ֹ����ּ� : " + orderInfoList.get(0).get("MEMADD"));
								System.out.println(" �������� : " + orderInfoList.get(0).get("SELYN"));
								System.out.println(
										" ==========================================================================");
							}
						}
					} else if (pageListSelect == 0) {
						return View.ORDER_HOME;
					}
				}
			} else if (choice == 2) { // �κ� �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				for (Map<String, Object> orderCode : orderCodeList) {
					System.out.println(" �ֹ��ڵ� : " + orderCode.get("ORDERCODE"));
				}
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" �ֹ��ڵ� �Է� >> ");
				String memIDstr = ScanUtil.nextLine();
				for (Map<String, Object> orderCode : orderCodeList) {
					if (memIDstr.equals(orderCode.get("ORDERCODE"))) {
						long sum = 0;
						long countSum = 0;
						long countDeilSum = 0;
						// ���̵������� �̿��� �ֹ����� ���̺� ������
						List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
								String.valueOf(orderCode.get("ORDERCODE")));
						// �ֹ�Ƚ���� ���� ĭ���� ���� �ֹ����� ������
						System.out
								.println(" ==========================================================================");
						System.out.println(" �ֹ��ڵ� : " + orderInfoList.get(0).get("ORDERCODE")); // ��¥���� ������ ����ϱ�
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(" �ֹ���¥ : " + orderInfoList.get(0).get("ORDERDATE"));
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> orderItem : orderInfoList) {
							System.out.print(" �޴��� : " + orderItem.get("MENUNM") + " | "); // �ߺ�
							System.out.print(" �ֹ����� : " + orderItem.get("ORDERQTY") + "��" + " | "); // �ߺ�
							System.out.println(" �޴��� �ݾ� : " + orderItem.get("TOTALPRICE") + "��"); // �ߺ�
							countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
							sum += countSum;
						}
						if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
							countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
							sum += countDeilSum;
							System.out.println(" ��޺� : " + orderInfoList.get(0).get("DELICOST") + "��");
							System.out.println(" ���/���� : ���");
						} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
							System.out.println(" ���/���� : ����");
						}
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(" �� �ݾ� : " + sum + "��");
						System.out.println(" ��޿���ð� : " + orderInfoList.get(0).get("ORDERETA") + "��");
						System.out.println(" �ֹ��ڸ� : " + orderInfoList.get(0).get("MEMNM"));
						System.out.println(" �ֹ����ּ� : " + orderInfoList.get(0).get("MEMADD"));
						System.out.println(" �������� : " + orderInfoList.get(0).get("SELYN"));
						System.out
								.println(" ==========================================================================");
					}
				}
			}

//			for(Map<String, Object> orderCode : orderCodeList) {
//				long sum = 0;
//				long countSum = 0;
//				long countDeilSum = 0;
//				// ���̵������� �̿��� �ֹ����� ���̺� ������
//				List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID, String.valueOf(orderCode.get("ORDERCODE")));
//				// �ֹ�Ƚ���� ���� ĭ���� ���� �ֹ����� ������
//				System.out.println(" �ֹ��ڵ� : " + orderInfoList.get(0).get("ORDERCODE")); // ��¥���� ������ ����ϱ�
//				System.out.println("---------------------------------------");
//				System.out.println(" �ֹ���¥ : " + orderInfoList.get(0).get("ORDERDATE"));
//				System.out.println("---------------------------------------");
//				for(Map<String, Object> orderItem : orderInfoList) {
//					System.out.print(" �޴��� : " + orderItem.get("MENUNM") + " | "); // �ߺ�
//					System.out.print(" �ֹ����� : " + orderItem.get("ORDERQTY") + "��"  + " | "); // �ߺ�
//					System.out.println(" �޴��� �ݾ� : " + orderItem.get("TOTALPRICE") + "��"); // �ߺ�
//					countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
//					sum += countSum;
//					if(orderItem.get("DELIORTAKE").equals("deliYN")) {
//						System.out.println(" ��޺� : " + orderInfoList.get(0).get("DELICOST") + "��");
//						countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
//						sum += countDeilSum;
//					}
//				}
//				if(orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
//					System.out.println(" ���/���� : ���");
//				}else if(orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
//					System.out.println(" ���/���� : ����");
//				}
//				System.out.println("---------------------------------------");
//				System.out.println(" �� �ݾ� : " + sum + "��");
//				System.out.println(" ��޿���ð� : " + orderInfoList.get(0).get("ORDERETA") + "��");
//				System.out.println(" �ֹ��ڸ� : " + orderInfoList.get(0).get("MEMNM"));
//				System.out.println(" �ֹ����ּ� : " + orderInfoList.get(0).get("MEMADD"));
//				System.out.println(" �������� : " + orderInfoList.get(0).get("SELYN"));
//				System.out.println(" ***************************");
//			}

			while (true) {
				System.out.println(" 0. ���� ȭ��");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" �Է� >> ");
				switch (ScanUtil.nextInt()) {
				case 0:
					return View.ORDER_HOME;
				default:
					System.out.println(" �� �߸��� �����Դϴ�. �ٽ� �Է��ϼ���.");
					break;
				}
			}
		}
		return View.ORDER_HOME;
	}
}