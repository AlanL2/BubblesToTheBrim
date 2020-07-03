/*
 * Due: June 15, 2020
 * Alan Li & Aidan Ang
 * Main
 * Mrs. Krasteva
 * The purpose of this program is to display our splash screen that we used at the beginning of our program 
 *
 * Revisions
 * May 28: Created ~ Aidan Ang
 * June 7: Commented ~ Aidan Ang
 * June 14: Finished ~ Aidan Ang
 */
//importing needed libraries
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
/*
 * driver class to run the program and control the timing of the splash screen and main menu
 */
public class BubblesToTheBrim {
   Timer timer; //timer for the Splash Screen and Main Menu to run at the right time
   JFrame frame; //JFrame for the display of the entire game, parameter for the all of the different classes 
   Player player; //Keeps track of all of the data that the player chooses at the start, name, gender, item choice etc.
   /*
    * Constructor that sets up the frame, initializes the timer and schedules the Splash Screen and Main Menu to appear
    */
   public BubblesToTheBrim () {
      frame = new JFrame("Welcome to Bubbles to the Brim!");
      frame.setVisible(true);
      frame.setSize(1280, 720);
      frame.setResizable (false);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      timer = new Timer();
      timer.schedule(new displaySplashScreen(), 0);
      timer.schedule(new displayMainMenu(), 8000);
   }
   /*
    * class used to display the splash screen
    */
   class displaySplashScreen extends TimerTask {
      /*
       * void method used to initialize the new object and run the splash screen
       */
      public void run () {
         SplashScreen screen = new SplashScreen(frame);
      }
   }
   /*
    * class used to display the main menu
    */
   class displayMainMenu extends TimerTask {
      /*
       * void method used to initialize the new object and run the main menu
       * cancels timer so that the rest of the game can run at any pace
       */
      public void run () {
         MainMenu menu = new MainMenu(frame, player);
         timer.cancel();
      }
   }
   /*
    * main driver method to run the game
    */
   public static void main(String[] args) { 
      BubblesToTheBrim b = new BubblesToTheBrim();
   }
}
