package model.player;

import model.cards.DealCard;
import model.dice.Dice;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private int Money;
    private int loan;
    private int bills;
    private ArrayList<DealCard> myDealCards;
    private final String name;
    private int MonthsRemaining;
    private final Dice myDice;
    private boolean FinishedPlaying;
    private boolean turn;
    private boolean PendingAction;
    private int CurrentPosition;
    private Player Opponent;
    /**
     * <b>Constructor<b> Constructs a new instance of a player and initializes all the values
     * @param name Initialises the name of the player
     */
    public Player(String name) {
        Money = 0;
        loan = 0;
        bills = 0;
        myDealCards = new ArrayList<>();
        this.name = name;
        turn = false;
        FinishedPlaying = false;
        PendingAction = false;
        myDice = new Dice();
        CurrentPosition = 0;
    }
    /**
     * <b>Transformer<b> sets the opponent of the player
     * @param A the opponent
     */
    public void setOpponent(Player A) {
        this.Opponent = A;
    }
    /**
     * <b>Observer<b>
     * @return if the stack with cards of the player is empty
     */
    public boolean EmptyCards() {
        return myDealCards.isEmpty();
    }
    /**
     * <b>Transformer<b> changes the pending state of the player 
     * @param a true or false depending if he has an action to do or not 
     */
    public void setPendingAction(boolean a) {
        this.PendingAction = a;
    }
    /**
     * <b>Observer<b> 
     * @return the pending state of the player
     */
    public boolean getPendingAction() {
        return this.PendingAction;
    }
    /**
     * <b>Accessor<b>
     * @return the opponent of this player
     */
    public Player getOpponent() {
        return this.Opponent;
    }
    /**
     * <b>Accessor<b>
     * @return the money this player has
     */
    public int getMoney() {
        return Money;
    }
    /**
     * <b>Accessor<b>
     * @return the loan that this player has taken
     */
    public int getLoan() {
        return loan;
    }
    /**
     * <b>Accessor<b>
     * @return the bills that this player has
     */
    public int getBills() {
        return bills;
    }
    /**
     * <b>Accessor<b>
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
    /**
     * <b>Transformer<b> adds more money to the loan you ve taken
     * postcondition:changes players loan
     * @param money new loan that we add to the old one 
     */
    public void setLoan(int money) {
        loan = money + loan;
    }
    /**
     * <b>Transformer<b> adds more money to the money you already have
     * postcondition:changes players money
     * @param money the money you add 
     */ 
    public void setMoney(int money) {
        Money = money + Money;
    }
    /**
     * <b>Transformer<b> adds more bills to the bills you already have
     * postcondition:changes players bills
     * @param bills the bills you a dd
     */
    public void SetBills(int bills) {
        this.bills = bills + this.bills;
    }
    /**
     * adds a new card to players cardlist and sorts them depending the profit
     * postcondition:adds an element in the arraylist 
     * @param C the new card u take 
     */
    public void KeepDealCard(DealCard C) {
        this.myDealCards.add(C);
        Collections.sort(myDealCards);
    }//keep the list sorted depending the profit
    /**
     * postcondition:excludes one card from the list 
     * @return the first card of the stack
     */
    public DealCard SellDealCard() {
        DealCard c=myDealCards.get(0);
        myDealCards.remove(0);
        return  c;
    }
    /**
     * <b>Transformer<b>changes the position of the player
     * postcondition:changes players position
     * @param NumberofSteps the steps that player moves
     */
    public void setPosition(int NumberofSteps) {
        if (NumberofSteps + CurrentPosition > 31) {
            CurrentPosition = 31;
        } else {
            CurrentPosition = NumberofSteps + CurrentPosition;
        }
    }
    /**
     * <b>Accessor<b>
     * @return the position of the player
     */
    public int getPosition() {
        return CurrentPosition;
    }
    /**
     * <b>Accessor<b>
     * @return the dice of the player
     */
    public Dice getDice() {
        return myDice;
    }
    /**
     * <b>Transformer<b> 
     * postcondition:changes player remaining months
     * @param months the months that the player has to play
     */
    public void setMonthsRemaining(int months) {
        this.MonthsRemaining = months;
    }
    /**
     * <b>Accessor<b>
     * @return the months that the player still has to play
     */
    public int getMonthsRemaining() {
        return this.MonthsRemaining;
    }
    /**
     * <b>Transformer<b>
     * @param turn sets the turn true or false depending if its player's turn or not 
     */
    public void setTurn(boolean turn) {
        if (this.FinishedPlaying) {
            this.turn = false;
        } else {
            this.turn = turn;
        }
    }
    /**
     * <b>Accessor<b>
     * @return if its player's turn
     */
    public boolean getTurn() {
        return this.turn;
    }
    /**
     * <b>Transformer<b>
     * @param a sets if the player has finished playing or not
     */
    public void setFinished(boolean a) {
        FinishedPlaying = a;
    }
    /**
     * <b>Accessor<b>
     * @return if has finished 
     */
    public boolean getFinished() {
        return this.FinishedPlaying;
    }
}
