package APCS2016Proj06Solitaire;

import java.util.Arrays;


import acm.graphics.GCompound;
import acm.graphics.GImage;

public class GDeck extends GCompound {
	 private Deck cards;
	 private final GImage back;
	 
	 /**
	  * creates a GObject instance of a deck
	  * @param startdeck
	  */
	 public GDeck(Deck startdeck){
		 cards=startdeck;
		 add(back=new GImage(((GCard)startdeck.get(0)).getBackImage().getImage()));
	 }
	
	 /**
	  * returns the deck used by the GDeck
	  * @return
	  */
	 public Deck getDeck(){
		 return cards;
	 }
	 
	 /**
	  * returns the image used by the back of the deck
	  * @return
	  */
	 public GImage getBack(){
 		 return back;
	 }
	
}
