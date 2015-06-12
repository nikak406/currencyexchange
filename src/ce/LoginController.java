package ce;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@Stateful
@SessionScoped
public class LoginController implements Serializable{

	public static final String LOGIN_URL = "login.xhtml";
	public static final String HOME_URL = "home.xhtml";

	@EJB
    UserDAO userDAO;

	@EJB
	CookiesController cookiesController;

	private UIComponent loginField;

	private UIComponent passwordField;

    private String currentUser = null;

    private List<User> getUsers() {
		return userDAO.getUsers();
    }

	private FacesContext getFC(){
		return FacesContext.getCurrentInstance();
	}

    public boolean containsUser(String login){
        return (getUser(login) != null);
    }

	private User getUser(String login){
        for(User user : getUsers()){
            if (login.equals(user.getLogin())) return user;
        }
        return null;
	}

    public boolean isLoginCorrect(Login login){
        User user = getUser(login.getLogin());
        FacesContext fc = getFC();
        if (user == null) {
            fc.addMessage(loginField.getClientId(fc), new FacesMessage("Login is wrong"));
            return false;
        }
        boolean result = (user.isPasswordCorrect(login.getPassword()));
        if (!result) fc.addMessage(passwordField.getClientId(fc), new FacesMessage("Password is wrong"));
        return result;
    }

	public void logout() { //TODO: get rid of ignored exception
        FacesContext fc = getFC();
        currentUser = null;
        try {
			cookiesController.dropCookies(fc);
            fc.getExternalContext().redirect(LOGIN_URL);
        } catch (IOException ignored) {}
    }

	public void login(Login login){ //TODO: get rid of ignored exception
        FacesContext fc = getFC();
        if (isLoginCorrect(login)){
            currentUser = login.getLogin();
            if(login.isRemember()){
				cookiesController.addCookies(fc, login.getLogin(), login.getPassword());
            }
            try {
                fc.getExternalContext().redirect(HOME_URL);
            } catch (IOException ignored) {}
        }
    }

	public void register(Register register){ //TODO add check of existing login
        if (containsUser(register.getLogin())) return;
        User user = new User(register.getLogin(), register.getPassword(), register.getName(),
				register.getEmail(), register.getLocation(), register.getRoom(), register.getPhoneNumber());
        userDAO.registerUser(user);
        FacesContext fc = getFC();
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

	public String tryAutoLogin(){
        FacesContext fc = getFC();
		Login login = cookiesController.getCookiesLogin(fc);
		if (login != null) login(login);
		return " ";
    }
}
