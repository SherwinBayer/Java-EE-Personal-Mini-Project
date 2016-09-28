/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essentials;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sherwin
 */
public class AdminOperations extends HttpServlet 
{
    Connection conn;
    PreparedStatement stmt;
    String dbTable;
    String dbBookName;
    String dbOneYear;
    String dbRecommend;
    
    public AdminOperations() throws SQLException, ClassNotFoundException, IOException
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
            out.println("<title>Servlet AdminOperations</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminOperations at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException 
    {
        //processRequest(request, response);
        String bookName = request.getParameter("booknameadmin");
        String oneYearOrNot = request.getParameter("radiobtnadmin");
        String dropDown = request.getParameter("recommendadmin");
        boolean oneYearOrNotBool = false; // Used for the sql statement?
        
        if(oneYearOrNot.equals("True"))
            oneYearOrNotBool = true;
        else
            oneYearOrNotBool = false;
        if(bookName == null || bookName.length() == 0 || oneYearOrNot == null || oneYearOrNot.length() == 0 ||
                dropDown == null || dropDown.length() == 0)
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WelcomeAdmin.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            if(request.getParameter("addadmin") != null)
            {
                try 
                {
                    stmt = conn.prepareStatement("insert into " + dbTable + " values(?,?,?)");
                    synchronized(this)
                    {
                        stmt.setString(1, bookName);
                        stmt.setBoolean(2, oneYearOrNotBool);
                        stmt.setString(3, dropDown);
                        stmt.executeUpdate();
                    }
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(AdminOperations.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(request.getParameter("update") != null)
            {
                try 
                {
                    stmt = conn.prepareStatement("Update " +dbTable+ " set " +dbRecommend+ " = ? where cast(" +dbBookName+ " as varchar(128)) = " + "'"+bookName+"' and " +dbOneYear+ " = " +"'"+oneYearOrNotBool+"'");
                    synchronized(this)
                    {
                        stmt.setString(1, dropDown); // the index no. is NOT the column no. in the database, it's the location of 
                                                     //where u want to store the element in the statement!
                        stmt.executeUpdate();
                    }
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(AdminOperations.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(request.getParameter("delete") != null)
            {
                try 
                {
                    stmt = conn.prepareStatement("delete from " + dbTable + " where cast(" +dbBookName+ " as varchar(128)) = ? and " +dbOneYear+ " = ? and " +dbRecommend+ " = ?");
                    synchronized(this)
                    {
                        stmt.setString(1, bookName);
                        stmt.setBoolean(2, oneYearOrNotBool);
                        stmt.setString(3, dropDown);
                        stmt.executeUpdate();
                    }
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(AdminOperations.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WelcomeAdmin.jsp");
            dispatcher.forward(request, response);
                
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
        //processRequest(request, response);
        doGet(request,response);
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
