package com.SA;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/page1")
public class page1 extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int n1 = Integer.parseInt(req.getParameter("num1"));
		HttpSession session = req.getSession();
		session.setAttribute("pass", n1);
		res.sendRedirect("page2");
	}
}
