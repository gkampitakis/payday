package model.position;

import model.player.Player;

public class Sweepstakes extends Position {

    /**
     * <b>constructor</b>:Constructs a new position Buyer which inside calls the
     * constructor of position to initialise the inherited variables
     *
     * @param imageURL must be a valid url of the position's image
     * @param numb positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public Sweepstakes(String imageURL, int numb) {
        super(imageURL, numb);
    }

    /**
     * <b>transformer</b>: here are the actions of this position take place
     *the player that sits in this position wins the dice's number*1000
     * @param player the player in this position
     * @param numb the dice's number
     */
    public void PositionAction(Player player, int numb) {
        player.setMoney(numb * 1000);
    }
}
