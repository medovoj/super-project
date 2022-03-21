package app.filter;

import app.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@WebFilter("/*")
public class SecurityFilter implements Filter {

    private final Map<String, List<String>> roles = new HashMap<>();

    private FilterConfig fConfig;

    public void destroy() {
        fConfig = null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        roles.put("administrator", Arrays.asList("/login", "/main", "/cart", "/admin"));
        roles.put("moderator", Arrays.asList("/login", "/main", "/cart"));
        roles.put("user", Arrays.asList("/login", "/list.jsp", "/list", "/cart", "buy"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            HttpSession session = req.getSession();

            User user = (User) session.getAttribute("user");

            if (user == null || req.getRequestURI().contains("login.jsp")) {
                filterChain.doFilter(servletRequest, servletResponse);

            } else {
                String path = req.getRequestURI();
                String role = user.getRole().getRoleName();

                if (roles.containsKey(role) && roles.get(role).contains(path)) {
                    filterChain.doFilter(servletRequest, servletResponse);

                } else {
                    req.getRequestDispatcher("/WEB-INF/fail.jsp").forward(req, resp);
                }
            }


    }
}
