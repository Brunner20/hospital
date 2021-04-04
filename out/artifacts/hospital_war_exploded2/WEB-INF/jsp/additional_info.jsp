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
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.btn.enter" var="submit"/>
    <fmt:message bundle="${loc}" key="local.page.additional_info" var="title"/>
    <title>${title}</title>
</head>
<body>

<div class="container">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-lg-5 flex">
            <form action="Controller" method="post" >
                <input type="hidden" name="command" value="addadditionalinfo" />
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="firstname" id="floatingInput" required>
                    <label for="floatingInput">${first}</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="lastname" id="lastname" required>
                    <label for="lastname">${last}</label>
                </div>
                <div class="form-floating mb-3">
                    <label for="age" class="form-label">${age}</label>
                    <input type="number" name="age" class="form-control" id="age" required />
                </div>
                <button type="submit" class="btn btn-success m-2">Ok</button>
            </form>
        </div>
    </div>
</div>

</body>
</body>
</html>