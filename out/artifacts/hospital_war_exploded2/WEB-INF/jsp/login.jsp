<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.btn.enter" var="submit"/>
    <fmt:message bundle="${loc}" key="local.warning.please_enter" var="please"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"/>

    <fmt:message bundle="${loc}" key="local.page.login" var="title"/>
    <title>${title}</title>
</head>

<body class="bg-info">

<div class="login-form m-5 ">

    <c:if test="${requestScope.errorMessage != null}">
            <div class="error">
                <h4><c:out value="${requestScope.errorMessage}"/></h4>
            </div>
    </c:if>


    <c:if test="${requestScope.informationMessage != null}">
            <div class="information">
                <h4><c:out value="${requestScope.informationMessage}"/></h4>
            </div>
    </c:if>

        <div class="container">
            <div class="row d-flex w-100 justify-content-center">
                <div class="col-lg-5 flex">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="login" />
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="login" id="floatingInput" required>
                        <label for="floatingInput" class="form-label">${login}</label>
                        <div class="invalid-feedback">
                            ${please} ${login}.
                        </div>
                    </div>
                    <div class="form-floating">
                        <input type="password" class="form-control" name="password" id="floatingPassword" required>
                        <label for="floatingPassword" class="form-label">${password}</label>
                        <div class="invalid-feedback">
                            ${please} ${password}.
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary m-2">${submit}</button>
                    <a href="Controller?command=registration">${registration} </a>
                </form>
                </div>
            </div>
        </div>

</div>

</body>
</html>
