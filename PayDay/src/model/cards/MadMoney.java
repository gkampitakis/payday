package model.cards;


import model.player.Player;

public class MadMoney extends MessageCard{
	/**
	 *  <b>constructor</b>:Constructs a new MadMoney Card
	 * @param money the money that the card costs
	 * @param message the message that it prints
	 * @param imageURL the URL of the card's image
	 * <b>postcondition</b>:creates a new card with a money a displayed message and an image
	 */
	public MadMoney(int money,String message,String imageURL){
		super(money,message,imageURL);
	}
	/**
	 *  <b>postcondition</b>:transfers money from oppoent to player
         * @param player the player that draws the card and receives the money
         * postcondition:transfers money between players
	 */
        @Override
	public void CardAction(Player player){
            if(this.getMoney()>player.getOpponent().getMoney())
            {
                player.getOpponent().setLoan(this.getMoney()-player.getOpponent().getMoney());
                player.getOpponent().setMoney(this.getMoney()-player.getOpponent().getMoney());
            }
            player.getOpponent().setMoney(-this.getMoney());
            player.setMoney(this.getMoney());
        }
}
