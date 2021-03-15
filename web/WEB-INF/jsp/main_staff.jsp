
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
	<h1 align="center">
	</h1>
	My Patients
	<table border="1">
		<c:forEach var="patient" items="${requestScope.patientList}">
			<tr>
				<td>
					<font size="18" color="blue">
						<c:out value="${patient.firstname}" />
						<c:out value="${patient.lastname}" />
					</font></td>
			</tr>
		</c:forEach>
	</table>
	
	<br/>
	
	<a href="Controller?command=logout">${logout}</a>
</body>
</html>