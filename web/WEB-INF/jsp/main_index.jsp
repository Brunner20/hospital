<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


	<title>Login Page</title>
</head>
<body>


<%
		String message = request.getParameter("message");

	if (message != null) {
	%>
	<font color="red"> <%
 	out.write(message);
 }
 %>
	</font>

	<br />

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="logination" /> Enter
		login:<br /> <input type="text" name="login" value="" /><br />
		Enter password:<br /> <input type="password" name="password" value="" /><br />

		<input type="submit" value="Отправить" /><br />
	</form>

	<br />

	<a href="Controller?command=registration">Registration</a>



</body>
</html>
