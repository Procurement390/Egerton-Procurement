package Control;

import Business.Items;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddItem extends HttpServlet {

    ArrayList<Items> item = new ArrayList<>();
    
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

        HttpSession session = request.getSession();
        Items items = new Items();

        items.setItem(request.getParameter("item"));
        items.setDescription(request.getParameter("description"));
        items.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        item.add(items);
        
        session.setAttribute("items", item);
        
        String message = request.getParameter("item") + " added to requisition list";
        
        request.setAttribute("message", message);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createRequisition.jsp");
        dispatcher.forward(request, response);
    }
}
