package ml.kanfa.qv.swing.components.tf;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import ml.kanfa.qv.swing.Round;
import ml.kanfa.qv.swing.Icon;

/**
 * @author  Kanfa.
 *
 * Class <code>RoundedTextField</code>
 *
 */
public class RoundedTextField extends JTextField{

    /**
     * Insets
     */
    private static final int INSET = 4;

    /**
     * Instance variables
     */
    private Image image;
    private ImageIcon icon;
    private Shape shape;
    private Round round;

    /**
     * Create a new Constructor with icon name and round arc
     * @param name  icon name
     * @param round round attribute
     */
    public RoundedTextField(String name, Round round){
        super();
        this.round = round;
        this.set(name);
    }

    /**
     * Create a new constructor with icon name and default round arc
     * @param name icon name
     */
    public RoundedTextField(String name){
        this(name, new Round());
    }

    /**
     * Create a new constructor with default values
     * @param round round value
     */
   public RoundedTextField(Round round){
       this("", round);
   }

    /**
     * Create a new constructor with default attributes
     */
    public RoundedTextField(){
        this("", new Round());
    }


    @Override public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, this.round.getArcx(), this.round.getArcy());
        if (image != null){
		    g2d.drawImage(image, 5, this.getPreferredSize().height / 2 - icon.getIconHeight() / 2, null);
		}
        super.paintComponent(g2d);
    }

    /**
     * Paints the component's border.
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, this.round.getArcx(), this.round.getArcy());
    }

    @Override public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, this.round.getArcx(), this.round.getArcy());
        }
        return shape.contains(x, y);
    }

    @Override public Insets getInsets() {
        return image == null ? super.getInsets() : new Insets(INSET, icon.getIconWidth() + (INSET * 2), INSET, 0);
    }

    /**
     * Change the TextField icon
     * @param name image name
     * @see #initialize
     */
    public void changeIcon(String name){
        this.initialize(name, true);
    }

    /**
     * Initialise construct values
     * @param name image name
     */
    private void set(String name){
        this.initialize(name, false);
    }

    /**
     * Call to initialise elements or change icon
     * @param name image name
     * @param repaint true if can repaint
     */
    private void initialize(String name, boolean repaint){
        if (name.isEmpty()){
            this.image = null;
        }
        else {
            this.icon = (new Icon(name)).getIcon();
            this.image = this.icon.getImage();
        }
        if (repaint) this.repaint();
    }
}
