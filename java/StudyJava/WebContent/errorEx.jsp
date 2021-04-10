<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page isErrorPage="true"%><!-- 에러 발생시 여기로 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	response.setStatus(200);
	String msg = exception.getMessage();
	%>
	error message : <%=msg %>
	<%=application.getAttribute("name") %>
</body>
</html>