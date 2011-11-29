/**
 * 
 */
package login;

import javax.swing.JFrame;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class LogInHandler {
	
	private UserContainer users;
	private LogInDialog login;
	private RegisterDialog register;
	
	public LogInHandler( JFrame frame ) {
		users = UserContainer.getInstance();
		this.readUsers();
		login = new LogInDialog( frame, this );
		login.setEnabled( true );
		login.setVisible( true );
		
	}
	
	public void validateLogIn() {
		User u = users.getUser(login.getUsername());
		if( u == null )
			login.setFeedback( "The username you have entered does not exist." );
		else if( !u.isPassword(this.passwordToString(login.getPassword())) ) 
			login.setFeedback( "The password you have entered is incorrect." );
		else
			login.setVisible( false );
	}
	
	private String passwordToString( char[] password ) {
		String pwd = "";
		for( int i=0; i<password.length; i++ ) {
			pwd += password[i];
		}
		return pwd;
	}
	
	public void createNewAccount() {
		login.setVisible(false);
		register = new RegisterDialog( (JFrame)login.getOwner(), this );
		register.setVisible(true);
	}
	
	public void validateRegister() {
		String uname = register.getUsername();
		User u = users.getUser(uname);
		String pwd1 = this.passwordToString(register.getPassword1());
		if( u != null ) 
			register.setFeedback( "The username you have entered already exists" );
		else if( uname.length() < 3 ) 
			register.setFeedback( "You have to enter a username with at least 3 signs" );
		else if( pwd1.length() < 6 )
			register.setFeedback( "You have to enter a password with at least 3 signs" );
		else {
			users.createNewUser(register.getUsername(), pwd1 );
			register.setVisible(false);
		}
		
	}
	
	public void writeUsers() {
		ReadWriteUsers.writeUsers( users.getUsers() );
	}
	
	public void readUsers() {
		users.setUsers( ReadWriteUsers.readUsers() );
	}
	
	/**
	 * Prepares the object for garbage collector.
	 */
	@Override
	public void finalize() throws Throwable {
		this.writeUsers();
		super.finalize();
	}
	
}
