package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import utils.AppContextSingleton;
import domain.ContactGroup;
import domain.IDAOContact;

@ManagedBean(name = "displayGroups")
@ViewScoped
public class DisplayGroups implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ContactGroup> groups;
	private ContactGroup selectedGroup;
	
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

}
