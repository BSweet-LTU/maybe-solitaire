/**
 * 
 */
package login;

import java.awt.Color;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField password;
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
		this.feedback = new JLabel( "Type your username and password in." );
		this.add( feedback );
		JPanel prow = new JPanel(new GridLayout(1,2));
		JLabel lbl1 = new JLabel( "Username: " );
		prow.add(lbl1);
		this.username = new JTextField();
		prow.add(username);
		this.add(prow);
		prow = new JPanel(new GridLayout(1,2));
		JLabel lbl2 = new JLabel( "Password: " );
		prow.add(lbl2);
		this.password = new JPasswordField();
		prow.add(password);
		this.add(prow);
		prow = new JPanel(new GridLayout(1,2));
		this.logInButton = new JButton( "Log in" );
		this.logInButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lih.validateLogIn();
			}
		});
		prow.add(logInButton);
		JButton registerButton = new JButton( "Make new account" );
		registerButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lih.createNewAccount();
			}
		});
		prow.add(registerButton);
		this.add(prow);
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
		this.feedback.setForeground( Color.RED );
		this.feedback.setText(feedback);
		this.feedback.setVisible(true);
	}
	
	
}
