/**
 * 
 */
package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * @author Administrator
 *
 */
public class AnimationEvent implements ActionListener {

	private JSolitaireSpace from;
	private JSolitaireSpace to;
	private JSolitaireBoard b;
	
	public AnimationEvent( JSolitaireSpace from, JSolitaireSpace to, JSolitaireBoard board ) {
		this.from = from;
		this.to = to;
		this.b = board;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.moveToXR();

	}
	
	public void moveToXR() {
		int x1 = from.getX();
		int x2 = to.getX();
		JSolitaireSpace tmp = new JSolitaireSpace();
		tmp.setSp(from.getSp());
		tmp.setLocation(x1, from.getY());
		for( int i=0; i<x2-x1; i++ ) {
			tmp.setLocation(tmp.getX()+i, /*from.getY()*/350);
			tmp.repaint();
			b.repaint();
			b.repaintBoard();
			//wait();
			System.out.println( "Moving..." );
			//Thread.sleep(100);
			
		}
	}

}
