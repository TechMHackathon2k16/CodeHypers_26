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
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		/*User user = new User();
		user.setName("ad");
		user.setMobile("123");*/
		PrintWriter pw = response.getWriter();
		pw.println("Method not allowed."/* + new UserData().writeUsertoXML(user)*/);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String mobile = request.getParameter("mobile");
		String name = request.getParameter("name");
		UserData userData = new UserData();
		User user = new User();
		user.setMobile(mobile);
		user.setName(name);
		String validateUser = userData.validateUser(user);
		if(validateUser.equals("SUCCESS")){
			String writeStatus = userData.writeUsertoXML(user);
			if(writeStatus.equals("SUCCESS")){
				Cookie mobileCookie = new Cookie("mobile", mobile);
				Cookie nameCookie = new Cookie("name", name);
				mobileCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
				nameCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
				response.addCookie(mobileCookie);
				response.addCookie(nameCookie);
				response.sendRedirect("dashboard.jsp");
			}else{
				request.setAttribute("status", "Sorry !!! We are not able to register you. Please try again.");
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request, response);
			}
		}else{
			request.setAttribute("status", "User already exists with mobile Number: " + mobile);
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			rd.forward(request, response);
		}
	}

}
