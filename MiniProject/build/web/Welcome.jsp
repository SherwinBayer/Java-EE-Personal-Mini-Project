<%-- 
    Document   : Welcome
    Created on : 31/08/2016, 1:21:01 PM
    Author     : Sherwin
--%>

<%@page import="essentials.BookListBean"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="essentials.Operations"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Properties"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="essentials.BookListEJBLocal"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Repository</title>
    </head>
    <body>
        <h1><center>Book Repository</center></h1>
        <%! private boolean validAdmin; %>
        <%! private ArrayList bookNames; %>
        <%! private ArrayList releases; %>
        <%! private ArrayList recommendations; %>
        <%-- <%! @EJB private BookListEJBLocal books; %> <%--With dependency injection, refer to the interface, not the actual EJB class --%>
        <%-- <%! private BookListEJBLocal booksss; %> --%>
        <%! private BookListBean books; %>
        
        <jsp:useBean id="admin" class="essentials.AdminBean" scope="session" />
        
        <%
            validAdmin = admin.isValidAdmin();
            if(validAdmin)
            {
                out.print("<p><b>You are logged in as an admin of this multi tier system. </b><p>");
                //out.print("<button onclick=dispatch()>Logout</button>");
                out.print("<form action=Logout method= post >"); // <form action = ... > does seem to work though
                //out.print("<input type=submit value=Logout onclick=form.action='Logout';/>"); Why does onclick not work here?
                out.print("<input type=submit value= Logout />");
                out.print("</form>");
                out.print("<br>");
                out.print("<form action=WelcomeAdmin.jsp method=post >");
                out.print("<input type=submit value= Admin Page />");
                out.print("</form>");
                out.print("<h4>Here you can search or add books to our database</h4>");
                out.print("<h5>Have a look</h5>");
            }
            else
            {
                out.print("<h4>Here you can search or add books to our database</h4>");
                out.print("<h5>Have a look</h5>");
                out.print("<h5>Or additionally, login for administrative control</h5>");
        
                out.print("<form action=AdminLogin.jsp method=post >");
                out.print("<input type=submit value=Login />");
                out.print("</form>");
            }
        %>
        <%-- Why does this give me a 404 error when uncommented?
        <script>
            
                function dispatch()
                {
                    <%
                    RequestDispatcher dispatcherLogout = getServletContext().getRequestDispatcher("/MiniProject/Logout");
                    dispatcherLogout.forward(request,response);
                    %>
                }    
               
        </script>
        --%>
        <%--<h4>Here you can search or add books to our database</h4>
        <h5>Have a look</h5>
        <h5>Or additionally, login for administrative control</h5>
        
        <form method="post">
            <input type="submit" value="Login" onclick="form.action='AdminLogin.jsp';"/>
        </form>
        --%>    
        
        <%-- Perform searching through entire books database here? --%>
        <%
            //Properties properties = new Properties();
            //properties.loadFromXML(getClass().getResourceAsStream("OperationsServletConfig.xml")); //Why does this give me a null pointer exception?
            /*String dbDriver = properties.get("dbDriver").toString();
            String dbUrl = properties.get("dbUrl").toString();
            String dbTable = properties.get("dbTable").toString();
            String dbBookName = properties.get("dbBookName").toString();
            String dbOneYear = properties.get("dbOneYear").toString();
            String dbRecommend = properties.get("dbRecommend").toString();
            String user = properties.get("user").toString();
            String password = properties.get("password").toString();
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, user, password);
            PreparedStatement stmt = conn.prepareStatement("select * from " + dbTable);
            bookNames = new ArrayList();
            releases = new ArrayList();
            recommendations = new ArrayList();*/
            /*
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
            books = new BookListBean();
            try
            {
                //Context namingContext = new InitialContext();
                //booksss = (BookListEJBLocal) namingContext.lookup(BookListEJBLocal.class.getName());
                synchronized(this)
                {
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next())
                    {
                        bookNames.add(rs.getString(dbBookName));
                        releases.add(rs.getBoolean(dbOneYear));
                        recommendations.add(rs.getString(dbRecommend));
                    }
                    books.setBookNames(bookNames);
                    books.setReleases(releases);
                    books.setRecommendations(recommendations);
                    //booksss.setBookNames(bookNames); 
                    //booksss.setReleases(releases);
                    //booksss.setRecommendations(recommendations);
                    //books.setBookNames(bookNames); // Null pointer exception when using EJB (Dependency Injection)? Why?
                    //books.setReleases(releases);
                    //books.setRecommendations(recommendations);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
            }
            //catch(NamingException e)
            //{
                //Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, e);
            //}
            
            session.setAttribute("bookList", books);*/
        %>    
        
        <%
            String bookName = request.getParameter("bookname");
            String option = request.getParameter("radiobtn");
            String dropDown = request.getParameter("recommend");
            
            if(bookName == null)
                bookName = "";
            if(option == null)
                option = "";
            if(dropDown == null)
                dropDown = "";
            
            if(request.getParameter("search") != null)
            {
                if(bookName.length() > 0 && option.length() > 0)
                {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MiniProject/Operations");
                    dispatcher.forward(request, response);
                }
                else
                {
                    out.print("<p><b><center>Please enter the following details: <br>");
                    if(bookName.length() == 0)
                    {
                        out.print("Book Name ");
                        if(option.length() == 0)
                        {
                            out.print("and  ");
                        }
                    }
                    if(option.length() == 0)
                        out.print("Time Frame");
                    
                    out.print("</center></b></p>");
                }
            }
            else if(request.getParameter("add") != null)
            {
                if(bookName.length() > 0 && option.length() > 0 && dropDown.length() > 0)
                {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MiniProject/OperationsAdd");
                    dispatcher.forward(request, response);
                }
                else
                {
                    out.print("<p><b><center>Please enter the following details: <br>");
                    if(bookName.length() == 0)
                    {
                        out.print("Book Name ");
                        if(option.length() == 0)
                        {
                            out.print(", Time Frame ");
                        }
                        if(dropDown.length() == 0)
                            out.print("and ");

                    }
                    if(option.length() == 0 && bookName.length() != 0)
                    {
                        out.print("Time Frame");
                        if(dropDown.length() == 0)
                            out.print(" and ");
                    }
                    if(dropDown.length() == 0)
                        out.print("Recommendation");

                    out.print("</center></b></p>");
                }
            }
            else
            {
                out.print("<p><b><center>Please enter the following details: <br>");
                if(bookName.length() == 0)
                {
                    out.print("Book Name ");
                    if(option.length() == 0)
                    {
                        out.print(", Time Frame ");
                    }
                    if(dropDown.length() == 0)
                        out.print("and ");
                        
                }
                if(option.length() == 0 && bookName.length() != 0)
                {
                    out.print("Time Frame");
                    if(dropDown.length() == 0)
                        out.print(" and ");
                }
                if(dropDown.length() == 0)
                    out.print("Recommendation");
                
                out.print("</center></b></p>");
            }
                    
        %>            
        
        <form method="get">
            <table>
                <tr>
                    <td>Book Name: </td><td><input type ="text" name="bookname" placeholder="Enter a book name"/></td>
                </tr>
                <tr>
                    <td>Brand new: </td><td><input type="radio" name="radiobtn" value="True"> < 1 yr</td><td><input type="radio" name="radiobtn" value="False"> >= 1 yr</td>
                    <%--<td>
                        <select name="test">
                                <option value="">Please Select a Year</option>
                                <option value="2016">2016</option>
                                <option value="2015">2015</option>
                                <option value="2014">2014</option>
                        </select>
                    </td> --%>
                                
                </tr>
                <tr>
                    <td>Would you recommend this book? </td>
                    <td>
                        <select name="recommend">
                                <option value="">Please Select an option</option>
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                                <option value="Unsure">Unsure</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="search" value="Search" onclick="form.action='Operations';"/></td>
                    <td><input type="submit" name="add" value="Add" onclick="form.action='OperationsAdd';"/></td>
                    <td><input type="submit" value="Test" onclick="form.action='TestServlet';"></td>
                </tr>
                
            </table>
        </form>
        
    </body>
</html>
