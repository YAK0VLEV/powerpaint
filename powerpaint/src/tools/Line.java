/*
 * Line.java
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
import javax.swing.ImageIcon;


/**
 * A class to draw a line.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class Line extends AbstractDrawingTool {
    
    /**
     * Construct a new shape.
     */
    public Line() {
        super("Line", new ImageIcon("images/line_bw.gif"));
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

        return new Line2D.Double(theX, theY, theWidth, theHeight);
    }

} // end Line class
