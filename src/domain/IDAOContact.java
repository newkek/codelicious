package domain;

import java.util.ArrayList;


public interface IDAOContact{

	
	public Contact 				addContact(String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone, String[] contactGroups);
	
	public Company				addCompany(String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone, String[] contactGroups, String numSiret);
	
	public int 					deleteContact(long id);
	
	public ArrayList<Contact> 	getContact(String firstname, String lastname, String email);
	
	public boolean 				modifyContact(long id, String firstname, String lastname, String email);
	
	public ArrayList<Contact> 	getContactByFirstName(String firstname);
	
	public ArrayList<Contact> 	getContactByLastName(String lastname);
	
	public ArrayList<Contact> 	getContactByEmail(String email);
	
	public Contact 				getContactById(long id);
	
	public ArrayList<Contact> 	getContacts();
	
	public boolean 				modifyContact(String id, String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone, String[] contactGroups);
	
	public boolean 				modifyContact(Contact c, String id, String firstname, String lastname, String email, String street, String city, String zip, String country, String personnalPhone, String businessPhone, String homePhone, String[] contactGroups);

	public void 				deleteAllContact();

	public Contact 				addContact(Contact contact);

	public void 				addManyContacts();
}
