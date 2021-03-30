
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
	<fmt:message bundle="${loc}" key="local.firstname" var="first"/>
	<fmt:message bundle="${loc}" key="local.lastname" var="last"/>
	<fmt:message bundle="${loc}" key="registration.btn" var="registration"/>
	<fmt:message bundle="${loc}" key="local.back" var="back"/>
	<fmt:message bundle="${loc}" key="local.warning.please_enter" var="please"/>
	<fmt:message bundle="${loc}" key="local.page.registration" var="title"/>
	<title>${title}</title>
</head>
<body>



<div class="container">
	<div class="row d-flex w-100 justify-content-center">
		<div class="col-5">
			<form  action="Controller" method="post">
					<input type="hidden" name="command" value="addaccount" />
					<div class="form-floating mb-3">
						<input type="text" class="form-control" name="firstname" id="floatingInput" required>
						<label for="floatingInput">${first}</label>
						<div class="invalid-feedback">
							${please} ${first}.
						</div>
					</div>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" name="lastname" id="lastname" required>
						<label for="lastname">${last}</label>
						<div class="invalid-feedback">
							${please} ${last}.
						</div>
					</div>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" name="login" id="login" required>
						<label for="login">${login}</label>
						<div class="invalid-feedback">
							${please} ${login}.
						</div>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control" name="password" id="passIn" required>
						<label for="passIn">${password}</label>
						<div class="invalid-feedback">
							${please} ${password}.
						</div>

					</div>
					<button type="submit" class="btn btn-primary">${registration}</button>
					<a href="Controller?command=gotoindexpage">${back} </a>
				</form>
		</div>
	</div>
</div>

</body>
</html>