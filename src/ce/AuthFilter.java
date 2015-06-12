package ce;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO: doesn't work correctly after setting /* url pattern (NPE)
@WebFilter(filterName = "AuthFilter", urlPatterns = "/nothing.xhtml")
public class AuthFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        LoginController lc = (LoginController) request.getSession().getAttribute("loginController");
        //String path = request.getRequestURI();
        if (lc == null || lc.getCurrentUserLogin() == null) response.sendRedirect(LoginController.LOGIN_URL);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
