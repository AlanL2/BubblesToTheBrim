/**
 * Due: June 15, 2020
 * Aidan Ang
 * Alan Li
 * UnicornButton
 * Mrs. Krasteva, ICS 4UO
 * This class creates a JButton with a randomly colored semi-transparent rectangle in its background
 */
import java.awt.*;
import javax.swing.*;

/**
    * Creates JButton with randomly colored semi-transparent rectangle in background
    * <pre>
    * Revision history:
    *  - June 14, 2020: Created ~Aidan Ang
    *  - June 14, 2020: Commented ~Aidan Ang
    *  - June 14, 2020: Updated ~Aidan Ang
    *  - June 17, 2020: Finished ~Aidan Ang
    * </pre>
    * @author Aidan Ang
    * @version 1
    */

public class UnicornButton extends JButton {
   private Color buttonColor;
 /** Calls the default JButton constructor
      */
   public UnicornButton () {
      super();
      super.setForeground(Color.white);
      //outsideButton = true;
   }
   /** Calls the default JButton constructor with the specified String parameter
  * @param text The String of text to be displayed on the Jlabel
      */
   public UnicornButton (String text) {
      super(text);
      super.setForeground(Color.white);
      //outsideButton = true;
   }
   /** Overrides the JButton method of same name and parameters to draw a semi-transparent rectangle
    * @override
    * @param g "The Graphics object to protect", according to the Oracle API (https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#paintComponent(java.awt.Graphics))
    */
   protected void paintComponent(Graphics g) {
      buttonColor = new Color ((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), 128);
      g.setColor(buttonColor);
      g.fillRect(0, 0, Integer.MAX_VALUE,Integer.MAX_VALUE);
      super.paintComponent(g);
   }
}
