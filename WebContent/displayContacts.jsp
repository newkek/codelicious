<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,domain.Contact" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Résultat de recherche</title>
</head>
<body>
<jsp:include page="main.jsp" />
<br/>
	<%
	ArrayList<Contact> list = (ArrayList<Contact>) request.getAttribute("listContacts");
	if(list.size()!=0){
	out.print("<table style=\"width:100%\">");
	out.print("<tr>");
	out.print("<td>nom</td>");
	out.print("<td>prenom</td>");
	out.print("</tr>");
		for(int i=0; i<list.size(); i++){
			Contact tmp = list.get(i);
			out.print("<tr>");
			out.print("<td>"+tmp.getLastName()+"</td>");
			out.print("<td>"+tmp.getFirstName()+"</td>");
			out.print("</tr>");
		}
	out.print("</table>");
	}else{
		out.print("pas de contact trouvé\n");
	}
	%>
</body>
</html>