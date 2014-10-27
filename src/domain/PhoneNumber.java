package domain;

public class PhoneNumber {
	private long id;
	private String phoneKind;
	private String phoneNumber;
	private Contact contact;
	
	public PhoneNumber(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void init(){
		System.out.println("init phone done");
	}
	
	public void cleanup(){
		System.out.println("cleanup phone done");
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}


}
