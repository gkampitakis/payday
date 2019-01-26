package model.position;

import model.player.Player;
import model.jackpot.Jackpot;

public class FamilyCasino extends Position {

    /**
     * <b>constructor</b>:Constructs a new FamilyCasino Buyer which inside calls
     * the constructor of position to initialise the inherited variables
     *
     * @param imageURL must be a valid url of the position's image
     * @param numb positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public FamilyCasino(String imageURL, int numb) {
        super(imageURL, numb);
    }

    /**
     * <b>transformer</b>: here are the actions of this position take place
     *  depending the diceNumber the player receives money of pays the jackpot
     * @param player the player who sits in this position
     * @param jackpot the jackpot of the game
     * @return
     */
    public boolean PositionAction(Player player, Jackpot jackpot) {
        int numb = player.getDice().getDiceNumb();
        boolean win = false;
        if (numb % 2 == 0) {
            player.setMoney(500);
            win = true;
        } else {
            if (player.getMoney() < 500) {
                player.setLoan(500 - player.getMoney());
                player.setMoney(-player.getMoney());
            } else {
                player.setMoney(-500);
            }
            jackpot.AddBalance(500);
        }
        return win;
    }
}
