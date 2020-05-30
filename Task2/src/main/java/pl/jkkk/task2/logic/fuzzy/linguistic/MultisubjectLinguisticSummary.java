package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;

public class MultisubjectLinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final Label<T>[] summarizers;
    private final FuzzySet<T> fuzzySetOfCompoundSummarizer;
    private final List<T> objectsP1;
    private final List<T> objectsP2;
    private final String attributeValue1;
    private final String attributeValue2;

    public MultisubjectLinguisticSummary(LinguisticQuantifier quantifier, List<T> objects,
            Function<T, String> attributeExtractor, String attributeValue1, String attributeValue2,
            Label<T>... summarizers) {
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        FuzzySet<T> tmpFuzzySet = summarizers[0].getFuzzySet();
        for (int i = 1; i < summarizers.length; i++) {
            tmpFuzzySet = tmpFuzzySet.and(summarizers[i].getFuzzySet());
        }
        this.fuzzySetOfCompoundSummarizer = tmpFuzzySet;
        this.objectsP1 = objects.stream().filter(value -> attributeExtractor.apply(value).equals(attributeValue1))
                .collect(Collectors.toList());
        this.objectsP2 = objects.stream().filter(value -> attributeExtractor.apply(value).equals(attributeValue2))
                .collect(Collectors.toList());
        this.attributeValue1 = attributeValue1;
        this.attributeValue2 = attributeValue2;
    }

    public double degreeOfTruth() {
        final double p1tmp = 1.0 / objectsP1.size() * fuzzySetOfCompoundSummarizer.cardinality(objectsP1);
        final double p2tmp = 1.0 / objectsP2.size() * fuzzySetOfCompoundSummarizer.cardinality(objectsP2);
        return quantifier.compatibilityLevel(p1tmp / (p1tmp + p2tmp));
    }

    @Override
    public String toString() {
        final StringBuilder compoundSummarizerName = new StringBuilder(summarizers[0].getName());
        for (int i = 1; i < summarizers.length; i++) {
            compoundSummarizerName.append(" and have ").append(summarizers[i].getName());
        }
        return quantifier.getName() + " " + attributeValue1 + " measurements compared to " + attributeValue2
                + " measurements have " + compoundSummarizerName;
    }
}
