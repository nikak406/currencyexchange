package ce.controller;

import ce.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class LoginService {

    public static final String LOGIN = "login";

    @EJB
    SessionService sessionService;

    @EJB
    UserController userController;

    @Produces
    @LoggedInUser
    public User getUser() {
        Object object = sessionService.readFromSession(LOGIN);
        String login = (String) object;
        return userController.getUser(login);
    }

    public User getUser(String login) {
        return userController.getUser(login);
    }

    public void removeUser(){
        sessionService.removeFromSession(LOGIN);
    }

    public void setUser(User user) {
        sessionService.attachToSession(LOGIN, user.getLogin());
    }

    public void update(User user) {
        userController.update(user);
    }
}
