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
import java.sql.SQLException;
import java.sql.Statement;
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
public class OperationsAdd extends HttpServlet {
    
    private Connection conn;
    private PreparedStatement stmt;
    private String dbTable;
    //private String dbDriver;
    //private String dbUrl;
    //private String user;
    //private String password;
    public OperationsAdd() throws SQLException, ClassNotFoundException, IOException
    {
        Properties properties = new Properties();
        properties.loadFromXML(getClass().getResourceAsStream("OperationsServletConfig.xml"));
        String dbDriver = properties.get("dbDriver").toString();
        String dbUrl = properties.get("dbUrl").toString();
        String user = properties.get("user").toString();
        String password = properties.get("password").toString();
        dbTable = properties.get("dbTable").toString();
        String dbBookName = properties.get("dbBookName").toString();
        String dbOneYear = properties.get("dbOneYear").toString();
        String dbRecommend = properties.get("dbRecommend").toString();
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbUrl, user, password);
        stmt = conn.prepareStatement("insert into " + dbTable + " values(?,?,?)");
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
            out.println("<title>Servlet OperationsAdd</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OperationsAdd at " + request.getContextPath() + "</h1>");
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
        String dropDown = request.getParameter("recommend");
        boolean oneYearOrNotBool = false;
        
        if(oneYearOrNot.equals("True"))
            oneYearOrNotBool = true;
        else
            oneYearOrNotBool = false;
        
        if((bookName == null) || (bookName.length() == 0) || (oneYearOrNot == null) || (oneYearOrNot.length() == 0) ||
                dropDown == null || dropDown.length() == 0) // WHY DOES THIS NOT WORK??!!!
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Welcome.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            try
            {
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
                Logger.getLogger(OperationsAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Welcome.jsp"); // Gives me a 404 error???
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Test.jsp");
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
        doGet(request, response);
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
