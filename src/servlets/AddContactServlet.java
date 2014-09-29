package servlets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import domain.Contact;
import domain.DAOContact;

/**
 * Servlet implementation class AddContactServlet
 */
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prenom=request.getParameter("firstName");
		String nom=request.getParameter("lastName");
		String email=request.getParameter("email");
		String street=request.getParameter("street");
		String city=request.getParameter("city");
		String zip=request.getParameter("zip");
		String country=request.getParameter("country");
		String personnalPhone=request.getParameter("personnalPhone");
		String businessPhone=request.getParameter("businessPhone");
		String homePhone=request.getParameter("homePhone");

		DAOContact dao=new DAOContact();
		
		Contact contact = dao.addContact( prenom, nom, email, street, city, zip, country, personnalPhone, businessPhone, homePhone);
		
		System.out.println("création faite nom: "+contact.getLastName()+", prenom: "+contact.getFirstName());
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		request.setAttribute("createdResult", 1);
		rd.forward(request, response);
		
	}

}
