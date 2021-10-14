package Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import RMI.IFileUploader;
import Services.FilesManager;


/**
 * Servlet implementation class filesServlet
 */
@WebServlet("/filesServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)
public class filesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int parrentId = Integer.parseInt(request.getParameter("pid"));
		String subjectId = request.getParameter("sub").toString();
		System.out.println(subjectId);
		subjectId = subjectId.replaceAll("_","/");
		System.out.println(subjectId);
		response.setContentType("application/json");
		List<Models.File> files = FilesManager.getFilesForSubjectId(subjectId, parrentId);
		response.getWriter().append(new Gson().toJson(files));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getParameter("path");
		Part filePart = request.getPart("file"); 
		InputStream fileContent = filePart.getInputStream();
		//Part path = request.getPart("path");
		String fileName = getSubmittedFileName(filePart);
		System.out.println(fileName);
		path = path.replaceAll("_","/");
		path = Services.Constants.root +"\\" +path+"\\"+ fileName;
		
		 byte[] fileData = new byte[8192];
			
		 filePart.getInputStream().read(fileData, 0, fileData.length);	
		 
		//copyInputStreamToFile(filePart.getInputStream(),newFile);
		
		try {
			IFileUploader fu = (IFileUploader) Naming.lookup("rmi://localhost:1099/Server");
			fu.uploadFile(path, fileData,fileData.length);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
	
	 private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {

	        // append = false
	        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
	            int read;
	            byte[] bytes = new byte[8192];
	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	        }

	    }

}
