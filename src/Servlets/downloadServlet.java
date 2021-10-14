package Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class downloadServlet
 */
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getParameter("path");
		path = path.replaceAll("_", "/");
		System.out.println(path);
		File file  = new File(".\\FAX\\Eclipse\\Classroom\\src\\Resources\\"+path);
		System.out.println(file.getAbsolutePath());
		response.setContentType(Files.probeContentType(file.toPath()));
		System.out.println(Files.probeContentType(file.toPath()));
		response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
		System.out.println(file.getName());
		FileInputStream fis = null;
		OutputStream responseOutputStream = null;
		try {
		fis = new FileInputStream(file);
		responseOutputStream = response.getOutputStream();
		
		int bytes;
        while ((bytes = fis.read()) != -1) {
        	responseOutputStream.write(bytes);
        }
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}  
		finally {
            fis.close();
            responseOutputStream.close();
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
