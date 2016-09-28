<%-- 
    Document   : LoginSuccess
    Created on : 5/09/2016, 2:20:29 PM
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
        <h1>Login Success</h1>
        
        <jsp:useBean id="admin" class="essentials.AdminBean" scope="session" />
        
        <p>The admin: <jsp:getProperty name="admin" property="userName" /> was found within the database, hence a valid admin. </p>
        
        <p>Click the button to go back or perform administrative duties</p><br>
        <form method="get">
            <input type="submit" value="Go Back" onclick="form.action='Welcome.jsp';"/>
            <input type="submit" value="Admin Page" onclick="form.action='WelcomeAdmin.jsp';"/>
        </form>
    </body>
</html>
