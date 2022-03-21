package app.servlet;

import app.entities.Cart;
import app.entities.CartItem;
import app.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (user == null) {
            out.println ("you are not login");
            return;
        }

        out.println (user.getLogin () + "  Cart ");



        if (cart != null) {
            for (CartItem cartItem : cart.getAllItems()) {
                out.println ("Book:    " + cartItem.getItem () + "      Count:     " + cartItem.getNumber ());
            }
        } else {
            out.println ("cart is empty");
        }

    }
}
