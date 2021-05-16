import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class About extends HttpServlet 
{

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
       out.println("<!DOCTYPE html>");
       out.println("<head><title>Response</title></head>");
  
	
	try
   {
    Class.forName("com.mysql.jdbc.Driver");

    String url20 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection conn=DriverManager.getConnection(url20,"root","root");

    Statement st20=conn.createStatement();
    
	String query20="Select * from imagestore where Username ='"+u_name+"'";
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query20);
   
     ResultSet rs1 = st20.executeQuery( query20 );
   
     if(rs1.next())
        {
               

	       String name1 = rs1.getString("Username");
    	   String photo = rs1.getString("profile");
           
           //out.println("<p1> User Name :"+name1+"</p1><br>");
		  //  out.println("<p1>"+photo+"<p1><br>");	 
	       //out.println("<img src= http://localhost/Project/images/"+photo+"  style=width:100px; height:100px  /></div>");
		   
		   out.println("<img src= "+" http://localhost:8080/Project/images/"+ photo+ " style=width:100px; height:100px  /></div><br>");
        		  
		}
       else{
    	 out.println("<h1>No record found username or password is incorrect </h1>");
         out.println("<a href='http://localhost:8080/Project/SignUP.html'>Click to Sign up SignUP Page for free </a> ");
          }



    }
	catch(Exception e)
	{
		
		out.println("<h1>No record found username or password is incorrect </h1>");

      out.println(e);
    }
	
   try
   {
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
	String query="Select * from accounts where Username ='"+u_name+"'";
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query);
   
     ResultSet rs = st.executeQuery( query );
   
     if(rs.next())
        {

	   String name = rs.getString("Username");
    	    String fname = rs.getString("FirstName");
            String lame = rs.getString("LastName");
    	    String email = rs.getString("Email");
            String phone = rs.getString("Phone");
    	    String gender = rs.getString("Gender");
			String pass = rs.getString("Password");
    	    
           
            //out.println("<h1>UserName  : "+name+"</h1><br>");
			out.println("<p1>FirstName  : "+fname+"</p><br>");
            out.println("<p1>LastName  : "+lame+"</p><br>");
			out.println("<p1>Password  : "+pass+"</p><br>");
			out.println("<p1>Email  : "+email+"</p><br>");
			out.println("<p1>Phone  : "+phone+"</p><br>");
            out.println("<p1>Gender  : "+gender+"</p><br>");
			
		}
       else{
    	 out.println("<h1>No record found username or password is incorrect </h1>");
         out.println("<a href='http://localhost:8080/Project/SignUP.html'>Click to Sign up SignUP Page for free </a>");
          }


         out.println("</body></html>");
           st.close();
           con.close();

    }
	catch(Exception e)
	{
		
		out.println("<h1>No record found username or password is incorrect </h1>");

      out.println(e);
    }
  }
  }

}