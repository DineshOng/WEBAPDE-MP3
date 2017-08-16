import java.util.*;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class myprofileSERVLET
 */
@WebServlet("/myprofileSERVLET")
public class myprofileSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DB db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myprofileSERVLET() {
        super();
        db = new DB();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
	    String text = "";
		try{
			ResultSet rs = db.getUsers("select description from accounts where username='"+session.getAttribute("username")+"'");
			
		 	

			
			ArrayList<String> id = new ArrayList<String>();
			ArrayList<String> u = new ArrayList<String>();
			ArrayList<String> t = new ArrayList<String>();
			ArrayList<String> d = new ArrayList<String>();
		    
		    try{
		    	if(rs.next()){
					text+="<div id='details'><span id='lbmpuname'>Username: "+session.getAttribute("username")+"</span><br><span id='lbmpdesc'>Description: "+rs.getString(1)+"</span></div><div id='image_container'>";
				}
		    	rs = db.getPhotos("select photoid, username, title, description from photos where username='"+session.getAttribute("username")+"' order by 1 desc");
				while(rs.next()){
					id.add(rs.getString(1));
					u.add(rs.getString(2));
					t.add(rs.getString(3));
					d.add(rs.getString(4));
				}
				
				String div1="\n<div id='image_item_container'>";
				String end="</div></div>\n\n";
				
				for(int i=0 ; i<id.size(); i++){
					String tags = "";
					rs = db.getTags("select distinct tagname from tags where photoid='"+id.get(i)+"'");
					while(rs.next()){
						tags+=rs.getString(1)+", ";
					}
					String info="<div id='imginfo'>"
							+ "<div id='username'>Uploader: <a id='photo_popup_name'>"+u.get(i)+"</a></div>"
							+ "<div id='title'>Title: <a id='photo_popup_album'>"+t.get(i)+"</a></div>"
							+ "<div id='desc'>Description: <a id='photo_popup_title'>"+d.get(i)+"</a></div>"
							+ "<div id='tag'>Tags: <a id='photo_popup_tags'>"+tags+"</a></div>"
							+ "<div id='addtag'><form action='/MP2/addTagSERVLET' method='post'><input name='tags' required/><input style='display:none' name='idd' value='"+id.get(i)+"'><input type='submit' value='add tag(s)'></form></div>"
							+ "</div>";
					String div2="<div id='pp"+id.get(i)+"' class='popup'>";
					String span="<span onclick='func1("+id.get(i)+")' id='spun' class='close'>&times;</span>";
					String img="<img id='imgpopup' src='getImage.jsp?id="+id.get(i)+"'>";
					text+=div1+"<img onclick='func("+id.get(i)+")' id='thumbnail' src='getImage.jsp?id="+id.get(i)+"' height='190' width='190'/>"+div2+span+info+img+end;
				}
				request.setAttribute("text", text);
			} catch(Exception e){
			}
		}catch (Exception ee){
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		if(cookies!=null){
			for(Cookie cookie: cookies){
				switch(cookie.getName()){
				case "username":
					session.setAttribute(cookie.getName(), cookie.getValue());
					flag = true;
					break;
				}
			}
		}
		if(!flag)
			response.sendRedirect("/MP2/index");
		else{
			init(request, response);
			request.getRequestDispatcher("myprofile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		request.getRequestDispatcher("myprofile.jsp").forward(request, response);
	}

}
