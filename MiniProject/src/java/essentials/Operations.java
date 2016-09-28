package essentials;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sherwin
 */
public class Operations extends HttpServlet {
    
    private Connection conn;
    private PreparedStatement stmt;
    private PreparedStatement stmtTwo;
    private String dbTable;
    private String dbBookName;
    private String dbOneYear;
    private String dbRecommend;
    //@EJB private BookListEJBLocal books; //With dependency injection, refer to the interface, not the actual EJB class
    //Decided not to use EJB for storing a list of all the books within database, as I would have to keep performing an
    //SQL select statement in each servlet which modifies the records in the database itself (which to me seems inefficient)
    //Hence I will perform a single SQL select statement in the WelcomeAdmin.jsp page, obtain the result set, add the records
    //from the result set into a respective arraylist, then simply get the records from the arraylists and print them out
    //using JSP writer object out while using the html <table> tag
    //private ArrayList bookNames;
    //private ArrayList releases;
    //private ArrayList recommendations;
    
    public Operations() throws SQLException, ClassNotFoundException, IOException
    {
        Properties properties = new Properties();
        properties.loadFromXML(getClass().getResourceAsStream("OperationsServletConfig.xml"));
        String dbDriver = properties.get("dbDriver").toString();
        String dbUrl = properties.get("dbUrl").toString();
        dbTable = properties.get("dbTable").toString();
        dbBookName = properties.get("dbBookName").toString();
        dbOneYear = properties.get("dbOneYear").toString();
        dbRecommend = properties.get("dbRecommend").toString();
        String user = properties.get("user").toString();
        String password = properties.get("password").toString();
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbUrl, user, password);
        //WHERE CAST(tablename AS VARCHAR(128)) = 'T1') cannot perform comparisons with long varchars unless you cast with this statement? 
        stmt = conn.prepareStatement("Select * from " + dbTable + " where cast(" + dbBookName + " as varchar(128)) = ? and " + dbOneYear + " = ?");
        stmtTwo = conn.prepareStatement("Select * from " + dbTable);
        //bookNames = new ArrayList();
        //releases = new ArrayList();
        //recommendations = new ArrayList();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Operations</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Operations at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String bookName = request.getParameter("bookname");
        String oneYearOrNot = request.getParameter("radiobtn");
        //String dropDown = request.getParameter("recommend");
        boolean oneYearOrNotBool = false; // Used for the sql statement?
        
        if(oneYearOrNot.equals("True"))
            oneYearOrNotBool = true;
        else
            oneYearOrNotBool = false;
        if(oneYearOrNot.length() == 0 || oneYearOrNot == null || bookName.length() == 0 || bookName == null)// ||
                //dropDown == null || dropDown.length() == 0) // WHY DOES THIS NOT WORK??!!!
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Welcome.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            BookBean book = new BookBean();
            book.setBookName(bookName);
            book.setOneYearOrNot(oneYearOrNot);
            //book.setRecommend(dropDown);
            boolean bookFound;
            //String recommend = null;
            
            try
            {
                synchronized(this)
                {
                    stmt.setString(1, bookName);
                    stmt.setBoolean(2, oneYearOrNotBool);
                    //stmt.setString(3, dropDown);
                    ResultSet rs = stmt.executeQuery();
                    bookFound = rs.next();
                    //recommend = rs.getString(dbRecommend);
                    book.setRecommend(rs.getString(dbRecommend));
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                bookFound = false;
            }
            //book.setRecommend(recommend);
            HttpSession session = request.getSession();
            session.setAttribute("book", book);
            
            /*try
            {
                synchronized(this)
                {
                    ResultSet rs = stmtTwo.executeQuery();
                    while(rs.next())
                    {
                        bookNames.add(rs.getString(dbBookName));
                        releases.add(rs.getBoolean(dbOneYear));
                        recommendations.add(rs.getString(dbRecommend));
                    }
                    books.setBookNames(bookNames);
                    books.setReleases(releases);
                    books.setRecommendations(recommendations);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            session.setAttribute("bookList", books);*/
            
            if(bookFound == true)
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/BookFound.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/BookNotFound.jsp");
                dispatcher.forward(request, response);
            }
            
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @Override
    public void destroy()
    {
        super.destroy();
        try
        { 
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        } catch (SQLException ex) 
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
