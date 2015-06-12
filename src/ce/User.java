package ce;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@NotNull
    @Id
    protected String login;

	protected String password;

	protected String name;

	protected String email;

	protected String location;

	protected String room;

	protected String phoneNumber;

	protected boolean notifyViaMail = false;

	public User(){}

	public User(String login, String password, String name, String email, String location, String room, String phone){
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
		this.location = location;
		this.room = room;
		this.phoneNumber = phone;
    }

	public boolean isPasswordCorrect(String password){ //TODO: handle exception correctly
        if (this.password.equals(password)) return true;
        else{
            try {
                Thread.sleep(1000); //broadforce defence
            } catch (InterruptedException ignored) {}
            return false;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isNotifyViaMail() {
		return notifyViaMail;
	}

	public void setNotifyViaMail(boolean notifyViaMail) {
		this.notifyViaMail = notifyViaMail;
	}
}
