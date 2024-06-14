<%@page import="model.FavBook"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録した本の一覧</title>
</head>
<body>
登録した本の一覧<br>


<table border="1">

<% ArrayList<FavBook> favBookList = (ArrayList<FavBook>)session.getAttribute("favBookList"); %>
<%= favBookList.size() %>
<% for(int i=0;i<favBookList.size();i++){ %>

<tr>

<td><%= favBookList.get(i).getBookTitle() %></td>
<td><%= favBookList.get(i).getCreator().getCreatorName() %></td>
<td><%= favBookList.get(i).getPublisher().getPublisherName() %></td>
<td><%= favBookList.get(i).getStatus().getStatus() %></td>
<td><%= favBookList.get(i).getIsbn() %></td>
</tr>
<% } %>

</table>

</body>
</html>
