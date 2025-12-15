<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String choice = request.getParameter("choice");
		if (choice.equals("java")) {
			response.sendRedirect("https://www.w3schools.com/java/");
		} else if (choice.equals("python")) {
			response.sendRedirect("https://www.w3schools.com/python/");
		} else if (choice.equals("nothing")) {
			response.sendRedirect("https://www.google.com/");
		} else {
			choice = "No valid choice selected.";
		}
		out.println("<h1>Your selected choice is: " + choice + "</h1>");
	%>
</body>
</html>