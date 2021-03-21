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

<link rel="stylesheet" href="../../css/login.css">
<body>

<div class="login-form">

    <c:if test="${requestScope.errorMessage != null}">
        <c:forEach var="error" items="${requestScope.errorMessage}">
            <div class="error">
                <h4>${error}</h4>
            </div>
        </c:forEach>
        <c:remove var="errorMessage"/>
    </c:if>


    <c:if test="${requestScope.informationMessage != null}">
        <c:forEach var="info" items="${requestScope.informationMessage}">
            <div class="information">
                <h4>${info}</h4>
            </div>
        </c:forEach>
        <c:remove var="info"/>
    </c:if>

    <div class="login-s">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="login" />
            <input type="text" name="login" required placeholder="${login}" />
            <input type="password" name="password" required placeholder="${password}" />
            <input type="submit" value="${submit}" /><br />
            <a href="Controller?command=registration">${registration} </a>
        </form>
    </div >
</div>

</body>
</html>
