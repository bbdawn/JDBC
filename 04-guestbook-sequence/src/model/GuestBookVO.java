package model;
// VO : Value Object 
public class GuestBookVO {
	private int guestbookNo;
	private String title;
	private String content;
	public GuestBookVO() {
		super();		
	}
	//overloading : 방명록 글등록시 사용 ( 글번호는 시퀀스에 의해 자동 생성되므로 )
	public GuestBookVO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	//constructor overloading : 방명록 글조회시 사용 
	public GuestBookVO(int guestbookNo, String title, String content) {
		super();
		this.guestbookNo = guestbookNo;
		this.title = title;
		this.content = content;
	}
	public int getGuestbookNo() {
		return guestbookNo;
	}
	public void setGuestbookNo(int guestbookNo) {
		this.guestbookNo = guestbookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "GuestBookVO [guestbookNo=" + guestbookNo + ", title=" + title + ", content=" + content + "]";
	}
	
}




