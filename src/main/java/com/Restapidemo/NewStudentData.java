package com.Restapidemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
  

@WebServlet("/NewStudent")
public class NewStudentData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try {
            Connection con = com.Restapidemo.Database.initializeDatabase();
            PreparedStatement st = con.prepareStatement("insert into student values(?, ?, ?, ?)");
            st.setInt(1, Integer.valueOf(request.getParameter("id")));
            st.setString(2, request.getParameter("name"));
            st.setString(3, request.getParameter("dob"));
            st.setString(4, request.getParameter("phone"));
            st.executeUpdate();
            st.close();
            con.close();
            JSONObject js = new JSONObject();
            js.put("out", "New Student added Successfully");
            response.getWriter().print(js.toString());
//            PrintWriter out = response.getWriter();
//            out.println("<html>"
//	            		+ "<body>"
//	            		+ "<b>Successfully Inserted</b><br>"
//	            		+ "<b>Insert new Student</b> <form action=\"Newstudent.html\"><input type=\"submit\" value=\"NEW\"></form>"
//	            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//	                    + "</body>"
//	                    + "</html>");
        }
        catch (Exception e) {
        	JSONObject js = new JSONObject();
            js.put("out", e.getMessage());
            response.getWriter().print(js.toString());
//            response.getWriter().println("<html>"
//            		+ "<body>"
//            		+ "<b>"+e.getMessage()+"</b><br>"
//            		+ "<b>Insert new Student</b> <form action=\"Newstudent.html\"><input type=\"submit\" value=\"NEW\"></form>"
//            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//                    + "</body>"
//                    + "</html>");
        }
    }
}