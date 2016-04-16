package ml.kf.qv.swing;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @author Kanfa.
 *
 * Class <code>RoundedBorder</code>
 */
public class RoundedBorder implements Border{

    /**
     * Position of painted border
     */
    private int arcx, arcy;

    /**
     * Border color
     */
    private Color color;

    /**
     * Default position of painted border
     */
    private static int DEFAULT_ARCX = 5;
    private static int DEFAULT_ARCY = 5;


    private static final int INSET = 2;
    /**
     * Border default Color
     */

    private static final Color DEFAULT_COLOR = new Color(128, 128, 128);

    /**
     * Create a new border with specified position
     * @param arcx the x position of the painted border
     * @param arcy the y position of the painted border
     */
    public RoundedBorder(int arcx, int arcy){
        this(DEFAULT_COLOR, arcx, arcy);
    }

    /**
     * Create a new border with default position and color
     */
    public RoundedBorder(){
        this(DEFAULT_COLOR, DEFAULT_ARCX, DEFAULT_ARCY);
    }

    /**
     * Create a new border with specified color and position
     * @param color border color
     * @param arcx the x position of the painted border
     * @param arcy the y position of the painted border
     */
    public RoundedBorder(Color color, int arcx, int arcy) {
        this.arcx = arcx;
        this.arcy = arcy;
        this.color = color;
    }

    /**
     * Create a new border with specified color
     * @param color border color
     */
    public RoundedBorder(Color color){
        this(color, DEFAULT_ARCX, DEFAULT_ARCY);
    }

    /**
     * Paints the border for the specified component with the specified
     * position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(this.color);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(x + (INSET / 2), y + (INSET / 2), width - INSET, height - INSET, this.arcx, this.arcy);
    }

    /**
     * Returns the insets of the border.
     * @param c the component for which this border insets value applies
     */
    @Override public Insets getBorderInsets(Component c) {
        return new Insets(INSET, 0, INSET, 0);
    }

    /**
     * Returns whether or not the border is opaque.  If the border
     * is opaque, it is responsible for filling in it's own
     * background when painting.
     */
    @Override public boolean isBorderOpaque() {
        return false;
    }
}
