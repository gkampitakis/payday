package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import model.dice.Dice;
import model.player.*;
import model.cards.*;
import model.position.*;
import java.util.ArrayList;

public class GraphicUI extends JFrame {
/**
 * 
 */
    Controller game;
    int LotterychoiceA, LotterychoiceB;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) Math.round(screenSize.getWidth()) - 200;
    private final int height = (int) Math.round(screenSize.getHeight()) - 200;
    private int counter = 0;
    private boolean RadioPos = false;
    private boolean LotteryPos = false;

    private JLayeredPaneExtension basic_panel;
    private URL imageURL;
    private ClassLoader cldr;
    private Image image;
    private JButton DealCards, MessageCards, DealCardsA, DealCardsB, GetLoanA, GetLoanB, EndTurnA, EndTurnB, DiceA, DiceB;
    private JDesktopPane InfoBox, tablo, playerA, playerB, jackPotPanel;
    private JLabel PayDayImage, PlayerNameA, PlayerNameB, jackPotLabel;
    private JTextField MoneyAText, MoneyBText, LoanAText, LoanBText, BillsAText, BillsBText, jackPotText, info, turn, monthsleft, command;
    private JTextField temp1;
    private JMenu fileMenu;
    private JDesktopPane[] position;
    JLayeredPaneExtension pawn_position[];
    ImageIcon ins;
/**
 *  Initialises some buttons and pictures of the game 
 */
    public GraphicUI() {
        game = new Controller();
        cldr = this.getClass().getClassLoader();
        playerA = new JDesktopPane();
        playerB = new JDesktopPane();

        tablo = new JDesktopPane();
        position = new JDesktopPane[35];
        pawn_position = new JLayeredPaneExtension[32];
        jackPotPanel = new JDesktopPane();
        jackPotText = new JTextField();
        jackPotLabel = new JLabel();

        InfoBox = new JDesktopPane();

        MoneyAText = new JTextField();
        MoneyBText = new JTextField();
        LoanAText = new JTextField();
        LoanBText = new JTextField();
        BillsAText = new JTextField();
        BillsBText = new JTextField();
        PayDayImage = new JLabel();
        PlayerNameA = new JLabel("Player A");
        PlayerNameA.setFont(new Font(null, Font.BOLD, 20));
        PlayerNameB = new JLabel("Player B");
        PlayerNameB.setFont(new Font(null, Font.BOLD, 20));

        info = new JTextField("Info Box");
        info.setFont(new Font(null, Font.BOLD, 15));
        turn = new JTextField();
        monthsleft = new JTextField();
        command = new JTextField();

        DealCards = new JButton();
        MessageCards = new JButton();
        GetLoanA = new JButton();
        EndTurnA = new JButton();
        GetLoanB = new JButton();
        EndTurnB = new JButton();
        DealCardsA = new JButton();
        DealCardsB = new JButton();
        DiceA = new JButton();
        DiceB = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 102, 0));
        this.setResizable(false);
        this.setTitle("PayDay");
        imageURL = cldr.getResource("resources/images/logo.png");
        this.setIconImage(new ImageIcon(imageURL).getImage());
        this.setVisible(true);
        init_Components();
    }
/**
 * Initialises some buttons and pictures of the game 
 */
    public void init_Components() {

        DealCards.setSize(width * (2 / 16) - 40, height * 1 / 7);
        imageURL = cldr.getResource("resources/images/dealCard.png");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width * (2 / 16) - 20, height * 1 / 7 - 15, Image.SCALE_SMOOTH);
        DealCards.setIcon(new ImageIcon(image));
        DealCards.addActionListener(new CardListener());

        MessageCards.setSize(width * (2 / 16) - 50, height * 1 / 7);
        imageURL = cldr.getResource("resources/images/mailCard.png");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width * (2 / 16) - 20, height * 1 / 7 - 15, Image.SCALE_SMOOTH);
        MessageCards.setIcon(new ImageIcon(image));
        MessageCards.addActionListener(new CardListener());

        DealCards.setEnabled(false);
        MessageCards.setEnabled(false);

        imageURL = cldr.getResource("resources/images/bg_green.png");
        image = new ImageIcon(imageURL).getImage();
        basic_panel = new JLayeredPaneExtension(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));

        this.setPreferredSize(new Dimension(width, height));
        this.add(basic_panel);

        PayDayImage.setIcon(getImageScaled("src/resources/logo.png", width * 6 / 8, height * 1 / 5));
        PayDayImage.setBounds(0, 0, width * 6 / 8, height * 1 / 5);
        basic_panel.add(PayDayImage);

        playerA.setSize(width * 2 / 8 - 50, height * 2 / 7);
        playerA.setBounds((width * 6 / 8) + 30, 10, width * 2 / 8 - 50, height * 2 / 7);
        playerA.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.YELLOW));

        playerB.setSize(width * 2 / 8 - 50, height * 2 / 7);
        playerB.setBounds((width * 6 / 8) + 30, height * 5 / 7 - 40, width * 2 / 8 - 50, height * 2 / 7 - 20);
        playerB.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.cyan));

        MessageCards.setBounds((width * 6 / 8) + 30, height * 1 / 2 + 20, width * 2 / 16 - 40, height * 1 / 10);
        DealCards.setBounds(width * 7 / 8 + 20, height * 1 / 2 + 20, width * 2 / 16 - 40, height * 1 / 10);

        basic_panel.add(DealCards);
        basic_panel.add(MessageCards);
        playerPanel();
        basic_panel.add(playerA);
        basic_panel.add(playerB);

        setInfobox();
        basic_panel.add(InfoBox);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        CreateMenu();
        menuBar.add(fileMenu);
        pack();
        Start();
    }
/**
 * calls all the initialising functions
 */
    private void Start() {
        game.init_game();
        game.init_table();

        tablotheseis();
        basic_panel.add(tablo);

        paintPawn(game.B.getPosition(), "B");
        paintPawn(game.A.getPosition(), "A");
        paintPlayer();

        paintDice(6, "A");
        paintDice(6, "B");
        paintInfoBox("----");
    }
/**
 * Starts a new instance of the game 
 */
    public void newgame() {
        this.setVisible(false);
        new GraphicUI();
    }
/**
 * initialises the infobox panel
 */
    public void setInfobox() {
        InfoBox.setBounds((width * 6 / 8) + 30, height * 1 / 3, width * 2 / 8 - 50, playerA.getHeight() / 2 + 20);
        InfoBox.setSize(width * 2 / 8 - 50, playerA.getHeight() / 2 + 20);
        InfoBox.setLayout(new BoxLayout(InfoBox, BoxLayout.Y_AXIS));
        InfoBox.setBackground(Color.WHITE);
        InfoBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black), BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())));

        info.setEditable(false);
        info.setBorder(null);
        info.setOpaque(false);

        turn.setEditable(false);
        turn.setOpaque(false);
        turn.setBorder(null);
        command.setEditable(false);
        command.setOpaque(false);
        command.setBorder(null);
        monthsleft.setEditable(false);
        monthsleft.setOpaque(false);
        monthsleft.setBorder(null);

        paintInfoBox("----");

        InfoBox.add(info);
        InfoBox.add(monthsleft);
        InfoBox.add(turn);
        InfoBox.add(command);
    }
/**
 *initialises the board with the positions 
 */
    public void tablotheseis() {
        tablo.setBounds(10, height * 1 / 5 + 10, PayDayImage.getWidth() - 10, height - PayDayImage.getHeight() - 80);
        tablo.setSize(PayDayImage.getWidth() - 10, height - PayDayImage.getHeight() - 70);
        GridLayout grid = new GridLayout(0, 7);
        tablo.setOpaque(false);
        tablo.setLayout(grid);

        for (int i = 0; i < 32; i++) {
            position[i] = new JDesktopPane();
            temp1 = new JTextField();//

            temp1.setText(game.tablo[i].getName() + " " + game.tablo[i].getpositionNumb());
            imageURL = cldr.getResource(game.tablo[i].getImage());
            Image pos = new ImageIcon(imageURL).getImage();
            pawn_position[i] = new JLayeredPaneExtension(pos.getScaledInstance(width * 1 / 9, height * 1 / 9, Image.SCALE_SMOOTH));

            pawn_position[i].setLayout(new FlowLayout());

            temp1.setEditable(false);
            temp1.setOpaque(true);
            temp1.setBackground(Color.YELLOW);
            temp1.setFont(new Font(null, Font.BOLD, 12));
            temp1.setMaximumSize(new Dimension(width * 1 / 9, 20));

            position[i].setLayout(new BoxLayout(position[i], BoxLayout.Y_AXIS));
            position[i].add(temp1);
            position[i].add(pawn_position[i]);
            tablo.add(position[i]);
        }

        jackPotPanel.setLayout(new BoxLayout(jackPotPanel, BoxLayout.Y_AXIS));
        jackPotPanel.setOpaque(false);

        paintJackpot();
        jackPotText.setEditable(false);
        jackPotText.setOpaque(false);
        jackPotText.setHorizontalAlignment(JTextField.CENTER);
        jackPotText.setFont(new Font(null, Font.BOLD, 12));
        jackPotText.setForeground(Color.white);
        jackPotText.setBorder(null);

        ImageIcon ins = getImageScaled("src/resources/images/jackpot.png", width * 1 / 9, height * 1 / 9);
        jackPotLabel.setIcon(ins);
        jackPotPanel.add(jackPotLabel);
        jackPotPanel.add(jackPotText);

        JLabel dummy = new JLabel();
        tablo.add(dummy);
        tablo.add(jackPotPanel);
    }
/**
 * Initialises the menu buttons
 */
    public void CreateMenu() {
        JMenuItem item;
        fileMenu = new JMenu("Game Options");
        item = new JMenuItem("New Game");
        item.addActionListener(new Menulistener());
        fileMenu.add(item);

        item = new JMenuItem("Exit Game");
        item.addActionListener(new Menulistener());
        fileMenu.add(item);
    }
/**
 * Initialise both playars' panels with buttons dice and texts containing information
 * for each player
 */
    public void playerPanel() {

        paintPlayer();
        playerA.setLayout(new BorderLayout());
        playerA.add(PlayerNameA, BorderLayout.NORTH);

        JDesktopPane temp0 = new JDesktopPane();
        temp0.setLayout(new BoxLayout(temp0, BoxLayout.Y_AXIS));

        MoneyAText.setEditable(false);
        MoneyAText.setOpaque(false);
        MoneyAText.setBorder(null);
        temp0.add(MoneyAText);

        LoanAText.setEditable(false);
        LoanAText.setOpaque(false);
        LoanAText.setBorder(null);
        temp0.add(LoanAText);

        BillsAText.setOpaque(false);
        BillsAText.setBorder(null);
        BillsAText.setEditable(false);
        temp0.add(BillsAText);
        DealCardsA.setText("My Deal Cards");
        DealCardsA.addActionListener(new ButtonListenerA());
        temp0.add(DealCardsA);
        playerA.add(temp0, BorderLayout.CENTER);

        JDesktopPane temp1 = new JDesktopPane();
        GetLoanA.setText("Get Loan");
        GetLoanA.addActionListener(new ButtonListenerA());
        EndTurnA.setText("End Turn");
        EndTurnA.addActionListener(new ButtonListenerA());
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.add(GetLoanA);
        temp1.add(EndTurnA);
        playerA.add(temp1, BorderLayout.SOUTH);
        DiceA.setBackground(Color.WHITE);
        DiceA.setBorder(null);
        DiceA.addActionListener(new DiceListener());
        paintDice(1, "A");
        playerA.add(DiceA, BorderLayout.EAST);

        //PLAYER B INITIALISE
        playerB.setLayout(new BorderLayout());
        playerB.add(PlayerNameB, BorderLayout.NORTH);

        JDesktopPane temp2 = new JDesktopPane();
        temp2.setLayout(new BoxLayout(temp2, BoxLayout.Y_AXIS));

        MoneyBText.setEditable(false);
        MoneyBText.setOpaque(false);
        MoneyBText.setBorder(null);
        temp2.add(MoneyBText);

        LoanBText.setEditable(false);
        LoanBText.setOpaque(false);
        LoanBText.setBorder(null);
        temp2.add(LoanBText);

        BillsBText.setOpaque(false);
        BillsBText.setBorder(null);
        BillsBText.setEditable(false);
        temp2.add(BillsBText);
        DealCardsB.setText("My Deal Cards");
        DealCardsB.addActionListener(new ButtonListenerB());
        temp2.add(DealCardsB);
        playerB.add(temp2, BorderLayout.CENTER);

        JDesktopPane temp3 = new JDesktopPane();
        GetLoanB.setText("Get Loan");
        GetLoanB.addActionListener(new ButtonListenerB());
        EndTurnB.setText("End Turn");
        EndTurnB.addActionListener(new ButtonListenerB());
        temp3.setLayout(new BoxLayout(temp3, BoxLayout.X_AXIS));
        temp3.add(GetLoanB);
        temp3.add(EndTurnB);

        DiceB.setBackground(Color.WHITE);
        DiceB.setBorder(null);
        DiceB.addActionListener(new DiceListener());
        paintDice(1, "B");
        playerB.add(DiceB, BorderLayout.EAST);
        playerB.add(temp3, BorderLayout.SOUTH);
    }
/**
 * ActionListener for the cards
 */
    private class CardListener implements ActionListener {

        Player cardPlayer;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.A.getTurn()) {
                cardPlayer = game.A;
            } else if (game.B.getTurn()) {
                cardPlayer = game.B;
            } else {
                
            }
            if (e.getSource() == DealCards && game.tablo[cardPlayer.getPosition()] instanceof DealPosition) {
                if (cardPlayer.getPendingAction()) {
                    //reshufflecards if empty
                    if (game.DealStack.isEmpty()) {
                        game.RejectedStack.returnCardsToStacks(game.MessageStack, game.DealStack);
                    }
                    DealCard myCard = (DealCard) game.DealStack.TakeCard();
                    if (showDealCard(myCard, "show") == 0)//take card and pay and add it to players dealcards
                    {
                        if (myCard.getMoney() > cardPlayer.getMoney()) {
                            paintInfoBox("Must get loan to buy this Card");
                            LoanDialog loan = new LoanDialog();

                            if (myCard.getMoney() <= cardPlayer.getMoney() + Integer.parseInt(loan.getChoice())) {

                                cardPlayer.setLoan(Integer.parseInt(loan.getChoice()));
                                cardPlayer.setMoney(Integer.parseInt(loan.getChoice()));
                                myCard.CardAction(cardPlayer);
                                paintInfoBox("Player " + cardPlayer.getName() + " bought the card ");
                                paintPlayer();
                            } else {
                                paintInfoBox("Not enough money to buy this card");
                                game.RejectedStack.RejectCard(myCard);
                            }
                        } else {
                            myCard.CardAction(cardPlayer);
                            paintInfoBox("Player " + cardPlayer.getName() + " bought the card ");
                            paintPlayer();
                        }
                    } else {
                        game.RejectedStack.RejectCard(myCard);
                    }
                    cardPlayer.setPendingAction(false);
                } else {
                    //we do nothing
                }
            } else if (e.getSource() == MessageCards && game.tablo[cardPlayer.getPosition()] instanceof MessagePosition) {
                if (game.MessageStack.isEmpty()) {
                    game.RejectedStack.returnCardsToStacks(game.MessageStack, game.DealStack);//if stack is empty
                }
                MessagePosition pos = (MessagePosition) game.tablo[cardPlayer.getPosition()];
                if (cardPlayer.getPendingAction()) {
                    MessageCard myCard = (MessageCard) game.MessageStack.TakeCard();
                    showMailCard(myCard);
                    game.RejectedStack.RejectCard(myCard);//to prosthetoume stin lista

                    MessageCardAction(cardPlayer, myCard);
                    if (pos.getOneCardDraw() && !(myCard instanceof MovetoDorB)) {

                        cardPlayer.setPendingAction(false);
                        paintInfoBox("Card taken");
                    } else {
                        
                        counter++;
                        if (counter >= 2 && !(myCard instanceof MovetoDorB)) {
                            cardPlayer.setPendingAction(false);
                            paintInfoBox("Card taken");
                            counter = 0;
                        }
                    }
                }

            }
        }
    }
/**
 * Depending the kind of MessageCard we do the right actions at the graphics
 * @param player  the player who draws the cards
 * @param C  the card which is drawn
 */
    public void MessageCardAction(Player player, MessageCard C) {
        if (C instanceof Advertisement) {

        } else if (C instanceof Bill) {
            Bill myCard = (Bill) C;
            myCard.CardAction(player);
            paintInfoBox("Bills added");
            paintPlayer();
        } else if (C instanceof Charity) {
            Charity myCard = (Charity) C;
            myCard.CardAction(player, game.jackpot);
            paintInfoBox("Charity paid");
            paintPlayer();
            paintJackpot();
        } else if (C instanceof MadMoney) {
            MadMoney myCard = (MadMoney) C;
            myCard.CardAction(player);
            paintInfoBox("Player " + player.getName() + " got the money");
            paintPlayer();

        } else if (C instanceof MovetoDorB) {
            MovetoDorB myCard = (MovetoDorB) C;
            pawn_position[player.getPosition()].removeAll();
            if (player.getPosition() == player.getOpponent().getPosition()) {
                paintPawn(player.getOpponent().getPosition(), player.getOpponent().getName());
            }
            pawn_position[player.getPosition()].repaint();
            player.setPosition(((MovetoDorB) C).CardAction(player, game.tablo) - player.getPosition());
            paintPawn(player.getPosition(), player.getName());
            if(((MovetoDorB) C).CardAction(player, game.tablo) - player.getPosition()!=0)Sunday(player);
            positionActions(player, player.getPosition());

        } else if (C instanceof PayNeighbor) {
            PayNeighbor myCard = (PayNeighbor) C;
            myCard.CardAction(player);
            paintInfoBox("Player " + player.getOpponent().getName() + " got the money");
            paintPlayer();
        }
    }
/**
 * Action Listener of player A
 */
    private class ButtonListenerA implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (game.A.getTurn()) {
                if (command.equals("End Turn") && game.A.getDice().getRolled()) {
                    if (game.A.getPendingAction()) {

                    }else if(RadioPos||LotteryPos)
                    {
                        
                    }
                    else if (game.B.getFinished()) {
                        if (game.A.getFinished()) {
                            game.A.setTurn(false);
                        } else {
                            DiceA.setEnabled(true);
                            game.A.getDice().setRolled(false);
                            game.A.setTurn(true);
                            paintInfoBox("");
                        }
                    } else {
                        game.B.setTurn(true);
                        game.A.setTurn(false);
                        DiceB.setEnabled(true);
                        game.A.getDice().setRolled(false);
                        paintInfoBox("");
                    }
                } else if (command.equals("Get Loan")) {
                    GetLoan(game.A);
                } else if (command.equals("My Deal Cards") && game.tablo[game.A.getPosition()] instanceof Buyer) {
                    MyDealCards(game.A);
                }
            }
        }
    }
/**
 * Action Listener of player B
 */
    private class ButtonListenerB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.B.getTurn()) {
                String command = e.getActionCommand();
                if (command.equals("End Turn") && game.B.getDice().getRolled()) {

                    if (game.B.getPendingAction()) {

                    }else if(RadioPos||LotteryPos)
                    {
                        
                    } else if (game.A.getFinished()) {
                        if (game.B.getFinished()) {
                            paintInfoBox("Game is prolly finished");
                            game.B.setTurn(false);
                        } else {
                            DiceB.setEnabled(true);
                            game.B.getDice().setRolled(false);
                            game.B.setTurn(true);
                            paintInfoBox("");
                        }
                        //an exoyme pending action den teleionei o giros
                    } else {
                        game.A.setTurn(true);
                        game.B.setTurn(false);
                        DiceA.setEnabled(true);
                        game.B.getDice().setRolled(false);
                        paintInfoBox("");
                    }

                } else if (command.equals("Get Loan")) {
                    GetLoan(game.B);
                } else if (command.equals("My Deal Cards") && game.tablo[game.B.getPosition()] instanceof Buyer) {
                    MyDealCards(game.B);
                }
            }
        }
    }
/**
 * ActionListener of the menu buttons
 */
    private class Menulistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "New Game":
                    WindowDialog win = new WindowDialog("yes", "no", "Do you really want to play a new game?", command, null);
                    if (win.getOption() == 1) {
                        newgame();
                    }
                    break;
                case "Exit Game":
                    WindowDialog win2 = new WindowDialog("yes", "no", "Do you really want to exit?", command, null);
                    if (win2.getOption() == 1) {
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
        }
    }
/**
 * ActionListener of the dice
 */
    private class DiceListener implements ActionListener {

        Dice dicea = game.A.getDice();
        Dice diceb = game.B.getDice();
        int choiceA;
        int choiceB;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() != null) {
                if (e.getSource() == DiceA && game.A.getPendingAction()) {
                    paintDice(dicea.roll(), "A");
                    DiceA.setEnabled(false);
                    if (game.tablo[game.A.getPosition()] instanceof Sweepstakes) {
                        Sweepstakes pos = (Sweepstakes) game.tablo[game.A.getPosition()];
                        pos.PositionAction(game.A, dicea.getDiceNumb());
                        paintPlayer();
                        paintInfoBox(game.A.getName() + " Won " + dicea.getDiceNumb() * 1000 + " E");
                        game.A.setPendingAction(false);
                    } else if (game.tablo[game.A.getPosition()] instanceof YardSale) {
                        YardSale pos = (YardSale) game.tablo[game.A.getPosition()];
                        DealCard C = game.DealStack.TakeCard();
                        pos.PositionAction(game.A, dicea.getDiceNumb(), C);
                        showDealCard(C, "");
                        paintPlayer();
                        paintInfoBox(game.A.getName() + " bought this card for " + dicea.getDiceNumb() * 100);
                        game.A.setPendingAction(false);
                    }
                } else if (e.getSource() == DiceB && game.B.getPendingAction()) {
                    paintDice(diceb.roll(), "B");
                    DiceB.setEnabled(false);
                    if (game.tablo[game.B.getPosition()] instanceof Sweepstakes) {
                        Sweepstakes pos = (Sweepstakes) game.tablo[game.B.getPosition()];
                        pos.PositionAction(game.B, diceb.getDiceNumb());
                        paintPlayer();
                        paintInfoBox(game.B.getName() + " Won " + diceb.getDiceNumb() * 1000 + " E");
                        game.B.setPendingAction(false);
                    } else if (game.tablo[game.B.getPosition()] instanceof YardSale) {
                        YardSale pos = (YardSale) game.tablo[game.B.getPosition()];
                        DealCard C = game.DealStack.TakeCard();
                        pos.PositionAction(game.B, diceb.getDiceNumb(), C);
                        showDealCard(C, "");
                        paintPlayer();
                        paintInfoBox(game.B.getName() + " bought this card for " + diceb.getDiceNumb() * 100);
                        game.B.setPendingAction(false);
                    }
                } else if (RadioPos) {
                    if(e.getSource()==DiceA) {
                        paintDice(dicea.roll(),"A");
                        DiceA.setEnabled(false);
                    }
                    else {
                        paintDice(diceb.roll(),"B");
                        DiceB.setEnabled(false);
                    }
                    
                    if(dicea.getRolled()&&diceb.getRolled()){
                        if(dicea.getDiceNumb() == diceb.getDiceNumb())
                        {
                            DiceA.setEnabled(true);
                            DiceB.setEnabled(true);
                            dicea.setRolled(false);
                            diceb.setRolled(false);
                            paintInfoBox("Throw again the dices");
                        }else
                        {
                            if(dicea.getDiceNumb()>diceb.getDiceNumb()) 
                            {
                                game.A.setMoney(1000);
                                paintPlayer();
                                paintInfoBox("A won 1000 from Radio");
                            }else 
                            {
                                game.B.setMoney(1000);
                                paintPlayer();
                                paintInfoBox("B won 1000 from Radio");
                            }
                            RadioPos=false;
                        }  
                    }
                } else if (LotteryPos) {
                    if(e.getSource()==DiceA) {
                        paintDice(dicea.roll(),"A");
                        DiceA.setEnabled(false);
                        if(dicea.getDiceNumb()==LotterychoiceA)
                        {
                            game.A.setMoney(1000);
                            paintPlayer();
                            paintInfoBox("A won 1000 from Lottery");
                            LotteryPos=false;
                        }else
                        {
                             DiceB.setEnabled(true);
                        }
                    }
                    else {
                        paintDice(diceb.roll(),"B");
                        DiceB.setEnabled(false);
                        if(diceb.getDiceNumb()==LotterychoiceB)
                        {
                            game.B.setMoney(1000);
                            paintPlayer();
                            paintInfoBox("B won 1000 from Lottery");
                            LotteryPos=false;
                        }else
                        {
                            DiceA.setEnabled(true);
                        }
                    }
                } else {
                    Move_Player(e);
                }
            }
        }
    }
/**
 * if its sunday the Sunday dialog appears asking to bet 
 * @param player the player who sits in a sunday position
 */
    public void Sunday(Player player) {
        if (game.tablo[player.getPosition()].isSunday()) {
            SundayFMatch match = new SundayFMatch();
            if (game.SundayMatch(player, match.getChoice())) {
                SundayFMatch match1 = new SundayFMatch(true);
            } else if (match.getChoice() != 3) {
                SundayFMatch match1 = new SundayFMatch(false);
            }
            paintPlayer();
        }
    }
/**
 *Moves the player depending the number of the dice and changes  the players position 
 * @param e the action event taken from the dicelistener
 */
    public void Move_Player(ActionEvent e) {
        Dice dicea = game.A.getDice();
        Dice diceb = game.B.getDice();
        if (e.getSource() == DiceA) {
            paintDice(dicea.roll(), "A");
            if (dicea.getRolled()) {
                DiceA.setEnabled(false);
                if (game.GameStarted) {//edo perpatane ta poylia
                    if (dicea.getDiceNumb() == 6) {
                        game.WinJackpot(game.A);
                        paintJackpot();
                        paintPlayer();
                    }
                    if (game.A.getPosition() == 0) {
                        pawn_position[31].removeAll();
                        pawn_position[31].repaint();
                    }
                    pawn_position[game.A.getPosition()].removeAll();
                    if (game.A.getPosition() == game.B.getPosition()) {
                        paintPawn(game.B.getPosition(), "B");
                    }
                    pawn_position[game.A.getPosition()].repaint();
                    game.A.setPosition(dicea.getDiceNumb());
                    paintPawn(game.A.getPosition(), "A");
                    Sunday(game.A);
                    positionActions(game.A, game.A.getPosition());
                }
            }
        } else {
            paintDice(diceb.roll(), "B");
            if (diceb.getRolled()) {
                DiceB.setEnabled(false);

                if (game.GameStarted) {//edo perpatane ta poylia
                    if (diceb.getDiceNumb() == 6) {
                        game.WinJackpot(game.B);
                        paintJackpot();
                        paintPlayer();
                    }//omoios me a
                    if (game.B.getPosition() == 0) {
                        pawn_position[31].removeAll();
                        pawn_position[31].repaint();
                    }
                    pawn_position[game.B.getPosition()].removeAll();
                    if (game.A.getPosition() == game.B.getPosition()) {
                        paintPawn(game.A.getPosition(), "A");
                    }
                    pawn_position[game.B.getPosition()].repaint();
                    game.B.setPosition(diceb.getDiceNumb());
                    paintPawn(game.B.getPosition(), "B");
                    Sunday(game.B);
                    positionActions(game.B, game.B.getPosition());
                }
            }
        }
        Starting_Player();
    }
/**
 * This function determines who player plays first buy letting both throwing the dice
 */
    public void Starting_Player() {
        Dice dicea = game.A.getDice();
        Dice diceb = game.B.getDice();
        if (dicea.getRolled() && diceb.getRolled() && !game.A.getTurn() && !game.B.getTurn()) {
            if (dicea.getDiceNumb() == diceb.getDiceNumb()) {//ean exoyme sto setturn idio arithmo 
                DiceA.setEnabled(true);
                DiceB.setEnabled(true);
                dicea.setRolled(false);
                diceb.setRolled(false);
            } else {
                game.SetStartingPlayer(dicea.getDiceNumb(), diceb.getDiceNumb());
                DealCards.setEnabled(true);
                MessageCards.setEnabled(true);

                game.GameStarted = true;
                paintInfoBox("");
                if (game.A.getTurn()) {
                    DiceA.setEnabled(true);
                } else {
                    DiceB.setEnabled(true);
                }

                dicea.setRolled(false);
                diceb.setRolled(false);
            }
        }
    }
/**
 * depending on the position the player is some actions in graphic are happening
 * @param player the player that sits on  this sit
 * @param position the number of the position
 */
    private void positionActions(Player player, int position) {
        if (game.tablo[position] instanceof DealPosition) {
            paintInfoBox(player.getName() + " Draw a Deal Card");
            player.setPendingAction(true);
        } else if (game.tablo[game.A.getPosition()] instanceof Radio&&game.A.getTurn() || game.tablo[game.B.getPosition()] instanceof Radio&&game.B.getTurn()) {
            RadioPos = true;
            DiceA.setEnabled(true);
            DiceB.setEnabled(true);
            paintInfoBox("Throw the dices");
            game.A.getDice().setRolled(false);
            game.B.getDice().setRolled(false);
        }else if (game.tablo[game.A.getPosition()] instanceof Lottery &&game.A.getTurn()|| game.tablo[game.B.getPosition()] instanceof Lottery&&game.B.getTurn()) {
            LotteryPos = true;
            ArrayList<Object> opt = new ArrayList();
            opt.add("1");
            opt.add("2");
            opt.add("3");
            opt.add("4");
            opt.add("5");
            opt.add("6");
            LotteryDialog dial1=new LotteryDialog(game.A,opt);
            LotterychoiceA=Integer.parseInt(dial1.getChoice());
            opt.remove(LotterychoiceA-1);
            LotteryDialog dial2=new LotteryDialog(game.B,opt);
            LotterychoiceB=Integer.parseInt(dial2.getChoice());
            paintInfoBox("A choice: "+LotterychoiceA+" B choice: "+LotterychoiceB);
            if(game.A.getTurn()){
                DiceA.setEnabled(true);
                game.A.getDice().setRolled(false);
            }else if(game.B.getTurn()){
                DiceB.setEnabled(true);
                game.B.getDice().setRolled(false);
            }
        }else if (game.tablo[position] instanceof PayDayPosition) {

            PayDayPosition pos = (PayDayPosition) game.tablo[position];
            if (player.getLoan() != 0) {
                PayDayDialog dial = new PayDayDialog();
                pos.PositionAction(player, dial.getChoice());
            } else {
                pos.PositionAction(player, -1);//doesnt pay loan 
            }
            game.ThrowLastCards(player);
            if (game.GameHasFinished()) {
                String s = game.Winner();
                paintInfoBox("Player A Score: " + game.ScoreA + " Player B Score: " + game.ScoreB);
                paintPlayer();
                WindowDialog dialog = new WindowDialog("Start new game", "Exit", s, "Τελος Παιχνιδιού", "src/resources/images/cup.jpg");
                if (dialog.getOption() == 1) {
                    newgame();
                } else if (dialog.getOption() == 2) {
                    System.exit(0);
                }
            }
            paintPlayer();
        } else if (game.tablo[position] instanceof Buyer) {
            if (player.EmptyCards()) {
                player.setPendingAction(false);//gt erxetai me true pending apo tin proigoymeni thesi
                paintInfoBox(player.getName() + ": Has no cards to sell");
            } else {
                paintInfoBox(player.getName() + ": Sell a card");
                player.setPendingAction(true);
            }
        } else if (game.tablo[position] instanceof MessagePosition) {
            MessagePosition pos = (MessagePosition) game.tablo[position];
            if (pos.getOneCardDraw()) {
                paintInfoBox(player.getName() + " Draw one Message Card");
            } else {
                paintInfoBox(player.getName() + " Draw two Message Cards");
            }
            player.setPendingAction(true);
        } else if (game.tablo[position] instanceof FamilyCasino) {
            FamilyCasino pos = (FamilyCasino) game.tablo[position];
            if (pos.PositionAction(player, game.jackpot)) {
                paintInfoBox(player.getName() + " Won in Family Casino 500 E");
                paintPlayer();
            } else {
                paintInfoBox(player.getName() + " Lost in Family Casino 500 E");
                paintJackpot();
                paintPlayer();
            }
        } else if (game.tablo[position] instanceof Sweepstakes || game.tablo[position] instanceof YardSale) {
            player.setPendingAction(true);
            paintInfoBox("Throw the Dice again");
            if (player.getName().equals("A")) {
                DiceA.setEnabled(true);
            } else {
                DiceB.setEnabled(true);
            }
        }
    }
/**
 * 
 * @param player 
 */
    public void GetLoan(Player player) {
        LoanDialog loan = new LoanDialog();
        if (Integer.parseInt(loan.getChoice()) != 0) {
            player.setLoan(Integer.parseInt(loan.getChoice()));
            player.setMoney(Integer.parseInt(loan.getChoice()));
            paintPlayer();
            paintInfoBox("Player " + player.getName() + " got " + Integer.parseInt(loan.getChoice()) + " loan");
        }
    }
/**
 * 
 * @param player 
 */
    public void MyDealCards(Player player) {
        if (!player.EmptyCards() && player.getPendingAction()) {
            DealCard myCard = player.SellDealCard();
            showDealCard(myCard, "sell");
            player.setPendingAction(false);
            player.setMoney(myCard.getSellValue());
            game.RejectedStack.RejectCard(myCard);
            paintPlayer();
            paintInfoBox(player.getName() + " : Sold the card");
        }
    }
/**
 * Set the  texts of both players information 
 */
    private void paintPlayer() {
        MoneyAText.setText("Money: " + game.A.getMoney() + " Euro");
        MoneyBText.setText("Money: " + game.B.getMoney() + " Euro");
        LoanAText.setText("Loan: " + game.A.getLoan() + " Euro");
        LoanBText.setText("Loan: " + game.B.getLoan() + " Euro");
        BillsAText.setText("Bills: " + game.A.getBills() + " Euro");
        BillsBText.setText("Bills: " + game.B.getBills() + " Euro");
    }
/**
 * Paints the pawn of the player
 * @param i the position
 * @param Player the player's pawn we want to paint
 */
    public void paintPawn(int i, String Player) {

        JLabel pawn = new JLabel();

        if (Player.equals("A")) {
            ins = getImageScaled("src/resources/images/pawn_yellow.png", 50, 50);
            pawn.setIcon(ins);
        } else {
            ins = getImageScaled("src/resources/images/pawn_blue.png", 50, 50);
            pawn.setIcon(ins);
        }
        pawn_position[i].add(pawn);
        Graphics g1 = position[i].getGraphics();
        position[i].paintComponents(g1);
        position[i].repaint();
    }
/**
 * Paints the money of the jackpot
 */
    public void paintJackpot() {
        jackPotText.setText(game.jackpot.getBalance() + " Euro");
    }
/**
 * paintts the infobox 
 * @param commandtxt cointains the command that must be executed in each state
 */
    private void paintInfoBox(String commandtxt) {

        monthsleft.setText(game.Infobox()[0]);
        turn.setText(game.Infobox()[1]);
        command.setText(commandtxt);
    }
/**
 * refreshes the image of the dice 
 * @param Dicenumb the number of the dice 
 * @param player the player's dice which we paint
 */
    private void paintDice(int Dicenumb, String player) {
        if (player.equals("A")) {
            imageURL = cldr.getResource("resources/images/dice-" + Dicenumb + ".jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            DiceA.setIcon(new ImageIcon(image));
        } else {

            imageURL = cldr.getResource("resources/images/dice-" + Dicenumb + ".jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            DiceB.setIcon(new ImageIcon(image));
            DiceB.setBorder(null);
        }
    }
/**
 *  a class which is used for putting a background image to a jdesktoppane
 */
    public class JLayeredPaneExtension extends JLayeredPane {

        private Image image;

        public JLayeredPaneExtension(Image img) {
            image = img;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    static ImageIcon getImageScaled(String image, int width, int height) {
        Image newimg = new ImageIcon(image).getImage().getScaledInstance(
                width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
/**
 * 
 * @param C the card we want to show 
 * @param choice if we want to show the buy dialog or the sell dialog
 * @return the choice we made
 */
    public int showDealCard(DealCard C, String choice) {

        Object[] options = {"Buy", "Ignore Deal"};
        Object[] options2 = {"OK"};
        int n;

        URL imageURL = cldr.getResource("resources/images/" + C.getImageURL()); //image
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();
        if (choice.equals("show")) {
            n = p.showOptionDialog(this,
                    C.getMessage() + "\nΤιμή Αγοράς: " + C.getMoney() + " Ευρώ \nΤιμή Πώλησης: " + C.getSellValue() + " Ευρώ \n",
                    "DealCard",
                    JOptionPane.OK_OPTION,
                    0,
                    new ImageIcon(image),
                    options,
                    options[0]);
        } else {
            n = p.showOptionDialog(this,
                    C.getMessage() + "\nΤιμή Αγοράς: " + C.getMoney() + " Ευρώ \nΤιμή Πώλησης: " + C.getSellValue() + " Ευρώ \n",
                    "DealCard",
                    JOptionPane.OK_OPTION,
                    0,
                    new ImageIcon(image),
                    options2,
                    options2[0]);
        }
        return n;

    }
/**
 * 
 * @param C the card we want to show 
 */
    public void showMailCard(Card C) {
        String s1 = "";
        String s = "";
        URL imageURL2;
        imageURL2 = cldr.getResource("resources/images/" + C.getImageURL());
        Image image = null;
        image = new ImageIcon(imageURL2).getImage();//see the bug
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);

        JOptionPane p = new JOptionPane();
        if (C instanceof PayNeighbor) {
            s1 = "Πλήρωσε το γείτονα";
            s = "Πλήρωσε " + C.getMoney() + " Ευρώ στον αντίπαλο";
        } else if (C instanceof MadMoney) {
            s1 = "Πάρε λεφτά από τον γείτονα";
            s = "Πάρε " + C.getMoney() + " Ευρώ από τον αντίπαλο";
        } else if (C instanceof Charity) {
            s1 = "Φιλανθρωπία";
            s = "Πλήρωσε " + C.getMoney() + " Ευρώ στο Jackpot";
        } else if (C instanceof Bill) {
            s1 = "Εξόφληση λογαριασμού";
            s = "Κράτα το λογαριασμό";
        } else if (C instanceof MovetoDorB) {
            s1 = "Μετακίνηση σε θέση Συμφωνίας/Αγοραστή";
            s = "Εντάξει";
        } else if (C instanceof Advertisement) {
            s1 = "Διαφήμιση";
            s = "Εντάξει";
        }
        Object[] options = {s};
        int n = p.showOptionDialog(this,
                C.getMessage(),
                s1,
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
    }
}
