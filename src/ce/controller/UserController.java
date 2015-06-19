package ce.controller;

import ce.model.DAO.UserDAO;
import ce.model.LoggedInUser;
import ce.model.User;
import ce.view.CurrentUser;
import ce.view.NewUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.util.List;

@Named
@Stateless
public class UserController {

	@EJB
	UserDAO userDAO;

	@EJB
	FacesContextValue fcb;

	@EJB
	LoggedInUser loggedInUser;

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

	//TODO add prefilled fields
	public void update(CurrentUser currentUser){
		User loggedInUser = this.loggedInUser.getUser();
		if (currentUser.getLocation() != null) {
			loggedInUser.setLocation(currentUser.getLocation());
		}
		if (currentUser.getRoom() != null) {
			loggedInUser.setRoom(currentUser.getRoom());
		}
		if (currentUser.getEmail() != null) {
			loggedInUser.setEmail(currentUser.getEmail());
		}
		if (currentUser.getPhoneNumber() != null) {
			loggedInUser.setPhoneNumber(currentUser.getPhoneNumber());
		}
		loggedInUser.setNotifyViaMail(currentUser.getNotifyViaMail());
		userDAO.updateUser(loggedInUser);
		javax.faces.context.FacesContext fc = fcb.getInstance();
		fc.addMessage(null, new FacesMessage("Successfully saved"));
	}
}
