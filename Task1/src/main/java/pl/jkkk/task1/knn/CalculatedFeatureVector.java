package pl.jkkk.task1.knn;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.jkkk.task1.featureextraction.FeatureVector;

public class CalculatedFeatureVector implements Comparable<CalculatedFeatureVector> {

    /*------------------------ FIELDS REGION ------------------------*/
    private FeatureVector featureVector;
    private Double distance;

    /*------------------------ METHODS REGION ------------------------*/
    public CalculatedFeatureVector(FeatureVector featureVector, Double distance) {
        this.featureVector = featureVector;
        this.distance = distance;
    }

    public FeatureVector getFeatureVector() {
        return featureVector;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(CalculatedFeatureVector o) {
        if (this.getDistance().equals(o.getDistance())) {
            return 0;
        } else if (this.getDistance() > o.getDistance()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CalculatedFeatureVector that = (CalculatedFeatureVector) o;

        return new EqualsBuilder()
                .append(featureVector, that.featureVector)
                .append(distance, that.distance)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(featureVector)
                .append(distance)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("featureVector", featureVector)
                .append("distance", distance)
                .toString();
    }
}
    