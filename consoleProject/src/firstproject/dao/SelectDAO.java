package firstproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.util.JDBCUtil;

public class SelectDAO {

	private static SelectDAO instance = null;

	private SelectDAO() {
	}

	public static SelectDAO getInstance() {
		if (instance == null)
			instance = new SelectDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	// 현 사용자의 최근 주문 내역
	public List<Map<String, Object>> orderhistory() {
		return jdbc
				.selectList("SELECT O.ORDERDATE, S.STONM, M.MENUNM, O.ORDERQTY" + " FROM ORDERHISTO O, STORE S, MENU M"
						+ "WHERE O.STOCODE = S.STOCODE" + "AND O.MENUCODE = M.MENUCODE" + "AND O.MEMID");
	}

	// 카테고리 선택에 따른 레스토랑 리스트
	// 배달선택시
	public List<Map<String, Object>> koreandelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'HS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> westerndelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'YS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> chinesedelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'CS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> japanesedelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'JS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> dessertdelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'DS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	// 포장선택시
	public List<Map<String, Object>> koreantakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'HS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> westerntakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'YS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> chinesetakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'CS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> japanesetakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'JS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> desserttakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '원' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'DS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	// 음식점리스트를 가져옴
	public List<Map<String, Object>> getMenuList(String storeName) {
		String sql = "SELECT * " + "FROM MENU a, STORE b " + "WHERE a.STOCODE = b.STOCODE "
				+ "AND a.STOCODE = (SELECT STOCODE FROM STORE WHERE STONM = ? )";
		List<Object> param = new ArrayList<>();
		param.add(storeName);
		return jdbc.selectList(sql, param);
	}

	// 라이더의 배달비 가져옴
	public Map<String, Object> getRiderDeliCost(String storeCode) {
		String sql = "SELECT DELICOST " + "  FROM RIDER " + " WHERE STOCODE = ? ";
		List<Object> param = new ArrayList<>();
		param.add(storeCode);
		return jdbc.selectOne(sql, param);
	}

	// 메뉴리스트를 가져옴
	public Map<String, Object> getMenuDetailList(String menuName, String storeName) {
		String sql = " SELECT * FROM MENU a, STORE b WHERE a.STOCODE = b.STOCODE AND a.MENUNM = ? AND b.STONM = ? ";
		List<Object> param = new ArrayList<>();
		param.add(menuName);
		param.add(storeName);
		return jdbc.selectOne(sql, param);
	}

	// 결제 후 포인트 차감 저장
	public int getPriceUpdate(List<Object> param) {
		return jdbc.update(" UPDATE MEMBER SET BALANCE = ? WHERE MEMID = ? ", param);
	}

	// 결제 후 메뉴 수량 변경 저장
	public int getRemainUpdate(List<Object> param1) {
		return jdbc.update(
				"UPDATE MENU SET REMAINQTY = ? WHERE MENUNM = ? AND STOCODE IN (SELECT STOCODE FROM STORE WHERE STONM = ? ) ",
				param1);
	}

	public int getOrderHistoInsert(List<Object> param2) {
		return jdbc.update(
				" INSERT INTO orderhisto (ordercode, memid, menucode, orderqty, orderdate, ordereta, deliortake, selyn, orderno) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, 'Y', ORDERHISTO_SEQ.NEXTVAL) ",
				param2);

	}

	// 아이디를 통해 사용자정보를 가져옴
	public Map<String, Object> BalanceOne(String userID) {
		String sql = "SELECT * FROM MEMBER WHERE MEMID = ? ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectOne(sql, param);
	}

	// 결제시 주소가 대전인 사람만 결제가능하게함
	public List<Map<String, Object>> daejeonList(String userID) {
		String sql = " SELECT * FROM MEMBER WHERE SUBSTR(MEMADD,1,2) = '대전' AND MEMID = ? ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectList(sql, param);
	}

}