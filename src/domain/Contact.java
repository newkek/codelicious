package domain;

import java.util.ArrayList;
import java.util.List;

public class Contact {

	private String firstName;
	private String lastName;
	private String email;
	private long id;
	private Address address;
	private List<PhoneNumber> phoneNumbers;
	
	public Contact(){
	}

	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress(){
		return address;
	}
	
	public void setPhone(){
		this.phoneNumbers = new ArrayList<PhoneNumber>();
	}

	public void addPhone(PhoneNumber number) {
		this.phoneNumbers.add(number);
	}
	
	
}
