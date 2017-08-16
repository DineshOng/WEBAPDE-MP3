import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/indexsearchSERVLET")
public class indexsearchSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DB db;
    
    public indexsearchSERVLET() {
        super();
        // TODO Auto-generated constructor stub
        db = new DB();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/MP2/index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagname=request.getParameter("searchinput");
		//HttpSession session = request.getSession();
		
		String text = "";
		
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<String> u = new ArrayList<String>();
		ArrayList<String> t = new ArrayList<String>();
		ArrayList<String> d = new ArrayList<String>();
		
		ResultSet rs = db.getPhotos("select distinct * from photos p, tags t where p.privacy='public' and p.photoid=t.photoid and t.tagname='"+tagname+"' order by 1 desc");
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
			if(text.equals("")){
				text="We couldn't find anything for <b>"+tagname+"</b>";
			}
			request.setAttribute("publicphotos", text);
		} catch(Exception e){
			System.out.print(e);
		}
		request.getRequestDispatcher("indexSearch.jsp").forward(request, response);
	}

}
