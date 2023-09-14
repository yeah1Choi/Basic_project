package firstproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdminUpdateDAO {

	private static AdminUpdateDAO instance = null;

	private AdminUpdateDAO() {
	}

	public static AdminUpdateDAO getInstance() {
		if (instance == null)
			instance = new AdminUpdateDAO();
		return instance;
	}

	private final String URL = "jdbc:oracle:thin:@192.168.36.94:1521:xe";
	private final String USER = "pc_22_project01";
	private final String PASSWORD = "java";

	private Connection conn = null;
	private Statement stmt = null; // 정적 쿼리
	private PreparedStatement pstmt = null; // 동적 쿼리
	private ResultSet rs = null;

	public int update(String sql, List<Object> param) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < param.size(); i++) {
				pstmt.setObject(i + 1, param.get(i)); // 오라클은 index 기준이 1. 그래서 i+1.
			}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return result;
	}

}
