package app.servlet;

import app.entities.User;
import app.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.get(login);

        if (login == null || password == null|| login.isEmpty()|| password.isEmpty() || user == null) {
            req.setAttribute("error","введите пароль");

            req.getRequestDispatcher("/WEB-INF/add.jsp").forward(req, resp);
        }

        if (user != null && user.getPassword().equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("error","login error");
            req.getRequestDispatcher("/WEB-INF/add.jsp").forward(req, resp);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
}
