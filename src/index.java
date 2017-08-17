

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DB db;
    private static String text = "";
    private static ArrayList<String> id = new ArrayList<String>();
    private static ArrayList<String> u = new ArrayList<String>();
    private static ArrayList<String> t = new ArrayList<String>();
    private static ArrayList<String> d = new ArrayList<String>();
    
    public index() {
        super();
        db = new DB();
    }

    public static void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	text="";
		ResultSet rs = db.getPhotos("select photoid, username, title, description from photos where privacy='public' order by 1 desc");
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
						+ "<div id='username' style='display: inline-block'>Uploader: <form style='display: inline-block' action='/MP2/indexprofileSERVLET' method='post'><input style='display:none' name='usern' value='"+u.get(i)+"'><input style='background-color: transparent; border: 0; font-size: 18px' type='submit' id='photo_popup_name' value='"+u.get(i)+"'></form></div>"
						+ "<div id='title'>Title: <a id='photo_popup_album'>"+t.get(i)+"</a></div>"
						+ "<div id='desc'>Description: <a id='photo_popup_title'>"+d.get(i)+"</a></div>"
						+ "<div id='tag'>Tags: <a id='photo_popup_tags'>"+tags+"</a></div>"
						+ "</div>";
				String div2="<div id='pp"+id.get(i)+"' class='popup'>";
				String span="<span onclick='func1("+id.get(i)+")' id='spun' class='close'>&times;</span>";
				String img="<img id='imgpopup' src='getImage.jsp?id="+id.get(i)+"'>";
				text+=div1+"<img onclick='func("+id.get(i)+")' id='thumbnail' src='getImage.jsp?id="+id.get(i)+"' height='190' width='190'/>"+div2+span+info+img+end;
			}
			request.setAttribute("photos", text);
		} catch(Exception e){
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
