package ce;

import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;

@Stateful
@SessionScoped
public class LoggedInUser {

    private User user;

    public User getUser() {
        return user;
    }

    public void setLoggedInUser(User user) {
        this.user = user;
    }

    public String getLogin(){
        if (user == null ) return null;
        return user.getLogin();
    }
}
