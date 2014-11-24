package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import utils.AppContextSingleton;
import domain.Contact;
import domain.IDAOContact;

@ManagedBean(name = "displayContacts")
@ViewScoped
public class DisplayContacts implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Contact> contacts;
	private Contact selectedContact;
	private long contactToDelete;
	
	public DisplayContacts(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		this.contacts = dao.getContacts();
	}
	

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}


	public long getContactToDelete() {
		return contactToDelete;
	}


	public void setContactToDelete(long contactToDelete) {
		this.contactToDelete = contactToDelete;
	}
	
	public void delete() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		
		//dao calls
		//delete from database
		
		
		dao.deleteContact(this.contactToDelete);
		
		System.out.println("Call in delete contact, contact to delete is : "+this.contactToDelete);
		
		
		//delete from list for MVC change
		Iterator<Contact> it = this.contacts.iterator();
		while (it.hasNext()) {
		  Contact curContact = it.next();
		  if (curContact.getId() == this.contactToDelete) {
			  System.out.println("found the contact to delete" + curContact.getId());
		    it.remove();
		  }
		}
		
	}

}
