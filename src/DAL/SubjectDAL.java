package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import Models.Subject;

import Data.ConnectionProvider;

public class SubjectDAL {

	public static List<Subject> getSubjectsForUser(int userId){
		List<Subject> subjects = new ArrayList<Subject>();
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT c.id, c.name FROM users_classes uc join classes c on uc.class_id = c.id where uc.user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				subjects.add(new Subject(rs.getInt(1), rs.getString(2), null));
			}
				
			
			return subjects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static int  addSubject(Subject subject,int createdBy)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		subject.setDate_created(new Date(System.currentTimeMillis()));
		try {
			PreparedStatement ps = con.prepareStatement("insert into classes values(NULL,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, subject.getName());
			ps.setDate(2, subject.getDate_created());
			ps.setInt(3, createdBy);
		
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
	
	public static boolean addUserToSubject(int subjectId, int userId)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into users_classes values(NULL,?,?)");
			ps.setInt(1, userId);
			ps.setInt(2, subjectId);
			
			ps.execute();
			return true;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isUserOwner(int subjectId, int userId)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id FROM `classes` WHERE id =? and createdBy = ? ");
			ps.setInt(1, subjectId);
			ps.setInt(2, userId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			return false;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
}
