<%-- 
    Document   : BookFound
    Created on : 2/09/2016, 6:16:04 PM
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
        <h1>Book Found</h1>
        
        <jsp:useBean id="book" class="essentials.BookBean" scope="session" /> <%-- Beans have to be placed in a package! --%>
        
        <p>The book <jsp:getProperty name="book" property="bookName" /> was found within the database </p>
        <p>Lesser than a year old: <jsp:getProperty name="book" property="oneYearOrNot" /> and customers say <jsp:getProperty
                name="book" property="recommend" /> as a recommendation </p>
        
        <p>Click the button to go back and perform another search</p><br>
        <form method="get">
            <input type="submit" value="Go Back" onclick="form.action='Welcome.jsp';"/>
            
        </form>
        
    </body>
</html>
