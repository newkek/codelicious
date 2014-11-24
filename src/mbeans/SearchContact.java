package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import domain.Contact;
import domain.IDAOContact;

@ManagedBean(name = "searchContact")
@SessionScoped
public class SearchContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private long contactToDelete;
	private String criteria;
	
	private ArrayList<Contact> searchResult ;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String search() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		//dao calls
		if(this.searchResult==null){
			this.searchResult = new ArrayList<Contact>();
		}
		this.searchResult.clear();
		this.searchResult = dao.getContact(firstName, lastName, email);
		
		return ("searchResult");
	}
	
	public String fastSearch(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		//dao calls
		if(this.searchResult==null){
			this.searchResult = new ArrayList<Contact>();
			
		}
		this.searchResult.clear();
		this.searchResult.addAll(dao.getContact(criteria, "", ""));
		this.searchResult.addAll(dao.getContact("", criteria, ""));
		this.searchResult.addAll(dao.getContact("", "", criteria));
		//System.out.println("first"+dao.getContact(criteria, "", ""));
		//System.out.println("secondt"+dao.getContact("", criteria, ""));
		//System.out.println("third"+dao.getContact("", "", criteria));
		//System.out.println(this.searchResult.get(0));
		return ("searchResult");
	}
	
	
	public ArrayList<Contact> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(ArrayList<Contact> searchResult) {
		this.searchResult = searchResult;
	}
	
	public String delete() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		
		//dao calls
		//delete from database
		
		//long contactRecup = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactToDelete"));
		
		dao.deleteContact(this.contactToDelete);
		
		System.out.println("Call in delete contact, contact to delete is : "+this.contactToDelete);
		
		
		//delete from list for MVC change
		Iterator<Contact> it = this.searchResult.iterator();
		while (it.hasNext()) {
		  Contact curContact = it.next();
		  if (curContact.getId() == this.contactToDelete) {
			  System.out.println("found the contact to delete" + curContact.getId());
		    it.remove();
		  }
		}
		
		if(this.searchResult.size()==0){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Plus de contact à afficher", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			
			return "main";
		}else{
			return null;
		}
		
	}
	public long getContactToDelete() {
		return contactToDelete;
	}
	public void setContactToDelete(long contactToDelete) {
		this.contactToDelete = contactToDelete;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

}
