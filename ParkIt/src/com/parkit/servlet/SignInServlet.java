package com.parkit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkit.dao.UserData;
import com.parkit.pojo.User;

/**
 * Servlet implementation class SignInServlet
 */
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.print("Method not allowed.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String mobile = request.getParameter("mobile");
		UserData userData = new UserData();
		String status = userData.validateLogin(mobile);
		if(status.equals("SUCCESS")){
			User user = userData.getUserByMobileNumber(mobile);
			String name = user.getName();
			Cookie mobileCookie = new Cookie("mobile", mobile);
			Cookie nameCookie = new Cookie("name", name);
			mobileCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
			nameCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
			response.addCookie(mobileCookie);
			response.addCookie(nameCookie);
			response.sendRedirect("dashboard.jsp");
		}else{
			request.setAttribute("status", "User doesn't exist. Please SignUp.");
			RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
		}
	}

}
