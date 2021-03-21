<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
    <fmt:message bundle="${loc}" key="header.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="header.lang" var="lang"/>
    <fmt:message bundle="${loc}" key="header.profile" var="profile"/>


<link rel="stylesheet" href="../../css/header.css">


<header >

<h1 align="center">${home}</h1>

    <ul>
         <li>
            <form action="Controller" method="post" class="locale">
                <input type="hidden" name="command" value="changelocale"/>
                <input type="hidden" name="lang" value="en"/>
                <input type="submit" value="${en_button}"/>
            </form>
        </li>
        <li>
            <form action="Controller" method="post" class="locale">
                <input type="hidden" name="command" value="changelocale"/>
                <input type="hidden" name="lang" value="ru"/>
                <input type="submit" value="${ru_button}"/>
            </form>
        </li>
    </ul>
    <ul>
        <c:if test="${sessionScope.auth == true}">
        <li><a href="Controller?command=logout">${logout}</a></li>
            <li><a href="Controller?command=gotoprofilepage">${profile}</a></li>
        </c:if>
        <c:if test="${(sessionScope.auth == false || sessionScope.auth == null) && sessionScope.url != 'Controller?command=gotoindexpage'}">
            <li><a href="Controller?command=gotoindexpage"><fmt:message bundle="${loc}" key="header.login"/></a></li>
        </c:if>
    </ul>
</header>


