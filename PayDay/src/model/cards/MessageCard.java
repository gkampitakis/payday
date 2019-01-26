package model.cards;

import model.player.Player;

public abstract class MessageCard extends Card{
	/**
	 *  <b>constructor</b>:Constructs a new MessageCard Card
	 * @param money the money that the card costs
	 * @param message the message that it prints
	 * @param imageURL the URL of the card's image
	 * <b>postcondition</b>:creates a new card with a money a displayed message and an image
	 * and sets the choice variable that inherits from card to false as the messageCard dont have a choice 	
	 */
	public MessageCard(int money,String message,String imageURL)
	{
		super(money,message,imageURL);
	}
        /**
         * DOES NOTHING JUST HERE IN ORDER THE OTHERS CLASSES TO INHERIT
         * @param player 
         */
        @Override
	public void CardAction(Player player){};
}
