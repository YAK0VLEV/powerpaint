/*
 * DrawingPanel.java
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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import tools.DrawingTool;
import tools.Line;

/**
 * The drawing panel. 
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class DrawingPanel extends JPanel {

    /* ================================================================
       ------ Declaring Class Constants(Static Final Fields) -------
    ================================================================ */

    /** A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -2385842428565572649L;

    /**
     * The panel width.
     */
    private static final int WIDTH = 400;

    /**
     * The panel height.
     */
    private static final int HEIGHT = 400;

    /**
     * The x coordinate for an initial start point.
     */
    private static final double INITIAL_X = -500.0;

    /**
     * The y coordinate for an initial start point.
     */
    private static final double INITIAL_Y = -500.0;
    
    /**
     * The tool name for Pencil.
     */
    private static final String PENCIL = "Pencil";  
    
    /**
     * The tool name for Eraser.
     */
    private static final String ERASER = "Eraser";
    
    /**
     * The tool name for Line.
     */
    private static final String LINE = "Line";

    /* ================================================================
       --- Declaring Instance Variables(Internal State / Fields) ---
    ================================================================ */

    /**
     * Start x coordinate when a mouse clicked.
     */
    private double myStartX;

    /**
     * Start y coordinate when a mouse clicked.
     */
    private double myStartY;

    /**
     * Start x coordinate when a mouse released.
     */
    private double myEndX;

    /**
     * Start y coordinate when a mouse released.
     */
    private double myEndY;

    /**
     * The current shape.
     */
    private Shape myShape;

    /**
     * The object which handles a shape during a mouse moving.
     */
    private Shape myDraggedShape 
                        = new Line2D.Double(INITIAL_X, INITIAL_Y, 0, 0);

    /**
     * An arrayList which contains all drawn shapes.
     */
    private final List<DrawnShapes> myAllDrawnShapesList 
                        = new ArrayList<DrawnShapes>();

    /**
     * The current chosen tool to draw.
     */
    private DrawingTool myCurrentDrawingTool = new Line();

    /**
     * A helper variable to clear all drawn shapes.
     */
    private boolean myShouldDeleteShapes;

    /**
     * Create enum of white color.
     */
    private final UWColor myWhite = UWColor.WHITE;

    /**
     * Create white color instance for eraser.
     */
    private final Color myEraserColor = myWhite.getColor();

    /**
     * Create enum of UW purple color.
     */
    private final UWColor myPurple = UWColor.PURPLE;

    /**
     * Create UW purple color instance.
     */
    private Color myPrimaryColor = myPurple.getColor();

    /**
     * Create enum of UW gold color.
     */
    private final UWColor myGold = UWColor.GOLD;

    /**
     * Create UW gold color instance.
     */
    private Color mySecondaryColor = myGold.getColor();

    /**
     * The current color.
     */
    private Color myCurrentColor = myPrimaryColor;

    /**
     * A helper variable for filling.
     */
    private boolean myShapeShouldBeFilled;

    /**
     * An initial object for thickness.
     */
    private BasicStroke myCurrentLineWidthOfShapeContour 
                                                = new BasicStroke(1);

    /* ================================================================
       -------------------- Making constructors --------------------
    ================================================================ */

    /**
     * Construct panel which uses as a canvas.
     */
    public DrawingPanel() {
        
        super();
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        final MouseInputAdapter mouseAdapter = new MyMouseInputAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        
    }

    /* ================================================================
       -------------- Calculation / Auxiliary methods --------------
    ================================================================ */

    /**
     * Override the paintComponent.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D drawing = (Graphics2D) theGraphics;

        // for better graphics display  
        drawing.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);

        drawing.setPaint(myCurrentColor);
        drawing.setStroke(myCurrentLineWidthOfShapeContour);

        // Clear the canvas if chosen.
        if (myShouldDeleteShapes) {
            deleteAllShapes();
        }

//        // Drawing shapes in time a mouse moving.
//        if (myShapeShouldBeFilled) {
//            drawing.fill(myDraggedShape);
//        } else {
//            drawing.draw(myDraggedShape);
//        }


        // Drawing all drawn shapes.
        
        for (final DrawnShapes shape : myAllDrawnShapesList) {

            drawing.setPaint(shape.getColor());
            drawing.setStroke(shape.getLineWidthOfShapeContour());
            if (shape.isFilling()) {
                drawing.fill(shape.getShape());
            } else {
                drawing.draw(shape.getShape());
            }

        }
        
        
        drawing.setPaint(myCurrentColor);
        drawing.setStroke(myCurrentLineWidthOfShapeContour);

        if (myCurrentLineWidthOfShapeContour.getLineWidth() == 0) {
            // do nothing
            getName();
        } else {
            // Drawing shapes in time a mouse moving.
            if (myShapeShouldBeFilled) {
                drawing.fill(myDraggedShape);
            } else {
                drawing.draw(myDraggedShape);
            }
        }
        
        
    }


    /**
     * Sets the current drawing tool.
     * 
     * @param theCurrentDrawingTool is figure to draw.
     */
    protected void setCurrentDrawinfTool(final DrawingTool theCurrentDrawingTool) {
        myCurrentDrawingTool = theCurrentDrawingTool;
    }

    /**
     * Clear the list with shapes.
     */
    protected void deleteAllShapes() {
        myAllDrawnShapesList.clear();
        myDraggedShape = new Line2D.Double(INITIAL_X, INITIAL_Y, 0, 0);
        repaint();
        myShouldDeleteShapes = false;
    }

    /**
     * Sets the option of clear shapes to true.
     */
    protected void setDeleteAllShapes() {
        myShouldDeleteShapes = true;
    }

    /** 
     * Sets a variable for visibility Clear button. 
     *
     * @return true if there is at least one drawn shape.
     */
    protected boolean isClearButtonShouldBeVisible() {
        return !myAllDrawnShapesList.isEmpty();
    }

    /** 
     * Sets a variable for visibility Fill button. 
     *
     * @return false if the current drawing tool eitherPencil, Line or Eraser.
     */
    protected boolean isFillButtonShouldBeVisible() {

        boolean result = true;
        if (PENCIL.equals(myCurrentDrawingTool.getDescription())
            || ERASER.equals(myCurrentDrawingTool.getDescription())
            || LINE.equals(myCurrentDrawingTool.getDescription())) {
            result = false;
        }

        return result;
        // return (!("Pencil".equals(myCurrentDrawingTool.getDescription())
        // && "Eraser".equals(myCurrentDrawingTool.getDescription())));
    }

    /** 
     * Sets a the primary color.
     * 
     *  @param theColor is the given color.
     */
    protected void setPrimaryColor(final Color theColor) {
        myPrimaryColor = theColor;
    }

    /** 
     * Sets a the secondary color. 
     *
     * @param theColor is the given color.
     */
    protected void setSecondaryColor(final Color theColor) {
        mySecondaryColor = theColor;
    }

    /** Fill a shape by current color. */
    protected void makeFilledShape() {
        myShapeShouldBeFilled = true;
    }

    /** Make a not filled shape by current color. */
    protected void makeUnfilledShape() {
        myShapeShouldBeFilled = false;
    }
    
    /** 
     * Return condition for a field if a shape should be filled.
     * 
     * @return is a shape should be filled.
     */
    protected boolean isShapeShouldBeFilled() {
        return myShapeShouldBeFilled;
    }
    
    /** 
     * Set condition for a field if a shape should be filled.
     * 
     * @param theFilling is a shape should be filled.
     */
    protected void setShapeShouldBeFilled(final boolean theFilling) {
        myShapeShouldBeFilled = theFilling;
    }

    /** 
     * Set line width of a shape contour by a given amount.
     *
     * @param theWidth is a thickness for a shape.
     */
    protected void setLineWidthOfShapeContour(final int theWidth) {
        myCurrentLineWidthOfShapeContour = new BasicStroke(theWidth);
    }

    /**
     * A MouseInputAdapter implementation specific to this GUI.
     * In the code were used examples given by Charles Bryan.
     *
     * @author Denis Yakovlev
     * @version 16 May 2017
     */
    class MyMouseInputAdapter extends MouseInputAdapter {

        /**
         * Setting color for a shape.
         * For Eraser, the color sets to white.
         * Getting coordinates on the canvas when a mouse was clicked.
         * Create an object, if necessary, either for Pencil or for Eraser. 
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {

            // Setting a color depends on a which mouse button was clicked. 
            if (SwingUtilities.isLeftMouseButton(theEvent)) {
                myCurrentColor = myPrimaryColor;
            } else if (SwingUtilities.isRightMouseButton(theEvent)) {
                myCurrentColor = mySecondaryColor;
            }

            // Setting color to white for eraser.
            if (ERASER.equals(myCurrentDrawingTool.getDescription())) {
                myCurrentColor = myEraserColor;
            }

            // Getting coordinates on a canvas when a mouse was clicked. 
            myStartX = theEvent.getX();
            myStartY = theEvent.getY();

            // Create an object which will manage Pencil and Eraser tools.
            if (PENCIL.equals(myCurrentDrawingTool.getDescription())
                || ERASER.equals(myCurrentDrawingTool.getDescription())) {

                myDraggedShape = myCurrentDrawingTool.drawingTool(myStartX, 
                                                                  myStartY, 
                                                                  myStartX,
                                                                  myStartY);

            }
        }

        /**
         * Getting coordinates where a mouse was released.
         * For tools different than Line, Pencil and Eraser correct coordinates 
         * for end point, and create Shape object.
         * For Pencil and Eraser, an object creates based on shapes manages by
         * the Path2D object.  
         * 
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myEndX = theEvent.getX();
            myEndY = theEvent.getY();

            if (!(PENCIL.equals(myCurrentDrawingTool.getDescription())
                  || LINE.equals(myCurrentDrawingTool.getDescription())
                  || ERASER.equals(myCurrentDrawingTool.getDescription()))) {

                myEndX = myEndX - myStartX;
                myEndY = myEndY - myStartY;
                
            }

            myShape = myCurrentDrawingTool.drawingTool(myStartX, 
                                                       myStartY, 
                                                       myEndX, 
                                                       myEndY);

            if (PENCIL.equals(myCurrentDrawingTool.getDescription())
                    || ERASER.equals(myCurrentDrawingTool.getDescription())) {
                myShape = myDraggedShape;
            }

            // Add the current shapes with its parameters, such as 
            // color, thickness and filling to the ArrayList of drawn shapes.
            if (myCurrentLineWidthOfShapeContour.getLineWidth() == 0) {
//                repaint();
                getName();
            } else {
                myAllDrawnShapesList.add(new DrawnShapes(myShape, myCurrentColor,
                                                         myCurrentLineWidthOfShapeContour,
                                                         myShapeShouldBeFilled));
            }
            // Refresh the canvas.
            repaint();

        }

        /**
         * Getting coordinates during the mouse moving time.
         * Manage a moving for Pencil tool by creating a new tiny line 
         * every mouse moving. 
         * For tools different than Line, Pencil and Eraser correct coordinates 
         * for end point, and create Shape object.
         * Creating an object which handles a shapes during a mouse moving,
         * for any objects other than Pencil and Eraser.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myEndX = theEvent.getX();
            myEndY = theEvent.getY();

            if (PENCIL.equals(myCurrentDrawingTool.getDescription())
                || ERASER.equals(myCurrentDrawingTool.getDescription())) {

                ((Path2D) myDraggedShape).lineTo(myEndX, myEndY);

            } else if (LINE.equals(myCurrentDrawingTool.getDescription())) {

                myDraggedShape = myCurrentDrawingTool.drawingTool(myStartX, 
                                                                  myStartY, 
                                                                  myEndX,
                                                                  myEndY);

            } else {

                myEndX = myEndX - myStartX;
                myEndY = myEndY - myStartY;

                myDraggedShape = myCurrentDrawingTool.drawingTool(myStartX, 
                                                                  myStartY, 
                                                                  myEndX,
                                                                  myEndY);


            }
            // Refresh the canvas.
            repaint();

        }

    } // end inner class

} // end DrawingPanel class
