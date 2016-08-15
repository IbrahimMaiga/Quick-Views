package ml.kf.qv.swing;

import ml.kf.qv.swing.larrv.component.LarrView;

import javax.swing.*;

/**
 * @author Kanfa.
 *
 * Class Icon
 */
public class Icon {

    /**
     * Icon used if <code>LarrComponent</code> is selected
     *
     *@see LarrView.LarrComponent
     */

    private ImageIcon icon;

    /**
     * Create an instance of <code>Icon</code> with image name
     * @param name
     */

    public Icon(String name){
        this.icon = new ImageIcon(this.getClass().getResource("/ml/kf/icon/" + name));
    }

    public Icon(boolean setDefault){
        this.icon = setDefault ? new ImageIcon(this.getClass().getResource("/ml/kf/icon/larrvselected.png")) : null;
    }
    public Icon(){
        this(true);
    }

    /**
     * Getter and setter
     */

    public ImageIcon getIcon(){
        return this.icon;
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }
}
