package com.Restapidemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Delete")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try {
            Connection con = com.Restapidemo.Database.initializeDatabase();
            Statement st = con.createStatement();
            String query = "delete from student where id = "+request.getParameter("id");
            st.execute(query);
            st.close();
            con.close();
            JSONObject js = new JSONObject();
            js.put("out", "Deleted Successfully");
            response.getWriter().print(js.toString());
//            out.println("<html>"
//	            		+ "<body>"
//	            		+ "<b>Successfully deleted</b><br>"
//	            		+ "<b>Delete Another Student Data</b> <form action=\"Delete.html\"><input type=\"submit\" value=\"Delete\"></form>"
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
//            		+ "<b>Delete Other Student Data</b> <form action=\"Delete.html\"><input type=\"submit\" value=\"Delete\"></form>"
//            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//                    + "</body>"
//                    + "</html>");
        }
 
    }
}
