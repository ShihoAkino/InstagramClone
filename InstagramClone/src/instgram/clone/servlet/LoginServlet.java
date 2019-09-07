package instgram.clone.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instagram.clone.beans.User;
import instagram.clone.utils.DBUtils;
import instagram.clone.utils.MyUtils;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);
		
		User user = null;
		String errorString = null;
		boolean hasError = false;
		
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Please input username and/or password.";
		} else {
			Connection conn = MyUtils.getStoredConnection(request);
			
			try {
				user = DBUtils.findUser(conn, userName, password);
				
				if (user == null) {
					hasError = true;
					errorString = "Invalid Username or Password.";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		
		// If error, forward to login view
		if (hasError) {
			request.setAttribute("errorString", errorString);
			
			// may have to be this.getSErvletContext() rather than request.
			// I don't know the difference so let's try 
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
			
		} else {
			// store user info in session
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);
			
			if(remember) {
				MyUtils.storeUserCookie(response, user);
			} else {
				MyUtils.deleteUserCookie(response);
			}
			
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
		
	}
	
	

}
