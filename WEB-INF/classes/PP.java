import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class PP extends HttpServlet 
{

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
   

	
	String u_name = (String)(session.getAttribute("username"));
	//String u_name=(String)req.getAttribute("name");
	//System.out.println(u_name);
    out.println("<!DOCTYPE html>");
    out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
   // out.println("<html>");
    out.println("<head><title>Response</title></head>");
    //out.println("<body bgcolor=\"#ffffff\">");
	
	try
   {
    Class.forName("com.mysql.jdbc.Driver");

    String url20 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection conn=DriverManager.getConnection(url20,"root","root");

    Statement st20=conn.createStatement();
    
	String query20="Select * from userphoto where Username ='"+u_name+"'";
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query20);
   
     ResultSet rs1 = st20.executeQuery( query20 );
   
     if(rs1.next())
        {
               int counter=1;
          // String name1 = rs1.getString("username");
    	   String photo = rs1.getString("photo");
           
           //out.println("<p1> User Name :"+name1+"</p1><br>");
		 // out.println("<p1>"+photo+"<p1><br>");	 
		  out.println("<h1> Picture no "+counter+"<h1><br>");	
	        out.println("<img src= "+" http://localhost:8080/Project/Personsimg/"+photo+" style=width:200px; height:100px  /><br>");
		   out.println("<br>");
          
		  while(rs1.next())
        {
			
		//	String name = rs1.getString("username");
    	   String photo1 = rs1.getString("photo");
		   counter=counter+1;
           out.println("<h1>Picture no : "+counter+"</h1><br>");
	       out.println("<img src= "+" http://localhost:8080/Project/Personsimg/"+photo1+" style=width:200px; height:100px  /> <br>");
		   out.println("<br>");

//			out.println("<p1>"+id+"</p><br>");
		//	out.println("<a href='http://localhost:8080/Project/Delete.html?id="+id+"'>delete this post </a>");
			
			
           
		}		

		
        		  
		}
       else{
    	 out.println("<h1>No picture found </h1>");
         out.println("<a href='http://localhost:8080/Project/UploadPictures.html'>Click to add pictures </a>");
          }
		



    }
	catch(Exception e)
	{
		
		out.println("<h1>No record found username or password is incorrect </h1>");

      out.println(e);
    }
	
  
}
  }
  }