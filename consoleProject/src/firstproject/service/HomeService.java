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

	// �α��� �� Ȩ
	public int home() {
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" �ȹ� �̽İ�");
		System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println(" 1. �α���");
		System.out.println(" 2. ȸ������");
		System.out.println(" 3. ID/PW ã��");
		System.out.println(" 9. ���α׷� ����");
		System.out.print("�Է� >> ");
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
			System.out.println("�߸��� �����Դϴ�.");
			System.out.println();
			return View.HOME;
		}
	}
}
