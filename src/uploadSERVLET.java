import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/uploadSERVLET")
@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to 16MB
public class uploadSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/webapde?useSSL=false";
	private String dbUser = "root";
	private String dbPass = "1234";
	private DB db;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		db = new DB();
		
		// gets values of text fields
		String u = (String)session.getAttribute("username");
		String t = request.getParameter("title");
		String d = request.getParameter("desc");
		String v = request.getParameter("privacy");
		String tags = request.getParameter("tags");
		String[] tag=null;
		
		if(!(tags.equals(" "))){
			tag = tags.split(", "); ///\\s+
		}
		for(int i=0; i<tag.length; i++){
			System.out.println(tag[i]+"\n");
			db.insertTags(tag[i]);
		}
		
		if(v.equals("private")){
			String[] s = request.getParameterValues("share");
			for(int i=0; i<s.length; i++){
				System.out.println(s[i]);
				db.insertAllowList(s[i]);
			}
		}
		
		ResultSet rs = db.getUsers("select username from accounts");
		String text = "";
		try{
			while(rs.next()){
				text+="<input type='checkbox' name='"+rs.getString(1)+"' value='"+rs.getString(1)+"'>"+rs.getString(1)+"<br>";
			}
			request.setAttribute("text", text);
		} catch(Exception e){
			System.out.print(e);
		}
		InputStream inputStream = null;
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();
		}
		
		Connection conn = null;
		String message = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

			String sql = "INSERT INTO photos (username, title, description, privacy, photo) values (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, u);
			statement.setString(2, t);
			statement.setString(3, d);
			statement.setString(4, v);
			
			if (inputStream != null) {
				statement.setBlob(5, inputStream);
			}

			int row = statement.executeUpdate();
			if (row > 0) {
				message = "Image Uploaded!";
				request.setAttribute("alertUpload", "alert('"+message+"')");
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			request.setAttribute("alertUpload", "alert('"+message+"')");
			//ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			request.getRequestDispatcher("upload.jsp").forward(request, response);
		}
	}
}