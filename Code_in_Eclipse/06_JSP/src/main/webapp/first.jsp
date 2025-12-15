<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo JSP</title>
</head>
<body>
	<%
		int num = Integer.parseInt(request.getParameter("number"));
	    int square = num * num;
	    out.println("<h1>The square of " + num + " is: " + square + "</h1> <br>");
	    out.println("<a href='radio.html'>Go for Choice</a>");
	%>
</body>
</html>