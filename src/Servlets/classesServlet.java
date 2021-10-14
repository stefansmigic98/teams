package Servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.cj.Constants;
import com.mysql.cj.xdevapi.JsonParser;

import DAL.SubjectDAL;
import Models.Subject;



/**
 * Servlet implementation class classesServlet
 */
@WebServlet("/classesServlet")
public class classesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public classesServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		try {
		
		 id=  (int) request.getSession(false).getAttribute("user");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		List<Subject> subjects = SubjectDAL.getSubjectsForUser(id);
	
		response.setContentType("application/json");
	
	    
		response.getWriter().append(new Gson().toJson(subjects));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		try {
		
		 id=  (int) request.getSession(false).getAttribute("user");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		Gson gson = new Gson();
		Subject s = new Subject();
		 s = gson.fromJson(request.getReader(), Subject.class);
		System.out.println(s.getName());
		int res = SubjectDAL.addSubject(s,id);
		if(res != -1)
		{
			
			File f = new File(Services.Constants.root+"/" +res);
			f.mkdir();
			response.setContentType("application/json");
			response.setStatus(200);
			SubjectDAL.addUserToSubject(res, id);
			
		}
		else
			response.setStatus(400);
		response.setContentType("application/json");
	}

}
