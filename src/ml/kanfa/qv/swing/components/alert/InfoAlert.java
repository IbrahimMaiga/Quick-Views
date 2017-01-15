package ml.kanfa.qv.swing.components.alert;

import ml.kanfa.qv.swing.*;
import ml.kanfa.qv.swing.Icon;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Class InfoAlert
 *
 * @author Ibrahim Maïga.
 */
public class InfoAlert extends Alert{

    public InfoAlert(int pos, JComponent component, ImageIcon icon, boolean showProgress, Round round, long time) {
        super(pos, component, icon, showProgress, round, time);
    }

    public InfoAlert(int posx, int posy, JComponent component, ImageIcon icon, boolean showProgress, Round round, long time) {
        super(posx, posy, component, icon, showProgress, round, time);
    }

    public InfoAlert(int pos, JComponent component){
        this(pos, component, (new Icon("info-60x60.png")).getIcon(),
             true, new Round(), DEAULT_SLEEP_TIME);
    }

    public InfoAlert(int posx, int posy, JComponent component){
        this(posx, posy, component, (new Icon("info-60x60.png")).getIcon(),
             false, new Round(), DEAULT_SLEEP_TIME);
    }

    @Override public void customize() {}

    @Override protected Color progressBackground() {
        return Color.GREEN;
    }

    @Override protected Color progressForeground() {
        return Color.GREEN;
    }

    @Override protected Border progressBorder() {
        return new LineBorder(Color.GREEN);
    }

    @Override protected Color background() {
        return Color.GREEN;
    }

    @Override protected Shape shape() {
        return new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(),
                                           this.round.getArcx(), this.round.getArcy());
    }
}
