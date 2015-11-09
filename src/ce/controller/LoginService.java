package ce.controller;

import ce.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class LoginService {

    public static final String LOGIN = "login";

    SessionService sessionService = new SessionService();

    @EJB
    UserController userController;

    @Produces
    @LoggedInUser
    //TODO BUG switch to login string oly instead of user object (commented out, but not working)
    public User getUser() {
        //Object object = sessionService.readFromSession(LOGIN);
        //String login = (String) object;
        //return userController.getUser(login);
        return (User) sessionService.readFromSession(LOGIN);
    }

    public void removeUser(){
        sessionService.removeFromSession(LOGIN);
    }

    public void setUser(User user) {
        sessionService.attachToSession(LOGIN, user/*.getLogin()*/);
    }
}
