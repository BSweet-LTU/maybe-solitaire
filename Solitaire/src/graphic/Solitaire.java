/**
 * 
 */
package graphic;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
/*import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;*/

import logic.Move;
import login.LogInHandler;

/**
 * @author Sepp Hoedenaeken
 *
 */
public class Solitaire extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenu helpMenu;
	private JMenuItem quit;
	private JMenuItem newGame;
	private static JMenuItem undo;
	private JMenuItem topScore;
	private JMenuItem help;
	private JMenuItem about;
	
	private static JSolitaireBoard board;
	
	private LogInHandler lih;
	
	static Container c;
	
	public Solitaire() {
		super( "Solitaire - Sepp Hoedenaeken" );
		
		board = new JSolitaireBoard( this );
		
		c = this.getContentPane();
		c.setLayout( new FlowLayout() );
		c.add( board );
		
		// TODO Make a spacebar with the score on, WIP
		/*JPanel statusbar = new JPanel();
		JLabel test = new JLabel( "Dit is een test" );
		//statusbar.setSize( 600 , 30 );
		statusbar.setSize( new Dimension(600,35) );
		statusbar.setBackground( Color.ORANGE );
		statusbar.add( test );
		c.add( statusbar );*/
		
		
		this.setMenu();
		this.setJMenuBar( menuBar );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize( 500, 450 );
		this.setMinimumSize( new Dimension(board.getPreferredSize().width+10, board.getPreferredSize().height+30) );
		this.setLocationRelativeTo(getRootPane());
		this.setResizable( false );
		lih = new LogInHandler( this );
		this.setVisible( true );
		
	}
	
	/* Sets the Menu, obviously */
	private void setMenu() {
		menuBar = new JMenuBar();
		gameMenu = new JMenu( "Game" );
		// sneltoets ALT+G
		gameMenu.setMnemonic( KeyEvent.VK_G );
		menuBar.add( gameMenu );
		
		helpMenu = new JMenu( "Help " );
		// sneltoets ALT+H
		helpMenu.setMnemonic( KeyEvent.VK_H );
		menuBar.add( helpMenu );
		
		// menu items
		
		// new game
		newGame = new JMenuItem( "New Game" );
		newGame.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F2,0) );
		gameMenu.add( newGame );
		newGame.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				makeNewGame();
			}
		});
		
		gameMenu.addSeparator();
		
		undo = new JMenuItem( "Undo last move" );
		undo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK) );
		gameMenu.add( undo );
		undo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				undo();
			}
		});
		undo.setEnabled( false );
		
		// topscores
		topScore = new JMenuItem( "Topscores" );
		gameMenu.add( topScore );
		topScore.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				// TODO WIP
			}
		});
		
		gameMenu.addSeparator();
		
		// exit game
		quit = new JMenuItem( "Quit" );
		quit.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK) );
		gameMenu.add( quit );
		quit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				lih.writeUsers();
				System.exit(0);
			}
		});
		
		// help
		help = new JMenuItem( "Help" );
		help.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F1,0) );
		helpMenu.add( help );
		help.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JDialog d = new JDialog();
				d.setVisible( true );
			}
		});
		
		helpMenu.addSeparator();
		
		//about
		about = new JMenuItem( "About Solitaire ");
		helpMenu.add( about );
		about.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/* Deals with undo */
	private void undo() {
		Move m = Move.undoLastMove();
		if( m != null ) {
			board.getJSolitaireSpaceOf(m.getFrom()).setColor();
			board.getJSolitaireSpaceOf(m.getOver()).setColor();
			board.getJSolitaireSpaceOf(m.getTo()).setColor();
			board.repaint();
		}
		if( Move.getMoves().size() == 0 )
			undo.setEnabled( false );
	}
	
	public static JMenuItem getUndo() {
		return undo;
	}
	
	/**
	 * Removes the old board and make a new one for a new game.
	 */
	public void makeNewGame() {
		c.removeAll();
		board = new JSolitaireBoard( this );
		c.add( board );
		board.setVisible( true );
		c.repaint();
		board.repaint();
		this.setVisible( true );
	}

}
