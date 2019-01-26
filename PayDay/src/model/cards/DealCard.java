package model.cards;

import model.player.Player;

public class DealCard extends Card implements Comparable<DealCard>{
	//vars final we can't change them again
	private final int sellValue; 
  /**
   *   <b>constructor</b>:Constructs a new DealCard
   * @param buyValue the cost of the card that player must pay to buy
   * @param sellValue the cost of the card that player takes if he sells it
   * @param Message the message that it prints
   * @param imageURL the path of the image
   * <b>postcondition</b>:creates a new card with sell and buy value a displayed message and an image
   * 
   */
	public DealCard(int buyValue,int sellValue,String Message,String imageURL){
		super(buyValue,Message,imageURL);
		this.sellValue=sellValue;
	}
	/**
	 *  <b>Accessor</b>:returns the card's sell value
	 * @return sellvalue
	 */
	public int getSellValue(){return sellValue;}
        
        /**
         * Its used in order to sort the DealCards of the player
         * @return the profit that a player makes from this card
         */
        public int profit(){
            return sellValue-super.getMoney();
        }
	/** 
	 * <b>postcondition</b>:adds a card to players cards if he buys it or leaves it to 
	 * the rejected stack
         * @param player
	 */
        @Override
	public void CardAction(Player player){
            player.setMoney(-this.getMoney());
            player.KeepDealCard(this);   
        }
        
    /**
     *
     * @param C Each card's sorting value
     * @return the value that the cards are sorted with
     */
    @Override
        public int compareTo(DealCard C) {
            return C.profit()-this.profit();
        }
}
