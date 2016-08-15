package ml.kf.qv.swing.larrv.component;

import javax.swing.*;

/**
 * @author Kanfa.
 *
 * Class LarrElement
 */
public class LarrElement {


    private JComponent component;
    private String title;


    /**
     * Create a new <code>LarrELement</code> with panel title and right component used for <code>LarrComponent</code>
     * in <LarrView></LarrView>
     * @see LarrView
     * @see ml.kf.qv.swing.larrv.component.LarrView.LarrComponent
     * @param title
     * @param component
     */
    public LarrElement(String title, JComponent component){
        this.title = title;
        this.component = component;
    }


    /*
     * Getter and setter
     */

    public JComponent getComponent(){
        return this.component;
    }

    public void setComponent(JComponent component){
        this.component = component;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
