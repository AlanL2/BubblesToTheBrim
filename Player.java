/**
 * Due: June 15, 2020
 * Aidan Ang
 * Alan Li
 * Player
 * Mrs. Krasteva, ICS 4UO
 * This class prompts the user to input information about themselves and stores the inputs and other user-related data
 * 
 * 
 * 
 * 
 * Revisions
 * June 2: Created ~ Aidan Ang
 * June 3: Updated ~ Aidan Ang
 * June 5: Updated ~ Aidan Ang
 * June 7: Updated ~ Aidan Ang
 * June 10: Updated ~ Aidan Ang
 * June 11: Commented ~Aidan Ang
 * June 12: Commented ~ Alan Li
 * June 13: Finished ~ Alan Li
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/** Prompts user to input information about themselves and stores user data
 */
public class Player implements ActionListener {
   /** The MainMenu instance which called this class */
   MainMenu menu;
   /** The frame to display to */
   JFrame frame;
   /** The title of the name input section */
   JLabel nameTitle;
   /** The textfield which the player enters their name into*/
   JTextField nameField;
   /** The submit button for the name input section */
   JButton nameSubmitButton;
   /** The title of the gender input section */
   JLabel genderTitle;
   /** The button to indicate your are male */
   JButton genderButtonMale;
   /** The button to indicate your are female */
   JButton genderButtonFemale;
   /** The button to indicate your are neither male nor female */
   JButton genderButtonOther;
   /** The title of the favorite drink input section */
   JLabel drinkTitle;
   /** The textfield which the player enters their favorite drink into*/
   JTextField drinkField;
   /** The submit button for the favorite drink input section */
   JButton drinkSubmitButton;
   
   // user data
   /** Player's name */
   String name;
   /** Player's gender */
   String gender;
   /** Player's favorite drink */
   String favoriteDrink;
   /** Player's chance of infection */
   double infectionChance;
   /** Array of which items the players has in their possesion */
   boolean [] itemsHeld = new boolean [8];
   /** Array of which items the players has equipped */
   boolean [] itemsEquipped = new boolean [8];
   
   /** Constructs a new Player, displaying graphics to the specified frame and passing the specified MainMenu instance to LevelOne.
    * The MainMenu reference moves through to LevelTwo so that LevelTwo knows where to return after it is finished
    * @param frameIn The frame to display graphics on
    * @param menuIn The MainMenu instance to pass to LevelOne
    */
   public Player (JFrame frameIn, MainMenu menuIn) {
      menu = menuIn;
      frame = frameIn;
      frame.getContentPane().removeAll();
      frame.setLayout(new GridLayout(5, 3));
            
      nameTitle = new JLabel ("Please enter your name:", SwingConstants.CENTER);
      nameField = new JTextField(10);
      nameSubmitButton = new JButton ("Submit");
      nameSubmitButton.addActionListener(this);
      
      genderTitle = new JLabel ("Please select your gender:", SwingConstants.CENTER);
      genderButtonMale = new JButton ("Male");
      genderButtonFemale = new JButton ("Female");
      genderButtonOther = new JButton ("Other");
      genderButtonMale.addActionListener(this);
      genderButtonFemale.addActionListener(this);
      genderButtonOther.addActionListener(this);
      
      drinkTitle = new JLabel ("Please enter the name of your favorite drink:", SwingConstants.CENTER);
      drinkField = new JTextField(10);
      drinkSubmitButton = new JButton ("Submit");
      drinkSubmitButton.addActionListener(this);
      
      infectionChance = 50;
      for (int x = 0; x < 8; x++)
      {
         itemsHeld[x] = false;
         itemsEquipped[x] = false;
      }
      
      askName();
   }
   /** Prompts the user to enter his/her name into the JTextField displayed
    */
   public void askName() {      
      for (int x = 0; x < 4; x++)
         frame.add(new JLabel());   
      frame.add (nameTitle); 
      for (int x = 0; x < 2; x++)
         frame.add(new JLabel());  
      frame.add(nameField);
      for (int x = 0; x < 2; x++)
         frame.add(new JLabel());  
         
      JPanel submitPane = new JPanel();
      submitPane.setLayout(new GridLayout(3, 3));
      for (int x = 0; x < 4; x++)
         submitPane.add(new JLabel()); 
      submitPane.add(nameSubmitButton, BorderLayout.CENTER);
      for (int x = 0; x < 4; x++)
         submitPane.add(new JLabel()); 
      frame.add(submitPane);
      
      for (int x = 0; x < 4; x++)
         frame.add(new JLabel());     
     
      frame.revalidate();
      frame.repaint();
   }
   /** Prompts the user to select his/her gender from the three JButton options displayed
    */
   public void askGender () {
      frame.getContentPane().removeAll();
      for (int x = 0; x < 4; x++)
         frame.add(new JLabel());   
      frame.add (genderTitle); 
      for (int x = 0; x < 2; x++)
         frame.add(new JLabel());  
      
      JPanel genderOptions = new JPanel();
      genderOptions.setLayout(new GridLayout(1,3));
      genderOptions.add(genderButtonMale);
      genderOptions.add(genderButtonFemale);
      genderOptions.add(genderButtonOther);
      frame.add(genderOptions);
   
      for (int x = 0; x < 7; x++)
         frame.add(new JLabel());  
      frame.setVisible(true);
      frame.repaint();
   }
   /** Makes a weighted random calculation on whether or not the player has been infected
    * This randomness simulates real life situations where an cautious citizen may contract disease (COVID-19), while an uncaring citizen may be perfectly healthy
    * @return True or false depending on the weighted random calculation
    */
   public boolean calcInfected () {
      int randNum = (int)(Math.random() * 100);
      if (randNum > infectionChance)
         return true;
      return false;
   }
   /** Prompts the user to enter his/her gender favorite bubble tea flavor into the JTextField displayed
    */
   public void askDrink() {    
      frame.getContentPane().removeAll();  
      for (int x = 0; x < 4; x++)
         frame.add(new JLabel());   
      frame.add (drinkTitle); 
      for (int x = 0; x < 2; x++)
         frame.add(new JLabel());  
      frame.add(drinkField);
      for (int x = 0; x < 2; x++)
         frame.add(new JLabel());  
         
      JPanel submitPane = new JPanel();
      submitPane.setLayout(new GridLayout(3, 3));
      for (int x = 0; x < 4; x++)
         submitPane.add(new JLabel()); 
      submitPane.add(drinkSubmitButton, BorderLayout.CENTER);
      for (int x = 0; x < 4; x++)
         submitPane.add(new JLabel()); 
      frame.add(submitPane);
      
      for (int x = 0; x < 4; x++)
         frame.add(new JLabel());     
     
      frame.revalidate();
      frame.repaint();
   }
   /** Called when an action occurs (a button is pressed); performs a specific action based on the source of the action (which button was pressed).
    * @param e The ActionEvent which triggered the function
    */
   public void actionPerformed (ActionEvent e)
   {
      if (e.getSource() == nameSubmitButton)
      {
         name = nameField.getText().trim();
         askGender();
      } 
      else if (e.getSource() == genderButtonMale)
      {
         gender = "Male";
         askDrink();
      }
      else if (e.getSource() == genderButtonFemale)
      {
         gender = "Female";
         askDrink();
      }
      else if (e.getSource() == genderButtonOther)
      {
         gender = "Other";
         askDrink();
      }
      else if (e.getSource() == drinkSubmitButton)
      {
         favoriteDrink = drinkField.getText().trim();
         menu.startGame();
      }
   }
}