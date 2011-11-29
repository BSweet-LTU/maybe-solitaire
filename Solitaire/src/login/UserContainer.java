/**
 * 
 */
package login;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Sepp Hoedenaeken
 *
 */
public final class UserContainer {

	
	private ArrayList<User> users;
	private static UserContainer instance = null;
	
	
	/**
	 * Only this class can make and manage an instance of itself.
	 */
	private UserContainer() {
		users = new ArrayList<User>();
		/*this.createNewUser( "test", "test" );
		this.createNewUser( "test2", "test" );
		this.createNewUser( "test3", "test" );
		this.createNewUser( "test4", "test" );
		this.createNewUser( "test5", "test" );
		System.out.println( "filling database" );*/
	}
	
	/**
	 * Returns a Collection of all the users.
	 * @return all the users
	 */
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	/**
	 * Sets the collection of users (if it doesn't exist yet)
	 * @param users
	 */
	protected void setUsers( ArrayList<User> users ) {
		if( this.users.isEmpty() ) 
			this.users = users;
		System.out.println( users.size() );
	}
	
	/**
	 * Returns the one and only instance of this UserContainer.
	 * @return one global instance of UserContainer
	 */
	public static UserContainer getInstance() {
		if( instance == null ) {
			// Only 1 thread at the time should be able to 
			synchronized( UserContainer.class ) {
				if( instance == null )
					instance = new UserContainer();
			}
		}
		return instance;
	}
	
	/* Adds a user to the Collection of users */
	private void addUser( User u ) {
		if( !this.getUsers().contains(u) )
			this.getUsers().add(u.getUserID(), u );
	}
	
	/**
	 * Returns the User with the given user name.
	 * @param username of the User you want to search
	 * @return user with the given user name
	 */
	public User getUser( String username ) {
		User user = null;
		if( !users.isEmpty() ) {
			for( User u : this.getUsers() ) {
				if( username.equalsIgnoreCase(u.getUsername()) )
					user = u;
			}
		}
		return user;
	}
	
	/**
	 * Creates a new User with the given user name and password and adds it to the Collection.
	 * @param username
	 * @param password
	 */
	public void createNewUser( String username, String password ) {
		if( this.getUser(username) == null ) 
			this.addUser( new User(this.getUsers().size(), username, password) );
	}
	
}
