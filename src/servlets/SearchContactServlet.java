package servlets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.DAOContact;
import domain.IDAOContact;

/**
 * Servlet implementation class SearchContactServlet
 */
public class SearchContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchContactServlet() {
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
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		IDAOContact dao = (IDAOContact)context.getBean("DAOC");

		int nbfields = 0;
		String choosen = new String();
		String field = new String();
		ArrayList<Contact> results = new ArrayList<Contact>();
		
		if (prenom.isEmpty() && nom.isEmpty() && email.isEmpty()){
			//error, the client filled multiple fields
			System.out.println("error fields null");
			response.sendRedirect("searchContact.jsp");
		}else{
			results = dao.getContact(prenom, nom, email);
			request.setAttribute("searchResults", results);

			RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
			rd.forward(request, response);
		}
		
		
		/*
		if (!prenom.isEmpty()){
			nbfields += 1;
			choosen = "FirstName";
			field = prenom;
		}
		if (!nom.isEmpty()){
			nbfields += 1;
			choosen = "LastName";
			field = nom;
		}
		if (!email.isEmpty()){
			nbfields += 1;
			choosen = "Email";
			field = email;
		}
		if (nbfields > 1){
			//error, the client filled multiple fields
			System.out.println("error fields");
		}
		else{
			try {
				Class c = Class.forName("domain.DAOContact");
				Constructor constr = c.getConstructor();
				Object o = constr.newInstance();

				java.lang.reflect.Method method = c.getMethod("getContactBy"+choosen, String.class);
				results = (ArrayList<Contact>) method.invoke(o, field); 
				request.setAttribute("searchResults", results);

				RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
				rd.forward(request, response);
				
				


			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}

