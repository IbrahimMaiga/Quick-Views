package ml.kf.qv.swing.larrv;

import ml.kf.qv.swing.larrv.component.LarrView;

/**
 * @author Kanfa.
 *
 * Interface Updater
 *
 * @see LarrView.LarrComponent
 */

public interface Updater {
    void deselect(int selectedIndex);
    void up(int currentIndex);
    void down(int currentIndex);
    void selectFirst(int lastIndex);
}
