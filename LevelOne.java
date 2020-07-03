/*
 * Due June 15, 2020
 * Alan Li & Aidan Ang
 * LevelOne
 * Mrs. Krasteva
 * The purpose of this class is to show a new window with the level one part of our game 
 */
//importing required classes 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
/*
 * LevelOne class to display the level 1 and allow for the user to press the buttons to get info on all of the items.
 * Revisions:
 * - May 29th: Created ~ Alan Li
 * - May 30th: Updated ~ Alan Li
 * - June 1: Updated ~ Alan Li
 * - June 5: Updated ~ Aidan Ang
 * - June 10: Updated ~ Aidan Ang
 * - June 11: Commented ~ Alan Li
 * - June 12: Updated ~ Alan Li
 * - June 16: Updated ~ Aidan Ang
 * - June 17: Commented ~ Alan Li
 * - June 17: Finished ~ Alan Li
 */
public class LevelOne {
    /** JFrame to display all of the buttons, text and images for this part of the game*/
    private JFrame frame; 
    
    /** Player object to store the items that the user picked*/
    private Player player; 
    
    /** MainMenu object so that once it reaches level 2 the user is able to go back to the menu*/
    private MainMenu menu;
    
    /** JPanel to store all of the components of this screen, and then can be added to frame. This will be initialized as background*/
    private JPanel screen;
    
    /** Array of JButtons to store each of the 8 buttons for each item*/
    private JButton[]itemButtons = {new JButton("Item 1"), new JButton("Item 2"), new JButton("Item 3"), new JButton("Item 4"),
        new JButton("Item 5"), new JButton("Item 6"), new JButton("Item 7"), new JButton("Item 8"), new RoundButton("Next Level")};
    
    /** Array of ImageIcons to import the raw images*/
    private ImageIcon[]rawImages = {new ImageIcon("Images/antibiotics.png"), new ImageIcon("Images/gloves.png"),
        new ImageIcon("Images/lysol.png"), new ImageIcon("Images/mask.png"), new ImageIcon("Images/purell.png"),
        new ImageIcon("Images/saline.png"), new ImageIcon("Images/thermos.png"), new ImageIcon("Images/VitaminC.png")};
    
    /** Array of booleans to check which buttons have been clicked, determines which infos to show*/
    boolean[]infoButtonsClicked = new boolean[8];
    
    /** Array of Images to store each of the resized images/icons, the items*/
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
    
    /** Array of JLabels to store each of the 8 item icons*/
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
    
    /** JLabel to represent which item we clicked on*/
    private JLabel title = new JLabel("Level 1", SwingConstants.CENTER);
    
    /** JLabel that shows the info about the level, put in the center*/
    private JLabel levelInfo = new JLabel("<html>" + "This level serves as an introduction to each game item's function."
                                              + "</html>", SwingConstants.CENTER);
    
    /** JLabel that shows the info on how to get to the next level*/
    private JLabel nextLevelInfo = new JLabel("<html>" + "Read info on all of the items by clicking the buttons to continue to the next level!"
                                                  + "</html>", SwingConstants.CENTER);
    /** Array of JPanes to store each of the 8 sections, including icon, button, and info*/
    private JPanel[]panelGrid = new JPanel[9];
    /**
     * Constructor for our class, initializes values, 
     * displays the level for the user to play
     * @param  frameIn         the original frame that is used throughout the entire program
     * @param playerIn         the player info that the user picks which is stored throughout all of the games
     * @param menuIn           the original menu object that we use which can be passed throughout the levels so the user can go back to menu
     */
    public LevelOne(JFrame frameIn, Player playerIn, MainMenu menuIn) {
        player = playerIn;
        frame = frameIn;
        menu = menuIn;
        setUpFrame();
        itemButtons[8].setPreferredSize(new Dimension(75, 75));
        itemButtons[8].setVisible(false);
        for(int i = 0; i<9; i++){
            itemButtons[i].addActionListener(new ItemListener());
        }
        title.setFont(new Font("Serif", Font.BOLD, 20));
        levelInfo.setFont(new Font("Serif", Font.PLAIN, 15));
        for(int i = 0; i<9; i++){
            panelGrid[i] = new JPanel();
            panelGrid[i].setOpaque(false);
            panelGrid[i].setBorder(new EmptyBorder(10, 10, 10, 10));
            panelGrid[i].setLayout(new BorderLayout());
            if(i==4){
                JPanel centerText = new JPanel();
                centerText.setLayout(new BorderLayout());
                centerText.setOpaque(false);
                centerText.add(title, BorderLayout.NORTH);
                centerText.add(levelInfo, BorderLayout.CENTER);
                centerText.add(nextLevelInfo, BorderLayout.SOUTH);
                panelGrid[4].add(centerText, BorderLayout.NORTH);
                JPanel buttonContainer = new JPanel();
                buttonContainer.setOpaque(false);
                buttonContainer.setLayout(new BorderLayout());
                buttonContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
                buttonContainer.add(itemButtons[8]);
                panelGrid[4].add(buttonContainer, BorderLayout.CENTER);
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
            screen.add(panelGrid[i]);
        }
        frame.add(screen);
        frame.revalidate();
        frame.repaint();
    }
    /**
     * Removes the previous content on the frame and sets up the frame again to allow for level one items/buttons to be added
     * @no return
     */
    public void setUpFrame(){
        frame.getContentPane().removeAll();
        frame.setTitle("Level 1: Item Info");
        screen = new BackgroundPanel("Images/level1background.png");
        screen.setOpaque(false);
        screen.setLayout(new GridLayout(3, 3));
        screen.setBorder(new EmptyBorder(20, 40, 20, 50));
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
     * Sense if a button has been pressed and determine which item info to show 
     * Implements ActionListener
     */
    class ItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int index = 0;
            JLabel title = new JLabel(), description = new JLabel();
            title.setFont(new Font("Serial", Font.PLAIN, 30));
            description.setFont(new Font("Serial", Font.PLAIN, 20));
            description.setPreferredSize(new Dimension(200, 400));
            if(e.getSource()==itemButtons[0]){
                index = 0;
                title = new JLabel("You clicked on: Antibiotics!", SwingConstants.CENTER);
                description = new JLabel("Antibiotics combats bacteria once ingested into the body.", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[1]){
                index = 1;
                title = new JLabel("You clicked on: Latex Gloves!", SwingConstants.CENTER);
                description = new JLabel("<html>" + "Latex gloves serves as a barrier between the virus \r\n" +
                                         "and your hands. However, you  must be careful \r\n" +
                                         "not to make any skin contact with the gloves as you \r\n"+
                                         "can get infected easily." + "</html>", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[2]){
                index = 2;
                title = new JLabel("You clicked on: Lysol Wipes!", SwingConstants.CENTER);
                description = new JLabel("<html>" + "These can be used to disinfect surfaces"
                                             + " but should not be used on your bare hands." 
                                             + "</html>", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[3]){
                index = 3;
                title = new JLabel("You clicked on: N-95 Facemask!", SwingConstants.CENTER);
                description = new JLabel("<html>" + "This is one of the most effective "
                                             + "facemasks available commercially. They are "
                                             + "extremely useful for filtering the air you breathe."
                                             + "</html>", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[4]){
                index = 5;
                title = new JLabel("You clicked on: Purell Hand Sanitizer!", SwingConstants.CENTER);
                description = new JLabel("<html>" + "This is very useful in disinfecting your hands"
                                             + " when soap is not available for you to use." 
                                             + "</html", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[5]){
                index = 6;
                title = new JLabel("You clicked on: Saline Nasal Spray!", SwingConstants.CENTER);
                description = new JLabel("<html> " + "When used to rinse your nose, saline can help you recover "
                                             + "quicker from sicknesses like the common cold."
                                             + "</html>" , SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[6]){
                index = 7;
                title = new JLabel("You clicked on: Thermos!", SwingConstants.CENTER);
                description = new JLabel("<html>" + "This can be used to contain and maintain "
                                             + "the temperature of hot water, which can be "
                                             + "consumed." + "</html>", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[7]){
                index = 8;
                title = new JLabel("You clicked on: Vitamin C Pills!", SwingConstants.CENTER);
                description = new JLabel("<html>" + "Purportedly, Vitamin C can help prevent "
                                             + "respiratory diseases, but there isn't enough "
                                             + "scientific evidence to support it. "
                                             +"</html>", SwingConstants.CENTER);
            }
            else if(e.getSource()==itemButtons[8]){
                ItemSelector i = new ItemSelector(frame, player, menu);
            }
            if(index>4){
                infoButtonsClicked[index-1] = true;
            }
            else 
                infoButtonsClicked[index] = true;
            boolean allButtonsClicked = true;
            for(int i = 0; i<infoButtonsClicked.length; i++){
                if(!infoButtonsClicked[i]){
                    allButtonsClicked = false;
                    break;
                }
            }
            if(allButtonsClicked){
                itemButtons[8].setVisible(true);
            }
            JPanel infoText = new JPanel();
            infoText.setOpaque(false);
            infoText.setLayout(new BorderLayout());
            infoText.add(title, BorderLayout.NORTH);
            infoText.add(description, BorderLayout.CENTER);
            panelGrid[index].add(infoText, BorderLayout.CENTER);
            frame.validate();
            frame.repaint();
        }
    }
}
