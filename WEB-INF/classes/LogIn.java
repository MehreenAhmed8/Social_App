import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogIn extends HttpServlet
{

  //Process the HTTP Post request
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {
	  PrintWriter out = res.getWriter();
	  String u_name=req.getParameter("name");
	String Password=req.getParameter("pass");
    
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");


    try
	{
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
	String query="Select * from accounts where Username ='"+u_name+"'and Password='"+Password+"'";
	System.out.println(query);
   
     ResultSet rs = st.executeQuery( query );
   
     if(rs.next())
	 {

            HttpSession session1 = req.getSession(false);
			if(session1 != null)
			{
				session1.invalidate();
				session1=null;
			}

            HttpSession session = req.getSession();
			session.setAttribute("username", u_name);
		    req.setAttribute("key", u_name);
            req.setAttribute("key1", Password);
            
            RequestDispatcher rd = req.getRequestDispatcher("/Page");
            rd.forward(req,res);

    	  
	  }
     
     else{
    	 out.println("<h1 username or password is incorrect </h1>");
        out.println("<a href='http://localhost:8080/Project/SignUP.html'>Click to Sign up SignUP Page for free </a>");
          }


          out.println("</body></html>");
           st.close();
           con.close();

    }
	catch(Exception e)
	{

      System.out.println(e);
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

  
  
  
  
  
  
  
  
  
  
  
  
