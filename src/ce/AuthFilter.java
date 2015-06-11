package ce;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO: get rid of LOGIN_URL, use relative linking
//TODO: button logout stopped working
@WebFilter(filterName = "AuthFilter", urlPatterns = "/ce/*")
public class AuthFilter implements Filter {
    public void destroy() {}

    public static final String LOGIN_URL = "http://localhost:8080/login.xhtml";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        LoginController lc;
        try {
            lc = (LoginController) request.getSession().getAttribute("loginController");
            if (lc.getCurrentUser() == null) response.sendRedirect(LOGIN_URL);
        } catch (NullPointerException e) {
            response.sendRedirect(LOGIN_URL);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
