/*
 * Due June 15, 2020
 * Alan Li & Aidan Ang
 * MainMenu
 * Mrs. Krasteva
 * The purpose of this program is to represent the main menu of our game, which can go to instructions, quit the game or allow
 * the user to play the game
 * 
 * 
 * 
 * Revisions
 * - May 27: Created ~ Alan Li
 * - May 28: Updated ~ Alan Li
 * - May 30: Updated ~ Alan Li
 * - June 10: Updated ~ Alan Li
 * - June 11: Commented ~ Alan Li
 * - June 12: Finished ~ Alan Li
 */
//importing needed libraries
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
/*
 * represents as the menu for the game  
 * implements actionlistener for the three buttons that are pressed
 */
public class MainMenu implements ActionListener {
    /** JFrame used for this class to add all of the buttons and background to*/
   JFrame frame;
   
   /** Object to store the input and choices that the user picks*/
   Player player;
   
   /** JButton to allow for the user to go to the game*/
   private JButton playButton = new SemiTransparentButton("Play Game");
   
   /** JButton to allow for the user to go to instructions page*/
   private JButton instructionButton = new SemiTransparentButton("Instructions");
   
   /** JButton to allow for the user to quit the game*/
   private JButton quitButton = new SemiTransparentButton("Quit Game");
   
   /** JLabel to store the game title and display it on the screen*/
   private JLabel title = new JLabel("Bubbles to the Brim", SwingConstants.CENTER);
   
   /** Array of JPanels to store the buttons and the title*/
   private JPanel menuGridLayout[] = new JPanel[4];
   
   /** JPanel to put the buttons together and include the background*/
   private JPanel screen;
   
   /** BufferedImage to load the background*/
   BufferedImage background;
   
   /**
    * Constructor to initialize the values, add ActionListeners, and draw the menu
    * @param frameIn                      original frame to add all of the JPanels, buttons and display it to the user
    * @param playerIn                     used to keep track of the player data that has been stored in this object
    */
   public MainMenu(JFrame frameIn, Player playerIn){
      frame = frameIn;
      player = playerIn;
      loadImages();
      setUpFrame();
      title.setFont(new Font("Serif", Font.PLAIN, 50));
      playButton.addActionListener(this);
      instructionButton.addActionListener(this);
      quitButton.addActionListener(this);
      playButton.setPreferredSize(new Dimension(150, 50));
      instructionButton.setPreferredSize(new Dimension(150, 50));
      quitButton.setPreferredSize(new Dimension(150, 50));
      playButton.setContentAreaFilled(false);
      instructionButton.setContentAreaFilled(false);
      quitButton.setContentAreaFilled(false);
      drawMenu();
   }
   
   /** 
    * method to set up the frame
    */
   public void setUpFrame(){
      frame.setLayout(new GridLayout());
      frame.getContentPane().removeAll();
      frame.setTitle("Main Menu");
      screen = new BackgroundPanel();
      screen.setLayout(new GridLayout(4, 1));
   }
   
   /**
    * method to draw the menu
    */
   public void drawMenu(){
      for(int i = 0; i<4; i++){
         menuGridLayout[i] = new JPanel();
         menuGridLayout[i].setOpaque(false);
         menuGridLayout[i].setBorder(new EmptyBorder(10, 10, 10, 10));
         if(i==0){
            menuGridLayout[i].add(title);
         }
         else if(i==1){
            menuGridLayout[i].add(playButton);
         }
         else if(i==2){
            menuGridLayout[i].add(instructionButton);
         }
         else{
            menuGridLayout[i].add(quitButton);
         }
         screen.add(menuGridLayout[i]);
      }
      frame.add(screen);
      frame.revalidate();
      frame.repaint();
   }
   
   /**
    * method to start the game once the start button is pressed
    */
   public void startGame() {
      LevelOne lvlOne = new LevelOne(frame, player, this);
   }
   
   /**
    * method to check if a button has been pressed or not
    * @param e               checks if an action has been performed or not (clicking a button)
    */ 
   public void actionPerformed(ActionEvent e){
      if(e.getSource()==playButton){
         player = new Player(frame, this);
      }
      else if(e.getSource()==instructionButton){
         Instructions i = new Instructions (frame, this);
      }
      else if(e.getSource()==quitButton){
         System.exit(0);
      }
   }
   
   /**
    * method to load the background
    */
   public void loadImages() {
      try {
         background = ImageIO.read(new File("Images/mainMenuBackground.png"));
      } catch (IOException e) {}
   }
   /**
    * class for the JPanel to be initialized to so it can get the background
    */
   private class BackgroundPanel extends JPanel{
       /**
        * method that is overridden to draw the background
        * @param g                  Graphics object to draw the background 
        */
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(background, 0, 0, this);
      }
   }
}