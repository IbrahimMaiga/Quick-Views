package ml.kf.qv.swing.larrv.models;

/**
 * @author Kanfa.
 *
 * Interface KeyAction
 */
public interface KeyAction {
    boolean up(int currentIndex);
    boolean down(int currentIndex);
    boolean selectFirst(int lastSelectedIndex);
}
