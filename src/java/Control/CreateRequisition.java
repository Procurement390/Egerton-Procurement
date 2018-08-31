package Control;

import Business.COD;
import Business.Items;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
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

public class CreateRequisition extends HttpServlet {

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

        HttpSession session = request.getSession();
        COD cod = (COD) session.getAttribute("cod");

        ArrayList<Items> items = (ArrayList<Items>) session.getAttribute("items");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String number = getRequisitionNumber();
        String url = "";
        String message;

        if (cod.getDepartment().isEmpty() || cod.getFaculty().isEmpty()) {

            message = "You must update your profile before submitting a requisition";
            url = "Profile.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {

            String query = "insert into requisition(id,department,faculty,username,status) values(?,?,?,?,?)";

            try {
                connection = datasource.getConnection();

                ps = connection.prepareStatement(query);
                ps.setString(1, number);
                ps.setString(2, cod.getDepartment());
                ps.setString(3, cod.getFaculty());
                ps.setString(4, cod.getUsername());
                ps.setString(5, "Pending");

                ps.executeUpdate();

                for (int i = 0; i < items.size(); i++) {
                    items.get(i);

                    query = "insert into items(item,description,quantity,reqid) values(?,?,?,?)";

                    ps = connection.prepareStatement(query);
                    ps.setString(1, items.get(i).getItem());
                    ps.setString(2, items.get(i).getDescription());
                    ps.setInt(3, items.get(i).getQuantity());
                    ps.setString(4, number);

                    ps.executeUpdate();
                }

                message = "You have submitted your requisition successfully";

                request.setAttribute("message", message);

                url = "/CODHomepage.jsp";

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                out.print("Connection to the database failed. please try again");
            } finally {

                out.close();
                   try {
                ps.close();
                rs.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllFailedRequisitions.class.getName()).log(Level.SEVERE, null, ex);
            }

            }

        }
    }

    public String getRequisitionNumber() {

        Random rand = new Random();
        String requisitionNo = "EU-";
        char random[] = new char[]{'x', 'y', 'z', 'a', 'b', 'c', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < 7; i++) {
            requisitionNo += random[rand.nextInt(random.length)];
        }

        return requisitionNo.toUpperCase(Locale.US);
    }

}
