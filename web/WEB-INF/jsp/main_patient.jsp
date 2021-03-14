<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome Patient</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
</head>
<body>

<h1 align="center">
    !!! Welcome Dear Patient!!!
</h1>

<br/>

<a href="Controller?command=logout">logout</a>
</body>
</html>