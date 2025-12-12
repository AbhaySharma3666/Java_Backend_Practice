package com.R;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		int sum = Integer.parseInt(req.getParameter("sum"));
		int square = sum * sum;

		PrintWriter pWriter = res.getWriter();
		pWriter.print("Square of " + sum + " is " + square);
		pWriter.close();
	}

}
