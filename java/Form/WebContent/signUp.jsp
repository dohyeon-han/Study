<% request.setCharacterEncoding("UTF-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	<%!
	String mName;
	String mPW;
	%>
	<%
	mName=request.getParameter("name");
	mPW=request.getParameter("password");
	%>
	<%=mName %>
	<%=mPW %>
</body>
</html>