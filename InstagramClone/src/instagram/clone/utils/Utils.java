package instagram.clone.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instagram.clone.beans.User;

public class Utils {
	
	public static final String ATT_NAME_FOR_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	
	// Store Connection in request attribute
	// the stored information only exists during a request
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_FOR_CONNECTION, conn);
	}
	
	// Get stored connection
	// Once a connection is opened and stored during a request,
	// use this method to reuse the connection to DB
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_FOR_CONNECTION);
		
		return conn;
	}
	
	// Stores user information in Session
	public static void storeLoginedUser(HttpSession session, User loginedUser) {
		// Access this attibute from JSP like ${loginedUser}
		session.setAttribute("loginedUser", loginedUser);
		
	}
	
	// Get the user information stored in the session
	public static User getLoginedUser(HttpSession session) {
		User loginedUser = (User) session.getAttribute("loginedUser");
		
		return loginedUser;
	}
	
	// Store information in Cookie
	public static void storeUserCookie(HttpServletResponse response, User user) {
		System.out.println("Store user cookie");
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
		// store the information in the cookie for 1 day
		cookieUserName.setMaxAge(24*60*60);
		response.addCookie(cookieUserName);
	}
	
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
					 return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	// Delete cookie
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
		// make this cookie expire in 0 seconds 
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
	
}
