package Control;

import Business.Items;
import Business.Requisition;
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

public class tenderCreation extends HttpServlet {

    @Resource(name = "jdbc/Procurement")
    private DataSource datasource;

    ArrayList<Items> items = new ArrayList<>();

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

        String reqId = request.getParameter("reqId");

        Requisition req = new Requisition();

        String message = "";
        String url = "";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String createTenderMsg;

        String query = "select faculty,department from requisition where id = ?";

        try {
            connection = datasource.getConnection();

            ps = connection.prepareStatement(query);
            ps.setString(1, reqId);

            rs = ps.executeQuery();

            while (rs.next()) {
                req.setId(reqId);
                req.setFaculty(rs.getString("faculty"));
                req.setDepartment(rs.getString("department"));
            }

            query = "select item,description,quantity from items where reqid = ?";

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
            session.setAttribute("req", req);

            url = "/tenderCreation.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            out.print("Connection to the database failed. please try again");
        } finally {

            out.close();

        }
    }

}
