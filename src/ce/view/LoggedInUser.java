package ce.view;

import ce.model.User;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoggedInUser {

    @Inject @ce.controller.LoggedInUser
    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public String getLogin(){
        if (user == null) return "";
        return user.getLogin();
    }
}
