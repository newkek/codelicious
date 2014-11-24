package mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import domain.IDAOContact;

@ManagedBean(name = "addGroup")
@ViewScoped
public class AddGroup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String create() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		boolean testAjout = dao.addGroup(this.groupName); 
		if(testAjout){
			return ("displayGroups");
		}else{
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible d'ajouter le groupe.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);

			return("main");
		}

		
	}

}
