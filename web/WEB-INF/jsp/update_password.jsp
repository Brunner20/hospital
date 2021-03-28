
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
    <fmt:message bundle="${loc}" key="local.old_password" var="old"/>
    <fmt:message bundle="${loc}" key="local.new_password" var="newp"/>
    <fmt:message bundle="${loc}" key="local.btn.update" var="update"/>
    <fmt:message bundle="${loc}" key="local.page.update_password" var="title"/>
    <title>${title}</title>
</head>
<body>



<form align = "center" action="Controller" method="post">
    <input type="hidden" name="command" value="updatepassword" />
    ${old}<br/>
    <input type="password" name="old_password" minlength="6" /><br/>
        ${newp}<br/>
    <input type="password" name="new_password" minlength="6" /><br />
    <input type="submit" value=  ${update} /><br />

</form>
</body>
</html>