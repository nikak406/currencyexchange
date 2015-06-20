package ce.controller;

import ce.model.DAO.UserDAO;
import ce.model.User;
import ce.view.EditUser;
import ce.view.NewUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Stateless
public class UserController {

	@EJB
	UserDAO userDAO;

	@EJB
	FacesContextValue fcb;

    @Inject @LoggedInUser
    User loggedInUser;

	public List<User> getUsers(){
		return userDAO.getUsers();
	}

	public User getUser(String login){
		for(User user : getUsers()){
			if (user.getLogin().equals(login)) return user;
		}
		return null;
	}

	public boolean containsUser(String login){
		return (getUser(login) != null);
	}

	public void register(NewUser newUser){
		if (containsUser(newUser.getLogin())) return;
		User user = new User(newUser.getLogin(), newUser.getPassword(), newUser.getName(),
				newUser.getEmail(), newUser.getLocation(), newUser.getRoom(), newUser.getPhoneNumber());
		userDAO.registerUser(user);
		javax.faces.context.FacesContext fc = fcb.getInstance();
		fc.addMessage(null, new FacesMessage("Successfully registered"));
	}

	public void update(EditUser editUser){
		if (editUser.getLocation() != null) {
			loggedInUser.setLocation(editUser.getLocation());
		}
		if (editUser.getRoom() != null) {
			loggedInUser.setRoom(editUser.getRoom());
		}
		if (editUser.getEmail() != null) {
			loggedInUser.setEmail(editUser.getEmail());
		}
		if (editUser.getPhoneNumber() != null) {
			loggedInUser.setPhoneNumber(editUser.getPhoneNumber());
		}
		loggedInUser.setNotifyViaMail(editUser.getNotifyViaMail());
		userDAO.updateUser(loggedInUser);
		javax.faces.context.FacesContext fc = fcb.getInstance();
		fc.addMessage(null, new FacesMessage("Successfully saved"));
	}
}
