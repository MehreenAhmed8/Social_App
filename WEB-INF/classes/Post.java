import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Post extends HttpServlet 
{

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
      String u_name=(String)session.getAttribute("username");
	  
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	
	 out.println("<p>"+u_name+"</p>");



    try{
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
	String query="Select * from posts where Username ='"+u_name+"'";
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query);
   
     ResultSet rs = st.executeQuery( query );
	 int counter=0;
   
       if(rs.next())
	   {
		   
		    out.println("<h1>"+u_name+"'s posts : </h1><br>");
			out.println("<h2>only visible to your friends </h2><br>");
		    counter=counter+1;
            out.println("<h1>Post no : "+counter+"</h1><br>");
	        String post1 = rs.getString("post");
   
			out.println("<p1>"+post1+"</p><br>");
			String id1 = rs.getString("id");
			
			//session.setAttribute("postid", id1);
	        out.println("<a href='http://localhost:8080/Project/Delete?id="+id1+"'>delete this post </a>");			
			
           
       while(rs.next())
        {
			counter=counter+1;
            out.println("<h1>post no : "+counter+"</h1><br>");
	        String post = rs.getString("post");
			String id = rs.getString("id");
   
			out.println("<p1>"+post+"</p><br>");
//			out.println("<p1>"+id+"</p><br>");
            //session.setAttribute("postid", id);
			out.println("<a href='http://localhost:8080/Project/Delete?id="+id+"'>delete this post </a>");
		
			
			
           
		}
	   }
       else{
    	 out.println("<h1>No post found  </h1>");
          String url4="http://localhost:8080/Project/WritePost.html?";
          out.println("<p1> <a href="+url4+">Click to add Post for Your Friends :)</a></p1><br>");
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

}
