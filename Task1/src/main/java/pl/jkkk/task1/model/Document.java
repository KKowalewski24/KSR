package pl.jkkk.task1.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.UUID;

public class Document {

    /*------------------------ FIELDS REGION ------------------------*/
    private final UUID uuid = UUID.randomUUID();
    private List<String> topicList;
    private List<String> placeList;
    private String title;
    private List<String> wordList;

    /*------------------------ METHODS REGION ------------------------*/
    public Document(List<String> topicList, List<String> placeList, String title,
                    List<String> wordList) {
        this.topicList = topicList;
        this.placeList = placeList;
        this.title = title;
        this.wordList = wordList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }

    public List<String> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<String> placeList) {
        this.placeList = placeList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
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
                .append(topicList, document.topicList)
                .append(placeList, document.placeList)
                .append(title, document.title)
                .append(wordList, document.wordList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(topicList)
                .append(placeList)
                .append(title)
                .append(wordList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("topicList", topicList)
                .append("placeList", placeList)
                .append("title", title)
                .append("wordList", wordList)
                .toString();
    }
}
    