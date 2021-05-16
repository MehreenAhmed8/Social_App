import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class Search extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {

     PrintWriter out = res.getWriter();
    HttpSession session = req.getSession(false);
	  
	 
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
   
         String uname = (String)session.getAttribute("username");
         // out.println("<p> user : "+uname+"<br></p>");
	  
	     String flag="true";
         String fname=req.getParameter("name");
	
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	out.println("<p1>"+uname+"</p1><br>");
   // out.println("<p2>"+url+"</p2>");
    //out.println("</body>");
	
	 try{
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
	String query="Select * from contacts where username ='"+uname+"' and friends = '"+fname+"'";
   
     ResultSet rs = st.executeQuery( query );
   
     if(rs.next())
	    {

	        out.println("<h1> You and "+fname+" are friends :) </h1><br>");
			out.println("<h1><a href=FriendProfile?name="+uname+"&friend="+fname+"&flag=false>Click to visit "+fname+"profile...</a></p1>");
            flag="false";			
			
			
        }
     
     else{
    	// out.println("<h1>No record found  </h1>");
          }


           //out.println("</body></html>");
           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }
	
	if(flag.equalsIgnoreCase("true"))
	{
	try{
    Class.forName("com.mysql.jdbc.Driver");

    String url1 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con1=DriverManager.getConnection(url1,"root","root");

    Statement st1=con1.createStatement();
    
	String query1="Select * from accounts where Username ='"+fname+"'";
   
     ResultSet rs1 = st1.executeQuery( query1 );
   
     if(rs1.next()){

	        out.println("<h1>Record found</h1>");
			
            String f1name = rs1.getString("FirstName");
            String lname = rs1.getString("LastName");
    	    String email = rs1.getString("Email");
			out.println("<h2> User name :"+fname+"</h1>");
			out.println("<h2> First name :"+f1name+"</h1>");
			out.println("<h2> Last name :"+lname+"</h1>");
			out.println("<h2> Email :"+email+"</h1>");
			out.println("<h2><a href=AddFriend?uname="+uname+"&friend="+fname+"&flag=false>Click to send "+fname+" friend request...</a></h2>");
			
			
            
    	  
	  }
     
     else{
    	 out.println("<h1>No record found  </h1>");
          }


out.println("</body></html>");
//           st.close();
 //          con.close();

    }catch(Exception e){

      out.println(e);
    }
	

	}
	  }
}
}
