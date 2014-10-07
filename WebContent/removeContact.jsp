<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Supprimer un contact</title>
</head>
<body>
<jsp:include page="main.jsp" />
<h2>Recherchez le contact à supprimer</h2>
<jsp:include page="searchContact.jsp" >
	<jsp:param name="type" value="remove" />
</jsp:include>



</body>
</html>