import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class FriendProfile extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {
    int x;    
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
	
	
    String username=(String)session.getAttribute("username");
	String friendname=(String)req.getParameter("friend");
	String flag=(String)req.getParameter("flag");
	String flag2="";
		
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	out.println("<h1>"+username+"</h1>");
	
	if(flag.equalsIgnoreCase("true"))
	{
	
         
      try
	                
	     {
		             Class.forName("com.mysql.jdbc.Driver");

                      String url = "jdbc:mysql://127.0.0.1/socialapp";

                      Connection con=DriverManager.getConnection(url,"root","root");
                      Statement st=con.createStatement();
					  String query="Select * from contacts  where username= '"+username+"' and friends= '"+friendname+"'";
			          ResultSet r = st.executeQuery(query);
					  int counter=0;
					  if(r.next())
		              {
						 flag2="true";
					  
					  }
					  else
					  {
						  
						 flag2="false";
					  }
					   
			          
         }
	  catch(Exception e)
	             {
		
		          out.println(e);
                  }		
    }
	else
	{
		
		flag2="true";
	}
    

    if(flag2.equalsIgnoreCase("true"))
	{		
       
	   try
   {
    Class.forName("com.mysql.jdbc.Driver");

    String url20 = "jdbc:mysql://127.0.0.1/socialapp";

    Connection conn=DriverManager.getConnection(url20,"root","root");

    Statement st20=conn.createStatement();
    
	String query20="Select * from imagestore where Username ='"+friendname+"'";
	//out.println("<h1>"+query+"</h1>");
	System.out.println(query20);
   
     ResultSet rs1 = st20.executeQuery( query20 );
   
     if(rs1.next())
        {
	       String name1 = rs1.getString("Username");
    	   String photo = rs1.getString("profile");
            out.println("<img src= "+" http://localhost:8080/Project/images/"+ photo+ " style=width:100px; height:100px  /></div><br>");
        		  
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

                      String url12 = "jdbc:mysql://127.0.0.1/socialapp";

                      Connection conn1=DriverManager.getConnection(url12,"root","root");
                      Statement st12=conn1.createStatement();
					  String query12="Select * from posts  where username= '"+friendname+"'";
			          ResultSet r1 = st12.executeQuery( query12 );
					  int counter=0;
					  if(r1.next())
		              {
						  counter=counter+1;
						out.println("<p1>"+friendname+"'s Posts :</p1><br>");
                        out.println("<p1>"+counter+"</p1><br>");						
						String f1=r1.getString("post");
				        out.println("<p1>"+f1+"</p1><br>");
						  
					  while(r1.next())
			          {
						counter=counter+1;
						out.println("<p1>"+counter+"</p1><br>");	
						String f=r1.getString("post");
				        out.println("<p1>"+f+"</p1><br>");
		
			          }
					  
					  out.println("<p1><a href=People?name="+friendname+"&flag=false>Click to check "+friendname+"basic info </a></p1><br>");
					  
					  
					  }
					  else
					  {
						  
						   out.println("<p1>NO Posts to show</p1>");
						   out.println("<p1><a href=People?name="+friendname+"&flag=false>Click to check "+friendname+"basic info </a></p1><br>");
						  
					  }
					   
			          
      }
	  catch(Exception e)
	             {
		
		          out.println(e);
                  }		
    
				    
	}
	else
	{
		
		
		 out.println("<p1>You have no such friend </p1>");
		
		
	}
	
	}
			
  }

}
