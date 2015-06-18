package ce.controller;

import ce.model.LoggedInUser;
import ce.model.User;
import ce.model.UserDAO;
import ce.view.CurrentUser;
import ce.view.Register;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
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

	public void register(Register register){
		if (containsUser(register.getLogin())) return;
		User user = new User(register.getLogin(), register.getPassword(), register.getName(),
				register.getEmail(), register.getLocation(), register.getRoom(), register.getPhoneNumber());
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
