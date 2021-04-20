package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import dao.MemberDAO;
import vo.MemberBean;

public class MemberDAO {
	Connection con;
	private static MemberDAO memberDAO;
	
	public static MemberDAO getInstance() {
		if(memberDAO== null)
			memberDAO = new MemberDAO();
		return memberDAO;
	}
	public void setConnection(Connection con) {
		this.con = con;
		
	}

	public MemberBean selectMember(String id) {
		MemberBean member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberBean();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPasswd(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setPh_no(rs.getString(5));
				member.setGrade(rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}
	public int insertMember(MemberBean member) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into member(id,name,passwd,email,ph_no) values (?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPh_no());
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	public ArrayList<MemberBean> selectMemberList(int page, int limit) {
		ArrayList<MemberBean> memberList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow=(page-1)*limit;
		try {
			pstmt = con.prepareStatement("select * from member limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberList = new ArrayList<MemberBean>();
				do {
					MemberBean member = new MemberBean();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setPasswd(rs.getString("passwd"));
					member.setEmail(rs.getString("email"));
					member.setPh_no(rs.getString("ph_no"));
					member.setGrade(rs.getString("grade"));
					memberList.add(member);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	public int updateMember(MemberBean member) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set passwd = ?, email = ?, ph_no = ?, grade  = ?, name=? where id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPh_no());
			pstmt.setString(4, member.getGrade());
			pstmt.setString(5, member.getName());
			pstmt.setString(6, member.getId());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	public int deleteMember(String id) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from member where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	public boolean getID(String id) {
		boolean isExistID=false;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select id from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())isExistID=true;//true가 생성 불가능임
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return isExistID;
	}
	public int selectListCount() {
		int listCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select count(*) from member");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return listCount;
	}
	public boolean resignMember(String id) {
		boolean isDelSuccess=false;
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement("delete from member where id=?");
			pstmt.setString(1, id);
			if(pstmt.executeUpdate()>0) {
				isDelSuccess=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) close(pstmt);
		}
		return isDelSuccess;
	}
}