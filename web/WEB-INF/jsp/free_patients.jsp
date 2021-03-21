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
    <title>Free patients page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.firstname" var="name"/>
    <fmt:message bundle="${loc}" key="local.select" var="select"/>

    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<form action="Controller" method="post">
    <table cellspacing="0" id="maket">
        <tr>
            <td id="leftcol">Выбрать<td><td id="rightcol">${name}</td>
        </tr>
        <c:forEach var="patient" items="${requestScope.patientList}">
            <tr>
                <td id="box">
                    <input type="checkbox" name="selected" id = "cb1" value="${patient.id}"/>
                <td>
                <td id="name">${patient.firstname} ${patient.lastname}</td>
            </tr>
        </c:forEach>
    </table>

    <input type="hidden" name="command" value="addpatientstodoctor"/>
    <input type="submit" value="${select}"/>
</form>


</body>
</html>
