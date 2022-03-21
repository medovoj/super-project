package app.servlet;

import app.entities.Book;
import app.entities.Cart;
import app.entities.CartItem;
import app.entities.User;
import app.service.BookService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list")
@NoArgsConstructor
public class ListServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init(ServletConfig config)  {
        bookService = (BookService) config.getServletContext().getAttribute("bookService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
