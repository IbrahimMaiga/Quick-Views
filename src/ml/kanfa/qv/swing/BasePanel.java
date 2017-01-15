package ml.kanfa.qv.swing;

import java.awt.Color;

import javax.swing.*;

/**
 * @author Ibrahim Maïga.
 *
 * class BasePanel
 */
public class BasePanel extends JPanel{

    /**
     * Default background color
     */
    private static final Color COLOR = Color.WHITE;

    /**
     * Creates a new <code>BasePanel</code> with define color
     * @param color background color
     */
    public BasePanel(Color color){
        this.setBackground(color);
    }

    /**
     * Create a new <code>BasePanel</code> with default background color
     */
    public BasePanel(){
        this(COLOR);
    }

}
