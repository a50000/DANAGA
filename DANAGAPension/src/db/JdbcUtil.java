package db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class JdbcUtil {
	public static Connection getConnection() {
		Connection con=null;
		
		try {
			Context envCtx=(Context)new InitialContext().lookup("java:comp/env");
			DataSource ds=(DataSource)envCtx.lookup("jdbc/MySQLDB");
			con=ds.getConnection();
			con.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}return con;
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("Commit Success");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("Rollback Success");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
