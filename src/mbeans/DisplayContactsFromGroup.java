package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import domain.Company;
import domain.ContactGroup;
import domain.IDAOContact;
import domain.Contact;
import domain.PhoneNumber;

@ManagedBean(name = "displayCFG")
@ViewScoped
public class DisplayContactsFromGroup implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContactGroup group;
	private List<Contact> contacts = new ArrayList<Contact>();
	private String selectedContactId;

	public DisplayContactsFromGroup() {
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		String idString = (String) context.getRequestParameterMap().get(
				"selectedGroupId");
		if (idString == null || idString.isEmpty()) {
			return;
		}
		this.group = dao.getGroup(Long.parseLong(idString));
		contacts.addAll(this.group.getContacts());
	}
	
	public ContactGroup getGroup() {
		return group;
	}

	public void setGroup(ContactGroup group) {
		this.group = group;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getSelectedContactId() {
		return selectedContactId;
	}

	public void setSelectedContactId(String selectedContactId) {
		this.selectedContactId = selectedContactId;
	}

}
