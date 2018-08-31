package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author andy
 */
public class CreateTender extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/Procurement")
    private DataSource datasource;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        Requisition req = (Requisition)session.getAttribute("req");

        Items item = new Items();

        String message = "";
        String url = "";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String createTenderMsg;

        // for inserting into tender table
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

        Date closingdate = null;

        try {
            closingdate = formater.parse(request.getParameter("closingdate"));
        } catch (ParseException ex) {
            Logger.getLogger(CreateTender.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            String query = "select * from items where reqid = ?";
            connection = datasource.getConnection();

            ps = connection.prepareStatement(query);

            ps.setString(1, req.getId());

            rs = ps.executeQuery();

            while (rs.next()) {
                item.setDescription(rs.getString("description"));
            }

            query = "insert into tenders values(?,?,?,?,?)";

            ArrayList<Tender> tenders = new ArrayList<>();
            connection = datasource.getConnection();

            ps = connection.prepareStatement(query);
            ps.setString(1, req.getId());
            ps.setString(2, item.getDescription());
            ps.setDate(3, new java.sql.Date(closingdate.getTime()));
            ps.setString(4, "00:00:00");
            ps.setString(5, "active");

            if (ps.executeUpdate() > 0) {
                createTenderMsg = "tender created successfuly";
                url = "/tender";
            } else {
                createTenderMsg = "Tender creation failed";
                url = "/tenderCreation.jsp";
            }

            request.setAttribute("tenderMessage", createTenderMsg);
            request.setAttribute("message", message);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(CreateTender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
