/*
 * DrawingTool.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package tools;

import java.awt.Shape;
import javax.swing.Icon;

/**
 * An Interface for shapes.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public interface DrawingTool {

    /**
     * Returns the x coordinate.
     * 
     * @return an object's x coordinate.
     */
    double getMyX();    
    
    /**
     * returns the y coordinate.
     * 
     * @return an object's y coordinate.
     */
    double getMyY();
    
    /**
     * Returns the width coordinate.
     * 
     * @return an object's width coordinate.
     */
    double getMyWidth();
    
    /**
     * Returns the height.
     * 
     * @return an object's height coordinate.
     */
    double getMyHeight();    
    
    /**
     * Set the x coordinate.
     * 
     * @param theX is the object's x coordinate.
     */
    void setMyX(double theX);

    /**
     * Set the y coordinate.
     * 
     * @param theY is the object's x coordinate.
     */
    void setMyY(double theY);
    
    /**
     * Set the width.
     * 
     * @param theWidth is the width of the object.
     */
    void setMyWidth(double theWidth);
    
    /**
     * Set the height.
     * 
     * @param theHeight is the height od the object.
     */
    void setMyHeight(double theHeight);
    
    
    /**
     * Create a chosen Shape.
     * 
     * @param theX is the x coordinate of a Shape.
     * @param theY is the y coordinate of a Shape.
     * @param theWidth is the width of a Shape.
     *        End x coordinate for a line.
     * @param theHeight is the height of a Shape
     *        End y coordinate for a line.
     * @return chosen drawing tool.
     */
    Shape drawingTool(double theX, double theY, double theWidth, double theHeight);
    
    /**
     * Returns a text description of this shape.
     * 
     * @return a text description of this shape.
     */
    String getDescription();
    
    /**
     * Returns an icon of this shape.
     * 
     * @return an icon of this shape.
     */
    Icon getIcon();

} // end DrawingTool interface
