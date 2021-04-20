package reservation.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.ReservationDAO;

public class ReservationPaySvc {

	public boolean updatePay(int no) {
		boolean paySuccess=false;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			paySuccess=reservationDAO.updatePay(no);
			if(paySuccess) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return paySuccess;	
	}

}
