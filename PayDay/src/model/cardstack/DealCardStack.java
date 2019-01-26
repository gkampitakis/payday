package model.cardstack;

import java.util.*;
import model.cards.*;

public class DealCardStack {
	private ArrayList<DealCard> Stack;
	/**
	 *  <b>constructor</b>: Constructs an arraylist of DealCards
	 *  Postcondition:constructs a new arraylist empty
	 */
	public DealCardStack(){
            Stack=new ArrayList<DealCard>();
        }//declare an arraylist
	/**
	 * <b>transformer</b>:shuffles the cards by putting them in random places inside the list
	 * Precondition:the list must have all elements
	 */
	public void Shuffle_Stack(){
          long seed=System.nanoTime();
        Collections.shuffle(Stack,new Random(seed));
        }//shuffle the cards
	/**
	 * <b>Observer</b>:checks if the list is empty or not 
	 * @return true if the list is empty else false
	 */
	public boolean isEmpty(){
            return Stack.isEmpty();
        }
	/**
	 *  <b>transformer</b>:adds a new card to the list
	 *  Postcondition: the list has a new item and becomes bigger by one element which is added at the start
	 * @param C the new card
	 */
	public void AddCardToStack(DealCard C){
            Stack.add(C);
        }//we add cards at the end 
	/**
	 *  <b>transformer</b>:takes a card from the list
	 *  Postcondition: the list has an item taken and becomes smaller by one element which is taken from the start
	 * @return the card
	 */
	public DealCard TakeCard(){
            DealCard C=Stack.get(0);
            Stack.remove(0);
            return C;
        }//and remove cards from the start   
}
