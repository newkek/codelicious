package servlets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.Company;
import domain.DAOContact;
import domain.IDAOContact;

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
		String[] contactGroups = request.getParameterValues("ContactGroup");
		String numSiret = request.getParameter("numSiret");
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		IDAOContact dao = (IDAOContact)context.getBean("DAOC");
		if(numSiret.isEmpty()){
			//Contact contact = dao.addContact( prenom, nom, email, street, city, zip, country, personnalPhone, businessPhone, homePhone, contactGroups);
		}else{
			//Company company = dao.addCompany( prenom, nom, email, street, city, zip, country, personnalPhone, businessPhone, homePhone, contactGroups, numSiret);
		}

		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		request.setAttribute("createdResult", "1");
		rd.forward(request, response);
		
	}

}
