/*
 * Eraser.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.ImageIcon;


/**
 * A class to draw as a pencil.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class Eraser extends AbstractDrawingTool {

    
    /**
     * Construct a new shape.
     */
    public Eraser() {
        super("Eraser", new ImageIcon("images/eraser_bw.gif"));
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
        
        final Shape line = new Line2D.Double(theX, 
                                             theY, 
                                             theWidth, 
                                             theHeight);
        
        return new Path2D.Double(line);
        
    }

} // end Eraser class
