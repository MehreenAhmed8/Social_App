import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class SignUP extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
	   
   ///get PrintWriter object
	PrintWriter out = response.getWriter();
    String Uname=request.getParameter("usname");
    String Fname=request.getParameter("uname");
	String Lname=request.getParameter("luname");
	String email=request.getParameter("email");
	String password=request.getParameter("pass");
	String cpassword=request.getParameter("confirm_pass");
	String phone=request.getParameter("ph");
	String gender=request.getParameter("r");
   
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
	
    try{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/socialapp";

    Connection con=DriverManager.getConnection(url, "root", "root");
    String sql="INSERT INTO accounts (Username,FirstName,LastName,Email,Password,Phone,Gender) VALUES (?,?,?,?,?,?,?)";
    PreparedStatement statement = con.prepareStatement(sql);
	System.out.println(sql);
	
	        statement.setString(1, Uname);
            statement.setString(2,Fname);
			statement.setString(3,Lname);
            statement.setString(4, email);
			statement.setString(5,password);
			statement.setString(6,phone);
			statement.setString(7,gender);
			
			int result = statement.executeUpdate();
			
			
			if(result>0)
			{
				
				   // RequestDispatcher rd = request.getRequestDispatcher("/PPicture.html?naam="+Uname)
	                //rd.forward(request,response);
			HttpSession session1 = request.getSession(false);
			if(session1 != null)
			{
				session1.invalidate();
				session1=null;
			}

            HttpSession session = request.getSession();
			session.setAttribute("username", Uname);
				
				out.println("<a href='http://localhost:8080/Project/PPicture.html'> Step 1 completed...Click to continue...  </a>");
			}
			else
			{
				
				System.out.println("<p1>Failed try again</p1>");
				
			}
			
	
	}
	catch(Exception e)
	{
		    out.println("<p1>username already taken please select annother username</p1>");
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
    out.println("<a href='http://localhost:8080/Project/LogIn.html'>Click to Login   </a> <br><br>");
	out.println("<a href='http://localhost:8080/Project/SignUP.html'>Click to SignUP </a>");
    out.println("</body></html>");

  

  }

}