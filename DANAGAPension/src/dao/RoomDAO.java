package dao;
import static db.JdbcUtil.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Connection;

import vo.RoomBean;
import vo.Room_StatBean;

public class RoomDAO {
	Connection con =null;
	private static RoomDAO roomDAO;
	public RoomDAO() {
		// TODO Auto-generated constructor stub
	}
	public static RoomDAO getInstance() {
		if(roomDAO==null) {
			roomDAO=new RoomDAO();
		}return roomDAO;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public ArrayList<RoomBean> selectRoomList() {
		ArrayList<RoomBean> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select * from room");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<RoomBean>();
				do {
					list.add(new RoomBean(rs.getString("r_no"),rs.getString("r_type"),rs.getString("r_size"),rs.getString("r_bedroom"),rs.getString("r_men"),rs.getString("r_content")));
				}while(rs.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return list;
	}
	public ArrayList<RoomBean> selectRoomInfo(String no) {
		ArrayList<RoomBean> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select * from room where r_no="+no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<RoomBean>();
				list.add(new RoomBean(rs.getString("r_no"),rs.getString("r_type"),rs.getString("r_size"),rs.getString("r_bedroom"),rs.getString("r_men"),rs.getString("r_content")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return list;
	}
	public int updateRoom(String no,RoomBean room) {
		int updateCounter=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement("update room set r_no=?,r_type=?, r_size=?, r_bedroom=?, r_men=?, r_content=? where r_no=?");
			pstmt.setString(1, room.getNo());
			pstmt.setString(2, room.getType());
			pstmt.setString(3, room.getSize());
			pstmt.setString(4, room.getBedroom());
			pstmt.setString(5, room.getMen());
			pstmt.setString(6, room.getContent());
			pstmt.setString(7, no);
			updateCounter=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCounter;
	}
	public ArrayList<Room_StatBean> getRoomStat(int page, int limit) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Room_StatBean> list=null;
		Room_StatBean room_StatBean=null;
		int startRow=(page-1)*limit;
		try {
			pstmt=con.prepareStatement("select * from room_stat order by no desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<Room_StatBean>();
				do {
					room_StatBean=new Room_StatBean(rs.getInt(1), rs.getInt(2),rs.getString(3));
					list.add(room_StatBean);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return list;
	}
	public int selectListCount() {
		int listCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement("select count(*) from room_stat");
			rs=pstmt.executeQuery();
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
}
