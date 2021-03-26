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
    <fmt:message bundle="${loc}" key="local.receiptDate" var="receipt"/>
    <fmt:message bundle="${loc}" key="local.preliminaryDiagnosis" var="diang"/>
    <fmt:message bundle="${loc}" key="local.page.additional_info" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="Controller" method="post" >
    <input type="hidden" name="command" value="addpatientstodoctor" />
    <input type="hidden" name="free_patient_id" value="${requestScope.free_patient_id}">
    <table border="0">
        <tr>
            <td>
                ${receipt}
            </td>
            <td><input type="date" name="receiptDate" required/></td>
        </tr>
        <tr>
            <td>
                ${diang}
            </td>
            <td>
                <textarea name="preliminaryDiagnosis" ></textarea>
            </td>
        </tr>

    </table>
    <input type="submit" value="Ok" />
</form>
</body>
</html>
