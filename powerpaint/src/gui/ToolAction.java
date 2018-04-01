/*
 * ToolAction.java
 *
 * TCSS 305 - Spring 2017
 * Instructor: Charles Bryan
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint)
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import tools.DrawingTool;

/**
 * Sets the current tool to draw on the panel, and the specified color.
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class ToolAction extends AbstractAction {
    
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 3151858024415447740L;
    
    /** The drawing tool to use. */
    private final DrawingTool myDrawingTool;
    
    /** The component to work on in this action. */
    private final JComponent myComponent;
    
    /**
     * Creates action for a drawing tool.
     * 
     * @param theDrawingTool is the chosen tool to draw.
     * @param theComponent is a place to use the chosen drawing tool.
     */
    public ToolAction(final DrawingTool theDrawingTool,
                      final JComponent theComponent) {
        
        super(theDrawingTool.getDescription());
        myDrawingTool = theDrawingTool;
        myComponent = theComponent;
        
        putKeys(theDrawingTool.getDescription(),
                theDrawingTool.getIcon());        
    }
    
    
    /**
     * Helper to set up the putValue for this action. 
     * 
     * @param theName The name.
     * @param theIcon The icon.
     */
    private void putKeys(final String theName, final Icon theIcon) {
        
        // small icons are usually assigned to the menu
//        putValue(Action.SMALL_ICON, theIcon);
        
        // Here is how to assign a larger icon to the tool bar.
        final ImageIcon icon = (ImageIcon) theIcon;
        final Image largeImage =
            icon.getImage().getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon largeIcon = new ImageIcon(largeImage);
        putValue(Action.LARGE_ICON_KEY, largeIcon);
        
        // set a mnemonic on the first character of the name
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        
        // tool tips
        putValue(Action.SHORT_DESCRIPTION, theName + " background");
   
        // coordinate button selection
        putValue(Action.SELECTED_KEY, true);

    }

    /**
     * Sets current figure to draw.
     */
    @Override
    public void actionPerformed(final ActionEvent arg0) {
//        System.out.println(myDrawingTool.getDescription());
        ((DrawingPanel) myComponent).setCurrentDrawinfTool(myDrawingTool);
        
        if ("Pencil".equals(myDrawingTool.getDescription()) 
                        || "Eraser".equals(myDrawingTool.getDescription())
                        || "Line".equals(myDrawingTool.getDescription())) {
            ((DrawingPanel) myComponent).makeUnfilledShape();
            
        } else if (((DrawingPanel) myComponent).isShapeShouldBeFilled()) {
            ((DrawingPanel) myComponent).setShapeShouldBeFilled(true);
        }
        
    }

}
