import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class Delete extends HttpServlet{
 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
 {
   PrintWriter out = response.getWriter();
   
   
   	HttpSession session = request.getSession(false);
	if(session == null)
	  {
		  
		  out.println("<html>");
          out.println("<head>");
          out.println("</head>");
          out.println("<body>");
		 out.println("<p1>Click to login</p1>");
	     String url1="http://localhost:8080/Project/LogIn.html";
		out.println("<p1><a href="+url1+">You have to login first...</a></p1><br>");
      }
	  else
	  {
		  out.println("<html>");
          out.println("<head>");
          out.println("</head>");
          out.println("<body>");
      String u_name=(String)session.getAttribute("username");
	   //String id =(String)session.getAttribute("postid");
	   //out.println("<p1>"+id+"</p1>");
   String id = (String)request.getParameter("id");
//if(delete.equalsIgnoreCase("Yes"))
//{
	int a = Integer.parseInt(id); 
	String jdbcUrl = "jdbc:mysql://localhost:3306/socialapp";
    String username = "root";
    String password = "root";
    String sql = "delete from posts where id ="+a+" AND username= '"+u_name+"'";
   
    try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
        Statement stmt = conn.createStatement();) {
      int s = stmt.executeUpdate(sql);
	  
	  if(s>0)
	  {
      out.println("<h1>Post deleted successfully</h1>");
	  out.println("<a href='http://localhost:8080/Project/Profile.html>Go Back</a>");
	  }
	  else
	  {
		  out.println("<h1>error</h1>");
		  
	  }
    } catch (SQLException e) {
      e.printStackTrace();
    }

//}
//if(delete.equalsIgnoreCase("NO"))
//{
	// out.println("<a href='http://localhost:8080/Project/Profile.html>Go Back</a>");
//}
	  }
}
}