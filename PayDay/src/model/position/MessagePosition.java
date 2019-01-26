package model.position;

public class MessagePosition extends Position{
	private final boolean OneCardDraw;
	/**
	 *<b>constructor</b>:Constructs a new message position 
	 *<b>Postcondition</b>:constructs a new message position with image a position number 
	 *and the option of oneCard draw true or false depending on how many cards we true(1 true-2 false)
         * @param imageURL the path of the position's image
         * @param OneCard if it's a position that we must draw one card
         * @param numb the number of the position
	 */
	public MessagePosition(String imageURL,boolean OneCard,int numb){
		super(imageURL,numb);
		this.OneCardDraw=OneCard;
	}
	/**
	 * <b>Accessor</b>:
	 * returns how many cards the player draws
	 * @return OneCardDraw
	 */
	public boolean getOneCardDraw(){return OneCardDraw;}
}
