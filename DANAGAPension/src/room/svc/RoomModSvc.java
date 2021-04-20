package room.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.RoomDAO;
import vo.RoomBean;

public class RoomModSvc {

	public ArrayList<RoomBean> getRoomInfo(String no) {
		ArrayList<RoomBean> info=null;
		RoomDAO roomDAO=null;
		Connection con=null;
		try {
			roomDAO=RoomDAO.getInstance();
			con=getConnection();
			roomDAO.setConnection(con);
			info=roomDAO.selectRoomInfo(no);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return info;
	}

}
