package pl.jkkk.task1;

import pl.jkkk.task1.featureextraction.DocumentLengthFE;
import pl.jkkk.task1.featureextraction.FeatureExtractor;
import pl.jkkk.task1.featureextraction.MostFrequentKeywordInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.MostFrequentWordInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.RelativeNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.UniqueNumberOfKeywordsInDocumentFragmentFE;

import java.util.Set;

/**
 * This class can transform string abbreviation
 * into real feature extractor object.
 * <p>
 * Format of abbreviation: X,A-B,Y, where:
 * X - number of feature from documentation (from 1 to 6)
 * A-B - optional range (in percents) of document (eg. 0-50)
 * Y - optional shortcut for related keywords set ('tfidf' or 'tfcitfoc')
 * <p>
 * Some features may not require all three parameters.
 */
public class FeatureExtractorAbbreviationResolver {

    final private Set<String> keywordsTfIdf;
    final private Set<String> keywordsTfcItfoc;

    public FeatureExtractorAbbreviationResolver(final Set<String> keywordsTfIdf,
                                                final Set<String> keywordsTfcItfoc) {
        this.keywordsTfIdf = keywordsTfIdf;
        this.keywordsTfcItfoc = keywordsTfcItfoc;
    }

    public FeatureExtractor resolveAbbreviation(final String abbr) {
        final String[] params = abbr.split(",");
        switch (Integer.parseInt(params[0])) {
            case 1:
                return new DocumentLengthFE();
            case 2:
                return new MostFrequentWordInDocumentFragmentFE(
                        Integer.parseInt(params[1].split("-")[0]),
                        Integer.parseInt(params[1].split("-")[1])
                );
            case 3:
                return new NumberOfKeywordsInDocumentFragmentFE(
                        params[2].equals("tfidf") ? keywordsTfIdf : keywordsTfcItfoc,
                        Integer.parseInt(params[1].split("-")[0]),
                        Integer.parseInt(params[1].split("-")[1])
                );
            case 4:
                return new RelativeNumberOfKeywordsInDocumentFragmentFE(
                        params[2].equals("tfidf") ? keywordsTfIdf : keywordsTfcItfoc,
                        Integer.parseInt(params[1].split("-")[0]),
                        Integer.parseInt(params[1].split("-")[1])
                );
            case 5:
                return new UniqueNumberOfKeywordsInDocumentFragmentFE(
                        params[2].equals("tfidf") ? keywordsTfIdf : keywordsTfcItfoc,
                        Integer.parseInt(params[1].split("-")[0]),
                        Integer.parseInt(params[1].split("-")[1])
                );
            case 6:
                return new MostFrequentKeywordInDocumentFragmentFE(
                        params[2].equals("tfidf") ? keywordsTfIdf : keywordsTfcItfoc,
                        Integer.parseInt(params[1].split("-")[0]),
                        Integer.parseInt(params[1].split("-")[1])
                );
        }
        return null;
    }
}
