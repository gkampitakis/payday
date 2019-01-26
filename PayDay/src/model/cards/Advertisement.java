package model.cards;

public class Advertisement extends MessageCard{
	/**
	 * <b>constructor</b>:Constructs a new Advertisement Card
	 * @param message the message that it prints 
	 * @param imageURL the URL of the card's image
	 * calls the parent class with money variable equal to zero as it doesnt have 
	 * any fine to pay
	 */
	public Advertisement(String message,String imageURL) {
		super(0, message, imageURL);
	}
}
