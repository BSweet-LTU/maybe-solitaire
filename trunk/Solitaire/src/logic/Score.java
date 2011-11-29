/**
 * 
 */
package logic;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class Score {
	
	private int score;
	private String username;
	
	public Score( String username ) {
		this.reset();
		this.setUsername( username );
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param score the score to set
	 */
	protected void setScore( int score ) {
		this.score = score;
	}
	
	/**
	 * @param username the username to set
	 */
	protected void setUsername( String username ) {
		this.username = username;
	}

	public void increment() {
		this.setScore( this.getScore() + 10 );;
	}
	public void decrement() {
		this.setScore( this.getScore() - 10 );
	}
	public void reset() {
		this.setScore( 0 );
	}

}
