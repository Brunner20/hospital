
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
	<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
	<fmt:message bundle="${loc}" key="local.btn.add_appointment" var="add"/>
	<fmt:message bundle="${loc}" key="local.btn.my_patients" var="my"/>
	<fmt:message bundle="${loc}" key="local.btn.get_patient" var="get"/>
	<fmt:message bundle="${loc}" key="local.btn.my_appointments" var="appoint"/>
	<fmt:message bundle="${loc}" key="local.page.staff" var="title"/>
	<title>${title}</title>
</head>
<body class="bg-info">
		<div class="d-flex flex-row flex-wrap justify-content-center">
				<c:if test="${sessionScope.role == 'doctor'}">
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${add}</h5>
							<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="gotoaddappointmentpage"/>
								<button class="btn btn-info card-link">${add}</button>
							</form>
						</div>
					</div>
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${my}</h5>
							<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="gotodoctorspatientspage"/>
								<button class="btn btn-info card-link">${my}</button>
							</form>
						</div>
					</div>
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${get}</h5>
							<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="gotofreepatientspage"/>
								<button class="btn btn-info card-link">${get}</button>
							</form>
						</div>
					</div>
				</c:if>
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${appoint}</h5>
							<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="gotostaffappointmentlistpage"/>
								<button class="btn btn-info card-link">${appoint}</button>
							</form>
						</div>
					</div>
			</div>


</body>
</html>