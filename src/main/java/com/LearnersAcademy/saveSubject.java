package com.LearnersAcademy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveSubject")
public class saveSubject extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		
		PrintWriter out = resp.getWriter();
		String subStr = req.getParameter("subject");
		int classId =  Integer.parseInt(req.getParameter("cid"));
	
		if(conn!=null) {
			try {
				out.print("<center> <span style='color:blue'><h3>Connection Established!<h3></center></span>");
				String sql = "INSERT INTO subject(subject, class_id) VALUES (?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, subStr);
				stmt.setInt(2, classId);
				
				
				int rowsInserted = stmt.executeUpdate();
				if(rowsInserted > 0) {
					System.out.println("A new subject has been added to the subject list.");
					out.println("<center> <span style='color:red'><br><h3>A new subject has been added to the subject list. <h3></span></center>");
					rd = req.getRequestDispatcher("subject.html");
					rd.include(req, resp);
				}else {
					out.print("<center> <span style='color:red'><br> Error! </span></center>");
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
		//resp.sendRedirect("index.html");
	}
	
}
