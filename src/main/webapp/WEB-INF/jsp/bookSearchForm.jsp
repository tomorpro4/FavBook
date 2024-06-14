<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
本の検索<br>
<form>
Title<br>
<input type="text" name="bookTitle">
<select name="bookTitleCon">
<option value="0">部分一致</option>
<option value="1">前方一致</option>
<option value="2">後方一致</option>
<option value="3">完全一致</option>
</select><br>
ISBN<br>
<input type="text" name="isbn">
<select name="isbnCon">
<option value="0">部分一致</option>
<option value="1">前方一致</option>
<option value="2">後方一致</option>
<option value="3">完全一致</option>
</select><br>
著者<br>
<input type="text" name="creatorName">
<select name="creatorNameCon">
<option value="0">部分一致</option>
<option value="1">前方一致</option>
<option value="2">後方一致</option>
<option value="3">完全一致</option>
</select><br>
出版社<br>
<input type="text" name="publisherName">
<select name="publisherNameCon">
<option value="0">部分一致</option>
<option value="1">前方一致</option>
<option value="2">後方一致</option>
<option value="3">完全一致</option>
</select><br>
カテゴリー<br>
<input type="text" name="categoryName">
<select name="categoryNameCon">
<option value="0">部分一致</option>
<option value="1">前方一致</option>
<option value="2">後方一致</option>
<option value="3">完全一致</option>
</select><br>
<button type="submit">検索</button>


</form>

</body>
</html>