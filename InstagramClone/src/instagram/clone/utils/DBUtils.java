package instagram.clone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import instagram.clone.beans.User;

public class DBUtils {
	
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

}
