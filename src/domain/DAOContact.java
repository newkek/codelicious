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

	/**
	 * Rajoute un contact dans la base de donnees.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param country
	 * @param zip
	 * @param city
	 * @param street
	 * @param homePhone
	 * @param businessPhone
	 * @param personalPhone
	 * @return renvoit le nouveau contact
	 */

	public Contact addContact(String firstname, String lastname, String email,
			String street, String city, String zip, String country,
			String personalPhone, String businessPhone, String homePhone,
			String[] contactGroups) {

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

		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession(); //d��marrer
		 * une transaction session.beginTransaction();
		 */
		// persister l���objet
		/*
		 * Iterator<ContactGroup> iterator = tempcontactGroups.iterator();
		 * while(iterator.hasNext()){ session.save(iterator.next()); }
		 */

		Set<ContactGroup> tempcontactGroups = new HashSet<ContactGroup>();
		for (int i = 0; i < contactGroups.length; i++) {
			ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
					.getHibernateTemplate().findByCriteria(
							DetachedCriteria.forClass(ContactGroup.class).add(
									Restrictions.like("groupName",
											contactGroups[i])));
			ContactGroup group = null;
			if (listgroups.size() != 0) {
				System.out.println("PAS DE GROUPS");
				group = (ContactGroup) listgroups.get(0);
			}

			if (group == null) {
				group = new ContactGroup();
				group.setGroupName(contactGroups[i]);
			}
			Set<Contact> temp = group.getContacts();
			temp.add(contact);
			group.setContacts(temp);
			tempcontactGroups.add(group);
		}
		if (tempcontactGroups.size() != 0) {
			contact.setContactGroups(tempcontactGroups);
		}

		// session.close();

		this.getHibernateTemplate().save(contact);
		// recharger l���objet �� partir de la session
		// contact=(Contact) session.load(Contact.class,contact.getId());
		// committer la transaction
		// session.getTransaction().commit();

		return contact;
	}

	public Company addCompany(String firstname, String lastname, String email,
			String street, String city, String zip, String country,
			String personalPhone, String businessPhone, String homePhone,
			String[] contactGroups, String numSiret) {

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

		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession(); //d��marrer
		 * une transaction session.beginTransaction();
		 */

		// persister l���objet
		/*
		 * Iterator<ContactGroup> iterator = tempcontactGroups.iterator();
		 * while(iterator.hasNext()){ session.save(iterator.next()); }
		 */
		/*
		 * Set<ContactGroup> tempcontactGroups = new HashSet<ContactGroup>();
		 * for(int i=0;i<contactGroups.length; i++){ ContactGroup group =
		 * (ContactGroup) session.createCriteria(ContactGroup.class)
		 * .add(Restrictions.like("groupName", contactGroups[i]) )
		 * .uniqueResult(); if(group == null){ group = new ContactGroup();
		 * group.setGroupName(contactGroups[i]); } Set<Contact> temp =
		 * group.getContacts(); temp.add(contact); group.setContacts(temp);
		 * tempcontactGroups.add(group); }
		 * contact.setContactGroups(tempcontactGroups);
		 */

		this.getHibernateTemplate().save(contact);

		return contact;
	}

	public Contact addContact(Contact contact) {
		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession(); //d��marrer
		 * une transaction session.beginTransaction();
		 */
		// persister l���objet
		/*
		 * Iterator<ContactGroup> iterator =
		 * contact.getContactGroups().iterator(); while(iterator.hasNext()){
		 * session.save(iterator.next()); }
		 */
		/*
		 * session.save(contact); //recharger l���objet �� partir de la session
		 * contact=(Contact) session.load(Contact.class,contact.getId());
		 * //committer la transaction session.getTransaction().commit();
		 */
		this.getHibernateTemplate().save(contact);

		return contact;
	}

	public boolean modifyContact(String id, String firstname, String lastname,
			String email, String street, String city, String zip,
			String country, String personalPhone, String businessPhone,
			String homePhone, String[] contactGroups) {
		int success;/*
					 * Session session =
					 * HibernateUtil.getSessionFactory().getCurrentSession();
					 * session.getTransaction().begin(); // Query q =
					 * session.createQuery
					 * ("UPDATE Contact set FIRSTNAME = '"+firstname
					 * +"' and LASTNAME = '"
					 * +lastname+"' and EMAIL = '"+email+"' and STREET = '"
					 * +street+"' and EMAIL = '"+email+"' and "); //
					 * q.setParameter("id", id); // success=q.executeUpdate();
					 * String hq1 = "FROM Contact C WHERE C.id=\'"+id+"\'";
					 * Contact contact = (Contact)
					 * session.createQuery(hq1).list().get(0); //Contact contact
					 * = (Contact) session.load(Contact.class, id); //Contact
					 * contact = (Contact) session.load(Contact.class,
					 * Long.parseLong(id)); contact.setFirstName(firstname);
					 * contact.setLastName(lastname); contact.setEmail(email);
					 * Address address = contact.getAddress();
					 * address.setStreet(street); address.setCity(city);
					 * address.setZip(zip); address.setCountry(country);
					 * Set<PhoneNumber> phoneNumbers =
					 * contact.getPhoneNumbers(); Iterator<PhoneNumber> iterator
					 * = phoneNumbers.iterator(); HashMap<String,PhoneNumber>
					 * map = new HashMap<String, PhoneNumber>();
					 * while(iterator.hasNext()){ PhoneNumber phone =
					 * iterator.next(); map.put(phone.getPhoneKind(), phone); }
					 * 
					 * if(personalPhone!=null){ if(!personalPhone.isEmpty()){
					 * if(map.containsKey("personalPhone")){
					 * if(!map.get("personalPhone"
					 * ).getPhoneNumber().equals(personalPhone)){
					 * phoneNumbers.remove(map.get("personalPhone"));
					 * PhoneNumber phone = new PhoneNumber();
					 * phone.setPhoneKind("personalPhone");
					 * phone.setPhoneNumber(personalPhone);
					 * phone.setContact(contact); phoneNumbers.add(phone); }
					 * }else{ PhoneNumber phone = new PhoneNumber();
					 * phone.setPhoneKind("personalPhone");
					 * phone.setPhoneNumber(personalPhone);
					 * phone.setContact(contact); phoneNumbers.add(phone); }
					 * 
					 * }else if(map.containsKey("personalPhone")){
					 * phoneNumbers.remove(map.get("personalPhone")); } }
					 * if(businessPhone!=null){ if(!businessPhone.isEmpty()){
					 * if(map.containsKey("businessPhone")){
					 * if(!map.get("businessPhone"
					 * ).getPhoneNumber().equals(businessPhone)){
					 * phoneNumbers.remove(map.get("businessPhone"));
					 * PhoneNumber phone = new PhoneNumber();
					 * phone.setPhoneKind("businessPhone");
					 * phone.setPhoneNumber(businessPhone);
					 * phone.setContact(contact); phoneNumbers.add(phone); }
					 * }else{ PhoneNumber phone = new PhoneNumber();
					 * phone.setPhoneKind("businessPhone");
					 * phone.setPhoneNumber(businessPhone);
					 * phone.setContact(contact); phoneNumbers.add(phone); }
					 * }else if(map.containsKey("businessPhone")){
					 * phoneNumbers.remove(map.get("businessPhone")); } }
					 * if(homePhone!=null){ if(!homePhone.isEmpty()){
					 * if(map.containsKey("homePhone")){
					 * if(!map.get("homePhone")
					 * .getPhoneNumber().equals(homePhone)){
					 * phoneNumbers.remove(map.get("homePhone")); PhoneNumber
					 * phone = new PhoneNumber();
					 * phone.setPhoneKind("homePhone");
					 * phone.setPhoneNumber(homePhone);
					 * phone.setContact(contact); phoneNumbers.add(phone); }
					 * }else{ PhoneNumber phone = new PhoneNumber();
					 * phone.setPhoneKind("homePhone");
					 * phone.setPhoneNumber(homePhone);
					 * phone.setContact(contact); phoneNumbers.add(phone); }
					 * }else if(map.containsKey("homePhone")){
					 * phoneNumbers.remove(map.get("homePhone")); } }
					 * 
					 * Set<ContactGroup> tempContactGroups =
					 * contact.getContactGroups(); Iterator<ContactGroup>
					 * iteratorGroup = tempContactGroups.iterator(); boolean
					 * test=false; Set<String> notCreated = new
					 * HashSet<String>(); Collections.addAll(notCreated,
					 * contactGroups); while(iteratorGroup.hasNext()){
					 * ContactGroup temp = iteratorGroup.next();
					 * 
					 * for(int i=0;i<contactGroups.length; i++){
					 * 
					 * if(temp.getGroupName().equals(contactGroups[i])){
					 * notCreated.remove(contactGroups[i]); test=true; break; }
					 * } if(!test){ temp.getContacts().remove(contact);
					 * iteratorGroup.remove(); } }
					 * 
					 * Iterator<String> iteratorNotCreated =
					 * notCreated.iterator();
					 * while(iteratorNotCreated.hasNext()){ String name =
					 * iteratorNotCreated.next(); ContactGroup group =
					 * (ContactGroup) session.createCriteria(ContactGroup.class)
					 * .add(Restrictions.like("groupName", name) )
					 * .uniqueResult(); if(group == null){ group = new
					 * ContactGroup(); group.setGroupName(name); } Set<Contact>
					 * temp = group.getContacts(); temp.add(contact);
					 * group.setContacts(temp); tempContactGroups.add(group);
					 * System.out.println("nom groupe: "+group.getGroupName());
					 * }
					 * 
					 * //contact.setPhoneNumbers(phoneNumbers);
					 * //contact.setAddress(address);
					 * session.saveOrUpdate(contact);
					 * session.getTransaction().commit();
					 */
		return true;
	}

	public boolean modifyContact(Contact contact, String id, String firstname,
			String lastname, String email, String street, String city,
			String zip, String country, String personalPhone,
			String businessPhone, String homePhone, String[] contactGroups) {
		int success;

		System.out.println("The name" + contact.getLastName());
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

			Collections.addAll(notCreated, contactGroups);

			Iterator<ContactGroup> theContactIteratorGroup = listContactGroups
					.iterator();

			while (theContactIteratorGroup.hasNext()) {

				ContactGroup curContactGroup = theContactIteratorGroup.next();

				for (int i = 0; i < contactGroups.length; i++) {

					if (curContactGroup.getGroupName().equals(contactGroups[i])) {

						notCreated.remove(contactGroups[i]);// group not to be
															// added

						formGroupIsInContactsGroup = true;

						break;
					}
				}

				if (!formGroupIsInContactsGroup) {
					// group has been removed from the contact
					curContactGroup.getContacts().remove(contact);

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
										.add(Restrictions.like("groupName",
												name)));
				ContactGroup group = null;
				if (listgroups.size() != 0) {
					System.out.println("PAS DE GROUPS");
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
				System.out.println("nom groupe: " + group.getGroupName());
			}
		} else {
			Iterator<ContactGroup> iteratorGroup = listContactGroups.iterator();
			while (iteratorGroup.hasNext()) {
				ContactGroup temp = iteratorGroup.next();

				temp.getContacts().remove(contact);
				iteratorGroup.remove();
			}
		}

		this.getHibernateTemplate().saveOrUpdate(contact);

		return true;
	}

	public boolean modifyContact(Contact contact, String id, String firstname,
			String lastname, String email, String street, String city,
			String zip, String country, String personalPhone,
			String businessPhone, String homePhone, List<String> contactGroups) {
		int success;

		System.out.println("The name" + contact.getLastName());
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
										.add(Restrictions.like("groupName",
												name)));
				ContactGroup group = null;
				if (listgroups.size() != 0) {
					System.out.println("PAS DE GROUPS");
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
				System.out.println("nom groupe: " + group.getGroupName());
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
		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); Contact contact = (Contact)
		 * session.get(Contact.class, id); //Query q =
		 * session.createQuery("DELETE FROM Contact WHERE id = :id");
		 * //q.setParameter("id", id); //success=q.executeUpdate();
		 * session.delete(contact); session.getTransaction().commit();
		 */
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

		// System.out.println("dao.getContact "+contacts.size() +
		// contacts.get(0).getLastName());

		return contacts;
	}

	/**
	 * Methode qui modifie les coordonees d'un contact
	 * 
	 * @param id
	 * @param firstname
	 * @param alstname
	 * @param email
	 * @return
	 */
	public boolean modifyContact(long id, String firstname, String lastname,
			String email) {
		boolean success = false;
		Connection con = null;
		// try{
		// Class.forName(Messages.getString("driver"));
		// con = DriverManager.getConnection(Messages.getString("database"),
		// Messages.getString("username"), Messages.getString("password"));
		// Statement stmt = con.createStatement();
		// String sqlFirstName =
		// "UPDATE contacts SET firstname = "+"'"+firstname+"'"+" WHERE id = "+id
		// ;
		// String sqlLastName =
		// "UPDATE contacts SET lastname = "+"'"+lastname+"'"+" WHERE id = "+id
		// ;
		// String sqlEmail =
		// "UPDATE contacts SET email = "+"'"+email+"'"+" WHERE id = "+id ;
		//
		// if(firstname != "")stmt.executeUpdate(sqlFirstName);
		// if(lastname != "")stmt.executeUpdate(sqlLastName);
		// if(email != "")stmt.executeUpdate(sqlEmail);
		//
		// success = true;
		// stmt.close();
		// con.close();
		//
		// } catch( Exception e ){
		// e.printStackTrace();
		// }
		return success;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firstname
	 * 
	 * @param firstname
	 * @return
	 */
	public ArrayList<Contact> getContactByFirstName(String firstname) {
		// String hq1 = "FROM Contact C WHERE C.firstName=\'"+firstname+"\'";

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(Contact.class).add(
								Restrictions.eq("firstName", firstname)));

		return contacts;
	}

	public Contact getContactById(long id) {
		Contact c = (Contact) this.getHibernateTemplate()
				.get(Contact.class, id);
		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); //Contact c = (Contact)
		 * session.load(Contact.class, id); String hq1 =
		 * "FROM Contact C WHERE C.id=\'"+id+"\'"; Contact c = (Contact)
		 * session.createQuery(hq1).list().get(0);
		 * session.getTransaction().commit();
		 * System.out.println(c.getPhoneNumbers().size());
		 */
		return c;
	}

	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * 
	 * @param lastname
	 * @return
	 */
	public ArrayList<Contact> getContactByLastName(String lastname) {

		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction();
		 */

		/*
		 * ArrayList<Contact> contacts = (ArrayList<Contact>)
		 * session.createCriteria(Contact.class)
		 * .add(Restrictions.like("lastName", lastname)).list();
		 */

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(Contact.class).add(
								Restrictions.eq("lastName", lastname)));

		// session.getTransaction().commit();
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * 
	 * @param email
	 * @return
	 */
	public ArrayList<Contact> getContactByEmail(String email) {
		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction();
		 * 
		 * ArrayList<Contact> contacts = (ArrayList<Contact>)
		 * session.createCriteria(Contact.class) .add(Restrictions.like("email",
		 * email)).list();
		 */
		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(Contact.class).add(
								Restrictions.like("email", email + "%")));

		// session.getTransaction().commit();
		return contacts;
	}

	public ArrayList<Contact> getContacts() {
		// TODO Auto-generated method stub
		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); String hq1 = "FROM Contact C";
		 * ArrayList<Contact> contacts = (ArrayList<Contact>)
		 * session.createQuery(hq1).list(); session.getTransaction().commit();
		 */

		ArrayList<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().find("FROM Contact C");

		return contacts;
	}

	public void deleteAllContact() {
		// TODO Auto-generated method stub

		List<Contact> contacts = (ArrayList<Contact>) this
				.getHibernateTemplate().find("FROM Contact C");
		this.getHibernateTemplate().deleteAll(contacts);

		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); Query q =
		 * session.createQuery("DELETE FROM Contact"); q.executeUpdate();
		 * session.getTransaction().commit();
		 */
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
			/*
			 * if(!personalPhone.isEmpty()){ PhoneNumber phone = new
			 * PhoneNumber(); phone.setPhoneKind("personalPhone");
			 * phone.setPhoneNumber(personalPhone); phone.setContact(contact);
			 * phoneNumbers.add(phone); } if(!businessPhone.isEmpty()){
			 * PhoneNumber phone = new PhoneNumber();
			 * phone.setPhoneKind("businessPhone");
			 * phone.setPhoneNumber(businessPhone); phone.setContact(contact);
			 * phoneNumbers.add(phone); } if(!homePhone.isEmpty()){ PhoneNumber
			 * phone = new PhoneNumber(); phone.setPhoneKind("homePhone");
			 * phone.setPhoneNumber(homePhone); phone.setContact(contact);
			 * phoneNumbers.add(phone); } contact.setPhoneNumbers(phoneNumbers);
			 */
			/*
			 * Set<ContactGroup> tempcontactGroups = new
			 * HashSet<ContactGroup>(); for(int i=0;i<contactGroups.length;
			 * i++){ ContactGroup group = new ContactGroup();
			 * group.setGroupName(contactGroups[i]); Set<Contact> temp =
			 * group.getContacts(); temp.add(contact); group.setContacts(temp);
			 * tempcontactGroups.add(group); }
			 * contact.setContactGroups(tempcontactGroups);
			 */
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
		for (int i = 0; i < res.size(); i++) {
			System.out.println("Group : " + res.get(i).getGroupName());
		}

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

		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession(); //d��marrer
		 * une transaction session.beginTransaction();
		 */
		// persister l���objet
		/*
		 * Iterator<ContactGroup> iterator = tempcontactGroups.iterator();
		 * while(iterator.hasNext()){ session.save(iterator.next()); }
		 */

		Set<ContactGroup> tempcontactGroups = new HashSet<ContactGroup>();
		for (int i = 0; i < contactGroups.size(); i++) {
			ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
					.getHibernateTemplate().findByCriteria(
							DetachedCriteria.forClass(ContactGroup.class).add(
									Restrictions.like("groupName",
											contactGroups.get(i))));
			ContactGroup group = null;
			if (listgroups.size() != 0) {
				System.out.println("PAS DE GROUPS");
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

		// session.close();

		this.getHibernateTemplate().save(contact);
		// recharger l���objet �� partir de la session
		// contact=(Contact) session.load(Contact.class,contact.getId());
		// committer la transaction
		// session.getTransaction().commit();

		return contact;
	}

	public Company addCompany(String firstname, String lastname, String email,
			String street, String city, String zip, String country,
			String personalPhone, String businessPhone, String homePhone,
			List<String> contactGroups, String numSiret) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
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

		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession(); //d��marrer
		 * une transaction session.beginTransaction();
		 */
		// persister l���objet
		/*
		 * Iterator<ContactGroup> iterator = tempcontactGroups.iterator();
		 * while(iterator.hasNext()){ session.save(iterator.next()); }
		 */

		Set<ContactGroup> tempcontactGroups = new HashSet<ContactGroup>();
		for (int i = 0; i < contactGroups.size(); i++) {
			ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>) this
					.getHibernateTemplate().findByCriteria(
							DetachedCriteria.forClass(ContactGroup.class).add(
									Restrictions.like("groupName",
											contactGroups.get(i))));
			ContactGroup group = null;
			if (listgroups.size() != 0) {
				System.out.println("PAS DE GROUPS");
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
		// recharger l���objet �� partir de la session
		// contact=(Contact) session.load(Contact.class,contact.getId());
		// committer la transaction
		// session.getTransaction().commit();

		return contact;
	}

	public ContactGroup getGroup(long id) {
		// TODO Auto-generated method stub
		ContactGroup group = (ContactGroup) this.getHibernateTemplate().get(
				ContactGroup.class, id);
		System.out.println("dao.getGroup " + group.getContacts().size());
		return group;
	}

	public boolean modifyContact(Contact contact, String id, String firstname,
			String lastname, String email, String street, String city,
			String zip, String country, String personalPhone,
			String businessPhone, String homePhone, List<String> contactGroups,
			String numSiret) {
		// TODO Auto-generated method stub
		System.out.println("The name" + contact.getLastName());
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
					this.getHibernateTemplate().merge(temp);
					// curContactGroup.getContacts().remove(contact);

					theContactIteratorGroup.remove();

				}

			}

			/*
			 * Iterator<String> iteratorNotCreated = notCreated.iterator();
			 * while (iteratorNotCreated.hasNext()) { String name =
			 * iteratorNotCreated.next(); // ContactGroup group = (ContactGroup)
			 * // session.createCriteria(ContactGroup.class) //
			 * .add(Restrictions.like("groupName", name) ) // .uniqueResult();
			 * ArrayList<ContactGroup> listgroups = (ArrayList<ContactGroup>)
			 * this .getHibernateTemplate().findByCriteria(
			 * DetachedCriteria.forClass(ContactGroup.class)
			 * .add(Restrictions.like("groupName", name))); ContactGroup group =
			 * null; if (listgroups.size() != 0) {
			 * System.out.println("PAS DE GROUPS"); group = (ContactGroup)
			 * listgroups.get(0); }
			 * 
			 * if (group == null) { group = new ContactGroup();
			 * group.setGroupName(name); } Set<Contact> temp =
			 * group.getContacts(); temp.add(contact); group.setContacts(temp);
			 * listContactGroups.add(group); System.out.println("nom groupe: " +
			 * group.getGroupName()); } } else { Iterator<ContactGroup>
			 * iteratorGroup = listContactGroups.iterator(); while
			 * (iteratorGroup.hasNext()) { ContactGroup temp =
			 * iteratorGroup.next();
			 * 
			 * temp.getContacts().remove(contact); iteratorGroup.remove(); }
			 */
		}
		this.getHibernateTemplate().merge(contact);

		// this.getHibernateTemplate().saveOrUpdate(contact);

		return true;
	}

	public boolean addGroup(String groupName) {
		ArrayList<ContactGroup> listTest = (ArrayList<ContactGroup>) this
				.getHibernateTemplate().findByCriteria(
						DetachedCriteria.forClass(ContactGroup.class).add(
								Restrictions.like("groupName", groupName)));
		
		if (listTest != null) {
			if(listTest.size()>0){
				return false;
			}else{
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
	
	public boolean modifyGroup(ContactGroup group){
		this.getHibernateTemplate().merge(group);
		
		return true;
	}

}
