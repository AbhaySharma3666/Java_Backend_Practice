package com.RD;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class servlet2 extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		res.setContentType("text/html"); // use text/plain + println if you want plain text
		String name = (String) req.getAttribute("Name");
		int age = (int) req.getAttribute("Age");
		int num1 = (int) req.getAttribute("Number1");
		int num2 = (int) req.getAttribute("Number2");
		int sum = (int) req.getAttribute("Sum");

		PrintWriter pWriter = res.getWriter();
		pWriter.print("Hello " + name + "<br>");
		pWriter.print("Your age is: " + age + "<br>");
		pWriter.print("The sum of " + num1 + " and " + num2 + " is: " + sum);
		pWriter.close();
	}

}
