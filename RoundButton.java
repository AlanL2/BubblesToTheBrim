/*
 * Due June 15th, 2020
 * Alan Li & Aidan Ang
 * Round Button
 * Mrs. Krasteva
 * The purpose of this class is to act as a template for the rounded buttons that were used in ItemSelector and LevelOne.
 * 
 * 
 * Revisions
 * - June 5: Created ~ Alan Li
 * - June 7: Updated ~ Alan Li
 * - June 12: Updated ~ Alan Li
 * ~ June 13: Commented ~ Alan Li
 * ~ June 14: Finished ~ Alan Li
 */
//importing needed libraries
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
/*
 * RoundButton class 
 * implements MouseListener so it can be clicked
 */
public class RoundButton extends JButton implements MouseListener{
    private int actions;
    private Color buttonCol = new Color(192, 192, 192);
    /*
     * Constructor that uses super to store the text on the button
     * @param buttonText Stores the text that is going to be put on the button
     */
    public RoundButton(String buttonText){
        super(buttonText);
    }
    /*
     * paint method that paints the rounded button
     * @param g Graphics used to draw the button itself and make it display
     */
    public void paint(Graphics g){
        setBackground(getParent().getBackground());
        setBorder(BorderFactory.createEmptyBorder());
        g.setColor(buttonCol);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
        g.setFont(new Font("Serial", Font.PLAIN, 15));
        g.setColor(Color.BLUE);
        FontRenderContext frc = new FontRenderContext(null, false, false); //measuring text
        Rectangle2D r = getFont().getStringBounds(getText(), frc); //drawing the border around 
        int x = (int)(getWidth()-r.getWidth())/2, y = (int)(getHeight()-getFont().getSize())/2; 
        g.drawString(getText(), x, (int)getFont().getSize()+y);
    }
    // @Override
    /*
     * overriden method to check if the mouse has entered the button or not
     * @param e                          Checks for an event/action done by the mouse
     */
    public void mouseEntered(MouseEvent e){
        buttonCol = new Color(192, 192, 192);
    }
    /*
     * overriden method to check if the mouse has exited the button
     * @param e                          Checks for an event/action done by the mouse
     */
    // @Override
    public void mouseExited(MouseEvent e){
    }
    // @Override
    /*
     * overriden method to check if the mouse has been pressed or not
     * @param e                          Checks for an event/action done by the mouse
     */
    public void mousePressed(MouseEvent e){
    }
    
    // @Override
    /*
     * overriden method to check if the mouse has been released or not
     * @param e                          Checks for an event/action done by the mouse
     */
    public void mouseReleased(MouseEvent e){
    }
    // @Override
    /*
     * overriden method to check if the mouse has been clicked or not
     * @param e                          Checks for an event/action done by the mouse
     */
    public void mouseClicked(MouseEvent e){   
    }
}
