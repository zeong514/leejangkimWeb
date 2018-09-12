package kr.co.master.dto;

public class Notice {
	private Long id;
	private String notice_title;
	private String notice_contents;
	private String notice_date;
	
	public Notice() {
		super();
	}

	public Notice(Long id, String notice_title, String notice_contents, String notice_date) {
		super();
		this.id = id;
		this.notice_title = notice_title;
		this.notice_contents = notice_contents;
		this.notice_date = notice_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_contents() {
		return notice_contents;
	}

	public void setNotice_contents(String notice_contents) {
		this.notice_contents = notice_contents;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	
	
}
