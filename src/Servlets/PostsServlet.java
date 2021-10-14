package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAL.PostsDAL;
import Models.Post;
import Models.UserSubject;

/**
 * Servlet implementation class PostsServlet
 */
@WebServlet("/PostsServlet")
public class PostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int subjectId = Integer.parseInt(request.getParameter("sid"));
		List<Post> posts = PostsDAL.getPosts(subjectId);
		response.setContentType("application/json");
		response.getWriter().append(new Gson().toJson(posts));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Post post = new Post();
		post = gson.fromJson(request.getReader(), Post.class);
		
		PostsDAL.createPost(post);
		response.setContentType("application/json");
		response.getWriter().append(new Gson().toJson(post));
	}

}
