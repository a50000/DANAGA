package reservation.svc;

import java.sql.Connection;

import dao.ReservationDAO;

import static db.JdbcUtil.*;
public class ReservationAppSvc {

	public boolean confirmReservation(int r_no,String re_date) {
		boolean isCofSuccess = false;
		Connection con = null;
		ReservationDAO reservationDAO = null;
		try {
			con = getConnection();
			reservationDAO = ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			int confirmCount = reservationDAO.confirmReservation(r_no,re_date);
			
			if(confirmCount > 0) {
				commit(con);
				isCofSuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isCofSuccess;
	}

	public void payConf(int re_no) {
		Connection con = null;
		ReservationDAO reservationDAO = null;
		boolean updateSuccess=false;
		try {
			con = getConnection();
			reservationDAO = ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			updateSuccess=reservationDAO.updatePay(re_no);
			if(updateSuccess) {
				commit(con);
				System.out.println("저장");
			}else {
				rollback(con);
				System.out.println("실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con); 
		}
	}

}
