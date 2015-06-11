package ce.auth;

import ce.auth.DAO.UserDAO;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@Stateful
@SessionScoped
public class LoginController implements Serializable{

	@EJB
	UserDAO userDAO;

	private UIComponent loginField;

	private UIComponent passwordField;

    private String currentUser=null;

    private List<User> getUsers() {
		return userDAO.getUsers();
    }

    public boolean containsUser(String login){
        return (getUser(login)!=null);
    }

	private User getUser(String login){
        for(User user : getUsers()){
            if (login.equals(user.getLogin())) return user;
        }
        return null;

	}
    public boolean isLoginCorrect(Login login){
        User user = getUser(login.getLogin());
        FacesContext fc = FacesContext.getCurrentInstance();
        if (user==null) {
            fc.addMessage(loginField.getClientId(fc), new FacesMessage("Login is wrong"));
            return false;
        }
        boolean result = (user.isPasswordCorrect(login.getPassword()));
        if (!result) fc.addMessage(passwordField.getClientId(fc), new FacesMessage("Password is wrong"));
        return result;
    }

	public void logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        currentUser = null;
        try {
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    cookie.setMaxAge(1);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("password")) {
                    cookie.setMaxAge(1);
                    response.addCookie(cookie);
                }
            }
            fc.getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

	public void login(Login login){
        FacesContext fc=FacesContext.getCurrentInstance();
        if (isLoginCorrect(login)){
            currentUser=login.getLogin();
            if(login.isRemember()){
                HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
                Cookie loginCookie = new Cookie("login", login.getLogin());
                loginCookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(loginCookie);
                Cookie passwordCookie = new Cookie("password", login.getPassword());
                passwordCookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(passwordCookie);
            }
            try {
                fc.getExternalContext().redirect("home.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public void register(Register register){
        if (containsUser(register.getLogin())) return;
        User user = new User(register.getLogin(), register.getPassword(), register.getName(), register.getEmail());
        userDAO.registerUser(user);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Successfully registered"));
    }

	public String getUserName(String login){
        User user = getUser(login);
        return user.getName();
    }

	public String getUserEmail(String login) {
        User user = getUser(login);
        return user.getEmail();
    }

	public String getCurrentUser() {
        return currentUser;
    }

	public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

	public UIComponent getLoginField() {
        return loginField;
    }

	public void setLoginField(UIComponent loginField) {
        this.loginField = loginField;
    }

	public UIComponent getPasswordField() {
        return passwordField;
    }

	public void setPasswordField(UIComponent passwordField) {
        this.passwordField = passwordField;
    }

	public String tryCookiesLogin(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        //HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        Cookie[] cookies = request.getCookies();
        String login=null;
        String password=null;
        if (cookies!=null) for(Cookie cookie: cookies){
            if (cookie.getName().equals("login")){
                login = cookie.getValue();
            }
            if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
        }
        if ((login!=null)&&(password!=null)){
            Login loginObj  = new Login();
            loginObj.setLogin(login);
            loginObj.setHashPassword(password);
            loginObj.setRemember(false);
            login(loginObj);
        }
        return " ";
    }
}
