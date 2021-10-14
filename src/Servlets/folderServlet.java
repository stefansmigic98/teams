package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Models.File;
import Models.IntResponse;

/**
 * Servlet implementation class folderServlet
 */
@WebServlet("/folderServlet")
public class folderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public folderServlet() {
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
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Models.File folder = new Models.File();
		folder = gson.fromJson(request.getReader(), Models.File.class);
		folder.setPath(folder.getPath().replaceAll("_", "/"));
		java.io.File f = new java.io.File(Services.Constants.root + "//" + folder.getPath() + "//" + folder.getName());
		f.mkdir();
		
		response.setContentType("application/json");
		response.getWriter().append(new Gson().toJson(new IntResponse(0)));
		
	}

}
