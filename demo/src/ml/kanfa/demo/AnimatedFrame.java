package ml.kanfa.demo;


import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

/**
 * @author Ibrahim Maïga.
 */
public class AnimatedFrame extends JFrame {

    int w = 400, h = 400;
    public AnimatedFrame(){
        this.setSize(w, h);
        JPanel container = new JPanel();
        container.add(new JLabel("Kanfa"));
        LayerUI<JPanel> layer = new TestLayer();
        JLayer<JPanel> decorator = new JLayer<>(container, layer);
        this.add(decorator);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public class TestLayer extends LayerUI<JPanel> {
        private Image image;
        public TestLayer(){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/ml/kanfa/demo/1.gif"));
            this.image = (icon).getImage();
        }
        @Override
        public void paint(Graphics g1, JComponent c) {
            super.paint(g1, c);
            Graphics2D g = (Graphics2D)g1;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            Rectangle bounds = c.getBounds();
            g.fillRect(0, 0, 400, 400);
        }

        @Override
        protected void processComponentEvent(ComponentEvent e, JLayer<? extends JPanel> l) {

        }

        @Override
        protected void processMouseEvent(MouseEvent e, JLayer<? extends JPanel> l) {
            System.out.println("super");
        }
    }

    public static void main(String[] args){
        new AnimatedFrame();
    }
}
