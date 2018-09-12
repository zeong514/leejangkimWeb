package kr.co.master.dto;

public class Bookmark {
	private Long id;
    private Long book_kind;
    private String book_start;
    private String book_end;
    private Long mem_id;
    
    
	public Bookmark() {

	}

	public Bookmark(Long id, Long book_kind, String book_start, String book_end, Long mem_id) {
		super();
		this.id = id;
		this.book_kind = book_kind;
		this.book_start = book_start;
		this.book_end = book_end;
		this.mem_id = mem_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBook_kind() {
		return book_kind;
	}

	public void setBook_kind(Long book_kind) {
		this.book_kind = book_kind;
	}

	public String getBook_start() {
		return book_start;
	}

	public void setBook_start(String book_start) {
		this.book_start = book_start;
	}

	public String getBook_end() {
		return book_end;
	}

	public void setBook_end(String book_end) {
		this.book_end = book_end;
	}

	public Long getMem_id() {
		return mem_id;
	}

	public void setMem_id(Long mem_id) {
		this.mem_id = mem_id;
	}
    
    

}
