package model.dice;

public class Dice {

    private int DiceNumb;//1-6
    private boolean rolled;//check if the player has rolled the dice

    /**
     * <b>constructor</b>:initialises the dicenumb in zero at start and rolled
     * into false
     * <b>postcondition</b>:Costructs a new dice which has two variables one tha
     * shows if the dice is rolled and one int that holds the dice's number
     */
    public Dice() {
        DiceNumb = 0;
        rolled = false;
    }

    /**
     * <b>transformer</b>:
     * <b>precondition</b>:if the dice in a turn is rolled we put the rolled
     * value equal to true else false
     *
     * @param a sets variable roll to a
     */
    public void setRolled(boolean a) {
        rolled = a;
    }

    /**
     * <b>Observer</b>:Gives us access to rolled variable
     *
     * @return the value of the rolled variable
     */
    public boolean getRolled() {
        return rolled;
    }

    /**
     * <b>Observer</b>:Gives us access to DiceNumb value
     *
     * @return the value of DiceNumb
     */
    public int getDiceNumb() {
        return DiceNumb;
    }//just observe the DiceNumb not generate

    /**
     * <b>transformer</b>:produces a random number from one to six and saves it
     * to DiceNumb
     * <p>
     * <b>Postcondition:</b>the number that is returned must be between 1 and 6
     *
     * @return the random number
     */
    public int roll() {
        int roll =1 + (int) (Math.random() * 6);
        rolled=true;
        DiceNumb = roll;
        return roll;
    }//produces a random number and saves it 
}
