package reservation.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.ReservationDAO;
import vo.Room_StatBean;

public class reservationStateSvc {

	public int[] getRoomList() {
		int[] list=null;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			list=reservationDAO.getRoomList();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) close(con);
		}
		return list;
	}

	public ArrayList<Room_StatBean> getStatList() {
		ArrayList<Room_StatBean> stat=null;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			stat=reservationDAO.getStatList();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) close(con);
		}
		return stat;
	}

}
