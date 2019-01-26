package model.position;

import model.player.Player;
import model.cards.DealCard;

public class YardSale extends Position {

    /**
     * <b>constructor</b>:Constructs a new position YardSale which inside calls
     * the constructor of position to initialise the inherited variables
     *
     * @param imageURL must be a valid url of the position's image
     * @param numb positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public YardSale(String imageURL, int numb) {
        super(imageURL, numb);
    }

    /**
     * <b>transformer</b>: here are the actions of this position take place
     *the player takes a card by paying the dice's numb*100
     * @param player the player that sits in this position
     * @param numb the number of the dice
     * @param C the dealCard that players buys 
     */
    public void PositionAction(Player player, int numb, DealCard C) {
        if (numb * 100 > player.getMoney()) {
            player.setLoan(numb * 100 - player.getMoney());
            player.setMoney(-player.getMoney());
        } else {
            player.setMoney(-numb * 100);
        }
        player.KeepDealCard(C);
    }
}
