/**
 * 
 */
package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import logic.Move;
import logic.SolitaireBoard;
import logic.SolitaireSpace;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JDialog;


/**
 * @author Sepp Hoedenaeken
 *
 */
public class JSolitaireBoard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SolitaireBoard board;
	private JSolitaireSpace[][] jboard;
	private JSolitaireSpace selected;
	private Solitaire owner;
	
	private int selects = 0;
	
	public JSolitaireBoard( JFrame owner ) {
		this.owner = (Solitaire) owner;
		this.board = new SolitaireBoard();
		this.setLayout( new GridLayout(7, 7) );
		this.setPreferredSize( new Dimension((7*50)+5,(7*50)+5) );
		
		jboard = new JSolitaireSpace[7][7];
		int y = 0;
		for( int i=0; i<board.getBoard().length; i++ ) {
			int x = 0;
			for( int j=0; j<board.getBoard()[i].length; j++ ) {
				JSolitaireSpace sp = new JSolitaireSpace();
				sp.setSp( board.getBoard()[i][j] );
				//sp.setEnabled( false );
				sp.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseClicked( MouseEvent arg0 ) {
						JSolitaireSpace s = (JSolitaireSpace)arg0.getSource();
						s.select();
						//s.repaint();
						move( s );
					}
					@Override
					public void mouseEntered( MouseEvent arg0 ) {
						// TODO Auto-generated method stub	
						repaintBoard();
					}
					@Override
					public void mouseExited( MouseEvent arg0 ) {
						// TODO Auto-generated method stub
					}
					});
				
				if( board.getBoard()[i][j] == null ) {
					sp.setVisible( false );
				}
				else
					sp.setColor();
				jboard[i][j] = sp;
				
				this.add( sp );
				sp.setLocation(x, y);
				sp.setSize(50, 50);
				//sp.setVisible(true);
				sp.repaint();
				x+= 50;
			}
			y+=50;
		}
		repaintBoard();
		this.setVisible( true );
	}
	
	// No Good, TODO fix this shit
	private void sssh( JSolitaireSpace s ) {
		ArrayList<SolitaireSpace> options = board.getOptions( s.getSp() );
		if( options.size() == 0 ) {
			selects = 0;
			s.select();
		}
		else {
			++selects;
			selected = s;
			repaintBoard();
		}
	}
	
	/* Checks whether the second Space is a valid option to move of the first one */
	private boolean isOption( JSolitaireSpace s1, JSolitaireSpace s2 ) {
		boolean isOption = false;
		ArrayList<SolitaireSpace> options = board.getOptions( s1.getSp() );
		for( SolitaireSpace sp : options ) {
			if( sp == s2.getSp() ) {
				isOption = true;
				break;
			}
		}
		return isOption;
	} 
	
	/* Moves the Pawns graphicaly */
	private void move( JSolitaireSpace s ) {
		if( !board.stillPossibleMoves() )
			lostDialog();
		// If you already have select a pawn
		if( selects == 1 ) {
			JSolitaireSpace s1 = selected;
			JSolitaireSpace s2 = s;
			if( s1 != s2 ) {
				// if viable Move
				if( isOption(s1, s2) ) {
					Move m = null;
					m = board.move(s1.getSp(), s2.getSp());
					JSolitaireSpace s3 = this.getJSolitaireSpaceOf(m.getOver());
					// set the graphics of the spaces
					s1.select();
					s1.setColor();
					//s1.repaint();
					s2.setColor();
					//s2.repaint();
					s3.setColor();
					//s3.repaint();
					selects = 0;
					repaintBoard();
					// enable undo menu item
					Solitaire.getUndo().setEnabled( true );
				}
				s2.select();
				if( !board.stillPossibleMoves() && !board.finished() ) {
					this.lostDialog();
				}
				if( board.finished() )
					JOptionPane.showMessageDialog( this, "Congratulations! You have won the game." );
			}
			else {
				// deselect all
				selected = null;
				selects = 0;
				repaintBoard();
			}
		}
		else {
			if( !s.getSp().isEmpty() ) {
				sssh( s );
			}
			else {
				selects = 0;
				s.select();
				s.repaint();
			}
		}
	}
	
	/* Get the graphical wrapper Space object from of the logical one  */
	protected JSolitaireSpace getJSolitaireSpaceOf( SolitaireSpace sp ) {
		JSolitaireSpace s = null;
		for( int i=0; i<jboard.length; i++ ) {
			for( int j=0; j<jboard[i].length; j++ ) {
				if( jboard[i][j] != null ) {
					if( jboard[i][j].getSp() == sp )
						s = jboard[i][j];
				}
			}
		}
		return s;
	}
	
	
	private void repaintBoard() {
		this.repaint();
	}
	
	/* Makes a Dialog for if you failed to complete the game */
	private void lostDialog() {
		Object[] options = { "Exit", "New Game", "Go Back" };
		int n = JOptionPane.showOptionDialog( this, "There are no more moves available. Loser!", "You lose", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
		switch( n ) {
			case 0 : System.exit(0);
					break;
			case 1 : owner.makeNewGame();
					break;
		}
	}
	
}
