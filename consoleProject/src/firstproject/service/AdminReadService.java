package firstproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.dao.AdminReadDAO;
import firstproject.util.EnterUtil;
import firstproject.util.GapUtil;
import firstproject.util.NullCheckUtil;
import firstproject.util.ScanUtil;
import firstproject.util.View;

public class AdminReadService {

	private static AdminReadService instance = null;

	private AdminReadService() {
	}

	public static AdminReadService getInstance() {
		if (instance == null)
			instance = new AdminReadService();
		return instance;
	}

	AdminReadDAO adminReadDAO = AdminReadDAO.getInstance();
	GapUtil gapUtil = GapUtil.getInstance();

	public int memRead() {
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

			// ����¡ ���
			int pageNumber = 1; // ���� ������ ��ȣ
			int pageSize = 5; // �������� ǥ���� ������ ��

			int startIndex = (pageNumber - 1) * pageSize; // ���� �ε���
			int endIndex = Math.min(startIndex + pageSize, memALL.size()); // �� �ε���

			int pageRowCnt = memALL.size(); // �ο� ����
			int totalPageSize = pageRowCnt / pageSize; // �� ������ ����

			if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
				totalPageSize++;
			}

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" 1. ��ü��� ����");
			System.out.println(" 2. ������ ��� ����");
			System.out.println(" 3. �κ� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // ��ü �˻�
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� ȸ�� �� " + pageRowCnt + " ��");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(
						" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				for (Map<String, Object> item : memALL) {
					int memIDLen = gapUtil.gapCnt(item, "MEMID");
					int memNMLen = gapUtil.gapCnt(item, "MEMNM");
					int memPWLen = gapUtil.gapCnt(item, "MEMPW");
					int memADDLen = gapUtil.gapCnt(item, "MEMADD");
					int memLVLen = gapUtil.gapCnt(item, "MEMLV");
					int memBALLen = gapUtil.gapCnt(item, "BALANCE");
					int memTELLen = gapUtil.gapCnt(item, "MEMTEL");

					StringBuilder memID = gapUtil.gapFullSpace(String.valueOf(item.get("MEMID")), maxIdLen, memIDLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")), maxNmLen, memNMLen);
					StringBuilder memPW = gapUtil.gapFullSpace(String.valueOf(item.get("MEMPW")), maxPwLen, memPWLen);
					StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")), maxAddLen,
							memADDLen);
					StringBuilder memLV = gapUtil.gapFullSpace(String.valueOf(item.get("MEMLV")), maxLvLen, memLVLen);
					StringBuilder memBAL = gapUtil.gapFullSpace(String.valueOf(item.get("BALANCE")), maxBalLen,
							memBALLen);
					StringBuilder memTEL = gapUtil.gapFullSpace(String.valueOf(item.get("MEMTEL")), maxTelLen,
							memTELLen);
					System.out.printf(
							"%-" + maxIdLen + "s%-" + maxNmLen + "s%-" + maxPwLen + "s%-" + maxAddLen + "s%-"
									+ maxBalLen + "s%-" + maxLvLen + "s%-" + maxTelLen + "s\n",
							memID, memNM, memPW, memADD, memBAL, memLV, memTEL);
				}
				EnterUtil.enterNext(1);
			} else if (choice == 2) { // ����¡ �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �������� �� ���� �����͸� ���ðڽ��ϱ�?");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, memALL.size());
				totalPageSize = pageRowCnt / pageSize; // �� ������ ����
				if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
					totalPageSize++;
				}
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� ȸ�� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(
						" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
				System.out.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
				for (int i = startIndex; i < endIndex; i++) {
					int memIDLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMID")));
					int memNMLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMNM")));
					int memPWLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMPW")));
					int memADDLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMADD")));
					int memLVLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMLV")));
					int memBALLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("BALANCE")));
					int memTELLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMTEL")));

					StringBuilder memID = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMID")), maxIdLen,
							memIDLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMNM")), maxNmLen,
							memNMLen);
					StringBuilder memPW = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMPW")), maxPwLen,
							memPWLen);
					StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMADD")), maxAddLen,
							memADDLen);
					StringBuilder memLV = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMLV")), maxLvLen,
							memLVLen);
					StringBuilder memBAL = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("BALANCE")), maxBalLen,
							memBALLen);
					StringBuilder memTEL = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMTEL")), maxTelLen,
							memTELLen);
					System.out.printf(
							"%-" + maxIdLen + "s %-" + maxNmLen + "s %-" + maxPwLen + "s %-" + maxAddLen + "s %"
									+ maxBalLen + "s %" + maxLvLen + "s %" + maxTelLen + "s \n",
							memID, memNM, memPW, memADD, memBAL, memLV, memTEL);
				}
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
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
									" �� ȸ�� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println("�ش� �������� ������ �����ϴ�.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, memALL.size());
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� ȸ�� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������");
							for (int i = startIndex; i < endIndex; i++) {
								int memIDLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMID")));
								int memNMLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMNM")));
								int memPWLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMPW")));
								int memADDLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMADD")));
								int memLVLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMLV")));
								int memBALLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("BALANCE")));
								int memTELLen = gapUtil.gapCnt(String.valueOf(memALL.get(i).get("MEMTEL")));

								StringBuilder memID = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMID")),
										maxIdLen, memIDLen);
								StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMNM")),
										maxNmLen, memNMLen);
								StringBuilder memPW = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMPW")),
										maxPwLen, memPWLen);
								StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMADD")),
										maxAddLen, memADDLen);
								StringBuilder memLV = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMLV")),
										maxLvLen, memLVLen);
								StringBuilder memBAL = gapUtil.gapFullSpace(
										String.valueOf(memALL.get(i).get("BALANCE")), maxBalLen, memBALLen);
								StringBuilder memTEL = gapUtil.gapFullSpace(String.valueOf(memALL.get(i).get("MEMTEL")),
										maxTelLen, memTELLen);
								System.out.printf(
										"%-" + maxIdLen + "s %-" + maxNmLen + "s %-" + maxPwLen + "s %-" + maxAddLen
												+ "s %" + maxBalLen + "s %" + maxLvLen + "s %" + maxTelLen + "s \n",
										memID, memNM, memPW, memADD, memBAL, memLV, memTEL);
							}
						}
					} else if (pageListSelect == 0) {
						return View.ADMIN_MEM_MANAGEMENT;
					}
				}
			} else if (choice == 3) { // �κ� �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" 1. ���̵�� �˻�");
				System.out.println(" 2. �̸����� �˻�");
				System.out.println(" 3. ��ȭ��ȣ�� �˻�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("ȸ�� ���̵� >> ");
					String memIDstr = ScanUtil.nextLine();
					List<Object> searchId = new ArrayList<>();
					searchId.add(memIDstr);
					Map<String, Object> memberInfo = adminReadDAO.memSearchFromId(searchId);
					if (NullCheckUtil.isEmpty(memberInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ȸ���� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						int memIDLen = gapUtil.gapCnt(memberInfo, "MEMID");
						int memNMLen = gapUtil.gapCnt(memberInfo, "MEMNM");
						int memPWLen = gapUtil.gapCnt(memberInfo, "MEMPW");
						int memADDLen = gapUtil.gapCnt(memberInfo, "MEMADD");
						int memLVLen = gapUtil.gapCnt(memberInfo, "MEMLV");
						int memBALLen = gapUtil.gapCnt(memberInfo, "BALANCE");
						int memTELLen = gapUtil.gapCnt(memberInfo, "MEMTEL");

						StringBuilder memID = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("MEMID")), maxIdLen,
								memIDLen);
						StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("MEMNM")), maxNmLen,
								memNMLen);
						StringBuilder memPW = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("MEMPW")), maxPwLen,
								memPWLen);
						StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("MEMADD")), maxAddLen,
								memADDLen);
						StringBuilder memLV = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("MEMLV")), maxLvLen,
								memLVLen);
						StringBuilder memBAL = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("BALANCE")),
								maxBalLen, memBALLen);
						StringBuilder memTEL = gapUtil.gapFullSpace(String.valueOf(memberInfo.get("MEMTEL")), maxTelLen,
								memTELLen);
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.printf(
								"%-" + maxIdLen + "s%-" + maxNmLen + "s%-" + maxPwLen + "s%-" + maxAddLen + "s%-"
										+ maxBalLen + "s%-" + maxLvLen + "s%-" + maxTelLen + "s\n",
								memID, memNM, memPW, memADD, memBAL, memLV, memTEL);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("ȸ�� �̸� >> ");
					String memNMstr = ScanUtil.nextLine();
					List<Object> searchNM = new ArrayList<>();
					searchNM.add(memNMstr);
					List<Map<String, Object>> memberInfo = adminReadDAO.memSearchFromNm(searchNM);
					if (NullCheckUtil.isEmpty(memberInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ȸ���� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
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
				} else if (searchno == 3) {
					System.out.print("ȸ�� ��ȭ��ȣ >> ");
					String memTELstr = ScanUtil.nextLine();
					List<Object> searchTEL = new ArrayList<>();
					searchTEL.add(memTELstr);
					List<Map<String, Object>> memberInfo = adminReadDAO.memSearchFromTel(searchTEL);
					if (NullCheckUtil.isEmpty(memberInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ȸ���� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								" ���̵�              �̸�       ��й�ȣ            �ּ�                                        �ܾ�     ȸ������    ��ȭ��ȣ");
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
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
				}
			}
		}

		return View.ADMIN_MEM_MANAGEMENT;
	}

	public int storeRead() {
		List<Map<String, Object>> storeALL = adminReadDAO.storeALL();

		if (NullCheckUtil.isEmpty(storeALL)) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("��ϵ� ���԰� �����ϴ�!");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
		} else {
			int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
			int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
			int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
			int maxMinLen = gapUtil.gapFullCnt(storeALL, "MINORDER");
			int maxCloseLen = gapUtil.gapFullCnt(storeALL, "CLOSEYN");
			int maxDeliLen = gapUtil.gapFullCnt(storeALL, "DELIYN");
			int maxPackLen = gapUtil.gapFullCnt(storeALL, "PACKYN");
			int maxCateLen = gapUtil.gapFullCnt(storeALL, "CATENM");

			// ����¡ ���
			int pageNumber = 1; // ���� ������ ��ȣ
			int pageSize = 5; // �������� ǥ���� ������ ��

			int startIndex = (pageNumber - 1) * pageSize; // ���� �ε���
			int endIndex = Math.min(startIndex + pageSize, storeALL.size()); // �� �ε���

			int pageRowCnt = storeALL.size(); // �ο� ����
			int totalPageSize = pageRowCnt / pageSize; // �� ������ ����

			if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
				totalPageSize++;
			}

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" 1. ��ü��� ����");
			System.out.println(" 2. ������ ��� ����");
			System.out.println(" 3. �κ� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // ��ü �˻�
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� ���� �� " + pageRowCnt + " ��");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(
						"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				for (Map<String, Object> item : storeALL) {
					int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
					int storeNmLen = gapUtil.gapCnt("STONM");
					int storeAddLen = gapUtil.gapCnt("STOADD");
					int storeMinLen = gapUtil.gapCnt("MINORDER");
					int storeCloseLen = gapUtil.gapCnt("CLOSEYN");
					int storeDeliLen = gapUtil.gapCnt("DELIYN");
					int storePackLen = gapUtil.gapCnt("PACKYN");
					int storeCateLen = gapUtil.gapCnt("CATENM");

					StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")), maxStoCodeLen,
							storeStoCodeLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen, storeNmLen);
					StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
							storeAddLen);
					StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(item.get("MINORDER")), maxMinLen,
							storeMinLen);
					StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(item.get("CLOSEYN")), maxCloseLen,
							storeCloseLen);
					StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(item.get("DELIYN")), maxDeliLen,
							storeDeliLen);
					StringBuilder memPACK = gapUtil.gapFullSpace(String.valueOf(item.get("PACKYN")), maxPackLen,
							storePackLen);
					StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATENM")), maxCateLen,
							storeCateLen);
					System.out.printf("%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
							+ "s%-" + storeCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-" + maxCateLen
							+ "s\n", memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
				}
				EnterUtil.enterNext(1);
			} else if (choice == 2) { // ����¡ �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �������� �� ���� �����͸� ���ðڽ��ϱ�?");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, storeALL.size());
				totalPageSize = pageRowCnt / pageSize; // �� ������ ����
				if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
					totalPageSize++;
				}
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� ���� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(
						"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				for (int i = startIndex; i < endIndex; i++) {
					int storeStoCodeLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("STOCODE")));
					int storeNmLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("STONM")));
					int storeAddLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("STOADD")));
					int storeMinLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("MINORDER")));
					int storeCloseLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("CLOSEYN")));
					int storeDeliLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("DELIYN")));
					int storePackLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("PACKYN")));
					int storeCateLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("CATENM")));

					StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("STOCODE")),
							maxStoCodeLen, storeStoCodeLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("STONM")), maxNmLen,
							storeNmLen);
					StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("STOADD")),
							maxAddLen, storeAddLen);
					StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("MINORDER")),
							maxMinLen, storeMinLen);
					StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("CLOSEYN")),
							maxCloseLen, storeCloseLen);
					StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("DELIYN")),
							maxDeliLen, storeDeliLen);
					StringBuilder memPACK = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("PACKYN")),
							maxPackLen, storePackLen);
					StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("CATENM")),
							maxCateLen, storeCateLen);
					System.out.printf("%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
							+ "s%-" + maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-" + maxCateLen
							+ "s\n", memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
				}
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
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
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� ���� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println("�ش� �������� ������ �����ϴ�.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, storeALL.size());
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� ���� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							for (int i = startIndex; i < endIndex; i++) {
								int storeStoCodeLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("STOCODE")));
								int storeNmLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("STONM")));
								int storeAddLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("STOADD")));
								int storeMinLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("MINORDER")));
								int storeCloseLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("CLOSEYN")));
								int storeDeliLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("DELIYN")));
								int storePackLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("PACKYN")));
								int storeCateLen = gapUtil.gapCnt(String.valueOf(storeALL.get(i).get("CATENM")));

								StringBuilder memSTOCODE = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("STOCODE")), maxStoCodeLen, storeStoCodeLen);
								StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(storeALL.get(i).get("STONM")),
										maxNmLen, storeNmLen);
								StringBuilder memADD = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("STOADD")), maxAddLen, storeAddLen);
								StringBuilder memMIN = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("MINORDER")), maxMinLen, storeMinLen);
								StringBuilder memCLOSE = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("CLOSEYN")), maxCloseLen, storeCloseLen);
								StringBuilder memDELI = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("DELIYN")), maxDeliLen, storeDeliLen);
								StringBuilder memPACK = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("PACKYN")), maxPackLen, storePackLen);
								StringBuilder memCATE = gapUtil.gapFullSpace(
										String.valueOf(storeALL.get(i).get("CATENM")), maxCateLen, storeCateLen);
								System.out.printf(
										"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
												+ "s%-" + maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-"
												+ maxCateLen + "s\n",
										memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
							}
						}
					} else if (pageListSelect == 0) {
						return View.ADMIN_STORE_MANAGEMENT;
					}
				}
			} else if (choice == 3) { // �κ� �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" 1. �����ڵ�� �˻�");
				System.out.println(" 2. ���Ը����� �˻�");
				System.out.println(" 3. �ּҷ� �˻�");
				System.out.println(" 4. ���� ������ ���� �˻�");
				System.out.println(" 5. ��� ������ ���� �˻�");
				System.out.println(" 6. ���� ����� ���� �˻�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("�����ڵ� >> ");
					String storeCodestr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(storeCodestr);
					Map<String, Object> storeInfo = adminReadDAO.storeSearchFromStoCode(searchStoCode);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���԰� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
						int storeNmLen = gapUtil.gapCnt("STONM");
						int storeAddLen = gapUtil.gapCnt("STOADD");
						int storeMinLen = gapUtil.gapCnt("MINORDER");
						int storeCloseLen = gapUtil.gapCnt("CLOSEYN");
						int storeDeliLen = gapUtil.gapCnt("DELIYN");
						int storePackLen = gapUtil.gapCnt("PACKYN");
						int storeCateLen = gapUtil.gapCnt("CATENM");

						StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("STOCODE")),
								maxStoCodeLen, storeStoCodeLen);
						StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("STONM")), maxNmLen,
								storeNmLen);
						StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("STOADD")), maxAddLen,
								storeAddLen);
						StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("MINORDER")),
								maxMinLen, storeMinLen);
						StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("CLOSEYN")),
								maxCloseLen, storeCloseLen);
						StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("DELIYN")),
								maxDeliLen, storeDeliLen);
						StringBuilder memPACK = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("PACKYN")),
								maxPackLen, storePackLen);
						StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("CATENM")),
								maxCateLen, storeCateLen);
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.printf(
								"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen + "s%-"
										+ maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-" + maxCateLen
										+ "s\n",
								memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("���Ը� >> ");
					String storeNMstr = ScanUtil.nextLine();
					List<Object> searchStoName = new ArrayList<>();
					searchStoName.add(storeNMstr);
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoName);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���԰� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
							int storeNmLen = gapUtil.gapCnt("STONM");
							int storeAddLen = gapUtil.gapCnt("STOADD");
							int storeMinLen = gapUtil.gapCnt("MINORDER");
							int storeCloseLen = gapUtil.gapCnt("CLOSEYN");
							int storeDeliLen = gapUtil.gapCnt("DELIYN");
							int storePackLen = gapUtil.gapCnt("PACKYN");
							int storeCateLen = gapUtil.gapCnt("CATENM");

							StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")),
									maxStoCodeLen, storeStoCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen,
									storeNmLen);
							StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
									storeAddLen);
							StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(item.get("MINORDER")), maxMinLen,
									storeMinLen);
							StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(item.get("CLOSEYN")),
									maxCloseLen, storeCloseLen);
							StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(item.get("DELIYN")), maxDeliLen,
									storeDeliLen);
							StringBuilder memPACK = gapUtil.gapFullSpace(String.valueOf(item.get("PACKYN")), maxPackLen,
									storePackLen);
							StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATENM")), maxCateLen,
									storeCateLen);
							System.out.printf(
									"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
											+ "s%-" + maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-"
											+ maxCateLen + "s\n",
									memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 3) {
					System.out.print("���� �ּ� >> ");
					String storeADDstr = ScanUtil.nextLine();
					List<Object> searchStoAdd = new ArrayList<>();
					searchStoAdd.add(storeADDstr);
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoAdd(searchStoAdd);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���԰� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"��ü�ڵ�    ��ü��                ��ü�ּ�                                                 �ּ��ֹ��ݾ�     �����������     ��޿���     ���忩��     ī�װ���");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
							int storeNmLen = gapUtil.gapCnt("STONM");
							int storeAddLen = gapUtil.gapCnt("STOADD");
							int storeMinLen = gapUtil.gapCnt("MINORDER");
							int storeCloseLen = gapUtil.gapCnt("CLOSEYN");
							int storeDeliLen = gapUtil.gapCnt("DELIYN");
							int storePackLen = gapUtil.gapCnt("PACKYN");
							int storeCateLen = gapUtil.gapCnt("CATENM");

							StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")),
									maxStoCodeLen, storeStoCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen,
									storeNmLen);
							StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
									storeAddLen);
							StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(item.get("MINORDER")), maxMinLen,
									storeMinLen);
							StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(item.get("CLOSEYN")),
									maxCloseLen, storeCloseLen);
							StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(item.get("DELIYN")), maxDeliLen,
									storeDeliLen);
							StringBuilder memPACK = gapUtil.gapFullSpace(String.valueOf(item.get("PACKYN")), maxPackLen,
									storePackLen);
							StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATENM")), maxCateLen,
									storeCateLen);
							System.out.printf(
									"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
											+ "s%-" + maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-"
											+ maxCateLen + "s\n",
									memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 4) {
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromPackYN();
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���԰� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"��ü�ڵ�    ��ü��              ��ü�ּ�                                          �ּ��ֹ��ݾ�     ���忩��    ī�װ���");
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
							int storeNmLen = gapUtil.gapCnt("STONM");
							int storeAddLen = gapUtil.gapCnt("STOADD");
							int storeMinLen = gapUtil.gapCnt("MINORDER");
							int storePackLen = gapUtil.gapCnt("PACKYN");
							int storeCateLen = gapUtil.gapCnt("CATENM");

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
							StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATENM")), maxCateLen,
									storeCateLen);
							System.out.printf(
									"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
											+ "s%-" + maxPackLen + "s%-" + maxCateLen + "s\n",
									memSTOCODE, memNM, memADD, memMIN, memPACK, memCATE);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 5) {
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromDeliYN();
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���԰� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"��ü�ڵ�    ��ü��              ��ü�ּ�                                          �ּ��ֹ��ݾ�     ��޿���    ī�װ���");
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
							int storeNmLen = gapUtil.gapCnt("STONM");
							int storeAddLen = gapUtil.gapCnt("STOADD");
							int storeMinLen = gapUtil.gapCnt("MINORDER");
							int storeDeliLen = gapUtil.gapCnt("DELIYN");
							int storeCateLen = gapUtil.gapCnt("CATENM");

							StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")),
									maxStoCodeLen, storeStoCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen,
									storeNmLen);
							StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
									storeAddLen);
							StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(item.get("MINORDER")), maxMinLen,
									storeMinLen);
							StringBuilder memDELI = gapUtil.gapFullSpace(String.valueOf(item.get("DELIYN")), maxDeliLen,
									storeDeliLen);
							StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATENM")), maxCateLen,
									storeCateLen);
							System.out.printf(
									"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
											+ "s%-" + maxDeliLen + "s%-" + maxCateLen + "s\n",
									memSTOCODE, memNM, memADD, memMIN, memDELI, memCATE);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 6) {
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromCloseYN();
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���԰� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"��ü�ڵ�    ��ü��              ��ü�ּ�                                          �ּ��ֹ��ݾ�     �������    ī�װ���");
						System.out
								.println(" ����������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int storeStoCodeLen = gapUtil.gapCnt("STOCODE");
							int storeNmLen = gapUtil.gapCnt("STONM");
							int storeAddLen = gapUtil.gapCnt("STOADD");
							int storeMinLen = gapUtil.gapCnt("MINORDER");
							int storeCloseLen = gapUtil.gapCnt("CLOSEYN");
							int storeCateLen = gapUtil.gapCnt("CATENM");

							StringBuilder memSTOCODE = gapUtil.gapFullSpace(String.valueOf(item.get("STOCODE")),
									maxStoCodeLen, storeStoCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxNmLen,
									storeNmLen);
							StringBuilder memADD = gapUtil.gapFullSpace(String.valueOf(item.get("STOADD")), maxAddLen,
									storeAddLen);
							StringBuilder memMIN = gapUtil.gapFullSpace(String.valueOf(item.get("MINORDER")), maxMinLen,
									storeMinLen);
							StringBuilder memCLOSE = gapUtil.gapFullSpace(String.valueOf(item.get("CLOSEYN")),
									maxCloseLen, storeCloseLen);
							StringBuilder memCATE = gapUtil.gapFullSpace(String.valueOf(item.get("CATENM")), maxCateLen,
									storeCateLen);
							System.out.printf(
									"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen
											+ "s%-" + maxCloseLen + "s%-" + maxCateLen + "s\n",
									memSTOCODE, memNM, memADD, memMIN, memCLOSE, memCATE);
						}
					}
					EnterUtil.enterNext(2);
				}
			}
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuRead() {
		List<Map<String, Object>> menuALL = adminReadDAO.menuALL();

		if (NullCheckUtil.isEmpty(menuALL)) {
			System.out.println("��ϵ� �޴��� �����ϴ�!");
		} else {
			int maxMenuCodeLen = gapUtil.gapFullCnt(menuALL, "MENUCODE");
			int maxNmLen = gapUtil.gapFullCnt(menuALL, "MENUNM");
			int maxPriceLen = gapUtil.gapFullCnt(menuALL, "MENUPRICE");
			int maxQtyLen = gapUtil.gapFullCnt(menuALL, "REMAINQTY");
			int maxStoCodeLen = gapUtil.gapFullCnt(menuALL, "STONM");

			// ����¡ ���
			int pageNumber = 1; // ���� ������ ��ȣ
			int pageSize = 5; // �������� ǥ���� ������ ��

			int startIndex = (pageNumber - 1) * pageSize; // ���� �ε���
			int endIndex = Math.min(startIndex + pageSize, menuALL.size()); // �� �ε���

			int pageRowCnt = menuALL.size(); // �ο� ����
			int totalPageSize = pageRowCnt / pageSize; // �� ������ ����

			if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
				totalPageSize++;
			}

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" 1. ��ü��� ����");
			System.out.println(" 2. ������ ��� ����");
			System.out.println(" 3. �κ� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // ��ü �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �޴� �� " + pageRowCnt + " ��");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				for (Map<String, Object> item : menuALL) {
					int memMenuCodeLen = gapUtil.gapCnt(item, "MENUCODE");
					int memNMLen = gapUtil.gapCnt(item, "MENUNM");
					int memPRICELen = gapUtil.gapCnt(item, "MENUPRICE");
					int memQTYLen = gapUtil.gapCnt(item, "REMAINQTY");
					int memStoCodeLen = gapUtil.gapCnt(item, "STONM");

					StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(item.get("MENUCODE")),
							maxMenuCodeLen, memMenuCodeLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")), maxNmLen, memNMLen);
					StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(item.get("MENUPRICE")), maxPriceLen,
							memPRICELen);
					StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(item.get("REMAINQTY")), maxQtyLen,
							memQTYLen);
					StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxStoCodeLen,
							memStoCodeLen);
					System.out.printf("%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
							+ "s%" + maxStoCodeLen + "s\n", memMenuCode, memNM, memPRICE, memQTY, memStoCode);
				}
				EnterUtil.enterNext(1);
			} else if (choice == 2) { // ����¡ �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �������� �� ���� �����͸� ���ðڽ��ϱ�?");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, menuALL.size());
				totalPageSize = pageRowCnt / pageSize; // �� ������ ����
				if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
					totalPageSize++;
				}
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �޴� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println("�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				for (int i = startIndex; i < endIndex; i++) {
					int memMenuCodeLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("MENUCODE")));
					int memNMLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("MENUNM")));
					int memPRICELen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("MENUPRICE")));
					int memQTYLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("REMAINQTY")));
					int memStoCodeLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("STONM")));

					StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(menuALL.get(i).get("MENUCODE")),
							maxMenuCodeLen, memMenuCodeLen);
					StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(menuALL.get(i).get("MENUNM")), maxNmLen,
							memNMLen);
					StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(menuALL.get(i).get("MENUPRICE")),
							maxPriceLen, memPRICELen);
					StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(menuALL.get(i).get("REMAINQTY")),
							maxQtyLen, memQTYLen);
					StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(menuALL.get(i).get("STONM")),
							maxStoCodeLen, memStoCodeLen);
					System.out.printf("%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
							+ "s%" + maxStoCodeLen + "s\n", memMenuCode, memNM, memPRICE, memQTY, memStoCode);
				}
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
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
							System.out.println(" ������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� �޴� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(" ������������������������������������������������������������������������������������������������������");
							System.out.println(
									"�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
							System.out.println(" ������������������������������������������������������������������������������������������������������");
							System.out.println("�ش� �������� ������ �����ϴ�.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, menuALL.size());
							System.out.println(" ������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� �޴� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(" ������������������������������������������������������������������������������������������������������");
							System.out.println(
									"�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
							System.out.println(" ������������������������������������������������������������������������������������������������������");
							for (int i = startIndex; i < endIndex; i++) {
								int memMenuCodeLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("MENUCODE")));
								int memNMLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("MENUNM")));
								int memPRICELen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("MENUPRICE")));
								int memQTYLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("REMAINQTY")));
								int memStoCodeLen = gapUtil.gapCnt(String.valueOf(menuALL.get(i).get("STONM")));

								StringBuilder memMenuCode = gapUtil.gapFullSpace(
										String.valueOf(menuALL.get(i).get("MENUCODE")), maxMenuCodeLen, memMenuCodeLen);
								StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(menuALL.get(i).get("MENUNM")),
										maxNmLen, memNMLen);
								StringBuilder memPRICE = gapUtil.gapFullSpace(
										String.valueOf(menuALL.get(i).get("MENUPRICE")), maxPriceLen, memPRICELen);
								StringBuilder memQTY = gapUtil.gapFullSpace(
										String.valueOf(menuALL.get(i).get("REMAINQTY")), maxQtyLen, memQTYLen);
								StringBuilder memStoCode = gapUtil.gapFullSpace(
										String.valueOf(menuALL.get(i).get("STONM")), maxStoCodeLen, memStoCodeLen);
								System.out.printf(
										"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-"
												+ maxQtyLen + "s%" + maxStoCodeLen + "s\n",
										memMenuCode, memNM, memPRICE, memQTY, memStoCode);
							}
						}
					} else if (pageListSelect == 0) {
						return View.ADMIN_MENU_MANAGEMENT;
					}
				}
			} else if (choice == 3) { // �κ� �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" 1. �޴��ڵ�� �˻�");
				System.out.println(" 2. �޴������� �˻�");
				System.out.println(" 3. �޴��������� �˻�");
				System.out.println(" 4. ���Ը����� �˻�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("�޴��ڵ� >> ");
					String storeCodeStr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(storeCodeStr);
					Map<String, Object> storeInfo = adminReadDAO.menuSearchFromMenuCode(searchStoCode);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						int memMenuCodeLen = gapUtil.gapCnt(storeInfo, "MENUCODE");
						int memNMLen = gapUtil.gapCnt(storeInfo, "MENUNM");
						int memPRICELen = gapUtil.gapCnt(storeInfo, "MENUPRICE");
						int memQTYLen = gapUtil.gapCnt(storeInfo, "REMAINQTY");
						int memStoCodeLen = gapUtil.gapCnt(storeInfo, "STONM");

						StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("MENUCODE")),
								maxMenuCodeLen, memMenuCodeLen);
						StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("MENUNM")), maxNmLen,
								memNMLen);
						StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("MENUPRICE")),
								maxPriceLen, memPRICELen);
						StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("REMAINQTY")),
								maxQtyLen, memQTYLen);
						StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(storeInfo.get("STONM")),
								maxStoCodeLen, memStoCodeLen);
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.printf(
								"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
										+ "s%" + maxStoCodeLen + "s\n",
								memMenuCode, memNM, memPRICE, memQTY, memStoCode);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("�޴��� >> ");
					String storeMenuNameStr = ScanUtil.nextLine();
					List<Object> searchMenuName = new ArrayList<>();
					searchMenuName.add(storeMenuNameStr);
					List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromMenuName(searchMenuName);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int memMenuCodeLen = gapUtil.gapCnt(item, "MENUCODE");
							int memNMLen = gapUtil.gapCnt(item, "MENUNM");
							int memPRICELen = gapUtil.gapCnt(item, "MENUPRICE");
							int memQTYLen = gapUtil.gapCnt(item, "REMAINQTY");
							int memStoCodeLen = gapUtil.gapCnt(item, "STONM");

							StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(item.get("MENUCODE")),
									maxMenuCodeLen, memMenuCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")), maxNmLen,
									memNMLen);
							StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(item.get("MENUPRICE")),
									maxPriceLen, memPRICELen);
							StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(item.get("REMAINQTY")),
									maxQtyLen, memQTYLen);
							StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")),
									maxStoCodeLen, memStoCodeLen);
							System.out.printf(
									"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
											+ "s%" + maxStoCodeLen + "s\n",
									memMenuCode, memNM, memPRICE, memQTY, memStoCode);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 3) {
					System.out.print("�� �̻��� �˻��ұ��? (0 ~ 9999999999) >> ");
					long storeMenuPriceInt = ScanUtil.nextLong();
					List<Object> searchMenuPrice = new ArrayList<>();
					searchMenuPrice.add(storeMenuPriceInt);
					List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromMenuPrice(searchMenuPrice);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int memMenuCodeLen = gapUtil.gapCnt(item, "MENUCODE");
							int memNMLen = gapUtil.gapCnt(item, "MENUNM");
							int memPRICELen = gapUtil.gapCnt(item, "MENUPRICE");
							int memQTYLen = gapUtil.gapCnt(item, "REMAINQTY");
							int memStoCodeLen = gapUtil.gapCnt(item, "STONM");

							StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(item.get("MENUCODE")),
									maxMenuCodeLen, memMenuCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")), maxNmLen,
									memNMLen);
							StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(item.get("MENUPRICE")),
									maxPriceLen, memPRICELen);
							StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(item.get("REMAINQTY")),
									maxQtyLen, memQTYLen);
							StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")),
									maxStoCodeLen, memStoCodeLen);
							System.out.printf(
									"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
											+ "s%" + maxStoCodeLen + "s\n",
									memMenuCode, memNM, memPRICE, memQTY, memStoCode);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 4) {
					System.out.print("��ü�� >> ");
					String storeStoCodeStr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(storeStoCodeStr);
					List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromStoCode(searchStoCode);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�޴��ڵ�        �޴���                                         ����        ���      ��ü��");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : storeInfo) {
							int memMenuCodeLen = gapUtil.gapCnt(item, "MENUCODE");
							int memNMLen = gapUtil.gapCnt(item, "MENUNM");
							int memPRICELen = gapUtil.gapCnt(item, "MENUPRICE");
							int memQTYLen = gapUtil.gapCnt(item, "REMAINQTY");
							int memStoCodeLen = gapUtil.gapCnt(item, "STONM");

							StringBuilder memMenuCode = gapUtil.gapFullSpace(String.valueOf(item.get("MENUCODE")),
									maxMenuCodeLen, memMenuCodeLen);
							StringBuilder memNM = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")), maxNmLen,
									memNMLen);
							StringBuilder memPRICE = gapUtil.gapFullSpace(String.valueOf(item.get("MENUPRICE")),
									maxPriceLen, memPRICELen);
							StringBuilder memQTY = gapUtil.gapFullSpace(String.valueOf(item.get("REMAINQTY")),
									maxQtyLen, memQTYLen);
							StringBuilder memStoCode = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")),
									maxStoCodeLen, memStoCodeLen);
							System.out.printf(
									"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
											+ "s%" + maxStoCodeLen + "s\n",
									memMenuCode, memNM, memPRICE, memQTY, memStoCode);
						}
					}
					EnterUtil.enterNext(2);
				}
			}
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int orderhistoRead() {
		List<Map<String, Object>> orderhistoALL = adminReadDAO.orderhistoALL();

		if (NullCheckUtil.isEmpty(orderhistoALL)) {
			System.out.println("��ϵ� �ֹ������� �����ϴ�!");
		} else {
			int maxOrderNoLen = gapUtil.gapFullCnt(orderhistoALL, "ORDERNO");
			int maxOrderCodeLen = gapUtil.gapFullCnt(orderhistoALL, "ORDERCODE");
			int maxMenuNmLen = gapUtil.gapFullCnt(orderhistoALL, "MENUNM");
			int maxOrderQtyLen = gapUtil.gapFullCnt(orderhistoALL, "ORDERQTY");
			int maxOrderEtaLen = gapUtil.gapFullCnt(orderhistoALL, "ORDERETA");
			int maxTotalPriceLen = gapUtil.gapFullCnt(orderhistoALL, "TOTALPRICE");
			int maxOrderDateLen = gapUtil.gapFullCnt(orderhistoALL, "ORDERDATE");
			int maxMemNmLen = gapUtil.gapFullCnt(orderhistoALL, "MEMNM");
			int maxMemAddLen = gapUtil.gapFullCnt(orderhistoALL, "MEMADD");
			int maxSelYnLen = gapUtil.gapFullCnt(orderhistoALL, "SELYN");

			// ����¡ ���
			int pageNumber = 1; // ���� ������ ��ȣ
			int pageSize = 5; // �������� ǥ���� ������ ��

			int startIndex = (pageNumber - 1) * pageSize; // ���� �ε���
			int endIndex = Math.min(startIndex + pageSize, orderhistoALL.size()); // �� �ε���

			int pageRowCnt = orderhistoALL.size(); // �ο� ����
			int totalPageSize = pageRowCnt / pageSize; // �� ������ ����

			if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
				totalPageSize++;
			}

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" 1. ��ü��� ����");
			System.out.println(" 2. ������ ��� ����");
			System.out.println(" 3. �κ� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // ��ü �˻�
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �ֹ� �� " + pageRowCnt + " ��");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(
						"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				for (Map<String, Object> item : orderhistoALL) {
					int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
					int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
					int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
					int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
					int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
					int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
					int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
					int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
					int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
					int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

					StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")), maxOrderNoLen,
							memOrderNoLen);
					StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
							maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")), maxMenuNmLen,
							memMenuNmLen);
					StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
							maxOrderQtyLen, memOrderQtyLen);
					StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
							maxOrderEtaLen, memOrderEtaLen);
					StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
							maxTotalPriceLen, memTotalPriceLen);
					StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
							maxOrderDateLen, memOrderDateLen);
					StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")), maxMemNmLen,
							memMemNmLen);
					StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")), maxMemAddLen,
							memMemAddLen);
					StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")), maxSelYnLen,
							memSelYnLen);
					System.out.printf("%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
							+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%" + maxOrderDateLen
							+ "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%" + maxSelYnLen + "s\n", memOrderNo,
							memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice, memOrderDate, memMemNm,
							memMemAdd, memSelYn);
				}
				EnterUtil.enterNext(1);
			} else if (choice == 2) { // ����¡ �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �������� �� ���� �����͸� ���ðڽ��ϱ�?");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, orderhistoALL.size());
				totalPageSize = pageRowCnt / pageSize; // �� ������ ����
				if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
					totalPageSize++;
				}
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �ֹ� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				System.out.println(
						"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
				System.out.println(
						" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
				for (int i = startIndex; i < endIndex; i++) {
					int memOrderNoLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERNO")));
					int memOrderCodeLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERCODE")));
					int memMenuNmLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("MENUNM")));
					int memOrderQtyLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERQTY")));
					int memOrderEtaLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERETA")));
					int memTotalPriceLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("TOTALPRICE")));
					int memOrderDateLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERDATE")));
					int memMemNmLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("MEMNM")));
					int memMemAddLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("MEMADD")));
					int memSelYnLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("SELYN")));

					StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(orderhistoALL.get(i).get("ORDERNO")),
							maxOrderNoLen, memOrderNoLen);
					StringBuilder memOrderCode = gapUtil.gapFullSpace(
							String.valueOf(orderhistoALL.get(i).get("ORDERCODE")), maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(orderhistoALL.get(i).get("MENUNM")),
							maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memOrderQty = gapUtil.gapFullSpace(
							String.valueOf(orderhistoALL.get(i).get("ORDERQTY")), maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memOrderEta = gapUtil.gapFullSpace(
							String.valueOf(orderhistoALL.get(i).get("ORDERETA")), maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memTotalPrice = gapUtil.gapFullSpace(
							String.valueOf(orderhistoALL.get(i).get("TOTALPRICE")), maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memOrderDate = gapUtil.gapFullSpace(
							String.valueOf(orderhistoALL.get(i).get("ORDERDATE")), maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(orderhistoALL.get(i).get("MEMNM")),
							maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(orderhistoALL.get(i).get("MEMADD")),
							maxOrderCodeLen, memOrderCodeLen);
					StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(orderhistoALL.get(i).get("SELYN")),
							maxOrderCodeLen, memOrderCodeLen);
					System.out.printf("%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
							+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%" + maxOrderDateLen
							+ "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%" + maxSelYnLen + "s\n", memOrderNo,
							memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice, memOrderDate, memMemNm,
							memMemAdd, memSelYn);
				}
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
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
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� �ֹ� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println("�ش� �������� ������ �����ϴ�.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, orderhistoALL.size());
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									" �� �ֹ� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							System.out.println(
									"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
							System.out.println(
									" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
							for (int i = startIndex; i < endIndex; i++) {
								int memOrderNoLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERNO")));
								int memOrderCodeLen = gapUtil
										.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERCODE")));
								int memMenuNmLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("MENUNM")));
								int memOrderQtyLen = gapUtil
										.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERQTY")));
								int memOrderEtaLen = gapUtil
										.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERETA")));
								int memTotalPriceLen = gapUtil
										.gapCnt(String.valueOf(orderhistoALL.get(i).get("TOTALPRICE")));
								int memOrderDateLen = gapUtil
										.gapCnt(String.valueOf(orderhistoALL.get(i).get("ORDERDATE")));
								int memMemNmLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("MEMNM")));
								int memMemAddLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("MEMADD")));
								int memSelYnLen = gapUtil.gapCnt(String.valueOf(orderhistoALL.get(i).get("SELYN")));

								StringBuilder memOrderNo = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("ORDERNO")), maxOrderNoLen,
										memOrderNoLen);
								StringBuilder memOrderCode = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("ORDERCODE")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memMenuNm = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("MENUNM")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memOrderQty = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("ORDERQTY")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memOrderEta = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("ORDERETA")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memTotalPrice = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("TOTALPRICE")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memOrderDate = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("ORDERDATE")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memMemNm = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("MEMNM")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memMemAdd = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("MEMADD")), maxOrderCodeLen,
										memOrderCodeLen);
								StringBuilder memSelYn = gapUtil.gapFullSpace(
										String.valueOf(orderhistoALL.get(i).get("SELYN")), maxOrderCodeLen,
										memOrderCodeLen);
								System.out.printf(
										"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
												+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen
												+ "s%" + maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen
												+ "s%" + maxSelYnLen + "s\n",
										memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
										memOrderDate, memMemNm, memMemAdd, memSelYn);
							}
						}
					} else if (pageListSelect == 0) {
						return View.ADMIN_ORDERHISTORY;
					}
				}
			} else if (choice == 3) { // �κ� �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" 1. �ֹ������ڵ�� �˻�");
				System.out.println(" 2. ȸ�������� �˻�");
				System.out.println(" 3. �޴������� �˻�");
				System.out.println(" 4. �ֹ���¥�� �˻�");
				System.out.println(" 5. ��޿���ð����� �˻�");
				System.out.println(" 6. �������η� �˻�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("�ֹ��ڵ� >> ");
					String orderOrdCodeStr = ScanUtil.nextLine();
					List<Object> searchOrderCode = new ArrayList<>();
					searchOrderCode.add(orderOrdCodeStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromOrderCode(searchOrderCode);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : orderInfo) {
							int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
							int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
							int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
							int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
							int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
							int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
							int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
							int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
							int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
							int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

							StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")),
									maxOrderNoLen, memOrderNoLen);
							StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
									maxOrderCodeLen, memOrderCodeLen);
							StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")),
									maxMenuNmLen, memMenuNmLen);
							StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
									maxOrderQtyLen, memOrderQtyLen);
							StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
									maxOrderEtaLen, memOrderEtaLen);
							StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
									maxTotalPriceLen, memTotalPriceLen);
							StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
									maxOrderDateLen, memOrderDateLen);
							StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")),
									maxMemNmLen, memMemNmLen);
							StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")),
									maxMemAddLen, memMemAddLen);
							StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")),
									maxSelYnLen, memSelYnLen);
							System.out.printf(
									"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
											+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%"
											+ maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%"
											+ maxSelYnLen + "s\n",
									memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
									memOrderDate, memMemNm, memMemAdd, memSelYn);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("ȸ���� >> ");
					String orderMemNmStr = ScanUtil.nextLine();
					List<Object> searchMemNm = new ArrayList<>();
					searchMemNm.add(orderMemNmStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromMemNm(searchMemNm);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : orderInfo) {
							int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
							int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
							int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
							int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
							int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
							int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
							int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
							int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
							int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
							int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

							StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")),
									maxOrderNoLen, memOrderNoLen);
							StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
									maxOrderCodeLen, memOrderCodeLen);
							StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")),
									maxMenuNmLen, memMenuNmLen);
							StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
									maxOrderQtyLen, memOrderQtyLen);
							StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
									maxOrderEtaLen, memOrderEtaLen);
							StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
									maxTotalPriceLen, memTotalPriceLen);
							StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
									maxOrderDateLen, memOrderDateLen);
							StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")),
									maxMemNmLen, memMemNmLen);
							StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")),
									maxMemAddLen, memMemAddLen);
							StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")),
									maxSelYnLen, memSelYnLen);
							System.out.printf(
									"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
											+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%"
											+ maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%"
											+ maxSelYnLen + "s\n",
									memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
									memOrderDate, memMemNm, memMemAdd, memSelYn);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 3) {
					System.out.print("�޴��� >> ");
					String orderMenuNmStr = ScanUtil.nextLine();
					List<Object> searchMenuNm = new ArrayList<>();
					searchMenuNm.add(orderMenuNmStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromMenuNm(searchMenuNm);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : orderInfo) {
							int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
							int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
							int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
							int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
							int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
							int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
							int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
							int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
							int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
							int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

							StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")),
									maxOrderNoLen, memOrderNoLen);
							StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
									maxOrderCodeLen, memOrderCodeLen);
							StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")),
									maxMenuNmLen, memMenuNmLen);
							StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
									maxOrderQtyLen, memOrderQtyLen);
							StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
									maxOrderEtaLen, memOrderEtaLen);
							StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
									maxTotalPriceLen, memTotalPriceLen);
							StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
									maxOrderDateLen, memOrderDateLen);
							StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")),
									maxMemNmLen, memMemNmLen);
							StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")),
									maxMemAddLen, memMemAddLen);
							StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")),
									maxSelYnLen, memSelYnLen);
							System.out.printf(
									"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
											+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%"
											+ maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%"
											+ maxSelYnLen + "s\n",
									memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
									memOrderDate, memMemNm, memMemAdd, memSelYn);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 4) {
					System.out.print("�ֹ����� (YYYY) �Է� >> ");
					int orderYearStr = ScanUtil.nextInt();
					System.out.print("�ֹ��� (MM) �Է� >> ");
					int orderMonthStr = ScanUtil.nextInt();
					System.out.print("�ֹ��� (DD) �Է� >> ");
					int orderDayStr = ScanUtil.nextInt();
					String orderDateStr = orderYearStr + "-" + orderMonthStr + "-" + orderDayStr;
					List<Object> searchOrderDate = new ArrayList<>();
					searchOrderDate.add(orderDateStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromOrderDate(searchOrderDate);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : orderInfo) {
							int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
							int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
							int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
							int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
							int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
							int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
							int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
							int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
							int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
							int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

							StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")),
									maxOrderNoLen, memOrderNoLen);
							StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
									maxOrderCodeLen, memOrderCodeLen);
							StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")),
									maxMenuNmLen, memMenuNmLen);
							StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
									maxOrderQtyLen, memOrderQtyLen);
							StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
									maxOrderEtaLen, memOrderEtaLen);
							StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
									maxTotalPriceLen, memTotalPriceLen);
							StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
									maxOrderDateLen, memOrderDateLen);
							StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")),
									maxMemNmLen, memMemNmLen);
							StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")),
									maxMemAddLen, memMemAddLen);
							StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")),
									maxSelYnLen, memSelYnLen);
							System.out.printf(
									"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
											+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%"
											+ maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%"
											+ maxSelYnLen + "s\n",
									memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
									memOrderDate, memMemNm, memMemAdd, memSelYn);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 5) {
					System.out.print("�� �̻� ��޽ð��� �����ϼ���? (XX�� ������ �Է�) >> ");
					int orderOrderEtaInt = ScanUtil.nextInt();
					List<Object> searchOrderEta = new ArrayList<>();
					searchOrderEta.add(orderOrderEtaInt);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromOrderEta(searchOrderEta);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : orderInfo) {
							int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
							int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
							int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
							int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
							int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
							int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
							int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
							int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
							int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
							int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

							StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")),
									maxOrderNoLen, memOrderNoLen);
							StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
									maxOrderCodeLen, memOrderCodeLen);
							StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")),
									maxMenuNmLen, memMenuNmLen);
							StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
									maxOrderQtyLen, memOrderQtyLen);
							StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
									maxOrderEtaLen, memOrderEtaLen);
							StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
									maxTotalPriceLen, memTotalPriceLen);
							StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
									maxOrderDateLen, memOrderDateLen);
							StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")),
									maxMemNmLen, memMemNmLen);
							StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")),
									maxMemAddLen, memMemAddLen);
							StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")),
									maxSelYnLen, memSelYnLen);
							System.out.printf(
									"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
											+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%"
											+ maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%"
											+ maxSelYnLen + "s\n",
									memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
									memOrderDate, memMemNm, memMemAdd, memSelYn);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 6) {
					System.out.print("���� ���� (y / n) >> ");
					String orderSelYnStr = ScanUtil.nextLine();
					orderSelYnStr = orderSelYnStr.toUpperCase();
					List<Object> searchSelYn = new ArrayList<>();
					searchSelYn.add(orderSelYnStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromSelYn(searchSelYn);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� �޴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						System.out.println(
								"�ֹ���ȣ   �ֹ��ڵ�        �޴���                                   �ֹ�����   ��޿���ð�   �޴����ѱݾ�   �ֹ���¥   �ֹ��ڸ�   �ֹ����ּ�   ��������");
						System.out.println(
								" ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
						for (Map<String, Object> item : orderInfo) {
							int memOrderNoLen = gapUtil.gapCnt(item, "ORDERNO");
							int memOrderCodeLen = gapUtil.gapCnt(item, "ORDERCODE");
							int memMenuNmLen = gapUtil.gapCnt(item, "MENUNM");
							int memOrderQtyLen = gapUtil.gapCnt(item, "ORDERQTY");
							int memOrderEtaLen = gapUtil.gapCnt(item, "ORDERETA");
							int memTotalPriceLen = gapUtil.gapCnt(item, "TOTALPRICE");
							int memOrderDateLen = gapUtil.gapCnt(item, "ORDERDATE");
							int memMemNmLen = gapUtil.gapCnt(item, "MEMNM");
							int memMemAddLen = gapUtil.gapCnt(item, "MEMADD");
							int memSelYnLen = gapUtil.gapCnt(item, "SELYN");

							StringBuilder memOrderNo = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERNO")),
									maxOrderNoLen, memOrderNoLen);
							StringBuilder memOrderCode = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERCODE")),
									maxOrderCodeLen, memOrderCodeLen);
							StringBuilder memMenuNm = gapUtil.gapFullSpace(String.valueOf(item.get("MENUNM")),
									maxMenuNmLen, memMenuNmLen);
							StringBuilder memOrderQty = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERQTY")),
									maxOrderQtyLen, memOrderQtyLen);
							StringBuilder memOrderEta = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERETA")),
									maxOrderEtaLen, memOrderEtaLen);
							StringBuilder memTotalPrice = gapUtil.gapFullSpace(String.valueOf(item.get("TOTALPRICE")),
									maxTotalPriceLen, memTotalPriceLen);
							StringBuilder memOrderDate = gapUtil.gapFullSpace(String.valueOf(item.get("ORDERDATE")),
									maxOrderDateLen, memOrderDateLen);
							StringBuilder memMemNm = gapUtil.gapFullSpace(String.valueOf(item.get("MEMNM")),
									maxMemNmLen, memMemNmLen);
							StringBuilder memMemAdd = gapUtil.gapFullSpace(String.valueOf(item.get("MEMADD")),
									maxMemAddLen, memMemAddLen);
							StringBuilder memSelYn = gapUtil.gapFullSpace(String.valueOf(item.get("SELYN")),
									maxSelYnLen, memSelYnLen);
							System.out.printf(
									"%-" + maxOrderNoLen + "s%-" + maxOrderCodeLen + "s%-" + maxMenuNmLen + "s%-"
											+ maxOrderQtyLen + "s%" + maxOrderEtaLen + "s%" + maxTotalPriceLen + "s%"
											+ maxOrderDateLen + "s%" + maxMemNmLen + "s%" + maxMemAddLen + "s%"
											+ maxSelYnLen + "s\n",
									memOrderNo, memOrderCode, memMenuNm, memOrderQty, memOrderEta, memTotalPrice,
									memOrderDate, memMemNm, memMemAdd, memSelYn);
						}
					}
					EnterUtil.enterNext(2);
				}
			}
		}

		return View.ADMIN_ORDERHISTORY;
	}

	public int riderRead() {
		List<Map<String, Object>> riderALL = adminReadDAO.riderALL();

		if (NullCheckUtil.isEmpty(riderALL)) {
			System.out.println("��ϵ� ���̴��� �����ϴ�!");
		} else {
			int maxRideCodeLen = gapUtil.gapFullCnt(riderALL, "RIDCODE");
			int maxDeliCostLen = gapUtil.gapFullCnt(riderALL, "DELICOST");
			int maxAbsetyLen = gapUtil.gapFullCnt(riderALL, "ABSEYN");
			int maxStoNmLen = gapUtil.gapFullCnt(riderALL, "STONM");

			// ����¡ ���
			int pageNumber = 1; // ���� ������ ��ȣ
			int pageSize = 5; // �������� ǥ���� ������ ��

			int startIndex = (pageNumber - 1) * pageSize; // ���� �ε���
			int endIndex = Math.min(startIndex + pageSize, riderALL.size()); // �� �ε���

			int pageRowCnt = riderALL.size(); // �ο� ����
			int totalPageSize = pageRowCnt / pageSize; // �� ������ ����

			if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
				totalPageSize++;
			}

			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println(" 1. ��ü��� ����");
			System.out.println(" 2. ������ ��� ����");
			System.out.println(" 3. �κ� �˻�");
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // ��ü �˻�
				System.out.println(" ����������������������������������������������������������������");
				System.out.println(" �� ���̴� �� " + pageRowCnt + " ��");
				System.out.println(" ����������������������������������������������������������������");
				System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
				System.out.println(" ����������������������������������������������������������������");
				for (Map<String, Object> item : riderALL) {
					int memRideCodeLen = gapUtil.gapCnt(item, "RIDCODE");
					int memDeliCostLen = gapUtil.gapCnt(item, "DELICOST");
					int memAbsetyLen = gapUtil.gapCnt(item, "ABSEYN");
					int memStoNmLen = gapUtil.gapCnt(item, "STONM");

					StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(item.get("RIDCODE")),
							maxRideCodeLen, memRideCodeLen);
					StringBuilder memDeliCost = gapUtil.gapFullSpace(String.valueOf(item.get("DELICOST")),
							maxDeliCostLen, memDeliCostLen);
					StringBuilder memAbsety = gapUtil.gapFullSpace(String.valueOf(item.get("ABSEYN")), maxAbsetyLen,
							memAbsetyLen);
					StringBuilder memStoNm = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")), maxStoNmLen,
							memStoNmLen);
					System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen + "s%-"
							+ maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
				}
				EnterUtil.enterNext(1);
			} else if (choice == 2) { // ����¡ �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" �� �������� �� ���� �����͸� ���ðڽ��ϱ�?");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, riderALL.size());
				totalPageSize = pageRowCnt / pageSize; // �� ������ ����
				if (pageRowCnt % pageSize != 0) { // ���� ���������� �ο찡 ����
					totalPageSize++;
				}
				System.out.println(" ����������������������������������������������������������������");
				System.out.println(" �� ���̴� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
				System.out.println(" ����������������������������������������������������������������");
				System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
				System.out.println(" ����������������������������������������������������������������");
				for (int i = startIndex; i < endIndex; i++) {
					int memRideCodeLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("RIDCODE")));
					int memDeliCostLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("DELICOST")));
					int memAbsetyLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("ABSEYN")));
					int memStoNmLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("STONM")));

					StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(riderALL.get(i).get("RIDCODE")),
							maxRideCodeLen, memRideCodeLen);
					StringBuilder memDeliCost = gapUtil.gapFullSpace(String.valueOf(riderALL.get(i).get("DELICOST")),
							maxDeliCostLen, memDeliCostLen);
					StringBuilder memAbsety = gapUtil.gapFullSpace(String.valueOf(riderALL.get(i).get("ABSEYN")),
							maxAbsetyLen, memAbsetyLen);
					StringBuilder memStoNm = gapUtil.gapFullSpace(String.valueOf(riderALL.get(i).get("STONM")),
							maxStoNmLen, memStoNmLen);
					System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen + "s%-"
							+ maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
				}
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
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
							System.out.println(" ����������������������������������������������������������������");
							System.out.println(
									" �� ���̴� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(" ����������������������������������������������������������������");
							System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
							System.out.println(" ����������������������������������������������������������������");
							System.out.println("�ش� �������� ������ �����ϴ�.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, riderALL.size());
							System.out.println(" ����������������������������������������������������������������");
							System.out.println(
									" �� ���̴� �� " + pageRowCnt + " ��  : " + pageNumber + " / " + totalPageSize + " ������");
							System.out.println(" ����������������������������������������������������������������");
							System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
							System.out.println(" ����������������������������������������������������������������");
							for (int i = startIndex; i < endIndex; i++) {
								int memRideCodeLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("RIDCODE")));
								int memDeliCostLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("DELICOST")));
								int memAbsetyLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("ABSEYN")));
								int memStoNmLen = gapUtil.gapCnt(String.valueOf(riderALL.get(i).get("STONM")));

								StringBuilder memRideCode = gapUtil.gapFullSpace(
										String.valueOf(riderALL.get(i).get("RIDCODE")), maxRideCodeLen, memRideCodeLen);
								StringBuilder memDeliCost = gapUtil.gapFullSpace(
										String.valueOf(riderALL.get(i).get("DELICOST")), maxDeliCostLen,
										memDeliCostLen);
								StringBuilder memAbsety = gapUtil.gapFullSpace(
										String.valueOf(riderALL.get(i).get("ABSEYN")), maxAbsetyLen, memAbsetyLen);
								StringBuilder memStoNm = gapUtil.gapFullSpace(
										String.valueOf(riderALL.get(i).get("STONM")), maxStoNmLen, memStoNmLen);
								System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen
										+ "s%-" + maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
							}
						}
					} else if (pageListSelect == 0) {
						return View.ADMIN_RIDER_MANAGEMENT;
					}
				}
			} else if (choice == 3) { // �κ� �˻�
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.println(" 1. ���̴��ڵ�� �˻�");
				System.out.println(" 2. ���������� �˻�");
				System.out.println(" 3. ���Ը����� �˻�");
				System.out.println(" 4. ��޺�� �˻�");
				System.out.println(" ������������������������������������������������������������������������������������������������������");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("���̴��ڵ� >> ");
					String riderCodeStr = ScanUtil.nextLine();
					List<Object> searchRiderCode = new ArrayList<>();
					searchRiderCode.add(riderCodeStr);
					Map<String, Object> riderInfo = adminReadDAO.riderSearchFromRiderCode(searchRiderCode);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���̴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						int memRideCodeLen = gapUtil.gapCnt(riderInfo, "RIDCODE");
						int memDeliCostLen = gapUtil.gapCnt(riderInfo, "DELICOST");
						int memAbsetyLen = gapUtil.gapCnt(riderInfo, "ABSEYN");
						int memStoNmLen = gapUtil.gapCnt(riderInfo, "STONM");

						StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(riderInfo.get("RIDCODE")),
								maxRideCodeLen, memRideCodeLen);
						StringBuilder memDeliCost = gapUtil.gapFullSpace(String.valueOf(riderInfo.get("DELICOST")),
								maxDeliCostLen, memDeliCostLen);
						StringBuilder memAbsety = gapUtil.gapFullSpace(String.valueOf(riderInfo.get("ABSEYN")),
								maxAbsetyLen, memAbsetyLen);
						StringBuilder memStoNm = gapUtil.gapFullSpace(String.valueOf(riderInfo.get("STONM")),
								maxStoNmLen, memStoNmLen);
						System.out.println(" ����������������������������������������������������������������");
						System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
						System.out.println(" ����������������������������������������������������������������");
						System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen + "s%-"
								+ maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("���̴� ���� (y / n) >> ");
					String riderAbseYnStr = ScanUtil.nextLine();
					riderAbseYnStr = riderAbseYnStr.toUpperCase();
					List<Object> searchRiderAbseYn = new ArrayList<>();
					searchRiderAbseYn.add(riderAbseYnStr);
					List<Map<String, Object>> riderInfo = adminReadDAO.riderSearchFromAbseYn(searchRiderAbseYn);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���̴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(" ����������������������������������������������������������������");
						System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
						System.out.println(" ����������������������������������������������������������������");
						for (Map<String, Object> item : riderInfo) {
							int memRideCodeLen = gapUtil.gapCnt(item, "RIDCODE");
							int memDeliCostLen = gapUtil.gapCnt(item, "DELICOST");
							int memAbsetyLen = gapUtil.gapCnt(item, "ABSEYN");
							int memStoNmLen = gapUtil.gapCnt(item, "STONM");

							StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(item.get("RIDCODE")),
									maxRideCodeLen, memRideCodeLen);
							StringBuilder memDeliCost = gapUtil.gapFullSpace(String.valueOf(item.get("DELICOST")),
									maxDeliCostLen, memDeliCostLen);
							StringBuilder memAbsety = gapUtil.gapFullSpace(String.valueOf(item.get("ABSEYN")),
									maxAbsetyLen, memAbsetyLen);
							StringBuilder memStoNm = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")),
									maxStoNmLen, memStoNmLen);
							System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen
									+ "s%-" + maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 3) {
					System.out.print("���Ը� >> ");
					String riderStoCodeStr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(riderStoCodeStr);
					List<Map<String, Object>> riderInfo = adminReadDAO.riderSearchFromStoNm(searchStoCode);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���̴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(" ����������������������������������������������������������������");
						System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
						System.out.println(" ����������������������������������������������������������������");
						for (Map<String, Object> item : riderInfo) {
							int memRideCodeLen = gapUtil.gapCnt(item, "RIDCODE");
							int memDeliCostLen = gapUtil.gapCnt(item, "DELICOST");
							int memAbsetyLen = gapUtil.gapCnt(item, "ABSEYN");
							int memStoNmLen = gapUtil.gapCnt(item, "STONM");

							StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(item.get("RIDCODE")),
									maxRideCodeLen, memRideCodeLen);
							StringBuilder memDeliCost = gapUtil.gapFullSpace(String.valueOf(item.get("DELICOST")),
									maxDeliCostLen, memDeliCostLen);
							StringBuilder memAbsety = gapUtil.gapFullSpace(String.valueOf(item.get("ABSEYN")),
									maxAbsetyLen, memAbsetyLen);
							StringBuilder memStoNm = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")),
									maxStoNmLen, memStoNmLen);
							System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen
									+ "s%-" + maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
						}
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 4) {
					System.out.print("�� �̻� ��޺� �˻��ұ��? (0 ~ 9999999999) >> ");
					long riderDeliCostStr = ScanUtil.nextLong();
					List<Object> searchDeliCost = new ArrayList<>();
					searchDeliCost.add(riderDeliCostStr);
					List<Map<String, Object>> riderInfo = adminReadDAO.riderSearchFromDeliCost(searchDeliCost);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ������������������������������������������������������������������������������������������������������");
						System.out.println("��ϵ� ���̴��� �����ϴ�!");
						System.out.println(" ������������������������������������������������������������������������������������������������������");
					} else {
						System.out.println(" ����������������������������������������������������������������");
						System.out.println("���̴��ڵ�    ��޺�    ��ް���    �ҼӵȾ�ü��");
						System.out.println(" ����������������������������������������������������������������");
						for (Map<String, Object> item : riderInfo) {
							int memRideCodeLen = gapUtil.gapCnt(item, "RIDCODE");
							int memDeliCostLen = gapUtil.gapCnt(item, "DELICOST");
							int memAbsetyLen = gapUtil.gapCnt(item, "ABSEYN");
							int memStoNmLen = gapUtil.gapCnt(item, "STONM");

							StringBuilder memRideCode = gapUtil.gapFullSpace(String.valueOf(item.get("RIDCODE")),
									maxRideCodeLen, memRideCodeLen);
							StringBuilder memDeliCost = gapUtil.gapFullSpace(String.valueOf(item.get("DELICOST")),
									maxDeliCostLen, memDeliCostLen);
							StringBuilder memAbsety = gapUtil.gapFullSpace(String.valueOf(item.get("ABSEYN")),
									maxAbsetyLen, memAbsetyLen);
							StringBuilder memStoNm = gapUtil.gapFullSpace(String.valueOf(item.get("STONM")),
									maxStoNmLen, memStoNmLen);
							System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen
									+ "s%-" + maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
						}
					}
					EnterUtil.enterNext(2);
				}
			}
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
