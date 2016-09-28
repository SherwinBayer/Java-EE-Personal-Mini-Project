package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import essentials.BookListBean;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.Context;
import essentials.Operations;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.ejb.EJB;
import essentials.BookListEJBLocal;
import java.util.ArrayList;

public final class Welcome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 private boolean validAdmin; 
 private ArrayList bookNames; 
 private ArrayList releases; 
 private ArrayList recommendations; 
 private BookListBean books; 
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Book Repository</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1><center>Book Repository</center></h1>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        \n");
      out.write("        ");
      essentials.AdminBean admin = null;
      synchronized (session) {
        admin = (essentials.AdminBean) _jspx_page_context.getAttribute("admin", PageContext.SESSION_SCOPE);
        if (admin == null){
          admin = new essentials.AdminBean();
          _jspx_page_context.setAttribute("admin", admin, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\n");
      out.write("        \n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("    \n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("        ");

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
        
      out.write("    \n");
      out.write("        \n");
      out.write("        ");

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
                    
        
      out.write("            \n");
      out.write("        \n");
      out.write("        <form method=\"get\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Book Name: </td><td><input type =\"text\" name=\"bookname\" placeholder=\"Enter a book name\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Brand new: </td><td><input type=\"radio\" name=\"radiobtn\" value=\"True\"> < 1 yr</td><td><input type=\"radio\" name=\"radiobtn\" value=\"False\"> >= 1 yr</td>\n");
      out.write("                    ");
      out.write("\n");
      out.write("                                \n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Would you recommend this book? </td>\n");
      out.write("                    <td>\n");
      out.write("                        <select name=\"recommend\">\n");
      out.write("                                <option value=\"\">Please Select an option</option>\n");
      out.write("                                <option value=\"Yes\">Yes</option>\n");
      out.write("                                <option value=\"No\">No</option>\n");
      out.write("                                <option value=\"Unsure\">Unsure</option>\n");
      out.write("                        </select>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td><input type=\"submit\" name=\"search\" value=\"Search\" onclick=\"form.action='Operations';\"/></td>\n");
      out.write("                    <td><input type=\"submit\" name=\"add\" value=\"Add\" onclick=\"form.action='OperationsAdd';\"/></td>\n");
      out.write("                    <td><input type=\"submit\" value=\"Test\" onclick=\"form.action='TestServlet';\"></td>\n");
      out.write("                </tr>\n");
      out.write("                \n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
