import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddFriend extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {
	  
	 String flag="true";
     String flag1="true";	 
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
  
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	
    String u_name=(String)(session.getAttribute("username"));
	String Friendname=(String)req.getParameter("friend");
	
	out.println("<h1> Hii "+u_name+"</h1>");
	
	 try
   {
    Class.forName("com.mysql.jdbc.Driver");

    String url10 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con10=DriverManager.getConnection(url10,"root","root");

    Statement st10=con10.createStatement();
    String query10 ="Select * from friendrequests where username ='"+Friendname+"' AND request= '"+u_name+"'";
	
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query10);
   
     ResultSet rs10 = st10.executeQuery( query10 );
   
     if(rs10.next())
        {
 
        	 out.println("<h1>you already sent request to this user </h1>");
			 flag="false";
	
		}
   


         //out.println("</body></html>");
           //st.close();
           //con.close();

    }
	catch(Exception e)
	{
		
		out.println("<h1>No record found username or password is incorrect </h1>");

      out.println(e);
    }
	
	if(flag=="true")
	{
	try
   {
    Class.forName("com.mysql.jdbc.Driver");

    String url20 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con20=DriverManager.getConnection(url20,"root","root");

    Statement st20=con20.createStatement();
    
	String query20 ="Select * from friendrequests where username ='"+u_name+"' AND request= '"+Friendname+"'";
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query20);
   
     ResultSet rs20 = st20.executeQuery( query20 );
   
     if(rs20.next())
        {
 
        	 out.println("<h1>the user already sent you friend request  </h1>");
			 flag1="false";
	
		}
   


         //out.println("</body></html>");
         //  st.close();
          // con.close();

    }
	catch(Exception e)
	{
		
		out.println("<h1>No record found username or password is incorrect </h1>");

      out.println(e);
    }
	
	}
	
if(flag == "true" && flag1 =="true")
{	
    try
	{

                   Class.forName("com.mysql.jdbc.Driver");

                   String url = "jdbc:mysql://127.0.0.1/socialapp";

                    Connection con=DriverManager.getConnection(url, "root", "root");
                    String sql="INSERT INTO friendrequests (username,request) VALUES (?,?)";
                    PreparedStatement statement = con.prepareStatement(sql);
	                System.out.println(sql);
	
           	        statement.setString(1,Friendname);
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
	
	out.println("<p> YOUR REQUEST HAS BEEN SENT</p1>");
    
	 }	
	  }	  
  }

}
