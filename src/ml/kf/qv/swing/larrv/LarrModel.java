package ml.kf.qv.swing.larrv;

/**
 * @author Kanfa.
 *
 * Class LarrModel
 */
public class LarrModel extends AbstractLarrModel {


    /**
     * Create a new <code>LarrModel</code> with updater
     * @param updater
     */
    public LarrModel(Updater updater) {
        super(updater);
    }


    @Override public void deselect(int index) {
        if (this.reference != null){
            this.reference.get().deselect(index);
        }
    }

    @Override public void goUp(int currentIndex) {
        if (this.reference != null){
            this.reference.get().up(currentIndex);
        }
    }

    @Override public void goDown(int currentIndex) {
        if (this.reference != null){
            this.reference.get().down(currentIndex);
        }
    }

    @Override public void doSelectFirst(int lastIndex) {
        if (this.reference != null){
            this.reference.get().selectFirst(lastIndex);
        }
    }

}
