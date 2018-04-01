/*
 * Rectangle.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;



/**
 * A class to draw a rectangle.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class Rectangle extends AbstractDrawingTool {

    
    /**
     * Construct a new shape.
     */
    public Rectangle() {
        this("Rectangle", new ImageIcon("images/rectangle_bw.gif"));
    }
    
    /**
     * Construct a new shape.
     * 
     * @param theShapeName is a name for a shape.
     * @param theIcon is the icon.
     */
    public Rectangle(final String theShapeName,
                     final Icon theIcon) {
        super(theShapeName,
              theIcon);
    }
      
    
    /**
     * Create a chosen Shape.
     * 
     * @param theX is the x coordinate of a Shape.
     * @param theY is the y coordinate of a Shape.
     * @param theWidth is the width of a Shape.
     *        End x coordinate for a line.
     * @param theHeight is the height of a Shape
     *        End y coordinate for a line.
     */
    @Override
    public Shape drawingTool(final double theX,
                            final double theY, 
                            final double theWidth, 
                            final double theHeight) {
        
        final Shape rectangle = new Rectangle2D.Double(theX, 
                                                       theY, 
                                                       theWidth, 
                                                       theHeight);
        
        ((Rectangle2D.Double) rectangle).setFrameFromDiagonal(theX, 
                                                             theY, 
                                                             theX + theWidth, 
                                                             theY + theHeight);
        
        return rectangle;
        
    }

} // end Ractangle class
