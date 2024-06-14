<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	ログイン<br>
	<form action="LoginServlet" method="post">
		<input type="text" name="userId"><br>
		<input type="password" name="pass"><br>
		<button type="submit">ログイン</button><br>
	</form>
</body>
</html>