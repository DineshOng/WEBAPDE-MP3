

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginSERVLET
 */
@WebServlet("/loginSERVLET")
public class loginSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DB db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginSERVLET() {
        super();
        db = new DB();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("/MP2/index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
