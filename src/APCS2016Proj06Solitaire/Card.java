package APCS2016Proj06Solitaire;

/**
 * Standard requirements for implementations of playing cards.
 * @author markjones
 *
 */
public interface Card {
	/** 
	 * Get the rank of the card.
	 * @return   the rank
	 */
	Rank getRank();

	/** 
	 * Get the suit of the card.
	 * @return   the suit
	 */
	Suit getSuit();

	/** 
	 * Is the card face up?
	 * @return  true if the card is face up
	 */
	boolean isFaceUp();

	/** 
	 * Turn the card face up.
	 * @return the card
	 */
	Card turnFaceUp();

	/**
	 * Turn the card face down.
	 * @return the card
	 */
	Card turnFaceDown();

	/** 
	 * Flip the card over.
	 * @return the card
	 */
	Card flipOver();
}
