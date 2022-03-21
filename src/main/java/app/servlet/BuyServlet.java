package app.servlet;

import app.entities.Book;
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
import java.util.UUID;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();

        String item = req.getParameter("book");

        int number = 0;
        try {
            number = Integer.parseInt(req.getParameter("number"));
        } catch (NumberFormatException e) {
            printWriter.println("wrong   count");
        }

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user != null) {
            CartItem cartItem = new CartItem();
            cartItem.setId(UUID.randomUUID().toString());
            cartItem.setItem(item);
            cartItem.setNumber(number);

            if (cart == null){
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            cart.add(cartItem);
            resp.sendRedirect("/list");

        }
    }}
