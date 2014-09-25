package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;

import util.HibernateUtil;

public class DAOContact implements IDAOContact{

	/**
	 * Rajoute un contact dans la base de donnees.
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return renvoit le nouveau contact
	 */
	public Contact addContact(String firstname, String lastname, String email){

		Contact contact = new Contact();
		Contact createdContact;
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setEmail(email);
		
		System.out.println("dao contact");

		Connection con = null;
//		try{
//			Class.forName(Messages.getString("driver")); 
//			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
//			Statement stmt = con.createStatement();
//			String request = "INSERT INTO contacts(id, firstname,lastname,email) VALUES("+idContact +", '"+firstname+"','"+lastname+"','"+email+"')";
//			stmt.executeUpdate(request);
//			stmt.close();   	
//			con.close();
//		} catch( Exception e ){
//			e.printStackTrace();
//		}
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//démarrer une transaction
		session.beginTransaction();
		//persister l’objet
		session.save(contact);
		//recharger l’objet à partir de la session
		createdContact=(Contact) session.load(Contact.class,
		contact.getId());
		//committer la transaction
		session.getTransaction().commit();

		return contact;
	}

	/**
	 * Suppresion d'un contact a partir de son identifiant
	 * @param id
	 * @return vrai si la suppression a bien ete effectuee
	 */
	public int deleteContact(long id){
		int success=0;
		Connection con = null;
//		try{
//			Class.forName(Messages.getString("driver")); 
//			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
//			Statement stmt = con.createStatement();
//			String request = "DELETE FROM contacts WHERE id = "+id; 
//			success = stmt.executeUpdate(request);
//			stmt.close();
//			con.close();
//			
//		} catch( Exception e ){
//			e.printStackTrace();
//		}

		return success;
	}

	/**
	 * Recuperation d'un contact a partir de son identifiant
	 * @param id
	 * @return
	 */
	public Contact getContact(long id){
		ResultSet rec = null;
		Contact contact = new Contact();
		Connection con = null;
//		try{
//			Class.forName(Messages.getString("driver")); 
//			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
//			Statement stmt = con.createStatement();
//			rec = stmt.executeQuery("SELECT * FROM contacts WHERE id = "+id); 
//
//			while (rec.next()) {
//				contact.setId(Long.parseLong(rec.getString("id"))); 
//				contact.setFirstName(rec.getString("firstname")); 
//				contact.setLastName(rec.getString("lastname")); 
//				contact.setEmail(rec.getString("email")); 
//			}
//
//			stmt.close();
//			rec.close();
//			con.close();
//
//		} catch( Exception e ){
//			e.printStackTrace();
//		}
		return contact;
	}

	/**
	 * Methode qui modifie les coordonees d'un contact
	 * @param id
	 * @param firstname
	 * @param alstname
	 * @param email
	 * @return
	 */
	public boolean modifyContact(long id, String firstname, String lastname, String email){
		boolean success = false;
		Connection con = null;
//		try{
//			Class.forName(Messages.getString("driver")); 
//			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
//			Statement stmt = con.createStatement();
//			String sqlFirstName = "UPDATE contacts SET firstname = "+"'"+firstname+"'"+" WHERE id = "+id ; 
//			String sqlLastName = "UPDATE contacts SET lastname = "+"'"+lastname+"'"+" WHERE id = "+id ; 
//			String sqlEmail = "UPDATE contacts SET email = "+"'"+email+"'"+" WHERE id = "+id ; 
//
//			if(firstname != "")stmt.executeUpdate(sqlFirstName); 
//			if(lastname != "")stmt.executeUpdate(sqlLastName); 
//			if(email != "")stmt.executeUpdate(sqlEmail); 
//
//			success = true;
//			stmt.close();
//			con.close();
//
//		} catch( Exception e ){
//			e.printStackTrace();
//		}
		return success;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firrstname
	 * @param firstname
	 * @return
	 */
	public ArrayList<Contact> getContactByFirstName(String firstname){

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		
		System.out.println("search contact by first name : "+firstname);
		
		/*try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE firstname = "+"'"+firstname+"'"); 

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setId(Long.parseLong(rec.getString("id"))); 
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email")); 
				
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}*/
		
	
		return contacts;
	}


	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * @param lastname
	 * @return
	 */
	public ArrayList<Contact> getContactByLastName(String lastname){

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		/*try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE lastname = "+"'"+lastname+"'"); 

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setId(Long.parseLong(rec.getString("id"))); 
				contact.setFirstName(rec.getString("firstname")); 
				contact.setLastName(rec.getString("lastname")); 
				contact.setEmail(rec.getString("email")); 
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}*/
		Contact contact2 = new Contact();
		contact2.setFirstName("a");
		contact2.setLastName("a");
		contact2.setEmail("a");
		contacts.add(contact2);
		Contact contact3 = new Contact();
		contact3.setFirstName("b");
		contact3.setLastName("b");
		contact3.setEmail("b");
		contacts.add(contact3);
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * @param email
	 * @return
	 */
	public ArrayList<Contact> getContactByEmail(String email){
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		/*try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE email = "+"'"+email+"'"); 

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setId(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname")); 
				contact.setEmail(rec.getString("email"));
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}*/
		return contacts;
	}
	


}
