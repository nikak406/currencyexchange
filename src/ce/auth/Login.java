package ce.auth;

import javax.faces.bean.ManagedBean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ManagedBean
public class Login {

    public Login() {}

    private String login;

	private String password;

	private boolean remember;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        StringBuffer sb;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format
            sb = new StringBuffer();
			for (byte aByteData : byteData) {
				sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
			}
			this.password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void setHashPassword(String hashPassword){
        this.password = hashPassword;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
