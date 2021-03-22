
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
<title>Welcome Staff</title>
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="localization.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
</head>
<body>


	!!! ${welcome}!!!
	<div class="content">

		<section>
				<c:if test="${sessionScope.role == 'doctor'}">
					<div class ="doctor">
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotoaddapointmentpage"/>
							<input type="hidden" name="page" value=""/>
							<input type="submit" value="Дать назначение пациенту"/>
						</form>

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotodoctorspatientspage"/>
							<input type="submit" value="Список моих пациентов"/>
						</form>

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotofreepatientspage"/>
							<input type="submit" value="Взять себе пациента"/>
						</form>
					</div>
				</c:if>
					<div>
						<form action="appointments.html" method="post">
							<input type="hidden" name="command" value=""/>
							<input type="submit" value="Назначения к выполнению"/>

						</form>
					</div>
		</section>

	</div>




</body>
</html>