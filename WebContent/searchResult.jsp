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
<a href="addContact.jsp">Créer un nouveau Contact</a>
<br/>
<a href="addContact.jsp">Supprimer un Contact</a>
<br/>
<a href="addContact.jsp">Modifier un Contact</a>
<br/>
<a href="searchContact.jsp">Rechercher un Contact</a>
<br/>
	<%
	ArrayList<Contact> list = (ArrayList<Contact>) request.getAttribute("searchResults");
	if(list.size()!=0){
		for(int i=0; i<list.size(); i++){
			Contact tmp = list.get(i);
			out.print("nom: "+tmp.getLastName()+"\nprenom: "+tmp.getFirstName());
		}
	}else{
		out.print("pas de contact trouvé\n");
	}
	%>
</body>
</html>