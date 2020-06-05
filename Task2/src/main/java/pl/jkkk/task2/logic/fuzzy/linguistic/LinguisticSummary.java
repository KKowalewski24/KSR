package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.TrapezoidalFuzzySet;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;

import java.util.Arrays;
import java.util.List;

public class LinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final Label<T> qualifier;
    private final Label<T>[] summarizers;
    private final FuzzySet<T> fuzzySetOfCompoundSummarizer;
    private final List<T> objects;

    public LinguisticSummary(LinguisticQuantifier quantifier, Label<T> qualifier, List<T> objects,
                             Label<T>... summarizers) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizers = summarizers;
        FuzzySet<T> tmpFuzzySet = summarizers[0].getFuzzySet();
        for (int i = 1; i < summarizers.length; i++) {
            tmpFuzzySet = tmpFuzzySet.and(summarizers[i].getFuzzySet());
        }
        this.fuzzySetOfCompoundSummarizer = tmpFuzzySet;
        this.objects = objects;
    }

    public LinguisticSummary(LinguisticQuantifier quantifier, List<T> objects,
                             Label<T>... summarizers) {
        this(quantifier, new AlwaysMatch<T>(), objects, summarizers);
    }

    /* T1 */
    public double degreeOfTruth() {
        /* If quantifier is absolute, it has to be the first form of linguistic summary */
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            return quantifier.compatibilityLevel(fuzzySetOfCompoundSummarizer.cardinality(objects));
        }
        /* If quantifier is relative, calculate as for the first, or the second form */
        else {
            return quantifier
                    .compatibilityLevel(fuzzySetOfCompoundSummarizer
                            .and(qualifier.getFuzzySet())
                            .cardinality(objects) / qualifier
                            .getFuzzySet().cardinality(objects));
        }
    }

    /* T2 */
    public double degreeOfImprecision() {
        return 1.0 - Arrays.stream(summarizers)
                .map(Label::getFuzzySet)
                .mapToDouble(fuzzySet -> fuzzySet.degreeOfFuzziness(objects))
                .reduce(1.0, (a, b) -> a * b);
    }

    /* T3 */
    public double degreeOfCovering() {
        return fuzzySetOfCompoundSummarizer.and(qualifier.getFuzzySet())
                .support(objects)
                .size() / (float) qualifier
                .getFuzzySet().support(objects).size();
    }

    /* T4 */
    public double degreeOfAppropriateness() {
        return Math.abs(Arrays.stream(summarizers)
                .mapToDouble(summarizer -> summarizer.getFuzzySet()
                        .support(objects)
                        .size() / (double) objects.size())
                .reduce(1.0, (a, b) -> a * b) - degreeOfCovering());
    }

    /* T5 */
    public double lengthOfSummary() {
        return 2.0 * Math.pow(0.5, summarizers.length);
    }

    /* T6 */
    public double degreeOfQuantifierImprecision() {
        if (quantifier.getFuzzySet() instanceof TrapezoidalFuzzySet) {
            TrapezoidalFuzzySet<Double> quantifierFuzzySet =
                    (TrapezoidalFuzzySet<Double>) quantifier.getFuzzySet();
            double continuousSupportLength = quantifierFuzzySet.getD() - quantifierFuzzySet.getA();
            if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
                return 1.0 - continuousSupportLength / objects.size();
            } else {
                return 1.0 - continuousSupportLength;
            }
        }
        return 0.0;
    }

    /* T7 */
    public double degreeOfQuantifierCardinality() {
        if (quantifier.getFuzzySet() instanceof TrapezoidalFuzzySet) {
            TrapezoidalFuzzySet<Double> quantifierFuzzySet =
                    (TrapezoidalFuzzySet<Double>) quantifier.getFuzzySet();
            final double a = quantifierFuzzySet.getA(), b = quantifierFuzzySet.getB(), c =
                    quantifierFuzzySet.getC(),
                    d = quantifierFuzzySet.getD();
            double measure = (b - a) * 0.5 + (c - b) + (d - c) * 0.5;
            if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
                return 1.0 - measure / objects.size();
            } else {
                return 1.0 - measure;
            }
        }
        return 0.0;
    }

    /* T8 */
    public double degreeOfSummarizerCardinality() {
        return 1.0 - Arrays.stream(summarizers)
                .mapToDouble(summarizer -> summarizer.getFuzzySet()
                        .cardinality(objects) / objects.size())
                .reduce(1.0, (a, b) -> a * b);
    }

    /* T9 */
    public double degreeOfQualifierImprecision() {
        return 1.0 - qualifier.getFuzzySet().degreeOfFuzziness(objects);
    }

    /* T10 */
    public double degreeOfQualifierCardinality() {
        return 1.0 - qualifier.getFuzzySet().cardinality(objects) / objects.size();
    }

    /* T11 */
    public double lengthOfQualifier() {
        return 2.0 * Math.pow(0.5, 1); //there is only one fuzzy set in qualifier for now
    }

    /* quality of summary for optimal summary selecting */
    public double quality(double w1, double w2, double w3, double w4, double w5, double w6,
                          double w7, double w8, double w9, double w10, double w11) {
        return degreeOfTruth() * w1
                + degreeOfImprecision() * w2
                + degreeOfCovering() * w3
                + degreeOfAppropriateness() * w4
                + lengthOfSummary() * w5
                + degreeOfQuantifierImprecision() * w6
                + degreeOfQuantifierCardinality() * w7
                + degreeOfSummarizerCardinality() * w8
                + degreeOfQualifierImprecision() * w9
                + degreeOfQualifierCardinality() * w10
                + lengthOfQualifier() * w11;
    }

    @Override
    public String toString() {
        final StringBuilder compoundSummarizerName = new StringBuilder(summarizers[0].getName());
        for (int i = 1; i < summarizers.length; i++) {
            compoundSummarizerName.append(" and have ").append(summarizers[i].getName());
        }
        if (qualifier instanceof AlwaysMatch) {
            return quantifier.getName() + " measurements have " + compoundSummarizerName;
        } else {
            return quantifier.getName() + " measurements, which have " + qualifier.getName() + ","
                    + " have "
                    + compoundSummarizerName;
        }
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
