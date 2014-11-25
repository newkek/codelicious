package domain;

import java.sql.Connection;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class DAOContact extends HibernateDaoSupport implements IDAOContact {

	public Contact addContact(Contact contact) {

		this.getHibernateTemplate().save(contact);

		return contact;
	}

	public boolean modifyContact(Contact contact, String id, String firstname,
			String lastname, String email, String street, String city,
			String zip, String country, String personalPhone,
			String businessPhone, String homePhone, List<String> contactGroups) {
		int success;

		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setEmail(email);
		Address address = contact.getAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		Set<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
		Iterator<PhoneNumber> iterator = phoneNumbers.iterator();
		HashMap<String, PhoneNumber> map = new HashMap<String, PhoneNumber>();
		while (iterator.hasNext()) {
			PhoneNumber phone = iterator.next();
			map.put(phone.getPhoneKind(), phone);
		}

		if (personalPhone != null) {
			if (!personalPhone.isEmpty()) {
				if (map.containsKey("personalPhone")) {
					if (!map.get("personalPhone").getPhoneNumber()
							.equals(personalPhone)) {
						phoneNumbers.remove(map.get("personalPhone"));
						PhoneNumber phone = new PhoneNumber();
						phone.setPhoneKind("personalPhone");
						phone.setPhoneNumber(personalPhone);
						phone.setContact(contact);
						phoneNumbers.add(phone);
					}
				} else {
					PhoneNumber phone = new PhoneNumber();
					phone.setPhoneKind("personalPhone");
					phone.setPhoneNumber(personalPhone);
					phone.setContact(contact);
					phoneNumbers.add(phone);
				}

			} else if (map.containsKey("personalPhone")) {
				phoneNumbers.remove(map.get("personalPhone"));
			}
		}
		if (businessPhone != null) {
			if (!businessPhone.isEmpty()) {
				if (map.containsKey("businessPhone")) {
					if (!map.get("businessPhone").getPhoneNumber()
							.equals(businessPhone)) {
						phoneNumbers.remove(map.get("businessPhone"));
						PhoneNumber phone = new PhoneNumber();
						phone.setPhoneKind("businessPhone");
						phone.setPhoneNumber(businessPhone);
						phone.setContact(contact);
						phoneNumbers.add(phone);
					}
				} else {
					PhoneNumber phone = new PhoneNumber();
					phone.setPhoneKind("businessPhone");
					phone.setPhoneNumber(businessPhone);
					phone.setContact(contact);
					phoneNumbers.add(phone);
				}
			} else if (map.containsKey("businessPhone")) {
				phoneNumbers.remove(map.get("businessPhone"));
			}
		}
		if (homePhone != null) {
			if (!homePhone.isEmpty()) {
				if (map.containsKey("homePhone")) {
					if (!map.get("homePhone").getPhoneNumber()
							.equals(homePhone)) {
						phoneNumbers.remove(map.get("homePhone"));
						PhoneNumber phone = new PhoneNumber();
						phone.setPhoneKind("homePhone");
						phone.setPhoneNumber(homePhone);
						phone.setContact(contact);
						phoneNumbers.add(phone);
					}
				} else {
					PhoneNumber phone = new PhoneNumber();
					phone.setPhoneKind("homePhone");
					phone.setPhoneNumber(homePhone);
					phone.setContact(contact);
					phoneNumbers.add(phone);
				}
			} else if (map.containsKey("homePhone")) {
				phoneNumbers.remove(map.get("homePhone"));
			}
		}

		Set<ContactGroup> listContactGroups = contact.getContactGroups();

		boolean formGroupIsInContactsGroup = false;

		Set<String> notCreated = new HashSet<String>();

		if (contactGroups != null) {

			notCreated.addAll(contactGroups);

			Iterator<ContactGroup> theContactIteratorGroup = listContactGroups
					.iterator();

			while (theContactIteratorGroup.hasNext()) {

				ContactGroup curContactGroup = theContactIteratorGroup.next();

				for (int i = 0; i < contactGroups.size(); i++) {

					if (curContactGroup.getGroupName().equals(
							contactGroups.get(i))) {

						notCreated.remove(contactGroups.get(i));// group not to
																// be
						// added

						formGroupIsInContactsGroup = true;

						break;
					}
				}

				if (!formGroupIsInContactsGroup) {
					// group has been removed from the contact
					ContactGroup temp = (ContactGroup) this
							.getHibernateTemplate().get(ContactGroup.class,
									curContactGroup.getGroupId());
					temp.getContacts().remove(contact);
					this.getHibernateTemplate().saveOrUpdate(temp);
					// curContactGroup.getContacts().remove(contact);

					theContactIteratorGroup.remove();

				}

			}

			Iterator<String> iteratorNotCreated = notCreated.iterator();
			while (iteratorNotCreated.hasNext()) {
				String name = iteratorNotCreated.next();
				// ContactGroup group = (ContactGroup)
				// session.createCriteria(ContactGroup.class)
				// .add(Restrictions.like("groupName", name) )
				// .uniqueResult();
				ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
						.getHibernateTemplate().findByCriteria(
								DetachedCriteria.forClass(ContactGroup.class)
										.add(Restrictions.eq("groupName",
												name)));
				ContactGroup group = null;
				if (listgroups.size() != 0) {
					//System.out.println("PAS DE GROUPS");
					group = (ContactGroup) listgroups.get(0);
				}

				if (group == null) {
					group = new ContactGroup();
					group.setGroupName(name);
				}
				Set<Contact> temp = group.getContacts();
				temp.add(contact);
				group.setContacts(temp);
				listContactGroups.add(group);
				//System.out.println("nom groupe: " + group.getGroupName());
			}
		} else {
			Iterator<ContactGroup> iteratorGroup = listContactGroups.iterator();
			while (iteratorGroup.hasNext()) {
				ContactGroup temp = iteratorGroup.next();

				temp.getContacts().remove(contact);
				iteratorGroup.remove();
			}
		}
		this.getHibernateTemplate().merge(contact);

		// this.getHibernateTemplate().saveOrUpdate(contact);

		return true;
	}

	/**
	 * Suppresion d'un contact a partir de son identifiant
	 * 
	 * @param id
	 * @return vrai si la suppression a bien ete effectuee
	 */
	public int deleteContact(long id) {
		int success = 0;

		Contact contact = (Contact) this.getHibernateTemplate().get(
				Contact.class, id);

		this.getHibernateTemplate().delete(contact);
		return success;
	}

	/**
	 * Recuperation d'un contact a partir de son identifiant
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Contact> getContact(String firstname, String lastname,
			String email) {

		DetachedCriteria critere = DetachedCriteria.forClass(Contact.class);

		if (!firstname.isEmpty()) {
			critere.add(Restrictions.eq("firstName", firstname));
		}
		if (!lastname.isEmpty()) {
			critere.add(Restrictions.eq("lastName", lastname));
		}
		if (!email.isEmpty()) {
			critere.add(Restrictions.eq("email", email));
		}

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(critere);

		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firstname
	 * 
	 * @param firstname
	 * @return
	 */
	public ArrayList<Contact> getContactByFirstName(String firstname) {

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(Contact.class).add(
								Restrictions.eq("firstName", firstname)));

		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * 
	 * @param lastname
	 * @return
	 */
	public ArrayList<Contact> getContactByLastName(String lastname) {

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(Contact.class).add(
								Restrictions.eq("lastName", lastname)));


		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * 
	 * @param email
	 * @return
	 */
	public ArrayList<Contact> getContactByEmail(String email) {

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(Contact.class).add(
								Restrictions.like("email", email + "%")));

		return contacts;
	}

	public ArrayList<Contact> getContacts() {

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().find("FROM Contact C");

		return contacts;
	}

	public void deleteAllContact() {
		// TODO Auto-generated method stub

		List<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().find("FROM Contact C");
		
		List<ContactGroup> groups = (ArrayList<ContactGroup>)this.getHibernateTemplate().find("FROM ContactGroup G");
		
		Iterator<ContactGroup> itGroup = groups.iterator();
		while(itGroup.hasNext()){
			ContactGroup c = itGroup.next();
			c.getContacts().clear();
			
			Iterator<Contact> itContact = contacts.iterator();
			while(itContact.hasNext()){
				Contact contact = itContact.next();
				if(contact.getContactGroups().contains(c)){
					contact.getContactGroups().remove(c);
				}
			}
			this.getHibernateTemplate().saveOrUpdate(c);
		}
		
		this.getHibernateTemplate().deleteAll(contacts);

	}

	public void addManyContacts() {

		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();
		for (int i = 0; i < 100000; i++) {
			Contact contact = new Contact();
			contact.setFirstName("firstname" + Integer.toString(i));
			contact.setLastName("lastnamename" + Integer.toString(i));
			contact.setEmail("email" + Integer.toString(i));
			Address address = new Address();
			address.setStreet("street" + Integer.toString(i));
			address.setCity("city" + Integer.toString(i));
			address.setZip("zip" + Integer.toString(i));
			address.setCountry("country" + Integer.toString(i));
			contact.setAddress(address);
			Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
			System.out.println("adding contact " + i);
			session.save(contact);
			if ((i % 20) == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
	}

	public ArrayList<ContactGroup> getGroups() {

		ArrayList<ContactGroup> res = (ArrayList<ContactGroup>) this
				.getHibernateTemplate().find("FROM ContactGroup C");

		return res;
	}

	public Contact addContact(String firstname, String lastname, String email,
			String street, String city, String zip, String country,
			String personalPhone, String businessPhone, String homePhone,
			List<String> contactGroups) {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setEmail(email);
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		contact.setAddress(address);
		Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		if (!personalPhone.isEmpty()) {
			PhoneNumber phone = new PhoneNumber();
			phone.setPhoneKind("personalPhone");
			phone.setPhoneNumber(personalPhone);
			phone.setContact(contact);
			phoneNumbers.add(phone);
		}
		if (!businessPhone.isEmpty()) {
			PhoneNumber phone = new PhoneNumber();
			phone.setPhoneKind("businessPhone");
			phone.setPhoneNumber(businessPhone);
			phone.setContact(contact);
			phoneNumbers.add(phone);
		}
		if (!homePhone.isEmpty()) {
			PhoneNumber phone = new PhoneNumber();
			phone.setPhoneKind("homePhone");
			phone.setPhoneNumber(homePhone);
			phone.setContact(contact);
			phoneNumbers.add(phone);
		}
		contact.setPhoneNumbers(phoneNumbers);

		Set<ContactGroup> tempcontactGroups = new HashSet<ContactGroup>();
		// pour chaque groupe coché par le client
		for (int i = 0; i < contactGroups.size(); i++) {
			// on récupère le groupe en base
			ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
					.getHibernateTemplate().findByCriteria(
							DetachedCriteria.forClass(ContactGroup.class).add(
									Restrictions.eq("groupName",
											contactGroups.get(i))));
			ContactGroup group = null;
			if (listgroups.size() != 0) {
				group = (ContactGroup) listgroups.get(0);
			}

			if (group == null) {
				group = new ContactGroup();
				group.setGroupName(contactGroups.get(i));
			}
			Set<Contact> temp = group.getContacts();
			temp.add(contact);
			group.setContacts(temp);
			tempcontactGroups.add(group);
			
		}
		if (tempcontactGroups.size() != 0) {
			contact.setContactGroups(tempcontactGroups);
		}

		
		this.getHibernateTemplate().save(contact);

		return contact;
	}

	public Company addCompany(String firstname, String lastname, String email,
			String street, String city, String zip, String country,
			String personalPhone, String businessPhone, String homePhone,
			List<String> contactGroups, String numSiret) {

		Company contact = new Company();
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setEmail(email);
		contact.setNumSiret(Integer.parseInt(numSiret));
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		contact.setAddress(address);
		Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		if (!personalPhone.isEmpty()) {
			PhoneNumber phone = new PhoneNumber();
			phone.setPhoneKind("personalPhone");
			phone.setPhoneNumber(personalPhone);
			phone.setContact(contact);
			phoneNumbers.add(phone);
		}
		if (!businessPhone.isEmpty()) {
			PhoneNumber phone = new PhoneNumber();
			phone.setPhoneKind("businessPhone");
			phone.setPhoneNumber(businessPhone);
			phone.setContact(contact);
			phoneNumbers.add(phone);
		}
		if (!homePhone.isEmpty()) {
			PhoneNumber phone = new PhoneNumber();
			phone.setPhoneKind("homePhone");
			phone.setPhoneNumber(homePhone);
			phone.setContact(contact);
			phoneNumbers.add(phone);
		}
		contact.setPhoneNumbers(phoneNumbers);

		Set<ContactGroup> tempcontactGroups = new HashSet<ContactGroup>();
		for (int i = 0; i < contactGroups.size(); i++) {
			ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
					.getHibernateTemplate().findByCriteria(
							DetachedCriteria.forClass(ContactGroup.class).add(
									Restrictions.eq("groupName",
											contactGroups.get(i))));
			ContactGroup group = null;
			if (listgroups.size() != 0) {
				group = (ContactGroup) listgroups.get(0);
			}

			if (group == null) {
				group = new ContactGroup();
				group.setGroupName(contactGroups.get(i));
			}
			Set<Contact> temp = group.getContacts();
			temp.add(contact);
			group.setContacts(temp);
			tempcontactGroups.add(group);
		}
		if (tempcontactGroups.size() != 0) {
			contact.setContactGroups(tempcontactGroups);
		}

		this.getHibernateTemplate().save(contact);

		return contact;
	}

	public ContactGroup getGroup(long id) {
		ContactGroup group = (ContactGroup) this.getHibernateTemplate().get(
				ContactGroup.class, id);
		return group;
	}

	public boolean modifyContact(Contact contact, String id, String firstname,
			String lastname, String email, String street, String city,
			String zip, String country, String personalPhone,
			String businessPhone, String homePhone, List<String> contactGroups,
			String numSiret) {
		
		//System.out.println("The name" + contact.getLastName());
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setEmail(email);
		if (contact instanceof Company) {
			((Company) contact).setNumSiret(Integer.parseInt(numSiret));
		}
		Address address = contact.getAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		Set<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
		Iterator<PhoneNumber> iterator = phoneNumbers.iterator();
		HashMap<String, PhoneNumber> map = new HashMap<String, PhoneNumber>();
		while (iterator.hasNext()) {
			PhoneNumber phone = iterator.next();
			map.put(phone.getPhoneKind(), phone);
		}

		if (personalPhone != null) {
			if (!personalPhone.isEmpty()) {
				if (map.containsKey("personalPhone")) {
					if (!map.get("personalPhone").getPhoneNumber()
							.equals(personalPhone)) {
						phoneNumbers.remove(map.get("personalPhone"));
						PhoneNumber phone = new PhoneNumber();
						phone.setPhoneKind("personalPhone");
						phone.setPhoneNumber(personalPhone);
						phone.setContact(contact);
						phoneNumbers.add(phone);
					}
				} else {
					PhoneNumber phone = new PhoneNumber();
					phone.setPhoneKind("personalPhone");
					phone.setPhoneNumber(personalPhone);
					phone.setContact(contact);
					phoneNumbers.add(phone);
				}

			} else if (map.containsKey("personalPhone")) {
				phoneNumbers.remove(map.get("personalPhone"));
			}
		}
		if (businessPhone != null) {
			if (!businessPhone.isEmpty()) {
				if (map.containsKey("businessPhone")) {
					if (!map.get("businessPhone").getPhoneNumber()
							.equals(businessPhone)) {
						phoneNumbers.remove(map.get("businessPhone"));
						PhoneNumber phone = new PhoneNumber();
						phone.setPhoneKind("businessPhone");
						phone.setPhoneNumber(businessPhone);
						phone.setContact(contact);
						phoneNumbers.add(phone);
					}
				} else {
					PhoneNumber phone = new PhoneNumber();
					phone.setPhoneKind("businessPhone");
					phone.setPhoneNumber(businessPhone);
					phone.setContact(contact);
					phoneNumbers.add(phone);
				}
			} else if (map.containsKey("businessPhone")) {
				phoneNumbers.remove(map.get("businessPhone"));
			}
		}
		if (homePhone != null) {
			if (!homePhone.isEmpty()) {
				if (map.containsKey("homePhone")) {
					if (!map.get("homePhone").getPhoneNumber()
							.equals(homePhone)) {
						phoneNumbers.remove(map.get("homePhone"));
						PhoneNumber phone = new PhoneNumber();
						phone.setPhoneKind("homePhone");
						phone.setPhoneNumber(homePhone);
						phone.setContact(contact);
						phoneNumbers.add(phone);
					}
				} else {
					PhoneNumber phone = new PhoneNumber();
					phone.setPhoneKind("homePhone");
					phone.setPhoneNumber(homePhone);
					phone.setContact(contact);
					phoneNumbers.add(phone);
				}
			} else if (map.containsKey("homePhone")) {
				phoneNumbers.remove(map.get("homePhone"));
			}
		}

		// ancien groupe du contact
		Set<ContactGroup> listContactGroups = contact.getContactGroups();

		boolean formGroupIsInContactsGroup = false;

		Set<String> notCreated = new HashSet<String>();

		// s'il existe des groupes cochés
		if (contactGroups != null) {

			// on les met tous dans notCreated
			notCreated.addAll(contactGroups);

			Iterator<ContactGroup> theContactIteratorGroup = listContactGroups
					.iterator();
			// on parcourt les anciens groups
			while (theContactIteratorGroup.hasNext()) {

				ContactGroup curContactGroup = theContactIteratorGroup.next();

				for (int i = 0; i < contactGroups.size(); i++) {
					// si on trouve la correspondance, le groupe n'a pas été modifié
					if (curContactGroup.getGroupName().equals(
							contactGroups.get(i))) {

						notCreated.remove(contactGroups.get(i));// group not to
																// be
						// added

						formGroupIsInContactsGroup = true;

						break;
					}
				}

				if (!formGroupIsInContactsGroup) {
					// group has been removed from the contact
					ContactGroup temp = (ContactGroup) this
							.getHibernateTemplate().get(ContactGroup.class,
									curContactGroup.getGroupId());
					temp.getContacts().remove(contact);
					theContactIteratorGroup.remove();
					this.getHibernateTemplate().merge(temp);
					// curContactGroup.getContacts().remove(contact);
				}
			}
			
			// on crée la correspondance avec tous les groupes qui restent de notCreated
			Iterator<String> itNewAssoc = notCreated.iterator();
			while(itNewAssoc.hasNext()){
				String gpName = itNewAssoc.next();
				ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
						.getHibernateTemplate().findByCriteria(
								DetachedCriteria.forClass(ContactGroup.class).add(
										Restrictions.eq("groupName",
												gpName)));
				
				ContactGroup group = null;
				if (listgroups.size() != 0) {
					group = (ContactGroup) listgroups.get(0);
				}

				if (group == null) {
					group = new ContactGroup();
					group.setGroupName(gpName);
				}
				Set<Contact> temp = group.getContacts();
				temp.add(contact);
				group.setContacts(temp);
				contact.getContactGroups().add(group);
				this.getHibernateTemplate().merge(group);
			}

		// pas de groupes coché et le contact a des anciens groupes, il faut les remove
		}else if(listContactGroups.size()!=0){
			Iterator<ContactGroup> itGroup = listContactGroups.iterator();
			while(itGroup.hasNext()){
				ContactGroup temp = (ContactGroup) this
						.getHibernateTemplate().get(ContactGroup.class,
								itGroup.next().getGroupId());
				temp.getContacts().remove(contact);
				itGroup.remove();
				this.getHibernateTemplate().merge(temp);
			}
		}
		
		try{
			this.getHibernateTemplate().merge(contact);
		}catch(Exception e){
			return false;
		}

		return true;
	}

	public boolean addGroup(String groupName) {
		ArrayList<ContactGroup> listTest = (ArrayList<ContactGroup>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(ContactGroup.class).add(
								Restrictions.eq("groupName", groupName)));

		if (listTest != null) {
			if (listTest.size() > 0) {
				return false;
			} else {
				ContactGroup group = new ContactGroup();
				group.setGroupName(groupName);
				this.getHibernateTemplate().save(group);
				return true;
			}

		} else {
			ContactGroup group = new ContactGroup();
			group.setGroupName(groupName);
			this.getHibernateTemplate().save(group);
			return true;
		}
	}

	public boolean modifyGroup(ContactGroup group) {

		ArrayList<ContactGroup> listTest = (ArrayList<ContactGroup>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(ContactGroup.class).add(
								Restrictions.eq("groupName",
										group.getGroupName())));

		if (listTest != null) {
			if (listTest.size() > 0) {
				return false;
			} else {
				this.getHibernateTemplate().merge(group);
			}
		} else {
			this.getHibernateTemplate().merge(group);
		}

		return true;
	}
	
	public boolean deleteGroup(ContactGroup group){

		ContactGroup contactGroup = (ContactGroup) this.getHibernateTemplate().get(ContactGroup.class, group.getGroupId());
		
		
		Iterator<Contact> iterator = contactGroup.getContacts().iterator();
		
		while(iterator.hasNext()){
			Contact contact= iterator.next();
			contact.getContactGroups().remove(contactGroup);
			iterator.remove();
			this.getHibernateTemplate().saveOrUpdate(contact);
		}
		
		this.getHibernateTemplate().delete(contactGroup);
		
		return true;
	}

	public Contact getContactById(long id) {
		Contact c = (Contact) this.getHibernateTemplate()
		.get(Contact.class, id);

		return c;
		}
	
	public boolean deleteContactFromGroup(Contact contact, ContactGroup group){
		contact.getContactGroups().remove(group);
		group.getContacts().remove(contact);
		this.getHibernateTemplate().merge(contact);
		this.getHibernateTemplate().merge(group);
		
		return true;
	}

}