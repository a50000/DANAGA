package member.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.MemberDAO;

public class IdCheckProSvc {

	public boolean getID(String id) {
		Connection con =null;
		MemberDAO memberDAO=null;
		boolean isExistID=false;
		try {
			con=getConnection();
			memberDAO=MemberDAO.getInstance();
			memberDAO.setConnection(con);
			isExistID=memberDAO.getID(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return isExistID;
	}

}
