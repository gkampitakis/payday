package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author csd3142
 */
public class PayDayDialog {

    ImageIcon image;
    int n;

    /**
     * <b>constructor</b>: Creates a new PayDay Window  <br />
     * <b>postconditions</b>: Creates a new PayDay Window
     */
    public PayDayDialog() {
        image = GraphicUI.getImageScaled("src/resources/images/logo.png", 170, 100);
        JFrame dialog = new JFrame();
        Object options[] = {"Ναι", "Πληρωμή μέρους", "Όχι"};
        n = JOptionPane.showOptionDialog(dialog, "Θέλις να αποπληρώσεις τα δάνειά σου?", "Τραπεζίτης", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
    }

    /**
     * <b>Accessor<b> Returns the choice of the player
     *
     * @return the choice of the player
     */
    public int getChoice() {
        return n;
    }
}
