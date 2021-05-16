import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class DeleteAccount extends HttpServlet{
 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
 {
   PrintWriter out = response.getWriter();
   String delete = (String)request.getParameter("D1");
   
   
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

            String name = (String)(session.getAttribute("username"));	
	  
             //String name =  request.getParameter("abc"); 
            if(delete.equalsIgnoreCase("Yes"))
			{
				String jdbcUrl2 = "jdbc:mysql://localhost:3306/socialapp";
                String username1 = "root";
                String password1 = "root";
                String sql1 = "delete from posts where username = '"+name+"'";
	            System.out.println(sql1);
   
                 try (Connection conn1 = DriverManager.getConnection(jdbcUrl2, username1, password1); 
                       Statement stmt1 = conn1.createStatement();) 
					   {
                                    int s1 = stmt1.executeUpdate(sql1);
	                                 if(s1>=0)
	                                   {
                                           out.println("<h1>deleting posts.... </h1>");
	                                     }
                                 	  else
	                                      {
		                                   out.println("<h1>error</h1>");
		  
	                                       }
                        } catch (SQLException e) {
                                           out.println(e);		
                                           e.printStackTrace();
                             }

                String jdbcUrl3 = "jdbc:mysql://localhost:3306/socialapp";
                String username2 = "root";
                String password2 = "root";
                String sql2 = "delete from contacts where username = '"+name+"'  OR friends = '"+name+"'";
	            System.out.println(sql2);
   
                try (Connection conn2 = DriverManager.getConnection(jdbcUrl3, username2, password2); 
                     Statement stmt2 = conn2.createStatement();)
		         {
                    int s2 = stmt2.executeUpdate(sql2);
	                if(s2>=0)
	                {
                             out.println("<h1>Deleting contacts..... </h1>");
	                }
	                else
	                {
		                     out.println("<h1>error</h1>");
		             }
                 } 
              	catch (SQLException e)
	              {
	                  out.println(e);	
                      e.printStackTrace();
	  
                   }

                String jdbcUrl4 = "jdbc:mysql://localhost:3306/socialapp";
                String username3 = "root";
                String password3 = "root";
                String sql3 = "delete from friendrequests where username = '"+name+"'";
	            System.out.println(sql3); 
               try (Connection conn3 = DriverManager.getConnection(jdbcUrl4, username3, password3); 
                Statement stmt3 = conn3.createStatement();) 
		     {
                 int s3 = stmt3.executeUpdate(sql3);
	             if(s3>=0)
	                {
                      out.println("<h1>deleting friendrequests.... </h1>");
	                 }
	             else
	              {
		                  out.println("<h1>error</h1>");
		  
	              }
             } 
	           catch (SQLException e)
	           {
		
	                      out.println(e);		
                          e.printStackTrace();
                }
	
                	String jdbcUrl5 = "jdbc:mysql://localhost:3306/socialapp";
                    String username4 = "root";
                    String password4 = "root";
                    String sql4 = "delete from friendrequests where request = '"+name+"'";
	                System.out.println(sql4);
   
                    try (Connection conn4 = DriverManager.getConnection(jdbcUrl4, username3, password3); 
                          Statement stmt4 = conn4.createStatement();)
		           {
                        int s4 = stmt4.executeUpdate(sql4);
	                    if(s4>=0)
	                   {
                       out.println("<h1>deleting friendrequests.... </h1>");
	                   }
	                  else
	                    {
		               out.println("<h1>error</h1>");
		  
	                    }
                   } 
	               catch (SQLException e)
	                   {
		
	                      out.println(e);		
                          e.printStackTrace();
                        }
	
	            String jdbcUrl = "jdbc:mysql://localhost:3306/socialapp";
                String username = "root";
                String password = "root";
                String sql = "delete from accounts where username = '"+name+"'";
	            System.out.println(sql);
   
               try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
                     Statement stmt = conn.createStatement();)
		          {
                   int s = stmt.executeUpdate(sql);
	               if(s>0)
	                {
	                out.println("<h1>deleting account.... </h1>");	
                    out.println("<h1>Account deleted . We will miss you . Hope to see you again...</h1>");
               	   if(session!=null)
		             {		
                        session.invalidate();  
		             }    
	                }
	             else
	                 {
		              out.println("<h1>error</h1>");
		  
	                 }
                   } 
	          catch (SQLException e) 
	               {
	            	out.println(e);
                    e.printStackTrace();
                   }

}

if(delete.equalsIgnoreCase("NO"))
                {	
	                 out.println("<a href='http://localhost:8080/Project/More.html?name="+name+">Go Back</a>");
                }
}
}
}