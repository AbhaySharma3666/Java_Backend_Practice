package com.RD;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class servlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));
		int sum = num1 + num2;

		req.setAttribute("Name", name);
		req.setAttribute("Age", age);
		req.setAttribute("Number1", num1);
		req.setAttribute("Number2", num2);
		req.setAttribute("Sum", sum);
		RequestDispatcher rd = req.getRequestDispatcher("servlet2");
		// rd.forward(req, res); // forwarding the request to servlet2 without knowledge
		// of the client(not visible on url)
		rd.include(req, res); // including the response of servlet2 into servlet1 response (visible on url)
	}

}
