/**
 * Due: June 15, 2020
 * Aidan Ang
 * Alan Li
 * LevelTwo
 * Mrs. Krasteva, ICS 4UO
 * This class performs the processing and displays Level 2 of our game, Bubbles to the Brim
 * \
 * 
 * 
 * Revisions
 * - June 1: Created ~ Aidan Ang
 * - June 2: Updated ~ Aidan Ang
 * - June 4: Updated ~ Aidan Ang
 * - June 7: Updated ~ Aidan Ang
 * - June 8: Updated ~ Aidan Ang
 * - June 9: Updated ~ Alan Li
 * - June 10: Updated ~ Aidan Ang
 * - June 11: Commented ~ Alan Li
 * - June 11: Commented ~ Aidan Ang
 * - June 12: Updated ~ Aidan Ang
 * - June 13: Updated ~ Aidan Ang
 * - June 13: Commented ~ Aidan Ang
 * - June 14: Updated ~ Aidan Ang
 * - June 17: Finished ~ Aidan Ang
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
/** Performs processing and displays Level 2 of Bubbles to the Brim
 */
public class LevelTwo extends JPanel implements ActionListener {
  /** The Player instance data should be drawn from */
   private Player player;
   /** The frame to display graphics on */
   private JFrame frame;
   /** MainMenu instance to return to upon game completion */
   private MainMenu menu;
   /** Array of each scene background in BufferedImage form */
   private BufferedImage [] sceneBGs = new BufferedImage [4];
   /** 2D Array of JButtons used in scenes; each row represents a scene */
   private JButton [] [] sceneButtons = new JButton [6] [15];
   /** Array of each scene's JPanel */
   private JPanel [] scenePanels = new JPanel [5];
   /** Whether the player has washed their hands in this instance of LevelTwo */
   boolean washedHands = false;
   /** How many times the player missed the "Click me!" button in the minigame in this instance of LevelTwo */
   int misses;
   
   /** Constructs a new LevelTwo, displaying graphics to the specified frame, 
    * drawing data from the specified Player instance and returning to the specified MainMenu instance
    * @param frameIn The frame to display graphics on
    * @param playerIn The Player instance to draw player data from
    * @param menuIn The MainMenu instance to return to once the player completes the game
    */
   public LevelTwo(JFrame frameIn, Player playerIn, MainMenu menuIn) {
      player = playerIn;
      frame = frameIn;
      menu = menuIn;
      setUpFrame();
      loadPanels();
      calibrateButtons();
      displayScene(1,1);
      frame.revalidate();
      frame.repaint();
   }
   /** Sets up the frame for display
    */
   public void setUpFrame() {
      frame.getContentPane().removeAll();
      frame.setLayout(new GridLayout());
      frame.setTitle("Level 2: Bubbles to the Brim!");
   }
   /** Loads background images into appropriate variables
    */
   public void loadImages() {
      try {
         sceneBGs[0] = ImageIO.read(new File("Images/scene1.png"));
         sceneBGs[1] = ImageIO.read(new File("Images/scene2.png"));
         sceneBGs[2] = ImageIO.read(new File("Images/scene3.png"));
         sceneBGs[3] = ImageIO.read(new File("Images/scene4.png"));
      } catch (IOException e) {}
   }
   /** Prepares all JPanels for display - initializes panels with correct background
    */
   public void loadPanels() {
      scenePanels [0] = new BackgroundPanel ("Images/scene1.png");
      scenePanels [1] = new BackgroundPanel ("Images/scene2.png");
      scenePanels [2] = new BackgroundPanel ("Images/scene3.png");
      scenePanels [3] = new BackgroundPanel ("Images/scene4.png");
      for (int x = sceneBGs.length; x < scenePanels.length; x++) {
         scenePanels [x] = new JPanel();
         scenePanels [x].setBackground(Color.black);
      }
   }
   /** Sets up all JButtons - adds ActionListeners, sets aesthetics like font and text
    */
   public void calibrateButtons() {
      for (int y = 0; y < sceneButtons.length; y++)
      {
         for (int x = 0; x < sceneButtons[y].length; x++)
         {
            sceneButtons [y][x] = new UnicornButton();
            sceneButtons [y][x].addActionListener(this);
            sceneButtons [y][x].setText("<html><center>Continue</center></html>");
            sceneButtons [y][x].setContentAreaFilled(false);
            sceneButtons [y][x].setFont(new Font("Dialog", Font.PLAIN, 24));
         }
      }
      sceneButtons[0][3].setText("<html><center>Yes. <b>Put on latex gloves.</b></center></html>");
      sceneButtons[0][4].setText("<html><center>Yes. <b>Put on N-95 facemask.</b></center></html>");
      sceneButtons[0][5].setText("<html><center>No. <b>Continue on into store.</b></center></html>");
      sceneButtons[1][2].setFont(new Font("Dialog", Font.PLAIN, 12));
      sceneButtons[1][2].setText("<html><center>Step out of line and order on your phone.</center></html>");
      sceneButtons[1][3].setFont(new Font("Dialog", Font.PLAIN, 12));
      sceneButtons[1][3].setText("<html><center>Step out of line and order at the kiosk.</center></html>");
      sceneButtons[1][4].setFont(new Font("Dialog", Font.PLAIN, 12));
      sceneButtons[1][4].setText("<html><center>Stay in line and wait for your turn.</center></html>");
      sceneButtons[1][6].setText("<html><center>A " + player.favoriteDrink + " please.</center></html>");
      sceneButtons[1][7].setText("<html><center>Card, thanks.</center></html>");
      sceneButtons[1][8].setText("<html><center>Cash, thanks.</center></html>");
      sceneButtons[2][0].setFont(new Font("Dialog", Font.PLAIN, 16));
      sceneButtons[2][0].setText("<html><center>wipe your drink with Lysol<br>" +
         "wipes before you take it.</center></html>");
      sceneButtons[2][1].setFont(new Font("Dialog", Font.PLAIN, 16));
      sceneButtons[2][1].setText("<html><center>take a sip, savoring<br>" +
         "the taste of your drink.</center></html>");
      sceneButtons[2][2].setFont(new Font("Dialog", Font.PLAIN, 16));
      sceneButtons[2][2].setText("<html><center>consume a couple Vitamin C<br>" +
         "pills before you enjoy your drink.</center></html>");
      sceneButtons[2][7].setText("<html><center><i>What's going on " + player.name + "?</i></center></html>");
      sceneButtons[2][8].setText("<html><center>Hey man!</center></html>");
      sceneButtons[2][9].setText("<html><center>It's been a while!</center></html>");
      sceneButtons[2][10].setText("<html><center>Oh! Hi Steve.</center></html>");
      sceneButtons[3][0].setFont(new Font("Dialog", Font.PLAIN, 16));
      sceneButtons[3][0].setText("<html><center><b>Move to the seat opposite to him</b> (<i>\"Geez, COVID-19 is scary, don't risk it man.\"</i>)</center></html>");
      sceneButtons[3][1].setFont(new Font("Dialog", Font.PLAIN, 16));
      sceneButtons[3][1].setText("<html><center><b>Start a conversation</b> (<i>\"So, how are you doing?\"</i>)</center></html>");
      sceneButtons[4][0].setFont(new Font("Dialog", Font.PLAIN, 20));
      sceneButtons[4][0].setText("<html><center>take some antibiotics</center></html>");
      sceneButtons[4][1].setFont(new Font("Dialog", Font.PLAIN, 20));
      sceneButtons[4][1].setText("<html><center>use hand sanitizer</center></html>");
      sceneButtons[4][2].setFont(new Font("Dialog", Font.PLAIN, 20));
      sceneButtons[4][2].setText("<html><center>rinse your nose<br>" +
         "with saline</center></html>");
      sceneButtons[4][3].setFont(new Font("Dialog", Font.PLAIN, 20));
      sceneButtons[4][3].setText("<html><center>drink some hot water<br>" +
         "from your thermos</center></html>");
      sceneButtons[4][4].setFont(new Font("Dialog", Font.PLAIN, 20));
      sceneButtons[4][4].setText("<html><center>go to the washroom<br>to wash your hands</center></html>");
      sceneButtons[4][5].setFont(new Font("Dialog", Font.PLAIN, 20));
      sceneButtons[4][5].setText("<html><center>go home</center></html>");
   }
   /** Displays appropriate scene based on specified scene and scene part
        * @param scene The scene number
        * @param part The part number
    */
   public void displayScene(int scene, int part){
      frame.getContentPane().removeAll();
      if (scene == 1) {
         if (part == 1) {
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Finally... a bubble tea shop!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][0]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs); 
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 2) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Because of the COVID-19 quarantine, it's been a looong time since you last saw one of these storefronts.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][1]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 3) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("<html><center>" +
               "At this point, you can't resist the urge for a good bubble tea,<br>" +
               "so you've ventured out to your local bubble tea shop, AA Tea Co." +
               "</html>"
               , SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][2]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 4) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("<html><center>" +
                "Of course, you've come prepared.<br>" +
                "Would you like to equip any of the items you've brought before you enter the shop?" +
                "</html>"
                , SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 1);
            buttonLocs.add(sceneButtons [0][3]);
            buttonLocs.add(sceneButtons [0][4]);
            buttonLocs.add(sceneButtons [0][5]);
            addJLabel(buttonLocs, 1);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 5) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Latex gloves equipped!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][6]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 6) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("N-95 facemask equipped!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][7]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 7) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Latex gloves unequipped!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][8]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 8) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("N-95 facemask unequipped!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][9]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 9) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Sorry, you didn't bring latex gloves.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][10]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         else if (part == 10) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Sorry, you didn't bring an N-95 facemask.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [0][11]);
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());   
         }
         frame.add(scenePanels[0]);
      }
      else if (scene == 2) {
         if (part == 1) {
            scenePanels[1].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You enter the shop.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[1].add(tempText);
            addJLabel(scenePanels[1], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [1][0]);
            addJLabel(buttonLocs, 2);
            scenePanels[1].add(buttonLocs);  
            scenePanels[1].add(new JLabel());
         }
         else if (part == 2) {
            scenePanels[1].removeAll();
            scenePanels[1].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Unsurprisingly, there's a huge line. Even with COVID-19, people will do anything for bubble tea.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[1].add(tempText);
            addJLabel(scenePanels[1], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [1][1]);
            addJLabel(buttonLocs, 2);
            scenePanels[1].add(buttonLocs);  
            scenePanels[1].add(new JLabel());
         }
         else if (part == 3) {
            Minigame game = new Minigame(frame, this);
         }
         else if (part == 4) {
            scenePanels[1].removeAll();
            scenePanels[1].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You realize that you could save a lot of time ordering on your phone, or even by using the self-checkout. Do you:", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[1].add(tempText);
            addJLabel(scenePanels[1], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 1);
            for (int x = 2; x <= 4; x++)
               buttonLocs.add(sceneButtons [1][x]);
            addJLabel(buttonLocs, 1);
            scenePanels[1].add(buttonLocs);  
            scenePanels[1].add(new JLabel());
         }
         else if (part == 5) {
            scenePanels[1].removeAll();
            scenePanels[1].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You place your order, and stand back to wait.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[1].add(tempText);
            addJLabel(scenePanels[1], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [1][5]);
            addJLabel(buttonLocs, 2);
            scenePanels[1].add(buttonLocs);  
            scenePanels[1].add(new JLabel());
         }
         else if (part == 6) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Hi there! What can I get for you today?", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [1][6]); // user-inputted favorite drink
            addJLabel(buttonLocs, 2);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 7) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Ok, that will be $" + player.favoriteDrink.length() + ".78. How will you be paying?", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 1);
            buttonLocs.add(sceneButtons [1][7]); // "Card, thanks."
            addJLabel(buttonLocs, 1);
            buttonLocs.add(sceneButtons [1][8]); // "Cash, thanks."
            addJLabel(buttonLocs, 1);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         if (part != 3) {
            if (part >= 1 && part <= 5)
               frame.add(scenePanels[1]);
            else
               frame.add(scenePanels[2]);
         }
      }
      else if (scene == 3) {
         if (part == 1) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("<html>Finally, you hear your name called. \"<i>" + player.name + "?</i>\" " + "As you approach the counter, you see your drink. You: <html>", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 1);
            for (int x = 0; x <= 2; x++)
               buttonLocs.add(sceneButtons [2][x]); // "wipe your drink with Lysol wipes before you take it.", "take a sip, savoring the taste of your drink.", "consume a couple Vitamin C pills before you enjoy your drink."
            addJLabel(buttonLocs, 1);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 2) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Drink wiped.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [2][3]); 
            addJLabel(buttonLocs, 2);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 3) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You don't have any wipes", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [2][4]);
            addJLabel(buttonLocs, 2);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 4) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You swallow a few Vitamin C pills.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [2][5]);
            addJLabel(buttonLocs, 2);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 5) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You don't have any Vitamin C pills.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [2][6]);
            addJLabel(buttonLocs, 2);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 6) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You turn around, ready to enjoy your drink. Suddenly, you hear a voice greet you.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [2][7]); // "What's going on?"
            addJLabel(buttonLocs, 2);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         else if (part == 7) {
            scenePanels[2].removeAll();
            scenePanels[2].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("<html><center>" +
                "\"<i>I haven't seen you in so long!</i>\"<br>" +
                "It's your old friend Steve." +
                "</html>"
                , SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[2].add(tempText);
            addJLabel(scenePanels[2], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 1);
            for (int x = 8; x <= 10; x++)
               buttonLocs.add(sceneButtons [2][x]); // "Hey man!", "It's been a while!", "Oh! Hi Steve."
            addJLabel(buttonLocs, 1);
            scenePanels[2].add(buttonLocs);  
            scenePanels[2].add(new JLabel());
         }
         frame.add(scenePanels[2]);
      }
      else if (scene == 4) {
         if (part == 1) {
            scenePanels[3].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You sit down at a nearby open booth. Steve sits down beside you", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[3].add(tempText);
            addJLabel(scenePanels[3], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 1);
            buttonLocs.add(sceneButtons [3][0]); // Move to the seat opposite to him ("Geez, COVID-19 is scary, don't risk it man.")
            addJLabel(buttonLocs, 1);
            buttonLocs.add(sceneButtons [3][1]); // Start a conversation ("So, how are you doing?")
            addJLabel(buttonLocs, 1);
            scenePanels[3].add(buttonLocs); 
            scenePanels[3].add(new JLabel());   
         }
         else if (part == 2) {
            scenePanels[3].removeAll();
            scenePanels[3].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You get up and move to the seat across from him.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[3].add(tempText);
            addJLabel(scenePanels[3], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [3][2]);
            addJLabel(buttonLocs, 2);
            scenePanels[3].add(buttonLocs);  
            scenePanels[3].add(new JLabel());
         }
         else if (part == 3) {
            scenePanels[3].removeAll();
            scenePanels[3].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("The two of you quickly slip into conversation, catching up on each other's lives.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[3].add(tempText);
            addJLabel(scenePanels[3], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [3][3]); 
            addJLabel(buttonLocs, 2);
            scenePanels[3].add(buttonLocs);  
            scenePanels[3].add(new JLabel());
         }
         else if (part == 4) {
            scenePanels[3].removeAll();
            scenePanels[3].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("After a while, Steve tells you his family is expecting him at home.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[3].add(tempText);
            addJLabel(scenePanels[3], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [3][4]);
            addJLabel(buttonLocs, 2);
            scenePanels[3].add(buttonLocs);  
            scenePanels[3].add(new JLabel());
         }
         else if (part == 5) {
            scenePanels[3].removeAll();
            scenePanels[3].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("Exchanging promises to keep in touch, you part ways.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[3].add(tempText);
            addJLabel(scenePanels[3], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [3][5]); 
            addJLabel(buttonLocs, 2);
            scenePanels[3].add(buttonLocs);  
            scenePanels[3].add(new JLabel());
         }
         frame.add(scenePanels[3]);
      }
      else if (scene == 5) {
         if (part == 1) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("<html><center>" +
               "As you leave, you realize you were exposed to a lot of potential COVID-19-infected surfaces.<br>" +
               "As a precautionary measure, you:" +
               "</center></html>"
               , SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 6);
            JPanel buttonLocsA = new JPanel();
            buttonLocsA.setOpaque(false);
            buttonLocsA.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocsA, 1);
            for (int x = 0; x <= 2; x++)
               buttonLocsA.add(sceneButtons [4][x]); // "take some antibiotics", "use hand sanitizer", "rinse your nose with saline"
            addJLabel(buttonLocsA, 1);
            scenePanels[0].add(buttonLocsA);  
            JPanel buttonLocsB = new JPanel();
            buttonLocsB.setOpaque(false);
            buttonLocsB.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocsB, 1);
            for (int x = 3; x <= 5; x++)
               buttonLocsB.add(sceneButtons [4][x]); // "drink some hot water from your thermos", "go to the washroom to wash your hands", "go home"
            addJLabel(buttonLocsB, 1);
            scenePanels[0].add(buttonLocsB);  
         }
         else if (part == 2) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You take some antibiotics.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][6]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 3) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You don't have any antibiotics on you.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][7]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 4) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You use your hand sanitizer.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][8]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 5) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You don't have any hand sanitizer on you.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][9]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 6) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You rinse your nose with saline.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][10]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 7) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You don't have any saline on you.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][11]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 8) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You drink some hot water.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][12]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 9) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You don't have any hot water on you.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][13]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 10) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You go back in the store to wash your hands, then come back out again.", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][12]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         else if (part == 11) {
            scenePanels[0].removeAll();
            scenePanels[0].setLayout(new GridLayout(10, 1));
            JLabel tempText = new SemiTransparentLabel("You already washed your hands!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            scenePanels[0].add(tempText);
            addJLabel(scenePanels[0], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [4][13]); 
            addJLabel(buttonLocs, 2);
            scenePanels[0].add(buttonLocs);  
            scenePanels[0].add(new JLabel());
         }
         frame.add(scenePanels[0]);
      }
      else if (scene == 6) {
         if (part == 1) {
            scenePanels[4].setLayout(new GridLayout(10, 1));
            if (player.infectionChance > 100)
               player.infectionChance = 100;
            JLabel tempText = new JLabel("Your chance of infection was... " + player.infectionChance + "%!", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
            if (player.infectionChance > 75)
               tempText.setForeground(Color.RED);
            else if (player.infectionChance > 50)
               tempText.setForeground(Color.ORANGE);
            else
               tempText.setForeground(Color.GREEN);
            scenePanels[4].add(tempText);
            addJLabel(scenePanels[4], 7);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(1, 5));
            addJLabel(buttonLocs, 2);
            buttonLocs.add(sceneButtons [5][0]); 
            addJLabel(buttonLocs, 2);
            scenePanels[4].add(buttonLocs);  
            scenePanels[4].add(new JLabel());
         }
         if (part == 2) {
            scenePanels[4].removeAll();
            scenePanels[4].setLayout(new GridLayout(3, 1));
            JLabel tempText = new JLabel("YOU WIN", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.BOLD, 72));
            tempText.setForeground(Color.GREEN);
            scenePanels[4].add(tempText);
            JLabel tempSubText;
            if (player.infectionChance > 75)
               tempSubText = new JLabel("You got lucky!", SwingConstants.CENTER);
            else if (player.infectionChance > 50)
               tempSubText = new JLabel("That was close!", SwingConstants.CENTER);
            else
               tempSubText = new JLabel("Good job!", SwingConstants.CENTER);
            tempSubText.setFont(new Font("Dialog", Font.BOLD, 24));
            tempSubText.setForeground(Color.GREEN);
            scenePanels[4].add(tempSubText);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(5, 5));
            addJLabel(buttonLocs, 12);
            buttonLocs.add(sceneButtons [5][1]); 
            addJLabel(buttonLocs, 12);
            scenePanels[4].add(buttonLocs);  
         }
         else if (part == 3) {
            scenePanels[4].removeAll();
            scenePanels[4].setLayout(new GridLayout(3, 1));
            JLabel tempText = new JLabel("YOU LOSE", SwingConstants.CENTER);
            tempText.setFont(new Font("Dialog", Font.BOLD, 72));
            tempText.setForeground(Color.RED);
            scenePanels[4].add(tempText);
            JLabel tempSubText;
            if (player.infectionChance > 75)
               tempSubText = new JLabel("Do better next time!", SwingConstants.CENTER);
            else if (player.infectionChance > 50)
               tempSubText = new JLabel("Good try!", SwingConstants.CENTER);
            else
               tempSubText = new JLabel("Aw, unlucky!", SwingConstants.CENTER);
            tempSubText.setFont(new Font("Dialog", Font.BOLD, 24));
            tempSubText.setForeground(Color.RED);
            scenePanels[4].add(tempSubText);
            JPanel buttonLocs = new JPanel();
            buttonLocs.setOpaque(false);
            buttonLocs.setLayout(new GridLayout(5, 5));
            addJLabel(buttonLocs, 12);
            buttonLocs.add(sceneButtons [5][1]); 
            addJLabel(buttonLocs, 12);
            scenePanels[4].add(buttonLocs);
         }
         frame.add(scenePanels[4]);
      }
      frame.revalidate();
      frame.repaint();
   }
   /** Adds a specified amount of empty JLabels to the specified JPanel; used for spacing
        * @param panel JPanel to add JLabels to
        * @param numLabels The number of JLabels to add to panel
    */
   public void addJLabel (JPanel panel, int numLabels) {
      for (int x = 0; x < numLabels; x++)
         panel.add(new JLabel());
   }
   /** Called when an action occurs (a button is pressed); performs a specific action based on the source of the action (which button was pressed).
     * Generally displays the next appropriate scene
        * @param e The ActionEvent which triggered the function
    */
   public void actionPerformed (ActionEvent e)
   {
      if (e.getSource() == sceneButtons [0][0])
      {
         displayScene(1,2);
      }
      else if (e.getSource() == sceneButtons [0][1])
      {
         displayScene(1,3);
      }
      else if (e.getSource() == sceneButtons [0][2])
      {
         displayScene(1,4);
      }
      else if (e.getSource() == sceneButtons [0][3])
      {
         if (player.itemsHeld[1] == true) {
            if (player.itemsEquipped[1] == false) {
               player.itemsEquipped[1] = true;
               displayScene(1,5);
            }
            else {
               player.itemsEquipped[1] = false;
               displayScene(1,7);
            }
         }
         else {
            displayScene(1,9);
         }
      }
      else if (e.getSource() == sceneButtons [0][4])
      {
         if (player.itemsHeld[3] == true) {
            if (player.itemsEquipped[3] == false) {
               player.itemsEquipped[3] = true;
               displayScene(1,6);
            }
            else {
               player.itemsEquipped[3] = false;
               displayScene(1,8);
            }
         }
         else {
            displayScene(1,10);
         }
      }
      else if (e.getSource() == sceneButtons [0][5])
      {
         displayScene(2,1);
      }
      else if (e.getSource() == sceneButtons [0][6] || e.getSource() == sceneButtons [0][7] || e.getSource() == sceneButtons [0][8] || 
         e.getSource() == sceneButtons [0][9] || e.getSource() == sceneButtons [0][10] || e.getSource() == sceneButtons [0][11])
      {
         displayScene(1,4);
      }
      else if (e.getSource() == sceneButtons [1][0])
      {
         displayScene(2,2);
      }
      else if (e.getSource() == sceneButtons [1][1])
      {
         displayScene(2,3);
      }
      else if (e.getSource() == sceneButtons [1][2]) {
         displayScene(2,5);
      }
      else if (e.getSource() == sceneButtons [1][3]) {
         if (player.itemsEquipped[1] == false)
            player.infectionChance += 20;
         displayScene(2,5);
      } 
      else if (e.getSource() == sceneButtons [1][4])
      {
         if (player.itemsEquipped[3] == true)
            player.infectionChance += 10;
         else
            player.infectionChance += 20;
         displayScene(2,6);
      }
      else if (e.getSource() == sceneButtons [1][6])
      {
         displayScene(2,7);
      }
      else if (e.getSource() == sceneButtons [1][7] || e.getSource() == sceneButtons [1][8])
      {
         displayScene(2,5);
      }
      else if (e.getSource() == sceneButtons [1][5])
      {
         displayScene(3,1);
      }
      else if (e.getSource() == sceneButtons [1][11])
      {
         displayScene(2,4);
      }
      else if (e.getSource() == sceneButtons [2][0])
      {
         if (player.itemsHeld[2] == true) {
            if (player.itemsHeld[1] == true)
               player.infectionChance -= 10;
            else
               player.infectionChance -= 20;
            player.itemsHeld[2] = false;
            displayScene(3,2);
         }
         else
            displayScene(3,3);
      }
      else if (e.getSource() == sceneButtons [2][1]) {
         if (player.itemsEquipped[1] == true)
            player.infectionChance += 10;
         else
            player.infectionChance += 20;
         displayScene(3,6);
      }
      else if (e.getSource() == sceneButtons [2][2]) {
         if (player.itemsHeld[7] == true) {
            player.itemsHeld[7] = false;
            displayScene(3,4);
         }
         else
            displayScene(3,5);
      }
      else if (e.getSource() == sceneButtons [2][3] || e.getSource() == sceneButtons [2][4] || e.getSource() == sceneButtons [2][5] || e.getSource() == sceneButtons [2][6])
      {
         displayScene(3,1);
      }
      else if (e.getSource() == sceneButtons [2][7])
      {
         displayScene(3,7);
      }
      else if (e.getSource() == sceneButtons [2][8] || e.getSource() == sceneButtons [2][9] || e.getSource() == sceneButtons [2][10])
      {
         displayScene(4,1);
      }
      else if (e.getSource() == sceneButtons [3][0])
      {
         displayScene(4,2);
      }
      else if (e.getSource() == sceneButtons [3][1])
      {
         if (e.getSource() == sceneButtons [3][1]) {
            if (player.itemsEquipped[3] == true)
               player.infectionChance += 10;
            else
               player.infectionChance += 20;
         }
         displayScene(4,3);
      }
      else if (e.getSource() == sceneButtons [3][2])
      {
         displayScene(4,3);
      }
      else if (e.getSource() == sceneButtons [3][3])
      {
         displayScene(4,4);
      }
      else if (e.getSource() == sceneButtons [3][4])
      {
         displayScene(4,5);
      }
      else if (e.getSource() == sceneButtons [3][5])
      {
         displayScene(5,1);
      }
      else if (e.getSource() == sceneButtons [4][0])
      {
         if (player.itemsHeld[0] == true) {
            player.itemsHeld[0] = false;
            displayScene(5,2);
         }
         else 
            displayScene(5,3);
      }
      else if (e.getSource() == sceneButtons [4][1])
      {
         if (player.itemsHeld[4] == true) {
            player.itemsHeld[4] = false;
            displayScene(5,4);
         }
         else 
            displayScene(5,5);
      }
      else if (e.getSource() == sceneButtons [4][2])
      {
         if (player.itemsHeld[5] == true) {
            player.itemsHeld[5] = false;
            displayScene(5,6);
         }
         else 
            displayScene(5,7);
      }
      else if (e.getSource() == sceneButtons [4][3])
      {
         if (player.itemsHeld[6] == true) {
            player.itemsHeld[6] = false;
            displayScene(5,8);
         }
         else 
            displayScene(5,9);
      }
      else if (e.getSource() == sceneButtons [4][4])
      {
         if (washedHands == false) {
            if (player.itemsEquipped[1] == true) {
               player.infectionChance -= 20;
            }
            washedHands = true;
            displayScene(5,10);
         }
         else
            displayScene(5,11);
      }
      else if (e.getSource() == sceneButtons [4][5])
      {
         player.infectionChance += (20 * misses);
         displayScene(6,1);
      }
      else if (e.getSource() == sceneButtons [4][6] || e.getSource() == sceneButtons [4][7] || e.getSource() == sceneButtons [4][8] || e.getSource() == sceneButtons [4][9] || 
         e.getSource() == sceneButtons [4][10] || e.getSource() == sceneButtons [4][11] || e.getSource() == sceneButtons [4][12] || e.getSource() == sceneButtons [4][13])
      {
         displayScene(5,1);
      }
      else if (e.getSource() == sceneButtons [5][0])
      {
         if (player.name.equals("Aidan") || player.name.equals("Alan"))
            displayScene(6,2);
         else {
            boolean win = player.calcInfected();
            if (win)
               displayScene(6,2);
            else
               displayScene(6,3);
         }
      }
      else if (e.getSource() == sceneButtons [5][1])
      {
         menu.setUpFrame();
         menu.drawMenu();
      }
   }
}
