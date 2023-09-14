package firstproject.dao;

import java.util.List;
import java.util.Map;

import firstproject.util.JDBCUtil;

public class AdminReadDAO {

	private static AdminReadDAO instance = null;

	private AdminReadDAO() {
	}

	public static AdminReadDAO getInstance() {
		if (instance == null)
			instance = new AdminReadDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	StringBuilder sb = null;
	String sql = null;

	// 회원 목록 전체 조회
	public List<Map<String, Object>> memALL() {
		sql = " SELECT * FROM MEMBER ";
		return jdbc.selectList(sql);
	}

	// 회원 목록 아이디로 검색
	public Map<String, Object> memSearchFromId(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMID = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 회원 목록 이름으로 검색
	public List<Map<String, Object>> memSearchFromNm(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMNM = ? ";
		return jdbc.selectList(sql, param);
	}

	// 회원 목록 전화번호로 검색
	public List<Map<String, Object>> memSearchFromTel(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMTEL = ? ";
		return jdbc.selectList(sql, param);
	}

	// 가게 목록 전체 조회
	public List<Map<String, Object>> storeALL() {
//		sql = " SELECT * FROM STORE ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE ";
		return jdbc.selectList(sql);
	}

	// 가게코드로 검색
	public Map<String, Object> storeSearchFromStoCode(List<Object> param) {
//		String sql = " SELECT * FROM STORE WHERE STOCODE = ? ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.STOCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 가게명으로 검색
	public List<Map<String, Object>> storeSearchFromStoName(List<Object> param) {
//		String sql = " SELECT * FROM STORE WHERE STONM = ? ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.STONM = ? ";
		return jdbc.selectList(sql, param);
	}

	// 주소로 검색
	public List<Map<String, Object>> storeSearchFromStoAdd(List<Object> param) {
//		String sql = " SELECT * FROM STORE WHERE STOADD = ? ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.STOADD = ? ";
		return jdbc.selectList(sql, param);
	}

	// 포장 가능한 가게 검색
	public List<Map<String, Object>> storeSearchFromPackYN() {
//		String sql = "SELECT * " + 
//				     "  FROM STORE " + 
//				     " WHERE PACKYN = 'Y' ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.PACKYN, 'Y', '포장가능', 'N', '포장불가', '오류') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.PACKYN = 'Y' ";
		return jdbc.selectList(sql);
	}

	// 배달 가능한 가게 검색
	public List<Map<String, Object>> storeSearchFromDeliYN() {
//		String sql = "SELECT * " + 
//				     "  FROM STORE " + 
//				     " WHERE DELIYN = 'Y' ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.DELIYN, 'Y', '배달가능', 'N', '배달불가', '오류') AS DELIYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.DELIYN = 'Y' ";
		return jdbc.selectList(sql);
	}

	// 현재 운영중인 가게 검색
	public List<Map<String, Object>> storeSearchFromCloseYN() {
//		String sql = "SELECT * " + 
//				     "  FROM STORE " + 
//				     " WHERE CLOSEYN = 'Y' ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '원' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '매장오픈', 'N', '매장닫힘', '오류') AS CLOSEYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.CLOSEYN = 'Y' ";
		return jdbc.selectList(sql);
	}

	// 메뉴 목록 전체 조회
	public List<Map<String, Object>> menuALL() {
//		sql = " SELECT * FROM MENU ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '원' AS MENUPRICE " + "     , A.REMAINQTY || '개' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE ";
		return jdbc.selectList(sql);
	}

	// 메뉴 코드 검색
	public Map<String, Object> menuSearchFromMenuCode(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE MENUCODE = ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '원' AS MENUPRICE " + "     , A.REMAINQTY || '개' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND A.MENUCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 메뉴명 검색
	public List<Map<String, Object>> menuSearchFromMenuName(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE MENUNM = ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '원' AS MENUPRICE " + "     , A.REMAINQTY || '개' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND A.MENUNM = ? ";
		return jdbc.selectList(sql, param);
	}

	// 메뉴가격 검색
	public List<Map<String, Object>> menuSearchFromMenuPrice(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE MENUPRICE >= ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '원' AS MENUPRICE " + "     , A.REMAINQTY || '개' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND A.MENUPRICE >= ? ";
		return jdbc.selectList(sql, param);
	}

	// 가게코드 검색
	public List<Map<String, Object>> menuSearchFromStoCode(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE STOCODE = ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '원' AS MENUPRICE " + "     , A.REMAINQTY || '개' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND B.STONM = ? ";
		return jdbc.selectList(sql, param);
	}

	// 주문내역 목록 전체 조회
	public List<Map<String, Object>> orderhistoALL() {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql);
	}

	// 주문내역코드 검색
	public List<Map<String, Object>> orderSearchFromOrderCode(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.ORDERCODE = ? "
				+ "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// 회원아이디 검색
	public List<Map<String, Object>> orderSearchFromMemNm(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND E.MEMNM = ? " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// 메뉴코드 검색
	public List<Map<String, Object>> orderSearchFromMenuNm(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND B.MENUNM = ? " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// 주문날짜 검색
	public List<Map<String, Object>> orderSearchFromOrderDate(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.ORDERDATE = ? "
				+ "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// 배달예상시간 검색
	public List<Map<String, Object>> orderSearchFromOrderEta(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.ORDERETA >= ? "
				+ "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// 결제여부 검색
	public List<Map<String, Object>> orderSearchFromSelYn(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '개' AS ORDERQTY " + "     , A.ORDERETA || '분' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '원' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '결제완료', 'N', '결제대기', '오류') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.SELYN = ? " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// 라이더 목록 전체 조회
	public List<Map<String, Object>> riderALL() {
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '대기중', 'N', '배달중', '오류') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '원' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE ";
		return jdbc.selectList(sql);
	}

	// 라이더코드 검색
	public Map<String, Object> riderSearchFromRiderCode(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE RIDCODE = ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '대기중', 'N', '배달중', '오류') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '원' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND A.RIDCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// 라이더 부재유무 검색
	public List<Map<String, Object>> riderSearchFromAbseYn(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE ABSEYN = ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '대기중', 'N', '배달중', '오류') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '원' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND A.ABSEYN = ? ";
		return jdbc.selectList(sql, param);
	}

	// 가게 코드 검색
	public List<Map<String, Object>> riderSearchFromStoNm(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE STOCODE = ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '대기중', 'N', '배달중', '오류') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '원' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND B.STONM = ? ";
		return jdbc.selectList(sql, param);
	}

	// 배달비 검색
	public List<Map<String, Object>> riderSearchFromDeliCost(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE DELICOST >= ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '대기중', 'N', '배달중', '오류') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '원' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND A.DELICOST >= ? ";
		return jdbc.selectList(sql, param);
	}

}
