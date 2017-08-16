

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addTagSERVLET
 */
@WebServlet("/addTagSERVLET")
public class addTagSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;
    /**
     * Default constructor. 
     */
    public addTagSERVLET() {
    	db = new DB();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("/MP2/addTagSERVLET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idd");
		String tags = request.getParameter("tags");
		String[] tag=null;
		
		if(!(tags.equals(" "))){
			tag = tags.split(", "); ///\\s+
		}
		for(int i=0; i<tag.length; i++){
			System.out.println(tag[i]+"\n");
			db.insertTags1(id, tag[i]);
		}
		request.setAttribute("click", "alert('tag(s) added!')");
		response.sendRedirect("/MP2/myprofileSERVLET");
	}

}
