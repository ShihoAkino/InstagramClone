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
import javax.servlet.http.HttpSession;

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
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		
		System.out.println("in doPost");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String bio = request.getParameter("bio");
		
		User user = new User(userName, password, bio);

		
		String errorString = null;
		boolean hasError = false;
		
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Please input username and/or password";
			
		}else {
			Connection conn = MyUtils.getStoredConnection(request);
			System.out.println("get sql connection");
			System.out.println(conn.toString());
			
			try {

				
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
		
		
		
		if(hasError) {
			// Store error message in request attribute
			request.setAttribute("errorString", errorString);
			
			// Forward to register view 
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");
			dispatcher.forward(request, response);	
		}
		
		// if no error redirect to home page with "Hello username" on top!
		else {
			// Set user in formation in session
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);
			
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
	
		
		
		
	}

}
