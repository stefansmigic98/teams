package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Data.ConnectionProvider;
import Models.Chat;
import Models.ChatMessage;
import Models.InboxItem;
import Servlets.chat;


public class ChatDAL {
	
	public static List<InboxItem> getInboxForUser(int userId)
	{
		List<InboxItem> inbox = new ArrayList<InboxItem>();
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select inb.id, inb.sender, inb.message, u.name\r\n"
					+ "from (select c.id as id, CASE WHEN c.user1_id=? THEN c.user2_id ELSE c.user1_id END as sender,m.message as message from (chat c INNER join messages m on c.id= m.chat_id) left join messages m1 on c.id= m1.chat_id and m.date_created < m1.date_created where (c.user1_id = ? or c.user2_id=?) and m1.date_created is null) inb INNER join users u on inb.sender = u.id");
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			ps.setInt(3, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				inbox.add(new InboxItem(rs.getInt(1), rs.getString(3), rs.getInt(2),rs.getString(4)));
			}
			return inbox;
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ChatMessage> getChat(int chatId)
	{
		List<ChatMessage> chat = new ArrayList<ChatMessage>();
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from messages WHERE chat_id = ? order by date_created");
			ps.setInt(1, chatId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				chat.add(new ChatMessage(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getString(4),rs.getInt(6)));
			}
			return chat;
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getOtherFromChat(int chatId, int userId)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id, CASE WHEN user1_id=? THEN user2_id  ELSE user1_id END as other from chat where id=?");
			ps.setInt(1, userId);
			ps.setInt(2, chatId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return rs.getInt(2);
			}
			return -1;
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public static int getChatForUsers(Chat c)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id from chat where (user1_id = ? and user2_id =?) or (user2_id = ? and user1_id =?) ");
			c.setUser2id(UserDAL.getIdForEmail(c.getUser2email()));
			ps.setInt(1, c.getUser1id());
			ps.setInt(2, c.getUser2id());
			ps.setInt(3, c.getUser2id());
			ps.setInt(4, c.getUser1id());
		
			
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			return -1;
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public static boolean addMessage(ChatMessage chatMessage)
	{
			Connection con = ConnectionProvider.getconnnConnection();
			System.out.println("sender = " + chatMessage.getSenderId());
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into messages values(NULL,?,?,?,NOW(),?)");
			ps.setInt(1, chatMessage.getSenderId());
			ps.setInt(2, chatMessage.getReciverId());
			ps.setString(3, chatMessage.getMessage());
			ps.setInt(4, chatMessage.getChatId());
			
			
			ps.execute();
			return true;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static int createChat(Chat chat)
	{

		Connection con = ConnectionProvider.getconnnConnection();
	
	try {
		PreparedStatement ps = con.prepareStatement("insert into chat values(NULL,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, chat.getUser1id());
		int user2id = UserDAL.getIdForEmail(chat.getUser2email());
		System.out.println(user2id);
		ps.setInt(2, user2id);
		
		ps.executeUpdate();
		ResultSet keys = ps.getGeneratedKeys();
		 if (keys.next()) {
                return keys.getInt(1);
            }
		return 0;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
	}

}
