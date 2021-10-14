package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.ConnectionProvider;
import Models.Post;


public class PostsDAL {
	
	public static List<Post> getPosts(int subjectId)
	{
		List<Post> posts = new ArrayList<Post>();
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id,content,class_id  from posts where class_id=? order by id desc");
			ps.setInt(1, subjectId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				posts.add(new Post(rs.getInt(1), rs.getString(2),rs.getInt(3)));
			}
				
			
			return posts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean createPost(Post post)
	{
		Connection con = ConnectionProvider.getconnnConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into posts values(NULL,?,NOW(),?)");
			ps.setString(1, post.getContent());
			ps.setInt(2, post.getSubjectId());
			
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

}
