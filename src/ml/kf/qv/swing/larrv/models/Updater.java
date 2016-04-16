package ml.kf.qv.swing.larrv.models;

import ml.kf.qv.swing.larrv.view.LarrView;

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
