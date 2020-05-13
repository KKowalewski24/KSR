package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;

public class Label extends LinguisticValue {

    public Label(String name, FuzzySet fuzzySet) {
        super(name, fuzzySet);
    }

    public Label and(Label label) {
        return new Label(this.getName() + " i " + label.getName(),
                getFuzzySet().intersection(label.getFuzzySet()));
    }

    public Label or(Label label) {
        return new Label(this.getName() + " lub " + label.getName(),
                getFuzzySet().union(label.getFuzzySet()));
    }

    public Label not() {
        return new Label("nie" + this.getName(), getFuzzySet().complement());
    }
}
