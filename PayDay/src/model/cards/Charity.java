package model.cards;
import model.jackpot.Jackpot;
import model.player.Player;

public class Charity extends MessageCard{
	/**
	 *  <b>constructor</b>:Constructs a new Charity Card
	 * @param money the money that the card costs
	 * @param message the message that it prints
	 * @param imageURL the URL of the card's image
         * <b>precondition</b>:money must be positive value and the imageURL must be a valid path 
	 * <b>postcondition</b>:creates a new card with a money a displayed message and an image
	 */
	public Charity(int money,String message,String imageURL)
	{
		super(money,message,imageURL);
	}
	/**
	 *  <b>postcondition</b>:transfers money from player to the jackpot
         * @param player the player who draws the card and pays the fine
	 * @param jackpot jacpot where the money go
	 */
	public void CardAction(Player player,Jackpot jackpot){
            if(this.getMoney()>player.getMoney())//ean den exei lefta na plirosei to poso 
            {//prostithetai daneio kai to plironei 
                player.setLoan(this.getMoney()-player.getMoney());
                player.setMoney(this.getMoney()-player.getMoney());
            }
            player.setMoney(-this.getMoney());
            jackpot.AddBalance(this.getMoney());
        }
}
