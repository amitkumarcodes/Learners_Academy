package com.LearnersAcademy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetchTeacher")
public class fetchTeacher extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		if(conn!=null) {
			try {
				out.print("<center> <span style='color:blue'><h3>Connection Established!<h3></span></center>");
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * FROM teacher");
				
				out.print("<div style='display:flex; flex-direction:row; justify-content:center; align-items:center; margin-top:10px;'>");
				out.print("<div style='width:80px; font-weight:bold; text-align:center;'>Teacher Id</div>");
				out.print("<div style='width:150px; font-weight:bold; text-align:center;'>Name</div>");
				out.print("<div style='width:80px; font-weight:bold; text-align:center;'>Subject ID</div>");
				out.print("</div>");
				
				while(result.next()) {
					out.print("<div style='display:flex; flex-direction:row; justify-content:center; align-items:center; margin-top:10px;'>");
					out.print("<div style='width:80px; font-weight:bold; text-align:center;'>" + result.getInt(1) + "</div>");
					out.print("<div style='width:150px; font-weight:bold; text-align:center;'>" + result.getString(2)  + "</div>");
					out.print("<div style='width:80px; font-weight:bold; text-align:center;'>" + result.getInt(3) + "</div>");
					out.print("</div>");

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
