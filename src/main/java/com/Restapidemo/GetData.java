package com.Restapidemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Get")
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try {
            Connection con = com.Restapidemo.Database.initializeDatabase();
            Statement st = con.createStatement();
            String query = "select * from student";
            if(request.getParameter("id")!="") {
            	query+=" where id = "+request.getParameter("id");
            }	
            ResultSet rs = st.executeQuery(query);
            if(rs.next()==false) {
            	JSONObject js = new JSONObject();
            	js.put("output","Requested ID is not available in the table");
            	response.getWriter().print(js.toString());
            }else {
            	JSONObject sd = new JSONObject();
            	JSONObject osd = new JSONObject();
	            osd.put("id",rs.getInt("id"));  
	            osd.put("name" , rs.getString("name"));  
	            osd.put("dob", rs.getString("dob"));
	            osd.put("phone",rs.getString("phone"));
	            sd.append("Students",osd.toString());
	            while (rs.next()) 
	            {  
		           osd.put("id" , rs.getInt("id"));  
		           osd.put("name", rs.getString("name"));  
		           osd.put("dob" , rs.getString("dob"));
		           osd.put("phone", rs.getString("phone"));
		           sd.append("Students",osd.toString());
		           
	            }
            st.close();
            con.close();
            PrintWriter out = response.getWriter();
            out.print(sd);
//            out.println("<html>"
//	            		+ "<body>"
//	            		+ "<b>"+output+"</b><br>"
//	            		+ "<b>Get Another Student Data</b> <form action=\"Get.html\"><input type=\"submit\" value=\"Get\"></form>"
//	            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//	                    + "</body>"
//	                    + "</html>");
        }
        }
        catch (Exception e) {
        	JSONObject js = new JSONObject();
            js.put("out", e.getMessage());
            response.getWriter().print(js.toString());
//            response.getWriter().println("<html>"
//            		+ "<body>"
//            		+ "<b>"+e.getMessage()+"</b><br>"
//            		+ "<b>Get Other Student Data</b> <form action=\"Get.html\"><input type=\"submit\" value=\"Get\"></form>"
//            		+ "<b>Go back to Index page</b> <form action=\"Index.html\"><input type=\"submit\" value=\"Back\"></form>"
//                    + "</body>"
//                    + "</html>");
        }
 
    }
}
