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
    <title>Welcome Patient</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.age" var="age"/>
    <fmt:message bundle="${loc}" key="local.pic" var="pic"/>
    <fmt:message bundle="${loc}" key="local.submit" var="submit"/>
</head>
<body>

<form align = "center" action="Controller" method="post">
    <input type="hidden" name="command" value="addadditionalinfopage" />
    ${pic}<br />
    <input type="image" name="pic" value="" /><br />
    ${age}<br />
    <input type="number" name="age" value="" /><br />
    <input type="submit" value=  ${submit} /><br />
</form>

</body>
</body>
</html>