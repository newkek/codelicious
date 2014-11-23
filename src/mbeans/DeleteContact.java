package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import utils.AppContextSingleton;
import domain.Contact;
import domain.IDAOContact;

@ManagedBean(name = "deleteContact")
@SessionScoped
public class DeleteContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long contactToDelete;
	
	
	public void delete() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		
		//dao calls
		//delete from database
		dao.deleteContact(this.contactToDelete);
		
		System.out.println("Call in delete contact, contact to delete is : "+this.contactToDelete);
		
		
		//delete from list for MVC change
		/*Iterator<Contact> it = this.searchResult.iterator();
		while (it.hasNext()) {
		  Contact curContact = it.next();
		  if (curContact.getId() == this.contactToDelete) {
			  System.out.println("found the contact to delete" + curContact.getId());
		    it.remove();
		  }
		}*/
	}
	public long getContactToDelete() {
		return contactToDelete;
	}
	public void setContactToDelete(long contactToDelete) {
		this.contactToDelete = contactToDelete;
	}

}