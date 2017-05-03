package APCS2016Proj06Solitaire;

public enum Rank {
	DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	/**
	 * returns the name of the rank formated as a string
	 * @return the name of the rank
	 */
	public String toString(){
		switch(name()){
		case"DEUCE": return "2";
		case"THREE": return "3";
		case"FOUR": return "4";
		case"FIVE": return "5";
		case"SIX": return "6";
		case"SEVEN": return "7";
		case"EIGHT": return "8";
		case"NINE": return "9";
		case"TEN": return "10";
		case"JACK": return "j";
		case"QUEEN": return "q";
		case"KING": return "k";
		case"ACE": return "a";
		}
		return "";
	}

}
