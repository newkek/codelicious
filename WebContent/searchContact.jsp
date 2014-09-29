<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Rechercher un contact</title>
</head>
<body>
	<form method="post" 
	action="<% 
		String type = request.getParameter("type");
		if(type==null){
			out.print("SearchContactServlet");
		}else{
			if(type.equals("modify"))
				out.print("ModifyContactServlet");
			if(type.equals("remove"))
				out.print("RemoveContactServlet");
		}
	%>">
	<input type="hidden" name="type" value="<%= request.getParameter("type") %>"/>
		<table>
			<tr>
				<th><h2>Veuillez renseigner ici votre critère de recherche</h2></th>
			
				<tr>
					<td><i>Recherche par First Name: <input type="text" name="firstName" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Recherche par Last Name: <input type="text" name="lastName" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Recherche par Email: <input type="text" name="email" size="25"></i></td>
				</tr>
				<tr>
					<td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
				</tr>
			
		</table>
	</form>
</body>
</html>