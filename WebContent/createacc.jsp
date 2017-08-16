<html>
    <head>
        <title>Create Account</title>
        <link rel="stylesheet" type="text/css" href="createacc.css" />
    </head>
    <body>
        <div id="header">
            <div id="searchform">
                <form id="btsearch" action="/MP2/indexsearchSERVLET" method="POST">
                    <input id="searchinput" name="searchinput" placeholder="Search tag..." required/>
                    <input id="submitsearch" type="submit" value="Search">
                </form>
            </div>
            <button><a id="bthome" href="index.jsp">Home</a></button>
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
    	<div id="body">
    		<script><%=request.getAttribute("alertcreate")%></script>
    		<form action="/MP2/createaccSERVLET" method="POST">
    			<span id="lbcreatenewacc">Create a New Account</span><br><br>
                <input id="usernameinput" name="username" type="text" placeholder="Username" required/><br><br>
                <input id="passwordinput" name="password" type="password" placeholder="Password" required/><br><br>
                <textarea id="descriptioninput" name="description" placeholder="Enter Short Description..."/></textarea><br><br>
                <input id="submitcreatenewacc" type="submit" value="Create Account">
            </form>
        </div>
    </body>
</html>