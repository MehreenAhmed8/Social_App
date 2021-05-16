import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.*;

@WebServlet("/Pic")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)

public class Pic extends HttpServlet {
    private static final String SAVE_DIR="images";
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException 
			{
				
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
	  
	 if(session == null)
	  {
		  
		  out.println("<html>");
          out.println("<head>");
          out.println("</head>");
          out.println("<body>");
		 out.println("<p1>Click to login</p1>");
	     String url1="http://localhost:8080/Project/SignUP.html";
		out.println("<p1><a href="+url1+">You have to SignUp first...</a></p1><br>");
      }
	  else
	  {

     String firstName = (String)(session.getAttribute("username"));
		String savePath = "C://apache-tomcat-8.5.40/webapps/Project/"+ SAVE_DIR;
                File fileSaveDir=new File(savePath);
                if(!fileSaveDir.exists())
				{
                    fileSaveDir.mkdir();
                }
            
			System.out.println("User : "+firstName);
			
            Part part=request.getPart("file");
            String fileName=extractFileName(part);
            part.write(savePath + File.separator + fileName);
    
		    try
			{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/socialapp","root","root");
            String query="INSERT INTO imagestore (username,profile) values (?, ?)";
           
                PreparedStatement pst;
                pst=con.prepareStatement(query);
                pst.setString(1, firstName);
                String filePath= savePath + File.separator + fileName ;
                pst.setString(2,fileName);
                int x = -1;
				x= pst.executeUpdate();
				
			    if (x!=-1)
				{
				out.println("<p1>account successfully created </p1>");
				out.println("<a href='http://localhost:8080/Project/LogIn.html'>Please Login to continue...  </a>");
			}
			}
			catch(Exception  e)
			{
				System.out.println(e);
			}		
	  }			
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) 
		{
            if (s.trim().startsWith("filename"))
			{
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
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

