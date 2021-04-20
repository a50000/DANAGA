package reservation.svc;

import java.sql.Connection;

import dao.ReservationDAO;

import static db.JdbcUtil.*;
import vo.ReservationBean;

public class ReservationInfoSvc {

	public ReservationBean getReservation(int re_no) {
		ReservationBean reservation = null;
		Connection con = null;
		
		try {
			con =getConnection();
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			reservation = reservationDAO.selectReservation(re_no);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return reservation;
	}

}
