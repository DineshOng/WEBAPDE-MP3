

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addTagSERVLET")
public class addTagSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;
   
    public addTagSERVLET() {
    	db = new DB();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/MP2/addTagSERVLET");
	}

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
