
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
<body>


	!!! ${welcome}!!!
	<div class="content">

		<section>
				<c:if test="${sessionScope.role == 'doctor'}">
					<div class ="doctor">
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotoaddappointmentpage"/>
							<input type="hidden" name="page" value=""/>
							<input type="submit" value="${add}"/>
						</form>

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotodoctorspatientspage"/>
							<input type="submit" value="${my}"/>
						</form>

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotofreepatientspage"/>
							<input type="submit" value="${get}"/>
						</form>
					</div>
				</c:if>
					<div>
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="gotostaffappointmentlistpage"/>
							<input type="submit" value="${appoint}"/>
						</form>
					</div>
		</section>

	</div>




</body>
</html>