package pl.jkkk.task1.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Document {

    /*------------------------ FIELDS REGION ------------------------*/
    private String title;
    private List<String> wordList;

    /*------------------------ METHODS REGION ------------------------*/
    public Document(String title, List<String> wordList) {
        this.title = title;
        this.wordList = wordList;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getWordList() {
        return wordList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Document document = (Document) o;

        return new EqualsBuilder()
                .append(title, document.title)
                .append(wordList, document.wordList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(wordList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("wordList", wordList)
                .toString();
    }
}
    