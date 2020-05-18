package pl.jkkk.task2.view.model;

import javafx.scene.control.ComboBox;

public class CustomComboBox extends ComboBox {

    /*------------------------ FIELDS REGION ------------------------*/
    private int counter = 0;

    /*------------------------ METHODS REGION ------------------------*/
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
    