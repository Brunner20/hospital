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
    <fmt:message bundle="${loc}" key="local.preliminaryDiagnosis" var="preliminary"/>
    <fmt:message bundle="${loc}" key="local.definitiveDiagnosis" var="definitive"/>
    <fmt:message bundle="${loc}" key="local.receiptDate" var="receip"/>
    <fmt:message bundle="${loc}" key="local.dischargeDate" var="discharge"/>
    <fmt:message bundle="${loc}" key="local.date_of_appointmen" var="appointment_date"/>
    <fmt:message bundle="${loc}" key="local.date_of_completion" var="completion_date"/>
    <fmt:message bundle="${loc}" key="local.appointed" var="appoited"/>
    <fmt:message bundle="${loc}" key="local.appointment" var="appointment"/>
    <fmt:message bundle="${loc}" key="local.performed" var="performed"/>
    <fmt:message bundle="${loc}" key="local.page.medical_history" var="title"/>
    <title>${title}</title>


</head>
<body>
<div class="container m-5">
    <div class="row d-flex">
        <div class="col-lg-3 flex">
            <table class="table table-bordered border-success">
                <tr>
                    <td rowspan="2">${preliminary}</td>
                    <td rowspan="2">${definitive}</td>
                    <td rowspan="2">${receip}</td>
                    <td rowspan="2">${discharge}</td>
                    <td colspan="5" style="text-align:center">${appointment}</td>
                </tr>
                <tr>
                    <td>${appoited}</td>
                    <td>${performed}</td>
                    <td>${appointment_date}</td>
                    <td>${completion_date}</td>
                    <td>${appointment}</td>
                </tr>

                <c:forEach var="epicrisis" items="${requestScope.medicalHistory}">
                    <tr>
                        <td rowspan="${epicrisis.appointmentList.size()+1}">${epicrisis.preliminaryDiagnosis}</td>
                        <td rowspan="${epicrisis.appointmentList.size()+1}">${epicrisis.definitiveDiagnosis}</td>
                        <td rowspan="${epicrisis.appointmentList.size()+1}">${epicrisis.receiptDate}</td>
                        <td rowspan="${epicrisis.appointmentList.size()+1}">${epicrisis.dischargeDate}</td>
                    </tr>
                    <c:forEach var="item" items="${epicrisis.appointmentList}">
                        <tr>
                            <td>${item.appointingDoctorFirstname} ${item.appointingDoctorLastname}</td>
                            <td>${item.executeStaffFirstname} ${item.executeStaffLastname}</td>
                            <td>${item.dateOfAppointment}</td>
                            <td>${item.dateOfCompletion}</td>
                            <td>${item.info}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
</div>


</body>
</html>
