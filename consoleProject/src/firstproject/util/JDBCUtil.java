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
	 * JDBC를 좀 더 쉽고 편하게 사용하기 위한 Utility 클래스
	 * 
	 * Map<String, Object> selectOne(String sql) Map<String, Object>
	 * selectOne(String sql, List<Object> param) List<Map<String, Object>>
	 * selectList(String sql) List<Map<String, Object>> selectList(String sql,
	 * List<Object> param) int update(String sql) int update(String sql,
	 * List<Object> param)
	 */

	// 싱글톤 패턴 : 인스턴스의 생성을 제한하여 하나의 인스턴스만 사용하는 디자인 패턴

	// 인스턴스를 보관할 변수
	private static JDBCUtil instance = null;

	// JDBCUtil 객체를 만들 수 없게(인스턴스화 할 수 없게) private으로 제한함
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

	// 부분 검색, 정적 쿼리
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
						Object value = rs.getObject(i); // 타입을 모르니까 Object로 받기
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
		
		// 부분 검색, 동적 쿼리
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
		
		// 전체 검색, 정적 쿼리
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
		
		// 전체 검색, 동적 쿼리
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
		
		// CUD, 정적 쿼리
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
		
		// CUD, 동적 쿼리
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
