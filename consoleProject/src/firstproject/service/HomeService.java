package firstproject.service;

import firstproject.util.ScanUtil;
import firstproject.util.View;

public class HomeService {

	private static HomeService instance = null;

	private HomeService() {
	}

	public static HomeService getInstance() {
		if (instance == null)
			instance = new HomeService();
		return instance;
	}

	String str = "";

	// 로그인 전 홈
	public int home() {
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println(" 안방 미식가");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.println(" 1. 로그인");
		System.out.println(" 2. 회원가입");
		System.out.println(" 3. ID/PW 찾기");
		System.out.println(" 9. 프로그램 종료");
		System.out.print("입력 >> ");
		int choice = ScanUtil.nextInt();
		switch (choice) {
		case 1:
			return View.MEMBER_LOGIN;
		case 2:
			return View.MEMBER_SIGNUP;
		case 3:
			return View.MEMBER_ID_PW_FIND;
		case 9:
			return View.SYSTEM_EXIT;
		default:
			System.out.println();
			System.out.println("잘못된 접근입니다.");
			System.out.println();
			return View.HOME;
		}
	}
}
