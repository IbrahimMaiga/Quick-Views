package ml.kf.qv.swing;

import java.awt.Color;

import javax.swing.*;

/**
 * @author Kanfa.
 *
 *
 * class BasePanel
 */
public class BasePanel extends JPanel{

    /**
     * <code>BasePanel</code> Background color
     */
    private static final Color COLOR = Color.WHITE;

    /**
     * Create a new <code>BasePanel</code> with background color white
     */
    public BasePanel(Color color){
        this.setBackground(color);
    }

    public BasePanel(){
        this(COLOR);
    }

}
