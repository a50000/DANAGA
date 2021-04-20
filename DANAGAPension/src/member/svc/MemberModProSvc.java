package member.svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class MemberModProSvc {

	public boolean modifyMember(MemberBean member) {
		boolean isModifySuccess = false;
		Connection con = null;
		MemberDAO memberDAO = null;
		try {
			con = getConnection();
			memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			int updateCount = memberDAO.updateMember(member);
			if(updateCount > 0) {
				commit(con);
				isModifySuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isModifySuccess;
	}

}
