/**
 * 
 */
package logic;

import java.util.ArrayList;

/**
 * @author Sepp Hoedenaeken
 * 
 */
public class Move {

	/**
	 * The variable which represents the starting space from the Move.
	 */
	private SolitaireSpace from;
	/**
	 * The Variable which represents the ending (empty) space from the Move.
	 */
	private SolitaireSpace to;
	/**
	 * The Variable which represents the middle space from the Move, where the
	 * pawn jumps over.
	 */
	private SolitaireSpace over;
	/**
	 * , The variable which represents a collection of all the done Moves.
	 */
	private static ArrayList<Move> moves = new ArrayList<Move>();

	// Constructor
	/**
	 * Constructs a new Move with the starting space to the end space
	 * 
	 * @param s1
	 *            - the starting space
	 * @param s2
	 *            - the ending (empty) space
	 */
	public Move(SolitaireSpace s1, SolitaireSpace s2) {
		this.setFrom(s1);
		this.setTo(s2);
		moves.add(this);
	}

	/**
	 * Removes the last done move from the list
	 */
	public static void removeLastMove() {
		moves.remove(moves.size() - 1);
	}

	/**
	 * Removes the last Move from the list and undo's the last and returns this
	 * Move.
	 * 
	 * @return the Move you have removed
	 */
	public static Move undoLastMove() {
		Move m = null;
		if (moves.size() > 0) {
			m = Move.getLastMove();
			Move.removeLastMove();
			m.from.setEmpty(false);
			m.over.setEmpty(false);
			m.to.setEmpty(true);
		}
		return m;
	}

	// Getters
	/**
	 * Returns the starting space from the Move.
	 * 
	 * @return the starting space
	 */
	public SolitaireSpace getFrom() {
		return this.from;
	}

	/**
	 * Returns the ending (empty) space from the Move.
	 * 
	 * @return the ending space
	 */
	public SolitaireSpace getTo() {
		return this.to;
	}

	/**
	 * Returns the space where the pawn jumps over.
	 * 
	 * @return the middle space
	 */
	public SolitaireSpace getOver() {
		return this.over;
	}

	/**
	 * Returns all the done moves.
	 * 
	 * @return list of all moves
	 */
	public static ArrayList<Move> getMoves() {
		return Move.moves;
	}

	/**
	 * Returns the last done move
	 * 
	 * @return last move
	 */
	public static Move getLastMove() {
		return moves.get(moves.size() - 1);
	}

	// Setters
	/**
	 * Sets the starting space of the Move.
	 * 
	 * @param from
	 *            the from to set
	 */
	protected void setFrom(SolitaireSpace from) {
		this.from = from;
	}

	/**
	 * Sets the ending space of the Move.
	 * 
	 * @param to
	 *            the to to set
	 */
	protected void setTo(SolitaireSpace t) {
		this.to = t;
	}

	/**
	 * Sets the the space where the pawn jumped over.
	 * 
	 * @param over
	 *            the over to set
	 */
	protected void setOver(SolitaireSpace over) {
		this.over = over;
	}

}
