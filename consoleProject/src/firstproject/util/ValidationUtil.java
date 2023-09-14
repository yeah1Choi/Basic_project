package firstproject.util;

import firstproject.dao.MemberDAO;

public class ValidationUtil {
	
	// �̱��� ����
	private static ValidationUtil instance = null;
	private ValidationUtil() {}
	public static ValidationUtil getInstance() {
		if (instance == null) instance = new ValidationUtil();
		return instance;
	}

	// https://coding-factory.tistory.com/819
	// ����ǥ���� ���� ����Ʈ ����
	
	// ȸ�� ���̵� validation 
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationID(String memID) {
		boolean result = true;
		if (memID.length() < 6) {
			System.out.println(" �� ���̵�� 6�ڸ� �̻��Դϴ�.");
			result = false;
		} else {
			if (memID.matches("^[a-zA-Z0-9]*$")) { // ����� ���ڸ� �Է� ����. ���� ��� ����.
				if (memID.matches("^[a-zA-Z]*$")) { // ��� �� ���
					System.out.println(" �� ���ڸ� �ʼ��� �Է��ؾ� �մϴ�.");
					result = false;
				} else if (memID.matches("^[0-9]*$")) { // ���ڸ� �� ���
					System.out.println(" �� ��� �ʼ��� �Է��ؾ� �մϴ�.");
					result = false;
				}
			} else {
				System.out.println(" �� ���������ڸ� �Է°��� �մϴ�.");
				result = false;
			}
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ȸ�� ��й�ȣ validation 
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationPW(String memPW) {
		boolean result = true;
		if (memPW.length() < 6) {
			System.out.println(" �� ��й�ȣ�� 6�ڸ� �̻��Դϴ�.");
			result = false;
		} else {
			if (memPW.matches("^[a-zA-Z0-9]*$")) { // ����� ���ڸ� �Է� ����. ���� ��� ����.
				if (memPW.matches("^[a-zA-Z]*$")) { // ��� �� ���
					System.out.println(" �� ���ڸ� �ʼ��� �Է��ؾ� �մϴ�.");
					result = false;
				} else if (memPW.matches("^[0-9]*$")) { // ���ڸ� �� ���
					System.out.println(" �� ��� �ʼ��� �Է��ؾ� �մϴ�.");
					result = false;
				}
			} else {
				System.out.println(" �� ���������ڸ� �Է°��� �մϴ�.");
				result = false;
			}
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ȸ�� �̸� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationName(String memNM) {
		boolean result = true;
		if (!memNM.matches("^[��-�R]*$")) { // �ѱ۸� �Է�. ���� �Ұ�.
			System.out.println(" �� �ѱ۸� �Է°����մϴ�. ���� �Ұ��� �մϴ�.");
			result = false;
		} else return result;
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ȸ�� �ּ� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationAddress(String memADD) {
		boolean result = true;
		if (!memADD.matches("^[0-9/\\s��-�R]*$")) { // �ѱ۰� ���ڸ� �Է� ����. ���� ���
			System.out.println(" �� �ѱۤ����ڸ� �Է°��� �մϴ�.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ����ó validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	// ^\d{2,3}-\d{3,4}-\d{4}$
	public static boolean validationTEL(String memTEL) {
		boolean result = true;
		if(!memTEL.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) { // 010-0000-0000 ���ĸ� �Է� ����
			System.out.println(" �� 0XX-0000-0000 ���ĸ� �Է� �����մϴ�.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// �ܿ��ݾ� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationBalance(long balance) {
		boolean result = true;
		long cnt = balance;
		int len = (int)(Math.log10(cnt)+1);
//			System.out.println(len);
		if(len > 10) { // �ݾ� ���ڰ� 10�ڸ� �̸��̶��
			System.out.println(" �� �ݾ��� 10�ڸ� �̸����� �Է��� �ּ���.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ��ü�� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationStoreNM(String StoreNM) {
		boolean result = true;
		if (!StoreNM.matches("^[0-9/\\s��-�R]*$")) { // �ѱ۰� ���ڸ� �Է� ����. ���� ���
			System.out.println(" �� �ѱۤ����ڤ����⸸ �Է°��� �մϴ�.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ��ü �ּ� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationStoreAddress(String StoreAdd) {
		boolean result = true;
		if (!StoreAdd.matches("^[0-9/\\s��-�R]*$")) { // �ѱ۰� ���ڸ� �Է� ����. ���� ���
			System.out.println(" �� �ѱۤ����ڸ� �Է°��� �մϴ�.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// �ּ��ֹ��ݾ� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationMinOrder(long MinOrder) {
		boolean result = true;
		long cnt = MinOrder;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 10) { // �ݾ� ���ڰ� 10�ڸ� �̸��̶��
			System.out.println(" �� �ݾ��� 10�ڸ� �̸����� �Է��� �ּ���.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// Y, N validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationYN(String ynCheck) {
		boolean result = true;
		if (!ynCheck.matches("^[yn]$")) { // y, n �ҹ��ڸ� �Է�. ���� �Ұ�.
			System.out.println(" �� y, n �ҹ��ڸ� �Է°����մϴ�. ���� �Ұ��� �մϴ�.");
			result = false;
		} else if (ynCheck.length() > 1) { // �̸��� 2���� �̸��̶�� �Է� �Ұ�
			System.out.println(" �� 1���ڸ� �Է��� �ּ���.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// Y, N �빮�� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationYNtoUpperCase(String ynCheck) {
		boolean result = true;
		if (!ynCheck.matches("^[YN]$")) { // Y, N �빮�ڸ� �Է�. ���� �Ұ�.
			System.out.println(" �� Y, N �빮�ڸ� �Է°����մϴ�. ���� �Ұ��� �մϴ�.");
			result = false;
		} else if (ynCheck.length() > 1) { // �̸��� 2���� �̸��̶�� �Է� �Ұ�
			System.out.println(" �� 1���ڸ� �Է��� �ּ���.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// �ڵ� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationCode(String code) {
		boolean result = true;
		if (code.length() > 12) {
			System.out.println(" �� �ڵ�� 12�ڸ� �����Դϴ�.");
			result = false;
		} else {
			if (code.matches("^[A-Z0-9]*$")) { // ���� �빮�ڿ� ���ڸ� �Է� ����. ���� ��� ����.
				if (code.matches("^[A-Z]*$")) { // ���� �빮�ڸ� �� ���
					System.out.println(" �� ���ڸ� �ʼ��� �Է��ؾ� �մϴ�.");
					result = false;
				} else if (code.matches("^[0-9]*$")) { // ���ڸ� �� ���
					System.out.println(" �� ���� �빮�ڸ� �ʼ��� �Է��ؾ� �մϴ�.");
					result = false;
				}
			} else {
				System.out.println(" �� ���� �빮�ڤ����ڸ� �Է°��� �մϴ�.");
				result = false;
			}
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// �޴��� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationMenuName(String menuname) {
		boolean result = true;
		if (!menuname.matches("^[\\s��-�R]*$")) { // �ѱ۸� �Է� ����. ���� ���
			System.out.println(" �� �ѱ۸� �Է°��� �մϴ�. ���� ���.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// �޴�����, ��޺� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationPrice(long price) {
		boolean result = true;
		long cnt = price;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 10) { // �ݾ� ���ڰ� 10�ڸ� �̸��̶��
			System.out.println(" �� �ݾ��� 10�ڸ� �̸����� �Է��� �ּ���.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// �޴����� validation
	// true : validation �Ϸ� | false : �ٽ� �Է�
	public static boolean validationMenuRemainQty(int remainqty) {
		boolean result = true;
		long cnt = remainqty;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 9) { // �ݾ� ���ڰ� 9�ڸ� �̸��̶��
			System.out.println(" �� ������ 9�ڸ� �̸����� �Է��� �ּ���.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
	// ȸ�� ���� ����
	public static boolean validationMemLv(int lv) {
		boolean result = true;
		long cnt = lv;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 1) { // ���� ���ڰ� 1�ڸ� ���϶��
			System.out.println(" �� ������ 1�ڸ� �������� �Է��� �ּ���.");
			result = false;
		}
		if (result) return result; // �Է� ������ �����ٸ� true ���� ��ٷ� ��ȯ
		System.out.println();
		System.out.println(" �� �ٽ� �Է��ϼ���.");
		System.out.println();
		return result;
	}
	
}
