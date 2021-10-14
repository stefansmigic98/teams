package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAL.ChatDAL;
import DAL.SubjectDAL;
import Models.InboxItem;


/**
 * Servlet implementation class inboxServlet
 */
@WebServlet("/inboxServlet")
public class inboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inboxServlet() {
        super();
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
		List<InboxItem> InboxItem = ChatDAL.getInboxForUser(id);
		
		response.setContentType("application/json");
	
	    
		response.getWriter().append(new Gson().toJson(InboxItem));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
