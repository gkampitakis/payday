package model.jackpot;

public class Jackpot {

    private int balance;
    /**
     * <b>constructor</b>:Constructs a new Jackpot instance
     * <b>postcondition</b>:initialises the balance value equal to zero
     */
    public Jackpot() {
        this.balance = 0;
    }//initialises balance to zero

    /**
     * <b>transformer</b>:adds money to the balance
     * <b>Precondition</b>:money must be bigger than zero
     * <b>Postcondition</b>:the new balance value is greater than the old one
     *
     * @param money>0
     */
    public void AddBalance(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException();
        }
        balance = balance + money;
    }

    /**
     * <b>transformer</b>:if a player wins the money takes the money that
     * balance contains and the new value of balance turns to zero
     *
     * @return the balance value
     */
    public int WinBalance() {
        int tmp = balance;
        balance = 0;
        return tmp;
    }//and makes balance zero

    /**
     * <b>Observer</b>:
     *
     * @return the value of balance
     */
    public int getBalance() {
        return balance;
    }//just shows the balance

    public String toString() {
        return balance + "";
    }
}
