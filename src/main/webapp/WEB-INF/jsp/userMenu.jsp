<%@page import="model.LoginUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% LoginUser loginuUser = (LoginUser)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(loginuUser != null){ %>
	ようこそ${loginUser.name}さん<br>
	<% }else{ %>
	ログインしていません<br>
	<% } %>
	<a href="SearchBookServlet">本を検索する</a><br>
	<a href="Favorite/FavoriteBookViewServlet">	登録した本を見る</a><br>
	<a href="LogoutServlet">ログアウト</a><br>
</body>
</html>