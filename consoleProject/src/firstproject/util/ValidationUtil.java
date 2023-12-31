package firstproject.util;

import firstproject.dao.MemberDAO;

public class ValidationUtil {
	
	// 싱글톤 패턴
	private static ValidationUtil instance = null;
	private ValidationUtil() {}
	public static ValidationUtil getInstance() {
		if (instance == null) instance = new ValidationUtil();
		return instance;
	}

	// https://coding-factory.tistory.com/819
	// 정규표현식 검증 사이트 모음
	
	// 회원 아이디 validation 
	// true : validation 완료 | false : 다시 입력
	public static boolean validationID(String memID) {
		boolean result = true;
		if (memID.length() < 6) {
			System.out.println(" ※ 아이디는 6자리 이상입니다.");
			result = false;
		} else {
			if (memID.matches("^[a-zA-Z0-9]*$")) { // 영어와 숫자만 입력 가능. 띄어쓰기 허용 안함.
				if (memID.matches("^[a-zA-Z]*$")) { // 영어만 쓴 경우
					System.out.println(" ※ 숫자를 필수로 입력해야 합니다.");
					result = false;
				} else if (memID.matches("^[0-9]*$")) { // 숫자만 쓴 경우
					System.out.println(" ※ 영어를 필수로 입력해야 합니다.");
					result = false;
				}
			} else {
				System.out.println(" ※ 영문ㆍ숫자만 입력가능 합니다.");
				result = false;
			}
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 회원 비밀번호 validation 
	// true : validation 완료 | false : 다시 입력
	public static boolean validationPW(String memPW) {
		boolean result = true;
		if (memPW.length() < 6) {
			System.out.println(" ※ 비밀번호는 6자리 이상입니다.");
			result = false;
		} else {
			if (memPW.matches("^[a-zA-Z0-9]*$")) { // 영어와 숫자만 입력 가능. 띄어쓰기 허용 안함.
				if (memPW.matches("^[a-zA-Z]*$")) { // 영어만 쓴 경우
					System.out.println(" ※ 숫자를 필수로 입력해야 합니다.");
					result = false;
				} else if (memPW.matches("^[0-9]*$")) { // 숫자만 쓴 경우
					System.out.println(" ※ 영어를 필수로 입력해야 합니다.");
					result = false;
				}
			} else {
				System.out.println(" ※ 영문ㆍ숫자만 입력가능 합니다.");
				result = false;
			}
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 회원 이름 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationName(String memNM) {
		boolean result = true;
		if (!memNM.matches("^[가-힣]*$")) { // 한글만 입력. 띄어쓰기 불가.
			System.out.println(" ※ 한글만 입력가능합니다. 띄어쓰기 불가능 합니다.");
			result = false;
		} else return result;
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 회원 주소 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationAddress(String memADD) {
		boolean result = true;
		if (!memADD.matches("^[0-9/\\s가-힣]*$")) { // 한글과 숫자만 입력 가능. 띄어쓰기 허용
			System.out.println(" ※ 한글ㆍ숫자만 입력가능 합니다.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 연락처 validation
	// true : validation 완료 | false : 다시 입력
	// ^\d{2,3}-\d{3,4}-\d{4}$
	public static boolean validationTEL(String memTEL) {
		boolean result = true;
		if(!memTEL.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) { // 010-0000-0000 형식만 입력 가능
			System.out.println(" ※ 0XX-0000-0000 형식만 입력 가능합니다.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 잔여금액 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationBalance(long balance) {
		boolean result = true;
		long cnt = balance;
		int len = (int)(Math.log10(cnt)+1);
//			System.out.println(len);
		if(len > 10) { // 금액 숫자가 10자리 미만이라면
			System.out.println(" ※ 금액을 10자리 미만으로 입력해 주세요.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 업체명 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationStoreNM(String StoreNM) {
		boolean result = true;
		if (!StoreNM.matches("^[0-9/\\s가-힣]*$")) { // 한글과 숫자만 입력 가능. 띄어쓰기 허용
			System.out.println(" ※ 한글ㆍ숫자ㆍ띄어쓰기만 입력가능 합니다.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 업체 주소 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationStoreAddress(String StoreAdd) {
		boolean result = true;
		if (!StoreAdd.matches("^[0-9/\\s가-힣]*$")) { // 한글과 숫자만 입력 가능. 띄어쓰기 허용
			System.out.println(" ※ 한글ㆍ숫자만 입력가능 합니다.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 최소주문금액 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationMinOrder(long MinOrder) {
		boolean result = true;
		long cnt = MinOrder;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 10) { // 금액 숫자가 10자리 미만이라면
			System.out.println(" ※ 금액을 10자리 미만으로 입력해 주세요.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// Y, N validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationYN(String ynCheck) {
		boolean result = true;
		if (!ynCheck.matches("^[yn]$")) { // y, n 소문자만 입력. 띄어쓰기 불가.
			System.out.println(" ※ y, n 소문자만 입력가능합니다. 띄어쓰기 불가능 합니다.");
			result = false;
		} else if (ynCheck.length() > 1) { // 이름이 2글자 미만이라면 입력 불가
			System.out.println(" ※ 1글자만 입력해 주세요.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// Y, N 대문자 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationYNtoUpperCase(String ynCheck) {
		boolean result = true;
		if (!ynCheck.matches("^[YN]$")) { // Y, N 대문자만 입력. 띄어쓰기 불가.
			System.out.println(" ※ Y, N 대문자만 입력가능합니다. 띄어쓰기 불가능 합니다.");
			result = false;
		} else if (ynCheck.length() > 1) { // 이름이 2글자 미만이라면 입력 불가
			System.out.println(" ※ 1글자만 입력해 주세요.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 코드 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationCode(String code) {
		boolean result = true;
		if (code.length() > 12) {
			System.out.println(" ※ 코드는 12자리 이하입니다.");
			result = false;
		} else {
			if (code.matches("^[A-Z0-9]*$")) { // 영어 대문자와 숫자만 입력 가능. 띄어쓰기 허용 안함.
				if (code.matches("^[A-Z]*$")) { // 영어 대문자만 쓴 경우
					System.out.println(" ※ 숫자를 필수로 입력해야 합니다.");
					result = false;
				} else if (code.matches("^[0-9]*$")) { // 숫자만 쓴 경우
					System.out.println(" ※ 영어 대문자를 필수로 입력해야 합니다.");
					result = false;
				}
			} else {
				System.out.println(" ※ 영어 대문자ㆍ숫자만 입력가능 합니다.");
				result = false;
			}
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 메뉴명 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationMenuName(String menuname) {
		boolean result = true;
		if (!menuname.matches("^[\\s가-힣]*$")) { // 한글만 입력 가능. 띄어쓰기 허용
			System.out.println(" ※ 한글만 입력가능 합니다. 띄어쓰기 허용.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 메뉴가격, 배달비 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationPrice(long price) {
		boolean result = true;
		long cnt = price;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 10) { // 금액 숫자가 10자리 미만이라면
			System.out.println(" ※ 금액을 10자리 미만으로 입력해 주세요.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 메뉴수량 validation
	// true : validation 완료 | false : 다시 입력
	public static boolean validationMenuRemainQty(int remainqty) {
		boolean result = true;
		long cnt = remainqty;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 9) { // 금액 숫자가 9자리 미만이라면
			System.out.println(" ※ 수량을 9자리 미만으로 입력해 주세요.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
	// 회원 레벨 검증
	public static boolean validationMemLv(int lv) {
		boolean result = true;
		long cnt = lv;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 1) { // 레벨 숫자가 1자리 이하라면
			System.out.println(" ※ 레벨을 1자리 이하으로 입력해 주세요.");
			result = false;
		}
		if (result) return result; // 입력 검증이 끝났다면 true 값을 곧바로 반환
		System.out.println();
		System.out.println(" ※ 다시 입력하세요.");
		System.out.println();
		return result;
	}
	
}
