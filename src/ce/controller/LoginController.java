package ce.controller;

import ce.controller.auth.CookiesHandler;
import ce.model.User;
import ce.view.Login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@Stateless
public class LoginController implements Serializable{

	@EJB
	UserController userController;

	@EJB
	CookiesHandler cookiesHandler;

    @EJB
    LoginService loginService;

	private UIComponent loginField;

	private UIComponent passwordField;

    public boolean isLoginCorrect(Login login){
        String loginString = login.getLogin();
		User user = userController.getUser(loginString);
        FacesContext fc = FacesContext.getCurrentInstance();
        if (user == null) {
            fc.addMessage(loginField.getClientId(fc), new FacesMessage("Login is wrong"));
            return false;
        }
        boolean result = (user.isPasswordCorrect(login.getPassword()));
        if (!result) fc.addMessage(passwordField.getClientId(fc), new FacesMessage("Password is wrong"));
        return result;
    }

	public void logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
		loginService.removeUser();
        try {
			cookiesHandler.dropCookies();
            fc.getExternalContext().redirect("/login.xhtml");
        } catch (IOException ignored) {}
    }

	public void login(Login login){
        FacesContext fc = FacesContext.getCurrentInstance();
        if (isLoginCorrect(login)){
            String currentLogin = login.getLogin();
			User currentUser = userController.getUser(currentLogin);
			loginService.setUser(currentUser);
            if(login.isRemember()){
				cookiesHandler.addCookies(login.getLogin(), login.getPassword());
            }
            try {
                fc.getExternalContext().redirect("/ce/home.xhtml");
            } catch (IOException ignored) {}
        }
    }

	public String getCurrentLogin() {
        return loginService.getUser().getLogin();
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

	public void tryAutoLogin(){
		Login login = cookiesHandler.getCookiesLogin();
		if (login != null) login(login);
	}
}
