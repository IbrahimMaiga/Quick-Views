package ml.kanfa.qv.swing.components.larrv;

import ml.kanfa.qv.swing.Round;
import ml.kanfa.qv.swing.Icon;

import javax.swing.*;
import java.awt.*;

/**
 * Class LarrStyle
 * @author Ibrahim Maïga.
 */
public class LarrStyle {

    public static final int ENTERED = 1;
    public static final int EXITED = 2;
    public static final int SELECTED = 3;

    /**
     * Round value and Color effect
     */
    private Round round;
    private Effect effect;
    private Icon icon;

    /**
     * Create a new <code>LarrStyle</code> with specified params
     * @param round @see {@link Round}
     * @param effect @see {@link Effect}
     * @param icon @see {@link Icon}
     */
    public LarrStyle(Round round, Effect effect, Icon icon){
        this.round = round;
        this.effect = effect;
        this.icon = icon;
    }

    /**
     * Create a new <code>LarrStyle</code> with default values
     */
    public LarrStyle(){
        this(new Round(), new Effect(), new Icon());
    }

    /**
     * @param element
     * @param type
     * @return corresponding color
     */
    public Color getColor(String element, int type){
        return this.effect.get(element, type);
    }

    public ImageIcon getImage(){
        return this.icon.getIcon();
    }

    public int getArcx(){
        return this.round.getArcx();
    }

    public int getArcy(){
        return this.round.getArcy();
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void setEffect(Effect effect){
        this.effect = effect;
    }

    public void setIcon(Icon icon){
        this.icon = icon;
    }
}
