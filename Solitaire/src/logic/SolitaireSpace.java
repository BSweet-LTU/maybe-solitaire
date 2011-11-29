/**
 * 
 */
package logic;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class SolitaireSpace {
	
	private boolean empty;
	private int row;
	private int column;
	
	public SolitaireSpace() {
		this.setEmpty( false );
		row = 0;
		column = 0;
	}

	// Getters
	/**
	 * Returns whether the space is empty or whether there is a pawn on it.
	 * @return true if the space is empty
	 */
	public boolean isEmpty() {
		return this.empty;
	}
	/**
	 * Returns the value of row of the position in the board of this Space.
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}
	/**
	 * Returns the value of column of the position in the board of this Space.
	 * @return the column
	 */
	public int getColumn() {
		return this.column;
	}
	
	
	// Setters
	/**
	 * @param row the row to set
	 */
	protected void setRow(int row) {
		this.row = row;
	}
	/**
	 * @param column the column to set
	 */
	protected void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @param empty the empty to set
	 */
	protected void setEmpty(boolean empty) {
		this.empty = empty;
	}
	

}
