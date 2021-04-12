<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
	<%
	Cookie[] cookies = request.getCookies();
	
	if(cookies!=null){
		System.out.println(cookies);
		for(Cookie c : cookies){
			if(c.getName().equals("AAA")){
				response.sendRedirect("loginOk.jsp");
			}
		}
	}
	
	
	%>
		<form action="loginCon" method="post">
			ID : <input type="text" name="ID"><br>
			PW : <input type="password" name="PW"><br>
			<input type="submit" value="login">
		</form>
	</body>
</html>