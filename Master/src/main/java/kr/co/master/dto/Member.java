package kr.co.master.dto;

public class Member {
	private Long id;
	private String mem_name;
	private String mem_mid;
	private String mem_pw;
	private String mem_myp;
	private String mem_email;
	private String mem_subp;
	private String mem_etc;
	private Long mem_age;
	private String mem_key;
	
	public Member() {
		
	}


	public Member(Long id, String mem_name, String mem_mid, String mem_pw, String mem_myp, String mem_email,
			String mem_subp, String mem_etc, Long mem_age, String mem_key) {
		super();
		this.id = id;
		this.mem_name = mem_name;
		this.mem_mid = mem_mid;
		this.mem_pw = mem_pw;
		this.mem_myp = mem_myp;
		this.mem_email = mem_email;
		this.mem_subp = mem_subp;
		this.mem_etc = mem_etc;
		this.mem_age = mem_age;
		this.mem_key = mem_key;
	}


	public String getMem_key() {
		return mem_key;
	}


	public void setMem_key(String mem_key) {
		this.mem_key = mem_key;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMem_name() {
		return mem_name;
	}


	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


	public String getMem_mid() {
		return mem_mid;
	}


	public void setMem_mid(String mem_mid) {
		this.mem_mid = mem_mid;
	}


	public String getMem_pw() {
		return mem_pw;
	}


	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}


	public String getMem_myp() {
		return mem_myp;
	}


	public void setMem_myp(String mem_myp) {
		this.mem_myp = mem_myp;
	}


	public String getMem_email() {
		return mem_email;
	}


	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}


	public String getMem_subp() {
		return mem_subp;
	}


	public void setMem_subp(String mem_subp) {
		this.mem_subp = mem_subp;
	}


	public String getMem_etc() {
		return mem_etc;
	}


	public void setMem_etc(String mem_etc) {
		this.mem_etc = mem_etc;
	}


	public Long getMem_age() {
		return mem_age;
	}


	public void setMem_age(Long mem_age) {
		this.mem_age = mem_age;
	}


	

	
	
	
	
}
