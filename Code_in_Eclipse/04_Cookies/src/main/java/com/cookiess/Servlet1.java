package com.cookiess;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String n1 = req.getParameter("name");
		PrintWriter out = res.getWriter();
		boolean uservisit = false;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				String name = c.getName();
				String value = c.getValue();
				if (name.equals("user_name") && value.equals("123")) {
					out.print("Welcome back " + n1);
					uservisit = true;
					break;
				}
			}

		}
		if (uservisit == false) {
			out.print("Welcome new user " + n1);
			Cookie ck = new Cookie("user_name", "123");
			res.addCookie(ck);

		}

	}

}
