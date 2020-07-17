/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class GlobalConnectionPool implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();
        ConnectionPool connectionPool = new ConnectionPool("JH6_wfabrizio",5,1);
        
        connectionPool.getConnection();
        servletContext.setAttribute("connectionPool", connectionPool);
        System.out.println("Set Up Connection");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();
        ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");
        
        if(connectionPool != null)
        {
            connectionPool.close();
        }
    }

    
}
