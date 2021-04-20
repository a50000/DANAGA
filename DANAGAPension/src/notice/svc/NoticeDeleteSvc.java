package notice.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;

public class NoticeDeleteSvc {

	public void deleteNotice(int no) {
		Connection con=null;
		NoticeDAO noticeDAO  =null;
		int deleteCount=0;
		try {
			con=getConnection();
			noticeDAO=new NoticeDAO();
			noticeDAO.setConnection(con);
			deleteCount=noticeDAO.deleteNotice(no);
			if(deleteCount>0) {
				commit(con);
			}else{
				rollback(con);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) close(con);
		}
		
	}

}
