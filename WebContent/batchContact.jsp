<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Batch Processing</title>
</head>
<body>
<form method="post" action="BatchContactServlet">
	Ajout 100 000 contacts dans base :<input type="checkbox" name="BatchChoice" value="addMany"><br/>
	Autre :<input type="checkbox" name="BatchChoice" value="other1"><br/>
	Autreautre : <input type="checkbox" name="BatchChoice" value="other2"><br/>
	<input class="button" type="submit" value="Submit" />
</form>

</body>
</html>