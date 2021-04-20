package reservation.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.ReservationDAO;
import vo.ReservationBean;

public class ReservationCheckSvc {

	public ArrayList<ReservationBean> chkReservation(String name) {
		ArrayList<ReservationBean> reservationBean=null;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			reservationBean=reservationDAO.chkReservation(name);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) close(con);
		}
		return reservationBean;
	}

}
