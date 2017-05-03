package APCS2016Proj06Solitaire;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GScalable;
import acm.program.GraphicsProgram;

/**
 * The rules for Spider Solitaire card games.
 * 
 * The Pack
 *    Each game involves 104 cards and eight suits (some duplicated). Depending on the difficulty, 
 *    the pack may include:
 *       * Beginner - one suit (8 decks but using only the spades)
 *       * Intermediate - two suits (4 decks but only using the spades and hearts)
 *       * Advanced - four suits (2 decks using all four suits)
 *    
 * Object of the Game
 *    The goal is to assemble 13 cards of a suit, in ascending sequence from ace through king, 
 *    on top of a pile. Whenever a full suit of 13 cards is so assembled, it is lifted off and 
 *    discarded from the game. The game is won if all eight suits are played out.
 *    
 * The Deal
 *    Ten piles of five cards each are dealt by rows. The first four piles have an additional
 *    sixth card dealt -- making a total of 54 initial cards dealt. All cards in each pile are 
 *    face down, except for the top card, which is face up. The remaining fifty cards are held
 *    in reserve. Under suitable conditions (see below), the player can dealer another row of
 *    ten cards from this reserve.
 *    
 * The Play
 *    The top card of a pile may be moved, together with all face-up cards below it that follow in 
 *    ascending suit and sequence.
 *    
 *    A sequence of available cards may be broken at any point by leaving some cards behind. 
 *    Example: If a pile from top down shows 4, 5, 6, 7, either the first one, two, or three cards 
 *    may be moved as a unit, but the 7 may not be moved until the covering three cards are removed. 
 *    When all face-up cards on a pile are removed, the next card below is turned face up and becomes 
 *    available.
 *    
 *    A movable unit of cards may be placed either in a space or on a card of the next-higher rank to the 
 *    bottom card of the unit, regardless of color or suit. Example: If the bottom card of a unit is the J, 
 *    it may be moved onto any one of the four queens.
 *    
 *    A king can be moved only onto a space. Alternatively, the spaces may be filled with any movable unit.
 *    
 *    When all possible or desired moves come to a standstill, the player deals another row of ten cards 
 *    face up. However, before such a deal may be made, all spaces must be filled. The final deal consists 
 *    of only four cards, which are placed on the first four piles.
 *   
 * @author markjones
 *
 */
@SuppressWarnings("serial")
public class SpiderSolitaire extends GraphicsProgram {

	private static final int INITIAL_WIDTH = 1000;
	private static final int INITIAL_HEIGHT = 600;
	private static final int MAX_ROWS=10;//the amount of total rows
	private static final int ROW_SPACE=20;//the space between rows on the mat
	private static final int CARD_SPACE=15;// the space between cards in the same pile
	private static final int START_DEAL=54;//the starting amount of cards dealt
	private static final int PACKS_NEEDED=5;//the starting amount of cards dealt
	private static final int CARDS_IN_PACKS=10;//the starting amount of cards dealt
	private static boolean goneYet=false;
	JButton newgamebtn;//the button to start a new game
	JComboBox<String> difficult;//the combo box to set the difficulty
	JLabel communicate=new JLabel("Welcome to Solitaire");//a label used to send messages to the player
	
	Pack startPack;//the starting pack
	
	ArrayList<GDeck> packs=new ArrayList<GDeck>();//an ArrayList of the packs made at the beginning of the game
	ArrayList<Deck> piles=new ArrayList<Deck>();//an ArrayList of the card piles 
	public static void main(String[] args) {
		new SpiderSolitaire().start(args);
	}
	
	/**
	 * Initializes the deck on the canvas and sets up event handling.
	 */
	@Override
	public void init() {
		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		setBackground(Color.GREEN.darker().darker());
		catchResizeEvents();//catches any changes in the screen size
		add(newgamebtn = new JButton("New Game"), SOUTH);// adds the "new game" button to the southern border 
		ActionListener buttonlistener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Give Up")) 
					communicate.setText("You lost, better luck next time");
				else
					communicate.setText("Go Get em Tiger");
	        		packs.clear();// clears the packs ArrayList
	        		piles.clear();//clears the piles ArrayList
	        		removeAll();//removes all objects from the canvas
	        		newgamebtn.setText("New Game");
	        		goneYet=false;
	        		//jlkasdfljkasdfjkl;
	        		setUp(getDifficulty());;//sets the game up depending on the currently selected difficulty
			}
		};
  
		newgamebtn.addActionListener(buttonlistener);//adds the listener to the new game button
		initDifficulty();//sets up the difficulty combobox
	    add(difficult, SOUTH);//adds the difficulty combobox to the southern border
		add(communicate,NORTH);//adds the jlabel to the northern border
		addMouseListeners();//adds the mouse listener
		setUp(getDifficulty());//sets up the game based on the currently selected difficulty
		
		
	}
	
	/**
	 * Sets up the handler for resize events.  This handler catches resize
	 * events, rescales the (GScalable) objects, and adjusts the locations
	 * of all GObjects. It does not adjust the font size for GLabels and such.
	 */
	private double wid, ht;  // width and height of the canvas (needed for resizing)
	private void catchResizeEvents() {
		wid = getWidth();
		ht = getHeight();

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				double scaleX = getWidth() / wid,  scaleY = getHeight() / ht;
				for (int i = 0; i < getElementCount(); i++) {
					Object obj = getElement(i);
					if (obj instanceof GObject) {
						if (obj instanceof GScalable) {
							((GScalable) obj).scale(scaleX, scaleY);
						}
						((GObject) obj).setLocation(((GObject) obj).getX()*scaleX, ((GObject) obj).getY()*scaleY);
					}
				}
				wid = getWidth(); ht = getHeight();
			}
		}); 		
	}
	
	private void setUp(Difficulty diff){
		startPack=new Pack(diff);//creates a pack based on the difficulty given
		startPack.shuffle();// shuffles the pack
		for(int x=0;x<MAX_ROWS;x++){//adds decks to the "piles" ArrayList up to the maximum number of desired rows
			piles.add(new Deck());
		}
		
		for(int x=0; x<START_DEAL; x++){//runs a for loop for however cards are desired to be dealt from the pack	
			if(x>=START_DEAL-MAX_ROWS)//flips the last card in every row
				startPack.get(0).turnFaceUp();
			piles.get(x%MAX_ROWS).add(startPack.deal());//adds the top card from the pack to a pile
		}
		
		for(int x=0; x<piles.size();x++){//runs for the amount of piles
			for(int i=0;i<piles.get(x).size();i++)//runs for the size of the deck in piles
			add((GObject)piles.get(x).get(i),(((GCard)piles.get(x).get(i)).cardWidth()+ROW_SPACE)*(x)+ROW_SPACE,CARD_SPACE*(i));//draws the card to the mat
		}
			
		makePacks();
		
	}
	private void initDifficulty() {
		difficult = new JComboBox<String>();
		difficult.addItem("Beginner");//adds the beginner option to the combo box
		difficult.addItem("Intermediate");//adds the intermediate option to the combo box
		difficult.addItem("Expert");//adds the expert option to the combo box
		difficult.setEditable(false);
		difficult.setSelectedItem("Intermediate");//sets the default value of the combo box to intermediate
	} 
	
	private Difficulty getDifficulty() {
		String name = (String) difficult.getSelectedItem();// sets name equal to the current value of the difficulty combo box
		if (name.equals("Beginner")) return Difficulty.BEGINNER;//returns beginner difficulty if the combo box is set to beginner
		if (name.equals("Intermediate")) return Difficulty.INTERMEDIATE;//returns intermediate difficulty if the combo box is set to intermediate
		if (name.equals("Expert")) return Difficulty.EXPERT;//returns expert difficulty if the combo box is set to expert
		
		return Difficulty.INTERMEDIATE;
	}
	
	private void makePacks(){
		for(int x=0;x<PACKS_NEEDED;x++){
			Deck arbs = new Deck();//an arbitrary deck
			for(int i=0;i<CARDS_IN_PACKS;i++){//runs for the 
				arbs.add(startPack.deal());
			}
			packs.add(new GDeck(arbs));
			add(packs.get(x), getWidth()-(x+2)*packs.get(x).getWidth()/2,getHeight()*.75);
		}
	}
	/**
	 * catches any mouse interactions with the game
	 */
	public void mouseClicked(MouseEvent e) {
		for (int x=0;x<packs.size();x++){
			if(packs.get(x).contains(new GPoint(e.getPoint()))){
				int dealnum=packs.get(packs.size()-1).getDeck().size();//sets dealnum equal to  
				for(int i=0; i<dealnum; i++){//runs a for loop for however cards are meant to be dealt
					packs.get(packs.size()-1).getDeck().get(0).turnFaceUp();	//turns the card about to be dealt face up
					piles.get(i).add(packs.get(packs.size()-1).getDeck().deal());//adds the top card from the deck selected to each pile
				}
				packs.remove(packs.size()-1);	
				goneYet=true;
				if(goneYet)
					newgamebtn.setText("Give Up");
			}	
		}
		removeAll();
		for(int x=0; x<piles.size();x++){
					for(int i=0;i<piles.get(x).size();i++)
					add((GObject)piles.get(x).get(i),(((GCard)piles.get(x).get(i)).cardWidth()+ROW_SPACE)*(x)+ROW_SPACE,CARD_SPACE*(i));//draws the card to the mat
				}
		for(int x=0;x<packs.size();x++){
		
					add(packs.get(x), getWidth()-(x+2)*packs.get(x).getWidth()/2,getHeight()*.75);
		}
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
}
