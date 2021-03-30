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
    <fmt:message bundle="${loc}" key="local.add_staff" var="add"/>
    <fmt:message bundle="${loc}" key="local.page.administrator" var="title"/>
    <title>${title}</title>
</head>
<body  class="bg-info">

<h1 align="center">
    !!! ${welcome}!!!
</h1>



        <c:if test="${sessionScope.role == 'admin'}">
            <div class="d-flex flex-row flex-wrap">
                <div class="card m-2" style="width: 18rem;">
                    <div class="card-body m-2">
                        <h5 class="card-title">${add}</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="gotoaddstaffpage"/>
                            <button class="btn btn-info card-link">${add}</button>
                        </form>
                    </div>
                </div>
        </c:if>




</body>
</html>