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


@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public HomeServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String errorString = null;
		boolean hasError = false;
		Map<Integer, Post> posts = null;
		
		Connection conn = MyUtils.getStoredConnection(request);
		try {
			
			
			// select posts
		
			
			posts = DBUtils.findPost(conn, null);
		
		
		} catch (Exception e) {
			hasError = true;
			errorString = e.getMessage();
			e.printStackTrace();
			request.setAttribute("errorString", errorString);
		}
		
		// Forward to homeView with posts.
		request.setAttribute("posts", posts);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
		dispatcher.forward(request, response);
	}
			
	
		
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
 
}
