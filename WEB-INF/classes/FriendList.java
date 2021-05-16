import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class FriendList extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {
    int x;    
    PrintWriter out = res.getWriter();
	 HttpSession session = req.getSession(false);
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
   
   
   
    String    username = (String)session.getAttribute("username");
   //String username=(String)req.getParameter("name");	
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	out.println("<h1>"+username+"</h1>");

      try
	                
	     {
		             Class.forName("com.mysql.jdbc.Driver");

                      String url12 = "jdbc:mysql://127.0.0.1/socialapp";

                      Connection conn1=DriverManager.getConnection(url12,"root","root");
                      Statement st12=conn1.createStatement();
					  String query12="Select * from contacts  where username= '"+username+"'";
			          ResultSet r1 = st12.executeQuery( query12 );
					  if(r1.next())
		              {
						  
						  String f1=r1.getString("friends");
						  out.println("<p1>"+f1+"</p1><br>");
						  out.println("<p1><a href=FriendProfile?name="+username+"&friend="+f1+"&flag=false>visit profile...</a></p1");	
						  
					  while(r1.next())
			          {
						String f=r1.getString("friends");
				        out.println("<p1>"+f+"</p1><br>");
						out.println("<p1><a href=FriendProfile?name="+username+"&friend="+f+"&flag=false>visit profile...</a></p1");
		
			          }
					  
					  
					  }
					  else
					  {
						  
						  out.println("<p1>NO Friends to show</p1>");
						  
						  
					  }
					   
			          
      }
	  catch(Exception e)
	             {
		
		          out.println(e);
                  }		
    
				    
	
	

	  }		
  }

}
