package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author csd3142
 */
public class LoanDialog {

    String loan;
    ImageIcon image;
    ClassLoader cldr = this.getClass().getClassLoader();

    /**
     * <b>constructor</b>: Creates a new LoanDialog Window  <br />
     * <b>postconditions</b>: Creates a new LoanDialog Window
     */
    public LoanDialog() {
        image = GraphicUI.getImageScaled("src/resources/images/Loan.jpg", 120, 100);
        JFrame frame = new JFrame();
        Object[] options = {"1000", "2000", "3000", "4000", "5000", "10000", "15000", "20000"};
        loan = (String) JOptionPane.showInputDialog(frame, "Choose the money you want", "Bank", JOptionPane.PLAIN_MESSAGE, image, options, "1000");
    }

    /**
     * <b>Accessor<b> Returns the choice of the player
     *
     * @return the choice of the player if he chooses nothing the returned value
     * is zero
     */
    public String getChoice() {
        if (loan == null) {
            return "0";
        } else {
            return loan;
        }
    }
}
