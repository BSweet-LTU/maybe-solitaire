/**
 * 
 */
package graphic;

import java.awt.Color;
import javax.swing.Timer;
import java.util.TimerTask;

/**
 * @author Administrator
 *
 */
public class Animation/* implements Runnable*/ {

	
	Timer timer;
	
	public Animation( JSolitaireSpace from, JSolitaireSpace to, JSolitaireBoard board ) {
		/*this.from = from;
		this.to = to;
		this.b = board;*/
		//timer = new Timer();
		//timer.schedule(new AnimationTask(from, to, board), 10 );
		
	}
	
}

	class AnimationTask extends TimerTask {
	
		private JSolitaireSpace from;
		private JSolitaireSpace to;
		private JSolitaireBoard b;
		
		public AnimationTask( JSolitaireSpace from, JSolitaireSpace to, JSolitaireBoard board ) {
			this.from = from;
			this.to = to;
			this.b = board;
		}
		
		/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if( from.getX() < to.getX() ) {
			try {
				this.moveToXR();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void moveToXR() throws InterruptedException {
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
