package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.ReservationBean;
import vo.Room_StatBean;

public class ReservationDAO {
	Connection con=null;
	private static ReservationDAO reservationDAO;
	public ReservationDAO() {
		// TODO Auto-generated constructor stub
	}
	public static ReservationDAO getInstance() {
		if(reservationDAO==null) reservationDAO=new ReservationDAO();
		return reservationDAO;
	}
	
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int[] getRoomList() {
		int room[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select count(r_no) from room");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				room=new int[rs.getInt(1)];
				pstmt=con.prepareStatement("select r_no from room");
				rs=pstmt.executeQuery();
				if(rs.next()) {
					int i=0;
					do {
						room[i]=rs.getInt(1);
						i++;
					}while(rs.next());
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		return room;
	}
	public ArrayList<Room_StatBean> getStatList() {
		ArrayList<Room_StatBean> stat=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select r_no, date from room_stat");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				stat=new ArrayList<Room_StatBean>();
				int i=0;
				do {
					stat.add(new Room_StatBean(rs.getInt(1), rs.getString(2)));
					i++;
				}while(rs.next());
			}
		}		catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		return stat;
	}
	public int insertReservation(ReservationBean reservation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into reservation(r_no,re_name,re_men,re_date,re_price,delay) values(?,?,?,?,?,curdate())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(reservation.getR_no()));
			pstmt.setString(2, reservation.getRe_name());
			pstmt.setInt(3, Integer.parseInt(reservation.getRe_men()));
			pstmt.setString(4, reservation.getRe_date());
			pstmt.setInt(5, Integer.parseInt(reservation.getRe_price()));
			if(pstmt.executeUpdate()>0) {
				pstmt=con.prepareStatement("insert into room_stat(r_no,date) values(?,?)");
				pstmt.setInt(1, Integer.parseInt(reservation.getR_no()));
				pstmt.setString(2, reservation.getRe_date());
				insertCount = pstmt.executeUpdate();
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		
		return insertCount;
	}
	
	public ReservationBean selectReservation(int re_no) {
		ReservationBean reservation = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where re_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservation = new ReservationBean();
				reservation.setRe_no(re_no);
				reservation.setR_no(rs.getString("r_no"));
				reservation.setRe_name(rs.getString("re_name"));
				reservation.setRe_men(rs.getString("re_men"));
				reservation.setRe_date(rs.getString("re_date"));
				reservation.setRe_night(rs.getString("re_night"));
				reservation.setRe_price(rs.getString("re_price"));
				reservation.setPay_yn(rs.getString("pay_yn"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return reservation;
	}
	
	public ArrayList<ReservationBean> selectReservationList(int page, int limit) {
		ArrayList<ReservationBean> reservationList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow=(page-1)*limit;
		try {
			pstmt = con.prepareStatement("select * from reservation order by re_no desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservationList = new ArrayList<ReservationBean>();
				do {
					ReservationBean reservation = new ReservationBean();
					reservation.setRe_no(rs.getInt("re_no"));
					reservation.setR_no(rs.getString("r_no"));
					reservation.setRe_name(rs.getString("re_name"));
					reservation.setRe_men(rs.getString("re_men"));
					reservation.setRe_date(rs.getString("re_date"));
					reservation.setDelay(rs.getDate("delay"));
					reservation.setRe_price(rs.getString("re_price"));
					reservation.setPay_yn(rs.getString("pay_yn"));
					reservationList.add(reservation);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return reservationList;
	}

	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from reservation";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		
		return listCount;
	}

	public int confirmReservation(int r_no, String re_date) {
	      PreparedStatement pstmt = null;
	      int confirmCount = 0;
	      String sql = "insert into room_stat(r_no,date) values(?,?)";
	      
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, r_no);
	         pstmt.setString(2, re_date);
	         
	         confirmCount = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	    	  if(pstmt!=null)close(pstmt);
	      }
	      return confirmCount;
	   }
	public boolean deleteReservationApp(int re_no, int r_no, String date) {
		boolean isDelSuccess=false;
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement("delete from reservation where re_no=?");
			pstmt.setInt(1, re_no);
			if(pstmt.executeUpdate()>0) {
				pstmt=con.prepareStatement("delete from room_stat where r_no=? and date=?");
				pstmt.setInt(1, r_no);
				pstmt.setString(2, date);
				if(pstmt.executeUpdate()>0) {
					isDelSuccess=true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)close(pstmt);
		}
		return isDelSuccess;
	}
	public boolean updatePay(int re_no) {
		boolean paySuccess=false;
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement("update reservation set pay_yn='y' where re_no="+re_no);
			if(pstmt.executeUpdate()>0) {
				paySuccess=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)close(pstmt);
		}return paySuccess;
	}
	public ArrayList<ReservationBean> chkReservation(String name) {
		ArrayList<ReservationBean> reservationBean=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select * from reservation where re_name=? order by re_date desc");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				reservationBean=new ArrayList<ReservationBean>();
				do {
					ReservationBean a=new ReservationBean();
					a.setR_no(rs.getString("r_no"));
					a.setRe_name(rs.getString("re_name"));
					a.setRe_men(rs.getString("re_men"));
					a.setRe_date(rs.getString("re_date"));
					a.setRe_price(rs.getString("re_price"));
					a.setDelay(rs.getDate("delay"));
					a.setPay_yn(rs.getString("pay_yn"));
					reservationBean.add(a);
					}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		
		return reservationBean;
	}
	public ArrayList<ReservationBean> selectReservationList(int page, int limit, String type, String search) {
		ArrayList<ReservationBean> reservationList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow=(page-1)*limit;
		String sql=null;
		if(type.equals("날짜")) {
			sql="select * from reservation where re_date=? order by re_no desc limit ?,?";
		}else{
			sql="select * from reservation where re_name=? order by re_no desc limit ?,?";
		}
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservationList = new ArrayList<ReservationBean>();
				do {
					ReservationBean reservation = new ReservationBean();
					reservation.setRe_no(rs.getInt("re_no"));
					reservation.setR_no(rs.getString("r_no"));
					reservation.setRe_name(rs.getString("re_name"));
					reservation.setRe_men(rs.getString("re_men"));
					reservation.setRe_date(rs.getString("re_date"));
					reservation.setDelay(rs.getDate("delay"));
					reservation.setRe_price(rs.getString("re_price"));
					reservation.setPay_yn(rs.getString("pay_yn"));
					reservationList.add(reservation);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return reservationList;
	}
	public int selectListCount1(String type, String search) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = type.equals("예약자")?"select count(*) from reservation where re_name=?":"select count(*) from reservation where re_date=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		
		return listCount;
	}
	public void updateReservation() {
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement("delete from reservation where pay_yn='n'and curdate()-delay>3");
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)close(pstmt);			
		}
		
	}
}

