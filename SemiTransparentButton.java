/**
 * Due: June 15, 2020
 * Aidan Ang
 * Alan Li
 * SemiTransparentLabel
 * Mrs. Krasteva, ICS 4UO
 * This class creates a JButton with a semi-transparent rectangle in its background
 */
import java.awt.*;
import javax.swing.*;

/**
    * Creates JButton with semi-transparent rectangle in background
    * <pre>
    * Revision history:
    *  - June 14, 2020: Created ~Aidan Ang
    *  - June 14, 2020: Commented ~Aidan Ang
    *  - June 14, 2020: Finished ~Aidan Ang
    * </pre>
    * @author Aidan Ang
    * @version 1
    */

public class SemiTransparentButton extends JButton {
 /** Calls the default JButton constructor
      */
   public SemiTransparentButton () {
      super();
   }
   /** Calls the default JButton constructor with the specified String parameter
  * @param text The String of text to be displayed on the Jlabel
      */
   public SemiTransparentButton (String text) {
      super(text);
   }
   /** Overrides the JButton method of same name and parameters to draw a semi-transparent rectangle
    * @override
    * @param g "The Graphics object to protect", according to the Oracle API (https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#paintComponent(java.awt.Graphics))
    */
   protected void paintComponent(Graphics g) {
      g.setColor(new Color (192, 192, 192, 128));
      g.fillRect(0, 0, Integer.MAX_VALUE,Integer.MAX_VALUE);
      super.paintComponent(g);
   }
}