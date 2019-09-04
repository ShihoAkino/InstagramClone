package instgram.clone.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instagram.clone.beans.Post;
import instagram.clone.beans.User;
import instagram.clone.utils.DBUtils;
import instagram.clone.utils.MyUtils;

@WebServlet(urlPatterns= {"/profile"})
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1l;
	
	public ProfileServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = MyUtils.getLoginedUser(session);
		
		
		
		String errorString = null;
		boolean hasError = false;
		Map<Integer, Post> posts = null;
		
		if (user != null) {
			
			Connection conn = MyUtils.getStoredConnection(request);
			
			try {
				
				
				// select posts
				if(user.getUserName() != null) {
					String userName = user.getUserName();
					
					posts = DBUtils.findPost(conn, userName);
			    }
				
				
			} catch (Exception e) {
				hasError = true;
				errorString = e.getMessage();
				e.printStackTrace();
				
			}
		} else {
			hasError = true;
			errorString = "Please login.";
		}
		
		if(hasError) {
			
			
			// if error just forward to loginView with error message
			request.setAttribute("errorString", errorString);
//			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
//			
//			dispatcher.forward(request, response);
			
			response.sendRedirect(request.getContextPath() + "/home");
			
		} else {
			
		//else if no errors , forward to proileView with posts.
		request.setAttribute("posts", posts);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
		dispatcher.forward(request, response);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		this.doGet(request, response);
		
	}
	

}
