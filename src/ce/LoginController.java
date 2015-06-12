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

@ManagedBean
@Stateful
@SessionScoped
public class LoginController implements Serializable{

	public static final String LOGIN_URL = "login.xhtml";
	public static final String HOME_URL = "home.xhtml";

	@EJB
	UserController userController;

	@EJB
	CookiesController cookiesController;

	@EJB
	FacesContextBean fcb;

	private UIComponent loginField;

	private UIComponent passwordField;

    private String currentUser = null;

    public boolean isLoginCorrect(Login login){
        User user = userController.getUser(login.getLogin());
        FacesContext fc = fcb.getFC();
        if (user == null) {
            fc.addMessage(loginField.getClientId(fc), new FacesMessage("Login is wrong"));
            return false;
        }
        boolean result = (user.isPasswordCorrect(login.getPassword()));
        if (!result) fc.addMessage(passwordField.getClientId(fc), new FacesMessage("Password is wrong"));
        return result;
    }

	public void logout() { //TODO: get rid of ignored exception
        FacesContext fc = fcb.getFC();
        currentUser = null;
        try {
			cookiesController.dropCookies();
            fc.getExternalContext().redirect(LOGIN_URL);
        } catch (IOException ignored) {}
    }

	public void login(Login login){ //TODO: get rid of ignored exception
        FacesContext fc = fcb.getFC();
        if (isLoginCorrect(login)){
            currentUser = login.getLogin();
            if(login.isRemember()){
				cookiesController.addCookies(login.getLogin(), login.getPassword());
            }
            try {
                fc.getExternalContext().redirect(HOME_URL);
            } catch (IOException ignored) {}
        }
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
		Login login = cookiesController.getCookiesLogin();
		if (login != null) login(login);
		return " ";
    }
}
