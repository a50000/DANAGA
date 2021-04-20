package vo;

public class MemberBean {
	private String id;
	private String name;
	private String passwd;
	private String email;
	private String ph_no;
	private String grade;
	public MemberBean() {}	
	public MemberBean(String id, String name, String passwd, String email, String ph_no, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.email = email;
		this.ph_no = ph_no;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPh_no() {
		return ph_no;
	}
	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}	
}
