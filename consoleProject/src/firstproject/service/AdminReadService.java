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
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println("등록된 회원이 없습니다!");
			System.out.println(" ───────────────────────────────────────────────────");
		} else {
			int maxIdLen = gapUtil.gapFullCnt(memALL, "MEMID");
			int maxNmLen = gapUtil.gapFullCnt(memALL, "MEMNM");
			int maxPwLen = gapUtil.gapFullCnt(memALL, "MEMPW");
			int maxAddLen = gapUtil.gapFullCnt(memALL, "MEMADD");
			int maxLvLen = gapUtil.gapFullCnt(memALL, "MEMLV");
			int maxBalLen = gapUtil.gapFullCnt(memALL, "BALANCE");
			int maxTelLen = gapUtil.gapFullCnt(memALL, "MEMTEL");

			// 페이징 기능
			int pageNumber = 1; // 현재 페이지 번호
			int pageSize = 5; // 페이지당 표시할 데이터 수

			int startIndex = (pageNumber - 1) * pageSize; // 시작 인덱스
			int endIndex = Math.min(startIndex + pageSize, memALL.size()); // 끝 인덱스

			int pageRowCnt = memALL.size(); // 로우 개수
			int totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수

			if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
				totalPageSize++;
			}

			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 1. 전체목록 보기");
			System.out.println(" 2. 페이지 목록 보기");
			System.out.println(" 3. 부분 검색");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // 전체 검색
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 회원 수 " + pageRowCnt + " 명");
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
				System.out.println(
						" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
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
			} else if (choice == 2) { // 페이징 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 한 페이지에 몇 개의 데이터를 보시겠습니까?");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, memALL.size());
				totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수
				if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
					totalPageSize++;
				}
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 회원 수 " + pageRowCnt + " 명  : " + pageNumber + " / " + totalPageSize + " 페이지");
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
				System.out.println(
						" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
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
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.println(" 1. 원하는 페이지 보기");
					System.out.println(" 0. 이전 화면");
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(" 몇 페이지를 보시겠습니까?");
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 회원 수 " + pageRowCnt + " 명  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println("해당 페이지는 정보가 없습니다.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, memALL.size());
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 회원 수 " + pageRowCnt + " 명  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
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
			} else if (choice == 3) { // 부분 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 1. 아이디로 검색");
				System.out.println(" 2. 이름으로 검색");
				System.out.println(" 3. 전화번호로 검색");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("회원 아이디 >> ");
					String memIDstr = ScanUtil.nextLine();
					List<Object> searchId = new ArrayList<>();
					searchId.add(memIDstr);
					Map<String, Object> memberInfo = adminReadDAO.memSearchFromId(searchId);
					if (NullCheckUtil.isEmpty(memberInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 회원이 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
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
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(
								" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.printf(
								"%-" + maxIdLen + "s%-" + maxNmLen + "s%-" + maxPwLen + "s%-" + maxAddLen + "s%-"
										+ maxBalLen + "s%-" + maxLvLen + "s%-" + maxTelLen + "s\n",
								memID, memNM, memPW, memADD, memBAL, memLV, memTEL);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("회원 이름 >> ");
					String memNMstr = ScanUtil.nextLine();
					List<Object> searchNM = new ArrayList<>();
					searchNM.add(memNMstr);
					List<Map<String, Object>> memberInfo = adminReadDAO.memSearchFromNm(searchNM);
					if (NullCheckUtil.isEmpty(memberInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 회원이 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(
								" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
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
					System.out.print("회원 전화번호 >> ");
					String memTELstr = ScanUtil.nextLine();
					List<Object> searchTEL = new ArrayList<>();
					searchTEL.add(memTELstr);
					List<Map<String, Object>> memberInfo = adminReadDAO.memSearchFromTel(searchTEL);
					if (NullCheckUtil.isEmpty(memberInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 회원이 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(
								" 아이디              이름       비밀번호            주소                                        잔액     회원레벨    전화번호");
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
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
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println("등록된 가게가 없습니다!");
			System.out.println(" ───────────────────────────────────────────────────");
		} else {
			int maxStoCodeLen = gapUtil.gapFullCnt(storeALL, "STOCODE");
			int maxNmLen = gapUtil.gapFullCnt(storeALL, "STONM");
			int maxAddLen = gapUtil.gapFullCnt(storeALL, "STOADD");
			int maxMinLen = gapUtil.gapFullCnt(storeALL, "MINORDER");
			int maxCloseLen = gapUtil.gapFullCnt(storeALL, "CLOSEYN");
			int maxDeliLen = gapUtil.gapFullCnt(storeALL, "DELIYN");
			int maxPackLen = gapUtil.gapFullCnt(storeALL, "PACKYN");
			int maxCateLen = gapUtil.gapFullCnt(storeALL, "CATENM");

			// 페이징 기능
			int pageNumber = 1; // 현재 페이지 번호
			int pageSize = 5; // 페이지당 표시할 데이터 수

			int startIndex = (pageNumber - 1) * pageSize; // 시작 인덱스
			int endIndex = Math.min(startIndex + pageSize, storeALL.size()); // 끝 인덱스

			int pageRowCnt = storeALL.size(); // 로우 개수
			int totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수

			if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
				totalPageSize++;
			}

			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 1. 전체목록 보기");
			System.out.println(" 2. 페이지 목록 보기");
			System.out.println(" 3. 부분 검색");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // 전체 검색
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 가게 수 " + pageRowCnt + " 개");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(
						"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
			} else if (choice == 2) { // 페이징 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 한 페이지에 몇 개의 데이터를 보시겠습니까?");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, storeALL.size());
				totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수
				if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
					totalPageSize++;
				}
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 가게 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(
						"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.println(" 1. 원하는 페이지 보기");
					System.out.println(" 0. 이전 화면");
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(" 몇 페이지를 보시겠습니까?");
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 가게 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println("해당 페이지는 정보가 없습니다.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, storeALL.size());
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 가게 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
			} else if (choice == 3) { // 부분 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 1. 가게코드로 검색");
				System.out.println(" 2. 가게명으로 검색");
				System.out.println(" 3. 주소로 검색");
				System.out.println(" 4. 포장 가능한 가게 검색");
				System.out.println(" 5. 배달 가능한 가게 검색");
				System.out.println(" 6. 현재 운영중인 가게 검색");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("가게코드 >> ");
					String storeCodestr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(storeCodestr);
					Map<String, Object> storeInfo = adminReadDAO.storeSearchFromStoCode(searchStoCode);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 가게가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
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
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.printf(
								"%-" + maxStoCodeLen + "s%-" + maxNmLen + "s%-" + maxAddLen + "s%-" + maxMinLen + "s%-"
										+ maxCloseLen + "s%-" + maxDeliLen + "s%-" + maxPackLen + "s%-" + maxCateLen
										+ "s\n",
								memSTOCODE, memNM, memADD, memMIN, memCLOSE, memDELI, memPACK, memCATE);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("가게명 >> ");
					String storeNMstr = ScanUtil.nextLine();
					List<Object> searchStoName = new ArrayList<>();
					searchStoName.add(storeNMstr);
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoName(searchStoName);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 가게가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.print("가게 주소 >> ");
					String storeADDstr = ScanUtil.nextLine();
					List<Object> searchStoAdd = new ArrayList<>();
					searchStoAdd.add(storeADDstr);
					List<Map<String, Object>> storeInfo = adminReadDAO.storeSearchFromStoAdd(searchStoAdd);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 가게가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"업체코드    업체명                업체주소                                                 최소주문금액     매장오픈유무     배달여부     포장여부     카테고리명");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 가게가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"업체코드    업체명              업체주소                                          최소주문금액     포장여부    카테고리명");
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
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
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 가게가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"업체코드    업체명              업체주소                                          최소주문금액     배달여부    카테고리명");
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
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
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 가게가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"업체코드    업체명              업체주소                                          최소주문금액     매장오픈    카테고리명");
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
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
			System.out.println("등록된 메뉴가 없습니다!");
		} else {
			int maxMenuCodeLen = gapUtil.gapFullCnt(menuALL, "MENUCODE");
			int maxNmLen = gapUtil.gapFullCnt(menuALL, "MENUNM");
			int maxPriceLen = gapUtil.gapFullCnt(menuALL, "MENUPRICE");
			int maxQtyLen = gapUtil.gapFullCnt(menuALL, "REMAINQTY");
			int maxStoCodeLen = gapUtil.gapFullCnt(menuALL, "STONM");

			// 페이징 기능
			int pageNumber = 1; // 현재 페이지 번호
			int pageSize = 5; // 페이지당 표시할 데이터 수

			int startIndex = (pageNumber - 1) * pageSize; // 시작 인덱스
			int endIndex = Math.min(startIndex + pageSize, menuALL.size()); // 끝 인덱스

			int pageRowCnt = menuALL.size(); // 로우 개수
			int totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수

			if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
				totalPageSize++;
			}

			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 1. 전체목록 보기");
			System.out.println(" 2. 페이지 목록 보기");
			System.out.println(" 3. 부분 검색");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // 전체 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 총 메뉴 수 " + pageRowCnt + " 개");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println("메뉴코드        메뉴명                                         가격        재고      업체명");
				System.out.println(" ───────────────────────────────────────────────────");
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
			} else if (choice == 2) { // 페이징 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 한 페이지에 몇 개의 데이터를 보시겠습니까?");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, menuALL.size());
				totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수
				if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
					totalPageSize++;
				}
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 총 메뉴 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println("메뉴코드        메뉴명                                         가격        재고      업체명");
				System.out.println(" ───────────────────────────────────────────────────");
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
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.println(" 1. 원하는 페이지 보기");
					System.out.println(" 0. 이전 화면");
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(" 몇 페이지를 보시겠습니까?");
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(" ───────────────────────────────────────────────────");
							System.out.println(
									" 총 메뉴 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(" ───────────────────────────────────────────────────");
							System.out.println(
									"메뉴코드        메뉴명                                         가격        재고      업체명");
							System.out.println(" ───────────────────────────────────────────────────");
							System.out.println("해당 페이지는 정보가 없습니다.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, menuALL.size());
							System.out.println(" ───────────────────────────────────────────────────");
							System.out.println(
									" 총 메뉴 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(" ───────────────────────────────────────────────────");
							System.out.println(
									"메뉴코드        메뉴명                                         가격        재고      업체명");
							System.out.println(" ───────────────────────────────────────────────────");
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
			} else if (choice == 3) { // 부분 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 1. 메뉴코드로 검색");
				System.out.println(" 2. 메뉴명으로 검색");
				System.out.println(" 3. 메뉴가격으로 검색");
				System.out.println(" 4. 가게명으로 검색");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("메뉴코드 >> ");
					String storeCodeStr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(storeCodeStr);
					Map<String, Object> storeInfo = adminReadDAO.menuSearchFromMenuCode(searchStoCode);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
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
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(
								"메뉴코드        메뉴명                                         가격        재고      업체명");
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.printf(
								"%-" + maxMenuCodeLen + "s%-" + maxNmLen + "s%-" + maxPriceLen + "s%-" + maxQtyLen
										+ "s%" + maxStoCodeLen + "s\n",
								memMenuCode, memNM, memPRICE, memQTY, memStoCode);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("메뉴명 >> ");
					String storeMenuNameStr = ScanUtil.nextLine();
					List<Object> searchMenuName = new ArrayList<>();
					searchMenuName.add(storeMenuNameStr);
					List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromMenuName(searchMenuName);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(
								"메뉴코드        메뉴명                                         가격        재고      업체명");
						System.out.println(" ───────────────────────────────────────────────────");
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
					System.out.print("얼마 이상을 검색할까요? (0 ~ 9999999999) >> ");
					long storeMenuPriceInt = ScanUtil.nextLong();
					List<Object> searchMenuPrice = new ArrayList<>();
					searchMenuPrice.add(storeMenuPriceInt);
					List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromMenuPrice(searchMenuPrice);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(
								"메뉴코드        메뉴명                                         가격        재고      업체명");
						System.out.println(" ───────────────────────────────────────────────────");
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
					System.out.print("업체명 >> ");
					String storeStoCodeStr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(storeStoCodeStr);
					List<Map<String, Object>> storeInfo = adminReadDAO.menuSearchFromStoCode(searchStoCode);
					if (NullCheckUtil.isEmpty(storeInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(
								"메뉴코드        메뉴명                                         가격        재고      업체명");
						System.out.println(" ───────────────────────────────────────────────────");
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
			System.out.println("등록된 주문내역이 없습니다!");
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

			// 페이징 기능
			int pageNumber = 1; // 현재 페이지 번호
			int pageSize = 5; // 페이지당 표시할 데이터 수

			int startIndex = (pageNumber - 1) * pageSize; // 시작 인덱스
			int endIndex = Math.min(startIndex + pageSize, orderhistoALL.size()); // 끝 인덱스

			int pageRowCnt = orderhistoALL.size(); // 로우 개수
			int totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수

			if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
				totalPageSize++;
			}

			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 1. 전체목록 보기");
			System.out.println(" 2. 페이지 목록 보기");
			System.out.println(" 3. 부분 검색");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // 전체 검색
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 주문 수 " + pageRowCnt + " 개");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(
						"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
			} else if (choice == 2) { // 페이징 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 한 페이지에 몇 개의 데이터를 보시겠습니까?");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, orderhistoALL.size());
				totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수
				if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
					totalPageSize++;
				}
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 주문 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(
						"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
				System.out.println(
						" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.println(" 1. 원하는 페이지 보기");
					System.out.println(" 0. 이전 화면");
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(" 몇 페이지를 보시겠습니까?");
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 주문 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println("해당 페이지는 정보가 없습니다.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, orderhistoALL.size());
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 주문 수 " + pageRowCnt + " 개  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
							System.out.println(
									"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
			} else if (choice == 3) { // 부분 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 1. 주문내역코드로 검색");
				System.out.println(" 2. 회원명으로 검색");
				System.out.println(" 3. 메뉴명으로 검색");
				System.out.println(" 4. 주문날짜로 검색");
				System.out.println(" 5. 배달예상시간으로 검색");
				System.out.println(" 6. 결제여부로 검색");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("주문코드 >> ");
					String orderOrdCodeStr = ScanUtil.nextLine();
					List<Object> searchOrderCode = new ArrayList<>();
					searchOrderCode.add(orderOrdCodeStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromOrderCode(searchOrderCode);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.print("회원명 >> ");
					String orderMemNmStr = ScanUtil.nextLine();
					List<Object> searchMemNm = new ArrayList<>();
					searchMemNm.add(orderMemNmStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromMemNm(searchMemNm);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.print("메뉴명 >> ");
					String orderMenuNmStr = ScanUtil.nextLine();
					List<Object> searchMenuNm = new ArrayList<>();
					searchMenuNm.add(orderMenuNmStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromMenuNm(searchMenuNm);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.print("주문연도 (YYYY) 입력 >> ");
					int orderYearStr = ScanUtil.nextInt();
					System.out.print("주문월 (MM) 입력 >> ");
					int orderMonthStr = ScanUtil.nextInt();
					System.out.print("주문일 (DD) 입력 >> ");
					int orderDayStr = ScanUtil.nextInt();
					String orderDateStr = orderYearStr + "-" + orderMonthStr + "-" + orderDayStr;
					List<Object> searchOrderDate = new ArrayList<>();
					searchOrderDate.add(orderDateStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromOrderDate(searchOrderDate);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.print("얼마 이상 배달시간을 예상하세요? (XX분 단위로 입력) >> ");
					int orderOrderEtaInt = ScanUtil.nextInt();
					List<Object> searchOrderEta = new ArrayList<>();
					searchOrderEta.add(orderOrderEtaInt);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromOrderEta(searchOrderEta);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
					System.out.print("결제 여부 (y / n) >> ");
					String orderSelYnStr = ScanUtil.nextLine();
					orderSelYnStr = orderSelYnStr.toUpperCase();
					List<Object> searchSelYn = new ArrayList<>();
					searchSelYn.add(orderSelYnStr);
					List<Map<String, Object>> orderInfo = adminReadDAO.orderSearchFromSelYn(searchSelYn);
					if (NullCheckUtil.isEmpty(orderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 메뉴가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println(
								"주문번호   주문코드        메뉴명                                   주문수량   배달예상시간   메뉴별총금액   주문날짜   주문자명   주문자주소   결제여부");
						System.out.println(
								" ──────────────────────────────────────────────────────────────────────────────────────────────────");
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
			System.out.println("등록된 라이더가 없습니다!");
		} else {
			int maxRideCodeLen = gapUtil.gapFullCnt(riderALL, "RIDCODE");
			int maxDeliCostLen = gapUtil.gapFullCnt(riderALL, "DELICOST");
			int maxAbsetyLen = gapUtil.gapFullCnt(riderALL, "ABSEYN");
			int maxStoNmLen = gapUtil.gapFullCnt(riderALL, "STONM");

			// 페이징 기능
			int pageNumber = 1; // 현재 페이지 번호
			int pageSize = 5; // 페이지당 표시할 데이터 수

			int startIndex = (pageNumber - 1) * pageSize; // 시작 인덱스
			int endIndex = Math.min(startIndex + pageSize, riderALL.size()); // 끝 인덱스

			int pageRowCnt = riderALL.size(); // 로우 개수
			int totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수

			if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
				totalPageSize++;
			}

			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 1. 전체목록 보기");
			System.out.println(" 2. 페이지 목록 보기");
			System.out.println(" 3. 부분 검색");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // 전체 검색
				System.out.println(" ────────────────────────────────");
				System.out.println(" 총 라이더 수 " + pageRowCnt + " 명");
				System.out.println(" ────────────────────────────────");
				System.out.println("라이더코드    배달비    배달가능    소속된업체명");
				System.out.println(" ────────────────────────────────");
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
			} else if (choice == 2) { // 페이징 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 한 페이지에 몇 개의 데이터를 보시겠습니까?");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, riderALL.size());
				totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수
				if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
					totalPageSize++;
				}
				System.out.println(" ────────────────────────────────");
				System.out.println(" 총 라이더 수 " + pageRowCnt + " 명  : " + pageNumber + " / " + totalPageSize + " 페이지");
				System.out.println(" ────────────────────────────────");
				System.out.println("라이더코드    배달비    배달가능    소속된업체명");
				System.out.println(" ────────────────────────────────");
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
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.println(" 1. 원하는 페이지 보기");
					System.out.println(" 0. 이전 화면");
					System.out.println(" ───────────────────────────────────────────────────");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println(" 몇 페이지를 보시겠습니까?");
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(" ────────────────────────────────");
							System.out.println(
									" 총 라이더 수 " + pageRowCnt + " 명  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(" ────────────────────────────────");
							System.out.println("라이더코드    배달비    배달가능    소속된업체명");
							System.out.println(" ────────────────────────────────");
							System.out.println("해당 페이지는 정보가 없습니다.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, riderALL.size());
							System.out.println(" ────────────────────────────────");
							System.out.println(
									" 총 라이더 수 " + pageRowCnt + " 명  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(" ────────────────────────────────");
							System.out.println("라이더코드    배달비    배달가능    소속된업체명");
							System.out.println(" ────────────────────────────────");
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
			} else if (choice == 3) { // 부분 검색
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 1. 라이더코드로 검색");
				System.out.println(" 2. 부재유무로 검색");
				System.out.println(" 3. 가게명으로 검색");
				System.out.println(" 4. 배달비로 검색");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				int searchno = ScanUtil.nextInt();
				if (searchno == 1) {
					System.out.print("라이더코드 >> ");
					String riderCodeStr = ScanUtil.nextLine();
					List<Object> searchRiderCode = new ArrayList<>();
					searchRiderCode.add(riderCodeStr);
					Map<String, Object> riderInfo = adminReadDAO.riderSearchFromRiderCode(searchRiderCode);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 라이더가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
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
						System.out.println(" ────────────────────────────────");
						System.out.println("라이더코드    배달비    배달가능    소속된업체명");
						System.out.println(" ────────────────────────────────");
						System.out.printf("%-" + maxRideCodeLen + "s%-" + maxDeliCostLen + "s%-" + maxAbsetyLen + "s%-"
								+ maxStoNmLen + "s\n", memRideCode, memDeliCost, memAbsety, memStoNm);
					}
					EnterUtil.enterNext(2);
				} else if (searchno == 2) {
					System.out.print("라이더 부재 (y / n) >> ");
					String riderAbseYnStr = ScanUtil.nextLine();
					riderAbseYnStr = riderAbseYnStr.toUpperCase();
					List<Object> searchRiderAbseYn = new ArrayList<>();
					searchRiderAbseYn.add(riderAbseYnStr);
					List<Map<String, Object>> riderInfo = adminReadDAO.riderSearchFromAbseYn(searchRiderAbseYn);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 라이더가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(" ────────────────────────────────");
						System.out.println("라이더코드    배달비    배달가능    소속된업체명");
						System.out.println(" ────────────────────────────────");
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
					System.out.print("가게명 >> ");
					String riderStoCodeStr = ScanUtil.nextLine();
					List<Object> searchStoCode = new ArrayList<>();
					searchStoCode.add(riderStoCodeStr);
					List<Map<String, Object>> riderInfo = adminReadDAO.riderSearchFromStoNm(searchStoCode);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 라이더가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(" ────────────────────────────────");
						System.out.println("라이더코드    배달비    배달가능    소속된업체명");
						System.out.println(" ────────────────────────────────");
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
					System.out.print("얼마 이상 배달비를 검색할까요? (0 ~ 9999999999) >> ");
					long riderDeliCostStr = ScanUtil.nextLong();
					List<Object> searchDeliCost = new ArrayList<>();
					searchDeliCost.add(riderDeliCostStr);
					List<Map<String, Object>> riderInfo = adminReadDAO.riderSearchFromDeliCost(searchDeliCost);
					if (NullCheckUtil.isEmpty(riderInfo)) {
						System.out.println(" ───────────────────────────────────────────────────");
						System.out.println("등록된 라이더가 없습니다!");
						System.out.println(" ───────────────────────────────────────────────────");
					} else {
						System.out.println(" ────────────────────────────────");
						System.out.println("라이더코드    배달비    배달가능    소속된업체명");
						System.out.println(" ────────────────────────────────");
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
