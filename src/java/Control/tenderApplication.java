package Control;

import Business.Items;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class tenderApplication extends HttpServlet {

    @Resource(name = "jdbc/Procurement")
    private DataSource datasource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        int count = (Integer) Integer.parseInt(request.getParameter("count"));

        HttpSession session = request.getSession();

        session.setAttribute("count", count);

        String reqId = request.getParameter("reqId");
        

        ArrayList<Items> items = new ArrayList<>();

        String message = "";
        String url = "";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "select * from items where reqid = ?";

        try {
            connection = datasource.getConnection();

            ps = connection.prepareStatement(query);
            ps.setString(1, reqId);

            rs = ps.executeQuery();

            while (rs.next()) {
                
                Items item = new Items();
                item.setItem(rs.getString("item"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getInt("quantity"));
                
                 items.add(item);
            }

           

            session.setAttribute("items", items);
            session.setAttribute("reqId", reqId);

            request.setAttribute("message", message);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tenderApplication.jsp");

            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            out.print("Connection to the database failed. please try again");
        } finally {

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


