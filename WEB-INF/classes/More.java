import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class More extends HttpServlet{
 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
 {
	 
	
   PrintWriter out = response.getWriter();
       HttpSession session = request.getSession(false);
	  
	 
	 //String att = (String)(session.getAttribute("username"))
	 
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
     	//out.println("<h1>Session ID: " + session.getId() + "</h1><br>");
        //out.println("<h1>Is New: " + session.isNew() + "</h1><br>");
        //out.println("<h1>Created: " + new Date(session.getCreationTime()) + "</h1><br>");
        //out.println("<h1>Last accessed: " + new Date(session.getLastAccessedTime())+ "</h1><br>");
        //out.println("<P>");
       //  out.println("<h3>The following data has been found in the session: </h3>");
        //Enumeration attributeNames = session.getAttributeNames();
   
         String name = (String)session.getAttribute("username");
   
   String option = (String)request.getParameter("B1");
   //String name =  request.getParameter("abc");
out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
   
if(option.equalsIgnoreCase("DeactivateAccount"))
{ 
 
 String url1="http://localhost:8080/Project/DeleteAccount.html";
 out.println("<p1><a href="+url1+">click to delete account....</a></p1><br>");
   
}
if(option.equalsIgnoreCase("LogOut"))
{
	
	
	//HttpSession session=request.getSession(false);
        if(session!=null)
		{		
            session.invalidate();  
		} 
       out.print("You are successfully logged out!"); 
       
 String url1="http://localhost:8080/Project/Main.html";
 out.println("<p1><a href="+url1+">Go to main Page </a></p1><br>");
   
  
	 
}
	  }
}
}