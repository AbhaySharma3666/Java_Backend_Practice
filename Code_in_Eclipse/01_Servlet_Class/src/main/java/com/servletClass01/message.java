package com.servletClass01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class message extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));
		int sum = num1 + num2;
		
		res.setContentType("text/html"); // use text/plain + println if you want plain text
		PrintWriter pWriter = res.getWriter();
		pWriter.print("Hello " + name + "<br>");
		pWriter.print("The sum of " + num1 + " and " + num2 + " is: " + sum);
		pWriter.close();
	}

}
