package com.LearnersAcademy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateStudent")
public class updateStudent extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		int student_id = Integer.parseInt(req.getParameter("sid"));
		String name = req.getParameter("student");
		int class_id = Integer.parseInt(req.getParameter("cid"));	
		RequestDispatcher rd = null;
		
		if(conn!=null) {
			try {
				//out.print("Connection Established!");
				String sql = "UPDATE student SET student_name = ?, class_id = ? WHERE student_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setInt(2,  class_id);
				stmt.setInt(3, student_id);
				
				int rowsInserted = stmt.executeUpdate();
				if(rowsInserted > 0) {
					System.out.println("Student list has been Updated.");
					out.println("<center> <span style='color:red'><br> Student list has been Updated! </span></center>");
					rd = req.getRequestDispatcher("student.html");
					rd.include(req, resp);
				}else {
					out.print("Data not added");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
