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
                <form id="btsearch" action="/MP2/indexsearchSERVLET" method="POST">
                    <input id="searchinput" name="searchinput" placeholder="Search tag..." required/>
                    <input id="submitsearch" type="submit" value="Search">
                </form>
            </div>
            <form id="bthome" action="/MP2/index" method="POST">
                    <input id="submitsearch" type="submit" value="Home">
            </form>
            <button><a id="btcreateacc" href="createacc.jsp">Create Account</a></button>
            
            <div id="loginform">
            	<script><%=request.getAttribute("alertinvalid")%></script>
                <form action="/MP2/loginSERVLET" method="POST">
                    <span id="lbusername">Username</span>
                    <input name="logusername" id="usernameinput" placeholder="Username" required/>
                    <span id="lbpassword">Password</span>
                    <input name="logpassword" id="passwordinput" type="password" placeholder="Password" required/>
                    <input id="submitlogin" type="submit" value="Log In">
                    <input type="checkbox" name="rememberMe" value="1" /><span style="color: white; font-size: 12">Remember Me</span>
                </form>
            </div>
        </div>
        
        <div id="image_container">
        	<%=request.getAttribute("publicphotos") %>
        </div>
    </body>
</html>