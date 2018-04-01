/*
 * Ellipse.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;



/**
 * A class to draw a ellipse.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class Ellipse extends AbstractDrawingTool {

    
    /**
     * Construct a new shape.
     */
    public Ellipse() {
        this("Ellipse", new ImageIcon("images/ellipse_bw.gif"));
    }
      
    
    /**
     * Construct a new shape.
     * 
     * @param theShapeName is a name for a shape.
     * @param theIcon is an icon.
     */
    public Ellipse(final String theShapeName,
                   final Icon theIcon) {
        super(theShapeName, theIcon);
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
        
        final Shape ellipse = new Ellipse2D.Double(theX, 
                                                   theY, 
                                                   theWidth, 
                                                   theHeight);
        
        ((Ellipse2D.Double) ellipse).setFrameFromDiagonal(theX, 
                                                          theY, 
                                                          theX + theWidth, 
                                                          theY + theHeight);
        
        return ellipse;
        
    }

} // end Ellipse class
