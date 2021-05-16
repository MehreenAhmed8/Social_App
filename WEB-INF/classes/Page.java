import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class Page extends HttpServlet{
 public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
 {
	 
   String name = (String) req.getAttribute("key");
   String pass = (String) req.getAttribute("key1");
   PrintWriter out = res.getWriter();
 try{
	 
	 HttpSession session = req.getSession(false);
	 
	 if(session == null)
	  {
	     res.sendRedirect("LogIn.html");
      }
	  
     String name22 = (String)session.getAttribute("username");     
	 
	 
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
     String query="Select * from accounts where Username='"+name+"'";
   
     ResultSet rs = st.executeQuery( query );
	 
	 String url1="http://localhost:8080/Project/Search.html";
	 String url2="http://localhost:8080/Project/Profile.html";
	 String url3="http://localhost:8080/Project/More.html";
         String url4="http://localhost:8080/Project/WritePost.html";
	 String url5="http://localhost:8080/Project/UploadPictures.html";
	 
   
     if(rs.next())
	 {

	    out.println("<h1>Welcome  "+name+"</h1><br>");
		out.println("<h2>We are happy to see you again </h2><br>");
                out.println("<p1><a href="+url4+" >Click to add Post for Your Friends :)</a></p1><br>");
		out.println("<p1><a href="+url2+" >Click to Your see profile</a></p1><br>");
		out.println("<p1><a href="+url1+">Search a friend on social inc...</a></p1><br>");
		out.println("<p1><a href="+url5+">Uplaod Pictures...</a></p1><br>");
		out.println("<p1><a href=FriendList>Click to check your friend List...</a></p1><br>");
		out.println("<p1><a href=FriendReq>Click to see your friend Req...</a></p1><br>");
		out.println("<p1><a href=FriendProfile.html>Click to check your friend's Profile...</a></p1><br>");
		out.println("<p1><a href="+url3+">//more...</a></p1><br>");
		 }
     
     else{
    	 out.println("<h1>No record found</h1>");
          }


         out.println("</body></html>");
           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }
 }
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
   {

    PrintWriter out = res.getWriter();

    String u_name=req.getParameter("name");
	String Password=req.getParameter("pass");
    
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
    out.println("<a href='http://localhost:8080/Project/LogIn.html'>Click to Login First  </a>");
    out.println("</body></html>");

  

  }
 
}
