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
    <title>Welcome Patient</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.date_of_appointmen" var="date_app"/>
    <fmt:message bundle="${loc}" key="local.date_of_completion" var="date_comp"/>
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.select" var="select"/>
    <fmt:message bundle="${loc}" key="local.btn.next" var="submit"/>
    <fmt:message bundle="${loc}" key="local.preparations" var="prep"/>
    <fmt:message bundle="${loc}" key="local.procedure" var="procedure"/>
    <fmt:message bundle="${loc}" key="local.appointment" var="appoint"/>
    <fmt:message bundle="${loc}" key="local.surgery" var="surgery"/>
    <fmt:message bundle="${loc}" key="local.type" var="type"/>
    <fmt:message bundle="${loc}" key="local.patient" var="patient"/>
</head>
<body>

    <form action="Controller" method="post">
            <input type="hidden" name="command" value="gotoaddappointmentnextpage" />
            <table border="0">
            <tr>
                <td>${date_app}</td>
                <td><input type="date" name="dateOfAppointment" required/></td>
            </tr>
            <tr>
                <td>${patient}</td>
                <td>
                    <select name = "select_patient_id" >
                        <c:forEach var="pati" items="${requestScope.allPatients}">
                        <option value="${pati.id}">${pati.firstname} ${pati.lastname} ${pati.age}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
           <tr>
               <td>${type}</td>
               <td>
               <select name = "select_type" >
                <c:forEach var="type" items="${requestScope.types}">
                    <c:if test="${type.name().equalsIgnoreCase('PREPARATION')}">
                     <option value="${type.id}">${prep}</option>
                    </c:if>
                    <c:if test="${type.name().equalsIgnoreCase('PROCEDURE')}">
                    <option value="${type.id}">${procedure}</option>
                    </c:if>
                    <c:if test="${type.name().equalsIgnoreCase('SURGERY')}">
                    <option value="${type.id}">${surgery}</option>
                    </c:if>
                </c:forEach>
            </select>
               </td>
           </tr>
        </table>
        <input type="submit" value="${submit}" /><br />
    </form>

</body>
</html>
