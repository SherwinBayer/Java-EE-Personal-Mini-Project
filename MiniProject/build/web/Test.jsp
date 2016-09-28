<%-- 
    Document   : Test
    Created on : 7/09/2016, 2:00:55 PM
    Author     : Sherwin
--%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
          // None of this gets displayed if the PrintWriter in doGet, in TestServlet is flushed or closed, why?
          // In conjunction, the servlet doesn't dispatch to this jsp page (If flushed or closed before the RequestDispatcher)
          PrintWriter pw = response.getWriter();
          pw.print(response.getStatus() + "\n");
          
          pw.print(response.getHeader("TestHeader")+ '\n');
          pw.print(response.getHeader("TestIntHeader")+ " ");
          BufferedReader br = request.getReader(); // This does not read the response's body!
          pw.print(br.readLine());
          
        %>    
        <form method="get">
            <input type="submit" value="Go Back" onclick="form.action='Welcome.jsp';"/>
            
        </form>
    </body>
</html>
