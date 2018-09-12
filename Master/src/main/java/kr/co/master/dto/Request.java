package kr.co.master.dto;

public class Request {
	private Long id;
	private Long li_kind;
	private String li_station;
	private Long li_year;
	private Long li_month;
	private Long li_day;
	private Long li_hour;
	private Long li_minute;
	private String li_post;
	private Long li_appr;
	private Long mem_id;
	private String li_otherSta;
	
	public Request() {
		
	}

	public Request(Long id, Long li_kind, String li_station, Long li_year, Long li_month, Long li_day, Long li_hour,
			Long li_minute, String li_post, Long li_appr, Long mem_id, String li_otherSta) {
		super();
		this.id = id;
		this.li_kind = li_kind;
		this.li_station = li_station;
		this.li_year = li_year;
		this.li_month = li_month;
		this.li_day = li_day;
		this.li_hour = li_hour;
		this.li_minute = li_minute;
		this.li_post = li_post;
		this.li_appr = li_appr;
		this.mem_id = mem_id;
		this.li_otherSta = li_otherSta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLi_kind() {
		return li_kind;
	}

	public void setLi_kind(Long li_kind) {
		this.li_kind = li_kind;
	}

	public String getLi_station() {
		return li_station;
	}

	public void setLi_station(String li_station) {
		this.li_station = li_station;
	}

	public Long getLi_year() {
		return li_year;
	}

	public void setLi_year(Long li_year) {
		this.li_year = li_year;
	}

	public Long getLi_month() {
		return li_month;
	}

	public void setLi_month(Long li_month) {
		this.li_month = li_month;
	}

	public Long getLi_day() {
		return li_day;
	}

	public void setLi_day(Long li_day) {
		this.li_day = li_day;
	}

	public Long getLi_hour() {
		return li_hour;
	}

	public void setLi_hour(Long li_hour) {
		this.li_hour = li_hour;
	}

	public Long getLi_minute() {
		return li_minute;
	}

	public void setLi_minute(Long li_minute) {
		this.li_minute = li_minute;
	}

	public String getLi_post() {
		return li_post;
	}

	public void setLi_post(String li_post) {
		this.li_post = li_post;
	}

	public Long getLi_appr() {
		return li_appr;
	}

	public void setLi_appr(Long li_appr) {
		this.li_appr = li_appr;
	}

	public Long getMem_id() {
		return mem_id;
	}

	public void setMem_id(Long mem_id) {
		this.mem_id = mem_id;
	}

	public String getLi_otherSta() {
		return li_otherSta;
	}

	public void setLi_otherSta(String li_otherSta) {
		this.li_otherSta = li_otherSta;
	}

	

	
	
	
	
	
	
	
}
