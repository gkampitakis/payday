package model.cards;

import model.player.Player;

public class PayNeighbor extends MessageCard{
	/**
	 *  <b>constructor</b>:Constructs a new PayNeighbotr Card
	 * @param money the money that the card costs
	 * @param message the message that it prints
	 * @param imageURL the URL of the card's image
         * <b>precondition</b>:money must be positive value and the imageURL must be a valid path 
	 * <b>postcondition</b>:creates a new card with a money a displayed message and an image
	 */
	public PayNeighbor(int money,String message,String imageURL){
		super(money,message,imageURL);
	}
	/**
	 *  <b>postcondition</b>:transfers money from player A to player B 
         * @param player the player who draws the card
         * postcondition:transfers money between players
	 */
        @Override
	public void CardAction(Player player){
            if(this.getMoney()>player.getMoney())
            {//ean den exei lefta pairnei daneio kai kseplironei
                player.setLoan(this.getMoney()-player.getMoney());
                player.setMoney(this.getMoney()-player.getMoney());
            }
            player.setMoney(-this.getMoney());
            player.getOpponent().setMoney(this.getMoney());
        }
}
