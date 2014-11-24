package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import domain.Company;
import domain.ContactGroup;
import domain.IDAOContact;
import domain.Contact;
import domain.PhoneNumber;

@ManagedBean(name = "modifyContactB")
@ViewScoped
public class ModifyContactBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Contact contact;
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

	public ModifyContactBean() {
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		String idString = (String) context.getRequestParameterMap().get(
				"selectedContactId");
		System.out.println("Modify contact bean constructor"+idString);
		if (idString == null || idString.isEmpty()) {
			return;
		}
		this.contact = dao.getContactById(Long.parseLong(idString));
		
		if(this.contact instanceof Company){
			numSiret = Integer.toString(((Company) contact).getNumSiret());
		}
		
		firstName = contact.getFirstName();
		lastName = contact.getLastName();
		email = contact.getEmail();
		id = contact.getId();
		zip = contact.getAddress().getZip();
		street = contact.getAddress().getStreet();
		city = contact.getAddress().getCity();
		country = contact.getAddress().getCountry();
		Iterator<PhoneNumber> iterator = contact.getPhoneNumbers().iterator();
		while (iterator.hasNext()) {
			PhoneNumber phone = iterator.next();
			System.out.println(phone.getPhoneNumber());
			System.out.println(phone.getPhoneKind());
			if (phone.getPhoneKind().equals("personalPhone")) {
				personalPhone = phone.getPhoneNumber();
			} else if (phone.getPhoneKind().equals("businessPhone")) {
				businessPhone = phone.getPhoneNumber();
			} else if (phone.getPhoneKind().equals("homePhone")) {
				homePhone = phone.getPhoneNumber();
			}
		}
		
		contactGroups.clear();
		Iterator<ContactGroup> iterator2 = contact.getContactGroups()
				.iterator();
		while (iterator2.hasNext()) {
			ContactGroup contactGroup = iterator2.next();
			contactGroups.add(contactGroup.getGroupName());
		}
		ArrayList<ContactGroup> tempContactGroups = dao.getGroups();
		allGroups.clear();
		for (ContactGroup temp : tempContactGroups) {
			allGroups.add(temp.getGroupName());
		}


	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		System.out.println("getLastName : " + lastName);
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

	public List<String> getAllGroups() {
		return allGroups;
	}

	public void setAllGroups(List<String> allGroups) {
		this.allGroups = allGroups;
	}

	public String modify() {

		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext()
				.getBean("DAOC");
		System.out.println("modify appelé");
		System.out.println(contact.getId());
		System.out.println(id);
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
		boolean retour = dao.modifyContact(contact, Long.toString(id), firstName, lastName,
				email, street, city, zip, country, personalPhone,
				businessPhone, homePhone, contactGroups, numSiret);
		
		if(retour){
			return ("main");
		}else{
			this.contact = dao.getContactById(id);
			
			if(this.contact instanceof Company){
				numSiret = Integer.toString(((Company) contact).getNumSiret());
			}
			
			firstName = contact.getFirstName();
			lastName = contact.getLastName();
			email = contact.getEmail();
			id = contact.getId();
			zip = contact.getAddress().getZip();
			street = contact.getAddress().getStreet();
			city = contact.getAddress().getCity();
			country = contact.getAddress().getCountry();
			Iterator<PhoneNumber> iterator = contact.getPhoneNumbers().iterator();
			while (iterator.hasNext()) {
				PhoneNumber phone = iterator.next();
				System.out.println(phone.getPhoneNumber());
				System.out.println(phone.getPhoneKind());
				if (phone.getPhoneKind().equals("personalPhone")) {
					personalPhone = phone.getPhoneNumber();
				} else if (phone.getPhoneKind().equals("businessPhone")) {
					businessPhone = phone.getPhoneNumber();
				} else if (phone.getPhoneKind().equals("homePhone")) {
					homePhone = phone.getPhoneNumber();
				}
			}
			
			contactGroups.clear();
			Iterator<ContactGroup> iterator2 = contact.getContactGroups()
					.iterator();
			while (iterator2.hasNext()) {
				ContactGroup contactGroup = iterator2.next();
				contactGroups.add(contactGroup.getGroupName());
			}
			ArrayList<ContactGroup> tempContactGroups = dao.getGroups();
			allGroups.clear();
			for (ContactGroup temp : tempContactGroups) {
				allGroups.add(temp.getGroupName());
			}
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le contact a changé entre temps, vérifiez les nouvelles informations.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			
			return null;
		}

		
	}

}
