<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
String id = request.getParameter("id");
try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapde?useSSL=false", "root", "1234");
    
    PreparedStatement ps = con.prepareStatement("select photo from photos where photoid=?");
    ps.setString(1, id);
    ResultSet rs = ps.executeQuery();
 
    if(rs.next()){
        Blob blob = rs.getBlob("photo");
        byte byteArray[] = blob.getBytes(1, (int)blob.length());
 
        response.setContentType("image/jpg");
        OutputStream os = response.getOutputStream();
        os.write(byteArray);
        os.flush();
        os.close();
        out.clear();
        return;
    }
}
catch(Exception e){
}   
%>