package model.position;

public class Radio extends Position{
	/**
	 *  <b>constructor</b>:Constructs a new position Buyer which inside calls
	 *  the constructor of position to initialise the inherited variables
	 * @param imageURL must be a valid url of the position's image
	 * @param numb positions number which must be from 1 to 30 and not be taken from another position
	 */
	public Radio(String imageURL,int numb){
		super(imageURL,numb);
	}
	
} 
