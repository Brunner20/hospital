
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.btn.add" var="add"/>
    <fmt:message bundle="${loc}" key="local.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="local.staff" var="staff"/>
    <fmt:message bundle="${loc}" key="local.info_for_password" var="describ"/>
    <fmt:message bundle="${loc}" key="local.nurse" var="nurse"/>
    <fmt:message bundle="${loc}" key="local.page.registration" var="title"/>
    <title>${title}</title>
</head>
<body>

<div class="container">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-lg-5 flex">
    <form align = "center" action="Controller" method="post">
        <input type="hidden" name="command" value="addaccount" />
        <div class="form-floating mb-3">
            <input type="text" class="form-control" name="firstname" id="floatingInput" required>
            <label for="floatingInput">${first}</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" name="lastname" id="lastname" required>
            <label for="lastname">${last}</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" name="login" id="login" required>
            <label for="login">${login}</label>
        </div>
        <div class="form-floating mb-3">
            <input type="password" class="form-control" name="password" id="passIn"  aria-describedby="passwordHelpBlock" minlength="6" required>
            <label for="passIn">${password}</label>
            <div id="passwordHelpBlock" class="form-text">
                ${describ}
            </div>
        </div>
        <label for="staff" class="form-label m-2">${staff}</label>
        <select name = "staff_type"  class="form-select m-2" id="staff" >
            <option value="1">${doctor} </option>
            <option value="2">${nurse} </option>
        </select><br/>
         <button type="submit" class="btn btn-primary m-2">${add}</button>
    </form>

            <c:if test="${errorMessage != null}">
                <c:forEach var="errorMessageKey" items="${errorMessage}">
                    <fmt:message bundle="${loc}" key="${errorMessageKey}" var="messageEr"/>
                    <div class="alert alert-danger" role="alert">
                            ${messageEr}
                    </div>
                </c:forEach>
                <c:remove var="errorMessage"/>
            </c:if>

            <c:if test="${informationMessage != null}">
                <c:forEach var="errorMessageKey" items="${informationMessage}">
                    <fmt:message bundle="${loc}" key="${errorMessageKey}" var="messageInf"/>
                    <div class="alert alert-info" role="alert">
                            ${messageInf}
                    </div>
                </c:forEach>
                <c:remove var="informationMessage"/>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>