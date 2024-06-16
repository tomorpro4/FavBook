<%@page import="model.Keyword"%>
<%@page import="model.FavBook"%>
<%@page import="model.BookList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
本の検索<br>
<form action="SearchBookServlet" method="post">
Title<br>
<input type="text" name="bookTitle">
<select name="bookTitleCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
<option value="2">前方一致</option>
<option value="3">完全一致</option>
</select><br>
ISBN<br>
<input type="text" name="isbn">
<input type="hidden" name="isbnCon"><br>
著者<br>
<input type="text" name="creatorName">
<select name="creatorNameCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
<option value="2">前方一致</option>
<option value="3">完全一致</option>
</select><br>
出版社<br>
<input type="text" name="publisherName">
<select name="publisherNameCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
<option value="2">前方一致</option>
<option value="3">完全一致</option>
</select><br>
カテゴリー<br>
<input type="text" name="categoryName">
<select name="categoryNameCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
</select><br>
検索件数<br>
<select name="maximumRecords">
<option value="10">10</option>
<option value="20">20</option>
<option value="50">50</option>
</select><br>
<input type="hidden" name="startRecord" value="1">
<button type="submit">検索</button>
</form>


<form action="RegisterFavServlet" method="post">
<% BookList bookList = (BookList)session.getAttribute("bookList"); %>
<% Keyword keyword = (Keyword)session.getAttribute("keyword"); %>

<% if(bookList != null){ %>
<%= bookList.getNumberOfRecord() %><br>
<%= bookList.getNextRecord() %><br>
<% for(int i=0;i<bookList.size();i++){ %>
<input type="hidden" name="<%= i+1 %>">
<select name="status<%= i+1 %>">
<option value="not"></option>
<option value="0">とりあえず</option>
<option value="1">ちょっと気になる</option>
<option value="2">気になる</option>
<option value="3">超気になる</option>
<option value="4">読んでる</option>
<option value="5">読み終わった</option>
<option value="6">積読</option>
</select><br>
<input type="text" name="memo<%= i+1 %>">
<%= bookList.get(i).getBookTitle() %><br>
<%= bookList.get(i).getCreator().getCreatorName() %><br>
<%= bookList.get(i).getPublisher().getPublisherName() %><br>
<% if(bookList.get(i).getStatus() != null){ %>
<%= bookList.get(i).getStatus().getStatusId() %><br>
<%= bookList.get(i).getStatus().getStatus() %><br>
<% } %>
<hr>


<% } %>
<% if(bookList.getNextRecord()-(keyword.getMaximumRecords()*2)>0){ %>
<a href="SearchBookServlet?nextPosition=<%= bookList.getNextRecord()-(keyword.getMaximumRecords()*2) %>">前へ</a>
<% } %>
<%  %>
<a href="SearchBookServlet?nextPosition=<%= bookList.getNextRecord() %>">次へ</a>
<% } %>

<button type="submit">登録</button>
</form>




</body>
</html>