package instgram.clone.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import instagram.clone.beans.User;
import instagram.clone.utils.DBUtils;
import instagram.clone.utils.MyUtils;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public RegisterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registerView.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		
		String errorString = null;
		boolean hasError = false;
		
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Please input username and/or password";
		}else {
			Connection conn = MyUtils.getStoredConnection(request);
			
			try {
				
//				User user = DBUtils.findUser(conn, userName);
//				
//				if(user != null) {
//					errorString = "The username is already taken. Please choose a different username.";
//					
//					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB_INF/views/registerView.jsp");
//					dispatcher.forward(request, response);
//				}
				
				User user = new User(userName, password);
				
				DBUtils.insertUser(conn, user);
				
				
			// SQLIntegrityConstraintViolationException handles duplicate entry.
			} catch (SQLIntegrityConstraintViolationException e) {
				hasError = true;
				errorString = "The username is already taken. Please user a different one.";
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		
		// if no error redirect to home page with "Hello username" on top!
		
		// if hasError == true forward to registerView.jsp 
		
		
		
	}

}
