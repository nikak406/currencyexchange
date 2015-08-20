package ce.controller.auth;

import ce.view.Login;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO BUG testing required, probably functional is doing wrong
@Stateless
public class CookiesHandler {

	public static final int ZERO = 0;
	public static final int MONTH = 60*60*24*30;

	public void addCookies(String login, String password){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		Cookie loginCookie = new Cookie("login", login);
		loginCookie.setMaxAge(MONTH);
		response.addCookie(loginCookie);
		Cookie passwordCookie = new Cookie("password", password);
		passwordCookie.setMaxAge(MONTH);
		response.addCookie(passwordCookie);
	}

	public Login getCookiesLogin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
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
		FacesContext fc = FacesContext.getCurrentInstance();
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
