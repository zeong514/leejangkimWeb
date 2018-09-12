package kr.co.master.dto;

public class List {
	private Request request;
	private Member member;
	
	public List() {
		
	}

	public List(Request request, Member member) {
		super();
		this.request = request;
		this.member = member;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
