package com.LearnersAcademy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginController() {
        super();
       
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		if(username.equalsIgnoreCase("Admin") && password.equals("password")) {
			rd = request.getRequestDispatcher("admin.html");
			out.println("<center> <span style='color:red'> Welcome Admin, Login Successful!</span></center>");
			rd.include(request, response);
		} else {
			rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			out.println("<center> <span style='color:red'> Invalid Credentials!! </span></center>");
		}
	}
}
