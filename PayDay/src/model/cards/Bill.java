package model.cards;

import model.player.Player;

public class Bill extends MessageCard{
	/**
	 *  <b>constructor</b>:Constructs a new Bill Card
	 * @param money the money that the card costs
	 * @param message the message that it prints
	 * @param imageURL the URL of the card's image
	 * <b>postcondition</b>:creates a new card with a money a displayed message and an image
	 */
	public Bill(int money,String message,String imageURL){
		super(money,message,imageURL);
	}
	/**
	 * <b>postcondition</b>:adds the money 
	 * to the bill balance of the player and pays it at the end 
         * @param player the player who draws the card
	 */
        @Override
	public void CardAction(Player player){
            player.SetBills(this.getMoney());
        }
}
