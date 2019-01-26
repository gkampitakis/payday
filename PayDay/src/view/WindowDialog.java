package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author csd3142
 */
public class WindowDialog {

    int option;
    int n;
    ImageIcon imageicon;
    ClassLoader cldr = this.getClass().getClassLoader();

    /**
     * <b>constructor</b>: Creates a new Window Dialog  <br />
     *
     * @param op1 the first option
     * @param op2 the second option
     * @param message the message that is displayed inside the dialog
     * @param WindowName the name of the window 
     * @param image the path where the image is located
     */
    public WindowDialog(Object op1, Object op2, String message, String WindowName, String image) {
        JFrame dialog = new JFrame();
        Object options[] = {op1, op2};
        imageicon = GraphicUI.getImageScaled(image, 170, 100);
        n = JOptionPane.showOptionDialog(dialog, message, WindowName, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                imageicon, options, options[0]);

        switch (n) {
            case JOptionPane.YES_OPTION:
                option = 1;
                break;
            case JOptionPane.NO_OPTION:
                option = 2;
                break;
            default:
                option = 0;
                break;
        }
    }
    /**
     * <b>Accessor<b> Returns the choice of the player
     * @return the choice of the player
     */
    public int getOption() {
        return option;
    }
}
