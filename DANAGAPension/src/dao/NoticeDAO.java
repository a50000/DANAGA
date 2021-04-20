package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vo.NoticeBean;

public class NoticeDAO {
	Connection con=null;
	private static NoticeDAO noticeDAO;
	public NoticeDAO() {
		// TODO Auto-generated constructor stub
	}
	public static NoticeDAO getInstance() {
		if(noticeDAO==null) noticeDAO=new NoticeDAO();
		return noticeDAO;
	}
	
	public void setConnection(Connection con) {
		this.con=con;
	}
	public ArrayList<NoticeBean> selectNoticeList(int page, int limit) {
		ArrayList<NoticeBean> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int startRow=(page-1)*limit;
		try {
			pstmt=con.prepareStatement("select * from notice order by board_no desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<NoticeBean>();
				do {
				list.add(new NoticeBean(rs.getInt("board_no"),rs.getString("board_subject"),rs.getString("board_content"),rs.getString("board_date"),rs.getInt("board_readcount")));
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	public int insertNotice(NoticeBean notice) {
		int insertCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		int no=0;
		String date=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE)+" "+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
		try {
			pstmt=con.prepareStatement("select max(board_no) from notice");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				no=rs.getInt("max(board_no)")+1;
				pstmt=con.prepareStatement("insert into notice(board_no,board_subject,board_content,board_date) values(?,?,?,?)");
				pstmt.setInt(1, no);
				pstmt.setString(2, notice.getBoard_subject());
				pstmt.setString(3, notice.getBoard_content());
				pstmt.setString(4, date);
				insertCount=pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(rs);
		}
		return insertCount;
	}
	public NoticeBean getNoticeInfo(int no) {
		NoticeBean notice=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select * from notice where board_no=?");
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				notice=new NoticeBean();
				notice.setBoard_no(rs.getInt("board_no"));
				notice.setBoard_subject(rs.getString("board_subject"));
				notice.setBoard_content(rs.getString("board_content"));
				notice.setBoard_date(rs.getString("board_date"));
				notice.setBoard_readcount(rs.getInt("board_readcount"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		return notice;
	}
	public int updateReadcount(int no) {
		int updateCount=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement("update notice set board_readcount=board_readcount+1 where board_no="+no);
			updateCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) close(pstmt);
		}
		
		return updateCount;
	}
	public int deleteNotice(int no) {
		PreparedStatement pstmt=null;
		int deleteCount=0;
		try {
			pstmt=con.prepareStatement("delete from notice where board_no="+no);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) close(pstmt);
		}
		return deleteCount;
	}
	public int selectListCount() {
		int listCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select count(*) from notice");
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
}
