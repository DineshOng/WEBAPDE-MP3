<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<html>
    <head>
        <title>Instagram RIP-OFF</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="images.css" />
        
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script>
        	function func(id){
        		var x = document.getElementById("pp"+id);   // Get the element with id="demo"
        		x.style.display = "block";  
        	}
        	function func1(id){
        		var x = document.getElementById("pp"+id);   // Get the element with id="demo"
        		x.style.display = "none";  
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
        
        	<%=request.getAttribute("text") %>
    </body>
</html>