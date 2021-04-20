package notice.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.NoticeBean;

public class NoticeListSvc {

	public ArrayList<NoticeBean> getNoticeList(int page, int limit) {
		ArrayList<NoticeBean> notice=null;
		Connection con=null;
		NoticeDAO noticeDAO=null;
		try {
			con=getConnection();
			noticeDAO=NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			notice=noticeDAO.selectNoticeList(page,limit);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(con);
		}
		return notice;
	}

	public static int getListCount() {
		int listCount=0;
		Connection con=null;
		NoticeDAO noticeDAO=null;
		try {
			con=getConnection();
			noticeDAO=NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			listCount=noticeDAO.selectListCount();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return listCount;
	}

}
