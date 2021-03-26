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
    <fmt:message bundle="${loc}" key="local.firstname" var="name"/>
    <fmt:message bundle="${loc}" key="local.btn.discharge" var="discharge"/>
    <fmt:message bundle="${loc}" key="local.page.my_patients" var="title"/>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<table cellspacing="0" id="maket">
    <tr>
        <td> ${name}</td>
    </tr>
    <c:forEach var="patient" items="${requestScope.patientList}">
        <tr>
            <td>
                <a href="Controller?command=gotomedicalhistorypage">${patient.firstname} ${patient.lastname}</a>
            <td>
            <td>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="gotoepicrisispage"/>
                    <input type="hidden" name="patient_id" value="${patient.id}"/>
                    <input type="submit" value="${discharge}"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
