package vo;

import java.sql.Date;

public class Room_StatBean {
	private int no;
	private int r_no;
	private String date;
	public Room_StatBean() {}
	public Room_StatBean(int r_no, String date) {
		this.r_no=r_no;
		this.date=date;
	}
	public Room_StatBean(int no, int r_no, String date) {
		this.no=no;
		this.r_no=r_no;
		this.date=date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
