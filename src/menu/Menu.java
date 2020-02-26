package menu;

import animation.Animation;

public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);
    T getStatus();
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * this method resets this menu.
     */
    void reset();
}