/**
 * 
 */
package logic;

import java.util.ArrayList;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class SolitaireBoard {
	
	private SolitaireSpace[][] board;
	
	public SolitaireBoard() {
		board = new SolitaireSpace[7][7];
		this.fillBoard();
	}

	
	// Getters
	/**
	 * @return the board
	 */
	public SolitaireSpace[][] getBoard() {
		return board;
	}
	
	/**
	 * Returns the amount of empty solitaire spaces on the board.
	 * @return amount of empty spaces
	 */
	public int getAmountEmptySpaces() {
		int count = 0;
		// loop the rows
		for( int i=0; i<board.length; i++ ){
			// loop the columns
			for( int j=0; j<board[i].length; j++ ){
				if( board[i][j] != null ) {
					SolitaireSpace sp = board[i][j];
					if( sp.isEmpty() )
						++count;
				}	
			}
		}
		return count;
	}
	
	// Setters
	
	/**
	 * @param board the board to set
	 */
	protected void setBoard(SolitaireSpace[][] board) {
		this.board = board;
	}
	
	
	/* Fill the Solitaire board */
	private void fillBoard() {
		int aSpaces = 1;
		// rows
		for( int i=0; i<board.length; i++ ){
			int start = 0;
			int end = board[i].length;
			// rows with reduced length
			if( i==0 || i==1 || i==5 || i==6 ) {
				start = 2;
				end = 5;
			}
			// columns
			for( int j=start; j<end; j++ ){
				SolitaireSpace sp = new SolitaireSpace();
				sp.setRow( i );
				sp.setColumn( j );
				// middle one has to be empty
				if( aSpaces == 17 )
					sp.setEmpty( true );
				board[i][j] = sp;
				++aSpaces;
			}
		}
	}
	
	/**
	 * Checks whether the Move is viable and sets it
	 * @param s1
	 * @param s2
	 * @return a viable Move - null if not viable
	 */
	public Move move( SolitaireSpace s1, SolitaireSpace s2 ) {
		SolitaireSpace s3 = this.validateMove( s1, s2 );
		if( s3 != null ) {
			s1.setEmpty( true );
			s2.setEmpty( false );
			s3.setEmpty( true );
		}
		Move m = new Move( s1, s2 );
		m.setOver( s3 );
		return m;
	}
	
	/**
	 * Returns the possible viable moves of the pawn on the given space
	 * @param sp - the 
	 * @return all the viable moves
	 */
	public ArrayList<SolitaireSpace> getOptions( SolitaireSpace sp ) {
		ArrayList<SolitaireSpace> options = new ArrayList<SolitaireSpace>();
		SolitaireSpace s = null;
		if( sp != null ) {
			// check whether you can move the given pawn
			SolitaireSpace[] emptys = this.getEmptySpacesAround( sp );
			for( int i=0; i<emptys.length; i++ ) {
				if( emptys[i] != null ) {
					s = this.validateMove( sp, emptys[i] );
					if( s != null )
						options.add( emptys[i] );
				}
			}
		}
		return options;
	}
	
	/**
	 * Checks if there are still possible viable Move left on the Board.
	 * @return true if there are still viable moves left
	 */
	public boolean stillPossibleMoves() {
		boolean isPossible = true;
		ArrayList<SolitaireSpace> options = new ArrayList<SolitaireSpace>();
		// Loops over the rows and columns
		for( int i=0; i<board.length; i++ ) {
			for( int j=0; j<board[i].length; j++ ) {
				if( board[i][j] != null ) {
					if( !board[i][j].isEmpty() )
						options.addAll( this.getOptions(board[i][j]) );
				}
			}
		}
		if( options.size() == 0 )
			isPossible = false;
		return isPossible;
	}
	
	/**
	 * Checks whether you successfully finished the game.
	 * @return true if you have won the game
	 */
	public boolean finished() {
		boolean isFinished = false;
		// if there are no more viable moves left and the only pawn left is the one in the middle
		if( !this.stillPossibleMoves() && (this.getAmountEmptySpaces()==32) && (!board[3][3].isEmpty()) ) {
			isFinished = true;
		}
		return isFinished;
	}
	
	/* Gives the empty spaces around a given space */
	private SolitaireSpace[] getEmptySpacesAround( SolitaireSpace sp ) {
		SolitaireSpace[] spaces = new SolitaireSpace[4];
		SolitaireSpace s = null;
		// checks above of the space
		if( sp.getRow() > 1 ) {
			s = board[sp.getRow()-2][sp.getColumn()];
			if( s != null ) {
				if( s.isEmpty() )
					spaces[0] = s;
			}
		}
		// checks right of the space
		if( sp.getColumn() < (board[sp.getRow()].length-2) ) {
			s = board[sp.getRow()][sp.getColumn()+2];
			if( s != null ) {
				if( s.isEmpty() )
					spaces[1] = s;
			}
		}
		// checks under of the space
		if( sp.getRow() < (board.length-2) ) {
			s = board[sp.getRow()+2][sp.getColumn()];
			if( s != null ) {
				if( s.isEmpty() )
					spaces[2] = s;
			}
		}
		// checks left of the space
		if( sp.getColumn() > 1 ) {
			s = board[sp.getRow()][sp.getColumn()-2];
			if( s != null ) {
				if( s.isEmpty() )
					spaces[3] = s;
			}
		}
		return spaces;
	}
	
	/**
	 * Checks whether the Move is viable and returns the space between the both given spaces
	 * @param s1
	 * @param s2
	 * @return
	 */
	public SolitaireSpace validateMove( SolitaireSpace s1, SolitaireSpace s2 ) {
		SolitaireSpace s3 = null;
		if( s2.isEmpty() || s1.isEmpty() ) {
			s3 = this.getSolitaireSpaceBetween( s1, s2 );
			if( s3 != null ) {
				// The space in the middle can't be empty for a viable move
				if( s3.isEmpty() ) 
					s3 = null;
			}
		}
		return s3;
	}
	
	/* Returns the space between 2 given spaces */
	protected SolitaireSpace getSolitaireSpaceBetween( SolitaireSpace s1, SolitaireSpace s2 ) {
		SolitaireSpace sp = null;
		if( (s1!=null) && (s2!=null) ) {
			// horizontally
			if( s1.getRow() == s2.getRow() ) {
				// (move) from left to right
				if( (s1.getColumn()>s2.getColumn()) && ((s1.getColumn()-s2.getColumn())==2) )
					sp = board[s1.getRow()][s1.getColumn()-1];
				// (move) from right to left
				else if( (s2.getColumn()-s1.getColumn()) == 2 ) {
					sp = board[s1.getRow()][s2.getColumn()-1];
				}
			}
			// vertically
			else if( s1.getColumn() == s2.getColumn() ) {
				// (move) from up to down
				if( (s1.getRow()>s2.getRow()) && ((s1.getRow()-s2.getRow())==2) )
					sp = board[s1.getRow()-1][s1.getColumn()];
				// (move) down to up
				else if( (s2.getRow()-s1.getRow()) == 2 )
					sp = board[s2.getRow()-1][s1.getColumn()];
			}
		}
		return sp;
	}
	

}
