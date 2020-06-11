package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultisubjectLinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final Label<T> qualifier1;
    private final Label<T> qualifier2;
    private final Label<T>[] summarizers;
    private final FuzzySet<T> fuzzySetOfCompoundSummarizer;
    private final List<T> objectsP1;
    private final List<T> objectsP2;
    private final String attributeValue1;
    private final String attributeValue2;

    private MultisubjectLinguisticSummary(LinguisticQuantifier quantifier,
                                          Label<T> qualifier1, Label<T> qualifier2, List<T> objects,
                                          Function<T, String> attributeExtractor,
                                          String attributeValue1, String attributeValue2,
                                          Label<T>... summarizers) {
        this.quantifier = quantifier;
        this.qualifier1 = qualifier1;
        this.qualifier2 = qualifier2;
        this.summarizers = summarizers;
        FuzzySet<T> tmpFuzzySet = summarizers[0].getFuzzySet();
        for (int i = 1; i < summarizers.length; i++) {
            tmpFuzzySet = tmpFuzzySet.and(summarizers[i].getFuzzySet());
        }
        this.fuzzySetOfCompoundSummarizer = tmpFuzzySet;
        this.objectsP1 = objects
                .stream()
                .filter(value -> attributeExtractor.apply(value).equals(attributeValue1))
                .collect(Collectors.toList());
        this.objectsP2 = objects
                .stream()
                .filter(value -> attributeExtractor.apply(value).equals(attributeValue2))
                .collect(Collectors.toList());
        this.attributeValue1 = attributeValue1;
        this.attributeValue2 = attributeValue2;
    }

    public MultisubjectLinguisticSummary(LinguisticQuantifier quantifier,
                                         List<T> objects, Function<T, String> attributeExtractor,
                                         String attributeValue1, String attributeValue2,
                                         Label<T>... summarizers) {
        this(quantifier, new AlwaysMatch<>(), new AlwaysMatch<>(),
                objects, attributeExtractor, attributeValue1, attributeValue2, summarizers);
    }

    public MultisubjectLinguisticSummary(LinguisticQuantifier quantifier,
                                         List<T> objects, Function<T, String> attributeExtractor,
                                         Label<T> qualifier1, String attributeValue1,
                                         String attributeValue2,
                                         Label<T>... summarizers) {
        this(quantifier, qualifier1, new AlwaysMatch<>(),
                objects, attributeExtractor, attributeValue1, attributeValue2, summarizers);
    }

    public MultisubjectLinguisticSummary(LinguisticQuantifier quantifier,
                                         List<T> objects, Function<T, String> attributeExtractor,
                                         String attributeValue1, Label<T> qualifier2,
                                         String attributeValue2,
                                         Label<T>... summarizers) {
        this(quantifier, new AlwaysMatch<>(), qualifier2,
                objects, attributeExtractor, attributeValue1, attributeValue2, summarizers);
    }

    public double degreeOfTruth() {
        final double p1tmp = 1.0 / objectsP1.size()
                * fuzzySetOfCompoundSummarizer
                .and(qualifier1.getFuzzySet()).cardinality(objectsP1);
        final double p2tmp = 1.0 / objectsP2.size()
                * fuzzySetOfCompoundSummarizer
                .and(qualifier2.getFuzzySet()).cardinality(objectsP2);

        return quantifier.compatibilityLevel(p1tmp / (p1tmp + p2tmp));
    }

    @Override
    public String toString() {
        final StringBuilder compoundSummarizerName = new StringBuilder(summarizers[0].getName());
        for (int i = 1; i < summarizers.length; i++) {
            compoundSummarizerName.append(" and have ").append(summarizers[i].getName());
        }

        final StringBuilder text = new StringBuilder();
        text.append(quantifier.getName());
        text.append(" ");
        text.append(attributeValue1);
        text.append(" measurements");
        if (!(qualifier1 instanceof AlwaysMatch)) {
            text.append(", which have ");
            text.append(qualifier1.getName());
            text.append(",");
        }
        text.append(" compared to ");
        text.append(attributeValue2);
        text.append(" measurements");
        if (!(qualifier2 instanceof AlwaysMatch)) {
            text.append(", which have ");
            text.append(qualifier2.getName());
            text.append(",");
        }
        text.append(" have ");
        text.append(compoundSummarizerName);
        return text.toString();
    }

    /**
     * This is a special class representing label, which everything always match to.
     * It is used in place of qualifier, where this is first form of linguistic summary.
     */
    private static class AlwaysMatch<T> extends Label<T> {
        public AlwaysMatch() {
            super(null, x -> 1, null);
        }
    }
}
