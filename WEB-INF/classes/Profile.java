import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class Profile extends HttpServlet{
	
	
 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
 {
   PrintWriter out = response.getWriter();
   String button1Click = request.getParameter("button1");
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
   
   
             String  abc = (String)session.getAttribute("username");
   
            if(button1Click.equalsIgnoreCase("About"))
			{
              RequestDispatcher rd = request.getRequestDispatcher("/About");
              rd.forward(request,response);
			}
if(button1Click.equalsIgnoreCase("Your_Posts"))
{
	// request.setAttribute("name",abc );
    RequestDispatcher rd = request.getRequestDispatcher("/Post");
    rd.forward(request,response);

}
if(button1Click.equalsIgnoreCase("Your_Pictures"))
{
	// request.setAttribute("name",abc );
    RequestDispatcher rd = request.getRequestDispatcher("/PP");
    rd.forward(request,response);

}
if(button1Click.equalsIgnoreCase("PostSomething"))
{
      String url4="http://localhost:8080/Project/WritePost.html";    
       out.println("<p1><a href="+url4+" >Click to add Post for Your Friends :)</a></p1>");
}
	

}
}
}