package ml.kf.qv.swing.alert;

import com.sun.awt.AWTUtilities;
import com.sun.java.swing.plaf.windows.WindowsProgressBarUI;
import ml.kf.qv.swing.BasePanel;
import ml.kf.qv.swing.KFProgressBarUI;
import ml.kf.qv.swing.Round;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Kanfa
 *
 * Class Alert
 */
public abstract class Alert extends JWindow implements Runnable, MouseListener{


    private static final Dimension MINIMUM_SIZE = new Dimension(300, 100);
    private static int width;
    private static int height;
    private static Point centerPoint;
    private static int MAX = 100;
    private static int MIN = 0;
    private static final float INITIAL = 1.0f;
    protected static long SLEEP_TIME_BEFORE_ANIMATION = 2000;
    protected static long DEAULT_SLEEP_TIME = 40;
    protected static int INCREMENT = 10;

    static {
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    }

    protected JPanel container;
    private Thread thread;
    private JComponent component;
    protected JProgressBar progress;
    private boolean stop = false;
    private boolean sleep = true;
    private boolean animated = true;
    protected boolean showProgress = false;
    private Point location;
    private long time;
    protected Round round;
    private ImageIcon icon;
    private JLabel quit;

    private final int WIDTH = 5;
    private final int HEIGHT = 5;
    private final int PROGRESSBAR_HEIGHT = 5;

    /**
     * Position values
     */
    public static final int TOP_LEFT = 1;
    public static final int TOP_CENTER = 2;
    public static final int TOP_RIGHT = 3;
    public static final int CENTER = 4;
    public static final int BOTTOM_LEFT = 5;
    public static final int BOTTOM_CENTER = 6;
    public static final int BOTTOM_RIGHT = 7;
    public static final int LEFT_CENTER = 8;
    public static final int RIGHT_CENTER = 9;

    /**
     * Create a new constructor with specified values
     * @param pos pre define value for location
     * @param component
     * @param icon
     * @param showProgress
     * @param round
     * @param time
     */
    public Alert(int pos, JComponent component, ImageIcon icon, boolean showProgress, Round round, long time){
        this.set(component, icon, showProgress, round, time);
        this.setLocation((this.location = this.getLocation(pos)));
        this.initComponents();
    }

    /**
     *
     * @param posx
     * @param posy
     * @param component
     * @param icon
     * @param showProgress
     * @param round
     * @param time
     */
    public Alert(int posx, int posy, JComponent component, ImageIcon icon, boolean showProgress, Round round, long time){
        this.set(component, icon, showProgress, round, time);
        this.location = new Point(posx, posy);
        this.setLocation(this.location);
        this.initComponents();
    }

    /**
     *
     * @param component
     * @param icon
     * @param showProgress
     * @param round
     * @param time
     */

    private void set(JComponent component, ImageIcon icon, boolean showProgress, Round round, long time){
        this.component = component;
        this.showProgress = showProgress;
        this.round = round;
        this.time = time;
        this.icon = icon;
    }

    /**
     *
     * @param pos
     * @return the location corresponding of pos
     */
    private Point getLocation(int pos){
        switch (pos){
            case TOP_CENTER:
                return new Point(centerPoint.x - this.getPreferredSize().width / 2, HEIGHT);
            case TOP_LEFT:
                return new Point(WIDTH, HEIGHT);
            case TOP_RIGHT:
                return new Point(width - (this.getPreferredSize().width + WIDTH), HEIGHT);
            case CENTER:
                return new Point(centerPoint.x - this.getPreferredSize().width / 2,
                                 centerPoint.y - this.getPreferredSize().height / 2);
            case BOTTOM_LEFT:
                return new Point(WIDTH, height - (this.getPreferredSize().height + (10 * HEIGHT)));
            case BOTTOM_CENTER:
                return new Point(centerPoint.x - this.getPreferredSize().width / 2,
                                 height - (this.getPreferredSize().height + (10 * HEIGHT)));
            case BOTTOM_RIGHT:
                return new Point(width - (this.getPreferredSize().width + WIDTH),
                                 height - (this.getPreferredSize().height + (10 * HEIGHT)));
            case LEFT_CENTER:
                return new Point(WIDTH, centerPoint.y - this.getPreferredSize().height / 2);
            case RIGHT_CENTER:
                return new Point(width - (this.getPreferredSize().width + WIDTH),
                                 centerPoint.y - this.getPreferredSize().height / 2);
            default: return new Point(0, 0);
        }
    }

    /**
     * Initialise components
     */
    private void initComponents(){
        this.setMinimumSize(MINIMUM_SIZE);
        this.addMouseListener(this);
        this.progress = new JProgressBar(JProgressBar.HORIZONTAL);
        this.progress.setIndeterminate(false);
        this.progress.setUI(new WindowsProgressBarUI());
        this.progress.setBorder(progressBorder());
        this.progress.setBackground(progressBackground());
        this.progress.setForeground(progressForeground());
        this.progress.setValue(MAX);
        this.progress.setUI(new KFProgressBarUI());
        this.progress.setMinimumSize(new Dimension(MINIMUM_SIZE.width, PROGRESSBAR_HEIGHT));
        this.progress.setBorderPainted(true);

        this.setAlwaysOnTop(true);
        this.container = new BasePanel();
        this.container.setLayout(new BorderLayout());
        this.container.add(this.component, BorderLayout.CENTER);

        JPanel quitPan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        quitPan.setOpaque(false);
        quit = new JLabel("x"){
        };

        quitPan.add(quit);
        quit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.quit.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                stop = true;
            }

            @Override public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                animated = false;
            }

            @Override public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                animated = true;
            }
        });

        this.container.add(quitPan, BorderLayout.NORTH);
        if (this.showProgress){
            this.container.add(progress, BorderLayout.SOUTH);
        }
        this.container.add(new JLabel(this.icon), BorderLayout.WEST);
        this.container.setBackground(background());
        AWTUtilities.setWindowShape(this, shape());
        this.customize();
        this.setContentPane(this.container);
        this.pack();
        this.setVisible(true);
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Returns location
     * @return
     */
    public Point getLocation(){
        return this.location;
    }

    @Override public void mouseClicked(MouseEvent mouseEvent) {}

    @Override public void mousePressed(MouseEvent mouseEvent) {}

    @Override public void mouseReleased(MouseEvent mouseEvent) {}

    @Override public void mouseEntered(MouseEvent mouseEvent) {
        this.animated = false;
    }

    @Override public void mouseExited(MouseEvent mouseEvent) {
        this.animated = true;
        this.time -= INCREMENT;
    }

    @Override public void run() {
        float i = INITIAL;
        while (i >= 0.01f && i <= 1.0f) {
            if (!this.stop) {
                if (this.animated) {
                    try {
                        if (this.sleep) {
                            Thread.sleep(SLEEP_TIME_BEFORE_ANIMATION);
                            this.sleep = false;
                        }
                        i -= 0.01f;
                        if (this.showProgress) {
                            this.progress.setValue((int) (MAX * i));
                        }
                        Thread.sleep(this.time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    i = INITIAL;
                    if (this.showProgress) {
                        this.progress.setValue(MAX);
                    }
                }
                setOpacity(i);
            }
            else{
                i = -1;
            }
        }
        dispose();
    }

    /*
       Abstract methods
     */
    protected abstract void customize();

    protected abstract Color progressBackground();

    protected abstract Color progressForeground();

    protected abstract Border progressBorder();

    protected abstract Color background();

    protected abstract Shape shape();
}