package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import utils.AppContextSingleton;
import domain.ContactGroup;
import domain.IDAOContact;

@ManagedBean(name = "addContact")
@ViewScoped
public class CreateContact implements Serializable {


	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String numSiret;
	private long id;
	private String zip;
	private String street;
	private String city;
	private String country;
	private String personalPhone;
	private String businessPhone;
	private String homePhone;
	private List<String> contactGroups = new ArrayList<String>();
	private List<String> allGroups = new ArrayList<String>();
	
	public CreateContact(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		ArrayList<ContactGroup> contactGroups = dao.getGroups();
		
		for (ContactGroup contactGroup : contactGroups) {
            this.allGroups.add(contactGroup.getGroupName());
        }
	}

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

	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getpersonalPhone() {
		return personalPhone;
	}

	public void setpersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public List<String> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(List<String> contactGroups) {
		this.contactGroups = contactGroups;
	}
	
	public List<String> getAllGroups(){
		return allGroups;
	}

	public String create() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		if(dao==null){
			System.out.println("WTF?");
			return "main";
		}
		System.out.println("modify appelé");
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(street);
		System.out.println(city);
		System.out.println(zip);
		System.out.println(country);
		System.out.println(personalPhone);
		System.out.println(businessPhone);
		System.out.println(homePhone);
		System.out.println(contactGroups.size());
		if(numSiret!=null){
			if (numSiret.isEmpty()) {
				dao.addContact(firstName, lastName, email, street, city, zip,
						country, personalPhone, businessPhone, homePhone,
						contactGroups);
			} else {
				dao.addCompany(firstName, lastName, email, street, city, zip,
						country, personalPhone, businessPhone, homePhone,
						contactGroups, numSiret);
			}
		}else{
			dao.addContact(firstName, lastName, email, street, city, zip,
					country, personalPhone, businessPhone, homePhone,
					contactGroups);
		}
		

		return ("displayContacts");
	}

}
