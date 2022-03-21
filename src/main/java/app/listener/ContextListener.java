package app.listener;

import app.dao.BookDao;
import app.dao.UserDao;
import app.service.BookService;
import app.service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext sc = event.getServletContext();
        sc.setAttribute("bookService", new BookService(new BookDao()));
        sc.setAttribute("userService", new UserService(new UserDao()));


//        try {
//            sc.setAttribute("roles", initRoles());
//        } catch (ParserConfigurationException | IOException | SAXException e) {
//            LOG.error("Cannot set roles to servlet context" + e);
//        }

    }

}
