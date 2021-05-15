package Controller.RestServlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.User;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/**
 * Servlet implementation class UploadFileControllers
 */
@WebServlet("/UploadFileControllers")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 100, // 10MB
maxRequestSize = 1024 * 1024 * 200) // 50MB
public class UploadFileControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "UploadFile";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8"); 
		
		PrintWriter out = response.getWriter();
		System.out.println("nha");
		try {
	         
	           String appPath = request.getServletContext().getRealPath("");
	           System.out.println("here 1 " + appPath);
				System.out.println("here 2 " + request.getServletContext().getContextPath());

			appPath = appPath.replace('\\', '/');
	 
	 
	           String fullSavePath = null;
	           if (appPath.endsWith("/")) {
	               fullSavePath = appPath + SAVE_DIRECTORY;
	           } else {
	               fullSavePath = appPath + "/" + SAVE_DIRECTORY;
	           }
	  			System.out.println("here "+ fullSavePath);

	           File fileSaveDir = new File(fullSavePath);
	           if (!fileSaveDir.exists()) {
	               fileSaveDir.mkdir();
	           }
			System.out.println("here 3 " + request.getParts());
			System.out.println("here 4 " + request.getParts().size());

	           for (Part part : request.getParts()) {
	               String fileName = extractFileName(part);
				   System.out.println("here 5 " + fileName);
				   String type = request.getParameter("type");
				   String id = request.getParameter("id");
				   if (fileName != null && fileName.length() > 0) {
					   String orinalFileName = fileName;
					   String extension = FilenameUtils.getExtension(orinalFileName);
					   fileName = type + id + "." + extension;
					   System.out.println("ten hinh "+fileName);
	                   String filePath = fullSavePath + File.separator + fileName;
	                   System.out.println("Write attachment to file: " + filePath);
	                   request.setAttribute("link", fileName);

					   HttpSession session = request.getSession();
					   Connection connection = MyUtils.getStoredConnection(request);
					   User user = MyUtils.getLoginedUser(session);
					   if(type.equals("contract"))
					   	DBUtils.updateContract(connection, Integer.valueOf(id), fileName, user.getEmployeeId());
					   else
							DBUtils.updatePortrait(connection, Integer.valueOf(id), fileName, user.getEmployeeId());
	                   // Ghi vÃ o file.
	                   part.write(filePath);
	                   out.write(fileName);
	                   out.flush();

	               }
	           }
	  
	
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	           System.out.println("loi nay la gi: "+ e.getMessage());
	           
	       }
	}
	
	private String extractFileName(Part part) {
	       // form-data; name="file"; filename="C:\file1.zip"
	       // form-data; name="file"; filename="C:\Note\file2.zip"
	       String contentDisp = part.getHeader("content-disposition");
	       String[] items = contentDisp.split(";");
	       for (String s : items) {
	           if (s.trim().startsWith("filename")) {
	               // C:\file1.zip
	               // C:\Note\file2.zip
	               String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	               clientFileName = clientFileName.replace("\\", "/");
	               int i = clientFileName.lastIndexOf('/');
	               // file1.zip
	               // file2.zip
	               return clientFileName.substring(i + 1);
	           }
	       }
	       return null;
	   }
	

}
