<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page errorPage="errorEx.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%!int num = 10;
	String str = "jsp";
	ArrayList<String> list = new ArrayList<String>();%>
	<%-- jsp 주석 파일 변환 과정에서 사라짐--%>

	<%
	if (num > 0) {
	%>
	<p>
		over<%=num%></p>
	<%
	} else {
	%>
	<p>
		less<%=num%></p>
	<%
	}
	%>
	<%@ include file="include.jsp"%>


	<%!String adminId, adminPw;
	String imgDir, testServerIP, realServerIP;
	String s;%>
	<%
	adminId = config.getInitParameter("adminId");
	adminPw = config.getInitParameter("adminPw");

	imgDir = application.getInitParameter("imgDir");/*다른 jsp에서도 사용 가능! */
	testServerIP = application.getInitParameter("testServerIP");
	realServerIP = application.getInitParameter("realServerIP");
	application.setAttribute("name", "Han");/*다른 jsp에서 name변수 사용 가능 */
	
	%>
	<div>
		<%=adminId%>
		<%=adminPw%>
	</div>
	<div>
		<%=imgDir%>
		<%=testServerIP%>
		<%=realServerIP%>
		<%=(String)application.getAttribute("name") %>
	</div>

	<%-- <%/* 에러 생성 */
	out.print(s.toString());
	%> --%>
</body>
</html>