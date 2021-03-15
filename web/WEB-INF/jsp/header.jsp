<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="style.css">


    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
    <fmt:message bundle="${loc}" key="header.home" var="home"/>

<br/>


<header style = "background-color:deepskyblue; height:100px">

<h1 align="center">${home}</h1>
    <form align = "right" action="Controller" method="post" class="locale">
        <input type="hidden" name="command" value="changelocale"/>
        <input type="hidden" name="lang" value="en"/>
        <input type="submit" value="${en_button}"/> <br />
    </form>

    <form align = "right" action="Controller" method="post" class="locale">
        <input type="hidden" name="command" value="changelocale"/>
        <input type="hidden" name="lang" value="ru"/>
        <input type="submit" value="${ru_button}"/> <br />
    </form>
</header>

<br/>
