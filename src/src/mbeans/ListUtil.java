package src.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import src.util.AppContextSingleton;
import domain.IDAOContact;
import domain.ContactGroup;


@ManagedBean(name = "list")
public class ListUtil {

	private List<String> groups = new ArrayList<String>();

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

}
