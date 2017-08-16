

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/homeSERVLET")
public class homeSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DB db;
    private static ArrayList<String> id = new ArrayList<String>();
    private static ArrayList<String> u = new ArrayList<String>();
    private static ArrayList<String> t = new ArrayList<String>();
    private static ArrayList<String> d = new ArrayList<String>();   
    
    public homeSERVLET() {
        super();
        db = new DB();
    }

    public static void init(HttpServletRequest request, HttpServletResponse response, String sql, String txt) throws ServletException, IOException {
    	id.clear();
        u.clear();
        t.clear();
        d.clear();
        String text = "";
		try{
		    ResultSet rs = db.getPhotos(sql);
		    
		    try{
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
							+ "<div id='username' style='display: inline-block'>Uploader: <form style='display: inline-block' action='/MP2/profileSERVLET' method='post'><input style='display:none' name='usern' value='"+u.get(i)+"'><input style='background-color: transparent; border: 0; font-size: 18px' type='submit' id='photo_popup_name' value='"+u.get(i)+"'></form></div>"
							+ "<div id='title'>Title: <a id='photo_popup_album'>"+t.get(i)+"</a></div>"
							+ "<div id='desc'>Description: <a id='photo_popup_title'>"+d.get(i)+"</a></div>"
							+ "<div id='tag'>Tags: <a id='photo_popup_tags'>"+tags+"</a></div>"
							+ "</div>";
					String div2="<div id='pp"+id.get(i)+"' class='popup'>";
					String span="<span onclick='func1("+id.get(i)+")' id='spun' class='close'>&times;</span>";
					String img="<img id='imgpopup' src='getImage.jsp?id="+id.get(i)+"'>";
					text+=div1+"<img onclick='func("+id.get(i)+")' id='thumbnail' src='getImage.jsp?id="+id.get(i)+"' height='190' width='190'/>"+div2+span+info+img+end;
				}
				request.setAttribute(txt, text);
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
	    	String ssuname = (String)session.getAttribute("username");
			String sql = "select distinct p.photoid, p.username, p.title, p.description from photos p, allowlist a, tags t where p.privacy='private' and p.photoid=a.photoid and a.photoid=t.photoid and a.username='"+ssuname+"' order by 1 desc";
			init(request, response, sql, "text");
			sql="select photoid, username, title, description from photos where privacy='public' order by 1 desc";
			init(request, response, sql, "text1");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ssuname = (String)session.getAttribute("username");
		String sql = "select distinct p.photoid, p.username, p.title, p.description from photos p, allowlist a, tags t where p.privacy='private' and p.photoid=a.photoid and a.photoid=t.photoid and a.username='"+ssuname+"' order by 1 desc";
		init(request, response, sql, "text");
		sql="select photoid, username, title, description from photos where privacy='public' order by 1 desc";
		init(request, response, sql, "text1");
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
