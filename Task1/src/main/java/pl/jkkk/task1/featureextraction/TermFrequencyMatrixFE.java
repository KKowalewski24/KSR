package pl.jkkk.task1.featureextraction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pl.jkkk.task1.model.Document;

public class TermFrequencyMatrixFE implements FeatureExtractor {
    
    private final Set<String> keywords;

    public TermFrequencyMatrixFE(final Set<String> keywords){
        this.keywords = keywords;
    }

    @Override
    public FeatureVector extract(Document document){
        /* initialize keyword frequency */
        Map<String, Double> keywordFrequency = new HashMap<>();
        keywords.forEach(keyword -> keywordFrequency.put(keyword, 0.0));

        /* fill keyword frequency */
        document.getWordList().forEach(word -> {
            if(keywordFrequency.containsKey(word))
                keywordFrequency.put(word, keywordFrequency.get(word) + 1.0);
        });
        
        /* create and return term frequency matrix */
        return new FeatureVector(keywordFrequency.values());
    }
}
