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

@WebServlet("/Update")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try {
            Connection con = com.Restapidemo.Database.initializeDatabase();
            Statement st = con.createStatement();
            String query = "update student set name='"+request.getParameter("name")
            				+"',dob='"+request.getParameter("dob")
            				+"',phone='"+request.getParameter("phone")
            				+"' where id="+Integer.valueOf(request.getParameter("id"));
            st.executeUpdate(query);
            st.close();
            con.close();
//            PrintWriter out = response.getWriter();
//            out.println("<html>"
//	            		+ "<body>"
//	            		+ "<b>Updated Successfully</b><br>"
//	            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//	                    + "</body>"
//	                    + "</html>");
            JSONObject js = new JSONObject();
            js.put("out", "Updated Successfully");
            response.getWriter().print(js.toString());
        }
        catch (Exception e) {
        	JSONObject js = new JSONObject();
            js.put("out", e.getMessage());
            response.getWriter().print(js.toString());
//            response.getWriter().println("<html>"
//            		+ "<body>"
//            		+ "<b>"+e.getMessage()+"</b><br>"
//            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//                    + "</body>"
//                    + "</html>");
        }
        
 
    }
}

