package ce.view;

import ce.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditUser extends User {

    @Inject @ce.controller.LoggedInUser
    User loggedInUser;

    public EditUser(){}

    @PostConstruct
    public void init(){
        if (loggedInUser == null) return;
        this.setEmail(loggedInUser.getEmail());
        this.setLocation(loggedInUser.getLocation());
        this.setRoom(loggedInUser.getRoom());
        this.setPhoneNumber(loggedInUser.getPhoneNumber());
        this.setNotifyViaMail(loggedInUser.getNotifyViaMail());
    }
}
