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
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.my_appointment" var="my_appoint"/>
    <fmt:message bundle="${loc}" key="local.page.patient" var="title"/>
    <title>${title}</title>
</head>
<body>

<h1 align="center">
    !!! ${welcome}!!!
</h1>

<div class="content">

    <section>
        <c:if test="${sessionScope.role == 'patient'}">
            <div class ="doctor">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="gotopatientappointmentlistpage"/>
                    <input type="submit" value="${my_appoint}"/>
                </form>

                <form action="medical.history.html" method="post">
                    <input type="hidden" name="command" value=""/>
                    <input type="submit" value="Моя история болезни"/>
                </form>

        </div>
        </c:if>
    </section>

</div>

</body>
</html>