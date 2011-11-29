/**
 * 
 */
package graphic;

import java.awt.*;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JPanel;

import logic.SolitaireSpace;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class JSolitaireSpace extends JPanel {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private SolitaireSpace sp;
	private Color color;
	private Image img;
	private boolean statusSelected;
	
	public JSolitaireSpace() {
		super();
		this.setPreferredSize( new Dimension(50, 50) );
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * @return the img
	 */
	public Image getImg() {
		return this.img;
	}

	/**
	 * @return the sp
	 */
	public SolitaireSpace getSp() {
		return this.sp;
	}

	/**
	 * Returns whether this Space is selected or not.
	 * @return true if selected
	 */
	public boolean isStatusSelected() {
		return this.statusSelected;
	}

	/**
	 * @param sp the sp to set
	 */
	protected void setSp( SolitaireSpace sp ) {
		this.sp = sp;
	}

	/**
	 * Sets the selected status of this Space.
	 * @param selected the selected to set
	 */
	public void setStatusSelected( boolean selected ) {
		this.statusSelected = selected;
	}
	
	/* Sets the image of the pawn */
	private void setImageBall() {
		URL url = this.getClass().getResource("ball1.png"); 
		img = Toolkit.getDefaultToolkit().getImage( url );
	}
	/* Sets the image of a selected pawn */
	private void setImageSelected() {
		URL url = this.getClass().getResource("ball2.png"); 
		img = Toolkit.getDefaultToolkit().getImage( url );
	}
	
	/* Updates the color/image according to state */
	protected void setColor() {
		if( sp.isEmpty() ) {
			this.color = Color.WHITE;
			img = null;
		}
		else if( statusSelected ) {
			this.color = Color.RED;
			this.setImageSelected();
		}
		else {
			this.color = Color.BLACK;
			this.setImageBall();
		}
		//this.setVisible( true );
		//this.repaint();
	}
	
	/**
	 * Deals with the selection/deselection of the pawn
	 */
	public void select() {
		if( this.isStatusSelected() ) {
			if( this.getSp().isEmpty() ) {
				this.color = Color.WHITE;
				img = null;
			}
			else{
				this.color = Color.BLACK;
				this.setImageBall();
			}
			this.setStatusSelected( false );
		}
		else {
			this.color = Color.RED;
			this.setImageSelected();
			this.setStatusSelected( true );
		}
		//this.repaint();
	}
	
	/**
	 * Paints the pawn on the screen.
	 */
	public void paint(Graphics g) {
		Dimension size = getSize();
        // diameter
        int d = Math.min(size.width, size.height); 
        int x = (size.width - d)/2;
        int y = (size.height - d)/2;

        // draw circle (color already set to foreground)
        /*if( this.getSp().isEmpty() )
        	g.setColor(Color.WHITE);
        else if( this.isStatusSelected() ) 
        	g.setColor( Color.RED );
        else
        	g.setColor(Color.BLACK);*/
        g.setColor( this.getColor() );
        //g.fillOval(x, y, d, d);
        //g. drawOval(x, y, d, d);
        if( img != null ) 
        	g.drawImage( img, x, y, this );
        else
        	g.fillOval(x, y, d, d);
	}
	
}
