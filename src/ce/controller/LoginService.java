package ce.controller;

import ce.model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class LoginService {

    public static final String LOGIN = "login";

    SessionService sessionService = new SessionService();

    @Produces
    @LoggenInUser
    public User getUser(){
        Object object = sessionService.readFromSession(LOGIN);
        return (User) object;
    }

    public void removeUser(){
        sessionService.removeFromSession(LOGIN);
    }

    public void setUser(User user) {
        sessionService.attachToSession(LOGIN, user);
    }
}
