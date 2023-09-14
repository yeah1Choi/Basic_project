package firstproject.util;

public class EnterUtil {

	// 엔터키를 눌러 다음 화면으로 넘어가게 버퍼를 거는 메서드
	public static void enterNext(int choice) {
		if(choice == 1) {
			System.out.println(" ───────────────────────────────────────────────────");
			System.out.println("     확인 : Enter");
			ScanUtil.nextLine();
		}else if(choice == 2) {
			System.out.println("     확인 : Enter");
			ScanUtil.nextLine();
		}
	}
	
}
