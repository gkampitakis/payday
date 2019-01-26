package controller;

import model.cards.*;
import model.cardstack.*;
import model.jackpot.Jackpot;
import model.player.Player;
import model.position.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MonthDialog;

/**
 * @version 1s
 * @author csd3142
 */
public class Controller {

    public Player A;
    public Player B;
    public Position tablo[];
    public MessageCardStack MessageStack;
    public DealCardStack DealStack;
    public CardStack RejectedStack;
    public Jackpot jackpot;
    private ClassLoader cldr;
    public boolean GameStarted;
    public int ScoreA;
    public int ScoreB;
    /**
     * <b>constructor<b>:Constructs a new controller and starts the game.<br />
     * <b>postcondition</b>:Constructs a new Controller with two players,a table
     * where the game is played the cards that are needed inside the game and
     * other sorts of variables in order to start the game.
     */
    public Controller() {//initialize players
        cldr = this.getClass().getClassLoader();
        jackpot = new Jackpot();
        MessageStack = new MessageCardStack();
        DealStack = new DealCardStack();
        RejectedStack = new CardStack();
        A=new Player("A");
        B=new Player("B");
        A.setOpponent(B);
        B.setOpponent(A);
    }
    /**
     * Initialises the game calling this functions
     */
    public void init_game()       
    {
        init_cardStacks();
        MonthDialog dial=new MonthDialog();
        setMonthsPlayed(Integer.parseInt(dial.getChoice()));
        Starting_PayDay();
    }

    /**
     * <b>transformer</b>Initialises the positions inside the tablo array
     * randomly
     * <p>
     * <b>Postcondition:</b> sets an array that holds all the positions of the
     * game tha are needed
     */
    public void init_table() {
        tablo = new Position[32];
        tablo[0] = new StartPosition("resources/images/start.png");
        tablo[31] = new PayDayPosition("resources/images/pay.png");

        int i = 1;
        while (i < 5) {
            tablo[i] = new MessagePosition("resources/images/mc1.png", true, 0);
            i++;
        }
        while (i < 9) {
            tablo[i] = new MessagePosition("resources/images/mc2.png", false, 0);
            i++;
        }
        while (i < 14) {
            tablo[i] = new DealPosition("resources/images/deal.png", 0);
            i++;
        }
        while (i < 16) {
            tablo[i] = new Sweepstakes("resources/images/sweep.png", 0);
            i++;
        }
        while (i < 19) {
            tablo[i] = new Lottery("resources/images/lottery.png", 0);
            i++;
        }
        while (i < 21) {
            tablo[i] = new Radio("resources/images/radio.png", 0);
            i++;
        }
        while (i < 27) {
            tablo[i] = new Buyer("resources/images/buyer.png", 0);
            i++;
        }
        while (i < 29) {
            tablo[i] = new FamilyCasino("resources/images/casino.png", 0);
            i++;
        }
        while (i < 31) {
            tablo[i] = new YardSale("resources/images/yard.png", 0);
            i++;
        }
        FixRandomPos(tablo);
    }
    /**
     *puts the positions in random positions inside the array except the start 
     * and payday 
     * precondition:An initialised array
     * postcondition:returns an array that except first and last 
     * all the other positions are random
     * @param a the array that holds the positions
     */
    private static void FixRandomPos(Position[] a) {
        int n = a.length - 1;
        for (int i = 1; i < n; i++) {
            int r = i + (int) (Math.random() * (n - i));
            Position tmp = a[i];
            a[i] = a[r];
            a[r] = tmp;
        }
        a[0].setName("Start");
        a[0].setpositionNumb(0);
        a[31].setName("Wednes.");
        a[31].setpositionNumb(31);
        for (int i = 1; i < n; i++) {
            a[i].setpositionNumb(i);
            switch (i % 7) {
                case 0:
                    a[i].setName("Sunday");
                    break;
                case 1:
                    a[i].setName("Monday");
                    break;
                case 2:
                    a[i].setName("Tuesday");
                    break;
                case 3:
                    a[i].setName("Wednes.");
                    break;
                case 4:
                    a[i].setName("Thursday");
                    break;
                case 5:
                    a[i].setName("Friday");
                    break;
                case 6:
                    a[i].setName("Saturday");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * <b>transformer</b>Initialises the cards and cardstacks
     * <p>
     * <b>Postcondition:</b> sets 3 stacks.the deal stack with the dealcards the
     * messagestack with the message cards and rejectedstack that is empty
     *
     */
    private void init_cardStacks() {
        //here call read function and initialise the cards and the cardstacks
        String dealCards[][] = readFile("resources/dealCards.csv", "Deal");
        String mailCards[][] = readFile("resources/mailCards.csv", "Mail");

        for (int i = 0; i < 20; i++) {
            int buyValue = Integer.parseInt(dealCards[i][3]);
            int sellValue = Integer.parseInt(dealCards[i][4]);
            String mess = dealCards[i][2];
            String img = dealCards[i][5];
            DealStack.AddCardToStack(new DealCard(buyValue, sellValue, mess, img));           
        }
        DealStack.Shuffle_Stack();
         for(int i=0;i<48;i++)
        {
            if(mailCards[i][1].equals("Αdvertisement")) MessageStack.AddCardToStack(new Advertisement(mailCards[i][2],mailCards[i][5]));
            else if(mailCards[i][1].equals("Bill")) MessageStack.AddCardToStack(new Bill(Integer.parseInt(mailCards[i][4]),mailCards[i][2],mailCards[i][5]));
            else if(mailCards[i][1].equals("Charity")) MessageStack.AddCardToStack(new Charity(Integer.parseInt(mailCards[i][4]),mailCards[i][2],mailCards[i][5]));
            else if(mailCards[i][1].equals("PayTheNeighbor")) MessageStack.AddCardToStack(new PayNeighbor(Integer.parseInt(mailCards[i][4]),mailCards[i][2],mailCards[i][5]));
            else if(mailCards[i][1].equals("MadMoney")) MessageStack.AddCardToStack(new MadMoney(Integer.parseInt(mailCards[i][4]),mailCards[i][2],mailCards[i][5]));
            else if(mailCards[i][1].equals("MoveToDealBuyer")) MessageStack.AddCardToStack(new MovetoDorB(mailCards[i][2],mailCards[i][5]));
        }   
        MessageStack.Shuffle_Stack();
    }

    /**
     * <b>transformer</b>asks at the start how many months wil be played
     * <p>
     * <b>Precondition:</b> the months can be from 1-3
     * <p>
     * <b>Postcondition:</b> each player monthremaining is equal to the month
     * @param months the number of months that both players agreed to play
     */
    public void setMonthsPlayed(int months) {
        A.setMonthsRemaining(months);
        B.setMonthsRemaining(months);
    }

    /**
     * <b>transformer</b>
     *give two numbers a and b  if a>b the player A starts to play
     * precondition:a and b must be different
     * postcondition: sets a player's turn
     * @param a the player's A dice number
     * @param b the player's B dice number
     */
    public void SetStartingPlayer(int a,int b) {
        if(a>b) A.setTurn(true);
        else B.setTurn(true);
    }

    /**
     * <b>transformer</b> initialises the payments of each player
     */
    public void Starting_PayDay() {
        A.setMoney(2500);
        B.setMoney(2500); 
    }//gives the starting money
    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> return true if tha game is over
     *
     * @return true if both players have not any months remaining to play
     */
    public boolean GameHasFinished() {
        return A.getFinished()&&B.getFinished();
    }
    /**
     * <b>Observer<b>
     * @return a string that is displayed at the end of the game 
     * depending the score of each player
     */
    public String Winner()
    {
        String s;
        ScoreA=A.getMoney()-A.getBills()-A.getLoan();
        ScoreB=B.getMoney()-B.getBills()-B.getLoan();
        if(ScoreA>ScoreB) s="Ο παιτης Α κέρδισε!Συγχαρητήρια θέτε να παίξετε κ αλλο παιχνίδι?";
        else if(ScoreB>ScoreA) s="O παίχτης Β κέρδισε!Συγχαρητήρια θέτε να παίξετε κ αλλο παιχνίδι?";
        else s="Ισοπαλία!Rematch όλα ή τίποτα?";
        return s;
    }
    /**
     * <b>Transformer<b>
     * @param player puts all the cards of the player in the rejected stack
     */
    public void ThrowLastCards(Player player)
    {
        if(player.getFinished())
        {
            while(!player.EmptyCards()){
                    this.RejectedStack.RejectCard(player.SellDealCard());
                }
        }
    }

    /**
     * <b>Postcondition:</b>returns a string describing the state of the game
     *
     * @return a string that is displayed in infobox that contains information
     * everytime an action is made
     */
    public String[] Infobox() {
        
        String arrayInfos[]=new String [2];
        if(A.getMonthsRemaining()>B.getMonthsRemaining())  arrayInfos[0]=A.getMonthsRemaining() + " Months remaining to play";
        else  arrayInfos[0]=B.getMonthsRemaining() + " Months remaining to play";
       
        if(A.getTurn()) arrayInfos[1]="Turn: A has turn";
        else if(B.getTurn())arrayInfos[1]="Turn: B has turn";
        
        return arrayInfos;
    }
    /**
     * <b>Transformer<b>
     * depending the choice of the player when he bets if he bets 
     * wins/loses money or nothig 
     * @param player the player that sits in sunday 
     * @param choice the choice of the player
     * @return true if he receives money
     */
    public boolean SundayMatch(Player player,int choice)
    {
        boolean win=false;
        if(choice!=3&&player.getMoney()<500)//daneio gia na tzogarei
        {
            player.setLoan(500);
            player.setMoney(500);
        }
        
        switch (choice) {
            case 0:
                if(player.getDice().getDiceNumb()==1||player.getDice().getDiceNumb()==2)
                {
                    player.setMoney(500);
                    win=true;
                }else {
                    player.setMoney(-500);
                    win=false;
                }   break;
            case 1:
                if(player.getDice().getDiceNumb()==3||player.getDice().getDiceNumb()==4) {
                    player.setMoney(500);
                    win= true;
                }
                else {
                    player.setMoney(-500);
                    win= false;
                }   break;
            case 2:
                if(player.getDice().getDiceNumb()==5||player.getDice().getDiceNumb()==6)
                {
                    player.setMoney(500);
                    win= true;
                }
                else{
                    player.setMoney(-500);
                    win= false;
                }   break;
            default:
                break;
        }
        return win;
    }
            
    /**
     * <b>transformer</b>
     * <b>Postcondition:</b> the jackpot's money are  added to player's balance
     * @param winner the player takes the money that jackpot has
     */
    public void WinJackpot(Player winner) {
        winner.setMoney(this.jackpot.WinBalance());
    }

    public String[][] readFile(String path, String type) {
        String[][] mailCards = new String[48][4];
        String[][] dealCards = new String[20][8];

        BufferedReader br = null;
        String sCurrentLine;
        try {
            String fullPath = cldr.getResource(path).getPath();
            br = new BufferedReader(new FileReader(fullPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        int splitCount = 0;
        HashMap<Integer, String> domainsMap = new HashMap<>();
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    mailCards[count++] = sCurrentLine.split(",");
                } else {

                    dealCards[count++] = sCurrentLine.split(",");
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (type.equals("Mail")) {
            return mailCards;
        } else {
            return dealCards;
        }
    }
}
   
