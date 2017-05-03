package APCS2016Proj06Solitaire;



public class Pack extends Deck{
	private final int PACKSIZE=104;
	
	/**
	 * creates a pack of cards based on the difficulty provided
	 * @param 
	 * diff the difficulty of the solitaire game
	 */
	public Pack(Difficulty diff){
	int decknumber=0;
		switch(diff){//switch statement using the difficulty
		case BEGINNER://the case for beginners
			decknumber=1;
			break;
		case INTERMEDIATE://the case for intermediate difficulty
			decknumber=2;
			break;
		case EXPERT://the case for expert difficulty
			decknumber=4;
		}
		
		for(int x=0;x<PACKSIZE;x++){//runs a for loop for the designated pack size
			this.add(new GCard(Rank.values()[x%Rank.values().length],Suit.values()[x%decknumber],false));
			//adds cards for each rank and as many suits as the difficulty requires
		}
		
		this.shuffle();//shuffles the deck
	}

	

}
