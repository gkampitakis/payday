package model.cardstack;

import model.cards.*;
import java.util.ArrayList;

public class CardStack {

    private ArrayList<Card> RejectedStack;

    /**
     * <b>constructor</b>: Constructs an arraylist of Cards
     * Postcondition:constructs a new arraylist empty
     */
    public CardStack() {
        RejectedStack = new ArrayList<Card>();
    }

    /**
     * <b>Transformer<b>:returns the cards depending their king to each stack   
     * Precondition:the rejectedstack must have elements 
     * Postcondition:the cards
     * are distributed to A and B and the rejectedstack stays empty
     *
     * @param A a MessageCardStack
     * @param B a dealCardStack
     */
    public void returnCardsToStacks(MessageCardStack A, DealCardStack B) {
        while (!RejectedStack.isEmpty()) {
            Card C = RejectedStack.get(0);
            RejectedStack.remove(0);
            if (C instanceof MessageCard) {
                A.AddCardToStack((MessageCard) C);
            } else {
                B.AddCardToStack((DealCard) C);
            }
        }
        A.Shuffle_Stack();
        B.Shuffle_Stack();
    }

    /**
     * <b>transformer</b>:adds a new card to the list Postcondition: the list
     * has a new item and becomes bigger by one element which is added at the
     * start
     *
     * @param C the new card
     */
    public void RejectCard(Card C) {
        RejectedStack.add(C);
    }//takes the card from a stack and puts it here 

    /**
     * <b>Observer</b>:checks if the list is empty or not
     *
     * @return true if the list is empty else false
     */
    public boolean isEmpty() {
        return RejectedStack.isEmpty();
    }
}
