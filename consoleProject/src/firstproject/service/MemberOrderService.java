package firstproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import firstproject.controller.Controller;
import firstproject.dao.SelectDAO;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class MemberOrderService {

	private static MemberOrderService instance = null;

	private MemberOrderService() {
	}

	public static MemberOrderService getInstance() {
		if (instance == null)
			instance = new MemberOrderService();
		return instance;
	}

	SelectDAO selectDAO = SelectDAO.getInstance();

//	// 로그인 후 주문 화면
//	public int home() {
//		System.out.println(" ───────────────────────────────────────────────────");
//		System.out.println(" 주문 화면");
//		System.out.println(" ───────────────────────────────────────────────────");
//		System.out.println("1. 배달");
//		System.out.println("2. 포장");
//		System.out.println("0. 로그아웃");
//		System.out.print("입력 >> ");
//		int choice = ScanUtil.nextIntegerLine();
//		switch(choice) {
//		case 1 : return View.CATEGORY_DELI;
//		case 2 : return View.CATEGORY_TAKE;
//		case 0 : return View.ORDER_LOGOUT;
//		default : 
//			System.out.println("잘못된 접근입니다.");
//			return View.ORDER_HOME;
//		}
//	}

	// 로그인 후 주문 화면
	public int orderhome() {

		Controller.sessionStorage.put("ordercode", null); // 주문코드 정보(O)

		// 1. 가게 선택 > 그 가게와 연결된 라이더 정보(라이더 코드, 부재 유무) 중에 배달비를 가져온다.
		Controller.sessionStorage.put("delicost", null); // 배달비 정보

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> loginInfo = (Map<String, Object>) obj;
		String memAdd = loginInfo.get("MEMADD").toString();
		String[] addArray = memAdd.split(" ");
		// System.out.println(addArray[1]);
		memAdd = addArray[1];
		Controller.sessionStorage.put("memAdd", memAdd); // 사용자 주소 정보(O)

		LocalDate now = LocalDate.now(); // 현재 날짜 구하기
		Controller.sessionStorage.put("orderdate", now); // 주문날짜 정보(O)

		Controller.sessionStorage.put("ordereta", null); // 배달예상시간 정보 (O)

		Controller.sessionStorage.put("deliOrTake", null); // 배달/포장 유무
//		System.out.println(Controller.sessionStorage.toString());

		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("     주문 화면");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println("   1. 배달");
		System.out.println("   2. 포장");
		System.out.println("   9. 마이페이지");
		System.out.println("   0. 로그아웃");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.print("   입력 >> ");
		int choice = ScanUtil.nextIntegerLine();
		switch (choice) {
		case 1:
			// 배달주문시 대전주소만 배달이 가능하게 하기
//				Object obj1 = Controller.sessionStorage.get("loginInfo");
//				Map<String, Object> userInfo = (Map<String, Object>) obj1;

			String userID = (String) loginInfo.get("MEMID");

			List<Map<String, Object>> daejeonOnly = selectDAO.daejeonList(userID);

			if (daejeonOnly == null) {
				System.out.println(" ※ 죄송합니다. 포장주문만 주문가능합니다.");
				System.out.println("주문 주소가 '대전'시여야 배달 주문이 가능합니다. 다시 주문해주세요");
				return View.ORDER_HOME;
			} else {
				return View.CATEGORY_DELI;
			}
		case 2:
			return View.CATEGORY_TAKE;
		case 9:
			return View.USERPAGE;
		case 0:
			return View.ORDER_LOGOUT;
		default:
			System.out.println(" ※ 잘못된 접근입니다.");
			return View.ORDER_HOME;
		}
	}

	// 회원 로그아웃
	public int logout() {
		String choice = "";

		while (true) {
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println(" 로그아웃");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println();
			System.out.println(" * 로그아웃 하시겠습니까? ( y / n )");
			System.out.println();
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.print(" >> ");
			choice = ScanUtil.nextLine();
			System.out.println(" ───────────────────────────────────────────────────");
			if (ValidationUtil.validationYN(choice))
				break;
		}

		if (choice.equals("y")) {
			Object loginInfoObj = Controller.sessionStorage.get("loginInfo");
			Map<String, Object> loginInfo = (Map<String, Object>) loginInfoObj;
			System.out.println("");
			System.out.println("  [ " + loginInfo.get("MEMNM") + " ] 님 로그아웃 되었습니다.");
			System.out.println("");
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println("");
			System.out.println(" << 이용해 주셔서 감사합니다 >> ");
			System.out.println();

			// 세션 초기화
			Controller.sessionStorage.put("login", null);
			Controller.sessionStorage.put("loginInfo", null);
			Controller.sessionStorage.remove("ordercode");
			Controller.sessionStorage.remove("delicost");
			Controller.sessionStorage.remove("memAdd");
			Controller.sessionStorage.remove("orderdate");
			Controller.sessionStorage.remove("ordereta");
			Controller.sessionStorage.remove("deliOrTake");

			return View.HOME;
		} else if (choice.equals("n")) {
			System.out.println(" * 사용자 화면으로 돌아갑니다.");
			return View.ORDER_HOME;
		} else {
			System.out.println(" >> 올바른 입력이 아닙니다. 다시 입력하세요.");
			return View.ORDER_LOGOUT;
		}
	}

}
