package vo;



public class NoticeBean {
	private int board_no;
	private String board_subject;
	private String board_content;
	private String board_date;
	private int board_readcount;
	public NoticeBean() {}
	public NoticeBean(int int1, String string, String string2, String string3, int int2) {
		this.board_no=int1;
		this.board_subject=string;
		this.board_content=string2;
		this.board_date=string3;
		this.board_readcount=int2;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
}
