<%-- 
    Document   : AdminLogin
    Created on : 5/09/2016, 1:26:17 PM
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
        <h1><center>Admin Login</center></h1>
        
        <h2>Please enter your login credentials (Case Sensitive) </h2>
        
        <%
            String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            
            if(userName == null)
                userName = "";
            if(passWord == null)
                passWord = "";
            
            if(userName.length() > 0 && passWord.length() > 0)
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MiniProject/Authorize");
                dispatcher.forward(request, response);
            }
            else
            {
                out.print("<p><b>Please enter: ");
                if(userName.length() == 0)
                {
                    out.print("Username ");
                    if(passWord.length() == 0)
                        out.print("and ");
                }
                if(passWord.length() == 0)
                    out.print("Password");
                
                out.print("</b></p>");
            }

        %>
        
        <form method="post"> <!-- method="get" displays form fields in URL, post does not, useful when handling personal info !-->
            
            <fieldset>
                <legend>Login:</legend>
                UserName:<br>
                <input type="text" name="username" placeholder="Username"/><br>
                Password:<br>
                <input type="password" name="password" placeholder="Password"/><br>
                <br>
                <input type="submit" value="Login" onclick="form.action='Authorize';"/>
                
            </fieldset>
            <br>
             <input type="submit" value="Go Back" onclick="form.action='Welcome.jsp';"/>
            
        </form>
        
    </body>
</html>
