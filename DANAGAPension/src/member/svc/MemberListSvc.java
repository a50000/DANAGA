package member.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import dao.NoticeDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class MemberListSvc {

	public ArrayList<MemberBean> getMemberList(int page, int limit) {
		ArrayList<MemberBean> memberList = null;
		Connection con = null;
		MemberDAO memberDAO = null;
		try {
			con = getConnection();
			memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			memberList = memberDAO.selectMemberList(page,limit);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return memberList;
	}

	public static int getListCount() {
		int listCount=0;
		Connection con=null;
		MemberDAO memberDAO =null;
		try {
			con=getConnection();
			memberDAO=MemberDAO.getInstance();
			memberDAO.setConnection(con);
			listCount=memberDAO.selectListCount();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return listCount;
	}
}
