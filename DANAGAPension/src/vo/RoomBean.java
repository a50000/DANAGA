package vo;

import java.util.ArrayList;

public class RoomBean {
	private String no;
	private String type;
	private String size;
	private String bedroom;
	private String men;
	private String content;
	public RoomBean() {}
	public RoomBean(String no, String type, String size, String bedroom, String men, String content){
		this.no=no;
		this.type=type;
		this.size=size;
		this.bedroom=bedroom;
		this.men=men;
		this.content=content;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getBedroom() {
		return bedroom;
	}
	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}
	public String getMen() {
		return men;
	}
	public void setMen(String men) {
		this.men = men;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
