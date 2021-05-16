import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AcceptReq extends HttpServlet {

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
	
    String username=(String)(session.getAttribute("username"));
	String Friendname=(String)req.getParameter("fname");
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	out.println("<h1>"+username+"</h1>");


    try
	{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url, "root", "root");
    String sql="INSERT INTO contacts (username,friends) VALUES (?,?)";
    PreparedStatement statement = con.prepareStatement(sql);
	System.out.println(sql);
	
	        statement.setString(1, username);
            statement.setString(2,Friendname);
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
	  out.println("<p> insert 2 Sucessful </p1>");
	 try
	{

    Class.forName("com.mysql.jdbc.Driver");

    String url20 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con20=DriverManager.getConnection(url20, "root", "root");
    String sql20="INSERT INTO contacts (username,friends) VALUES (?,?)";
    PreparedStatement statement20 = con20.prepareStatement(sql20);
	System.out.println(sql20);
	
	        statement20.setString(1,Friendname);
	        statement20.setString(2, username);
           
			int result20 = statement20.executeUpdate();
			
			
			if(result20<=0)
			{
				
				out.println("<p1>error </p1>");
		
			}
			
	}
	catch(Exception e)
	{
		    out.println("<p>Not found </p1>");
            System.out.println(e);
		
	}
	
	
	
	out.println("<p> insert 2 Sucessful </p1>");
     try
	                
	     {
		             Class.forName("com.mysql.jdbc.Driver");

                      String url1 = "jdbc:mysql://127.0.0.1/socialapp";

                      Connection conn=DriverManager.getConnection(url1,"root","root");
                      Statement st1=conn.createStatement();
					  String query1="Delete from friendrequests  where username= '"+username+"' and request='"+Friendname+"'";
			          int r = st1.executeUpdate( query1 );
					  if(r<=0)
			          {
				
				           out.println("<p1>error del </p1>");
		
			           }
			          
      }
	  catch(Exception e)
	             {
		
		          out.println(e);
                  }
	  out.println("<p1>you are now friends :) </p1>");
	  
	  }
	   

			
  }

}
