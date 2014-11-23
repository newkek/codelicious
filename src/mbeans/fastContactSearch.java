package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import domain.Contact;
import domain.IDAOContact;

@ManagedBean(name = "fastContactSearch")
@SessionScoped
public class fastContactSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String criteria;
	private String lastName;
	private ArrayList<Contact> searchResult = new ArrayList<Contact>();
	

	public String search(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		//dao calls
		 this.searchResult.addAll(dao.getContact(criteria, "", ""));
		 this.searchResult.addAll(dao.getContact("", criteria, ""));
		 this.searchResult.addAll(dao.getContact("", "", criteria));
		System.out.println("first"+dao.getContact(criteria, "", ""));
		System.out.println("secondt"+dao.getContact("", criteria, ""));
		System.out.println("third"+dao.getContact("", "", criteria));
		System.out.println(this.searchResult.get(0));
		return ("fastSearchResult");
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public ArrayList<Contact> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(ArrayList<Contact> searchResult) {
		this.searchResult = searchResult;
	}
	

}