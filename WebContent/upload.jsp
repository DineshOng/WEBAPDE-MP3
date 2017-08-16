<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<html>
    <head>
        <title>Upload</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <script src="jquery-1.8.1.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script>
    		function show(){
    			$("#sharephoto").show();
    		}
    		function hide(){
    			$("#sharephoto").hide();
    		}
    	</script>
    </head>
    <body>
        <div id="header">
            <div id="searchform">
                <form id="btsearch" action="/MP2/homesearchSERVLET" method="POST">
                    <input id="searchinput" name="searchinput" placeholder="Search tag..." required/>
                    <input id="submitsearch" type="submit" value="Search">
                </form>
            </div>  
            <form id="bthome" action="/MP2/homeSERVLET" method="POST">
                 <input id="" type="submit" value="Home">
            </form>
            <form id="btupload" action="/MP2/touploadSERVLET" method="POST">
                 <input id="" type="submit" value="Upload Photo">
            </form>
            <form id="btmyprofile" action="/MP2/myprofileSERVLET" method="POST">
                 <input id="" type="submit" value="My Profile (<%=session.getAttribute("username")%>)">
            </form>
            <form style="float: right" id="btlogout" action="/MP2/logoutSERVLET" method="POST">
                 <input id="" type="submit" value="Log Out">
            </form>
        </div>
        <div id="body">
            <div id="formcontent">
            	<script><%=request.getAttribute("alertUpload")%></script>
                <form method="post" action="/MP2/uploadSERVLET" enctype="multipart/form-data">
                    <input type="file" name="photo" accept="image/*" required/><br><br>
                    Title:<br>
                    <input id="title" name="title" placeholder="Enter Title..." required/><br><br>
                    Description:<br>
                    <textarea rows="5" name="desc" id="descriptioninput" type="text" placeholder="Enter Description..." required/></textarea><br><br>
                    Tags:<br>
                    <textarea rows="3" name="tags" id="tagsinput" type="text" placeholder="ex. good, nice, 4.0" required/></textarea><br><br>
                    Privacy:<br>
                    <input onclick="show()" type="radio" name="privacy" value="private" checked>Private<br>
                    <input onclick="hide()" type="radio" name="privacy" value="public" >Public<br><br>
                    <div id="sharephoto">
                        Share photo with:<br>
                        <%=request.getAttribute("text") %>
                    </div>
                    <br>
                    <input type="submit" value="Upload Photo">
                </form>         
            </div>
        </div>
    </body>
</html>