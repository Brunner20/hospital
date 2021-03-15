<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"/>
</head>
<body>


    <c:if test="${requestScope.errorMessage != null}">
        <font color="red">${requestScope.errorMessage}</font>
        <c:remove var="errorMessage"/>
    </c:if>


    <c:if test="${requestScope.informationMessage != null}">
        <font color="green">${requestScope.informationMessage}</font>
        <c:remove var="informationMessage"/>
    </c:if>



    <form action="Controller" method="post">
        <input type="hidden" name="command" value="login" />
        <input type="text" name="login" placeholder="${login}" /><br />
        <input type="password" name="password" placeholder="${password}" /><br />

        <input type="submit" value="${submit}" /><br />


        <br />

        <a href="Controller?command=registration">${registration} </a>
    </form>



</body>
</html>
