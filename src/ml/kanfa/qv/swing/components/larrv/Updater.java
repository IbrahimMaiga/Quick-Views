package ml.kanfa.qv.swing.components.larrv;

/**
 * Interface Updater
 *
 * @see LarrView.LarrComponent
 *
 * @author Ibrahim Maïga.
 */
public interface Updater {
    void deselect(int selectedIndex);
    void up(int currentIndex);
    void down(int currentIndex);
    void selectFirst(int lastIndex);
}
