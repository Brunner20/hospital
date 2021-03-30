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
    <fmt:message bundle="${loc}" key="local.btn.medical_hsitory" var="hist"/>
    <fmt:message bundle="${loc}" key="local.page.patient" var="title"/>
    <title>${title}</title>
</head>
<body  class="bg-info">

<h1 align="center">
     ${welcome}!!!
</h1>


    <div class="d-flex flex-row flex-wrap justify-content-center">
        <c:if test="${sessionScope.role == 'patient'}">
            <div class="card m-2" style="width: 18rem;">
                <div class="card-body m-2">
                    <h5 class="card-title">${my_appoint}</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="gotopatientappointmentlistpage"/>
                        <button class="btn btn-info card-link">${my_appoint}</button>
                    </form>
                </div>
            </div>
            <div class="card m-2" style="width: 18rem;">
                <div class="card-body m-2">
                    <h5 class="card-title">${hist}</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="gotomedicalhistorypage"/>
                        <button class="btn btn-info card-link">${hist}</button>
                    </form>
                </div>
            </div>
        </c:if>
</div>
</body>
</html>