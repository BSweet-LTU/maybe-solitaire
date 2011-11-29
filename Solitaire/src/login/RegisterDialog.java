/**
 * 
 */
package login;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JLabel feedback;
	private JButton registerButton;
	private LogInHandler lih;
	
	public RegisterDialog( JFrame owner, LogInHandler lih ) {
		super( owner, true );
		this.lih = lih;
		this.init();
	}
	
	public void init() {
		this.setTitle( "Log in" );
		this.setLayout( new GridLayout(5, 2) );
		feedback = new JLabel( "Choose your username and password." );
		this.add( feedback );
		JPanel prow = new JPanel(new GridLayout(1,2));
		JLabel lbl1 = new JLabel( "Username: " );
		prow.add(lbl1);
		this.username = new JTextField();
		prow.add(username);
		this.add(prow);
		prow = new JPanel(new GridLayout(1,2));
		lbl1 = new JLabel( "Password: " );
		prow.add(lbl1);
		password1 = new JPasswordField();
		prow.add(password1);
		this.add(prow);
		prow = new JPanel(new GridLayout(1,2));
		lbl1 = new JLabel( "Retype password: " );
		prow.add(lbl1);
		password2 = new JPasswordField();
		prow.add(password2);
		this.add(prow);
		registerButton = new JButton( "Register" );
		registerButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
		this.feedback.setVisible(false);
		this.feedback.setForeground( Color.RED );
		this.feedback.setText(feedback);
		this.pack();
		this.feedback.setVisible(true);
	}
	
	
	
}
