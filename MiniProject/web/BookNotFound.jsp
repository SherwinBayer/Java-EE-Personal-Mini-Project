<%-- 
    Document   : BookNotFound
    Created on : 2/09/2016, 6:16:43 PM
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
        <h1>Book Not Found</h1>
        
        <jsp:useBean id="book" class="essentials.BookBean" scope="session" />
        
        <p>The book <jsp:getProperty name="book" property="bookName" /> was not found within the database </p>
        
        <p>Click the button to go back and perform another search</p><br>
        <form method="get">
            <input type="submit" value="Go Back" onclick="form.action='Welcome.jsp';"/>
            
        </form>
    </body>
</html>
