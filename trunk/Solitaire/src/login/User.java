/**
 * 
 */
package login;

import java.io.Serializable;
import java.security.MessageDigest;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6831006779044261140L;
	private String username;
	private String password;
	private final int userID;
	
	public User( int ID, String username, String password ) {
		this.setUsername( username );
		this.setPassword( password );
		this.userID = ID;
	}
	
	/**
	 * @return the user name
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param username the user name to set
	 */
	protected void setUsername( String username ) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	protected void setPassword( String password ) {
		this.password = this.encryptedPassword( password );
	}
	
	/* Encrypts the given password */
	private String encryptedPassword( String password ) {
		String encpwd = null;
		try {
	         MessageDigest sha = MessageDigest.getInstance("MD5");
	         byte[] tmp = password.getBytes();
	         sha.update(tmp);
	         encpwd = new String(sha.digest());
	      }
	      catch( java.security.NoSuchAlgorithmException e ) {
	         System.out.println( "Rats, MD5 doesn't exist" );
	         System.out.println( e.toString() );
	      }
		return encpwd;
	}
	
	public boolean isPassword( String password ) {
		if( this.getPassword().equalsIgnoreCase(this.encryptedPassword(password)) ) 
			return true;
		else
			return false;
	}
	

}
