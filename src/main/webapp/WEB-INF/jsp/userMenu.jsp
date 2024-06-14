<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ようこそ${loginUser.name}さん<br>
	<a href="SearchBookServlet">本を検索する</a><br>
	<a href="FavoriteBookViewServlet">	登録した本を見る</a><br>
	<a href="LogoutServlet">ログアウト</a><br>
</body>
</html>