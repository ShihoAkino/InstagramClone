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

import instagram.clone.beans.Post;
import instagram.clone.beans.User;
import instagram.clone.utils.DBUtils;
import instagram.clone.utils.MyUtils;

@WebServlet(urlPatterns = {"/addPost"})
public class AddPostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public AddPostServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addPostView.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String errorString = null;
		HttpSession session = request.getSession();
		User user = MyUtils.getLoginedUser(session);
		
		String pictureLink = request.getParameter("pictureLink");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String author = null;
		Post post = null;
		
		if (user != null) {
			author = user.getUserName();
		} else {
			errorString = "Please login to post a photo.";
		}
		
		Connection conn = MyUtils.getStoredConnection(request);
		
		if (pictureLink == null ) {
			errorString = "Please input picture link.";
		} else if (author == null) {
			errorString = "Please login to post a photo.";
		} else {
			post = new Post(description, author, pictureLink, category);
		}
		
		if (errorString == null) {
			try {
				DBUtils.insertPost(conn, post);
			} catch (Exception e) {
				errorString = e.getMessage();
				e.getStackTrace();
			}
		} 
		
		// Store information to request attribute
		request.setAttribute("errorString", errorString);
		
		// If error forward to addPost page
		if (errorString != null) {
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addPostView.jsp");
		dispatcher.forward(request, response);
	   } else {
		   // if no error, redirect to profile view
		   response.sendRedirect(request.getContextPath() + "/profile");
	   }
		
	}

}
