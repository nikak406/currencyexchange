package ce;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@Stateless
public class UserController {

	@EJB
	UserDAO userDAO;

	@EJB
	FacesContextBean fcb;

	public List<User> getUsers(){
		return userDAO.getUsers();
	}

	public User getUser(String login){
		for(User user : getUsers()){
			if (login.equals(user.getLogin())) return user;
		}
		return null;
	}

	public boolean containsUser(String login){
		return (getUser(login) != null);
	}

	public void register(Register register){ //TODO add check of existing login
		if (containsUser(register.getLogin())) return;
		User user = new User(register.getLogin(), register.getPassword(), register.getName(),
				register.getEmail(), register.getLocation(), register.getRoom(), register.getPhoneNumber());
		userDAO.registerUser(user);
		FacesContext fc = fcb.getFC();
		fc.addMessage(null, new FacesMessage("Successfully registered"));
	}

	public void update(CurrentUser currentUser){
		FacesContext fc = fcb.getFC();
		fc.addMessage(null, new FacesMessage("Successfully saved"));
	}
}
