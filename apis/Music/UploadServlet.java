import java.io.*;
import java.util.*;
import java.sql.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import org.json.*;

public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   //private int maxFileSize =50 * 50 * 1024;
  // private int maxMemSize = 50* 4 * 1024;
   private File file ;
   private  String  user_name,music_name, artist_name, genre,coverurl;
   private	int genreid, user_id;
 

   public void init( ){
      // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload"); 
   }
   
  
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
   
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
	  System.out.println("Servlet Called");
   
      if( !isMultipart ) {
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
  
      DiskFileItemFactory factory = new DiskFileItemFactory();
   
      // maximum size that will be stored in memory
      //factory.setSizeThreshold(maxMemSize);
   
      // Location to save data that is larger than maxMemSize.
      //factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
   

      try { 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);
		System.out.println(fileItems.toString());
         // Process the uploaded file items
         Iterator i = fileItems.iterator();

         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
		
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
			System.out.println(fi.toString());
            if ( !fi.isFormField () ) {
				
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               String fileName = fi.getName();
               String contentType = fi.getContentType();
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file
			   if(fieldName.equals("file")){
					if( fileName.lastIndexOf("\\") >= 0 ) {
						file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
					} else {
						file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					System.out.println(file.toString()	);
					fi.write( file ) ;
			   }else if(fieldName.equals("username")){
				      user_name = fi.getName();
			   }else if(fieldName.equals("musicname")){
				   music_name =fi.getName();
			   }else if(fieldName.equals("artistname")){
				   artist_name =fi.getName();
			   }else if(fieldName.equals("genre")){
				   genre =fi.getName();
			   }
			         
               System.out.println("Uploaded Filename: " + fileName + "<br>");
			   
            }
         }
         Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica","root","root");
            PreparedStatement stmt = con.prepareStatement("select g.id, g.Img_url,u.User_ID from genre g, user u where g.genre_name=? and u.User_Name=? ");
            		stmt.setString(1,genre);
			stmt.setString(2,user_name);
	   
            ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				genreid = rs.getInt("id");
				coverurl=rs.getString("Img_url");
				user_id=rs.getInt("User_ID");
				
				PreparedStatement stmt1 = con.prepareStatement("insert into music(user_id,genre_id,name,artist_name,cover_img_url,Music_url) values(?,?,?,?,?,?);");
				stmt1.setInt(1,user_id);
				stmt1.setInt(2,genreid);
				stmt1.setString(3,music_name);
				stmt1.setString(4,artist_name);
				stmt1.setString(5,coverurl);
				stmt1.setString(6,"music/"+music_name+".mp3");
				int result = stmt1.executeUpdate();
				JSONObject res = new JSONObject();
				res.put("result",result);
				out.println(res.get("result"));
			}
        } catch(Exception ex) {
		ex.printStackTrace();
    }
	  }
      
      public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, java.io.IOException {

         throw new ServletException("GET method used with " +
            getClass( ).getName( )+": POST method required.");
      }
   }

