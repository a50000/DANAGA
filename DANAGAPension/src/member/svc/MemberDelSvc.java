package member.svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;
public class MemberDelSvc {

	public boolean deleteMember(String id) {
		boolean isDeleteSuccess = false;
		Connection con = null;
		MemberDAO memberDAO = null;
		try {
			con = getConnection();
			memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			int deleteCount = memberDAO.deleteMember(id);
			if(deleteCount > 0) {
				commit(con);
				isDeleteSuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isDeleteSuccess;
	}

}
