package APCS2016Proj06Solitaire;

import java.util.ArrayList;
import java.util.Collections;
public class Deck extends ArrayList<Card> {
	 /**
	  * removes the top card from the deck and returns it
	  * @return top card
	  */
	public Card deal(){
		Card placehold =this.get(0);
		this.remove(0);
		return placehold;
	}
	/**
	 * shuffles the deck
	 */
	public void shuffle(){
		Collections.shuffle(this);
	}
	
	
}
