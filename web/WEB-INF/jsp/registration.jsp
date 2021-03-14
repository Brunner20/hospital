
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Registration page</title>
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="localization.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.login" var="login"/>
	<fmt:message bundle="${loc}" key="local.password" var="password"/>
	<fmt:message bundle="${loc}" key="local.firstname" var="first"/>
	<fmt:message bundle="${loc}" key="local.lastname" var="last"/>
	<fmt:message bundle="${loc}" key="registration.btn" var="registration"/>
</head>
<body>


<br />
<form action="Controller" method="post" class="locale">
	<input type="hidden" name="command" value="changelocale"/>
	<input type="hidden" name="loc" value="eng"/>
	<input type="submit" value="${en_button}"/> <br />
</form>

<form action="Controller" method="post" class="locale">
	<input type="hidden" name="command" value="changelocale"/>
	<input type="hidden" name="loc" value="rus"/>
	<input type="submit" value="${ru_button}"/> <br />
</form>

<br />


<form align = "center" action="Controller" method="post">
		<input type="hidden" name="command" value="addaccount" />
	   ${first}<br />
		<input type="text" name="firstname" value="" /><br />
	   ${last}<br />
		<input type="text" name="lastname" value="" /><br />
	   ${login}<br />
	   <input type="text" name="login" value="" /><br />
	   ${password}<br />
	   <input type="password" name="password" value="" /><br />

	   <input type="submit" value=  ${registration} /><br />
	</form>
</body>
</html>