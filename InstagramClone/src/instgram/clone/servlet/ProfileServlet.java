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

import instagram.clone.beans.Post;
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
		
		String userName = request.getParameter("userName");
		
		String errorString = null;
		boolean hasError = false;
		Map<Integer, Post> posts = null;
		
		if (!(userName == null || userName.length() == 0)) {
			
			Connection conn = MyUtils.getStoredConnection(request);
			
			try {
				
				// select posts
				posts = DBUtils.findPost(conn, userName);
				
				
			} catch (Exception e) {
				hasError = true;
				errorString = e.getMessage();
				e.printStackTrace();
				
			}
		}
		
		if(hasError) {
			// if error just forward to profileView with error message for now
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
			
			dispatcher.forward(request, response);
		}
		//else if no errors , forward to proileView with posts.
		request.setAttribute("posts", posts);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
		dispatcher.forward(request, response);
			
		
	}
	

}
