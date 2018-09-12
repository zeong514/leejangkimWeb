package kr.co.master.dto;

public class Meskey {
	private Long id;
	private String key;
	private Long mem_id;
	
	public Meskey() {
		
	}

	public Meskey(Long id, String key, Long mem_id) {
		super();
		this.id = id;
		this.key = key;
		this.mem_id = mem_id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getMem_id() {
		return mem_id;
	}
	public void setMem_id(Long mem_id) {
		this.mem_id = mem_id;
	}
	
	
}
