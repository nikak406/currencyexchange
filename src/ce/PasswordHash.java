package ce;

import javax.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//TODO: correct exception handling
@Stateless
public class PasswordHash {
	public String create(String password){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ignored) {
			return null;
		}
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		//convert the byte to hex format
		StringBuilder sb = new StringBuilder();
		for (byte aByteData : byteData) {
			sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
