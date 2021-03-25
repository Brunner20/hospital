<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.error.wrong_in_catch" var="error"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.page.error" var="title"/>
    <title>${title}</title>
</head>
<body>

<form action="Controller" method="post" class="locale">
    <input type="hidden" name="command" value="changelocale"/>
<%--    <input type="hidden" name="page" value="${pageContext.request.requestURI}"/>--%>
    <input type="submit" value="${en_button}"/> <br />
</form>

<form action="Controller" method="post" class="locale">
    <input type="hidden" name="command" value="changelocale"/>
<%--    <input type="hidden" name="page" value="${pageContext.request.requestURI}"/>--%>
    <input type="submit" value="${ru_button}"/> <br />
</form>

<c:out value = "${error}" />

<br />

<a href="Controller?command=logout">${logout}</a>
</body>
</html>