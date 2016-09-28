<%-- 
    Document   : LoginFailed
    Created on : 5/09/2016, 2:20:39 PM
    Author     : Sherwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Repository</title>
    </head>
    <body>
        <h1>Login Failed</h1>
        
        <jsp:useBean id="admin" class="essentials.AdminBean" scope="session" />
        
        <p>Sorry, the admin:  <jsp:getProperty name="admin" property="userName" /> was not found within the database, or you have entered
            an incorrect password.</p>
        
        <p>Click the button to go back and perform something else or try login again</p><br>
        <form method="get">
            <input type="submit" value="Search" onclick="form.action='Welcome.jsp';"/>
            <input type="submit" value="Login" onclick="form.action='AdminLogin.jsp';"/>
        </form>
    </body>
</html>
