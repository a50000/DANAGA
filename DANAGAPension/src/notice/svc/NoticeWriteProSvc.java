package notice.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;
import vo.NoticeBean;

public class NoticeWriteProSvc {
	public boolean noticeWrite(NoticeBean notice) {
		boolean isWriteSuccess=false;
		Connection con=null;
		try {
			con=getConnection();
			NoticeDAO noticeDAO=NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			int insertCount=noticeDAO.insertNotice(notice);
			if(insertCount>0) {
				commit(con);
				isWriteSuccess=true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isWriteSuccess;
	}

}
