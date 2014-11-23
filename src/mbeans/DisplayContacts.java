package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import utils.AppContextSingleton;
import domain.IDAOContact;

@ManagedBean(name = "displayContacts")
public class DisplayContacts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6338203704087131134L;
	private String firstName;
	private String lastName;
	private String email;
	private String numSiret;
	private long id;
	private String zip;
	private String street;
	private String city;
	private String country;
	private String personnalPhone;
	private String businessPhone;
	private String homePhone;
	private List<String> contactGroups = new ArrayList<String>();

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

	public String getPersonnalPhone() {
		return personnalPhone;
	}

	public void setPersonnalPhone(String personnalPhone) {
		this.personnalPhone = personnalPhone;
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

	public String create() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		if (numSiret.isEmpty()) {
			dao.addContact(firstName, lastName, email, street, city, zip,
					country, personnalPhone, businessPhone, homePhone,
					contactGroups);
		} else {
			dao.addCompany(firstName, lastName, email, street, city, zip,
					country, personnalPhone, businessPhone, homePhone,
					contactGroups, numSiret);
		}

		return ("main");
	}

}