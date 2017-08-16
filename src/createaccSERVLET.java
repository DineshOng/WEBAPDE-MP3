

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/createaccSERVLET")
public class createaccSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;
	
    public createaccSERVLET() {
        super();
        db = new DB();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.sendRedirect("createacc.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String desc = request.getParameter("description");
		//HttpSession session = request.getSession();
		request.setAttribute("username", uname);
		request.setAttribute("password", pass);
		request.setAttribute("description", desc);
		try{
			if(db.insertAcc(uname, pass, desc))
				request.setAttribute("alertcreate", "alert('Your account has been created!')");
			else 
				request.setAttribute("alertcreate", "alert('Username is taken!')");
		} catch(Exception e){
			System.out.print(e);
		}
		request.getRequestDispatcher("createacc.jsp").forward(request, response);
	}

}
