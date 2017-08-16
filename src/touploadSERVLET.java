
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class touploadSERVLET
 */
@WebServlet("/touploadSERVLET")
public class touploadSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DB db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public touploadSERVLET() {
        super();
        db = new DB();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try{
		    ResultSet rs = db.getUsers("select username from accounts where username!='"+session.getAttribute("username")+"'");
		    String text = "";
			try{
				while(rs.next()){
					text+="<input type='checkbox' name='share' value='"+rs.getString(1)+"'>"+rs.getString(1)+"<br>";
				}
				request.setAttribute("text", text);
			} catch(Exception e){
			}
		} catch(Exception ee){
		};
		request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

}
