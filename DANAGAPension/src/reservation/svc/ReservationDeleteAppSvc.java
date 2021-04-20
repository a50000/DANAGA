package reservation.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.ReservationDAO;

public class ReservationDeleteAppSvc {

	public boolean deleteReservationApp(int re_no, int r_no, String date) {
		boolean isDelSuccess=false;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			isDelSuccess=reservationDAO.deleteReservationApp(re_no, r_no,date);
			if(isDelSuccess) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return isDelSuccess;
	}

}
