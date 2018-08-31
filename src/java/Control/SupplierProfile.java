package Control;

import Business.COD;
import Business.Supplier;
import Business.User;
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

public class SupplierProfile extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        Supplier supplier = new Supplier();
        User user = (User) session.getAttribute("user");
        
        
        String message = "";
        String url = "";
     
        String companyname = request.getParameter("companyName");
        String taxcompliance = request.getParameter("tax");
        String kracertificate = request.getParameter("kra");
        String businesspermit = request.getParameter("business");
        
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "select * from users where username = ?";
        
        try
        {
            connection = datasource.getConnection();
            
                       
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
                    
            rs = ps.executeQuery();
            
            String duplicate = null;
            
            while(rs.next())
            {
                 query = "insert into supplier(username,companyname,taxcompliance,kracertificate,businesspermit) values(?,?,?,?,?)";
                    
                        ps = connection.prepareStatement(query);
                        ps.setString(1, user.getUsername());
                        ps.setString(2, companyname);
                        ps.setString(3, taxcompliance);
                        ps.setString(4, kracertificate);
                        ps.setString(5, businesspermit);
                    
                        if(ps.executeUpdate()>0)
                            {
                                supplier.setCompanyName(companyname);
                                supplier.setTaxCompliance(taxcompliance);
                                supplier.setBusinessPermit(businesspermit);
                                supplier.setKraCertificate(kracertificate);
                                session.setAttribute("supplier", supplier);
                                url = "/supplierHomepage.jsp";
                            }
                        else
                            {
                                url = "/profile.jsp";
                            }
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
