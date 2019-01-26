package model.position;

public class DealPosition extends Position{
	/**
	 *  <b>constructor</b>:Constructs a new DealPosition Buyer which inside calls
	 *  the constructor of position to initialise the inherited variables
	 * @param imageURL must be a valid url of the position's image
	 * @param numb positions number which must be from 1 to 30 and not be taken from another position
	 */
	public DealPosition(String imageURL,int numb){
		super(imageURL,numb);
	}
}
