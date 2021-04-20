package reservation.svc;

import java.sql.Connection;

import dao.ReservationDAO;

import static db.JdbcUtil.*;
import vo.ReservationBean;

public class ReservationProSvc {

	public static boolean reservationRoom(ReservationBean reservation) {
		boolean isReservationSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			
			int insertCount = reservationDAO.insertReservation(reservation);
			
			if(insertCount >0) {
				commit(con);
				isReservationSuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isReservationSuccess;
	}

}
