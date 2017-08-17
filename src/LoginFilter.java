import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// you can put as much url that you want this filter to intercept
@WebFilter(urlPatterns = { "/home.jsp", "/homeSearch.jsp", "/myprofile.jsp", "/profile.jsp", "/upload.jsp", "/addTagSERVLET", "/homesearchSERVLET", "/homeSERVLET", "/profileSERVLET", "/myprofileSERVLET", "/touploadSERVLET", "/uploadSERVLET" })
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// cast to HttpServlet for use
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// retrieve person from session 
		//Person person = (Person)httpRequest.getSession().getAttribute("person");
		HttpSession session = ((HttpServletRequest) request).getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			// person is null means the user is not logged in. 
			// forward user to login page
			httpResponse.sendRedirect("/MP2/index");
		} else {
			// if person is logged in then continue with the request
			chain.doFilter(request, response);
		}
		
	}

	// implement the other methods required in the interface 
	// but leave as empty usually used for clean up etc.
	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}	
}
