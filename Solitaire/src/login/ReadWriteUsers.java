/**
 * 
 */
package login;

import java.util.ArrayList;
import java.io.*;

/**
 * @author Gebruiker
 *
 */
public class ReadWriteUsers {
	
	
	public static void writeUsers( ArrayList<User> users ) {
		try {
			ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream("users.udb") );
			System.out.println("writing");
			os.writeObject( users );
			os.close();
		} 
		catch( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<User> readUsers() {
		ArrayList<User> users = null;
		try {
			ObjectInputStream is = new ObjectInputStream( new FileInputStream("users.udb") );
			System.out.println("reading");
			users = ((ArrayList<User>) is.readObject());
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
