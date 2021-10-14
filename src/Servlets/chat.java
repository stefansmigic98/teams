package Servlets;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import DAL.ChatDAL;
import Models.ChatMessage;



@ServerEndpoint("/chat/{senderid}")
public class chat {
		//static Set<Session,int> chatRoomUsers = Collections.synchronizedSet(new HashSet<>());
		static HashMap<Integer,Session > users = new HashMap<Integer,Session>();
	 	@OnOpen
	    public void onOpen(Session session, @PathParam("senderid") int senderid ) {
	 		users.put(senderid, session);
	 		System.out.println("user " +  senderid +" konektovan");
	 		
	 		
	    }
	    @OnMessage
	    public void onMessage(Session session, String message, @PathParam("senderid") int senderid ) {
	      System.out.println(message);
	      //send to another user
	      
	      
	      
	      //store to database
	      System.out.println(senderid);
	      Gson gson = new Gson();
	      ChatMessage msg = gson.fromJson(message, ChatMessage.class);
	      int reciver = ChatDAL.getOtherFromChat(msg.getChatId(), senderid);
	      msg.setReciverId(reciver);
	      msg.setSenderId(senderid);
	      ChatDAL.addMessage(msg);
	      
	      if(users.containsKey(reciver))
	      {
	    	  users.get(reciver).getAsyncRemote().sendText("poruka");
	      }
	      
	    }
	    @OnClose
	    public void onClose(Session session,@PathParam("senderid") int senderid) {
	        
	    }
}
