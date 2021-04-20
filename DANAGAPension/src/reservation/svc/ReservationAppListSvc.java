package reservation.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import dao.ReservationDAO;
import dao.RoomDAO;
import vo.ReservationBean;

public class ReservationAppListSvc {

	public ArrayList<ReservationBean> getReservationList(int page, int limit) {
		ArrayList<ReservationBean> reservationList = null;
		Connection con = null;
		ReservationDAO reservationDAO = null;
		
		try {
			con = getConnection();
			reservationDAO = ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			
			reservationList = reservationDAO.selectReservationList(page, limit);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return reservationList;
	}

	public static int getListCount() {
		int listCount=0;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			listCount=reservationDAO.selectListCount();
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return listCount;
	}

	public ArrayList<ReservationBean> getReservationList(int page, int limit, String type, String search) {
		ArrayList<ReservationBean> reservationList = null;
		Connection con = null;
		ReservationDAO reservationDAO = null;
		
		try {
			con = getConnection();
			reservationDAO = ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			
			reservationList = reservationDAO.selectReservationList(page, limit,type,search);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return reservationList;
	}

	public static int getListCount1(String type, String search) {
		int listCount=0;
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			listCount=reservationDAO.selectListCount1(type,search);
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
		return listCount;
	}

	public void updateReservation() {
		Connection con=null;
		ReservationDAO reservationDAO=null;
		try {
			con=getConnection();
			reservationDAO=ReservationDAO.getInstance();
			reservationDAO.setConnection(con);
			reservationDAO.updateReservation();
			commit(con);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null)close(con);
		}
	}

}
