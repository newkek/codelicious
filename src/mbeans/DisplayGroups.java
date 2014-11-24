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
import domain.ContactGroup;
import domain.IDAOContact;

@ManagedBean(name = "displayGroups")
@ViewScoped
public class DisplayGroups implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ContactGroup> groups;
	private ContactGroup selectedGroup;
	private ContactGroup groupToDelete;
	
	public DisplayGroups(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		this.groups = dao.getGroups();
	}
	

	public List<ContactGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ContactGroup> groups) {
		this.groups = groups;
	}

	public ContactGroup getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(ContactGroup selectedGroup) {
		this.selectedGroup = selectedGroup;
	}
	
	public ContactGroup getGroupToDelete() {
		return groupToDelete;
	}

	public void setGroupToDelete(ContactGroup groupToDelete) {
		this.groupToDelete = groupToDelete;
	}
	
	public void delete() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		
		//dao calls
		//delete from database
		
		
		dao.deleteGroup(this.groupToDelete);
		
		//delete from list for MVC change
		Iterator<ContactGroup> it = this.groups.iterator();
		while (it.hasNext()) {
		  ContactGroup curGroup = it.next();
		  if (curGroup == this.groupToDelete) {
			  System.out.println("found the contact to delete" + curGroup.getGroupId());
		    it.remove();
		  }
		}
		
	}

}
