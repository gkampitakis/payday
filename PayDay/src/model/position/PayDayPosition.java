package model.position;

import model.player.Player;

public class PayDayPosition extends Position{
	/**
	 *  <b>constructor</b>:Constructs a new position Buyer which inside calls
	 *  the constructor of position to initialise the inherited variables and the position numb 
	 *  is 31
	 * @param imageURL must be a valid url of the position's image
	 */
	public PayDayPosition(String imageURL){
		super(imageURL,31);
	}
	/**
	 * <b>transformer</b>: here are the actions of this position take place 
	 *postcondition:do all the neccessary actions of the payday
         * @param A the player that sits in this position
         * @param choice if equals to 0 1 player pays the loan or part of it 
	 */
	public void PositionAction(Player A,int choice){
            A.setMonthsRemaining(A.getMonthsRemaining()-1);
            A.setMoney(2500);
            //TAXES 
            int tax=A.getLoan()/10;
            if(tax>A.getMoney())
            {
                A.setLoan(tax-A.getMoney());
                A.setMoney(-A.getMoney());
            }else A.setMoney(-tax);
            //PAY BILLS 
         
            if(A.getBills()>A.getMoney())
            {
                A.setLoan(A.getBills()-A.getMoney());
                A.setMoney(-A.getMoney());
                A.SetBills(-A.getBills());
            }else
            {
                A.setMoney(-A.getBills());
                A.SetBills(-A.getBills());
            }
            
            //Loan Payments
            if(choice==0){
                if(A.getLoan()>A.getMoney())
                {
                    A.setLoan(-A.getMoney());
                    A.setMoney(-A.getMoney());
                    
                }else
                {
                   A.setMoney(-A.getLoan());
                   A.setLoan(-A.getLoan());
                }
            }else if(choice==1)
            {
                if(A.getLoan()/2>A.getMoney())
                {
                    A.setLoan(-A.getMoney()/2);
                    A.setMoney(-A.getMoney()/2);
                }else
                {
                   A.setMoney(-A.getLoan()/2);
                   A.setLoan(-A.getLoan()/2);
                }
            }
            
            if(A.getMonthsRemaining()<=0) 
            {
                A.setFinished(true);
            }
            if(!A.getFinished()) A.setPosition(-31);
        }
}
