package ml.kanfa.qv.swing.components.larrv;

import java.awt.*;

/**
 * Class ElementColor
 *
 * @author Ibrahim Maïga.
 */
public class ElementColor {

    /**
     * Default element color
     */
    private static Color defaultBackground = Color.WHITE;
    private static Color defaultForegroundColor = Color.BLACK;
    private static Color defaultBorderColor = Color.BLACK;

    /**
     * Background color
     */
    private Color backgroundColor;

    /**
     * Foreground color
     */
    private Color foregroundColor;

    /**
     * Border color
     */
    private Color borderColor;

    /**
     * Creaete a new <code>ElementColor</code> with specified colors
     * @param background color of background
     * @param foreground color of foreground
     * @param border color of border
     */
    public ElementColor(Color background, Color foreground, Color border){
        this.backgroundColor = background;
        this.foregroundColor = foreground;
        this.borderColor = border;
    }

    /**
     * Create a new <code>ElementColor</code> with default colors
     */
    public ElementColor(){
        this(defaultBackground, defaultForegroundColor, defaultBorderColor);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroudColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
