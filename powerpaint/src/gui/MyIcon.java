/*
 * MyIcon.java
 *
 * TCSS 305 - Spring 2017 
 * Instructor: Charles Bryan 
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint) 
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * The Icon for Color in Menu Bar. 
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 17 May 2017
 */
public class MyIcon extends JPanel {
   
    /** A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 750299891052323030L;
    
    /** The width for an icon. */
    private static final int WIDTH = 15; 
    
    /** The height for an icon. */
    private static final int HEITH = 15;
    

    /**
     * Create an icon based on given color.
     * 
     * @param theColor is the given color.
     * @return an Image for an Icon.
     */
    protected Image createIcon(final Color theColor) {
        
        final BufferedImage iconImage 
            = new BufferedImage(15, 15, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D drawing = iconImage.createGraphics();
//        g2d.drawImage(this, 0, 0, 5, 5, this);
        drawing.setPaint(theColor);
        drawing.fillRect(0, 0, WIDTH, HEITH);
        drawing.dispose();
        
        return iconImage;
        
    }

    
} // end MyIcon class
