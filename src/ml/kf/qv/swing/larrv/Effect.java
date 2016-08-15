package ml.kf.qv.swing.larrv;

import java.awt.*;

/**
 * @author Kanfa.
 *
 * Class Effect
 */
public class Effect {

    /**
     * Mouse entered color values for background, foreground and border
     */
    private ElementColor entered;

    /**
     * Mouse existed color values for background, foreground and border
     */

    private ElementColor exited;

    /**
     * Mouse clicked color values for background, foreground and border
     */

    private ElementColor selected;

    /**
     * Default element color for mouse entered
     */
    private final static ElementColor ENTERED = new ElementColor(Color.BLACK, Color.WHITE, Color.GREEN);

    /**
     * Default element color for mouse clicked
     */

    private final static ElementColor SELECTED = new ElementColor(Color.WHITE, Color.BLACK, Color.BLACK);

    /**
     * Default element color for mouse exited
     */

    private final static ElementColor EXITED = new ElementColor();

    /**
     * Action types
     */
    private static final int ENTER = 1;
    private static final int EXIT = 2;
    private static final int SELECT = 3;

    /**
     * Element types
     */
    private static final String BORDER = "Border";
    private static final String FOREGROUND = "Foreground";
    private static final String BACKGROUND = "Background";

    /**
     * Create a new <code>Effect</code> with specified <code>ElementColor</code>
     * @see ElementColor
     * @param entered
     * @param exited
     * @param selected
     */
    public Effect(ElementColor entered, ElementColor exited, ElementColor selected){
        this.entered = entered;
        this.exited = exited;
        this.selected = selected;
    }

    /**
     * Create a new <code>Effect</code> with default <code>ElementColor</code>
     * @see ElementColor
     */
    public Effect(){
        this(ENTERED, EXITED, SELECTED);
    }

    /**
     * Returns the corresponding color to the type of element and the action
     * @param element
     * @param type
     * @return corresponding color
     */
    public Color get(String element, int type){
        switch (element){
            case BACKGROUND:
                return this.getType(type).getBackgroundColor();
            case FOREGROUND:
                return this.getType(type).getForegroundColor();
            case BORDER:
                return this.getType(type).getBorderColor();
            default:
                throw new IllegalArgumentException("Paramètre invalide");
        }
    }

    /**
     *
     * @param type
     * @return element type
     */
    private ElementColor getType(int type){
        switch (type){
            case ENTER:
                return this.entered;
            case EXIT:
                return this.exited;
            case SELECT:
                return this.selected;
            default:
                throw new IllegalArgumentException("Paramètre invalide");
        }
    }

    /**
     * Getter an setter
     */

    public ElementColor getEntered() {
        return this.entered;
    }

    public void setEntered(ElementColor entered) {
        this.entered = entered;
    }

    public ElementColor getExited() {
        return this.exited;
    }

    public void setExited(ElementColor exited) {
        this.exited = exited;
    }

    public ElementColor getSelected(){
        return this.selected;
    }

    public void setSelected(ElementColor selected){
        this.selected = selected;
    }
}
