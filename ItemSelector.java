/*
 * Due June 15, 2020
 * Alan Li & Aidan Ang
 * ItemSelector
 * Mrs. Krasteva
 * The purpose of this class is to allow for the user to pick 4 out of the 8 objects and then proceed to level 2.
 * 
 * 
 * 
 * Revisions
 * - May 26: Created ~ Alan Li
 * - May 27: Updated ~ Alan Li
 * - May 30: Updated ~ Alan Li
 * - June 2: Commented ~ Alan Li
 * - June 4: Updated ~ Alan Li
 * - June 6: Updated ~ Aidan Ang
 * - June 9: Updated ~ Aidan Ang
 * - June 11: Commented ~ Alan Li
 * - June 12: Finished ~ Alan Li
 */
//importing required classes 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
/**
 * class for the items to be selected
 */
public class ItemSelector {
   /** JFrame to display all of the buttons, text and images for this part of the game*/
   JFrame frame;
   
   /** Player object to store the items that the user picked*/
   Player player;
   
   /** MainMenu object so that once it reaches level 2 the user is able to go back to the menu*/
   MainMenu menu;
   
   /** JButton array to store all of the buttons that can be pressed to select an item*/
   private JButton[]itemButtons = {new JButton("Item 1"), new JButton("Item 2"), new JButton("Item 3"), new JButton("Item 4"),
    new JButton("Item 5"), new JButton("Item 6"), new JButton("Item 7"), new JButton("Item 8"), new RoundButton("Start Level")};
   /** stores the number of items that you have to pick to continue, and the other variable stores the number that is currently selected*/
   private int numOfItems = 4, curSelected = 0;
   
   /** Stores the rawImages that were imported*/
   private ImageIcon[]rawImages = {new ImageIcon("Images/antibiotics.png"), new ImageIcon("Images/gloves.png"),
    new ImageIcon("Images/lysol.png"), new ImageIcon("Images/mask.png"), new ImageIcon("Images/purell.png"),
    new ImageIcon("Images/saline.png"), new ImageIcon("Images/thermos.png"), new ImageIcon("Images/VitaminC.png")};
   
   /** boolean array to check if an item is currently selected or not*/
   private boolean[]itemSelectChecker = new boolean[8]; 
   
   /** Image array that scales the images to 100px * 100px */
   private Image[]scaledImages = {
        rawImages[0].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
        rawImages[1].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH), 
        rawImages[2].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
        rawImages[3].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
        rawImages[4].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
        rawImages[5].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
        rawImages[6].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
        rawImages[7].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH),
    };
   /** JLabel array to store all of the item images*/
   private JLabel[]items = {
        new JLabel(new ImageIcon(scaledImages[0])),
        new JLabel(new ImageIcon(scaledImages[1])),
        new JLabel(new ImageIcon(scaledImages[2])),
        new JLabel(new ImageIcon(scaledImages[3])),
        new JLabel(new ImageIcon(scaledImages[4])),
        new JLabel(new ImageIcon(scaledImages[5])),
        new JLabel(new ImageIcon(scaledImages[6])),
        new JLabel(new ImageIcon(scaledImages[7])),
    };
   
   /** JLabel to show the level title screen*/
   private JLabel levelTitle = new JLabel("Level 2", SwingConstants.CENTER);
   
   /** JLabel to show the info on how to go start level 2*/
   private JLabel levelInfo = new JLabel("<html>" + " You can now pick the 4 items that you think will be the "
                                              + "best in protecting yourself from COVID-19 while buying bubble tea." 
                                              + "</html>", SwingConstants.CENTER);
   /** JLabel to display to the user that they have to select 4 items to continue*/
   private JLabel nextLevelInfo = new JLabel("Select " + numOfItems + " Items to continue.");
   
   /** Array of JLabels to store each item's status*/
   private JLabel[]itemPickStatus = new JLabel[9];
   
   /** Array of JPanels to store each item, its corresponding button and its status*/
   private JPanel[]panelGrid = new JPanel[9];
   
   /** 
    * Constructor to run the item selector part of the program and initialize values
    * @param frameIn                         stores the frame that is used throughout the game
    * @param playerIn                        stores the data that the player has picked
    * @param menuIn                          stores the MainMenu object so in level 2 we can go back to main menu
    */
   public ItemSelector(JFrame frameIn, Player playerIn, MainMenu menuIn) {
      player = playerIn;
      frame = frameIn;
      menu = menuIn;
      setUpFrame();
      itemButtons[8].setPreferredSize(new Dimension(100, 100));
      itemButtons[8].setVisible(false);
      itemButtons[8].setBackground(Color.GRAY);
      itemButtons[8].setForeground(Color.BLUE);
      for(int i = 0; i<9; i++){
         itemPickStatus[i] = new JLabel();
      }
      for(int i = 0; i<9; i++){
         itemButtons[i].addActionListener(new ItemPicker());
      }
      levelTitle.setFont(new Font("Serif", Font.BOLD, 20));
      levelInfo.setFont(new Font("Serif", Font.PLAIN, 15));
      for(int i = 0; i<9; i++){
         panelGrid[i] = new JPanel();
         panelGrid[i].setBorder(new EmptyBorder(10, 10, 10, 10));
         panelGrid[i].setLayout(new BorderLayout());
         if(i==4){
            JPanel centerText = new JPanel();
            centerText.setLayout(new BorderLayout());
            centerText.add(levelTitle, BorderLayout.NORTH);
            centerText.add(levelInfo, BorderLayout.CENTER);
            centerText.add(nextLevelInfo, BorderLayout.SOUTH);
            panelGrid[4].add(centerText, BorderLayout.NORTH);
            panelGrid[4].add(itemButtons[8]);
         }
         else{
            if(i<4){
               panelGrid[i].add(items[i], BorderLayout.NORTH);
               panelGrid[i].add(itemButtons[i], BorderLayout.SOUTH);
            }
            else{
               panelGrid[i].add(items[i-1], BorderLayout.NORTH);
               panelGrid[i].add(itemButtons[i-1], BorderLayout.SOUTH);
            }
         }
         frame.add(panelGrid[i]);
      }
      frame.revalidate();
      frame.repaint();
   }
   
   /**
    * void method to set up the frame, setting up layout, title and removing previous data
    */
   public void setUpFrame(){
      frame.getContentPane().removeAll();
      frame.setLayout(new GridLayout(3, 3)); //modify later
      frame.setTitle("Level 2: Item Selector");
   }
   
   /** 
    * void method to continue to "refresh" the screen so that the current statuses of the buttons are correct
    */
   public void validate(){
       if(curSelected==numOfItems){
            itemButtons[8].setVisible(true);
       }
       else{ 
           itemButtons[8].setVisible(false);
           frame.getContentPane().removeAll();
           for(int i = 0; i<9; i++){
               panelGrid[i] = new JPanel();
               panelGrid[i].setBorder(new EmptyBorder(10, 10, 10, 10));
               panelGrid[i].setLayout(new BorderLayout());
               if(i==4){
                   JPanel centerText = new JPanel();
                   centerText.setLayout(new BorderLayout());
                   centerText.add(levelTitle, BorderLayout.NORTH);
                   centerText.add(levelInfo, BorderLayout.CENTER);
                   centerText.add(nextLevelInfo, BorderLayout.SOUTH);
                   panelGrid[4].add(centerText, BorderLayout.NORTH);
                   panelGrid[4].add(itemButtons[8]);
               }
               else{
                   JPanel infoText = new JPanel();
                   infoText.setLayout(new BorderLayout());
                   infoText.add(itemPickStatus[i], BorderLayout.NORTH);
                   if(i<4){
                       panelGrid[i].add(items[i], BorderLayout.NORTH);
                       panelGrid[i].add(infoText, BorderLayout.CENTER);
                       panelGrid[i].add(itemButtons[i], BorderLayout.SOUTH);
                       
                   }
                   else{
                       panelGrid[i].add(items[i-1], BorderLayout.NORTH);
                       panelGrid[i].add(infoText, BorderLayout.CENTER);
                       panelGrid[i].add(itemButtons[i-1], BorderLayout.SOUTH);
                   }
               }
               frame.add(panelGrid[i]);
           }
       }
       frame.validate();
       frame.repaint();
   }
    /*
     * 0: antibiotics
     * 1: gloves
     * 2: lysol
     * 3: mask
     * 4: purell
     * 5: saline
     * 6: thermos
     * 7: Vitamin C
     */
   
   /**
    * class to allow buttons to have an ActionListener so that they can be pressed and do something
    */
   class ItemPicker implements ActionListener{
       /**
        * void actionPerformed method to check if a button has been pressed or not
        * @param e                              ActionEvent to check if mouse has been clicked or not
        */
      public void actionPerformed(ActionEvent e){
         int index = 0;
         JLabel title = new JLabel(), description = new JLabel();
         title.setFont(new Font("Serial", Font.PLAIN, 30));
         validate();
         if(e.getSource()==itemButtons[0]){
            index = 0;
            if(!itemSelectChecker[index]){
               title = new JLabel("Antibiotics selected!", SwingConstants.CENTER);
               itemSelectChecker[index] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Antibiotics unselected.", SwingConstants.CENTER);
               itemSelectChecker[index] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[1]){
            index = 1;
            if(!itemSelectChecker[index]){
               title = new JLabel("Latex Gloves selected!", SwingConstants.CENTER);
               itemSelectChecker[index] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Latex Gloves unselected.", SwingConstants.CENTER);
               itemSelectChecker[index] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[2]){
            index = 2;
            if(!itemSelectChecker[index]){
               title = new JLabel("Lysol Wipes selected!", SwingConstants.CENTER);
               itemSelectChecker[index] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Lysol Wipes unselected.", SwingConstants.CENTER);
               itemSelectChecker[index] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[3]){
            index = 3;
            if(!itemSelectChecker[index]){
               title = new JLabel("N-95 Facemask selected!", SwingConstants.CENTER);
               itemSelectChecker[index] = true;
               curSelected++;
            }
            else{
               title = new JLabel("N-95 Facemask unselected.", SwingConstants.CENTER);
               itemSelectChecker[index] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[4]){
            index = 5;
            if(!itemSelectChecker[index-1]){
               title = new JLabel("Purell Hand Sanitizer selected!", SwingConstants.CENTER);
               itemSelectChecker[index-1] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Purell Hand Sanitizer unselected.", SwingConstants.CENTER);
               itemSelectChecker[index-1] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[5]){
            index = 6;
            if(!itemSelectChecker[index-1]){
               title = new JLabel("Saline Nasal Spray selected!", SwingConstants.CENTER);
               itemSelectChecker[index-1] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Saline Nasal Spray unselected.", SwingConstants.CENTER);
               itemSelectChecker[index-1] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[6]){
            index = 7;
            if(!itemSelectChecker[index-1]){
               title = new JLabel("Thermos selected!", SwingConstants.CENTER);
               itemSelectChecker[index-1] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Thermos unselected.", SwingConstants.CENTER);
               itemSelectChecker[index-1] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[7]){
            index = 8;
            if(!itemSelectChecker[index-1]){
               title = new JLabel("Vitamin C Pills selected!", SwingConstants.CENTER);
               itemSelectChecker[index-1] = true;
               curSelected++;
            }
            else{
               title = new JLabel("Vitamin C Pills unselected.", SwingConstants.CENTER);
               itemSelectChecker[index-1] = false;
               curSelected--;
            }
            itemPickStatus[index] = title;
            validate();
         }
         else if(e.getSource()==itemButtons[8]){
            for (int x = 0; x < itemSelectChecker.length; x++)
               player.itemsHeld[x] = itemSelectChecker[x];
            LevelTwo lvlTwo = new LevelTwo(frame, player, menu);
         }
         System.out.println(title.getText());
         validate();
      }
   }
}