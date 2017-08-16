

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logoutSERVLET
 */
@WebServlet("/logoutSERVLET")
public class logoutSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logoutSERVLET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie cookie = null;
         Cookie[] cookies = null;
         //String c = (String)session.getAttribute("cookie");
         // Get an array of Cookies associated with the this domain
         cookies = request.getCookies();
         
         if( cookies != null ) {
            
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               //if(c!=null){
               if((cookie.getName( )).compareTo("username") == 0 ) {
                  cookie.setMaxAge(0);
                  response.addCookie(cookie);
                  
               }
               //}
            }
         } 
         response.sendRedirect("/MP2/index");
	}

}
