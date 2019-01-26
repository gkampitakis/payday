package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 *
 * @author csd3142
 */
public class SundayFMatch {
    ImageIcon image;
    int n;
    ClassLoader cldr=this.getClass().getClassLoader();
     /**
	 * <b>constructor</b>: Creates a new Sunday Match Window  <br />
	 * <b>postconditions</b>: Creates a new Sunday Match
	 * 
	 */
    public SundayFMatch()
    {
        image=GraphicUI.getImageScaled("src/resources/images/Barcelona_Real.jpg",170,100);
        JFrame match=new JFrame();
        Object options[]={"Νίκη Μπαρτσελόνα","Ισοπαλία","Νίκη Ρεάλ","Δεν θέλω να κάνω πρόβλεψη"};
        n=JOptionPane.showOptionDialog(match,"Στοιχημάτισε 500 Ευρώ για τον αγώνα Μπαρτσελόνα-Ρεάλ","Ποδοσφαιρικός Αγώνας Κυριακής", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
    }
     /**
	 * <b>constructor</b>: Creates a new Sunday Match Window  <br />
	 * <b>postconditions</b>: Creates a new Sunday Match Window 
         * depending the boolean value it prints a win dialog or lose 
         * @param win if the player wins or loses
	 */
    public SundayFMatch(boolean win)
    {
        image=GraphicUI.getImageScaled("src/resources/images/Barcelona_Real.jpg",170,100);
        JFrame match=new JFrame();
        if(win)
        {
            Object options[]={"Κέρδισες 1000 Ευρώ"};
            n=JOptionPane.showOptionDialog(match,"Σωστή Πρόβλεψη","Ποδοσφαιρικός Αγώνας Κυριακής", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else
        {
            Object options[]={"Δυστυχώς έχασες"};
            n=JOptionPane.showOptionDialog(match,"Λάθος τα υπολογισες Unlucky","Ποδοσφαιρικός Αγώνας Κυριακής", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }
    }
    /**
     * <b>Accessor<b> Returns the choice of the player
     * @return the choice of the player
     */
    public int getChoice()
    {
        return n;
    }
    
}
