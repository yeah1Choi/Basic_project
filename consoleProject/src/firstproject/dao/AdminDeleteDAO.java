package firstproject.dao;

import java.util.List;
import java.util.Map;

import firstproject.util.JDBCUtil;

public class AdminDeleteDAO {

	private static AdminDeleteDAO instance = null;

	private AdminDeleteDAO() {
	}

	public static AdminDeleteDAO getInstance() {
		if (instance == null)
			instance = new AdminDeleteDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	StringBuilder sb = null;
	String sql = null;

	// ȸ�� ���� ����
	public Map<String, Object> searchMemLv(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" SELECT MEMLV FROM MEMBER WHERE MEMID = ? ");
		sql = sb.toString();
		return jdbc.selectOne(sql, param);
	}

	// ȸ�� ����
	public int deleteMember(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" DELETE FROM MEMBER WHERE MEMID = ? ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

	// ��ü ����
	public int deleteStore(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" DELETE FROM STORE WHERE STOCODE = ? ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

	// �޴��� ��������
	public Map<String, Object> searchMenuName(List<Object> param) {
		sql = " SELECT MENUNM FROM MENU WHERE MENUCODE = ? ";
		return jdbc.selectOne(sql, param);
	}

	// �޴� ����
	public int deleteMenu(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" DELETE FROM MENU WHERE MENUCODE = ? ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

	// ���̴� �ڵ� �˻�
	public List<Map<String, Object>> riderSearchFromStoreName(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" SELECT A.RIDCODE AS RIDCODE ");
		sb.append("      , B.STONM AS STONM ");
		sb.append("      , B.STOADD AS STOADD ");
		sb.append("   FROM RIDER A, STORE B ");
		sb.append("  WHERE A.STOCODE = B.STOCODE ");
		sb.append("    AND B.STONM = ? ");
		sql = sb.toString();
		return jdbc.selectList(sql, param);
	}

	// ���̴� ����
	public int deleteRider(List<Object> param) {
		sb = new StringBuilder();
		sb.append(" DELETE FROM RIDER WHERE RIDCODE = ? ");
		sql = sb.toString();
		return jdbc.update(sql, param);
	}

}
