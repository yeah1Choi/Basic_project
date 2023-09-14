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

	// ȸ�� ��� ��ü ��ȸ
	public List<Map<String, Object>> memALL() {
		sql = " SELECT * FROM MEMBER ";
		return jdbc.selectList(sql);
	}

	// ȸ�� ��� ���̵�� �˻�
	public Map<String, Object> memSearchFromId(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMID = ? ";
		return jdbc.selectOne(sql, param);
	}

	// ȸ�� ��� �̸����� �˻�
	public List<Map<String, Object>> memSearchFromNm(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMNM = ? ";
		return jdbc.selectList(sql, param);
	}

	// ȸ�� ��� ��ȭ��ȣ�� �˻�
	public List<Map<String, Object>> memSearchFromTel(List<Object> param) {
		String sql = " SELECT * FROM MEMBER WHERE MEMTEL = ? ";
		return jdbc.selectList(sql, param);
	}

	// ���� ��� ��ü ��ȸ
	public List<Map<String, Object>> storeALL() {
//		sql = " SELECT * FROM STORE ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE ";
		return jdbc.selectList(sql);
	}

	// �����ڵ�� �˻�
	public Map<String, Object> storeSearchFromStoCode(List<Object> param) {
//		String sql = " SELECT * FROM STORE WHERE STOCODE = ? ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.STOCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// ���Ը����� �˻�
	public List<Map<String, Object>> storeSearchFromStoName(List<Object> param) {
//		String sql = " SELECT * FROM STORE WHERE STONM = ? ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.STONM = ? ";
		return jdbc.selectList(sql, param);
	}

	// �ּҷ� �˻�
	public List<Map<String, Object>> storeSearchFromStoAdd(List<Object> param) {
//		String sql = " SELECT * FROM STORE WHERE STOADD = ? ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN "
				+ "     , DECODE(A.DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN "
				+ "     , DECODE(A.PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.STOADD = ? ";
		return jdbc.selectList(sql, param);
	}

	// ���� ������ ���� �˻�
	public List<Map<String, Object>> storeSearchFromPackYN() {
//		String sql = "SELECT * " + 
//				     "  FROM STORE " + 
//				     " WHERE PACKYN = 'Y' ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.PACKYN = 'Y' ";
		return jdbc.selectList(sql);
	}

	// ��� ������ ���� �˻�
	public List<Map<String, Object>> storeSearchFromDeliYN() {
//		String sql = "SELECT * " + 
//				     "  FROM STORE " + 
//				     " WHERE DELIYN = 'Y' ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.DELIYN = 'Y' ";
		return jdbc.selectList(sql);
	}

	// ���� ����� ���� �˻�
	public List<Map<String, Object>> storeSearchFromCloseYN() {
//		String sql = "SELECT * " + 
//				     "  FROM STORE " + 
//				     " WHERE CLOSEYN = 'Y' ";
		sql = "SELECT A.STOCODE AS STOCODE " + "     , A.STONM AS STONM " + "     , A.STOADD AS STOADD "
				+ "     , A.MINORDER || '��' AS MINORDER "
				+ "     , DECODE(A.CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN " + "     , B.CATENM AS CATENM "
				+ "  FROM STORE A, CATEGORY B " + " WHERE A.CATECODE = B.CATECODE " + "   AND A.CLOSEYN = 'Y' ";
		return jdbc.selectList(sql);
	}

	// �޴� ��� ��ü ��ȸ
	public List<Map<String, Object>> menuALL() {
//		sql = " SELECT * FROM MENU ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '��' AS MENUPRICE " + "     , A.REMAINQTY || '��' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE ";
		return jdbc.selectList(sql);
	}

	// �޴� �ڵ� �˻�
	public Map<String, Object> menuSearchFromMenuCode(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE MENUCODE = ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '��' AS MENUPRICE " + "     , A.REMAINQTY || '��' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND A.MENUCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// �޴��� �˻�
	public List<Map<String, Object>> menuSearchFromMenuName(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE MENUNM = ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '��' AS MENUPRICE " + "     , A.REMAINQTY || '��' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND A.MENUNM = ? ";
		return jdbc.selectList(sql, param);
	}

	// �޴����� �˻�
	public List<Map<String, Object>> menuSearchFromMenuPrice(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE MENUPRICE >= ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '��' AS MENUPRICE " + "     , A.REMAINQTY || '��' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND A.MENUPRICE >= ? ";
		return jdbc.selectList(sql, param);
	}

	// �����ڵ� �˻�
	public List<Map<String, Object>> menuSearchFromStoCode(List<Object> param) {
//		String sql = " SELECT * FROM MENU WHERE STOCODE = ? ";
		sql = "SELECT A.MENUCODE AS MENUCODE " + "     , A.MENUNM AS MENUNM "
				+ "     , A.MENUPRICE || '��' AS MENUPRICE " + "     , A.REMAINQTY || '��' AS REMAINQTY "
				+ "     , B.STONM AS STONM " + "  FROM MENU A, STORE B " + " WHERE A.STOCODE = B.STOCODE "
				+ "   AND B.STONM = ? ";
		return jdbc.selectList(sql, param);
	}

	// �ֹ����� ��� ��ü ��ȸ
	public List<Map<String, Object>> orderhistoALL() {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql);
	}

	// �ֹ������ڵ� �˻�
	public List<Map<String, Object>> orderSearchFromOrderCode(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.ORDERCODE = ? "
				+ "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// ȸ�����̵� �˻�
	public List<Map<String, Object>> orderSearchFromMemNm(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND E.MEMNM = ? " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// �޴��ڵ� �˻�
	public List<Map<String, Object>> orderSearchFromMenuNm(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND B.MENUNM = ? " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// �ֹ���¥ �˻�
	public List<Map<String, Object>> orderSearchFromOrderDate(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.ORDERDATE = ? "
				+ "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// ��޿���ð� �˻�
	public List<Map<String, Object>> orderSearchFromOrderEta(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.ORDERETA >= ? "
				+ "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// �������� �˻�
	public List<Map<String, Object>> orderSearchFromSelYn(List<Object> param) {
		sql = "SELECT A.ORDERNO AS ORDERNO " + "     , A.ORDERCODE AS ORDERCODE " + "     , B.MENUNM AS MENUNM "
				+ "     , A.ORDERQTY || '��' AS ORDERQTY " + "     , A.ORDERETA || '��' AS ORDERETA "
				+ "     , (A.ORDERQTY * B.MENUPRICE) || '��' AS TOTALPRICE "
				+ "     , TO_CHAR(A.ORDERDATE, 'YYYY-MM-DD') AS ORDERDATE " + "     , E.MEMNM AS MEMNM "
				+ "     , E.MEMADD AS MEMADD " + "     , DECODE(A.SELYN, 'Y', '�����Ϸ�', 'N', '�������', '����') AS SELYN "
				+ "  FROM ORDERHISTO A, MENU B, STORE C, RIDER D, MEMBER E " + " WHERE 1=1 "
				+ "   AND A.MEMID = E.MEMID " + "   AND A.MENUCODE = B.MENUCODE " + "   AND B.STOCODE = C.STOCODE "
				+ "   AND C.STOCODE = D.STOCODE " + "   AND A.SELYN = ? " + "   ORDER BY A.ORDERDATE DESC, A.ORDERNO ";
		return jdbc.selectList(sql, param);
	}

	// ���̴� ��� ��ü ��ȸ
	public List<Map<String, Object>> riderALL() {
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '�����', 'N', '�����', '����') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '��' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE ";
		return jdbc.selectList(sql);
	}

	// ���̴��ڵ� �˻�
	public Map<String, Object> riderSearchFromRiderCode(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE RIDCODE = ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '�����', 'N', '�����', '����') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '��' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND A.RIDCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// ���̴� �������� �˻�
	public List<Map<String, Object>> riderSearchFromAbseYn(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE ABSEYN = ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '�����', 'N', '�����', '����') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '��' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND A.ABSEYN = ? ";
		return jdbc.selectList(sql, param);
	}

	// ���� �ڵ� �˻�
	public List<Map<String, Object>> riderSearchFromStoNm(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE STOCODE = ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '�����', 'N', '�����', '����') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '��' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND B.STONM = ? ";
		return jdbc.selectList(sql, param);
	}

	// ��޺� �˻�
	public List<Map<String, Object>> riderSearchFromDeliCost(List<Object> param) {
//		sql = " SELECT * FROM RIDER WHERE DELICOST >= ? ";
		sql = "SELECT A.RIDCODE AS RIDCODE " + "     , DECODE(A.ABSEYN, 'Y', '�����', 'N', '�����', '����') AS ABSEYN "
				+ "     , B.STONM AS STONM " + "     , A.DELICOST || '��' AS DELICOST " + "  FROM RIDER A, STORE B "
				+ " WHERE A.STOCODE = B.STOCODE " + "   AND A.DELICOST >= ? ";
		return jdbc.selectList(sql, param);
	}

}
