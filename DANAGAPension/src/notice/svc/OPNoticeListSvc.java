package notice.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.NoticeBean;

public class OPNoticeListSvc {

	public ArrayList<NoticeBean> getNoticeList(int page, int limit) {
		ArrayList<NoticeBean> notice=null;
		Connection con=null;
		try {
			con=getConnection();
			NoticeDAO noticeDAO=NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			notice=noticeDAO.selectNoticeList(page,limit);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(con);
		}
		return notice;
	}

}
