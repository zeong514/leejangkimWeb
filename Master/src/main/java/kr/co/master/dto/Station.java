package kr.co.master.dto;

public class Station {
	private Long id;
	private String st_id;
	private String st_pw;
	private String st_name;
	private String st_phone;
	
	public Station() {
		
	}

	public Station(Long id, String st_id, String st_pw, String st_name, String st_phone) {
		super();
		this.id = id;
		this.st_id = st_id;
		this.st_pw = st_pw;
		this.st_name = st_name;
		this.st_phone = st_phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public String getSt_pw() {
		return st_pw;
	}

	public void setSt_pw(String st_pw) {
		this.st_pw = st_pw;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_phone() {
		return st_phone;
	}

	public void setSt_phone(String st_phone) {
		this.st_phone = st_phone;
	}
	
	

}
