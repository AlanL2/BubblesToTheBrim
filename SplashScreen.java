/*
 * Due: June 15, 2020
 * Alan Li & Aidan Ang
 * SplashScreen
 * Mrs. Krasteva
 * The purpose of this program is to display our splash screen that we used at the beginning of our program 
 * 
 * 
 * 
 * 
 * Revisions
 * - May 26: Created ~ Aidan Ang
 * - May 27: Updated ~ Aidan Ang
 * - May 29: Updated ~ Aidan Ang
 * - May 3: Commented ~ Aidan Ang
 * - June 6: Updated ~ Aidan Ang
 * - June 10: Commented ~Alan Li
 * - June 11: Finished ~ Aidan Ang
 */
//importing needed libraries
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen { //declaring the SplashScreen class to act as template, by making an object it will run the splash screen
   Timer timer; //Set a timer variable to keep track of when the GIFs are being displayed
   JFrame frame; //Declaring a JFrame for this specific part of the game
   public SplashScreen (JFrame frameIn) { //constructor that accepts a JFrame as the frame, this allows us to keep using the same frame
      timer = new Timer(); //initialize the timer to a new timer
      frame = frameIn; //set the class' instance variable frame to the passed in frame
      
      timer.schedule(new DisplayGIFIn(frame), 0); //schedule the fade-in part first at time 0, so it starts immediately
      timer.schedule(new DisplayPNG(frame), 2000); //fade-in lasts for 2 seconds, and we let the frame stop at the normal image by displaying a png 
      timer.schedule(new DisplayGIFOut(frame), 4000); //last for 2 seconds again, and fade out for 2 seconds
      timer.schedule(new Blackout(frame), 6000); //blackout and then transition to main menu
   }
   class DisplayGIFIn extends TimerTask { //Fade-in class used to allow for the 2 second fade-in of the splash screen
      JFrame frame; //set a new JFrame specifically for this part
      public DisplayGIFIn (JFrame frameIn) //constructor that accepts the original frame to be used
      {
         frame = frameIn; //set the instance variable frame to the frame used for everything
      }
      public void run () { //void run() method to run this part of the program/the fade-in
         frame.getContentPane().removeAll(); //remove all of the content in the current JFrame
      
         ImageIcon gif = new ImageIcon("Images/virusoftLogoFadeIn.gif"); //Declare and initialize an ImageIcon that stores the GIF
         Image scaled = gif.getImage().getScaledInstance(1280, 720, java.awt.Image.SCALE_DEFAULT); /*Scale the image to 1280 by 720, the size of our 
         frame by converting to an Image variable*/
         JLabel label = new JLabel(new ImageIcon(scaled)); //convert the image to a JLabel to be added to the frame       
         frame.getContentPane().add(label); //add the GIF to the frame
         frame.pack(); //pack the frame
      }
   }  
   class DisplayPNG extends TimerTask { //Displaying the PNG which allows for the 2-second stop time of just displaying the company logo
      JFrame frame; //declare a new JFrame specifically for this part
      public DisplayPNG (JFrame frameIn) //constructor that accepts the original frame of the SplashScreeen class
      {
         frame = frameIn; //initialize this frame to the original frame
      }
      public void run() { //void run() method to display the GIF
         frame.getContentPane().removeAll(); //remove all previous things in the frame
         ImageIcon img = new ImageIcon("Images/virusoftLogo.png"); //Declare and initialize an ImageIcon to store the logo png
         JLabel label = new JLabel(img); //Store the ImageIcon to a JLabel to modify size and be added to the frame
         label.setPreferredSize(new Dimension(1280, 720)); //set the size to the size of the frame
       
         frame.getContentPane().add(label); //add the JLabel to the frame
         frame.pack(); //pack the frame
         frame.repaint(); //repaint the frame to make these changes appear
      }
   }
   class DisplayGIFOut extends TimerTask { //class to display the GIF fade-out part
      JFrame frame; //declare a JFrame for this part of the program
      public DisplayGIFOut (JFrame frameIn) //constructor to accept the original JFrame
      {
         frame = frameIn; //set the current JFrame to the original
      }
      public void run() { //void run() method to display the fade-out part
         frame.getContentPane().removeAll(); //remove all previous content in the frame
      
         ImageIcon gif = new ImageIcon("Images/virusoftLogoFadeOut.gif"); //Declare an ImageIcon to store the fade-out gif
         Image scaled = gif.getImage().getScaledInstance(1280, 720, java.awt.Image.SCALE_DEFAULT); //scale the image to size of frame
         JLabel label = new JLabel(new ImageIcon(scaled)); //Convert the scaled image to a JLabel so it can be added to the frame
       
         frame.getContentPane().add(label); //add the JLabel to the frame
         frame.pack(); //pack the frame
         frame.repaint(); //repaint frame so changes will occur
      }
   }
   class Blackout extends TimerTask { //Fade-out to black part of program
      JFrame frame; //Declare a JFrame just for this area
      public Blackout (JFrame frameIn) //constructor to accept the original JFrame
      {
         frame = frameIn; //set the JFrame to the original JFrame passed in
      }
      public void run() { //void run() method to fade out to black
         frame.getContentPane().removeAll(); //remove all previous content in the program,
         
         JLabel black = new JLabel(); //declare and initialize a new JLabel to display a black screen
         black.setPreferredSize(new Dimension(1280, 720)); //set preferred size to the frame size 
         black.setOpaque(true); //set the JLabel to be opaque so everything will fade to black and nothing else will show
         black.setBackground(Color.black); //set the background of the JLabel to black, basically setting the entire screen to be black
         frame.add(black); //add the JLabel to the frame so the screen will appear to be black
         
         frame.pack(); //pack the frame
         frame.repaint(); //repaint to show the changes 
      }
   }
}