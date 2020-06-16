/**
 * Due: June 15, 2020
 * Aidan Ang
 * Alan Li
 * Instructions
 * Mrs. Krasteva, ICS 4UO
 * This class displays the game's instructions and is accessed through/returns to the game's main menu
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

/**
    *  Displays  game's instructions and is accessed through/returns to main menu
    * <pre>
    * Revision history:
    *  - May 27, 2020: Created ~Alan Li
    *  - June 5, 2020: Updated ~Aidan Ang
    *  - June 14, 2020: Commented ~Aidan Ang
    *  - June 14, 2020: Finished ~Aidan Ang
    * </pre>
    * @author Aidan Ang
    * @author Alan Li
    * @version 1
    */
public class Instructions implements ActionListener {
  /** The frame to display to */
   JFrame frame;
   /** The instructions */
   JLabel instructionField;
   /** MainMenu instance to return to upon game completion */
   MainMenu menu;
   /** Button to return to main menu */
   private JButton backButton = new JButton("Back to Main Menu");
   /** Constructs a new Instructions, displaying graphics to the specified frame and returning to the specified MainMenu instance when the user is finished reading
    * @param frameIn The frame to display graphics on
    * @param menuIn The MainMenu instance to return to
    */
   public Instructions (JFrame frameIn, MainMenu menuIn) {
      menu = menuIn;
      frame = frameIn;
      frame.getContentPane().removeAll();
      frame.setTitle("Instructions");
      frame.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      setUpFrame(c);
      backButton.addActionListener(this);
      frame.revalidate();
      frame.repaint();
   }
   /** Sets up the frame for display
    * @param c The GridBagConstraints object to be used for adding components to containers
    */
   public void setUpFrame (GridBagConstraints c) {     
      instructionField = new JLabel(
         "<html>" +
         "Instructions<br><br>" + 
         "Welcome to Bubbles to the Brim!<br>" +
         "Bubbles to the Brim is a choose-your-own-adventure game, featuring:<br>" +
         "70 possible item combinations, 57 different choices, 7 custom-made images and 4 backgrounds!<br>" +
         "The purpose of this game is to successfully purchase everyone's favorite drink, bubble tea, without contracting COVID-19.<br>" +
         "To achieve this goal, you will be able to select four of an array of eight different useful items.<br>" +
         "Of course, before you choose which items you want to take with you, you must know what they do.<br>" +
         "When you press play, the game will take you to a Level 1:<br>" +
         "an interactive gallery of the various items which you can use in Bubbles to the Brim.<br>" +  
         "To complete the level, you must click on each item's button to view its description.<br>" +
         "\tHint: Make sure to read each description carefully!<br>" +
         "Upon completion of Level 1, Level 2 will begin with an item selection screen.<br>" +
         "After you choose your items, your journey will begin!<br>" +
         "Do you best to navigate the bubble tea shop, wary of any situations with potential for infection.<br><br>" +
         "Note: In game navigation is operated with on-screen buttons, unless otherwise stated.<br><br>" +
         "Good luck player!<br>" +
         "- Aidan Ang & Alan Li, Virusoft Inc. founders" + 
         "</html>"
         , SwingConstants.CENTER);
      instructionField.setFont(new Font("Serif", Font.PLAIN, 18));
      c.fill = GridBagConstraints.HORIZONTAL;
      c.ipady = 100;
      c.weightx = 0.5;
      c.gridwidth = 3;
      c.gridx = 0;
      c.gridy = 1;
      frame.add(instructionField, c);
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.ipady = 0;
      c.weighty = 1.0;
      c.gridwidth = 3;
      c.insets = new Insets(0,360,0,360);
      c.gridx = 0;
      c.gridy = 2;
      frame.add(backButton, c);
   }
   /** Called when an action occurs (a button is pressed); when the backButton is pressed the game returns to the MainMenu instance specified in the constructor
    * @param e The ActionEvent which triggered the function
    */
   public void actionPerformed (ActionEvent e)
   {
      if (e.getSource() == backButton)
      {
         menu.setUpFrame();
         menu.drawMenu();
      }
   }
}