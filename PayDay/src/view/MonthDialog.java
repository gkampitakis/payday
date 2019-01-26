package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author csd3142
 */
public class MonthDialog {

    String month;
    ImageIcon image;
    ClassLoader cldr = this.getClass().getClassLoader();

    /**
     * <b>constructor</b>: Creates a new MonthDialog Window  <br />
     * <b>postconditions</b>: Creates a new MonthDialog Window
     */
    public MonthDialog() {
        image = GraphicUI.getImageScaled("src/resources/logo.png", 120, 100);
        JFrame frame = new JFrame();
        Object[] options = {"1", "2", "3"};
        month = (String) JOptionPane.showInputDialog(frame, "How many months you wish to play", "Months Playing", JOptionPane.PLAIN_MESSAGE, image, options, "1");
    }

    /**
     * <b>Accessor<b> Returns the choice of the player
     *
     * @return the choice of the player
     */
    public String getChoice() {
        if (month == null) {
            return "1";
        } else {
            return month;
        }
    }
}
