package model.cards;

import model.player.Player;
import model.position.*;

public class MovetoDorB extends MessageCard {

    /**
     * <b>constructor</b>:Constructs a new MovetoDorB Card
     *
     * @param message the message that it prints
     * @param imageURL the URL of the card's image calls the parent class with
     * money variable equal to zero as it doesnt have any fine to pay
     */
    public MovetoDorB(String message, String imageURL) {
        super(0, message, imageURL);
    }

    /**
     *
     * @param player the player that draws the card
     * @param array the array that hodlds the positions of the board
     * @return the position that the player must go
     * postcondition:returns a number to move if there is a deal or a buyer position
     * ahead else returns the same position that the player is now 
     */
    public int CardAction(Player player, Position array[]) {
        for (int i = player.getPosition(); i < array.length; i++) {
            if (array[i] instanceof Buyer || array[i] instanceof DealPosition) {
                return i;
            }
        }
        return player.getPosition();
    }
}
