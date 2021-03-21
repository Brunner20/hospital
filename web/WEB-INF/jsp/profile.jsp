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
    <title>Profile page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.age" var="age"/>
    <fmt:message bundle="${loc}" key="profile.status.main" var="status"/>
    <fmt:message bundle="${loc}" key="profile.status.discharged" var="dis"/>
    <fmt:message bundle="${loc}" key="profile.status.on_treatment" var="treatment"/>
    <fmt:message bundle="${loc}" key="profile.attending_doctor" var="attending_doctor"/>
    <fmt:message bundle="${loc}" key="profile.status.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="profile.status.nurse" var="nurse"/>
</head>
<body>
<div class="profile">
    <c:if test="${sessionScope.role=='patient'}">
    <table cellspacing="0" id="maket" border="0">

        <tr>
            <td>${first}<td>
            <td>${requestScope.patient.firstname}</td>
        </tr>
        <tr>
            <td>${last}<td>
            <td>${requestScope.patient.lastname}</td>
        </tr>
        <tr>
            <td>${age}<td>
            <td>${requestScope.patient.age}</td>
        </tr>
        <tr>
            <td>${status}<td>
            <c:if test="${requestScope.patient.statusID == 1}">
                <td>${treatment}</td>
            </c:if>
            <c:if test="${requestScope.patient.statusID == 2}">
                <td>${dis}</td>
            </c:if>
        </tr>
        <tr>
            <td>${attending_doctor}<td>
            <td>${requestScope.attendingDoctor.firstname} ${requestScope.attendingDoctor.lastname}</td>
        </tr>
    </c:if>
    <c:if test="${sessionScope.role=='doctor'|| sessionScope.role=='nurse'}">
        <table cellspacing="0" id="maket" border="0">

            <tr>
                <td>${first}<td>
                <td>${requestScope.staff.firstname}</td>
            </tr>
            <tr>
                <td>${last}<td>
                <td>${requestScope.staff.lastname}</td>
            </tr>
            <tr>
                <td>${status}<td>
                <c:if test="${requestScope.staff.staffTypeID==1}">
                    <td>${doctor}</td>
                </c:if>
                <c:if test="${requestScope.staff.staffTypeID==2}">
                    <td>${nurse}</td>
                </c:if>
            </tr>
            </c:if>
</div >
</body>
</html>
