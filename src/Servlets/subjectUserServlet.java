package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAL.SubjectDAL;
import DAL.UserDAL;
import Models.Subject;
import Models.UserSubject;

/**
 * Servlet implementation class subjectUserServlet
 */
@WebServlet("/subjectUserServlet")
public class subjectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public subjectUserServlet() {
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
		UserSubject us = new UserSubject();
		us = gson.fromJson(request.getReader(), UserSubject.class);
		
		int userid = UserDAL.getIdForEmail(us.getEmail());
		System.out.println(userid);
		if(userid == -1)
		{
			response.setStatus(400);
			return;
		}
		SubjectDAL.addUserToSubject( us.getSubjectId(),userid);
		response.setContentType("application/json");
		response.setStatus(200);
	}

}
