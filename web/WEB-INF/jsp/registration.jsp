<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hospital Registration</title>
</head>
<body>
   <form action="Controller" method="post">
		<input type="hidden" name="command" value="addaccount" />
		Enter Firstname:<br />
		<input type="text" name="firstname" value="" /><br />
		Enter Lastname:<br />
		<input type="text" name="lastname" value="" /><br />
	   Enter Login:<br />
	   <input type="text" name="login" value="" /><br />
	   Enter Password:<br />
	   <input type="text" name="password" value="" /><br />

	   <input type="submit" value="Отправить" /><br />
	</form>
</body>
</html>