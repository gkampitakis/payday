package model.cardstack;

import java.util.*;
import model.cards.MessageCard;

public class MessageCardStack {

    private ArrayList<MessageCard> Stack;//vector i arraylist gia na kanoume tin anaparastasi stoibas kartwn

    /**
     * <b>constructor</b>: Constructs an arraylist of MessageCards
     * Postcondition:constructs a new arraylist empty
     */
    public MessageCardStack() {
        Stack = new ArrayList<MessageCard>();
    }//declare an arraylist

    /**
     * <b>transformer</b>:shuffles the cards by putting them in random places
     * inside the list Precondition:the list must have all elements
     */
    public void Shuffle_Stack() {
        long seed=System.nanoTime();
        Collections.shuffle(Stack,new Random(seed));
    }//shuffle the cards

    /**
     * <b>Observer</b>:checks if the list is empty or not
     *
     * @return true if the list is empty else false
     */
    public boolean isEmpty() {
        if (Stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <b>transformer</b>:adds a new card to the list Postcondition: the list
     * has a new item and becomes bigger by one element which is added at the
     * start
     *
     * @param C the new card
     */
    public void AddCardToStack(MessageCard C) {
        Stack.add(C);
    }//we add cards at the end 

    /**
     * <b>transformer</b>:takes a card from the list Postcondition: the list has
     * an item taken and becomes smaller by one element which is taken from the
     * start
     *
     * @return the card
     */
    public MessageCard TakeCard() {
        MessageCard C = Stack.get(0);
        Stack.remove(0);
        return C;
    }//and remove cards from the start  
}
