<%-- 
    Document   : WelcomeAdmin
    Created on : 6/09/2016, 4:07:43 PM
    Author     : Sherwin
--%>

<%@page import="essentials.Operations"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Repository</title>
    </head>
    <body>
        <%! private ArrayList bookNames; %>
        <%! private ArrayList releases; %>
        <%! private ArrayList recommendations; %>
        <h1><center>Book Repository</center></h1>
        
        <h4>Here you can perform administrative duties</h4>
        
        
        <%
            String bookName = request.getParameter("booknameadmin");
            String option = request.getParameter("radiobtnadmin");
            String dropDown = request.getParameter("recommendadmin");
            
            if(bookName == null)
                bookName = "";
            if(option == null)
                option = "";
            if(dropDown == null)
                dropDown = "";
            if(bookName.length() > 0 && option.length() > 0 && dropDown.length() == 0)
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MiniProject/AdminOperations");
                dispatcher.forward(request, response);
            }
            else
            {
                out.print("<p><b><center>Please enter the following details: <br>");
                if(bookName.length() == 0)
                {
                    out.print("Book name ");
                    if(option.length() == 0)
                    {
                        out.print(", Time frame");
                        if(dropDown.length() == 0)
                            out.print(" and ");
                    }
                        
                }
                if(option.length() == 0 && bookName.length() != 0)
                {
                    out.print("Time frame");
                    if(dropDown.length() == 0)
                        out.print(" and ");
                }
                if(dropDown.length() == 0)
                    out.print("Recommendation");    
                
                out.print("</center></b></p>");
            }
            
        %>
        
        <form method="post">
            
            <table>
                <tr>
                    <td>Book Name: </td><td><input type="text" name="booknameadmin" placeholder="Enter a book name"></td>
                </tr>
                <tr>
                    <td>Brand new: </td><td><input type="radio" name="radiobtnadmin" value="True">< 1 yr</td><td><input type="radio" name="radiobtnadmin" value="False"> >= 1 yr</td>
                </tr>
                <tr>
                    <td>Would you recommend this book? </td>
                    <td>
                        <select name="recommendadmin">
                                <option value="">Please Select an option</option>
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                                <option value="Unsure">Unsure</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="update" value="Update Book" onclick="form.action='AdminOperations';"></td><td><input type="submit" name="delete" value="Delete Book" onclick="form.action='AdminOperations';"></td>
                    <td><input type="submit" name="addadmin" value="Add Book" onclick="form.action='AdminOperations';"></td>
                </tr>
            </table>
            
            <br>
            <input type="submit" value="Go back" onclick="form.action='Welcome.jsp';">
        </form>     
        
        <h3><center>Here are the records contained in the database</center></h3>
        
        <%-- <jsp:useBean id="bookList" class="essentials.BookListEJBLocal" 
                     scope="session" /> <%--Refer to interface for EJB, not the actual EJB class --%>
        
        <%--<jsp:useBean id="bookList" class="essentials.BookListBean" 
                     scope="session" /> --%>
        
        <%
            
            String dbDriver = "org.apache.derby.jdbc.ClientDriver";
            String dbUrl = "jdbc:derby://localhost:1527/Practice";
            String dbTable = "PRACTICE.BOOKS";
            String dbBookName = "Bookname";
            String dbOneYear = "Oneyear";
            String dbRecommend = "recommend";
            String user = "practice";
            String password = "practice";
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, user, password);
            PreparedStatement stmt = conn.prepareStatement("select * from " + dbTable);
            bookNames = new ArrayList();
            releases = new ArrayList();
            recommendations = new ArrayList();
            
            try
            {
                synchronized(this)
                {
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next())
                    {
                        bookNames.add(rs.getString(dbBookName));
                        releases.add(rs.getBoolean(dbOneYear));
                        recommendations.add(rs.getString(dbRecommend));
                    }
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //ArrayList bookNames = bookList.getBookNames();
            //ArrayList releases = bookList.getReleases();
            //ArrayList recommendations = bookList.getRecommendations();
            
            out.print("<table>");
            out.print("<tr>");
            out.print("<td>Book Name: </td>");
            out.print("<td>Less than a year old: </td>");
            out.print("<td>Recommend: </td>");
            out.print("</tr>");
            
            for(int i = 0; i < bookNames.size(); i++)
            {
                out.print("<tr>");
                out.print("<td>");
                out.print("" + bookNames.get(i));
                out.print("</td>");
                out.print("<td>");
                out.print("" + releases.get(i));
                out.print("</td>");
                out.print("<td>");
                out.print("" + recommendations.get(i));
                out.print("</td>");
                out.print("</tr>");
            }
            
            out.print("</table>");
        %>    
    </body>      
</html>        
    

