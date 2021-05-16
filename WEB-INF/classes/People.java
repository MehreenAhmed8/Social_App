import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class People extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    
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
	
	
	
    String user=(String)session.getAttribute("username");


    String u_name=(String)req.getParameter("name");
	
    
	String flag=(String)req.getParameter("flag");
	
    //System.out.println(u_name);
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");


    try{
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
	String query="Select * from accounts where Username ='"+u_name+"'";
	System.out.println("<h1>"+query+"</h1>");
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
		
           
            out.println("<h1>UserName  : "+name+"</h1><br>");
			out.println("<p1>FirstName  : "+fname+"</p><br>");
            out.println("<p1>LastName  : "+lame+"</p><br>");
			out.println("<p1>Email  : "+email+"</p><br>");
			out.println("<p1>Phone  : "+phone+"</p><br>");
            out.println("<p1>Gender  : "+gender+"</p><br>");
            if(flag.equalsIgnoreCase("true"))
            {
            out.println("<p1><a href=FriendReq>Go Back...</a></p1");	
            }
        
        }
       else{
    	 out.println("<h1>No record found username or password is incorrect </h1>");
         out.println("<a href='http://localhost:8080/Project/SignUP.html'>Click to Sign up SignUP Page for free </a>");
          }


         out.println("</body></html>");
           st.close();
           con.close();

    }catch(Exception e){
		
		out.println("<h1>No record found username or password is incorrect </h1>");

      out.println(e);
    }

  }

}
