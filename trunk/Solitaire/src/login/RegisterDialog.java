/**
 * 
 */
package login;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Administrator
 *
 */
public class RegisterDialog extends JDialog {

	private JPasswordField password1;
	private JPasswordField password2;
	private JTextField username;
	private JLabel feedback = new JLabel();
	private JButton registerButton;
	private LogInHandler lih;
	
	public RegisterDialog( JFrame owner, LogInHandler lih ) {
		super( owner, true );
		this.lih = lih;
		this.init();
	}
	
	public void init() {
		this.setTitle( "Log in" );
		this.setLayout( new GridLayout(4, 2) );
		JLabel lbl1 = new JLabel( "Username: " );
		this.add( lbl1 );
		username = new JTextField();
		this.add(username);
		lbl1 = new JLabel( "Password: " );
		this.add(lbl1);
		password1 = new JPasswordField();
		this.add(password1);
		lbl1 = new JLabel( "Retype password: " );
		this.add( lbl1 );
		password2 = new JPasswordField();
		this.add(password2);
		registerButton = new JButton( "Register" );
		registerButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lih.validateRegister();
			}
			
		});
		this.add( registerButton );
		this.setSize( 450, 400 );
		this.pack();
		this.setLocationRelativeTo(getRootPane());
		//this.setVisible(true);
	}

	/**
	 * @return the password1
	 */
	public char[] getPassword1() {
		return password1.getPassword();
	}

	/**
	 * @return the password2
	 */
	public JPasswordField getPassword2() {
		return password2;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username.getText();
	}

	/**
	 * @return the feedback
	 */
	public JLabel getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	protected void setFeedback( String feedback ) {
		this.feedback = new JLabel( feedback );
	}
	
	
	
}
