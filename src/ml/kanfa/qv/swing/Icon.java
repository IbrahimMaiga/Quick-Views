package ml.kanfa.qv.swing;

import ml.kanfa.qv.swing.components.larrv.LarrView;

import javax.swing.*;

/**
 * @author Ibrahim Maïga.
 *
 * Class Icon
 */
public class Icon {

    /**
     * Directory path
     */
    private static final String DIRECTORY_PATH = "/ml/kanfa/icon/";
    private static final String DEFAULT_ICON_PATH = DIRECTORY_PATH + "larrvselected.png";

    /**
     * Icon used if <code>LarrComponent</code> is selected
     * @see LarrView.LarrComponent
     */
    private ImageIcon icon;

    /**
     * Create an instance of <code>Icon</code> with image name
     * @param name icon name
     */

    public Icon(String name){
        this.icon = new ImageIcon(this.getClass().getResource(DIRECTORY_PATH + name));
    }

    public Icon(boolean setDefault){
        this.icon = setDefault ? new ImageIcon(this.getClass().getResource(DEFAULT_ICON_PATH)) : null;
    }
    public Icon(){
        this(true);
    }

    public ImageIcon getIcon(){
        return this.icon;
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }
}
