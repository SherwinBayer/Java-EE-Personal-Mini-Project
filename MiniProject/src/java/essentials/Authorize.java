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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Authorize extends HttpServlet {
    
    private Connection conn;
    private PreparedStatement stmt;
    
    public Authorize() throws SQLException, ClassNotFoundException, IOException
    {
        Properties prop = new Properties();
        prop.loadFromXML(getClass().getResourceAsStream("AdminsServletConfig.xml"));
        String dbDriver = prop.get("dbDriver").toString();
        String dbUrl = prop.get("dbUrl").toString();
        String dbTable = prop.get("dbTable").toString();
        String dbUserName = prop.get("dbUserName").toString();
        String dbPassWord = prop.get("dbPassWord").toString();
        String user = prop.get("user").toString();
        String password = prop.get("password").toString();
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbUrl, user, password);
        stmt = conn.prepareStatement("select * from " +dbTable+ " where " +dbUserName+ " = ? and " +dbPassWord+ " = ?");
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
            out.println("<title>Servlet Authorize</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authorize at " + request.getContextPath() + "</h1>");
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
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        if(userName.length() == 0 || userName == null || passWord.length() == 0 || passWord == null) // WHY DOES THIS NOT WORK??!!!
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminLogin.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            AdminBean admin = new AdminBean();
            admin.setUserName(userName);
            admin.setPassWord(passWord);
            boolean validAdmin = false;
            
            try
            {
                synchronized(this)
                {
                    stmt.setString(1, userName);
                    stmt.setString(2, passWord);
                    ResultSet rs = stmt.executeQuery();
                    validAdmin = rs.next();
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Authorize.class.getName()).log(Level.SEVERE, null, ex);
            }
            admin.setValidAdmin(validAdmin);
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            if(validAdmin)
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginSuccess.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginFailed.jsp");
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
       // processRequest(request, response);
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

}
