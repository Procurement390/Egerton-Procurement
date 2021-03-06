package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class TenderApplicants extends HttpServlet { 
    
    @Resource(name = "jdbc/Procurement")
    private DataSource datasource;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            response.setContentType("text/html; charset=UTF-8");
            
            PrintWriter out = response.getWriter();
   
            
            String tenderNumber = request.getParameter("tender");
            
            ArrayList<Bid> bids = new ArrayList<>();
            
            
            String query = "select ID, tenderNumber,bid.username ,amount, status  from bid inner join users on bid.username = users.username where tenderNumber = ?";
            
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try{
           
               connection = datasource.getConnection();
               
               ps = connection.prepareStatement(query);
               ps.setString(1, tenderNumber);
               
               rs = ps.executeQuery();
               
               while(rs.next()){
                    int amount  = rs.getInt("amount");

                    int Id = rs.getInt("ID");
                    
                    String status = rs.getString("status");
                    
                    String username = rs.getString("username");
                    
                    Bid bid = new Bid(Id, username, amount, status);
                    
                    bids.add(bid);
                  
                    out.println("Tender Number: " + bid.getStatus());
               }
 
                
            } catch (SQLException ex) {
                Logger.getLogger(TenderApplicants.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
            
            }
            
            request.setAttribute("tenderNumber", tenderNumber);
            getServletContext().setAttribute("bids", bids);
            request.getRequestDispatcher("/tenderApplicants.jsp").forward(request, response);
       
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}