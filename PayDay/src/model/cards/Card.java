package model.cards;

import model.player.Player;

public abstract class Card {
	private final String message;
	private final int money;
	private final String imageURL;
	/**
	 * <b>constructor</b>:Constructs a new Card
	 * @param money the money that the card costs
	 * @param message the message that it prints
	 * @param imageURL the URL of the card's image
	 * <b>postcondition</b>:creates a new card with a money a displayed message and an image
	 */
	public Card(int money,String message,String imageURL)
	{
                if(money<0) throw new IllegalArgumentException();
		this.message=message;
		this.money=money;
		this.imageURL=imageURL;
	}
	/**
	 * <b>Accessor</b>:
	 * @return the message 
	 */
	public String getMessage(){return message;}
	/**
         *  <b>Accessor</b>:
         * @return the cost of the card 
         */
	public int getMoney(){return money;}
	/**
	 * <b>Accessor</b>:returns the image's path
	 * @return imageURL
	 */
	public String getImageURL(){return imageURL;}
	/**
	 * its an abstract function that every class that inherits Card must
	 * have this function 
	 */
	public abstract void CardAction(Player player);
}
