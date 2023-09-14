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
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     로그인 화면");
		System.out.println(" ───────────────────────────────────────────────────");

		System.out.print("   아이디 >> ");
		String memID = ScanUtil.nextLine();
		List<Object> searchId = new ArrayList<>();
		searchId.add(memID);

		System.out.print("   비밀번호 >> ");
		String memPW = ScanUtil.nextLine();
		List<Object> searchPw = new ArrayList<>();
		searchPw.add(memPW);

		Map<String, Object> memberInfoId = memberDAO.loginId(searchId);
		Map<String, Object> memberInfoPw = memberDAO.loginPw(searchPw);

		if (memberInfoId == null) {
			System.out.println(" ※ 일치하는 아이디가 없습니다.");
			EnterUtil.enterNext(1);
			return View.HOME;
		} else if (memberInfoPw == null) {
			System.out.println(" ※ 일치하는 비밀번호가 없습니다.");
			EnterUtil.enterNext(1);
			return View.HOME;
		} else {
			List<Object> param = new ArrayList<>();
			param.add(memID);
			param.add(memPW);
			Map<String, Object> memberInfo = memberDAO.login(param);

			// 로그인한 회원의 세션 정보 집어 넣기
			Controller.sessionStorage.put("login", true);
			Controller.sessionStorage.put("loginInfo", memberInfo);

			if (Controller.isAdmin()) {
//				str = "[관리자]님 환영합니다!";
				System.out.println(" ───────────────────────────────────────────────────");
				str = "   [ " + memberInfo.get("MEMNM").toString() + " ]님 환영합니다!";
				System.out.println(str);
				System.out.println(" ───────────────────────────────────────────────────");
				EnterUtil.enterNext(2);
				return View.ADMIN_HOME;
			} else if (Controller.isMember()) {
				System.out.println(" ───────────────────────────────────────────────────");
				str = "   [ " + memberInfo.get("MEMNM").toString() + " ]님 환영합니다!";
				System.out.println(str);
				System.out.println(" ───────────────────────────────────────────────────");
				EnterUtil.enterNext(2);
				return View.ORDER_HOME;
			}
		}
		return 0;
	}

	// 회원가입 메서드
	public int signUp() {
		String memID = ""; // 회원 아이디
		String memPW = ""; // 회원 비밀번호
		String memNM = ""; // 회원 이름
		String memADD = ""; // 회원 주소
		String memTEL = ""; // 회원 연락처
		long balance = 0; // 잔여금액
		int memLv = 1; // 회원 레벨

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     [회원가입]");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println();

		while (true) {
			System.out.println(" * 아이디 입력 [ 영문ㆍ숫자 필수 / 6글자 이상 입력 ]");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			memID = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationID(memID))
				break;
		}

		while (true) {
			System.out.println(" * 비밀번호 입력 [ 영문ㆍ숫자 필수 / 6글자 이상 입력 ]");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			memPW = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationPW(memPW))
				break;
		}

		while (true) {
			System.out.println(" * 이름 입력 [ 한글 입력 ]");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			memNM = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationName(memNM))
				break;
		}

		while (true) {
			System.out.println(" * 주소 입력 [ 한글ㆍ숫자 입력 ]");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			memADD = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationAddress(memADD))
				break;
		}

		while (true) {
			System.out.println(" * 연락처 입력[ 0XX-XXXX-XXXX 형식으로 입력 ]");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			memTEL = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationTEL(memTEL))
				break;
		}

		while (true) {
			System.out.println(" * 금액을 충전하시겠습니까? (y/n) ");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			String chargeYN = ScanUtil.nextLine();
			if (chargeYN.equals("y")) {
				System.out.println(" * 금액 입력[ 숫자는 10자리 미만으로 입력 ] ");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				balance = ScanUtil.nextLong();
				System.out.println();
			} else {
				balance = 0;
			}
			if (ValidationUtil.validationBalance(balance))
				break;
		}

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println(" * 입력하신 정보로 회원가입 하시겠습니까? (y/n)");
		System.out.print("   >> ");
		if (ScanUtil.nextLine().equals("y")) {
			Map<String, Object> result = memberDAO.isValID(memID);
			if (result != null) {
				System.out.println(" ※ 이미 등록된 아이디 입니다!");
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
					str = "[" + memID + "]님 가입을 환영합니다!";
					System.out.println(str);
				} else
					System.out.println(" ※ 회원가입 실패!");
			}
		} else {
			System.out.println(" * 회원가입을 취소합니다.");
		}

		EnterUtil.enterNext(1);
		return View.HOME;
	}

	// 아이디/비밀번호 찾기 메서드
	public int find() {
		boolean result = false;

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     [ 아이디 / 비밀번호 찾기 ]");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println();
		System.out.println(" 1. 아이디 찾기");
		System.out.println(" 2. 비밀번호 찾기");
		System.out.println(" 0. 이전 메뉴");
		System.out.println();
		System.out.println(" ───────────────────────────────────────────────────");
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
			System.out.println(" ※ 잘못된 접근입니다.");
			System.out.println();
			return View.MEMBER_ID_PW_FIND;
		}

		if (result) {
			return View.HOME;
		} else {
			return View.MEMBER_ID_PW_FIND;
		}

	}

	// 아이디 찾기 메서드
	private boolean findID() {
		boolean result = true;
		String userNM = "";
		String userTEL = "";

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     [아이디 찾기]");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println();

		while (true) {
			System.out.println(" * 이름을 입력하세요.");
			System.out.println();
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			userNM = ScanUtil.nextLine();
			if (ValidationUtil.validationName(userNM))
				break;
		}

		while (true) {
			System.out.println(" * 전화번호를 입력하세요.");
			System.out.println();
			System.out.println(" ───────────────────────────────────────────────────");
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
			System.out.println(" ※ 입력한 이름과 전화번호를 가진 회원정보가 없습니다.");
			result = false;
		} else {
			str = " 해당 정보로 조회된 아이디는 [" + selectMem.get("MEMID") + "] 입니다.";
			System.out.println(str);
			result = true;
		}

		EnterUtil.enterNext(1);
		return result;
	}

	// 비밀번호 찾기 메서드
	private boolean findPW() {
		boolean result = true;
		String userID = "";
		String userTEL = "";

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     [비밀번호 찾기]");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println();

		while (true) {
			System.out.println(" * 회원 ID를 입력하세요.");
			System.out.println();
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print("   >> ");
			userID = ScanUtil.nextLine();
			if (ValidationUtil.validationID(userID))
				break;
		}

		while (true) {
			System.out.println(" * 전화번호를 입력하세요.");
			System.out.println();
			System.out.println(" ───────────────────────────────────────────────────");
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
			System.out.println(" ※ 입력한 회원ID와 전화번호를 가진 회원정보가 없습니다.");
			result = false;
		} else {
			str = " 해당 정보로 조회된 비밀번호는 [" + selectMem.get("MEMPW") + "] 입니다.";
			System.out.println(str);
			result = true;
		}

		EnterUtil.enterNext(1);
		return result;
	}

	// 포인트 충전 서비스 메소드
	public int pointDeposit() {

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;

		String userID = (String) userInfo.get("MEMID");
		String userNM = (String) userInfo.get("MEMNM");
		String userPW = (String) userInfo.get("MEMPW");

		Map<String, Object> balanceOne = selectDAO.BalanceOne(userID);
		BigDecimal balanceBigDecimal = (BigDecimal) balanceOne.get("BALANCE");
		long balance = balanceBigDecimal.longValue();

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     [포인트 충전]");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("   [" + userID + "]님의 현재 사용가능한 잔고 :");
		System.out.println("    ** " + balance + "원 **");

		while (true) {
			System.out.println(" 1. 충전하기");
			System.out.println(" 2. 뒤로가기");
			System.out.print("   입력  >> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				System.out.println(" 비밀번호를 입력해 인증해주십시오.");
				System.out.print(" 비밀번호 입력 >> ");
				String matchPW = ScanUtil.nextLine();

				if (matchPW.equals(userPW)) {
					System.out.println(" [" + userNM + "]님 감사합니다.");
					System.out.println(" 얼마를 충전하시겠습니까?");
					System.out.print(" 금액 [ 숫자는 10자리 미만으로 입력 ]  >> ");

					long addPoint = ScanUtil.nextLong();

					while (true) {
						if (addPoint > 999999999) {
							System.out.println(" 조건에 맞게 다시 입력하세요.");
							return View.POINT;
						} else {
							System.out.println(" " + addPoint + "원을 충전합니다.");
							balance = balance + addPoint;
							List<Object> param = new ArrayList<>();
							param.add(balance);
							param.add(userID);
							memberDAO.depositPoint(param);
							break;
						}
					}

					System.out.println(" 엔터를 누르면 장바구니로 돌아갑니다.");
					System.out.println(" 장바구니에서 결제를 완료해주세요.");
					EnterUtil.enterNext(2);
					return View.CART;

				} else {
					System.out.println(" ※ 틀린 비밀번호 입니다. 다시 입력해주세요.");
					break;
				}
			case 2:
				return View.ORDER_HOME;

			default:
				System.out.println(" ※ 잘못된 접근입니다. 다시 입력해주세요.");
			}
		}
	}

	// 마이페이지 : 주문내역페이지
	public int userPage() {

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     ** 마이 페이지 **");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("    ** 주문내역리스트 : **");

		// 로그인된 유저의 정보를 세션으로부터 가져옴 - 그 중에 쿼리를 위한 아이디정보를 뺌
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		// 아이디정보를 이용해 주문코드를 가져옴
		List<Map<String, Object>> orderCodeList = memberDAO.orderCodeSearch(userID);
//		System.out.println("orderCodeList : " + orderCodeList);

		// 주문내역 널 오류 처리
		if (NullCheckUtil.isEmpty(orderCodeList)) {
			System.out.println(" ※ 주문하신 내역이 없습니다.");
			System.out.println(" ***************************");
		} else {

			// 페이징 기능
			int pageNumber = 1; // 현재 페이지 번호
			int pageSize = 5; // 페이지당 표시할 데이터 수

			int startIndex = (pageNumber - 1) * pageSize; // 시작 인덱스
			int endIndex = Math.min(startIndex + pageSize, orderCodeList.size()); // 끝 인덱스

			int pageRowCnt = orderCodeList.size(); // 로우 개수
			int totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수

			if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
				totalPageSize++;
			}

			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 1. 페이지 목록 보기");
			System.out.println(" 2. 주문코드 검색");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // 페이징 처리
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.println(" 한 페이지에 몇 개의 데이터를 보시겠습니까?");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, orderCodeList.size());
				totalPageSize = pageRowCnt / pageSize; // 총 페이지 개수
				if (pageRowCnt % pageSize != 0) { // 다음 페이지까지 로우가 남음
					totalPageSize++;
				}
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
				System.out.println(" 총 주문 수 " + pageRowCnt + " 건  : " + pageNumber + " / " + totalPageSize + " 페이지");
				System.out.println(" ──────────────────────────────────────────────────────────────────────────");
				for (int i = startIndex; i < endIndex; i++) {
					long sum = 0;
					long countSum = 0;
					long countDeilSum = 0;
					// 아이디정보를 이용해 주문내역 테이블 가져옴
					Map<String, Object> orderCode = orderCodeList.get(i);
					List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
							String.valueOf(orderCode.get("ORDERCODE")));
					// 주문횟수에 따라 칸마다 나눠 주문정보 보여줌
					System.out.println(" ==========================================================================");
					System.out.println(" 주문코드 : " + orderInfoList.get(0).get("ORDERCODE")); // 날짜형식 지정해 출력하기
					System.out.println(" ──────────────────────────────────────────────────────────────────────────");
					System.out.println(" 주문날짜 : " + orderInfoList.get(0).get("ORDERDATE"));
					System.out.println(" ──────────────────────────────────────────────────────────────────────────");
					for (Map<String, Object> orderItem : orderInfoList) {
						System.out.print(" 메뉴명 : " + orderItem.get("MENUNM") + " | "); // 중복
						System.out.print(" 주문수량 : " + orderItem.get("ORDERQTY") + "개" + " | "); // 중복
						System.out.println(" 메뉴별 금액 : " + orderItem.get("TOTALPRICE") + "원"); // 중복
						countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
						sum += countSum;
					}
					if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
						countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
						sum += countDeilSum;
						System.out.println(" 배달비 : " + orderInfoList.get(0).get("DELICOST") + "원");
						System.out.println(" 배달/포장 : 배달");
					} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
						System.out.println(" 배달/포장 : 포장");
					}
					System.out.println(" ──────────────────────────────────────────────────────────────────────────");
					System.out.println(" 총 금액 : " + sum + "원");
					System.out.println(" 배달예상시간 : " + orderInfoList.get(0).get("ORDERETA") + "분");
					System.out.println(" 주문자명 : " + orderInfoList.get(0).get("MEMNM"));
					System.out.println(" 주문자주소 : " + orderInfoList.get(0).get("MEMADD"));
					System.out.println(" 결제여부 : " + orderInfoList.get(0).get("SELYN"));
					System.out.println(" ==========================================================================");
				}
				while (true) {
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
									" 총 주문 수 " + pageRowCnt + " 건  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println("해당 페이지는 정보가 없습니다.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, orderCodeList.size());
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							System.out.println(
									" 총 주문 수 " + pageRowCnt + " 건  : " + pageNumber + " / " + totalPageSize + " 페이지");
							System.out.println(
									" ──────────────────────────────────────────────────────────────────────────");
							for (int i = startIndex; i < endIndex; i++) {
								long sum = 0;
								long countSum = 0;
								long countDeilSum = 0;
								// 아이디정보를 이용해 주문내역 테이블 가져옴
								Map<String, Object> orderCode = orderCodeList.get(i);
								List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
										String.valueOf(orderCode.get("ORDERCODE")));
								// 주문횟수에 따라 칸마다 나눠 주문정보 보여줌
								System.out.println(
										" ==========================================================================");
								System.out.println(" 주문코드 : " + orderInfoList.get(0).get("ORDERCODE")); // 날짜형식 지정해 출력하기
								System.out.println(
										" ──────────────────────────────────────────────────────────────────────────");
								System.out.println(" 주문날짜 : " + orderInfoList.get(0).get("ORDERDATE"));
								System.out.println(
										" ──────────────────────────────────────────────────────────────────────────");
								for (Map<String, Object> orderItem : orderInfoList) {
									System.out.print(" 메뉴명 : " + orderItem.get("MENUNM") + " | "); // 중복
									System.out.print(" 주문수량 : " + orderItem.get("ORDERQTY") + "개" + " | "); // 중복
									System.out.println(" 메뉴별 금액 : " + orderItem.get("TOTALPRICE") + "원"); // 중복
									countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
									sum += countSum;
								}
								if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
									countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
									sum += countDeilSum;
									System.out.println(" 배달비 : " + orderInfoList.get(0).get("DELICOST") + "원");
									System.out.println(" 배달/포장 : 배달");
								} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
									System.out.println(" 배달/포장 : 포장");
								}
								System.out.println(
										" ──────────────────────────────────────────────────────────────────────────");
								System.out.println(" 총 금액 : " + sum + "원");
								System.out.println(" 배달예상시간 : " + orderInfoList.get(0).get("ORDERETA") + "분");
								System.out.println(" 주문자명 : " + orderInfoList.get(0).get("MEMNM"));
								System.out.println(" 주문자주소 : " + orderInfoList.get(0).get("MEMADD"));
								System.out.println(" 결제여부 : " + orderInfoList.get(0).get("SELYN"));
								System.out.println(
										" ==========================================================================");
							}
						}
					} else if (pageListSelect == 0) {
						return View.ORDER_HOME;
					}
				}
			} else if (choice == 2) { // 부분 검색
				System.out.println(" ───────────────────────────────────────────────────");
				for (Map<String, Object> orderCode : orderCodeList) {
					System.out.println(" 주문코드 : " + orderCode.get("ORDERCODE"));
				}
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" 주문코드 입력 >> ");
				String memIDstr = ScanUtil.nextLine();
				for (Map<String, Object> orderCode : orderCodeList) {
					if (memIDstr.equals(orderCode.get("ORDERCODE"))) {
						long sum = 0;
						long countSum = 0;
						long countDeilSum = 0;
						// 아이디정보를 이용해 주문내역 테이블 가져옴
						List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
								String.valueOf(orderCode.get("ORDERCODE")));
						// 주문횟수에 따라 칸마다 나눠 주문정보 보여줌
						System.out
								.println(" ==========================================================================");
						System.out.println(" 주문코드 : " + orderInfoList.get(0).get("ORDERCODE")); // 날짜형식 지정해 출력하기
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(" 주문날짜 : " + orderInfoList.get(0).get("ORDERDATE"));
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						for (Map<String, Object> orderItem : orderInfoList) {
							System.out.print(" 메뉴명 : " + orderItem.get("MENUNM") + " | "); // 중복
							System.out.print(" 주문수량 : " + orderItem.get("ORDERQTY") + "개" + " | "); // 중복
							System.out.println(" 메뉴별 금액 : " + orderItem.get("TOTALPRICE") + "원"); // 중복
							countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
							sum += countSum;
						}
						if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
							countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
							sum += countDeilSum;
							System.out.println(" 배달비 : " + orderInfoList.get(0).get("DELICOST") + "원");
							System.out.println(" 배달/포장 : 배달");
						} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
							System.out.println(" 배달/포장 : 포장");
						}
						System.out
								.println(" ──────────────────────────────────────────────────────────────────────────");
						System.out.println(" 총 금액 : " + sum + "원");
						System.out.println(" 배달예상시간 : " + orderInfoList.get(0).get("ORDERETA") + "분");
						System.out.println(" 주문자명 : " + orderInfoList.get(0).get("MEMNM"));
						System.out.println(" 주문자주소 : " + orderInfoList.get(0).get("MEMADD"));
						System.out.println(" 결제여부 : " + orderInfoList.get(0).get("SELYN"));
						System.out
								.println(" ==========================================================================");
					}
				}
			}

//			for(Map<String, Object> orderCode : orderCodeList) {
//				long sum = 0;
//				long countSum = 0;
//				long countDeilSum = 0;
//				// 아이디정보를 이용해 주문내역 테이블 가져옴
//				List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID, String.valueOf(orderCode.get("ORDERCODE")));
//				// 주문횟수에 따라 칸마다 나눠 주문정보 보여줌
//				System.out.println(" 주문코드 : " + orderInfoList.get(0).get("ORDERCODE")); // 날짜형식 지정해 출력하기
//				System.out.println("---------------------------------------");
//				System.out.println(" 주문날짜 : " + orderInfoList.get(0).get("ORDERDATE"));
//				System.out.println("---------------------------------------");
//				for(Map<String, Object> orderItem : orderInfoList) {
//					System.out.print(" 메뉴명 : " + orderItem.get("MENUNM") + " | "); // 중복
//					System.out.print(" 주문수량 : " + orderItem.get("ORDERQTY") + "개"  + " | "); // 중복
//					System.out.println(" 메뉴별 금액 : " + orderItem.get("TOTALPRICE") + "원"); // 중복
//					countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
//					sum += countSum;
//					if(orderItem.get("DELIORTAKE").equals("deliYN")) {
//						System.out.println(" 배달비 : " + orderInfoList.get(0).get("DELICOST") + "원");
//						countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
//						sum += countDeilSum;
//					}
//				}
//				if(orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
//					System.out.println(" 배달/포장 : 배달");
//				}else if(orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
//					System.out.println(" 배달/포장 : 포장");
//				}
//				System.out.println("---------------------------------------");
//				System.out.println(" 총 금액 : " + sum + "원");
//				System.out.println(" 배달예상시간 : " + orderInfoList.get(0).get("ORDERETA") + "분");
//				System.out.println(" 주문자명 : " + orderInfoList.get(0).get("MEMNM"));
//				System.out.println(" 주문자주소 : " + orderInfoList.get(0).get("MEMADD"));
//				System.out.println(" 결제여부 : " + orderInfoList.get(0).get("SELYN"));
//				System.out.println(" ***************************");
//			}

			while (true) {
				System.out.println(" 0. 이전 화면");
				System.out.println(" ───────────────────────────────────────────────────");
				System.out.print(" 입력 >> ");
				switch (ScanUtil.nextInt()) {
				case 0:
					return View.ORDER_HOME;
				default:
					System.out.println(" ※ 잘못된 접근입니다. 다시 입력하세요.");
					break;
				}
			}
		}
		return View.ORDER_HOME;
	}
}