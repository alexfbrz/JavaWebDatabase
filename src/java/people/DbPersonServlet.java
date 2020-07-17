/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Statement;

/**
 *
 * @author alexf
 */
@WebServlet(name = "DbPersonServlet", urlPatterns = {"/DbPersonServlet"})
public class DbPersonServlet extends HttpServlet {


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
       
        ServletContext servletContext = getServletContext();
        ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");
        
        Connection connection;
        Statement statement;
        String errorMessage = "";
        
        try
        {
            connection = connectionPool.getConnection();
            statement = (Statement) connection.createStatement();
            
            Statement stmt = connectionPool.getConnection().createStatement();        
            String sql = "CREATE TABLE PERSONCOLLECTION(name varchar(10), eyeColor varchar(10), hairColor varchar(10), height varchar(10), weight varchar(10), id INT NOT NULL)"; // GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1))";
            //String sql = "DROP TABLE PERSONCOLLECTION";
            stmt.execute(sql);
           
            if(statement != null)
            {
                errorMessage = DbPersonCollection.update(statement, request);
                //statement.close();
            }
            if(connection != null)
            {
                connectionPool.returnConnection(connection);
            }
        }
        catch(SQLException e)
        {
            errorMessage = e.toString();
        }
        
        request.setAttribute("errorMessage", errorMessage);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DbPersonCollection.jsp");
        dispatcher.forward(request, response);
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

}
