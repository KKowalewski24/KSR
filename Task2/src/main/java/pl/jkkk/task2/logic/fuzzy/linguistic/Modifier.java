package pl.jkkk.task2.logic.fuzzy.linguistic;

public enum Modifier {
    ABSOLUTELY("absolutely", 4.0),
    VERY("very", 2.0),
    MUCH_MORE("much more", 1.75),
    MORE("more", 1.5),
    PLUS("plus", 1.25),
    MINUS("minus", 0.75),
    MORE_OR_LESS("more or less", 0.5),
    SLIGHTLY("slightly", 0.25);

    private final String text;
    private final double r;

    Modifier(String text, double r) {
        this.text = text;
        this.r = r;
    }

    public String getText() {
        return text;
    }

    public double getR() {
        return r;
    }
}
