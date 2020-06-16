/**
 * Due: June 15, 2020
 * Aidan Ang
 * Alan Li
 * Minigame
 * Mrs. Krasteva, ICS 4UO
 * This class performs the processing and displays the minigame which takes place within Level 2 of our game, Bubbles to the Brim
 * 
 * 
 * Revisions
 * - June 8: Created ~ Alan Li
 * - June 9: Updated ~ Alan Li
 * - June 10: Updated ~ Alan Li
 * - June 11: Updated ~ Aidan Ang
 * - June 11: Updated ~ Alan Li
 * - June 12: Updated ~ Aidan Ang
 * - June 13: Commented ~ Alan Li
 * - June 14: Commented ~ Aidan Ang
 * - JUne 14: Finished ~ Alan Li
 */
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.time.*;
import javax.imageio.*;
/** Performs the processing and displays minigame which takes place within Level 2
 * Randomly displays a button, which expires in one second and moves to a new location if the player does not click it in time.
 * If the button expires, the player has "touched their face" and an amount will be added to their infectionChance (stored in the Player class)
 */
public class Minigame extends JPanel implements ActionListener {
  /** The randomly moving button */
   private JButton randomMovingButton = new JButton("Click me!");
   /** The button to progress through the intro scene */
   private JButton introButton = new JButton("Begin");
   /** The button to progress through the outro scene */
   private JButton outroButton = new JButton("<html><i>Sigh</i></html>");
   /** The background image */
   BufferedImage storeInside;
   /** The frame to display graphics on */
   JFrame frame;
   /** The LevelTwo instance to return to */
   LevelTwo lvlTwo;
   /** The number of buttons shown so far in this instance of Minigame */
   private int buttonsShown;
   /** The number of buttons the player will see in this instance of Minigame */
   private int pressRequirement;
   /** Number of clicks the player has missed in this instance of Minigame */
   public int missedClicks;
   /** Output JPanel */
   JPanel screen;
   /** Application's timer */
   Timer timer;
   /** Constructs a new Minigame, displaying graphics to the specified frame and returning to the specified LevelTwo instance upon completion
    * @param frameIn The frame to display graphics on
    * @param lvlTwoIn The LevelTwo instance to return to once the player completes the game
    */
   public Minigame(JFrame frameIn, LevelTwo lvlTwoIn) {
      frame = frameIn;
      lvlTwo = lvlTwoIn;
      loadImages();
      buttonsShown = 0;
      pressRequirement = (int)(Math.random()*6)+5;
      missedClicks = 0;
      randomMovingButton.addActionListener(this);
      randomMovingButton.setForeground(Color.RED);
      introButton.addActionListener(this);
      outroButton.addActionListener(this);
      
      setUpFrame();
      cutScene(1);
   }
   /** Moves button, adds one to missedClicks and schedules itself one second in the future if the user does not click the button before two seconds elapse
    * Is a TimerTask
    */
   class ScreenDisplayer extends TimerTask{
      /** The code which is executes when the TimerTask executes
       * Progresses to the closing cutscene or moves the random-button and restarts timer
       */
      public void run(){
         if(buttonsShown == pressRequirement)
            cutScene(2);
         else {
            buttonsShown++;
            missedClicks++;
            setUpFrame();
            timer.cancel();
            timer = new Timer();
            timer.schedule(new ScreenDisplayer(), 1000);
            moveButton();
         }
      }
   }
   /** Displays the specified scene
    * @param scene The scene to display
    */
   public void cutScene (int scene) {
      frame.getContentPane().removeAll();
      screen = new BackgroundPanel();
      if (scene == 1) {
         screen.setLayout(new GridLayout(10, 1));
         JLabel tempText = new SemiTransparentLabel("<html><center>Uh oh. Your nose is itchy, but you have to resist the urge to scratch or you'll touch your face!<br>" +
            "Click the buttons before they disappear (1 sec.) to resist!<center><html>"
            , SwingConstants.CENTER);
         tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
         screen.add(tempText);
         for (int x = 0; x < 7; x++)
            screen.add(new JLabel());
         JPanel buttonLocs = new JPanel();
         buttonLocs.setOpaque(false);
         buttonLocs.setLayout(new GridLayout(1, 5));
         for (int x = 0; x < 2; x++)
            buttonLocs.add(new JLabel());
         buttonLocs.add(introButton);
         for (int x = 0; x < 2; x++)
            buttonLocs.add(new JLabel());
         screen.add(buttonLocs);  
         screen.add(new JLabel());
      }
      else if (scene == 2) {
         screen.setLayout(new GridLayout(10, 1));
         JLabel tempText = new SemiTransparentLabel("Phew... well your nose isn't itchy anymore...", SwingConstants.CENTER);
         tempText.setFont(new Font("Dialog", Font.PLAIN, 24));
         screen.add(tempText);
         for (int x = 0; x < 7; x++)
            screen.add(new JLabel());
         JPanel buttonLocs = new JPanel();
         buttonLocs.setOpaque(false);
         buttonLocs.setLayout(new GridLayout(1, 5));
         for (int x = 0; x < 2; x++)
            buttonLocs.add(new JLabel());
         buttonLocs.add(outroButton);
         for (int x = 0; x < 2; x++)
            buttonLocs.add(new JLabel());
         screen.add(buttonLocs);  
         screen.add(new JLabel());
      }
      frame.add(screen);
      frame.revalidate();
      frame.repaint();
   }
   /** Moves the random-button to a new random location
    */
   public void moveButton () {
      int random = (int)(Math.random()*100);
      for(int i = 0; i < random; i++)
         screen.add(new JLabel());
      screen.add(randomMovingButton);
      for(int i = random; i < 100; i++)
         screen.add(new JLabel());
      frame.add(screen);
      frame.revalidate();
      frame.repaint();
   }
   /** Sets up the frame for display, resets the screen JPanel
    */
   public void setUpFrame(){
      frame.getContentPane().removeAll();
      frame.setTitle("Level 2: Bubbles to the Brim");
      frame.setLayout(new GridLayout());
      screen = new BackgroundPanel();
      screen.setLayout(new GridLayout(10, 10));
   }
   /** Loads the background image for the minigame
    */
   public void loadImages() {
      try {
         storeInside = ImageIO.read(new File("Images/scene2.png"));
      } catch (IOException e) {}
   }
   /** Creates a JPanel with an image in the background
    */
   private class BackgroundPanel extends JPanel{
     /** Overrides the JPanel method of same name and parameters to draw a background on the JPanel
       * @override
       * @param g "The Graphics object to protect", according to the Oracle API (https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#paintComponent(java.awt.Graphics))
       */
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(storeInside, 0, 0, this);
      }
   }
   /** Ends the game
    * @param game The Minigame instance to remove from randomMovingButton
    */
   public void endGame (Minigame game) {
      timer.cancel();
      randomMovingButton.removeActionListener(game);
      lvlTwo.misses = missedClicks;
      lvlTwo.displayScene(2,4);
   }
   /** Called when an action occurs (a button is pressed); either moves the random-button or progresses to a new scene
    * @param e The ActionEvent which triggered the function
    */
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == randomMovingButton){
         buttonsShown++;         
         if(buttonsShown == pressRequirement)
            cutScene(2);
         else {
            setUpFrame();
            timer.cancel();
            timer = new Timer();
            timer.schedule(new ScreenDisplayer(), 1000);
            moveButton();
         }
      }
      else if(e.getSource() == introButton){
         timer = new Timer();
         setUpFrame();
         moveButton();
         timer.schedule(new ScreenDisplayer(), 1000);
      }
      else if(e.getSource() == outroButton)
         endGame(this);
   }
}