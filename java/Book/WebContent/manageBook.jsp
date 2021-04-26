<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="dbBook" method="post">
			<div>
				번호 : <input type="text" name="id">
				책 이름 : <input type="text" name="book_name">
				저자 : <input type="text" name="author">
			</div>
			<input type="submit">
		</form>
		<form action="dbModify" method="post">
			<div>
				번호 : <input type="text" name="id">
				책 이름 : <input type="text" name="book_name">
				저자 : <input type="text" name="author">
			</div>
			<input type="submit">
		</form>
		<input type="button" value="목록" onclick="location.href='dbSelect'">
	</body>
</html>