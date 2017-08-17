

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginSERVLET")
public class loginSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DB db;
    
    public loginSERVLET() {
        super();
        db = new DB();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/MP2/index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("logusername");
		String password = request.getParameter("logpassword");
		if(db.login(username, password)){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(1814400);
			response.addCookie(cookie);
			response.sendRedirect("/MP2/homeSERVLET");
		} else {
			request.setAttribute("alertinvalid", "alert('Username or password is incorrect.')");
			response.sendRedirect("/MP2/index");
		}
	}

}
