package pl.jkkk.task2.view.model;

public class CustomBoolean {

    /*------------------------ FIELDS REGION ------------------------*/
    private boolean state;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomBoolean(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
