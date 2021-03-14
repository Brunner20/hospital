<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link  href="header.jsp">
    <title>Login page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
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
        <c:remove var="infoMessage"/>
    </c:if>

    <br />
    <form action="Controller" method="post" class="locale">
        <input type="hidden" name="command" value="changelocale"/>
        <input type="hidden" name="loc" value="local_en"/>
        <input type="submit" value="${en_button}"/> <br />
    </form>

    <form action="Controller" method="post" class="locale">
        <input type="hidden" name="command" value="changelocale"/>
        <input type="hidden" name="loc" value="local_ru"/>
        <input type="submit" value="${ru_button}"/> <br />
    </form>

    <br />


    <form action="Controller" method="post">
        <input type="hidden" name="command" value="login" />
        <input type="text" name="login" placeholder="${login}" /><br />
        <input type="password" name="password" placeholder="${password}" /><br />

        <input type="submit" value="${submit}" /><br />


        <br />

        <a href="Registration">${registration} </a>
    </form>



</body>
</html>
