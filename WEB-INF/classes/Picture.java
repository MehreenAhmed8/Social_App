import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/Picture")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)

public class Picture extends HttpServlet{
	  
 private static final String SAVE_DIR="PersonsImg";
 public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
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
	      String url10="http://localhost:8080/Project/LogIn.html";
		  out.println("<p1><a href="+url10+">You have to login first...</a></p1><br>");
      }
	  else
	  {
	  
        String name22 = (String)session.getAttribute("username");
        out.println("<p> Username : "+name22 + "<br></p>");
        res.setContentType("text/html;charset=UTF-8");
        String savePath = "C://apache-tomcat-8.5.40/webapps/Project/"+ SAVE_DIR;
                File fileSaveDir=new File(savePath);
                if(!fileSaveDir.exists())
				{
                    fileSaveDir.mkdir();
                }
            Part part=req.getPart("file");
            String fileName=extractFileName(part);
			fileName=name22+"_"+fileName;
            part.write(savePath + File.separator + fileName);  //File me save
		    try
			{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/socialapp","root","root");
            String query="INSERT INTO userphoto (username,photo) values (?, ?)";
           
                PreparedStatement pst;
                pst=con.prepareStatement(query);
                pst.setString(1, name22);
                String filePath= savePath + File.separator + fileName ;
                pst.setString(2,fileName);
                int x = -1;
				x= pst.executeUpdate();
				
			    if (x!=-1)
				{
				out.println("<p1>Picture Successfully added :) </p1>");
				out.println("<a href='http://localhost:8080/Project/UploadPictures.html'>click to add more picture...  </a>");
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

