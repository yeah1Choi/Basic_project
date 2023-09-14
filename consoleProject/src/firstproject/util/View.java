package firstproject.util;

public interface View {

	// 메인 화면
	int HOME = 1;
	int ORDER_HOME = 101; // 로그인 후 주문 화면
	int ADMIN_HOME = 102; // 관리자 메뉴

	// 회원
	int MEMBER = 2;
	int MEMBER_SIGNUP = 201; // 회원가입
	int MEMBER_LOGIN = 202; // 로그인
	int MEMBER_ID_PW_FIND = 203; // ID/PW 찾기

	// 주문 화면
	int ORDER = 3;
	int ORDER_LOGOUT = 309; // 회원 로그아웃

	// 서비스 이용
	// 배달포장선택
//	int ORDER_TAKEOUT = 301;

	// 카테고리선택
	int CATEGORY = 4;
	int CATEGORY_DELI = 400;
	int CATEGORY_TAKE = 401;
	int CATEGORY_KOREAN = 402;
	int CATEGORY_WESTERN = 403;
	int CATEGORY_CHINESE = 404;
	int CATEGORY_JAPANESE = 405;
	int CATEGORY_DESSERT = 406;

	// 메뉴 선택
	int MENU = 5;
	int MENU_DETAIL = 501;

	// 결제화면(장바구니)
	int CART = 6;
	int RECEIPT = 601; // 주문완료페이지
	int CART_DELI = 602; // 배달선택 > 배달비 처리
	int CART_TAKE = 603; // 포장선택 > 배달비 미처리
	int USERPAGE = 609; // 주문내역(최근주문목록)페이지

	// 잔액충전
	int POINT = 7;

	// 관리자
	int ADMIN = 9;
	int ADMIN_MEM_MANAGEMENT = 901; // 회원정보 관리
	int ADMIN_STORE_MANAGEMENT = 902; // 가게리스트 관리
	int ADMIN_MENU_MANAGEMENT = 903; // 메뉴 관리
	int ADMIN_ORDERHISTORY = 904; // 주문내역 관리
	int ADMIN_RIDER_MANAGEMENT = 905; // 라이더 관리
	int ADMIN_LOGOUT = 909; // 관리자 로그아웃

	// 프로그램 종료
	int SYSTEM_EXIT = 0;

}
