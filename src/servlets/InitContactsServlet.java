package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.IDAOContact;

/**
 * Servlet implementation class InitContactsServlet
 */
public class InitContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitContactsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IDAOContact dao = (IDAOContact)context.getBean("DAOC");
		Contact contact = (Contact)context.getBean("FIRSTCONTACT");
		Contact contact2 = (Contact)context.getBean("SECONDCONTACT");
		try{
			dao.addContact(contact);
			dao.addContact(contact2);
			System.out.println("Init servlet done");
			request.setAttribute("response", "Initialized");
		}
		catch(Exception e){
			System.out.println("Contacts already initialized");
			request.setAttribute("response", "Already initialized");
		}

		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);

		//response.sendRedirect("main.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
