package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contact {

	protected String firstName;
	protected String lastName;
	protected String email;
	protected long id;
	protected Address address;
	protected Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
	protected Set<ContactGroup> contactGroups = new HashSet<ContactGroup>();
	public int version;
	
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
	
	public void setPhoneNumbers(Set<PhoneNumber> numbers){
		this.phoneNumbers = numbers;
	}
	
	public Set<PhoneNumber> getPhoneNumbers(){
		return phoneNumbers;
	}
	
	public void init() { 
		System.out.println("init done");
	}
	
	public void cleanup(){
		System.out.println("cleanup done");
	}

	public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

}
