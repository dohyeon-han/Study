<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="signUp.jsp" method="post">
		name :
		<input type="text" name="name">
		<br> pw :
		<input type="password" name="password">
		<br> hobby : 운동
		<input type="checkbox" name="hobby" value="운동">
		노래
		<input type="checkbox" name="hobby" value="노래">
		게임
		<input type="checkbox" name="hobby" value="게임">
		코딩
		<input type="checkbox" name="hobby" value="코딩">
		<br> 성별 : man
		<input type="radio" name="gender" value="man" checsked="checked">
		woman
		<input type="radio" name="gender" value="woman">
		<br>
		<input type="submit" value="전송">
	</form>
</body>
</html>