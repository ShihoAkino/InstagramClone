package instagram.clone.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import instagram.clone.beans.User;
import instagram.clone.utils.DBUtils;
import instagram.clone.utils.MyUtils;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {
	
	public CookieFilter() {}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {}
	
	@Override 
	public void destroy() {}
	
	@Override 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws ServletException, IOException {
		System.out.println("cookie Filter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		User userInSession = MyUtils.getLoginedUser(session);
		
		if (userInSession != null) {
			// "CHECKED" means user is stored in the cookie 
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		} 
		
		// Get connection that was created in JDBC Filter
		Connection conn = MyUtils.getStoredConnection(request);
		
		// Flag check cookie
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) {
			String userName = MyUtils.getUserNameInCookie(req);
			if (userName != null) {
				try {
					User user = DBUtils.findUser(conn, userName);
					MyUtils.storeLoginedUser(session, user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// Mark checked cookies
				session.setAttribute("COOKIE_CHECKED", "CHEKCED");
			}
		}
		
		chain.doFilter(request, response);
		
	}
	

}
