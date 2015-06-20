package ce.controller.auth;

import ce.controller.LoginService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//TODO @WebFilter(filterName = "AuthFilter", urlPatterns = "/ce/*")
public class AuthFilter implements Filter {

	public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
    	HttpSession session = request.getSession();
		if (session == null) {
            response.sendRedirect("/login.xhtml");
        } else {
            Object login = session.getAttribute(LoginService.LOGIN);
			if (login == null) response.sendRedirect("/login.xhtml");
		}
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
