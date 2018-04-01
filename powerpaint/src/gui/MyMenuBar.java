/*
 * MyMenuBar.java
 *
 * TCSS 305 - Spring 2017 
 * Instructor: Charles Bryan 
 * Student: Denis Yakovlev
 * Programming Assignment 5 (PowerPaint) 
 * The due date: 05/17/2017 (Wednesday) by 11:59pm
 */

package gui;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * The menu bar. 
 * In the code were used examples given by Charles Bryan.
 *
 * @author Denis Yakovlev
 * @version 16 May 2017
 */
public class MyMenuBar extends JMenuBar {    
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -5140482244683777924L;
    
    /**
     * The initial thickness.
     */
    private static final int INITIAL_THICKNESS = 1;

    /**
     * The maximum thickness.
     */
    private static final int MAX_THICKNESS = 20;
    
    /**
     * The minor tick spacing for the FPS slider.
     */
    private static final int MINOR_TICK_SPACING = 1;

    /**
     * The major tick spacing for the FPS slider.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /**
     * The name for Color Chooser.
     */
    private static final String COLOR_CHOOSER = "A Color Chooser";
    
    /**
     * The name for About option.
     */
    private static final String ABOUT_AUTHOR = "About";
   
    /**
     * The slider for thickness.
     */
    private JSlider mySlider;
    
    /** A list of tool actions. */
    private final List<Action> myToolActions;    
    
    /** The Drawing Panel. */
    private final JComponent myDrawingPanel;
    
    /**
     * An instance for menu item Clear.
     */
    private final JMenuItem myClear = new JMenuItem("Clear");
    
    /**
     * An instance for menu item Fill.
     */
    private final JCheckBoxMenuItem myFill = new JCheckBoxMenuItem("Fill");
    
    
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
     * An Icon instance for primary color.
     */
    private final MyIcon myPrimaryIcon = new MyIcon();
    
    /**
     * An Icon instance for primary color.
     */
    private ImageIcon myPrimaryImageIcon 
                = new ImageIcon(myPrimaryIcon.createIcon(myPrimaryColor));
    
    
    /**
     * An Icon instance for primary color.
     */
    private final MyIcon mySecondaryIcon = new MyIcon();
    
    /**
     * An Icon instance for primary color.
     */
    private ImageIcon mySecondaryImageIcon 
            = new ImageIcon(mySecondaryIcon.createIcon(mySecondaryColor));
    
    /**
     * Create an instance of menu bar.
     * 
     * @param theActionsList is the Action list.
     * @param theDrawingPanel is the drawing panel.
     */
    public MyMenuBar(final List<Action> theActionsList,
                     final JComponent theDrawingPanel) {
        
        super();
        
        myToolActions = theActionsList;
        myDrawingPanel = theDrawingPanel;
        
        add(createFileMenu());
        add(createOptionsMenu());
        add(createToolsMenu(theActionsList));
        add(createHelpMenu());
        
    }
    
    /**
     * Creates JMenu to handle the File options.
     * 
     * @return created JMenu.
     */
    private JMenu createFileMenu() {
        
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.addMenuListener(new MenuListener() {
            
            @Override
            public void menuSelected(final MenuEvent theEvent) {

                if (((DrawingPanel) myDrawingPanel).isClearButtonShouldBeVisible()) {
                    setClearButtonVisibilityToTrue();
                } else {
                    setClearButtonVisibilityToFalse();
                }
            }

            @Override
            public void menuCanceled(final MenuEvent theEvent) {  
            }

            @Override
            public void menuDeselected(final MenuEvent theEvent) {
            }
        });      
        
        // CLEAR option
        myClear.setMnemonic(KeyEvent.VK_C);
        myClear.setEnabled(false);        
        myClear.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {               
                ((DrawingPanel) myDrawingPanel).setDeleteAllShapes();                
            }
        });
        fileMenu.add(myClear);
        fileMenu.addSeparator();
        
        // QUIT option
        final JMenuItem quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(0);
            }
        });
        fileMenu.add(quit);
        
        return fileMenu;        
    }
    
    
    
    /**
     * Creates JMenu to handle the Options options.
     * 
     * @return created JMenu.
     */
    private JMenu createOptionsMenu() {
        
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        optionsMenu.addMenuListener(new MenuListener() {
            
            @Override
            public void menuSelected(final MenuEvent theEvent) {
                
                if (((DrawingPanel) myDrawingPanel).isFillButtonShouldBeVisible()) {
                    setFillButtonVisibilityToTrue();
                } else {
                    setFillButtonVisibilityToFalse();
                }                
            }
            
            @Override
            public void menuDeselected(final MenuEvent theEvent) {
            }
            
            @Override
            public void menuCanceled(final MenuEvent theEvent) {  
            }
        });
                       
        // THICKNESS option
        final JMenu thickness = new JMenu("Thickness");
        thickness.setMnemonic(KeyEvent.VK_T);
        thickness.add(createSlider());        
        optionsMenu.add(thickness);
        
        optionsMenu.addSeparator();
        
        // PRIMaRY COLOR option
        final JMenuItem primaryColor = new JMenuItem("Primary Color...");
        primaryColor.setIcon(myPrimaryImageIcon);
        primaryColor.setMnemonic(KeyEvent.VK_P);
        primaryColor.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, COLOR_CHOOSER, null);
                if (result != null) {
                    // Send a new primary color to the drawing panel
                    ((DrawingPanel) myDrawingPanel).setPrimaryColor(result);
                    // Set a new color to the icon for a primary color
                    myPrimaryColor = result;
                    myPrimaryImageIcon 
                              = new ImageIcon(myPrimaryIcon.createIcon(myPrimaryColor));
                    primaryColor.setIcon(myPrimaryImageIcon);
                }
            }
        });
        
        optionsMenu.add(primaryColor);      
        
        // SECONDARY COLOR option
        final JMenuItem secondaryColor = new JMenuItem("Secondary Color...");
        secondaryColor.setIcon(mySecondaryImageIcon);
        secondaryColor.setMnemonic(KeyEvent.VK_S);
        secondaryColor.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, COLOR_CHOOSER, null);
                if (result != null) {
                    ((DrawingPanel) myDrawingPanel).setSecondaryColor(result);
                    mySecondaryColor = result;
                    mySecondaryImageIcon 
                            = new ImageIcon(mySecondaryIcon.createIcon(mySecondaryColor));
                    secondaryColor.setIcon(mySecondaryImageIcon);
                }
            }
        });
        optionsMenu.add(secondaryColor);        
        optionsMenu.addSeparator();
         
        // FILL option
        myFill.setMnemonic(KeyEvent.VK_F);
        myFill.setVisible(true);
        optionsMenu.add(myFill);
        myFill.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {

                if (myFill.isSelected()) {
                    ((DrawingPanel) myDrawingPanel).setShapeShouldBeFilled(true);
//                    ((DrawingPanel) myDrawingPanel).makeFilledShape();
                } else {
                    ((DrawingPanel) myDrawingPanel).setShapeShouldBeFilled(false);
//                    ((DrawingPanel) myDrawingPanel).makeUnfilledShape();
                }
                
                ((DrawingPanel) myDrawingPanel).revalidate();                
            }
        });
        
        return optionsMenu;        
    }
    
    
    /**
     * Creates JMenu to handle the Tools options.
     * 
     * @param theActionsList i s the Action list.
     * @return created JMenu.
     */
    private JMenu createToolsMenu(final List<Action> theActionsList) {
        // Get rid of PMD warning for unused parameter.
        theActionsList.size();
        
        final JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        
        final ButtonGroup menuBarButtonsGroup = new ButtonGroup();
        
        for (final Action drawingToolType : myToolActions) {
            final JRadioButtonMenuItem menuBarRadioButton 
                           = new JRadioButtonMenuItem(drawingToolType);

            if ("Line".equals(menuBarRadioButton.getText())) {
                menuBarRadioButton.setSelected(true);
            }
            
            if ("Eraser".equals(menuBarRadioButton.getText())) {
                menuBarRadioButton.setMnemonic('a');
            }
            
            menuBarButtonsGroup.add(menuBarRadioButton);
            toolsMenu.add(menuBarRadioButton);
        }
        
        return toolsMenu;
        
    }
    
    
    
    /**
     * Creates JMenu to handle the Help options.
     * 
     * @return created JMenu.
     */
    private JMenu createHelpMenu() {
        
      //****************************** HELP
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        // SET MNEMONIC FOR JMenuItem
        //JMenuItem about = new JMenuItem("About");
        final JMenuItem about = new JMenuItem();
        final Action mnemonicAbout = new AbstractAction(ABOUT_AUTHOR) {
            
            /** A generated serialization ID. */
            private static final long serialVersionUID = 781585116279244073L;

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
 //               about();
            }
        };
         
        mnemonicAbout.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        about.setAction(mnemonicAbout);        
//        about.setMnemonic(KeyEvent.VK_A);
        
        // SET ACTION LISTENER
        final ActionListener waitForAbout = new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                about();                
            }
        };
        about.addActionListener(waitForAbout);
        helpMenu.add(about);
        
        return helpMenu;
    } 
    
    
    /**
     * A helper method to create a Slider in the menu.
     * 
     * @return the Tool Bar
     */
    private JSlider createSlider() {
        
//        mySlider = new JSlider(SwingConstants.HORIZONTAL, 0);
        mySlider = new JSlider(SwingConstants.HORIZONTAL, 
                               0, 
                               MAX_THICKNESS, 
                               INITIAL_THICKNESS);
                
        mySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        mySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        
        
        mySlider.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                
                final int value = mySlider.getValue();
                if (value > -1) {
                    ((DrawingPanel) myDrawingPanel).setLineWidthOfShapeContour(value);
                }
                
            }
        });

        return mySlider;
    }
    
    
    /**
     * Show the author and the project info.
     */
    private void about() {
        final String message 
            = "Denis Yakovlev\nSpring 2017\nTCSS 305 PowerPaint";
        JOptionPane.showMessageDialog(new JPanel(), 
                                  message, 
                                  ABOUT_AUTHOR,
                                  JOptionPane.INFORMATION_MESSAGE,
                                  new ImageIcon("images/1.gif"));
    }
    
    /** Set clear button to be enabled.  */
    protected void setClearButtonVisibilityToTrue() {
        myClear.setEnabled(true);
    }
    
    /** Set clear button to be disabled.  */
    protected void setClearButtonVisibilityToFalse() {
        myClear.setEnabled(false);
    }

    /** Set fill button to be enabled.  */
    protected void setFillButtonVisibilityToTrue() {
        myFill.setEnabled(true);
    }
    
    /** Set fill button to be disabled.  */
    protected void setFillButtonVisibilityToFalse() {
        myFill.setEnabled(false);
    }
}
