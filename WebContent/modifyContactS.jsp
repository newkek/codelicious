<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.List,domain.Contact,domain.Address,domain.PhoneNumber" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Modifier les valeurs</title>
</head>
<body>
	<form method="post" action="ModifyContactServlet">
		<table>
			<tr>
				<% Contact c = (Contact) request.getAttribute("getContactResults"); %>
				<th><h2>Veuillez renseigner ici les nouvelles informations de ce contact</h2></th>
				<% 
					out.print("<tr>");
					out.print("<td><i>ID: "+c.getId()+"</i></td>");
					out.print("</tr>");
					
					out.print("<tr>");
					out.print("<td><i>First Name: <input type=\"text\" name=\"firstName\" size=\"25\" value=\""+ c.getFirstName()+"\"></i></td>");
					out.print("</tr>");
					
					out.print("<tr>");
					out.print("<td><i>Last Name: <input type=\"text\" name=\"lastName\" size=\"25\" value=\""+ c.getLastName()+"\"></i></td>");
					out.print("</tr>");
					
					out.print("<tr>");
					out.print("<td><i>email: <input type=\"text\" name=\"email\" size=\"25\" value=\""+ c.getEmail()+"\"></i></td>");
					out.print("</tr>");
					
					Address a = c.getAddress();
					
					out.print("<tr>");
					out.print("<td><i>Street: <input type=\"text\" name=\"street\" size=\"25\" value=\""+ a.getStreet()+"\"></i></td>");
					out.print("</tr>");
					
					out.print("<tr>");
					out.print("<td><i>City: <input type=\"text\" name=\"city\" size=\"25\" value=\""+ a.getCity()+"\"></i></td>");
					out.print("</tr>");
					
					out.print("<tr>");
					out.print("<td><i>zip: <input type=\"text\" name=\"zip\" size=\"25\" value=\""+ a.getZip()+"\"></i></td>");
					out.print("</tr>");
					
					out.print("<tr>");
					out.print("<td><i>Country: <input type=\"text\" name=\"country\" size=\"25\" value=\""+ a.getCountry()+"\"></i></td>");
					out.print("</tr>");
					
					List<PhoneNumber> phoneNumbers = (List<PhoneNumber>) c.getPhoneNumbers();
					
					out.print("<tr>");
					out.print("<td><i>Personnal phone: <input type=\"text\" name=\"personnalPhone\" size=\"25\" value=\""+ phoneNumbers.size()+"\"></i></td>");
					out.print("</tr>");
					
					request.setAttribute("id", c.getId());
					
				%>
				<tr>
					<td>
					<input type="hidden" name="step" value="modify"/>
					<input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset">
					</td>
				</tr>
			
		</table>
	</form>
</body>
</html>