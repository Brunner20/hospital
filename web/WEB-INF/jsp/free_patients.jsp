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
    <fmt:message bundle="${loc}" key="local.btn.next" var="select"/>
    <fmt:message bundle="${loc}" key="local.page.free_patients" var="title"/>
    <title>${title}</title>

    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<form action="Controller" method="post">
    <table cellspacing="0" id="maket">
        <tr>
           <td>${name}</td>
        </tr>
        <tr>
            <td>
                <select name = "free_patient_id" size="3" >
                <c:forEach var="patient" items="${requestScope.patientList}">
                        <option value="${patient.id}">${patient.firstname} ${patient.lastname} </option>
                </c:forEach>
                </select>
            </td>
        </tr>
    </table>

    <input type="hidden" name="command" value="gotoreceiptdatepage"/>
    <input type="submit" value="${select}"/>
</form>


</body>
</html>
