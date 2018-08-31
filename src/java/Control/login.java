package Control;

import Business.COD;
import Business.Requisition;
import Business.Supplier;
import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Business.User;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class login extends HttpServlet {

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

        User user = new User();
        COD cod = new COD();
        Supplier supplier = new Supplier();

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "";
        String url = "";
        String query = "select * from users where username = ? and password = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = datasource.getConnection();

            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            } else {
                url = "/login.jsp";

                String errorMessage = "A user with the username or password entered does not exist";

                request.setAttribute("errorMessage", errorMessage);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            switch (user.getRole()) {
                case "DCPO":
                    url = "/tender";
                    break;
                case "Supplier":
                    query = "select * from supplier where username = ?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, username);
                    rs = ps.executeQuery();
                    while (rs.next()) {

                        supplier.setCompanyName(rs.getString("companyname"));
                        supplier.setBusinessPermit(rs.getString("businesspermit"));
                        supplier.setKraCertificate(rs.getString("kracertificate"));
                        supplier.setTaxCompliance(rs.getString("taxcompliance"));
                    }

                    session.setAttribute("supplier", supplier);
                    url = "/tender";
                    break;
                case "COD":
                    query = "select * from cod where username = ?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, username);
                    rs = ps.executeQuery();
                    while (rs.next()) {

                        cod.setFaculty(rs.getString("faculty"));
                        cod.setDepartment(rs.getString("department"));
                    }
                    cod.setName(user.getName());
                    cod.setUsername(user.getUsername());
                    cod.setPassword(user.getPassword());
                    cod.setRole(user.getRole());
                    session.setAttribute("cod", cod);
                    url = "/CODHomepage.jsp";
                    break;
            }

            request.setAttribute("message", message);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
