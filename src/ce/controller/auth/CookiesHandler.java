package ce.controller.auth;

import ce.view.FacesContextValue;
import ce.view.Login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO testing required, probably functional is doing wrong
@Stateless
public class CookiesHandler {

	@EJB
	FacesContextValue fcb;

	public static final int ZERO = 0;
	public static final int MONTH = 60*60*24*30;

	public void addCookies(String login, String password){
		javax.faces.context.FacesContext fc = fcb.getInstance();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		Cookie loginCookie = new Cookie("login", login);
		loginCookie.setMaxAge(MONTH);
		response.addCookie(loginCookie);
		Cookie passwordCookie = new Cookie("password", password);
		passwordCookie.setMaxAge(MONTH);
		response.addCookie(passwordCookie);
	}

	public Login getCookiesLogin(){
		javax.faces.context.FacesContext fc = fcb.getInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		//HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		Cookie[] cookies = request.getCookies();
		String login = null;
		String password = null;
		if (cookies != null) for(Cookie cookie: cookies){
			if (cookie.getName().equals("login")){
				login = cookie.getValue();
			}
			if (cookie.getName().equals("password")){
				password = cookie.getValue();
			}
		}
		if ((login != null) && (password != null)){
			Login loginObj  = new Login();
			loginObj.setLogin(login);
			loginObj.setHashPassword(password);
			loginObj.setRemember(false);
			return loginObj;
		}
		return null;
	}

	public void dropCookies(){
		javax.faces.context.FacesContext fc = fcb.getInstance();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("login")) {
				cookie.setMaxAge(ZERO);
				response.addCookie(cookie);
			}
			if (cookie.getName().equals("password")) {
				cookie.setMaxAge(ZERO);
				response.addCookie(cookie);
			}
		}
	}
}
