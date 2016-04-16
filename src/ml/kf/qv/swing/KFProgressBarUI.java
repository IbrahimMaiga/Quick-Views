package ml.kf.qv.swing;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

/**
 * @author Kanfa.
 *
 * Class KFProgressBarUI
 */
public class KFProgressBarUI extends BasicProgressBarUI{

    Rectangle r = new Rectangle();
    public KFProgressBarUI(){
    }

    @Override protected void paintDeterminate(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        r = c.getBounds();
        g.setColor(progressBar.getForeground());
        g.fillOval(r.x, r.y, r.width, r.height);
        super.paintDeterminate(g, c);
    }
}
