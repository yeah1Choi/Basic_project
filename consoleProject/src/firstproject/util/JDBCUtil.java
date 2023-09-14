package firstproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	/*
	 * JDBC�� �� �� ���� ���ϰ� ����ϱ� ���� Utility Ŭ����
	 * 
	 * Map<String, Object> selectOne(String sql) Map<String, Object>
	 * selectOne(String sql, List<Object> param) List<Map<String, Object>>
	 * selectList(String sql) List<Map<String, Object>> selectList(String sql,
	 * List<Object> param) int update(String sql) int update(String sql,
	 * List<Object> param)
	 */

	// �̱��� ���� : �ν��Ͻ��� ������ �����Ͽ� �ϳ��� �ν��Ͻ��� ����ϴ� ������ ����

	// �ν��Ͻ��� ������ ����
	private static JDBCUtil instance = null;

	// JDBCUtil ��ü�� ���� �� ����(�ν��Ͻ�ȭ �� �� ����) private���� ������
	private JDBCUtil() {
	}

	public static JDBCUtil getInstance() {
		if (instance == null)
			instance = new JDBCUtil();
		return instance;
	}

	private String URL = "jdbc:oracle:thin:@192.168.36.94:1521:xe";
	private String USER = "pc_22_project01";
	private String PASSWORD = "java";

	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	// �κ� �˻�, ���� ����
		public Map<String, Object> selectOne(String sql){
			Map<String, Object> row = null;
			try {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				while(rs.next()) {
					row = new HashMap<String, Object>();
					for(int i=1;i<=columnCount;i++) {
						String key = rsmd.getColumnName(i);
						Object value = rs.getObject(i); // Ÿ���� �𸣴ϱ� Object�� �ޱ�
						row.put(key, value);
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) try {rs.close();} catch(Exception e) {}
				if(pstmt!=null) try {pstmt.close();} catch(Exception e) {}
				if(conn!=null) try {conn.close();} catch(Exception e) {}
			}
			return row;
		}
		
		// �κ� �˻�, ���� ����
		public Map<String, Object> selectOne(String sql, List<Object> param) {
			Map<String, Object> row = null;
			try {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<param.size();i++) {
					pstmt.setObject(i+1, param.get(i));
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				while(rs.next()) {
					row=new HashMap<String, Object>();
					for(int i=1;i<=columnCount;i++) {
						String key = rsmd.getColumnName(i);
						Object value = rs.getObject(i);
						row.put(key, value);
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) try {rs.close();} catch(Exception e) {}
				if(pstmt!=null) try {pstmt.close();} catch(Exception e) {}
				if(conn!=null) try {conn.close();} catch(Exception e) {}
			}
			return row;
		}
		
		// ��ü �˻�, ���� ����
		public List<Map<String, Object>> selectList(String sql) {
			List<Map<String, Object>> list = null;
			try {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				while (rs.next()) {
					Map<String, Object> row = new HashMap<String, Object>();
					if(list == null)list=new ArrayList<Map<String,Object>>();
					for(int i=1;i<=columnCount;i++) {
						String key = rsmd.getColumnName(i);
						Object value = rs.getObject(i);
						row.put(key, value);
					}
					list.add(row);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) try {rs.close();} catch(Exception e) {}
				if(pstmt!=null) try {pstmt.close();} catch(Exception e) {}
				if(conn!=null) try {conn.close();} catch(Exception e) {}
			}
			return list;
		}
		
		// ��ü �˻�, ���� ����
		public List<Map<String, Object>> selectList(String sql, List<Object> param) {
			List<Map<String, Object>> list = null;
			try {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<param.size();i++) {
					pstmt.setObject(i+1, param.get(i));
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				while (rs.next()) {
					if(list==null) list = new ArrayList<Map<String,Object>>();
					Map<String, Object> row = new HashMap<String, Object>();
					
					for(int i=1;i<=columnCount;i++) {
						String key = rsmd.getColumnName(i);
						Object value = rs.getObject(i);
						row.put(key, value);
					}
					list.add(row);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) try {rs.close();} catch(Exception e) {}
				if(pstmt!=null) try {pstmt.close();} catch(Exception e) {}
				if(conn!=null) try {conn.close();} catch(Exception e) {}
			}
			return list;
		}
		
		// CUD, ���� ����
		public int update(String sql) {
			int result = 0;
			try {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeUpdate();
//				conn.setAutoCommit(true);
				if(result > 0) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) try {rs.close();} catch(Exception e) {}
				if(pstmt!=null) try {pstmt.close();} catch(Exception e) {}
				if(conn!=null) try {conn.close();} catch(Exception e) {}
			}
			return result;
		}
		
		// CUD, ���� ����
		public int update(String sql, List<Object> param) {
			int result = 0;
			try {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<param.size();i++) {
					pstmt.setObject(i+1, param.get(i));
				}
				result = pstmt.executeUpdate();
//				conn.setAutoCommit(true);
				if(result > 0) {
					conn.commit();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) try {rs.close();} catch(Exception e) {}
				if(pstmt!=null) try {pstmt.close();} catch(Exception e) {}
				if(conn!=null) try {conn.close();} catch(Exception e) {}
			}
			return result;
		}
		
	}
