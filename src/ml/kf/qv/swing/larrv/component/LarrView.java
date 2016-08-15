package ml.kf.qv.swing.larrv.component;

import ml.kf.qv.swing.BasePanel;
import ml.kf.qv.swing.larrv.*;
import ml.kf.qv.swing.larrv.LarrController;
import ml.kf.qv.swing.tf.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Kanfa
 */
public class LarrView extends BasePanel implements KeyListener, Updater {

    /**
     * Constants
     */
    private static final int VSPACE = 2;
    private static final int FIRST = 0;
    private static final int SCROLLBAR_WIDTH = 10;
    private static final String DEFAULT_ICON_NAME = "search.png";
    private static final Dimension DEFAULT_DIMENSION = new Dimension(110, 50);

    /**
     * Lelt Panel
     */
    private BasePanel         left;
    /**
     * Receive Panel
     */
    private BasePanel         center;

    /**
     * Left panel scrollpane
     */
    private JScrollPane       scrollPane;

    /**
     * search panel textfield
     */
    private RoundedTextField        searchField;

    /**
     * contains left panel and search panel
     */

    private JPanel            content;

    /**
     * LarrController
     */
    private LarrController controller;
    /**
     * LarrViewChecher
     */
    private LarrComponentChecker checker;

    /**
     * LarrComponents list
     */
    private List<LarrComponent> larrComponents;

    /**
     * LarrElement
     */
    private List<LarrElement> larrElements;

    /**
     * Activate search panel
     */
    private boolean   active = false;

    /**
     * LarrComponent width and height
     */
    private int               width;
    private int               height;

    /**
     * Search panel
     */
    private BasePanel searchPanel;

    /**
     * Selected Larr Component
     */
    private LarrComponent selectedLarrComponent;

    /**
     * Larr Style
     */
    private LarrStyle style;

    private String searchIcon;

    private int lastSelectedIndex = -1;
    private int selectedIndex = -1;

    /**
     * Create a new <code>LarrView</code> instance with <code>LarrElement</code>
     * style <code>LarrStyle</code>and boolean active search panel
     * @see LarrElement
     * @see LarrStyle
     * @param larrElements
     * @param active
     */
    public LarrView(List<LarrElement> larrElements, LarrStyle style, boolean active) {
        this.larrElements = larrElements;
        this.active = active;
        this.style = style;
        AbstractLarrModel larrModel = new LarrModel(this);
        this.controller = new LarrController(larrModel);
        this.larrComponents = new ArrayList<>();
        this.createLarrComponents();
        this.checker = new LarrComponentChecker(this.larrComponents);
        this.setLayout(new BorderLayout());
        this.left = new BasePanel();
        this.center = new BasePanel();
        this.center.setLayout(new BorderLayout());
        this.content = new BasePanel();
        this.content.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.center.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.content.setLayout(new BorderLayout());
        this.left.setLayout(null);
        UIManager.put("ScrollBar.width", SCROLLBAR_WIDTH);
        this.scrollPane = new JScrollPane(this.left, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.addViews();
        if (active) {
            this.activeSearchField(this.searchIcon == null ? DEFAULT_ICON_NAME : this.searchIcon);
        }
        this.setOpaque(false);
        this.left.addKeyListener(this);
        this.left.setFocusable(true);
        this.content.add(scrollPane, BorderLayout.CENTER);
        this.add(this.content, BorderLayout.WEST);
        this.add(this.center, BorderLayout.CENTER);
    }

    /**
     * Create a new <code>LarrView</code> instance with <code>LarrElement</code>
     * style <code>LarrStyle</code>and boolean active search panel
     * @see LarrElement
     * @param larrElements
     * @param active
     */

    public LarrView(List<LarrElement> larrElements, boolean active){
        this(larrElements, new LarrStyle(), active);
    }

    /**
     * Initialze search panel
     * @param name
     */
    private void activeSearchField(String name){
        this.searchField = new RoundedTextField(name);
        this.searchField.setFont(new Font("Verdana", Font.PLAIN, 12));
        this.searchField.setForeground(Color.LIGHT_GRAY);
        this.searchPanel = new BasePanel();
        this.searchPanel.setLayout(new BorderLayout());
        this.searchPanel.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
        this.searchPanel.add(this.searchField, BorderLayout.CENTER);
        this.searchPanel.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
        this.searchPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        this.searchPanel.add(Box.createVerticalStrut(14), BorderLayout.SOUTH);
        this.content.add(this.searchPanel, BorderLayout.NORTH);
    }

    /**
     * Resize an add <code>LarrComponent</code> on left panel <code>left</code>
     */
    public void addViews() {
        this.resizeViews();
        for (int i = 0; i < this.larrComponents.size(); i++) {
            int y = (i == 0) ? VSPACE : i * (this.height + VSPACE) + VSPACE;
            this.larrComponents.get(i).setBounds(5, y, this.width, this.height);
            this.larrComponents.get(i).setReceive(this.center);
            this.left.add(this.larrComponents.get(i));
        }
        this.selectedLarrComponent = null;
    }

    /**
     * Resize views
     */
    private void resizeViews(){
        this.left.setPreferredSize(calcDimension());
        this.scrollPane.setPreferredSize(new Dimension(calcDimension().width + (int)UIManager.get("ScrollBar.width") + 2, calcDimension().height));
        this.attributeSameDimension();
    }

    /**
     * Calculates and returns the appropriate size
     * @return calcuate Dimension
     */
    private Dimension calcDimension(){
        this.width = this.getMaxDimension().width + 2;
        this.height = this.getMaxDimension().height;
        return new Dimension(this.width,
                             ((this.getMaxDimension().height + VSPACE) * (this.larrComponents.size())) + (2 * VSPACE));
    }

    /**
     * Returns the maximum dimension of <code>LarrComponent</code>
     * @return max dimension
     */
    private Dimension getMaxDimension() {
        if (this.larrComponents.isEmpty()) {
            return DEFAULT_DIMENSION;
        } else {
            LarrComponent max = this.larrComponents.get(0);
            for (LarrComponent larrComponent : this.larrComponents) {
                if (larrComponent.getTitle().length() > max.getTitle().length()) {
                    max = larrComponent;
                }
            }
            return max.getPreferredSize();
        }
    }

    /**
     * Gives the same size to the <code>LarrComponent</code>
     */
    private void attributeSameDimension() {
        int i = 0;
        for (LarrComponent larrComponent : this.larrComponents) {
            if (i != this.getLastIndex()){
                larrComponent.resizeLarrComponent(getMaxDimension());
            }
            i++;
        }
    }

    /**
     * creates <code>LarrComponents</code>
     */
    private void createLarrComponents() {
        int i = 0;
        for (LarrElement larrElement : this.larrElements) {
            LarrComponent larrComponent = new LarrComponent(i, larrElement.getTitle(), this.style);
            larrComponent.setReceive(this.center);
            larrComponent.setRight(larrElement.getComponent());
            this.larrComponents.add(larrComponent);
            i++;
        }
    }

    /**
     * Call the LarrView Controller for verification
     * @param index selected index
     */
    public void setData(final int index){
        this.selectedIndex = index;
        this.controller.control(index);
    }

    /**
     * Getter
     * @return true if search panel is activate
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Activates search panel
     * @param iconname icon name
     */
    public void activateSearch(String iconname) {
        if (!active){
            this.active = true;
            this.activeSearchField(this.searchIcon != null ? this.searchIcon : iconname.isEmpty() ? DEFAULT_ICON_NAME : iconname);
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * Disables search panel
     */
    public void deactivateSearch(){
        if (this.searchPanel != null){
            this.active = false;
            this.content.remove(this.searchPanel);
            this.revalidate();
            this.repaint();
        }
    }


    /**
     * Reset and return all <code>LarrComponent</code>
     * @return list of LarrComponents
     */
    public List<LarrComponent> getLarrComponents() {
        this.larrComponents.stream().filter(larrComponent -> larrComponent.isSelected()).forEach(LarrComponent::reset);
        return this.larrComponents;
    }

    /**
     * Reset and set larrComponents
     * @param larrComponents
     */
    public void setLarrComponents(final ArrayList<LarrComponent> larrComponents) {
        this.larrComponents.stream().filter(larrComponent -> larrComponent.isSelected()).forEach(larrComponent -> {
            larrComponent.setSelected(false);
            larrComponent.init();
        });
        this.larrComponents = larrComponents;
    }

    /**
     * Add a <code>LarrComponent</code> to left panel
     * @param larrElement
     */
    public void addLarrComponent(LarrElement larrElement) {
        LarrComponent larrComponent = new LarrComponent(this.getLastIndex(), larrElement.getTitle(), this.style);
        this.addElements(larrComponent, larrElement);
        this.addViews();
        this.revalidate();
        this.repaint();
        larrComponent.selected();
    }

    /**
     *
     * @param larrComponent
     * @param larrElement
     */
    private void addElements(LarrComponent larrComponent, LarrElement larrElement){
        this.larrComponents.add(larrComponent);
        this.larrElements.add(larrElement);
        larrComponent.setRight(larrElement.getComponent());
        larrComponent.setReceive(this.center);
    }

    /**
     * Removes the element corresponding to the <code>index</code> in the two array
     * @param index
     */
    private void removeIn(int index){
        this.larrComponents.remove(index);
        this.larrElements.remove(index);
    }

    private void removeAll(final JComponent... components){
        for (JComponent component : components){
            component.removeAll();
        }
    }

    private void revalidateAll(final JComponent... components){
        for (JComponent component : components){
            component.revalidate();
        }
    }

    /**
     * Removes selected <code>LarrComponent</code>
     */
    public void removeSelected() {
        if (this.selectedLarrComponent != null){
            this.larrComponents.remove(this.selectedLarrComponent);
            this.larrElements.remove(this.selectedLarrComponent.getIndex());
            this.reorganise();
            this.removeAll(this.left, this.center);
            this.addViews();
            this.revalidateAll(this.left, this.center);
            this.repaint();
            this.selectedLarrComponent = null;
            this.selectedIndex = this.lastSelectedIndex = -1;
            this.left.requestFocus();
        }
    }

    /**
     * Reorganise Ids
     */
    private void reorganise(){
        for (int i = 0; i < this.larrComponents.size(); i++){
            this.larrComponents.get(i).setIndex(i);
        }
    }

    /**
     * Returns Selected LarrComponent
     */

    public LarrComponent getSelected(){
        return this.selectedLarrComponent;
    }

    /**
     * Removes all <code>LarrComponent</code>
     */
    public void removeLarrComponents() {
        this.larrComponents.clear();
        this.larrElements.clear();
        this.selectedLarrComponent = null;
        this.removeAll(this.left, this.center);
        this.revalidateAll(this.left, this.center);
        this.repaint();
        this.selectedIndex = this.lastSelectedIndex = -1;
    }

    /**
     * Returns last index
     * @return last index
     */
    public int getLastIndex() {
        return this.larrComponents.size();
    }

    public String getSearchIcon(){
        return this.searchIcon;
    }

    public void setLastSelectedIndex(int lastSelectedIndex){
        this.lastSelectedIndex = lastSelectedIndex;
    }

    private void changeSearchIcon(final String searchIcon){
        this.searchField.changeIcon(searchIcon);
    }

    public void setSearchIcon(final String searchIcon){
        if (this.active){
            this.searchIcon = searchIcon;
            this.changeSearchIcon(searchIcon);
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        this.controller.control(e.getKeyCode(), this.selectedIndex);
    }

    @Override public void deselect(int selectedIndex) {
        this.checker.deselectPrevious(this.lastSelectedIndex);
        this.lastSelectedIndex = selectedIndex;
        this.left.requestFocus();
        this.selectedLarrComponent = this.larrComponents.get(this.selectedIndex);
    }

    @Override public void up(int currentIndex) {
        if (this.checker.up(currentIndex)){
            this.lastSelectedIndex = this.selectedIndex;
            this.selectedIndex = --currentIndex;
            this.selectedLarrComponent = this.larrComponents.get(this.selectedIndex);
        }
    }

    @Override public void down(int currentIndex) {
        if (this.checker.down(currentIndex)){
            this.lastSelectedIndex = this.selectedIndex;
            this.selectedIndex = ++currentIndex;
            this.selectedLarrComponent = this.larrComponents.get(this.selectedIndex);
        }
    }

    @Override public void selectFirst(int lastIndex) {
        if (this.checker.selectFirst(lastIndex)){
            this.selectedLarrComponent = this.larrComponents.get(FIRST);
            selectedIndex = FIRST;
        }
    }


    /**
     * Class LarrComponent
     */

    public class LarrComponent extends BasePanel implements MouseListener{

        /**
         * The component identifier to remove when the mouse enters , exits , clicks on the component
         */
        private static final int CURRENT = 1;


        /**
         * Instance variables
         */
        private Dimension dimension;
        private JLabel label;
        private JLabel currentLabel;
        private JLabel selectedLabel;
        private JLabel emptyLabel;
        private ImageIcon selectedIcon;
        private boolean selected = false;
        private boolean changed = true;
        private int arcx;
        private int arcy;
        private JComponent rightPanel;
        private JPanel receivePanel;
        private JPanel panel;

        /**
         * <code>LarrComponent</code> id
         */
        private int index;

        /**
         * <code>LarrComponent</code> title
         */
        private String text;

        /**
         * A style to apply to the component
         * @see LarrStyle
         */
        private LarrStyle style;

        /**
         * Border current color
         */
        private Color currentBorderColor;

        /**
         * Create a new <code>Larrcomponent</code> with id, title and style
         * @param index <code>LarrComponent</code> id
         * @param text <code>LarrComponent</code> title
         * @param style <code>LarrComponent</code> style
         */
        public LarrComponent(int index, String text, LarrStyle style) {
            this.index = index;
            this.text = text;
            this.style = style;
            this.arcx = this.style.getArcx();
            this.arcy = this.style.getArcy();
            this.selectedIcon = this.style.getImage();
            this.setOpaque(false);
            this.dimension = new Dimension((new JLabel(text)).getPreferredSize().width + 60,
                                           (new JLabel(text)).getPreferredSize().height + 10);
            this.initComponents();
        }

        @Override protected void paintBorder(Graphics g) {
            super.paintBorder(g);
            Graphics2D g2d= (Graphics2D) g;
            g2d.setColor(currentBorderColor);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, this.arcx, this.arcy);
        }


        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(this.getBackground());
            RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(renderingHints);
            g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.arcx, this.arcy);
            super.paintComponent(g);
        }

        /**
         * Initialize components
         */
        private void initComponents() {
            this.label = new JLabel(this.text);
            this.panel = new JPanel();
            this.panel.setOpaque(false);
            this.emptyLabel = new JLabel();
            this.currentLabel = this.emptyLabel;
            this.selectedLabel = new JLabel(this.selectedIcon);
            this.apply(LarrStyle.EXITED);
            this.setLayout(null);
            this.addElements();
            this.addMouseListener(this);
            this.rightPanel = new JPanel();
        }

        /**
         *
         */
        private void addElements(){
            this.setPreferredSize(this.dimension);
            this.panel.setPreferredSize(this.label.getPreferredSize());
            this.panel.add(this.label);
            this.panel.setBounds(10, 0, this.panel.getPreferredSize().width, this.panel.getPreferredSize().height + 10);
            this.currentLabel.setBounds(this.getWidth() - 18, (this.getHeight() / 2) - (this.selectedIcon.getIconHeight() / 2),
                                        this.selectedIcon.getIconWidth(), this.selectedIcon.getIconHeight());
            this.add(this.panel);
            this.add(this.currentLabel);
        }

        /**
         * Change icon used for <code>LarrComponent</code>
         * icon is empty if <code>LarrComponent</code> is not selected
         */
        private void changeIcon() {
            this.remove(CURRENT);
            this.currentLabel = this.changed ? this.emptyLabel : this.selectedLabel;
            this.currentLabel.setBounds(this.getWidth() - 18,
                                        (this.getHeight() / 2) - (this.selectedIcon.getIconHeight() / 2 + 1),
                                        this.selectedIcon.getIconWidth(), this.selectedIcon.getIconHeight());
            this.add(this.currentLabel);
        }

        /**
         * Apply <code>LarrStyle</code> to <code>LarrComponent</code>
         * @param type equals (entered, exited, selected)
         */
        private void apply(int type){
            this.setBackground(this.style.getColor("Background", type));
            this.label.setForeground(this.style.getColor("Foreground", type));
            this.currentBorderColor = this.style.getColor("Border", type);
        }

        /**
         * Resize <code>LarrComponent</code>
         * @param dimension resize dimension
         */
        public void resizeLarrComponent(final Dimension dimension){
            this.dimension = dimension;
            this.removeAll();
            this.addElements();
            this.revalidate();
            this.repaint();
        }

        /**
         * hover action if mouse entered
         */
        public void hover() {
            if (changed) {
                this.apply(LarrStyle.ENTERED);
                this.selected = false;
                this.changeIcon();
            }
            this.repaint();
        }

        /**
         * Select <code>LarrComponent</code>
         */
        public void selected() {
            if (!selected){
                this.selected = true;
                this.changed = false;
                this.addTo(this.receivePanel, this.rightPanel);
                this.changeIcon();
                this.apply(LarrStyle.SELECTED);
                setData(this.index);
            }
            this.repaint();
        }

        /**
         * Reset <code>LarrComponent</code>
         */
        public void reset() {
            if (changed) {
                this.apply(LarrStyle.EXITED);
                this.selected = false;
            }
            this.changeIcon();
            this.repaint();
        }

        /**
         * Add component to right panel
         * @param receive
         * @param component
         */
        private void addTo(final JPanel receive, final JComponent component){
            receive.removeAll();
            if (receive.getLayout() instanceof BorderLayout){
                receive.add(component, BorderLayout.CENTER);
            }
            else{
                receive.add(component);
            }
            receive.revalidate();
            receive.repaint();
        }

        public void init(){
            this.setChanged(true);
            this.reset();
        }


        /*
            Getter and setter
         */

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isChanged() {
            return this.changed;
        }

        public String getTitle() {
            return this.text;
        }

        public void setTitle(String text) {
            this.text = text;
        }

        public void setChanged(boolean changed) {
            this.changed = changed;
        }

        public JComponent getRight() {
            return this.rightPanel;
        }

        public void setRight(JComponent rightPanel) {
            this.rightPanel = rightPanel;
        }

        public void setReceive(JPanel receivePanel) {
            this.receivePanel = receivePanel;
        }

        public int getWidth() {
            return this.dimension.width;
        }

        public int getHeight() {
            return this.dimension.height;
        }

        public LarrStyle getStyle(){
            return this.style;
        }

        /*
            Mouse Listener implementation methods
         */
        public void mouseClicked(MouseEvent e) {
            this.selected();
        }
        public void mouseEntered(MouseEvent e) {
            this.hover();
        }
        public void mouseExited(MouseEvent e) {
            this.reset();
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }


    /**
     * Class LarrComponentChecker
     */

    public class LarrComponentChecker implements Checker, KeyAction {

        /**
         * Instance Variable
         */
        private List<LarrComponent> larrComponents;

        /**
         *
         * @param larrComponents
         *
         */
        public LarrComponentChecker(List<LarrComponent> larrComponents) {
            this.larrComponents = larrComponents;
        }

        @Override public void deselectPrevious(int lastSelectedIndex) {
            if (lastSelectedIndex != -1){
                this.larrComponents.get(lastSelectedIndex).init();
            }

        }

        @Override public boolean up(int index) {
            if (index != - 1 && index > 0) {
                this.larrComponents.get(index).init();
                larrComponents.get(--index).selected();
                return true;
            }
            return false;
        }

        @Override public boolean down(int index) {
            if (index != - 1 && index < larrComponents.size() - 1) {
                this.larrComponents.get(index).init();
                this.larrComponents.get(++index).selected();
                return true;
            }
            return false;
        }

        @Override public boolean selectFirst(int lastIndex) {
            boolean emptySelection = (lastIndex == - 1) && this.larrComponents.size() >= 1;
            if (emptySelection){
                this.larrComponents.get(FIRST).selected();
            }
            return emptySelection;
        }
    }

    public class LarrWorker extends SwingWorker<String, String>{

        @Override protected String doInBackground() throws Exception {
            return null;
        }
    }

}
