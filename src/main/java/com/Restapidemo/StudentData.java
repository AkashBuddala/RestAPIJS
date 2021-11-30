package com.Restapidemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/students")
public class StudentData extends HttpServlet{
	boolean put=false;
	private static final long serialVersionUID = 1L;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
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
            	if(request.getParameter("test")!="") {
            		js.put("out","Requested data is not available in the table");
            	}
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
            response.setHeader("content-type", "text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(sd.toString());
            }
       	}
        catch (Exception e) {
        	JSONObject js = new JSONObject();
            js.put("out", e.getMessage());
            response.getWriter().print(js.toString());
        }
	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException
		    {
		 String body = "";
	 		InputStream inp = request.getInputStream();
	 		int r;
	 		while((r = inp.read()) != -1) {
	 			body += Character.toString((char)r);
	 		}
	 		System.out.println("body: "+ body);
	 		JSONObject bodyjs = new JSONObject(body);
	 		
		        try {
		            Connection con = com.Restapidemo.Database.initializeDatabase();
		            System.out.println(request.getParameter("name1"));
		            PreparedStatement st = con.prepareStatement("insert into student(name,dob,phone) values(?, ?, ?)");
		            JSONObject js = new JSONObject();
		            if(put) {
		            	st.setString(1, (String)request.getAttribute("name1"));
			            st.setString(2, (String)request.getAttribute("dob"));
			            st.setString(3, (String)request.getAttribute("phone"));
			            js.put("fromput","Given ID not Available");
		            }else {
		            	st.setString(1, bodyjs.getString("name1"));
			            st.setString(2, bodyjs.getString("dob"));
			            st.setString(3, bodyjs.getString("phone"));
		            }
		            put=false;
		            st.executeUpdate();
		            st=con.prepareStatement("select Last_Insert_id() from student");
		            ResultSet rs = st.executeQuery();
		            rs.next();
		            int id=rs.getInt(1);
		            st.close();
		            con.close();
		            js.put("out", "New Student added Successfully");
		            js.put("newid","ID of new student is "+id );
		            response.getWriter().print(js.toString());
		        }
		        catch (Exception e) {
		        	JSONObject js = new JSONObject();
		            js.put("out", e.getMessage());
		            response.getWriter().print(js.toString());
		        }
		 		}
	 protected void doPut(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException
		    {
		 		System.out.println("PUT");
		 		String body = "";
		 		InputStream inp = request.getInputStream();
		 		int r;
		 		while((r = inp.read()) != -1) {
		 			body += Character.toString((char)r);
		 		}
		 		System.out.println("body: "+ body);
		 		JSONObject bodyjs = new JSONObject(body);
		 		
		        try {
		            Connection con = com.Restapidemo.Database.initializeDatabase();
		            Statement st = con.createStatement();
		            System.out.println(request.getParameter("sid"));
		            String query = "select * from student where id = "+bodyjs.getInt("sid");
		            System.out.println(query);
		            ResultSet rs = st.executeQuery(query);
		            if(rs.next()==false) {
		            	JSONObject js = new JSONObject();
		            	
		            	request.setAttribute("name1", bodyjs.getString("name1"));
		            	request.setAttribute("dob",bodyjs.getString("dob"));
		            	request.setAttribute("phone", bodyjs.getString("phone"));
		            	put=true;
		            	js.put("out", "Given ID not available");
		            	System.out.println(js);
		            	doPost(request,response);
		            	con.close();
		            	st.close();
		            }else {
		            query = "update student set name='"+bodyjs.getString("name1")
		            				+"',dob='"+bodyjs.getString("dob")
		            				+"',phone='"+bodyjs.getString("phone")
		            				+"' where id="+bodyjs.getInt("sid");
		            System.out.println("name"+bodyjs.getString("name1"));
		            st.executeUpdate(query);
		            st.close();
		            con.close();
		            JSONObject js = new JSONObject();
		            js.put("out", "Updated Successfully");
		            response.getWriter().print(js.toString());
		        }
		      }
		        catch (Exception e) {
		        	JSONObject js = new JSONObject();
		            js.put("out", e.getMessage());
		            System.out.println("error"+e.getMessage());
		            e.printStackTrace();
		            response.getWriter().print(js.toString());
		        }    
		    }
	 protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException
		    {
		 		System.out.println("Do Delete");
		        try {
		            Connection con = com.Restapidemo.Database.initializeDatabase();
		            Statement st = con.createStatement();
		            String query = "select * from student where id = "+request.getParameter("id");
		            ResultSet rs = st.executeQuery(query);
		            if(rs.next()==false) {
		            	JSONObject js = new JSONObject();
		            	js.put("out","Given ID is not available");
		            	response.getWriter().print(js.toString());
		            	con.close();
		            	st.close();
		            }else {
		            query = "delete from student where id = "+request.getParameter("id");
		            st.executeUpdate(query);
		            st.close();
		            con.close();
		            JSONObject js = new JSONObject();
		            js.put("out", "Deleted Successfully");
		            System.out.println(js.toString());
		            response.setHeader("content-type", "application/json");
		            response.getWriter().print(js.toString());
		        }
		       }
		        catch (Exception e) {
		        	System.out.println("error: ");
		        	e.printStackTrace();
		        	JSONObject js = new JSONObject();
		            js.put("out", e.getMessage());
		            response.getWriter().print(js.toString());
		        }
		    }
}
