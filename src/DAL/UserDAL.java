package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.ConnectionProvider;
import Models.User;





public class UserDAL {

	

	
	public static int register(User user)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into users values(NULL,?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			ps.execute();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
		
		
	}
	
	public static int login(String email, String password)
	{
		
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id from users where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getInt(1);
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public static int getIdForEmail(String email)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id from users where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getInt(1);
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
