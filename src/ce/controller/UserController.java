package ce.controller;

import ce.model.DAO.UserDAO;
import ce.model.User;
import ce.view.EditUser;
import ce.view.NewUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Stateless
public class UserController {

	@EJB
	UserDAO userDAO;

	public List<User> getUsers(){
		return userDAO.getUsers();
	}

	public User getUser(String login){
		return userDAO.getUser(login);
    }

	public boolean containsUser(String login){
		return (getUser(login) != null);
	}

	public void register(NewUser newUser){
		if (containsUser(newUser.getLogin())) return;
		User user = new User(newUser.getLogin(), newUser.getPassword(), newUser.getName(),
				newUser.getEmail(), newUser.getLocation(), newUser.getRoom(), newUser.getPhoneNumber());
		userDAO.registerUser(user);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Successfully registered"));
	}

	public void update(User user) {
		userDAO.updateUser(user);
	}
}
