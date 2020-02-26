package menu;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuAnimation<T> implements Menu<T> {
    private List<T> itemKeys;
    private List<String> itemMessages;
    private List<T> returnValues;
    private KeyboardSensor keyboardSensor;
    private T status;
    private String menuTitle;
    private Boolean stop;
    private List<Menu<T>> listOfSub;
    private List<Boolean> checkForSubMenu;
    private AnimationRunner ar;


    public MenuAnimation(String menuTitle,KeyboardSensor keyboardSensor,AnimationRunner animationRunner) {
        this.itemKeys =new ArrayList<>();
        this.itemMessages = new ArrayList<>();
        this.returnValues = new ArrayList<>();
        this.keyboardSensor=keyboardSensor;
        this.menuTitle=menuTitle;
        this.stop=false;
        this.listOfSub = new ArrayList<Menu<T>>();
        this.checkForSubMenu = new ArrayList<>();
        this.ar = animationRunner;

    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.itemKeys.add((T) key);
        this.itemMessages.add(message);
        this.returnValues.add(returnVal);
        this.checkForSubMenu.add(false);
    }

    @Override
    public T getStatus() {
       return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.itemKeys.add((T) key);
        this.itemMessages.add(message);
        this.listOfSub.add(subMenu);
        this.returnValues.add(null);
        this.checkForSubMenu.add(true);

    }

    public void setStop(Boolean b){
        this.stop=b;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(70, 95, 205));
        d.drawText(320, 300, this.menuTitle, 50);
        int j = 350;
        d.setColor(new Color(45, 149, 237));
        for (int i = 0; i < this.returnValues.size(); i++) {
            d.drawText(320, j, ("(" + this.itemKeys.get(i) + ")"
                    + " " + this.itemMessages.get(i)), 30);
            j += 40;
        }
        for (int i=0; i<this.itemKeys.size(); i++) {
            if (this.keyboardSensor.isPressed((String) this.itemKeys.get(i))) {
                //This is not the sub menu
                if (!checkForSubMenu.get(i)) {
                    this.status = this.returnValues.get(i);
                    this.stop = true;
                }
                else {
                    this.ar.run(this.listOfSub.get(i));
                    this.status = this.listOfSub.get(i).getStatus();
                    this.listOfSub.get(i).reset();
                    this.stop = true;
                    break;
                }
            }

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * this method resets this menu.
     */
    public void reset() {
        this.status = null;
        this.stop = false;
    }
}
