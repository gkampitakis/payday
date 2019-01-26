package model.position;

public class StartPosition extends Position{
	/**
	 *  <b>constructor</b>:Constructs a new position Buyer which inside calls
	 *  the constructor of position to initialise the inherited variables and the position numb 
	 *  is 0
	 * @param imageURL must be a valid url of the position's image
	 */
	public StartPosition(String imageURL){
		super(imageURL,0);
	}
}
