package pl.jkkk.task2.logic.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "POLLUTION")
public class Pollution extends BaseEntity {

    /*------------------------ FIELDS REGION ------------------------*/
    private String address;
    private String state;
    private String county;
    private String city;
    private LocalDate dateLocal;

    private Double NO2Mean;
    private Double NO21stMaxValue;
    private Double NO21stMaxHour;
    private Double NO2AQI;

    private Double O3Mean;
    private Double O31stMaxValue;
    private Double O31stMaxHour;
    private Double O3AQI;

    private Double SO2Mean;
    private Double SO21stMaxValue;
    private Double SO21stMaxHour;
    private Double SO2AQI;

    private Double COMean;
    private Double CO1stMaxValue;
    private Double CO1stMaxHour;
    private Double COAQI;

    /*------------------------ METHODS REGION ------------------------*/
    public Pollution() {
    }

    public Pollution(String address, String state, String county, String city,
                     LocalDate dateLocal, Double NO2Mean, Double NO21stMaxValue,
                     Double NO21stMaxHour, Double NO2AQI, Double O3Mean, Double O31stMaxValue,
                     Double O31stMaxHour, Double O3AQI, Double SO2Mean, Double SO21stMaxValue,
                     Double SO21stMaxHour, Double SO2AQI, Double COMean, Double CO1stMaxValue,
                     Double CO1stMaxHour, Double COAQI) {
        this.address = address;
        this.state = state;
        this.county = county;
        this.city = city;
        this.dateLocal = dateLocal;
        this.NO2Mean = NO2Mean;
        this.NO21stMaxValue = NO21stMaxValue;
        this.NO21stMaxHour = NO21stMaxHour;
        this.NO2AQI = NO2AQI;
        this.O3Mean = O3Mean;
        this.O31stMaxValue = O31stMaxValue;
        this.O31stMaxHour = O31stMaxHour;
        this.O3AQI = O3AQI;
        this.SO2Mean = SO2Mean;
        this.SO21stMaxValue = SO21stMaxValue;
        this.SO21stMaxHour = SO21stMaxHour;
        this.SO2AQI = SO2AQI;
        this.COMean = COMean;
        this.CO1stMaxValue = CO1stMaxValue;
        this.CO1stMaxHour = CO1stMaxHour;
        this.COAQI = COAQI;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateLocal() {
        return dateLocal;
    }

    public void setDateLocal(LocalDate dateLocal) {
        this.dateLocal = dateLocal;
    }

    public Double getNO2Mean() {
        return NO2Mean;
    }

    public void setNO2Mean(Double NO2Mean) {
        this.NO2Mean = NO2Mean;
    }

    public Double getNO21stMaxValue() {
        return NO21stMaxValue;
    }

    public void setNO21stMaxValue(Double NO21stMaxValue) {
        this.NO21stMaxValue = NO21stMaxValue;
    }

    public Double getNO21stMaxHour() {
        return NO21stMaxHour;
    }

    public void setNO21stMaxHour(Double NO21stMaxHour) {
        this.NO21stMaxHour = NO21stMaxHour;
    }

    public Double getNO2AQI() {
        return NO2AQI;
    }

    public void setNO2AQI(Double NO2AQI) {
        this.NO2AQI = NO2AQI;
    }

    public Double getO3Mean() {
        return O3Mean;
    }

    public void setO3Mean(Double o3Mean) {
        O3Mean = o3Mean;
    }

    public Double getO31stMaxValue() {
        return O31stMaxValue;
    }

    public void setO31stMaxValue(Double o31stMaxValue) {
        O31stMaxValue = o31stMaxValue;
    }

    public Double getO31stMaxHour() {
        return O31stMaxHour;
    }

    public void setO31stMaxHour(Double o31stMaxHour) {
        O31stMaxHour = o31stMaxHour;
    }

    public Double getO3AQI() {
        return O3AQI;
    }

    public void setO3AQI(Double o3AQI) {
        O3AQI = o3AQI;
    }

    public Double getSO2Mean() {
        return SO2Mean;
    }

    public void setSO2Mean(Double SO2Mean) {
        this.SO2Mean = SO2Mean;
    }

    public Double getSO21stMaxValue() {
        return SO21stMaxValue;
    }

    public void setSO21stMaxValue(Double SO21stMaxValue) {
        this.SO21stMaxValue = SO21stMaxValue;
    }

    public Double getSO21stMaxHour() {
        return SO21stMaxHour;
    }

    public void setSO21stMaxHour(Double SO21stMaxHour) {
        this.SO21stMaxHour = SO21stMaxHour;
    }

    public Double getSO2AQI() {
        return SO2AQI;
    }

    public void setSO2AQI(Double SO2AQI) {
        this.SO2AQI = SO2AQI;
    }

    public Double getCOMean() {
        return COMean;
    }

    public void setCOMean(Double COMean) {
        this.COMean = COMean;
    }

    public Double getCO1stMaxValue() {
        return CO1stMaxValue;
    }

    public void setCO1stMaxValue(Double CO1stMaxValue) {
        this.CO1stMaxValue = CO1stMaxValue;
    }

    public Double getCO1stMaxHour() {
        return CO1stMaxHour;
    }

    public void setCO1stMaxHour(Double CO1stMaxHour) {
        this.CO1stMaxHour = CO1stMaxHour;
    }

    public Double getCOAQI() {
        return COAQI;
    }

    public void setCOAQI(Double COAQI) {
        this.COAQI = COAQI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pollution pollution = (Pollution) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(address, pollution.address)
                .append(state, pollution.state)
                .append(county, pollution.county)
                .append(city, pollution.city)
                .append(dateLocal, pollution.dateLocal)
                .append(NO2Mean, pollution.NO2Mean)
                .append(NO21stMaxValue, pollution.NO21stMaxValue)
                .append(NO21stMaxHour, pollution.NO21stMaxHour)
                .append(NO2AQI, pollution.NO2AQI)
                .append(O3Mean, pollution.O3Mean)
                .append(O31stMaxValue, pollution.O31stMaxValue)
                .append(O31stMaxHour, pollution.O31stMaxHour)
                .append(O3AQI, pollution.O3AQI)
                .append(SO2Mean, pollution.SO2Mean)
                .append(SO21stMaxValue, pollution.SO21stMaxValue)
                .append(SO21stMaxHour, pollution.SO21stMaxHour)
                .append(SO2AQI, pollution.SO2AQI)
                .append(COMean, pollution.COMean)
                .append(CO1stMaxValue, pollution.CO1stMaxValue)
                .append(CO1stMaxHour, pollution.CO1stMaxHour)
                .append(COAQI, pollution.COAQI)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(address)
                .append(state)
                .append(county)
                .append(city)
                .append(dateLocal)
                .append(NO2Mean)
                .append(NO21stMaxValue)
                .append(NO21stMaxHour)
                .append(NO2AQI)
                .append(O3Mean)
                .append(O31stMaxValue)
                .append(O31stMaxHour)
                .append(O3AQI)
                .append(SO2Mean)
                .append(SO21stMaxValue)
                .append(SO21stMaxHour)
                .append(SO2AQI)
                .append(COMean)
                .append(CO1stMaxValue)
                .append(CO1stMaxHour)
                .append(COAQI)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("address", address)
                .append("state", state)
                .append("county", county)
                .append("city", city)
                .append("dateLocal", dateLocal)
                .append("NO2Mean", NO2Mean)
                .append("NO21stMaxValue", NO21stMaxValue)
                .append("NO21stMaxHour", NO21stMaxHour)
                .append("NO2AQI", NO2AQI)
                .append("O3Mean", O3Mean)
                .append("O31stMaxValue", O31stMaxValue)
                .append("O31stMaxHour", O31stMaxHour)
                .append("O3AQI", O3AQI)
                .append("SO2Mean", SO2Mean)
                .append("SO21stMaxValue", SO21stMaxValue)
                .append("SO21stMaxHour", SO21stMaxHour)
                .append("SO2AQI", SO2AQI)
                .append("COMean", COMean)
                .append("CO1stMaxValue", CO1stMaxValue)
                .append("CO1stMaxHour", CO1stMaxHour)
                .append("COAQI", COAQI)
                .toString();
    }
}
    