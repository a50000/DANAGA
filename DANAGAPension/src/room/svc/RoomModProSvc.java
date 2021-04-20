package room.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.RoomDAO;
import vo.RoomBean;

public class RoomModProSvc {

	public boolean modRoom(String no,RoomBean room) {
		boolean isModSuccess=false;
		Connection con=null;
		try {
			con=getConnection();
			RoomDAO roomDAO=RoomDAO.getInstance();
			roomDAO.setConnection(con);
			int updateCount=roomDAO.updateRoom(no,room);
			if(updateCount>0) {
				commit(con);
				isModSuccess=true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isModSuccess;
	}

}
