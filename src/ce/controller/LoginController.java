package ce.controller;

import ce.controller.auth.CookiesHandler;
import ce.model.LoggedInUser;
import ce.view.FacesContextValue;
import ce.view.Login;
import ce.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@Stateless
@SessionScoped
public class LoginController implements Serializable{

	@EJB
	UserController userController;

	@EJB
	CookiesHandler cookiesHandler;

	@EJB
	FacesContextValue fcb;

	@EJB
	LoggedInUser loggedInUser;

	private UIComponent loginField;

	private UIComponent passwordField;

    public boolean isLoginCorrect(Login login){
        User user = userController.getUser(login.getLogin());
        javax.faces.context.FacesContext fc = fcb.getInstance();
        if (user == null) {
            fc.addMessage(loginField.getClientId(fc), new FacesMessage("Login is wrong"));
            return false;
        }
        boolean result = (user.isPasswordCorrect(login.getPassword()));
        if (!result) fc.addMessage(passwordField.getClientId(fc), new FacesMessage("Password is wrong"));
        return result;
    }

	public void logout() { //TODO: get rid of ignored exception
        javax.faces.context.FacesContext fc = fcb.getInstance();
		loggedInUser.setUser(null);
        try {
			cookiesHandler.dropCookies();
            fc.getExternalContext().redirect("/login.xhtml");
        } catch (IOException ignored) {}
    }

	public void login(Login login){ //TODO: get rid of ignored exception
        javax.faces.context.FacesContext fc = fcb.getInstance();
        if (isLoginCorrect(login)){
            String currentLogin = login.getLogin();
			User currentUser = userController.getUser(currentLogin);
			loggedInUser.setUser(currentUser);
            if(login.isRemember()){
				cookiesHandler.addCookies(login.getLogin(), login.getPassword());
            }
            try {
                fc.getExternalContext().redirect("/ce/home.xhtml");
            } catch (IOException ignored) {}
        }
    }

	public String getCurrentLogin() {
        return loggedInUser.getLogin();
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

	//TODO refactor here
	public String tryAutoLogin(){
		Login login = cookiesHandler.getCookiesLogin();
		if (login != null) login(login);
		return " ";
    }
}
