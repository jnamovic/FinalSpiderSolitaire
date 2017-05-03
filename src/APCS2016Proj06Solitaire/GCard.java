package APCS2016Proj06Solitaire;
import acm.graphics.GCompound;
import acm.graphics.GImage;

/** 
 * Models a (graphical) playing card.  This model includes not only the 
 * rank and suit, but also the graphical representation of the card.
 * 
 * @author Dr. Mark A. Jones
 */
@SuppressWarnings("serial")
public class GCard extends GCompound implements Card {
	
	/* private class variables */
	private static final String IMAGE_LOCATION = "APCSimages/";
	private static final GImage BACK_IMAGE = new GImage("back-blue-75-1.png");
	
	/* private instance variables */
	private final Rank rank;
	private final Suit suit;

	private final GImage frontImage;
	private final GImage backImage;
	
	/** Create a playing card with a given rank and suit.
	 * @param rank    the rank
	 * @param suit    the suit
	 * @param faceUp  true if card should be initially face up, false otherwise
	 */
	public GCard(Rank rank, Suit suit, boolean faceUp) {
		super();
		this.rank = rank;
		this.suit = suit;
		add(backImage = new GImage("back-blue-75-1.png"));
		add(frontImage = new GImage( suit + "-" + rank + "-75.png"));
		if (faceUp)
			turnFaceUp();
		else
			turnFaceDown();
	}
	
	public GCard(Rank rank, Suit suit) {
		this(rank, suit, false);
	}
	
	/** Get the rank of the card.
	 * @return   the rank
	 */
	public Rank getRank() {
		return rank;
	}
	
	/** Get the suit of the card.
	 * @return   the suit
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/** A card as a string.
	 * @return  the card as a string
	 */
	public String toString() {
		return isFaceUp() ? rank + " of " + suit : "? of ?";
	}
	
	/** Is the card face up?
	 * @return  true if the card is face up
	 */
	public boolean isFaceUp() {
		return frontImage.isVisible();
	}
	
	/** Turn the card face up.
	 * 
	 * @return the card
	 */
	public GCard turnFaceUp() {
		backImage.setVisible(false);
		frontImage.setVisible(true);
		return this;
	}
	
	/** Turn the card face down.
	 * 
	 * @return the card
	 */
	public GCard turnFaceDown() {
		backImage.setVisible(true);
		frontImage.setVisible(false);
		return this;
	}
	
	/** Flip the card over.
	 * 
	 * @return the card
	 */
	public GCard flipOver() {
		backImage.setVisible(!backImage.isVisible());
		frontImage.setVisible(!frontImage.isVisible());
		return this;
	}
	
	/** Get the width for any playing card.
	 * @return  the width
	 */
	public static double cardWidth() {
		return BACK_IMAGE.getWidth();
	}
	
	/** Get the height for any playing card.
	 * @return  the height
	 */
	public static double cardHeight() {
		return BACK_IMAGE.getHeight();
	}
	
	/** Get the back image for any playing card.
	 * @return  the back image
	 */
	public static GImage getBackImage() {
		return BACK_IMAGE;
	}
}
