package vo;

import java.sql.Date;

public class ReservationBean {
	private int re_no;
	private String r_no;
	private String re_name;
	private String re_men;
	private String re_date;
	private String re_night;
	private String re_price;
	private Date delay;
	private String pay_yn;


	public String getPay_yn() {
		return pay_yn;
	}



	public void setPay_yn(String pay_yn) {
		this.pay_yn = pay_yn;
	}



	public Date getDelay() {
		return delay;
	}



	public void setDelay(Date delay) {
		this.delay = delay;
	}



	public ReservationBean() {
		super();
	}

	

	public ReservationBean(int re_no, String r_no, String re_name, String re_men, String re_date, String re_night,
			String re_price) {
		super();
		this.re_no = re_no;
		this.r_no = r_no;
		this.re_name = re_name;
		this.re_men = re_men;
		this.re_date = re_date;
		this.re_night = re_night;
		this.re_price = re_price;
	}



	public int getRe_no() {
		return re_no;
	}


	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}


	public String getR_no() {
		return r_no;
	}


	public void setR_no(String r_no) {
		this.r_no = r_no;
	}


	public String getRe_name() {
		return re_name;
	}


	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}


	public String getRe_men() {
		return re_men;
	}


	public void setRe_men(String re_men) {
		this.re_men = re_men;
	}


	public String getRe_date() {
		return re_date;
	}


	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}


	public String getRe_night() {
		return re_night;
	}


	public void setRe_night(String re_night) {
		this.re_night = re_night;
	}


	public String getRe_price() {
		return re_price;
	}


	public void setRe_price(String re_price) {
		this.re_price = re_price;
	}
	
	
	
}
