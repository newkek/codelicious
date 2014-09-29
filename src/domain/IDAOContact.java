package domain;

import java.util.ArrayList;

public interface IDAOContact {

	
	public Contact addContact(String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone);
	
	public int deleteContact(long id);
	
	public Contact getContact(long id);
	
	public boolean modifyContact(long id, String firstname, String lastname, String email);
	
	public ArrayList<Contact> getContactByFirstName(String firstname);
	
	public ArrayList<Contact> getContactByLastName(String lastname);
	
	public ArrayList<Contact> getContactByEmail(String email);
	
	public ArrayList<Contact> getContacts();
	
}
