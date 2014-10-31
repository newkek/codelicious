package domain;

import java.util.ArrayList;

public interface IDAOContact {

	
	public Contact addContact(String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone, String[] contactGroups);
	
	public int deleteContact(long id);
	
	public Contact getContact(long id);
	
	public boolean modifyContact(long id, String firstname, String lastname, String email);
	
	public ArrayList<Contact> getContactByFirstName(String firstname);
	
	public ArrayList<Contact> getContactByLastName(String lastname);
	
	public ArrayList<Contact> getContactByEmail(String email);
	
	public Contact getContactById(long id);
	
	public ArrayList<Contact> getContacts();
	
	public void modifyContact(String id, String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone, String[] contactGroups);

	public void deleteAllContact();

	public Contact addContact(Contact contact);

	
}
