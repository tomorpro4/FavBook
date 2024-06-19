<%@page import="model.Keyword"%>
<%@page import="model.FavBook"%>
<%@page import="model.BookList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BookList bookList = (BookList)request.getAttribute("bookList"); %>
<% Keyword keyword = (Keyword)session.getAttribute("keyword"); %>

<% String keywordTitle = ""; %>
<% String keywordIsbn = ""; %>
<% String keywordCreator = ""; %>
<% String keywordPublisher = ""; %>
<% String keywordCategory = ""; %>

<% if(keyword != null){ %>
<% keywordTitle = keyword.getTitle(); %>
<% if(keywordTitle == null) keywordTitle = ""; %>
<% keywordIsbn = keyword.getIsbn(); %>
<% if(keywordIsbn == null) keywordIsbn = ""; %>
<% keywordCreator = keyword.getCreator(); %>
<% if(keywordCreator == null) keywordCreator = ""; %>
<% keywordPublisher = keyword.getPublisher(); %>
<% if(keywordPublisher == null) keywordPublisher = ""; %>
<% keywordCategory = keyword.getCategory(); %>
<% if(keywordCategory == null) keywordCategory = ""; %>
<% } %>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="css/favbook.css">
<link href="css/bootstrap.css" rel="stylesheet" >
</head>
<body>
本の検索<br>
<form action="SearchBookServlet" method="get">
<div class="m-3">
Title<br>
<input type="text" name="bookTitle" class="form-control" value="<%= keywordTitle%>">
<select name="bookTitleCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
<option value="2">前方一致</option>
<option value="3">完全一致</option>
</select><br>
ISBN<br>
<input type="text" name="isbn" class="form-control" value="<%= keywordIsbn%>">
<input type="hidden" name="isbnCon">
著者<br>
<input type="text" name="creatorName" class="form-control" value="<%= keywordCreator%>">
<select name="creatorNameCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
<option value="2">前方一致</option>
<option value="3">完全一致</option>
</select><br>
出版社<br>
<input type="text" name="publisherName" class="form-control" value="<%= keywordPublisher%>">
<select name="publisherNameCon">
<option value="0">部分一致（AND）</option>
<option value="1">部分一致（OR）</option>
<option value="2">前方一致</option>
<option value="3">完全一致</option>
</select><br>
カテゴリー<br>
<input type="text" name="categoryName" class="form-control"  value="<%= keywordCategory%>">
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
<!--
<input type="hidden" name="startRecord" value="1">
-->
</div>
<input type="hidden" name="action" value="search">
<button type="submit" class="btn btn-primary">検索</button>
</form>


<form action="Favorite/RegisterFavServlet" method="post">

<% if(bookList != null){ %>
<%= bookList.getNumberOfRecord() %><br>
<%= bookList.getNextRecord() %><br>
<% int selectedId; %>
<% for(int i=0;i<bookList.size();i++){ %>
<input type="hidden" name="<%= i+1 %>">
<% selectedId=0; %>
<% String isbn = bookList.get(i).getIsbn(); %>

<% if(bookList.get(i).getStatus() != null){ %>
<% selectedId = bookList.get(i).getStatus().getStatusId(); %><br>
<%= bookList.get(i).getStatus().getStatus() %><br>
<% } %>
<% String[] selected = {"","","","","","","",""};  %>
<% selected[selectedId] = "selected"; %>

<% String memo = bookList.get(i).getMemo(); %>
<% if(memo == null){ %>
<% memo = ""; %>
<% } %>



<% if(!(isbn==null||isbn.equals(""))){ %>
<table border="1" class="table table-striped">
<tr>
<td rowspan="4" class="table-img">
<%="<img src=\"https://ndlsearch.ndl.go.jp/thumbnail/" %><%= bookList.get(i).getIsbn() %><%=".jpg\" style=\"float: left ;\" class=\"book-img\">" %>
</td>
<td class="table-head align-middle">
タイトル
</td>
<% } %>
<td colspan="4" class="table-text1 align-middle">
<%= bookList.get(i).getBookTitle() %>
</td>
</tr>
<tr>
<td class="align-middle">
著者
</td>
<td class="table-text2 align-middle">
<%= bookList.get(i).getCreator().getCreatorName() %>
</td>

<td class="table-head align-middle">
ISBN
</td>
<td class="table-text3 align-middle">
<%= isbn %>
</td>
</tr>
<tr>
<td class="align-middle">
出版社
</td>
<td class="align-middle">
<%= bookList.get(i).getPublisher().getPublisherName() %>

</td>
<td  colspan="2" class="table-selector align-middle">
<select name="status<%= i+1 %>">
<option value="0" <%= selected[0] %>></option>
<option value="1" <%= selected[1] %>>とりあえず</option>
<option value="2" <%= selected[2] %>>ちょっと気になる</option>
<option value="3" <%= selected[3] %>>気になる</option>
<option value="4" <%= selected[4] %>>超気になる</option>
<option value="5" <%= selected[5] %>>読んでる</option>
<option value="6" <%= selected[6] %>>読み終わった</option>
<option value="7" <%= selected[7] %>>積読</option>
</select>

</td>
</tr>
<tr>
<td colspan="4" class="align-middle">
<input type="text" name="memo<%= i+1 %>" value="<%=memo %>" class="table-input">
</td>
</tr>
</table>

<hr>

<% } %>
<button type="submit"　id="submit-button">登録</button><br>

<% if(bookList.getNextRecord()-(keyword.getMaximumRecords()*2)>0){ %>
<a href="SearchBookServlet?bookTitle=<%= keyword.getTitle() %>&bookTitleCon=<%= keyword.getTitleCon() %>&isbn=<%= keyword.getIsbn() %>&isbnCon=<%= keyword.getIsbnCon() %>&creatorName=<%= keyword.getCreator() %>&creatorNameCon=<%= keyword.getCreatorCon() %>&publisherName=<%= keyword.getPublisher() %>&publisherNameCon=<%= keyword.getPublisherCon() %>&categoryName=<%= keyword.getCategory() %>&categoryNameCon=<%= keyword.getCategoryCon() %>&maximumRecords=<%= keyword.getMaximumRecords() %>&nextPosition=<%= bookList.getNextRecord()-(keyword.getMaximumRecords()*2) %>">前へ</a>
action=search
<% }else{ %>
前へ
<% } %>
<a href="SearchBookServlet?bookTitle=<%= keyword.getTitle() %>&bookTitleCon=<%= keyword.getTitleCon() %>&isbn=<%= keyword.getIsbn() %>&isbnCon=<%= keyword.getIsbnCon() %>&creatorName=<%= keyword.getCreator() %>&creatorNameCon=<%= keyword.getCreatorCon() %>&publisherName=<%= keyword.getPublisher() %>&publisherNameCon=<%= keyword.getPublisherCon() %>&categoryName=<%= keyword.getCategory() %>&categoryNameCon=<%= keyword.getCategoryCon() %>&maximumRecords=<%= keyword.getMaximumRecords() %>&nextPosition=<%= bookList.getNextRecord() %>">次へ</a>
<% } %>
<input type="hidden" name="action" value="FavRegi">
</form>



<a href="MainMenuServlet">メインメニュー</a>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>