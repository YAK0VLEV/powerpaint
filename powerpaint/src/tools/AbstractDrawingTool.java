/*
 * AbstractDrawingTool.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package tools;

import javax.swing.Icon;

// import java.awt.Shape;

/**
 * An Abstract super class for shapes.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public abstract class AbstractDrawingTool implements DrawingTool {
    
    
    /**
     * The initial x coordinate.
     */
    private static final double MY_INITIAL_X = -500.0;
    
    /**
     * The initial y coordinate.
     */
    private static final double MY_INITIAL_Y = -500.0;
    
    /**
     * The description of this drawing tool (will be used on buttons).
     */
    private final String myDescription;
    
    /**
     * The icon of this drawing tool (will be used on buttons).
     */
    private final Icon myIcon;
    
    /**
     * The x coordinate of a Shape.
     */
    private double myX;
    
    /**
     * The y coordinate of a Shape.
     */
    private double myY;
    
    /**
     * The width of a Shape.
     */
    private double myWidth;
    
    /**
     * The height of a Shape.
     */
    private double myHeight;
    
    
    
    /**
     * Constructs a tool with the specified description.
     * 
     * @param theDescription is a description.
     * @param theIcon is an icon for a drawing tool.
     */
    public AbstractDrawingTool(final String theDescription,
                               final Icon theIcon) {
        
        myDescription = theDescription;
        myIcon = theIcon;
        
        myX = MY_INITIAL_X;
        myY = MY_INITIAL_Y;
        myWidth = 0.0;
        myHeight = 0.0;
        
    }   
    
    /**
     * Returns the x coordinate.
     * 
     * @return an object's x coordinate.
     */
    @Override
    public double getMyX() {
        return myX;
    }
    
    /**
     * returns the y coordinate.
     * 
     * @return an object's y coordinate.
     */
    @Override
    public double getMyY() {
        return myY;
    }
    
    /**
     * Returns the width coordinate.
     * 
     * @return an object's width coordinate.
     */
    @Override
    public double getMyWidth() {
        return myWidth;
    }
    
    /**
     * Returns the height.
     * 
     * @return an object's height coordinate.
     */
    @Override
    public double getMyHeight() {
        return myHeight;
    }
    
    
    /**
     * Set the x coordinate.
     * 
     * @param theX is the object's x coordinate.
     */
    @Override
    public void setMyX(final double theX) {
        myX = theX;
    }

    /**
     * Set the y coordinate.
     * 
     * @param theY is the object's x coordinate.
     */
    @Override
    public void setMyY(final double theY) {
        myY = theY;
    }
    
    /**
     * Set the width.
     * 
     * @param theWidth is the width of the object.
     */
    @Override
    public void setMyWidth(final double theWidth) {
        myWidth = theWidth;
    }
    
    /**
     * Set the height.
     * 
     * @param theHeight is the height of the object.
     */
    @Override
    public void setMyHeight(final double theHeight) {
        myHeight = theHeight;
    }

    /**
     * Returns the text description of this tool.
     * 
     * @return the text description of this tool.
     */
    @Override
    public String getDescription() {
        return myDescription;
    }

    /**
     * Returns the icon of this tool.
     * 
     * @return the icon of this tool.
     */
    @Override
    public Icon getIcon() {
        return myIcon;
    }
} // end AbstractDrawingTool
