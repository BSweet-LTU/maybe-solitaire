/**
 * 
 */
package graphic;

/**
 * @author Administrator
 *
 */
public class Animation implements Runnable {

	private JSolitaireSpace from;
	private JSolitaireSpace to;
	
	public Animation( JSolitaireSpace from, JSolitaireSpace to ) {
		this.from = from;
		this.to = to;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x = from.getX();
	}

}
