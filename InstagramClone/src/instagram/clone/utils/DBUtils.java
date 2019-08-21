package instagram.clone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		pstm.setString(3, user.getBio());
		pstm.setTimestamp(4, user.getRegistrationDate());
		
		pstm.executeUpdate();
	}

}
