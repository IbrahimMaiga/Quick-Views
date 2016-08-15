package ml.kf.qv.swing.larrv;

import ml.kf.qv.swing.larrv.AbstractLarrModel;

import java.awt.event.KeyEvent;

/**
 *
 * @author Kanfa.
 *
 * Class LarrController
 */
public class LarrController{

    /**
     * AbstractModel
     */
    private AbstractLarrModel larrModel;

    /**
     * Create a new constructor with Abstract model as param
     * @see AbstractLarrModel
     * @param larrModel
     */
    public LarrController(AbstractLarrModel larrModel){
        this.larrModel = larrModel;
    }

    /**
     *
     * @param index
     */
    public void control(int index) {
        if (index >= 0){
            this.larrModel.deselect(index);
        }
    }

    /**
     *
     * @param keyCode
     * @param currentIndex
     */
    public void control(int keyCode, int currentIndex) {
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
                this.larrModel.goDown(currentIndex);
                break;
            case KeyEvent.VK_UP:
                this.larrModel.goUp(currentIndex);
                break;
            case KeyEvent.VK_ENTER:
                this.larrModel.doSelectFirst(currentIndex);
                break;
            default:

        }
    }
}
