package mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import utils.AppContextSingleton;
import domain.Contact;
import domain.IDAOContact;
import domain.ContactGroup;


@ManagedBean(name = "list")
public class ListUtil {

	private List<String> groups = new ArrayList<String>();
	private List<ContactGroup> groups2 = new ArrayList<ContactGroup>();
	private List<Contact> contacts = new ArrayList<Contact>();
	private ContactGroup selectedGroup;
	private Contact selectedContact;
	private List<Contact> contactsFromSelectedGroup = new ArrayList<Contact>();

	public List<String> getGroups() {
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		ArrayList<ContactGroup> contactGroups = dao.getGroups();
		
		for (ContactGroup contactGroup : contactGroups) {
            groups.add(contactGroup.getGroupName());
        }
		
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	
	public List<Contact> getContacts() {
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		this.contacts = dao.getContacts();

		
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void init(){
		ApplicationContext context = AppContextSingleton.getContext();
		IDAOContact dao = (IDAOContact)context.getBean("DAOC");
		Contact contact = (Contact)context.getBean("FIRSTCONTACT");
		Contact contact2 = (Contact)context.getBean("SECONDCONTACT");
		try{
			dao.addContact(contact);
			dao.addContact(contact2);
			System.out.println("Init servlet done");
		}
		catch(Exception e){
			System.out.println("Contacts already initialized");
		}
	}

	public List<ContactGroup> getGroups2() {
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		groups2 = dao.getGroups();
		
		return groups2;
	}

	public void setGroups2(List<ContactGroup> groups2) {
		this.groups2 = groups2;
	}
	
	public String members(){
		return "displayContactsFromGroup";
	}

	public ContactGroup getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(ContactGroup selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}
	
	public List<Contact> getContactsFromSelectedGroup(){
		ArrayList<Contact> list = new ArrayList<Contact>();
		ApplicationContext context = AppContextSingleton.getContext();
		IDAOContact dao = (IDAOContact)context.getBean("DAOC");

		ContactGroup group = dao.getGroup(this.selectedGroup.getGroupId());
		System.out.println("list.getContactsFromS... 1       "+group.getContacts().size());
		this.contactsFromSelectedGroup.addAll(group.getContacts());
		System.out.println("list.getContactsFromS... 2       "+this.contactsFromSelectedGroup.size());
		return this.contactsFromSelectedGroup;
	}
	
	public void setContactsFromSelectedGroup(List<Contact> contactFromSelectedGroup){
		this.contactsFromSelectedGroup = contactFromSelectedGroup;
	}

}
