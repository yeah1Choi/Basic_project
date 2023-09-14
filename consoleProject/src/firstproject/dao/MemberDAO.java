package firstproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import firstproject.util.JDBCUtil;

public class MemberDAO {

	private static MemberDAO instance = null;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	StringBuilder sb = null;
	String sql = null;

	// 로그인
	public Map<String, Object> login(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" SELECT * FROM MEMBER ");
		sb.append(" WHERE MEMID = ? ");
		sb.append(" AND MEMPW = ? ");
		sql = sb.toString();
		return jdbc.selectOne(sql, param);
	}

	// 로그인 시 아이디 검증
	public Map<String, Object> loginId(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMID = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 로그인 시 비밀번호 검증
	public Map<String, Object> loginPw(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMPW = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 등록된 회원 아이디 조회 후 중복 아이디 체크
	public Map<String, Object> isValID(String memID) {
		String sql = "SELECT * FROM MEMBER WHERE MEMID='" + memID + "'";
		return jdbc.selectOne(sql);
	}

	// 회원 가입
	public int signUp(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" INSERT INTO MEMBER ");
		sb.append(" (MEMID, MEMNM, MEMPW, MEMADD, MEMLV, BALANCE, MEMTEL) ");
		sb.append(" VALUES ");
		sb.append(" (?, ?, ?, ?, ?, ?, ?) ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

	// 아이디 찾기
	public Map<String, Object> findID(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMNM = ? AND MEMTEL = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 비밀번호 찾기
	public Map<String, Object> findPW(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMID = ? AND MEMTEL = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 잔액 충전 서비스
	public long depositPoint(List<Object> param) {
		return jdbc.update(" UPDATE MEMBER SET BALANCE = ? WHERE MEMID = ? ", param);
	}

	public List<Map<String, Object>> searchOrderCode(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" SELECT ORDERCODE ");
		sb.append("   FROM (SELECT TRIM(TO_CHAR(SUBSTR(ORDERCODE, 9, 4)+1, '0000')) AS ORDERCODE ");
		sb.append("           FROM ORDERHISTO ");
		sb.append("          WHERE ORDERCODE LIKE ? ");
		sb.append("          ORDER BY ORDERCODE DESC) ");
		sb.append("  WHERE ROWNUM = 1 ");
		sql = sb.toString();
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> orderCode(String userID) {
		String sql = " SELECT * FROM ORDERHISTO WHERE MEMID = ? ORDER BY ORDERDATE DESC, ORDERNO DESC ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectList(sql, param);
	}

	// 유저아이디를 통해 주문코드를 가져오는 쿼리, 주문내역관리
	public List<Map<String, Object>> orderCodeSearch(String userID) {
		sql = "SELECT DISTINCT A.ORDERCODE AS ORDERCODE " + "  FROM ORDERHISTO A, MEMBER B " + " WHERE 1=1 "
				+ "   AND A.MEMID = B.MEMID " + "   AND B.MEMID = ? ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectList(sql, param);
	}

	// 유저아이디를 통해 유저의 orderhisto 테이블의 정보를 가져오는 쿼리, 주문내역관리
	public List<Map<String, Object>> orderListStr(String userID, String orderCode) {
		String sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY AS ORDERQTY " + "     , A.ORDERETA AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "     , D.DELICOST AS DELICOST " + "     , A.DELIORTAKE AS DELIORTAKE "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " +

				"   AND E.MEMID = ? " + "   AND A.ORDERCODE = ? " + " ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		param.add(orderCode);
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> orderList(String userID, String orderCode) {
		String sql = " SELECT A.ORDERCODE AS ORDERCODE, " + " B.MENUNM AS MENUNM, " + " B.MENUPRICE AS MENUPRICE, "
				+ " C.STONM AS STONM, " + " A.ORDERQTY || '개' AS ORDERQTY, " + " A.ORDERDATE AS ORDERDATE, "
				+ " A.ORDERETA || '분' AS ORDERETA, " + " A.ORDERQTY * B.MENUPRICE AS TOTALPRICE, "
				+ " E.MEMADD AS MEMADD " + " FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ " AND A.MEMID = E.MEMID " + " AND A.MENUCODE = B.MENUCODE " + " AND B.STOCODE = C.STOCODE "
				+ " AND C.STOCODE = D.STOCODE " + " AND E.MEMID = ? " + " AND A.ORDERCODE = ? "
				+ " ORDER BY A.ORDERDATE DESC, A.ORDERNO DESC ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		param.add(orderCode);
		return jdbc.selectList(sql, param);
	}

	// 유저아이디를 통해 유저의 orderhisto 테이블의 정보를 가져오는 쿼리
	public List<Map<String, Object>> orderList(String userID) {
		String sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY AS ORDERQTY " + "     , A.ORDERETA AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "     , D.DELICOST AS DELICOST " + "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E "
				+ " WHERE 1=1 " + "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE "
				+ "   AND B.STOCODE = C.STOCODE " + "   AND C.STOCODE = D.STOCODE " +

				"   AND E.MEMID = ? " + " ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectList(sql, param);
	}

	// 메뉴명 가져오는 쿼리
	public List<Map<String, Object>> menuInfoList(String menucode) {
//		String sql = " SELECT M.MENUCODE, M.MENUNM, M.MENUPRICE, M.REMAINQTY, M.STOCODE "
//				+ " FROM MENU M, ORDERHISTO O " + " WHERE M.MENUCODE =  O.MENUCODE " + " AND O.MENUCODE = ? ";
		sql = "SELECT M.MENUCODE AS MENUCODE " + "     , M.MENUNM AS MENUNM " + "     , M.MENUPRICE AS MENUPRICE "
				+ "     , M.REMAINQTY AS REMAINQTY " + "     , M.STOCODE AS STOCODE " + "  FROM MENU M, ORDERHISTO O "
				+ " WHERE M.MENUCODE = O.MENUCODE " + "   AND O.MENUCODE = ? ";

		List<Object> param = new ArrayList<>();
		param.add(menucode);
		return jdbc.selectList(sql, param);
	}
}
