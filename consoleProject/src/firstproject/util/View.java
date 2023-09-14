package firstproject.util;

public interface View {

	// ���� ȭ��
	int HOME = 1;
	int ORDER_HOME = 101; // �α��� �� �ֹ� ȭ��
	int ADMIN_HOME = 102; // ������ �޴�

	// ȸ��
	int MEMBER = 2;
	int MEMBER_SIGNUP = 201; // ȸ������
	int MEMBER_LOGIN = 202; // �α���
	int MEMBER_ID_PW_FIND = 203; // ID/PW ã��

	// �ֹ� ȭ��
	int ORDER = 3;
	int ORDER_LOGOUT = 309; // ȸ�� �α׾ƿ�

	// ���� �̿�
	// ������弱��
//	int ORDER_TAKEOUT = 301;

	// ī�װ�����
	int CATEGORY = 4;
	int CATEGORY_DELI = 400;
	int CATEGORY_TAKE = 401;
	int CATEGORY_KOREAN = 402;
	int CATEGORY_WESTERN = 403;
	int CATEGORY_CHINESE = 404;
	int CATEGORY_JAPANESE = 405;
	int CATEGORY_DESSERT = 406;

	// �޴� ����
	int MENU = 5;
	int MENU_DETAIL = 501;

	// ����ȭ��(��ٱ���)
	int CART = 6;
	int RECEIPT = 601; // �ֹ��Ϸ�������
	int CART_DELI = 602; // ��޼��� > ��޺� ó��
	int CART_TAKE = 603; // ���弱�� > ��޺� ��ó��
	int USERPAGE = 609; // �ֹ�����(�ֱ��ֹ����)������

	// �ܾ�����
	int POINT = 7;

	// ������
	int ADMIN = 9;
	int ADMIN_MEM_MANAGEMENT = 901; // ȸ������ ����
	int ADMIN_STORE_MANAGEMENT = 902; // ���Ը���Ʈ ����
	int ADMIN_MENU_MANAGEMENT = 903; // �޴� ����
	int ADMIN_ORDERHISTORY = 904; // �ֹ����� ����
	int ADMIN_RIDER_MANAGEMENT = 905; // ���̴� ����
	int ADMIN_LOGOUT = 909; // ������ �α׾ƿ�

	// ���α׷� ����
	int SYSTEM_EXIT = 0;

}
