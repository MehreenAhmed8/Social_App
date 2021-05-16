import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class WritePost extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {

    
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
 
     String u_name = (String)(session.getAttribute("username"));
	 String text =req.getParameter("text");
	
	 out.println("<html>");
     out.println("<head><title>Response</title></head>");
     out.println("<body bgcolor=\"#ffffff\">");
	 out.println("<h1>"+u_name+"</h1>");


    try
	{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url, "root", "root");
    String sql="INSERT INTO posts (post,username) VALUES (?,?)";
    PreparedStatement statement = con.prepareStatement(sql);
	System.out.println(sql);
	
	        statement.setString(1, text);
            statement.setString(2,u_name);
			int result = statement.executeUpdate();
			
			
			if(result<=0)
			{
				
				out.println("<p1>error </p1>");
		
			}
			
	}
	catch(Exception e)
	{
		    out.println("<p>Not found </p1>");
            System.out.println(e);
		
	}
	
	 out.println("<p> post Sucessful </p1> <br>");
	 String url4="http://localhost:8080/Project/WritePost.html";
	 out.println("<p1><a href="+url4+" >Click to add more Posts </a></p1><br>");




	  }
}
}
