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
<div class="container">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-lg-5 flex">
            <form action="Controller" method="post" >
                <input type="hidden" name="command" value="addpatientstodoctor" />
                <input type="hidden" name="free_patient_id" value="${requestScope.free_patient_id}">
                <label for="dat" class="form-label m-2">${receipt}</label>
                <input type="date" class="form-control m-2" name="receiptDate"  id="dat" required/>
                <div class="form-floating">
                    <textarea class="form-control m-2" name="preliminaryDiagnosis" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
                    <label for="floatingTextarea">${diang}</label>
                </div>
                <button type="submit" class="btn btn-primary m-2 col-md-4">Ok</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
