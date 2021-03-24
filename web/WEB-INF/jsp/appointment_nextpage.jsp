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
    <fmt:message bundle="${loc}" key="local.date_of_completion" var="date_comp"/>
    <fmt:message bundle="${loc}" key="local.select" var="select"/>
    <fmt:message bundle="${loc}" key="local.btn.add" var="add"/>
    <fmt:message bundle="${loc}" key="local.appointment" var="appoint"/>
    <fmt:message bundle="${loc}" key="local.staff" var="st"/>
    <fmt:message bundle="${loc}" key="local.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="local.nurse" var="nurse"/>

</head>
<body>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="addappointment" />
    <input type="hidden" name="select_type" value="${requestScope.select_type}" />
    <input type="hidden" name="dateOfAppointment" value="${requestScope.dateOfAppointment}" />
    <input type="hidden" name="select_patient_id" value="${requestScope.select_patient_id}" />
    <table>

        <c:if test="${requestScope.select_type!=1}">
            <tr>
                <td>${date_comp}</td>
                <td><input type="date" name="dateOfCompletion" required/></td>
            </tr
        </c:if>
        <tr>
            <td>${st}</td>
            <td>
                <select name = "select_staff_id">
                        <optgroup label="${doctor}">
                            <c:forEach var="staff" items="${requestScope.allStaff}">
                               <c:if test="${staff.staffTypeID==1}">
                                   <option value="${staff.id}">${staff.firstname} ${staff.lastname} </option>
                               </c:if>
                            </c:forEach>
                        </optgroup>
                        <optgroup label="${nurse}">
                            <c:forEach var="staff" items="${requestScope.allStaff}">
                                <c:if test="${staff.staffTypeID==2}">
                                    <option value="${staff.id}">${staff.firstname} ${staff.lastname} </option>
                                </c:if>
                            </c:forEach>
                        </optgroup>

                </select>
            </td>
        </tr>
        <tr>
            <td>${appoint}</td>
            <td><textarea name="info" ></textarea></td>
        </tr>
    </table>
    <input type="submit" value="${add}"/><br />
</form>

</body>
</html>
