package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import model.player.Player;
import java.util.ArrayList;

/**
 *
 * @author csd3142
 */
public class LotteryDialog {

    String choice;
    ImageIcon image;
    ClassLoader cldr = this.getClass().getClassLoader();

    /**
     * <b>constructor</b>: Creates a new LotteryDialog Window  <br />
     * <b>postconditions</b>: Creates a new LotteryDialog Window
     * @param player the player that is in the lottery position
     * @param opt the options that the dialog gives to choose 
     */
    public LotteryDialog(Player player, ArrayList opt) {
        Object[] options = opt.toArray();
        image = GraphicUI.getImageScaled("src/resources/images/lottery.jpg", 120, 100);
        JFrame frame = new JFrame();
        choice = (String) JOptionPane.showInputDialog(frame, "Choose the number you bet", player.getName() + " Player Chooces", JOptionPane.PLAIN_MESSAGE, image, options, "1000");
    }

    /**
     * <b>Accessor<b> Returns the choice of the player
     *
     * @return the choice of the player or returns a random number from 1 to 6
     * if players chooses nothing
     */
    public String getChoice() {
        int i = 1 + (int) (Math.random() * 6);
        if (choice == null) {
            return i + "";
        } else {
            return choice;
        }
    }
}
