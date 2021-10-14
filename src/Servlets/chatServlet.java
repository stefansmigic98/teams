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
import Models.Chat;
import Models.ChatMessage;
import Models.IntResponse;


/**
 * Servlet implementation class chatServlet
 */
@WebServlet("/chatServlet")
public class chatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int chatId = Integer.parseInt(request.getParameter("cid"));
		List<ChatMessage> chat = ChatDAL.getChat(chatId);
		
		response.setContentType("application/json");
	
	    
		response.getWriter().append(new Gson().toJson(chat));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Chat c = new Chat();
		c = gson.fromJson(request.getReader(), Chat.class);
		
		int chatid = ChatDAL.getChatForUsers(c);
		
		if(chatid == -1 )
		{
			chatid = ChatDAL.createChat(c);
		}
		
		response.getWriter().append(gson.toJson(new IntResponse(chatid)));
	}

}
