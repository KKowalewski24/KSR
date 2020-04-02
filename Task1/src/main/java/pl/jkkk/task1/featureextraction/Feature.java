package pl.jkkk.task1.featureextraction;

public class Feature {
    private String text;
    private Double number;

    public Feature(String text) {
        this.text = text;
        this.number = 0.0;
    }

    public Feature(Double number) {
        this.number = number;
        this.text = null;
    }

    public boolean isNumber() {
        return text == null;
    }

    public boolean isText() {
        return text != null;
    }

    public String getText() {
        return text;
    }

    public Double getNumber() {
        return number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
