package servlets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.DAOContact;
import domain.IDAOContact;

/**
 * Servlet implementation class ModifyContactServlet
 */
public class ModifyContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyContactServlet() {
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
		if(request.getParameter("step").equals("search")){
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IDAOContact dao = (IDAOContact)context.getBean("DAOC");
			String id = request.getParameter("contactId");
			Contact c = dao.getContactById(Long.parseLong(id));
			HttpSession session = request.getSession(true);
			session.setAttribute("contact", c);
			ServletContext sc = getServletContext();
			sc.setAttribute("contact", c);
			request.setAttribute("getContactResults", c);
			request.setAttribute("restart", "false");
			RequestDispatcher rd = request.getRequestDispatcher("modifyContactS.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("step").equals("modify")){
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IDAOContact dao = (IDAOContact)context.getBean("DAOC");
			
			HttpSession session = request.getSession(false);
			
			Contact contact = (Contact) session.getAttribute("contact");
			String id=request.getParameter("id");
			System.out.println("id : "+id);
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
			if(dao.modifyContact(contact, id, prenom, nom, email, street, city, zip, country, personnalPhone, businessPhone, homePhone, contactGroups)){
				RequestDispatcher rd = request.getRequestDispatcher("modifiedContact.jsp");
				request.setAttribute("modifiedResult", "1");
				rd.forward(request, response);
			}else{
				Contact c = dao.getContactById(Long.parseLong(id));
				session.setAttribute("contact", c);
				ServletContext sc = getServletContext();
				sc.setAttribute("contact", c);
				request.setAttribute("getContactResults", c);
				request.setAttribute("restart", "true");
				RequestDispatcher rd = request.getRequestDispatcher("modifyContactS.jsp");
				rd.forward(request, response);
			}
			
		}
	}

}
