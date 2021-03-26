
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="localization.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.login" var="login"/>
	<fmt:message bundle="${loc}" key="local.password" var="password"/>
	<fmt:message bundle="${loc}" key="local.firstname" var="first"/>
	<fmt:message bundle="${loc}" key="local.lastname" var="last"/>
	<fmt:message bundle="${loc}" key="registration.btn" var="registration"/>
	<fmt:message bundle="${loc}" key="local.back" var="back"/>
	<fmt:message bundle="${loc}" key="local.page.registration" var="title"/>
	<title>${title}</title>
</head>
<body>



<form align = "center" action="Controller" method="post">
		<input type="hidden" name="command" value="addaccount" />
	   ${first}<br />
		<input type="text" name="firstname" value="" /><br />
	   ${last}<br />
		<input type="text" name="lastname" value="" /><br />
	   ${login}<br />
	   <input type="text" name="login" value="" /><br />
	   ${password}<br />
	   <input type="password" name="password" minlength="6" /><br />

	   <input type="submit" value=  ${registration} /><br />
	<a href="Controller?command=gotoindexpage">${back} </a>
	</form>
</body>
</html>