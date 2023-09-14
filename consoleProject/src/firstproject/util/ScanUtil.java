package firstproject.util;

import java.util.Scanner;

public class ScanUtil {

	private static Scanner sc = new Scanner(System.in);
	
	public static String nextLine() {
		return sc.nextLine();
	}
	
	public static int nextIntegerLine() {
		String numStr = "";
		int num = 0;
		
		numStr = sc.nextLine();
		if(numStr.equals("")) {
			numStr = "999999999";
			num = Integer.parseInt(numStr);
		}else if(!numStr.matches("^[0-9]*$")) {
			System.out.println(" �� ���� �̿��� ���� �Է����� ������!");
			numStr = "999999999";
			num = Integer.parseInt(numStr);
		}else {
			num = Integer.parseInt(numStr);
		}
			
		return num;
	}
	
	public static long nextLongLine() {
		String numStr = "";
		long num = 0;
		
		numStr = sc.nextLine();
		if(numStr.equals("")) {
			numStr = "999999999";
			num = Long.parseLong(numStr);
		}else if(!numStr.matches("^[0-9]*$")) {
			System.out.println(" �� ���� �̿��� ���� �Է����� ������!");
			numStr = "999999999";
			num = Long.parseLong(numStr);
		}else {
			num = Long.parseLong(numStr);
		}
			
		return num;
	}
	
	public static int nextInt() {
		int result = 0;
		while(true) {
			try {
				result = Integer.parseInt(sc.nextLine());
				break;
			}catch (Exception e) {
				System.out.println(" �� �ùٸ� ���ڸ� �Է��� �ּ���!");
				break;
			}
		}
		return result;
	}
	
	public static long nextLong() {
		long result = 0;
		while(true) {
			try {
				result = Long.parseLong(sc.nextLine());
				break;
			}catch (Exception e) {
				System.out.println(" �� �ùٸ� ���ڸ� �Է��� �ּ���!");
				break;
			}
		}
		return result;
	}
	
}
