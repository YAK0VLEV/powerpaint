/*
 * DrawnShapes.java
 *
 * TCSS 305 - Spring 2017 
 * Instructor: Charles Bryan 
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint) 
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

/**
 * The class creates a Shape object with the following parameters:
 * Color, Thickness, Filling. 
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class DrawnShapes {
    
    /**
     * A Shape object.
     */
    private final Shape myCurrentShape;
    
    /**
     * A Color object.
     */
    private final Color myCurrentColor;
    
    /**
     * An object for thickness.
     */
    private final BasicStroke myCurrentLineWidthOfShapeContour;
    
    /**
     * Keeps a condition for filling.
     */
    private final boolean myFilling;
    
    /**
     * 
     * @param theCurrentShape is the given Shape object.
     * @param theCurrentColor is the given Color object.
     * @param theCurrentLineWidthOfShapeContour is the given Thickness object.
     * @param theFilling is the current condition for filling.
     */
    public DrawnShapes(final Shape theCurrentShape,
                       final Color theCurrentColor,
                       final BasicStroke theCurrentLineWidthOfShapeContour,
                       final boolean theFilling) {
        
        myCurrentShape = theCurrentShape;
        myCurrentColor = theCurrentColor;
        myCurrentLineWidthOfShapeContour 
                                    = theCurrentLineWidthOfShapeContour;
        myFilling = theFilling;
        
    }
    
    /**
     * A getter for Shape object.
     * 
     * @return the Shape object.
     */
    public Shape getShape() {
        return myCurrentShape;
    }
    
    /**
     * A getter for Color object.
     * 
     * @return the Color object.
     */
    public Color getColor() {
        return myCurrentColor;
    }
    
    /**
     * A getter for Thickness object.
     * 
     * @return the Stroke object.
     */
    public BasicStroke getLineWidthOfShapeContour() {
        return myCurrentLineWidthOfShapeContour;
    }
    
    /**
     * A getter for filling condition.
     * 
     * @return true or false.
     */
    public boolean isFilling() {
        return myFilling;
    }

} // end DrawnShapes class.
