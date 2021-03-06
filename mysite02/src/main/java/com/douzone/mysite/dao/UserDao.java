package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserDao {
	
	public static boolean insert(UserVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "insert into user values(null, ?, ?, ?, ?, now())";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)을 한다.
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			//5. SQL 구문을 실행한다.
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static boolean update(UserVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			System.out.println(vo.getEmail());
			System.out.println(vo.getPassword());
			System.out.println(vo.getGender());
			System.out.println(vo.getName());
			System.out.println(vo.getNo());
			
			//3. SQL 구문을 준비한다.
			String sql = "update user "
						+ "set email = ifnull(?, email)"
							+ ", password = ?"
							+ ", gender = ifnull(?, gender) "
							+ ", name = ifnull(?, name)"
						+ "where no = ?";							//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)을 한다.
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getName());
			pstmt.setLong(5, vo.getNo());
			
			//5. SQL 구문을 실행한다.
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public UserVo findByNo(Long no) {
		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "select name, email, gender, no from user where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setLong(1, no);
			
			//4. SQL 구문을 실행한다.
			rs = pstmt.executeQuery();
			if(rs.next()){
				String name = rs.getString(1);
				String email = rs.getString(2);
				String gender = rs.getString(3);
				Long userNo = rs.getLong(4);
				
				vo = new UserVo();
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
				vo.setNo(userNo);
			}
			
		} catch(SQLException e) {
			
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public UserVo findByEmailAndPassword(String email, String password){

		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "select no, name from user where email = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			//4. SQL 구문을 실행한다.
			rs = pstmt.executeQuery();
			if(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}
			
		} catch(SQLException e) {
			
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	private static Connection getconnection() throws SQLException{
		
		Connection conn  = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. SQL과 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

	
}
