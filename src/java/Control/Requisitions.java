package Control;

import Business.Requisition;
import Business.Supplier;
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

public class Requisitions extends HttpServlet {

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

        //set content type
        response.setContentType("text/html");

        //print writer
        PrintWriter out = response.getWriter();

        ArrayList<Requisition> req = new ArrayList<>();

        String message = "";
        String url = "";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "select * from requisition where status = ?";

        try {
            connection = datasource.getConnection();

            ps = connection.prepareStatement(query);
            ps.setString(1, "Pending");

            rs = ps.executeQuery();

            while (rs.next()) {
                Requisition requisition = new Requisition();

                requisition.setId(rs.getString("id"));
                requisition.setUsername(rs.getString("username"));
                requisition.setFaculty(rs.getString("faculty"));
                requisition.setDepartment(rs.getString("department"));

                req.add(requisition);
            }

            HttpSession session = request.getSession();
            session.setAttribute("requisition", req);

            request.setAttribute("message", message);

            url = "/newRequisitions.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
