package APCS2016Proj06Solitaire;

public enum Suit {SPADE, HEART, CLUB, DIAMOND;
	/**
	 * Returns a string of the name of the suit
	 * @return the name of the suit
	 */
	public String toString(){
		switch(name()){
		case"SPADE": return "spades";
		case"HEART": return "hearts";
		case"CLUB": return "clubs";
		case"DIAMOND": return "diamonds";
		}
		return null;
	}

}
