/*
 * MyToolBar.java
 *
 * TCSS 305 - Spring 2017 
 * Instructor: Charles Bryan 
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint) 
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package gui;

import java.util.List;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * The tool bar. 
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class MyToolBar extends JToolBar {

    /* ================================================================
       ------ Declaring Class Constants(Static Final Fields) -------
    ================================================================ */
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 5395018690106241891L;
    
    
    /* ================================================================
       -------------------- Making constructors --------------------
    ================================================================ */
    
    /**
     * Create tool bar instance.
     * 
     * @param theActionsList is the Action list.
     */
    public MyToolBar(final List<Action> theActionsList) {
        
        super();
        
        final List<Action> toolActions = theActionsList;
     
        final ButtonGroup toolBarButtonsGroup = new ButtonGroup();
        
        for (final Action drawingToolType : toolActions) {
            final JToggleButton toolBarToggleButton = new JToggleButton(drawingToolType);
            
            if ("Eraser".equals(toolBarToggleButton.getText())) {
                toolBarToggleButton.setMnemonic('a');
            }
            
            toolBarButtonsGroup.add(toolBarToggleButton);
            this.add(toolBarToggleButton);
        }

    }
    
} // end MyToolBar class
