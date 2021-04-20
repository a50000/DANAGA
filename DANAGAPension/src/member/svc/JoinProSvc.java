package member.svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class JoinProSvc {

	public boolean joinMember(MemberBean member) {
		boolean isJoinSuccess = false;
		Connection con = null;
		MemberDAO memberDAO = null;
		try {
			con = getConnection();
			 memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			int insertCount = memberDAO.insertMember(member);
			
			if(insertCount > 0) {
				commit(con);
				isJoinSuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isJoinSuccess;
	}

}
