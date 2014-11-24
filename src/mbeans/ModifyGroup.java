package mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import domain.Contact;
import domain.ContactGroup;
import domain.IDAOContact;

@ManagedBean(name = "modifyGroup")
@ViewScoped
public class ModifyGroup implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private ContactGroup group;
	private String groupName;
	private List<Contact> contacts;
	
	public ModifyGroup(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		String idString = (String) context.getRequestParameterMap().get(
				"selectedGroupId");
		if (idString == null || idString.isEmpty()) {
			return;
		}
		System.out.println(idString);
		this.group = dao.getGroup(Long.parseLong(idString));
		this.groupName = this.group.getGroupName();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String modify() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		
		boolean testMod = dao.modifyGroup(this.group); 
		if(!testMod){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible de modifier le nom du groupe.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}

		return null;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public ContactGroup getGroup() {
		return group;
	}

	public void setGroup(ContactGroup group) {
		this.group = group;
	}
}
