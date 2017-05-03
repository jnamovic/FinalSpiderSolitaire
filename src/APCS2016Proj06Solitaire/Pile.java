package APCS2016Proj06Solitaire;

import java.util.ArrayList;

import acm.graphics.GCompound;

public class Pile extends GCompound {
	private ArrayList<Card> cardsinpile;
	private final int YSPACE=10;
	public Pile( ArrayList<Card> cards){
		cardsinpile= cards;
		for(int x=0;x<cards.size();x++){
			add((GCard)cards.get(x),0,YSPACE*x);
		}
	}
}
