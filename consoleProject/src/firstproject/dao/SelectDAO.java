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

	// �� ������� �ֱ� �ֹ� ����
	public List<Map<String, Object>> orderhistory() {
		return jdbc
				.selectList("SELECT O.ORDERDATE, S.STONM, M.MENUNM, O.ORDERQTY" + " FROM ORDERHISTO O, STORE S, MENU M"
						+ "WHERE O.STOCODE = S.STOCODE" + "AND O.MENUCODE = M.MENUCODE" + "AND O.MEMID");
	}

	// ī�װ� ���ÿ� ���� ������� ����Ʈ
	// ��޼��ý�
	public List<Map<String, Object>> koreandelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'HS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> westerndelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'YS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> chinesedelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'CS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> japanesedelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'JS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> dessertdelilist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'DS' AND DELIYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	// ���弱�ý�
	public List<Map<String, Object>> koreantakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'HS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> westerntakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'YS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> chinesetakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'CS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> japanesetakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'JS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	public List<Map<String, Object>> desserttakelist() {
		return jdbc.selectList(" SELECT STOCODE, STONM, STOADD, CATECODE, " + " MINORDER || '��' AS MINORDER, "
				+ " DECODE(CLOSEYN, 'Y', '�������', 'N', '�������', '����') AS CLOSEYN, "
				+ " DECODE(DELIYN, 'Y', '��ް���', 'N', '��޺Ұ�', '����') AS DELIYN, "
				+ " DECODE(PACKYN, 'Y', '���尡��', 'N', '����Ұ�', '����') AS PACKYN " + " FROM STORE "
				+ " WHERE CATECODE = 'DS' AND PACKYN = 'Y' AND CLOSEYN = 'Y' ");
	}

	// ����������Ʈ�� ������
	public List<Map<String, Object>> getMenuList(String storeName) {
		String sql = "SELECT * " + "FROM MENU a, STORE b " + "WHERE a.STOCODE = b.STOCODE "
				+ "AND a.STOCODE = (SELECT STOCODE FROM STORE WHERE STONM = ? )";
		List<Object> param = new ArrayList<>();
		param.add(storeName);
		return jdbc.selectList(sql, param);
	}

	// ���̴��� ��޺� ������
	public Map<String, Object> getRiderDeliCost(String storeCode) {
		String sql = "SELECT DELICOST " + "  FROM RIDER " + " WHERE STOCODE = ? ";
		List<Object> param = new ArrayList<>();
		param.add(storeCode);
		return jdbc.selectOne(sql, param);
	}

	// �޴�����Ʈ�� ������
	public Map<String, Object> getMenuDetailList(String menuName, String storeName) {
		String sql = " SELECT * FROM MENU a, STORE b WHERE a.STOCODE = b.STOCODE AND a.MENUNM = ? AND b.STONM = ? ";
		List<Object> param = new ArrayList<>();
		param.add(menuName);
		param.add(storeName);
		return jdbc.selectOne(sql, param);
	}

	// ���� �� ����Ʈ ���� ����
	public int getPriceUpdate(List<Object> param) {
		return jdbc.update(" UPDATE MEMBER SET BALANCE = ? WHERE MEMID = ? ", param);
	}

	// ���� �� �޴� ���� ���� ����
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

	// ���̵� ���� ����������� ������
	public Map<String, Object> BalanceOne(String userID) {
		String sql = "SELECT * FROM MEMBER WHERE MEMID = ? ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectOne(sql, param);
	}

	// ������ �ּҰ� ������ ����� ���������ϰ���
	public List<Map<String, Object>> daejeonList(String userID) {
		String sql = " SELECT * FROM MEMBER WHERE SUBSTR(MEMADD,1,2) = '����' AND MEMID = ? ";
		List<Object> param = new ArrayList<>();
		param.add(userID);
		return jdbc.selectList(sql, param);
	}

}