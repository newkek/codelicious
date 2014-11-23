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
	private boolean test=false;

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
	
	public void resetContacts(){
		ApplicationContext context = AppContextSingleton.getContext();
		IDAOContact dao = (IDAOContact)context.getBean("DAOC");
		this.contacts = dao.getContacts();
	}
	
	public void resetContactsFromGroup(){
		ApplicationContext context = AppContextSingleton.getContext();
		IDAOContact dao = (IDAOContact)context.getBean("DAOC");

		/*
		ContactGroup group = dao.getGroup(this.selectedGroup.getGroupId());
		System.out.println("list.getContactsFromS... 1       "+group.getContacts().size());
		this.contactsFromSelectedGroup.addAll(group.getContacts());
		System.out.println("list.getContactsFromS... 2       "+this.contactsFromSelectedGroup.size());
		*/
		this.contactsFromSelectedGroup.addAll(this.selectedGroup.getContacts());
	}
	
	public void resetGroups(){
		ApplicationContext context = AppContextSingleton.getContext();
		IDAOContact dao = (IDAOContact)context.getBean("DAOC");
		this.groups2 = dao.getGroups();
		if(this.selectedGroup!=null){
			System.out.println(this.selectedGroup.getGroupName());
		}
		
	}
	
	public List<Contact> getContacts() {
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
			System.out.println("Init bean done");
		}
		catch(Exception e){
			System.out.println("Contacts already initialized");
		}
	}

	public List<ContactGroup> getGroups2() {
		if(!test){
			this.resetGroups();
		}
		return groups2;
	}

	public void setGroups2(List<ContactGroup> groups2) {
		this.groups2 = groups2;
	}

	public ContactGroup getSelectedGroup() {
		System.out.println(this.selectedGroup.getGroupName());
		return this.selectedGroup;
	}

	public void setSelectedGroup(ContactGroup selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public Contact getSelectedContact() {
		return this.selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}
	
	
	public List<Contact> getContactsFromSelectedGroup(){
		return this.contactsFromSelectedGroup;
	}
	
	public void setContactsFromSelectedGroup(List<Contact> contactFromSelectedGroup){
		this.contactsFromSelectedGroup = contactFromSelectedGroup;
	}

}
