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
    <fmt:message bundle="${loc}" key="local.age" var="age"/>
    <fmt:message bundle="${loc}" key="local.pic" var="pic"/>
    <fmt:message bundle="${loc}" key="local.btn.enter" var="submit"/>
    <fmt:message bundle="${loc}" key="local.dischargeDate" var="discharge"/>
    <fmt:message bundle="${loc}" key="local.definitiveDiagnosis" var="diagnosis"/>
    <fmt:message bundle="${loc}" key="local.page.epicrisis" var="title"/>
    <title>${title}</title>
</head>
<body>


<form action="Controller" method="post" >
    <input type="hidden" name="command" value="addepicrisis" />
    <input type="hidden" name="patient_id" value="${requestScope.patient_id}">
    <table border="0">
        <tr>
            <td>
                ${discharge}
            </td>
            <td><input type="date" name="dischargeDate" required/></td>
        </tr>
        <tr>
            <td>${diagnosis}</td>
            <td><textarea name="definitiveDiagnosis" ></textarea></td>
        </tr>
    </table>
    <input type="submit" value="Ok" />

</form>

</body>
</body>
</html>