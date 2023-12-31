package firstproject.service;

import java.util.ArrayList;
import java.util.List;

import firstproject.dao.AdminUpdateDAO;
import firstproject.util.EnterUtil;
import firstproject.util.GapUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class AdminUpdateService {

	private static AdminUpdateService instance = null;

	private AdminUpdateService() {
	}

	public static AdminUpdateService getInstance() {
		if (instance == null)
			instance = new AdminUpdateService();
		return instance;
	}

	AdminUpdateDAO adminUpdateDAO = AdminUpdateDAO.getInstance();
	GapUtil gapUtil = GapUtil.getInstance();

	public int memUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE MEMBER SET ";
		int flag = 0; // 熱薑 罹睡 �挫�
		String yesOrNot = ""; // y, n 殮溘

		String memId = ""; // �蛾蠷ぜ拑�
		String memNm = ""; // �蛾衋抶�
		String memPw = ""; // �蛾羉髀邿醽�
		String memAdd = ""; // �蛾讔祤�
		String memTel = ""; // �蛾蠵炮藽�
		long balance = 0; // 濤罹旎擋
		int memlv = 0; // �蛾禶劃�

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 滲唳й �蛾� 嬴檜蛤蒂 殮溘ж撮蹂. ");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			memId = ScanUtil.nextLine();
			if (ValidationUtil.validationID(memId)) {
				param.add(memId);
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾籪簎� 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й �蛾籪� 殮溘 [ и旋 殮溘 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				memNm = ScanUtil.nextLine();
				if (ValidationUtil.validationName(memNm)) {
					sql += "MEMNM = '" + memNm + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾� 綠塵廓�ㄧ� 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 綠塵廓�� 殮溘 [ 艙僥王璋濠 в熱 / 6旋濠 檜鼻 殮溘 ] ");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				memPw = ScanUtil.nextLine();
				if (ValidationUtil.validationPW(memPw)) {
					sql += "MEMPW = '" + memPw + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾� 輿模蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 輿模 殮溘 [ и旋王璋濠 殮溘 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				memAdd = ScanUtil.nextLine();
				if (ValidationUtil.validationAddress(memAdd)) {
					sql += "MEMADD = '" + memAdd + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾� 翱塊籀蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й �蛾� 翱塊籀 殮溘[ 0XX-XXXX-XXXX ⑽衝戲煎 殮溘 ] ");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				memTel = ScanUtil.nextLine();
				if (ValidationUtil.validationTEL(memTel)) {
					sql += "MEMTEL = '" + memTel + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 濤罹旎擋擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 濤罹旎擋 殮溘[ 璋濠朝 10濠葬 檜ж煎 殮溘 ] ");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				balance = ScanUtil.nextLongLine();
				if (ValidationUtil.validationBalance(balance)) {
					sql += "BALANCE = " + balance + ", ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾� 溯漣擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("�蛾� 溯漣 (0 ~ 9) ");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				memlv = ScanUtil.nextIntegerLine();
				if (ValidationUtil.validationMemLv(memlv)) {
					sql += "MEMLV = " + memlv + ", ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE MEMID = ? ";

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾� 薑爾蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" �蛾� 熱薑檜 諫猿腎歷蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" �蛾� 熱薑檜 褒ぬц蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" �蛾� 薑爾 婦葬 む檜雖煎 檜翕м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_MEM_MANAGEMENT;
	}

	public int storeUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE STORE SET ";
		int flag = 0; // 熱薑 罹睡 �挫�
		String yesOrNot = ""; // y, n 殮溘

		String storeCode = ""; // 機羹囀萄
		String storeNm = ""; // 機羹貲
		String storeAdd = ""; // 機羹輿模
		long minOrder = 0; // 譆模輿僥旎擋
		String deliYn = ""; // 寡殖陛棟嶸鼠
		String packYn = ""; // ん濰陛棟嶸鼠
		String closeYn = ""; // 衙濰螃Ъ嶸鼠

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 滲唳й 機羹囀萄蒂 殮溘ж撮蹂. ( ex. AGHS01 ) ");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			storeCode = ScanUtil.nextLine();
			if (ValidationUtil.validationCode(storeCode)) {
				param.add(storeCode);
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 機羹貲擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 機羹貲 殮溘 [ и旋, 璋濠, 嗥橫噙晦 殮溘 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				storeNm = ScanUtil.nextLine();
				if (ValidationUtil.validationStoreNM(storeNm)) {
					sql += "STONM = '" + storeNm + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 機羹輿模蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 機羹輿模 殮溘 [ и旋, 璋濠, 嗥橫噙晦 殮溘 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				storeAdd = ScanUtil.nextLine();
				if (ValidationUtil.validationStoreAddress(storeAdd)) {
					sql += "STOADD = '" + storeAdd + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 譆模輿僥旎擋擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 譆模輿僥旎擋 殮溘 [ 0 ~ 9999999999 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				minOrder = ScanUtil.nextLongLine();
				if (ValidationUtil.validationMinOrder(minOrder)) {
					sql += "MINORDER = '" + minOrder + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 寡殖陛棟嶸鼠蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 寡殖 陛棟 : Y / 寡殖 碳陛 : N 擊 殮溘ж撮蹂. 嗥橫噙晦 碳陛.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				deliYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(deliYn)) {
					sql += "DELIYN = '" + deliYn + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" ん濰陛棟嶸鼠蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * ん濰 陛棟 : Y / ん濰 碳陛 : N 擊 殮溘ж撮蹂. 嗥橫噙晦 碳陛.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				packYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(packYn)) {
					sql += "PACKYN = '" + packYn + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 衙濰螃Ъ嶸鼠蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 衙濰 螃Ъ : Y / 衙濰 殘晦 : N 擊 殮溘ж撮蹂. 嗥橫噙晦 碳陛.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				closeYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(closeYn)) {
					sql += "CLOSEYN = '" + closeYn + "', ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE STOCODE = ? ";

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 陛啪 薑爾蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 陛啪 熱薑檜 諫猿腎歷蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 陛啪 熱薑檜 褒ぬц蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 陛啪 薑爾 婦葬 む檜雖煎 檜翕м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_STORE_MANAGEMENT;
	}

	public int menuUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE MENU SET ";
		int flag = 0; // 熱薑 罹睡 �挫�
		String yesOrNot = ""; // y, n 殮溘

		String menuCode = ""; // 詭景囀萄
		String menuNm = ""; // 詭景貲
		long menuPrice = 0; // 詭景陛問
		int remainQty = 0; // 濤罹熱榆

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 滲唳й 詭景囀萄蒂 殮溘ж撮蹂. ( ex. YGSIG112 ) ");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			menuCode = ScanUtil.nextLine();
			if (ValidationUtil.validationCode(menuCode)) {
				param.add(menuCode);
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 詭景貲擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 詭景貲 殮溘 [ и旋, 嗥橫噙晦 殮溘 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				menuNm = ScanUtil.nextLine();
				if (ValidationUtil.validationMenuName(menuNm)) {
					sql += "MENUNM = '" + menuNm + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 詭景陛問擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 詭景陛問 殮溘 [ 0 ~ 9999999999 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				menuPrice = ScanUtil.nextLongLine();
				if (ValidationUtil.validationPrice(menuPrice)) {
					sql += "MENUPRICE = '" + menuPrice + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 詭景熱榆擊 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 詭景熱榆 殮溘 [ 0 ~ 999999999 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				remainQty = ScanUtil.nextIntegerLine();
				if (ValidationUtil.validationMenuRemainQty(remainQty)) {
					sql += "REMAINQTY = '" + remainQty + "', ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE MENUCODE = ? ";

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 詭景 薑爾蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 詭景 熱薑檜 諫猿腎歷蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 詭景 熱薑檜 褒ぬц蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 詭景 薑爾 婦葬 む檜雖煎 檜翕м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_MENU_MANAGEMENT;
	}

	public int riderUpdate() {
		List<Object> param = new ArrayList<Object>();
		String sql = " UPDATE RIDER SET ";
		int flag = 0; // 熱薑 罹睡 �挫�
		String yesOrNot = ""; // y, n 殮溘

		String riderCode = ""; // 塭檜渦囀萄
		String abseYn = ""; // 睡營嶸鼠
		long deliCost = 0; // 寡殖綠
		String stoCode = ""; // 機羹囀萄

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 滲唳й 塭檜渦囀萄蒂 殮溘ж撮蹂. ( ex. YGOUT44 ) ");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			riderCode = ScanUtil.nextLine();
			if (ValidationUtil.validationCode(riderCode)) {
				param.add(riderCode);
				break;
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 塭檜渦 睡營 嶸鼠蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 塭檜渦 �做� 陛棟 : Y / 塭檜渦 �做� 碳陛棟 : N 擊 殮溘ж撮蹂. 嗥橫噙晦 碳陛.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				abseYn = ScanUtil.nextLine();
				if (ValidationUtil.validationYNtoUpperCase(abseYn)) {
					sql += "ABSEYN = '" + abseYn + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 寡殖綠蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 寡殖綠 殮溘 [ 0 ~ 9999999999 ]");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				deliCost = ScanUtil.nextLongLine();
				if (ValidationUtil.validationPrice(deliCost)) {
					sql += "DELICOST = '" + deliCost + "', ";
					break;
				}
			}
		}

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 輿 剪楚 機羹蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			while (true) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" * 滲唳й 機羹囀萄 殮溘 ( ex. AGHS01 )");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				stoCode = ScanUtil.nextLine();
				if (ValidationUtil.validationCode(stoCode)) {
					sql += "STOCODE = '" + stoCode + "', ";
					break;
				}
			}
		}

		int sqllen = sql.length();
		sql = sql.substring(0, sqllen - 2); // (endindex-1)-1[,]
		sql += " WHERE RIDCODE = ? ";

		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 塭檜渦 薑爾蒂 滲唳ж衛啊蝗棲梱? (y/n)");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			yesOrNot = ScanUtil.nextLine();
			if (ValidationUtil.validationYN(yesOrNot))
				break;
		}

		if (yesOrNot.equals("y")) {
			flag = adminUpdateDAO.update(sql, param);
			if (flag != 0) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 塭檜渦 熱薑檜 諫猿腎歷蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			} else {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 塭檜渦 熱薑檜 褒ぬц蝗棲棻.");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
			}
		} else if (yesOrNot.equals("n")) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 塭檜渦 薑爾 婦葬 む檜雖煎 檜翕м棲棻.");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			EnterUtil.enterNext(2);
		}

		return View.ADMIN_RIDER_MANAGEMENT;
	}

}
