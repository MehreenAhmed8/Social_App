import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class FriendReq extends HttpServlet
 {

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
	  else
	  {
   
   
   
    String    u_name = (String)session.getAttribute("username");




    //String u_name=(String)req.getParameter("name");
	
  
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	out.println("<h1>"+u_name+"</h1>");


    

     try{
                      Class.forName("com.mysql.jdbc.Driver");

                      String url1 = "jdbc:mysql://127.0.0.1/socialapp";

                      Connection conn=DriverManager.getConnection(url1,"root","root");
                      Statement st1=conn.createStatement();
					  String query1="Select * from friendrequests where username ='"+u_name+"'";
			          ResultSet r = st1.executeQuery( query1 );
					  int counter=0;
			          while(r.next())
			             {
				            counter=counter+1;
				            String Name=r.getString("request");
				            out.println("<p1>"+Name+"</p1><br>");
					        out.println("<p1><a href=AcceptReq?fname="+Name+">Click to Accept "+Name+" 's request</a></p1><br>");
				            out.println("<p1><a href=People?name="+Name+"&flag=true>Click to check "+Name+"profile</a></p1><br>");
				 
			              }
						  
						  
						out.println("<h1>You have "+counter+" friend request</h1><br>");
						//out.println("<p1><a href=Page?name="+u_name+">Go Back</a></p1><br>");
						  
                    }
				catch(Exception e)
	             {
		    
		          out.println(e);
                  }

	  }	
}

}
