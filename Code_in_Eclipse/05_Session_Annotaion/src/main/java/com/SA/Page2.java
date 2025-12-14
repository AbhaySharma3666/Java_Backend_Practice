package com.SA;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/page2")
public class Page2 extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		int num = (int) session.getAttribute("pass");
		int square = num * num;
		res.getWriter().println("The square of " + num + " is: " + square);
	}
}
