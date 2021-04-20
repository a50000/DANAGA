package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;

public class MemberResignSvc {

	public boolean resignMember(String id) {
		boolean isDelSuccess=false;
		Connection con=null;
		MemberDAO memberDAO=null;
		try {
			con=getConnection();
			memberDAO=MemberDAO.getInstance();
			memberDAO.setConnection(con);
			isDelSuccess=memberDAO.resignMember(id);
			if(isDelSuccess) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) close(con);
		}
		return isDelSuccess;	
	}

}
