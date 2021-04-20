package notice.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;
import vo.NoticeBean;

public class OPNoticeInfoSvc {

	public static NoticeBean getNoticeInfo(int no) {
		NoticeBean notice=null;
		NoticeDAO noticeDAO=null;
		Connection con=null;
		int updateCount=0;
		try {
			con=getConnection();
			noticeDAO=NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			updateCount=noticeDAO.updateReadcount(no);
			if(updateCount>0) {
				commit(con);
			}else {
				rollback(con);
			}
			notice=noticeDAO.getNoticeInfo(no);			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) close(con); 
		}
		return notice;
	}

}
