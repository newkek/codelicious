<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Créer un nouveau contact</title>
</head>
<body>
	<form method="post" action="AddContactServlet">
		<table>
			<tr>
				<th><h2>Veuillez renseigner ici les informations de votre nouveau contact</h2></th>
				<tr>
					<td><i>First Name: <input type="text" name="firstName" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Last Name: <input type="text" name="lastName" size="25"></i></td>
				</tr>
				<tr>
					<td><i>email: <input type="text" name="email" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Street: <input type="text" name="street" size="25"></i></td>
				</tr>
				<tr>
					<td><i>City: <input type="text" name="city" size="25"></i></td>
				</tr>
				<tr>
					<td><i>zip: <input type="text" name="zip" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Country: <input type="text" name="country" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Personnal phone: <input type="text" name="personnalPhone" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Business phone: <input type="text" name="businessPhone" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Home phone: <input type="text" name="homePhone" size="25"></i></td>
				</tr>
				<tr>
					<td><i>Amis:<input type="checkbox" name="ContactGroup" value="friend"></i></td>
					<td><i>Famille:<input type="checkbox" name="ContactGroup" value="family"></i></td>
				</tr>
				<tr>
					<td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
				</tr>
			
		</table>
	</form>
</body>
</html>