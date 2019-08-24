package instagram.clone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import instagram.clone.beans.Post;
import instagram.clone.beans.User;

public class DBUtils {
	
	
	// find user from user_name and password 
	// only use this when a user logs in 
	public static User findUser(Connection conn, String userName, String password) 
			throws SQLException{
		
		String sql = "SELECT user_name, password, bio, registration_date "
				+ "FROM User"
				+ "WHERE user_name = ? AND password = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			User user = new User();
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setBio(rs.getString("bio"));
			user.setRegistrationDate(rs.getTimestamp("registration_date"));
			
			return user;
		}
		return null;
	}
	
	// find a user from user_name
	public static User findUser(Connection conn, String userName) 
			throws SQLException {
		
		String sql = "SELECT user_name, password, bio, registration_date"
				+ "FROM User"
				+ "WHERE user_name = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			User user = new User();
			user.setUserName(userName);
			user.setPassword(rs.getString("password"));
			user.setBio(rs.getString("bio"));
			user.setRegistrationDate(rs.getTimestamp("registration_date"));
			
			return user;
		}
		return null;
	}
	
	// add user to database
	// use this when a user is registering to the app for the first time
	public static void insertUser(Connection conn, User user) throws SQLException {
		String sql = "INSERT INTO User(user_name, password, bio, registration_date)" 
				+ "values (?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getPassword());
		
		if (user.getBio() == null) {
			pstm.setString(3,  null);
		} else {
			pstm.setString(3, user.getBio());
		}
		
		pstm.setTimestamp(4, user.getRegistrationDate());
		
		pstm.executeUpdate();
	}
	
	// add a post to the database
	// user this when a user upload a post 
	public static void insertPost(Connection conn, Post post) throws SQLException { 
		String sql = "INSERT INTO Post(description, author, pictureLink, posted_date, category)"
				+ "values(?, ?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		if (post.getDescription() == null) {
			pstm.setString(1, null);
		}else {
			pstm.setString(1, post.getDescription());
		}
		
		pstm.setString(2, post.getAuthor());
		pstm.setString(3, post.getPictureLink());
		pstm.setTimestamp(4, post.getPostedDate());
		
		if (post.getCategory() == null) {
			pstm.setString(5, null);
		} else {
			pstm.setString(5, post.getCategory());
		}
		
		pstm.executeUpdate();
		
	}
	
	// Find posts by author
	public static Map<Integer, Post> findPost(Connection conn, String author) throws SQLException {
		String sql = "SELECT * FROM Post WEHRE author = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, author);
		
		ResultSet rs = pstm.executeQuery();
		
		Map<Integer, Post> resultMap = new HashMap<Integer, Post>();
		while (rs.next()) {
			Post post = new Post();
			post.setPostId(rs.getInt("post_id"));
			post.setDescription(rs.getString("description"));
			post.setAuthor(rs.getString("author"));
			post.setPictureLink(rs.getString("picture_link"));
			post.setPostedDate(rs.getTimestamp("posted_date"));
			
			resultMap.put(rs.getInt("post_id"), post);
		}
		
		if (resultMap.isEmpty()) {
			return null;
		}
		
		return resultMap;
	}
	
	

}
