package firstproject.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.controller.Controller;
import firstproject.dao.MemberDAO;
import firstproject.dao.SelectDAO;
import firstproject.util.EnterUtil;
import firstproject.util.NullCheckUtil;
import firstproject.util.ScanUtil;
import firstproject.util.ValidationUtil;
import firstproject.util.View;

public class MemberService {

	private static MemberService instance = null;

	private MemberService() {
	}

	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberService();
		return instance;
	}

	MemberDAO memberDAO = MemberDAO.getInstance();
	SelectDAO selectDAO = SelectDAO.getInstance();
	ValidationUtil validationUtil = ValidationUtil.getInstance();

	String str = "";

	public int login() {
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     煎斜檣 �飛�");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");

		System.out.print("   嬴檜蛤 >> ");
		String memID = ScanUtil.nextLine();
		List<Object> searchId = new ArrayList<>();
		searchId.add(memID);

		System.out.print("   綠塵廓�� >> ");
		String memPW = ScanUtil.nextLine();
		List<Object> searchPw = new ArrayList<>();
		searchPw.add(memPW);

		Map<String, Object> memberInfoId = memberDAO.loginId(searchId);
		Map<String, Object> memberInfoPw = memberDAO.loginPw(searchPw);

		if (memberInfoId == null) {
			System.out.println(" ≦ 橾纂ж朝 嬴檜蛤陛 橈蝗棲棻.");
			EnterUtil.enterNext(1);
			return View.HOME;
		} else if (memberInfoPw == null) {
			System.out.println(" ≦ 橾纂ж朝 綠塵廓�ㄟ� 橈蝗棲棻.");
			EnterUtil.enterNext(1);
			return View.HOME;
		} else {
			List<Object> param = new ArrayList<>();
			param.add(memID);
			param.add(memPW);
			Map<String, Object> memberInfo = memberDAO.login(param);

			// 煎斜檣и �蛾衋� 撮暮 薑爾 餵橫 厥晦
			Controller.sessionStorage.put("login", true);
			Controller.sessionStorage.put("loginInfo", memberInfo);

			if (Controller.isAdmin()) {
//				str = "[婦葬濠]椒 �紊腎桭炴�!";
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				str = "   [ " + memberInfo.get("MEMNM").toString() + " ]椒 �紊腎桭炴�!";
				System.out.println(str);
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
				return View.ADMIN_HOME;
			} else if (Controller.isMember()) {
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				str = "   [ " + memberInfo.get("MEMNM").toString() + " ]椒 �紊腎桭炴�!";
				System.out.println(str);
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				EnterUtil.enterNext(2);
				return View.ORDER_HOME;
			}
		}
		return 0;
	}

	// �蛾灠㊣� 詭憮萄
	public int signUp() {
		String memID = ""; // �蛾� 嬴檜蛤
		String memPW = ""; // �蛾� 綠塵廓��
		String memNM = ""; // �蛾� 檜葷
		String memADD = ""; // �蛾� 輿模
		String memTEL = ""; // �蛾� 翱塊籀
		long balance = 0; // 濤罹旎擋
		int memLv = 1; // �蛾� 溯漣

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [�蛾灠㊣偟");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println();

		while (true) {
			System.out.println(" * 嬴檜蛤 殮溘 [ 艙僥王璋濠 в熱 / 6旋濠 檜鼻 殮溘 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			memID = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationID(memID))
				break;
		}

		while (true) {
			System.out.println(" * 綠塵廓�� 殮溘 [ 艙僥王璋濠 в熱 / 6旋濠 檜鼻 殮溘 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			memPW = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationPW(memPW))
				break;
		}

		while (true) {
			System.out.println(" * 檜葷 殮溘 [ и旋 殮溘 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			memNM = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationName(memNM))
				break;
		}

		while (true) {
			System.out.println(" * 輿模 殮溘 [ и旋王璋濠 殮溘 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			memADD = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationAddress(memADD))
				break;
		}

		while (true) {
			System.out.println(" * 翱塊籀 殮溘[ 0XX-XXXX-XXXX ⑽衝戲煎 殮溘 ]");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			memTEL = ScanUtil.nextLine();
			System.out.println();
			if (ValidationUtil.validationTEL(memTEL))
				break;
		}

		while (true) {
			System.out.println(" * 旎擋擊 醱瞪ж衛啊蝗棲梱? (y/n) ");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			String chargeYN = ScanUtil.nextLine();
			if (chargeYN.equals("y")) {
				System.out.println(" * 旎擋 殮溘[ 璋濠朝 10濠葬 嘐虜戲煎 殮溘 ] ");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				balance = ScanUtil.nextLong();
				System.out.println();
			} else {
				balance = 0;
			}
			if (ValidationUtil.validationBalance(balance))
				break;
		}

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println(" * 殮溘ж褐 薑爾煎 �蛾灠㊣� ж衛啊蝗棲梱? (y/n)");
		System.out.print("   >> ");
		if (ScanUtil.nextLine().equals("y")) {
			Map<String, Object> result = memberDAO.isValID(memID);
			if (result != null) {
				System.out.println(" ≦ 檜嘐 蛔煙脹 嬴檜蛤 殮棲棻!");
			} else {
				List<Object> param = new ArrayList<>();
				param.add(memID);
				param.add(memNM);
				param.add(memPW);
				param.add(memADD);
				param.add(memLv);
				param.add(balance);
				param.add(memTEL);
				int isSuccess = memberDAO.signUp(param);
				if (isSuccess > 0) {
					str = "[" + memID + "]椒 陛殮擊 �紊腎桭炴�!";
					System.out.println(str);
				} else
					System.out.println(" ≦ �蛾灠㊣� 褒ぬ!");
			}
		} else {
			System.out.println(" * �蛾灠㊣埬� 鏃模м棲棻.");
		}

		EnterUtil.enterNext(1);
		return View.HOME;
	}

	// 嬴檜蛤/綠塵廓�� 瓊晦 詭憮萄
	public int find() {
		boolean result = false;

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [ 嬴檜蛤 / 綠塵廓�� 瓊晦 ]");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println();
		System.out.println(" 1. 嬴檜蛤 瓊晦");
		System.out.println(" 2. 綠塵廓�� 瓊晦");
		System.out.println(" 0. 檜瞪 詭景");
		System.out.println();
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print("   >> ");

		switch (ScanUtil.nextInt()) {
		case 1:
			result = findID();
			break;
		case 2:
			result = findPW();
			break;
		case 0:
			return View.HOME;
		default:
			System.out.println();
			System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻.");
			System.out.println();
			return View.MEMBER_ID_PW_FIND;
		}

		if (result) {
			return View.HOME;
		} else {
			return View.MEMBER_ID_PW_FIND;
		}

	}

	// 嬴檜蛤 瓊晦 詭憮萄
	private boolean findID() {
		boolean result = true;
		String userNM = "";
		String userTEL = "";

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [嬴檜蛤 瓊晦]");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println();

		while (true) {
			System.out.println(" * 檜葷擊 殮溘ж撮蹂.");
			System.out.println();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			userNM = ScanUtil.nextLine();
			if (ValidationUtil.validationName(userNM))
				break;
		}

		while (true) {
			System.out.println(" * 瞪�食醽ㄧ� 殮溘ж撮蹂.");
			System.out.println();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			userTEL = ScanUtil.nextLine();
			if (ValidationUtil.validationTEL(userTEL))
				break;
		}

		List<Object> param = new ArrayList<>();
		param.add(userNM);
		param.add(userTEL);
		Map<String, Object> selectMem = memberDAO.findID(param);

		if (selectMem == null) {
			System.out.println(" ≦ 殮溘и 檜葷婁 瞪�食醽ㄧ� 陛霞 �蛾讔內萼� 橈蝗棲棻.");
			result = false;
		} else {
			str = " п渡 薑爾煎 褻�葭� 嬴檜蛤朝 [" + selectMem.get("MEMID") + "] 殮棲棻.";
			System.out.println(str);
			result = true;
		}

		EnterUtil.enterNext(1);
		return result;
	}

	// 綠塵廓�� 瓊晦 詭憮萄
	private boolean findPW() {
		boolean result = true;
		String userID = "";
		String userTEL = "";

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [綠塵廓�� 瓊晦]");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println();

		while (true) {
			System.out.println(" * �蛾� ID蒂 殮溘ж撮蹂.");
			System.out.println();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			userID = ScanUtil.nextLine();
			if (ValidationUtil.validationID(userID))
				break;
		}

		while (true) {
			System.out.println(" * 瞪�食醽ㄧ� 殮溘ж撮蹂.");
			System.out.println();
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("   >> ");
			userTEL = ScanUtil.nextLine();
			if (ValidationUtil.validationTEL(userTEL))
				break;
		}

		List<Object> param = new ArrayList<>();
		param.add(userID);
		param.add(userTEL);
		Map<String, Object> selectMem = memberDAO.findPW(param);

		if (selectMem == null) {
			System.out.println(" ≦ 殮溘и �蛾醼D諦 瞪�食醽ㄧ� 陛霞 �蛾讔內萼� 橈蝗棲棻.");
			result = false;
		} else {
			str = " п渡 薑爾煎 褻�葭� 綠塵廓�ㄣ� [" + selectMem.get("MEMPW") + "] 殮棲棻.";
			System.out.println(str);
			result = true;
		}

		EnterUtil.enterNext(1);
		return result;
	}

	// ん檣お 醱瞪 憮綠蝶 詭模萄
	public int pointDeposit() {

		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;

		String userID = (String) userInfo.get("MEMID");
		String userNM = (String) userInfo.get("MEMNM");
		String userPW = (String) userInfo.get("MEMPW");

		Map<String, Object> balanceOne = selectDAO.BalanceOne(userID);
		BigDecimal balanceBigDecimal = (BigDecimal) balanceOne.get("BALANCE");
		long balance = balanceBigDecimal.longValue();

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     [ん檣お 醱瞪]");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("   [" + userID + "]椒曖 ⑷營 餌辨陛棟и 濤堅 :");
		System.out.println("    ** " + balance + "錳 **");

		while (true) {
			System.out.println(" 1. 醱瞪ж晦");
			System.out.println(" 2. 菴煎陛晦");
			System.out.print("   殮溘  >> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				System.out.println(" 綠塵廓�ㄧ� 殮溘п 檣隸п輿褊衛螃.");
				System.out.print(" 綠塵廓�� 殮溘 >> ");
				String matchPW = ScanUtil.nextLine();

				if (matchPW.equals(userPW)) {
					System.out.println(" [" + userNM + "]椒 馬餌м棲棻.");
					System.out.println(" 橡葆蒂 醱瞪ж衛啊蝗棲梱?");
					System.out.print(" 旎擋 [ 璋濠朝 10濠葬 嘐虜戲煎 殮溘 ]  >> ");

					long addPoint = ScanUtil.nextLong();

					while (true) {
						if (addPoint > 999999999) {
							System.out.println(" 褻勒縑 蜃啪 棻衛 殮溘ж撮蹂.");
							return View.POINT;
						} else {
							System.out.println(" " + addPoint + "錳擊 醱瞪м棲棻.");
							balance = balance + addPoint;
							List<Object> param = new ArrayList<>();
							param.add(balance);
							param.add(userID);
							memberDAO.depositPoint(param);
							break;
						}
					}

					System.out.println(" 縛攪蒂 援腦賊 濰夥掘棲煎 給嬴骨棲棻.");
					System.out.println(" 濰夥掘棲縑憮 唸薯蒂 諫猿п輿撮蹂.");
					EnterUtil.enterNext(2);
					return View.CART;

				} else {
					System.out.println(" ≦ ぎ萼 綠塵廓�� 殮棲棻. 棻衛 殮溘п輿撮蹂.");
					break;
				}
			case 2:
				return View.ORDER_HOME;

			default:
				System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛 殮溘п輿撮蹂.");
			}
		}
	}

	// 葆檜む檜雖 : 輿僥頂羲む檜雖
	public int userPage() {

		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("     ** 葆檜 む檜雖 **");
		System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("    ** 輿僥頂羲葬蝶お : **");

		// 煎斜檣脹 嶸盪曖 薑爾蒂 撮暮戲煎睡攪 陛螳褥 - 斜 醞縑 蘭葬蒂 嬪и 嬴檜蛤薑爾蒂 賒
		Object obj = Controller.sessionStorage.get("loginInfo");
		Map<String, Object> userInfo = (Map<String, Object>) obj;
		String userID = (String) userInfo.get("MEMID");

		// 嬴檜蛤薑爾蒂 檜辨п 輿僥囀萄蒂 陛螳褥
		List<Map<String, Object>> orderCodeList = memberDAO.orderCodeSearch(userID);
//		System.out.println("orderCodeList : " + orderCodeList);

		// 輿僥頂羲 割 螃盟 籀葬
		if (NullCheckUtil.isEmpty(orderCodeList)) {
			System.out.println(" ≦ 輿僥ж褐 頂羲檜 橈蝗棲棻.");
			System.out.println(" ***************************");
		} else {

			// む檜癒 晦棟
			int pageNumber = 1; // ⑷營 む檜雖 廓��
			int pageSize = 5; // む檜雖渡 ル衛й 等檜攪 熱

			int startIndex = (pageNumber - 1) * pageSize; // 衛濛 檣策蝶
			int endIndex = Math.min(startIndex + pageSize, orderCodeList.size()); // 部 檣策蝶

			int pageRowCnt = orderCodeList.size(); // 煎辦 偃熱
			int totalPageSize = pageRowCnt / pageSize; // 識 む檜雖 偃熱

			if (pageRowCnt % pageSize != 0) { // 棻擠 む檜雖梱雖 煎辦陛 陴擠
				totalPageSize++;
			}

			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println(" 1. む檜雖 跡煙 爾晦");
			System.out.println(" 2. 輿僥囀萄 匐儀");
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print(" >> ");
			int choice = ScanUtil.nextInt();
			if (choice == 1) { // む檜癒 籀葬
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" и む檜雖縑 賃 偃曖 等檜攪蒂 爾衛啊蝗棲梱?");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" >> ");
				pageSize = ScanUtil.nextInt();
				startIndex = (pageNumber - 1) * pageSize;
				endIndex = Math.min(startIndex + pageSize, orderCodeList.size());
				totalPageSize = pageRowCnt / pageSize; // 識 む檜雖 偃熱
				if (pageRowCnt % pageSize != 0) { // 棻擠 む檜雖梱雖 煎辦陛 陴擠
					totalPageSize++;
				}
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println(" 識 輿僥 熱 " + pageRowCnt + " 勒  : " + pageNumber + " / " + totalPageSize + " む檜雖");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				for (int i = startIndex; i < endIndex; i++) {
					long sum = 0;
					long countSum = 0;
					long countDeilSum = 0;
					// 嬴檜蛤薑爾蒂 檜辨п 輿僥頂羲 纔檜綰 陛螳褥
					Map<String, Object> orderCode = orderCodeList.get(i);
					List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
							String.valueOf(orderCode.get("ORDERCODE")));
					// 輿僥�蝦鶹� 評塭 蘊葆棻 釭散 輿僥薑爾 爾罹邀
					System.out.println(" ==========================================================================");
					System.out.println(" 輿僥囀萄 : " + orderInfoList.get(0).get("ORDERCODE")); // 陳瞼⑽衝 雖薑п 轎溘ж晦
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println(" 輿僥陳瞼 : " + orderInfoList.get(0).get("ORDERDATE"));
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					for (Map<String, Object> orderItem : orderInfoList) {
						System.out.print(" 詭景貲 : " + orderItem.get("MENUNM") + " | "); // 醞犒
						System.out.print(" 輿僥熱榆 : " + orderItem.get("ORDERQTY") + "偃" + " | "); // 醞犒
						System.out.println(" 詭景滌 旎擋 : " + orderItem.get("TOTALPRICE") + "錳"); // 醞犒
						countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
						sum += countSum;
					}
					if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
						countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
						sum += countDeilSum;
						System.out.println(" 寡殖綠 : " + orderInfoList.get(0).get("DELICOST") + "錳");
						System.out.println(" 寡殖/ん濰 : 寡殖");
					} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
						System.out.println(" 寡殖/ん濰 : ん濰");
					}
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.println(" 識 旎擋 : " + sum + "錳");
					System.out.println(" 寡殖蕨鼻衛除 : " + orderInfoList.get(0).get("ORDERETA") + "碟");
					System.out.println(" 輿僥濠貲 : " + orderInfoList.get(0).get("MEMNM"));
					System.out.println(" 輿僥濠輿模 : " + orderInfoList.get(0).get("MEMADD"));
					System.out.println(" 唸薯罹睡 : " + orderInfoList.get(0).get("SELYN"));
					System.out.println(" ==========================================================================");
				}
				while (true) {
					System.out.println(" 1. 錳ж朝 む檜雖 爾晦");
					System.out.println(" 0. 檜瞪 �飛�");
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
					System.out.print(" >> ");
					int pageListSelect = ScanUtil.nextInt();
					if (pageListSelect == 1) {
						System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						System.out.println(" 賃 む檜雖蒂 爾衛啊蝗棲梱?");
						System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						System.out.print(" >> ");
						pageNumber = ScanUtil.nextInt();
						if (pageNumber > totalPageSize || pageNumber <= 0) {
							System.out.println(
									" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
							System.out.println(
									" 識 輿僥 熱 " + pageRowCnt + " 勒  : " + pageNumber + " / " + totalPageSize + " む檜雖");
							System.out.println(
									" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
							System.out.println("п渡 む檜雖朝 薑爾陛 橈蝗棲棻.");
						} else {
							startIndex = (pageNumber - 1) * pageSize;
							endIndex = Math.min(startIndex + pageSize, orderCodeList.size());
							System.out.println(
									" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
							System.out.println(
									" 識 輿僥 熱 " + pageRowCnt + " 勒  : " + pageNumber + " / " + totalPageSize + " む檜雖");
							System.out.println(
									" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
							for (int i = startIndex; i < endIndex; i++) {
								long sum = 0;
								long countSum = 0;
								long countDeilSum = 0;
								// 嬴檜蛤薑爾蒂 檜辨п 輿僥頂羲 纔檜綰 陛螳褥
								Map<String, Object> orderCode = orderCodeList.get(i);
								List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
										String.valueOf(orderCode.get("ORDERCODE")));
								// 輿僥�蝦鶹� 評塭 蘊葆棻 釭散 輿僥薑爾 爾罹邀
								System.out.println(
										" ==========================================================================");
								System.out.println(" 輿僥囀萄 : " + orderInfoList.get(0).get("ORDERCODE")); // 陳瞼⑽衝 雖薑п 轎溘ж晦
								System.out.println(
										" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
								System.out.println(" 輿僥陳瞼 : " + orderInfoList.get(0).get("ORDERDATE"));
								System.out.println(
										" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
								for (Map<String, Object> orderItem : orderInfoList) {
									System.out.print(" 詭景貲 : " + orderItem.get("MENUNM") + " | "); // 醞犒
									System.out.print(" 輿僥熱榆 : " + orderItem.get("ORDERQTY") + "偃" + " | "); // 醞犒
									System.out.println(" 詭景滌 旎擋 : " + orderItem.get("TOTALPRICE") + "錳"); // 醞犒
									countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
									sum += countSum;
								}
								if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
									countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
									sum += countDeilSum;
									System.out.println(" 寡殖綠 : " + orderInfoList.get(0).get("DELICOST") + "錳");
									System.out.println(" 寡殖/ん濰 : 寡殖");
								} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
									System.out.println(" 寡殖/ん濰 : ん濰");
								}
								System.out.println(
										" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
								System.out.println(" 識 旎擋 : " + sum + "錳");
								System.out.println(" 寡殖蕨鼻衛除 : " + orderInfoList.get(0).get("ORDERETA") + "碟");
								System.out.println(" 輿僥濠貲 : " + orderInfoList.get(0).get("MEMNM"));
								System.out.println(" 輿僥濠輿模 : " + orderInfoList.get(0).get("MEMADD"));
								System.out.println(" 唸薯罹睡 : " + orderInfoList.get(0).get("SELYN"));
								System.out.println(
										" ==========================================================================");
							}
						}
					} else if (pageListSelect == 0) {
						return View.ORDER_HOME;
					}
				}
			} else if (choice == 2) { // 睡碟 匐儀
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				for (Map<String, Object> orderCode : orderCodeList) {
					System.out.println(" 輿僥囀萄 : " + orderCode.get("ORDERCODE"));
				}
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" 輿僥囀萄 殮溘 >> ");
				String memIDstr = ScanUtil.nextLine();
				for (Map<String, Object> orderCode : orderCodeList) {
					if (memIDstr.equals(orderCode.get("ORDERCODE"))) {
						long sum = 0;
						long countSum = 0;
						long countDeilSum = 0;
						// 嬴檜蛤薑爾蒂 檜辨п 輿僥頂羲 纔檜綰 陛螳褥
						List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID,
								String.valueOf(orderCode.get("ORDERCODE")));
						// 輿僥�蝦鶹� 評塭 蘊葆棻 釭散 輿僥薑爾 爾罹邀
						System.out
								.println(" ==========================================================================");
						System.out.println(" 輿僥囀萄 : " + orderInfoList.get(0).get("ORDERCODE")); // 陳瞼⑽衝 雖薑п 轎溘ж晦
						System.out
								.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						System.out.println(" 輿僥陳瞼 : " + orderInfoList.get(0).get("ORDERDATE"));
						System.out
								.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						for (Map<String, Object> orderItem : orderInfoList) {
							System.out.print(" 詭景貲 : " + orderItem.get("MENUNM") + " | "); // 醞犒
							System.out.print(" 輿僥熱榆 : " + orderItem.get("ORDERQTY") + "偃" + " | "); // 醞犒
							System.out.println(" 詭景滌 旎擋 : " + orderItem.get("TOTALPRICE") + "錳"); // 醞犒
							countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
							sum += countSum;
						}
						if (orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
							countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
							sum += countDeilSum;
							System.out.println(" 寡殖綠 : " + orderInfoList.get(0).get("DELICOST") + "錳");
							System.out.println(" 寡殖/ん濰 : 寡殖");
						} else if (orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
							System.out.println(" 寡殖/ん濰 : ん濰");
						}
						System.out
								.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						System.out.println(" 識 旎擋 : " + sum + "錳");
						System.out.println(" 寡殖蕨鼻衛除 : " + orderInfoList.get(0).get("ORDERETA") + "碟");
						System.out.println(" 輿僥濠貲 : " + orderInfoList.get(0).get("MEMNM"));
						System.out.println(" 輿僥濠輿模 : " + orderInfoList.get(0).get("MEMADD"));
						System.out.println(" 唸薯罹睡 : " + orderInfoList.get(0).get("SELYN"));
						System.out
								.println(" ==========================================================================");
					}
				}
			}

//			for(Map<String, Object> orderCode : orderCodeList) {
//				long sum = 0;
//				long countSum = 0;
//				long countDeilSum = 0;
//				// 嬴檜蛤薑爾蒂 檜辨п 輿僥頂羲 纔檜綰 陛螳褥
//				List<Map<String, Object>> orderInfoList = memberDAO.orderListStr(userID, String.valueOf(orderCode.get("ORDERCODE")));
//				// 輿僥�蝦鶹� 評塭 蘊葆棻 釭散 輿僥薑爾 爾罹邀
//				System.out.println(" 輿僥囀萄 : " + orderInfoList.get(0).get("ORDERCODE")); // 陳瞼⑽衝 雖薑п 轎溘ж晦
//				System.out.println("---------------------------------------");
//				System.out.println(" 輿僥陳瞼 : " + orderInfoList.get(0).get("ORDERDATE"));
//				System.out.println("---------------------------------------");
//				for(Map<String, Object> orderItem : orderInfoList) {
//					System.out.print(" 詭景貲 : " + orderItem.get("MENUNM") + " | "); // 醞犒
//					System.out.print(" 輿僥熱榆 : " + orderItem.get("ORDERQTY") + "偃"  + " | "); // 醞犒
//					System.out.println(" 詭景滌 旎擋 : " + orderItem.get("TOTALPRICE") + "錳"); // 醞犒
//					countSum = Long.parseLong(String.valueOf(orderItem.get("TOTALPRICE")));
//					sum += countSum;
//					if(orderItem.get("DELIORTAKE").equals("deliYN")) {
//						System.out.println(" 寡殖綠 : " + orderInfoList.get(0).get("DELICOST") + "錳");
//						countDeilSum = Long.parseLong(String.valueOf(orderInfoList.get(0).get("DELICOST")));
//						sum += countDeilSum;
//					}
//				}
//				if(orderInfoList.get(0).get("DELIORTAKE").equals("deliYN")) {
//					System.out.println(" 寡殖/ん濰 : 寡殖");
//				}else if(orderInfoList.get(0).get("DELIORTAKE").equals("takeYN")) {
//					System.out.println(" 寡殖/ん濰 : ん濰");
//				}
//				System.out.println("---------------------------------------");
//				System.out.println(" 識 旎擋 : " + sum + "錳");
//				System.out.println(" 寡殖蕨鼻衛除 : " + orderInfoList.get(0).get("ORDERETA") + "碟");
//				System.out.println(" 輿僥濠貲 : " + orderInfoList.get(0).get("MEMNM"));
//				System.out.println(" 輿僥濠輿模 : " + orderInfoList.get(0).get("MEMADD"));
//				System.out.println(" 唸薯罹睡 : " + orderInfoList.get(0).get("SELYN"));
//				System.out.println(" ***************************");
//			}

			while (true) {
				System.out.println(" 0. 檜瞪 �飛�");
				System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.print(" 殮溘 >> ");
				switch (ScanUtil.nextInt()) {
				case 0:
					return View.ORDER_HOME;
				default:
					System.out.println(" ≦ 澀跤脹 蕾斬殮棲棻. 棻衛 殮溘ж撮蹂.");
					break;
				}
			}
		}
		return View.ORDER_HOME;
	}
}