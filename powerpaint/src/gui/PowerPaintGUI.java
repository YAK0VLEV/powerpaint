/*
 * PowerPaintGUI.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tools.Circle;
import tools.DrawingTool;
import tools.Ellipse;
import tools.Eraser;
import tools.Line;
import tools.Pencil;
import tools.Rectangle;
import tools.Square;

/**
 * The graphical user interface (GUI) for an application that draws 
 * and manipulates shapes.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class PowerPaintGUI extends JFrame {

    /* ================================================================
       ------ Declaring Class Constants(Static Final Fields) -------
    ================================================================ */

    /** A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -6870003831272220140L;
    
    /**
     * The panel width.
     */
    private static final int WIDTH = 400;

    /**
     * The panel height.
     */
    private static final int HEIGHT = 400;

    
    /* ================================================================
       --- Declaring Instance Variables(Internal State / Fields) ---
    ================================================================ */
    
    /** Drawing panel. */
    private JPanel myDrawingPanel;
    
    /** Drawing tools list.*/
    private final List<DrawingTool> myDrawingToolsList 
                                        = new ArrayList<DrawingTool>();
    
    /** A list of tool actions. */
    private List<Action> myToolActions;    
    
    
    /* ================================================================
       -------------------- Making constructors --------------------
    ================================================================ */

    /**
     * Create an instance of PowerPaintGUI class.
     */
    PowerPaintGUI() {
        super("PowerPaint");
    }
 
    /* ================================================================
       -------------- Calculation / Auxiliary methods --------------
    ================================================================ */
 
    /**
     * Layout the components and makes this frame visible.
     */
    public void start() {

        // Make window and install some panels into it.
        initializePanels();
        
        
        //========================== ADD JPANEL
        myDrawingPanel = new DrawingPanel();
        add(myDrawingPanel);

        // Make a list of drawing tools
        createDrawingToolsList(myDrawingToolsList);
        
        // initializes myActions
        setupActions();
        
        //========================== ADD MENUS        
        add(new MyToolBar(myToolActions), BorderLayout.SOUTH);       
        setJMenuBar(new MyMenuBar(myToolActions, myDrawingPanel));
   
        // Place the frame in the center of a screen.
        setLocationRelativeTo(null);
        setPreferredSize(getSize());   
//        setMinimumSize(getSize());
//        pack();      align to real size with all JToolBar buttons to be visible on a screen
        setVisible(true);

    } // end start()

    /**
     * Initializes main JFrame and JPanel components.
     */
    private void initializePanels() {

        // SET FRAME
        final Dimension frameSize = new Dimension(WIDTH, HEIGHT);
        
        // SET ICON
        final ImageIcon icon = new ImageIcon("images/1.gif");
        setIconImage(icon.getImage());        

        setSize(frameSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } // end initializePanels
    
    
    /**
     * Initialize all drawing tools and save them in the ArrayList.
     * 
     * @param theDrawingToolsList is an empty list for shapes.
     */
    private void createDrawingToolsList(final List<DrawingTool> 
                                             theDrawingToolsList) {        
        
        theDrawingToolsList.add(new Pencil());
        theDrawingToolsList.add(new Line());
        theDrawingToolsList.add(new Rectangle());
        theDrawingToolsList.add(new Square());
        theDrawingToolsList.add(new Ellipse());
        theDrawingToolsList.add(new Circle());
        theDrawingToolsList.add(new Eraser());          
        
    } // end createDrawingToolsList
    
    
    /**
     * Sets up all the Actions.
     */
    private void setupActions() {
        
        myToolActions = new ArrayList<Action>();
        
        for (final DrawingTool shape : myDrawingToolsList) {
            
            myToolActions.add(new ToolAction(shape, myDrawingPanel));
        }

    }    
    
} // end PowerPaint class
