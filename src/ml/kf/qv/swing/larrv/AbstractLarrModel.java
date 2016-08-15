package ml.kf.qv.swing.larrv;

import java.lang.ref.WeakReference;

/**
 *
 * @author Kanfa
 *
 * Class AbstractLarrModel
 */
public abstract class AbstractLarrModel{

    /**
     * Weak reference toward <code>Updater</code>
     */
    protected WeakReference<Updater> reference;

    /**
     * Create a new constructor with <code>Updater</code>
     * @param updater
     */
    public AbstractLarrModel(Updater updater){
        this.reference = new WeakReference<>(updater);
    }

    /**
     * Abstract method select
     * @param index
     */
    public abstract void deselect(int index);

    /**
     * Abstract method goUp
     * @param currentIndex
     */
    public abstract void goUp(int currentIndex);

    /**
     * Abstract method goDown
     * @param currentIndex
     */
    public abstract void goDown(int currentIndex);

    /**
     * Abstract method doSelectFirst
     */
    public abstract void doSelectFirst(int lastIndex);

}
