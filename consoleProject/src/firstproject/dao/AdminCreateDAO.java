package firstproject.dao;

import java.util.List;
import java.util.Map;

import firstproject.util.JDBCUtil;

public class AdminCreateDAO {

	private static AdminCreateDAO instance = null;

	private AdminCreateDAO() {
	}

	public static AdminCreateDAO getInstance() {
		if (instance == null)
			instance = new AdminCreateDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	StringBuilder sb = null;
	String sql = null;

	// 업체코드 중복 검증 및 코드숫자 자동 증가
	public List<Map<String, Object>> searchStoreCode(List<Object> param) {
//		String sql = " SELECT STOCODENUM " + 
//					 "   FROM ( " + 
//					 "  SELECT TRIM(TO_CHAR(SUBSTR(STOCODE,5,2)+1, '00')) AS STOCODENUM " + 
//					 "    FROM STORE " + 
//					 "   WHERE STOCODE LIKE ?% " + 
//					 "   ORDER BY STOCODE DESC " + 
//					 "  ) " + 
//					 " WHERE ROWNUM = 1 ";
		sb = new StringBuilder();
		sb.append(" SELECT STOCODE ");
		sb.append("   FROM (SELECT TRIM(TO_CHAR(SUBSTR(STOCODE,5,2)+1, '00')) AS STOCODE ");
		sb.append("           FROM STORE ");
		sb.append("          WHERE STOCODE LIKE ? ");
		sb.append("          ORDER BY STOCODE DESC) ");
		sb.append("  WHERE ROWNUM = 1 ");
		sql = sb.toString();
		return jdbc.selectList(sql, param);
	}

	// 가게 등록
	public int createStore(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" INSERT INTO STORE ");
		sb.append(" (STOCODE, STONM, STOADD, MINORDER, DELIYN, PACKYN, CLOSEYN, CATECODE) ");
		sb.append(" VALUES ");
		sb.append(" (?, ?, ?, ?, ?, ?, ?, ?) ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

	// 가게명 가져오기
	public Map<String, Object> searchStoreName(List<Object> param) {
		sql = " SELECT STONM FROM STORE WHERE STOCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 메뉴코드 중복 검증 및 코드숫자 자동 증가
	public List<Map<String, Object>> searchMenuCode(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" SELECT MENUCODE ");
		sb.append("   FROM (SELECT TRIM(TO_CHAR(SUBSTR(MENUCODE,6,3)+1, '000')) AS MENUCODE ");
		sb.append("           FROM MENU ");
		sb.append("          WHERE MENUCODE LIKE ? ");
		sb.append("          ORDER BY MENUCODE DESC) ");
		sb.append("  WHERE ROWNUM = 1 ");
		sql = sb.toString();
		return jdbc.selectList(sql, param);
	}

	// 메뉴 등록
	public int createMenu(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" INSERT INTO MENU ");
		sb.append(" (MENUCODE, MENUNM, MENUPRICE, REMAINQTY, STOCODE) ");
		sb.append(" VALUES ");
		sb.append(" (?, ?, ?, ?, ?) ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

	// 라이더코드 중복 검증 및 코드숫자 자동 증가
	public List<Map<String, Object>> searchRiderCode(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" SELECT RIDCODE ");
		sb.append("   FROM (SELECT TRIM(TO_CHAR(SUBSTR(RIDCODE,6,2)+1, '00')) AS RIDCODE ");
		sb.append("           FROM RIDER ");
		sb.append("          WHERE RIDCODE LIKE ? ");
		sb.append("          ORDER BY RIDCODE DESC) ");
		sb.append("  WHERE ROWNUM = 1 ");
		sql = sb.toString();
		return jdbc.selectList(sql, param);
	}

	// 라이더 등록
	public int createRider(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" INSERT INTO RIDER ");
		sb.append(" (RIDCODE, ABSEYN, DELICOST, STOCODE) ");
		sb.append(" VALUES ");
		sb.append(" (?, ?, ?, ?) ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

}
