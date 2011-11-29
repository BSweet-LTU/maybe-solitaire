/**
 * 
 */
package login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class LogInDialog extends JDialog {

	private /*JTextField*/ JPasswordField password;
	private JTextField username;
	private JLabel feedback = new JLabel();
	private JButton logInButton;
	private LogInHandler lih;
	
	
	public LogInDialog( JFrame owner, LogInHandler lih ) {
		super( owner, true );
		this.lih = lih;
		this.init();
	}
	
	public void init() {
		this.setTitle( "Log in" );
		this.setLayout( new GridLayout(4, 2) );
		feedback = new JLabel( "Type your username and password in." );
		this.add( feedback );
		this.add( new JLabel("") );
		JLabel lbl1 = new JLabel( "Username: " );
		this.add( lbl1 );
		username = new JTextField();
		this.add(username);
		JLabel lbl2 = new JLabel( "Password: " );
		this.add(lbl2);
		password = new JPasswordField();
		this.add(password);
		logInButton = new JButton( "Log in" );
		logInButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lih.validateLogIn();
			}
			
		});
		this.add( logInButton );
		JButton registerButton = new JButton( "Make new account" );
		registerButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lih.createNewAccount();
			}
			
		});
		this.add(registerButton);
		this.setSize( 450, 400 );
		this.pack();
		this.setLocationRelativeTo(getRootPane());
	}
	
	public String getUsername() {
		return this.username.getText();
	}
	public char[] getPassword() {
		return this.password.getPassword();
	}

	/**
	 * Sets the feedback
	 * @param feedback the feedback to set
	 */
	protected void setFeedback( String feedback ) {
		this.feedback.setVisible(false);
		this.feedback.setText(feedback);
		this.feedback.setVisible(true);
	}
	
	
}
