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


    <form action="Controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="addadditionalinfopage" />
        ${pic}<input type="file" name="file" value="select images..."  /><br/>
        ${age}<input type="number" name="age" /><br/>
        <input type="submit" value="Ok" />
    </form>

</body>
</body>
</html>