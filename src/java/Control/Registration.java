package Control;

import Business.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Registration extends HttpServlet {

    @Resource(name="jdbc/Procurement")
     private DataSource datasource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        //set content type
        response.setContentType("text/html");
        
        //print writer
        PrintWriter out = response.getWriter();
        
        Supplier supplier = new Supplier();
        
        supplier.setName(request.getParameter("name"));
        supplier.setUsername(request.getParameter("username"));
        supplier.setPassword(request.getParameter("password"));
        supplier.setRole(request.getParameter("role"));
        
        String message = "";
        String url = "";
     
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "select * from users where username = ?";
        
        try
        {
            connection = datasource.getConnection();
            
                       
            ps = connection.prepareStatement(query);
            ps.setString(1, supplier.getUsername());
                    
            rs = ps.executeQuery();
            
            String duplicate = null;
            
            while(rs.next())
            {
                duplicate = rs.getString(1);
            }
            
            if(duplicate == null)
            {
               
                         
                        query = "insert into users values(?,?,?,?)";
                    
                        ps = connection.prepareStatement(query);
                        ps.setString(1, supplier.getName());
                        ps.setString(2, supplier.getUsername());
                        ps.setString(3, supplier.getPassword());
                        ps.setString(4, supplier.getRole());
                    
                        if(ps.executeUpdate()>0)
                            {
                                url = "/login.jsp";
                                message = "You have successfully registered";
                            }
                        else
                            {
                                url = "/registration.jsp";
                                message = "Your registration failed. Please try again";
                            }
                
                   
            }
            else
            {
                url = "/registration.jsp";
                message = "The username exists";
            }
                    
            request.setAttribute("message", message);
        
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
                
           
            
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            out.print("Connection to the database failed. please try again");
        }
        finally
        {
            
            out.close();
            try {
                connection.close();
                ps.close();
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
       
    }
 
}
