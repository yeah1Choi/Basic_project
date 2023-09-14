package firstproject.util;

import firstproject.dao.MemberDAO;

public class ValidationUtil {
	
	// ½Ì±ÛÅæ ÆĞÅÏ
	private static ValidationUtil instance = null;
	private ValidationUtil() {}
	public static ValidationUtil getInstance() {
		if (instance == null) instance = new ValidationUtil();
		return instance;
	}

	// https://coding-factory.tistory.com/819
	// Á¤±ÔÇ¥Çö½Ä °ËÁõ »çÀÌÆ® ¸ğÀ½
	
	// È¸¿ø ¾ÆÀÌµğ validation 
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationID(String memID) {
		boolean result = true;
		if (memID.length() < 6) {
			System.out.println(" ¡Ø ¾ÆÀÌµğ´Â 6ÀÚ¸® ÀÌ»óÀÔ´Ï´Ù.");
			result = false;
		} else {
			if (memID.matches("^[a-zA-Z0-9]*$")) { // ¿µ¾î¿Í ¼ıÀÚ¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë ¾ÈÇÔ.
				if (memID.matches("^[a-zA-Z]*$")) { // ¿µ¾î¸¸ ¾´ °æ¿ì
					System.out.println(" ¡Ø ¼ıÀÚ¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
					result = false;
				} else if (memID.matches("^[0-9]*$")) { // ¼ıÀÚ¸¸ ¾´ °æ¿ì
					System.out.println(" ¡Ø ¿µ¾î¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
					result = false;
				}
			} else {
				System.out.println(" ¡Ø ¿µ¹®¤ı¼ıÀÚ¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
				result = false;
			}
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// È¸¿ø ºñ¹Ğ¹øÈ£ validation 
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationPW(String memPW) {
		boolean result = true;
		if (memPW.length() < 6) {
			System.out.println(" ¡Ø ºñ¹Ğ¹øÈ£´Â 6ÀÚ¸® ÀÌ»óÀÔ´Ï´Ù.");
			result = false;
		} else {
			if (memPW.matches("^[a-zA-Z0-9]*$")) { // ¿µ¾î¿Í ¼ıÀÚ¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë ¾ÈÇÔ.
				if (memPW.matches("^[a-zA-Z]*$")) { // ¿µ¾î¸¸ ¾´ °æ¿ì
					System.out.println(" ¡Ø ¼ıÀÚ¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
					result = false;
				} else if (memPW.matches("^[0-9]*$")) { // ¼ıÀÚ¸¸ ¾´ °æ¿ì
					System.out.println(" ¡Ø ¿µ¾î¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
					result = false;
				}
			} else {
				System.out.println(" ¡Ø ¿µ¹®¤ı¼ıÀÚ¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
				result = false;
			}
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// È¸¿ø ÀÌ¸§ validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationName(String memNM) {
		boolean result = true;
		if (!memNM.matches("^[°¡-ÆR]*$")) { // ÇÑ±Û¸¸ ÀÔ·Â. ¶ç¾î¾²±â ºÒ°¡.
			System.out.println(" ¡Ø ÇÑ±Û¸¸ ÀÔ·Â°¡´ÉÇÕ´Ï´Ù. ¶ç¾î¾²±â ºÒ°¡´É ÇÕ´Ï´Ù.");
			result = false;
		} else return result;
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// È¸¿ø ÁÖ¼Ò validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationAddress(String memADD) {
		boolean result = true;
		if (!memADD.matches("^[0-9/\\s°¡-ÆR]*$")) { // ÇÑ±Û°ú ¼ıÀÚ¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë
			System.out.println(" ¡Ø ÇÑ±Û¤ı¼ıÀÚ¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ¿¬¶ôÃ³ validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	// ^\d{2,3}-\d{3,4}-\d{4}$
	public static boolean validationTEL(String memTEL) {
		boolean result = true;
		if(!memTEL.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) { // 010-0000-0000 Çü½Ä¸¸ ÀÔ·Â °¡´É
			System.out.println(" ¡Ø 0XX-0000-0000 Çü½Ä¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ÀÜ¿©±İ¾× validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationBalance(long balance) {
		boolean result = true;
		long cnt = balance;
		int len = (int)(Math.log10(cnt)+1);
//			System.out.println(len);
		if(len > 10) { // ±İ¾× ¼ıÀÚ°¡ 10ÀÚ¸® ¹Ì¸¸ÀÌ¶ó¸é
			System.out.println(" ¡Ø ±İ¾×À» 10ÀÚ¸® ¹Ì¸¸À¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ¾÷Ã¼¸í validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationStoreNM(String StoreNM) {
		boolean result = true;
		if (!StoreNM.matches("^[0-9/\\s°¡-ÆR]*$")) { // ÇÑ±Û°ú ¼ıÀÚ¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë
			System.out.println(" ¡Ø ÇÑ±Û¤ı¼ıÀÚ¤ı¶ç¾î¾²±â¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ¾÷Ã¼ ÁÖ¼Ò validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationStoreAddress(String StoreAdd) {
		boolean result = true;
		if (!StoreAdd.matches("^[0-9/\\s°¡-ÆR]*$")) { // ÇÑ±Û°ú ¼ıÀÚ¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë
			System.out.println(" ¡Ø ÇÑ±Û¤ı¼ıÀÚ¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ÃÖ¼ÒÁÖ¹®±İ¾× validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationMinOrder(long MinOrder) {
		boolean result = true;
		long cnt = MinOrder;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 10) { // ±İ¾× ¼ıÀÚ°¡ 10ÀÚ¸® ¹Ì¸¸ÀÌ¶ó¸é
			System.out.println(" ¡Ø ±İ¾×À» 10ÀÚ¸® ¹Ì¸¸À¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// Y, N validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationYN(String ynCheck) {
		boolean result = true;
		if (!ynCheck.matches("^[yn]$")) { // y, n ¼Ò¹®ÀÚ¸¸ ÀÔ·Â. ¶ç¾î¾²±â ºÒ°¡.
			System.out.println(" ¡Ø y, n ¼Ò¹®ÀÚ¸¸ ÀÔ·Â°¡´ÉÇÕ´Ï´Ù. ¶ç¾î¾²±â ºÒ°¡´É ÇÕ´Ï´Ù.");
			result = false;
		} else if (ynCheck.length() > 1) { // ÀÌ¸§ÀÌ 2±ÛÀÚ ¹Ì¸¸ÀÌ¶ó¸é ÀÔ·Â ºÒ°¡
			System.out.println(" ¡Ø 1±ÛÀÚ¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// Y, N ´ë¹®ÀÚ validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationYNtoUpperCase(String ynCheck) {
		boolean result = true;
		if (!ynCheck.matches("^[YN]$")) { // Y, N ´ë¹®ÀÚ¸¸ ÀÔ·Â. ¶ç¾î¾²±â ºÒ°¡.
			System.out.println(" ¡Ø Y, N ´ë¹®ÀÚ¸¸ ÀÔ·Â°¡´ÉÇÕ´Ï´Ù. ¶ç¾î¾²±â ºÒ°¡´É ÇÕ´Ï´Ù.");
			result = false;
		} else if (ynCheck.length() > 1) { // ÀÌ¸§ÀÌ 2±ÛÀÚ ¹Ì¸¸ÀÌ¶ó¸é ÀÔ·Â ºÒ°¡
			System.out.println(" ¡Ø 1±ÛÀÚ¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ÄÚµå validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationCode(String code) {
		boolean result = true;
		if (code.length() > 12) {
			System.out.println(" ¡Ø ÄÚµå´Â 12ÀÚ¸® ÀÌÇÏÀÔ´Ï´Ù.");
			result = false;
		} else {
			if (code.matches("^[A-Z0-9]*$")) { // ¿µ¾î ´ë¹®ÀÚ¿Í ¼ıÀÚ¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë ¾ÈÇÔ.
				if (code.matches("^[A-Z]*$")) { // ¿µ¾î ´ë¹®ÀÚ¸¸ ¾´ °æ¿ì
					System.out.println(" ¡Ø ¼ıÀÚ¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
					result = false;
				} else if (code.matches("^[0-9]*$")) { // ¼ıÀÚ¸¸ ¾´ °æ¿ì
					System.out.println(" ¡Ø ¿µ¾î ´ë¹®ÀÚ¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
					result = false;
				}
			} else {
				System.out.println(" ¡Ø ¿µ¾î ´ë¹®ÀÚ¤ı¼ıÀÚ¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
				result = false;
			}
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ¸Ş´º¸í validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationMenuName(String menuname) {
		boolean result = true;
		if (!menuname.matches("^[\\s°¡-ÆR]*$")) { // ÇÑ±Û¸¸ ÀÔ·Â °¡´É. ¶ç¾î¾²±â Çã¿ë
			System.out.println(" ¡Ø ÇÑ±Û¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù. ¶ç¾î¾²±â Çã¿ë.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ¸Ş´º°¡°İ, ¹è´Şºñ validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationPrice(long price) {
		boolean result = true;
		long cnt = price;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 10) { // ±İ¾× ¼ıÀÚ°¡ 10ÀÚ¸® ¹Ì¸¸ÀÌ¶ó¸é
			System.out.println(" ¡Ø ±İ¾×À» 10ÀÚ¸® ¹Ì¸¸À¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// ¸Ş´º¼ö·® validation
	// true : validation ¿Ï·á | false : ´Ù½Ã ÀÔ·Â
	public static boolean validationMenuRemainQty(int remainqty) {
		boolean result = true;
		long cnt = remainqty;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 9) { // ±İ¾× ¼ıÀÚ°¡ 9ÀÚ¸® ¹Ì¸¸ÀÌ¶ó¸é
			System.out.println(" ¡Ø ¼ö·®À» 9ÀÚ¸® ¹Ì¸¸À¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
	// È¸¿ø ·¹º§ °ËÁõ
	public static boolean validationMemLv(int lv) {
		boolean result = true;
		long cnt = lv;
		int len = (int)(Math.log10(cnt)+1);
		if(len > 1) { // ·¹º§ ¼ıÀÚ°¡ 1ÀÚ¸® ÀÌÇÏ¶ó¸é
			System.out.println(" ¡Ø ·¹º§À» 1ÀÚ¸® ÀÌÇÏÀ¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			result = false;
		}
		if (result) return result; // ÀÔ·Â °ËÁõÀÌ ³¡³µ´Ù¸é true °ªÀ» °ğ¹Ù·Î ¹İÈ¯
		System.out.println();
		System.out.println(" ¡Ø ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
		System.out.println();
		return result;
	}
	
}
