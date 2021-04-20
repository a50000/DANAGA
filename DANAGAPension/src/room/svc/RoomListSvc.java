package room.svc;
import static db.JdbcUtil.*;
import java.util.ArrayList;
import java.sql.Connection;


import dao.RoomDAO;
import vo.RoomBean;

public class RoomListSvc {

	public ArrayList<RoomBean> getRoomList() {
		ArrayList<RoomBean> list=null;
		RoomDAO roomDAO=null;
		Connection con=null;
		try {
			roomDAO=RoomDAO.getInstance();
			con=getConnection();
			roomDAO.setConnection(con);
			list=roomDAO.selectRoomList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return list;
	}

}
