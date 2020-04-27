package pl.jkkk.task2.logic.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "POLLUTION")
public class Pollution {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    private Integer id;

    private Integer stateCode;
    private Integer countyCode;
    private Integer siteNum;
    private String address;
    private String state;
    private String county;
    private String city;
    private LocalDate dateLocal;

    private String NO2Units;
    private Double NO2Mean;
    private Double NO21stMaxValue;
    private Integer NO21stMaxHour;
    private Integer NO2AQI;

    private String O3Units;
    private Double O3Mean;
    private Double O31stMaxValue;
    private Integer O31stMaxHour;
    private Integer O3AQI;

    private String SO2Units;
    private Double SO2Mean;
    private Double SO21stMaxValue;
    private Integer SO21stMaxHour;
    private Integer SO2AQI;

    private String COUnits;
    private Double COMean;
    private Double CO1stMaxValue;
    private Integer CO1stMaxHour;
    private Integer COAQI;

    /*------------------------ METHODS REGION ------------------------*/
    public Pollution() {
    }

    public Pollution(Integer id, Integer stateCode, Integer countyCode, Integer siteNum,
                     String address, String state, String county, String city,
                     LocalDate dateLocal, String NO2Units, Double NO2Mean, Double NO21stMaxValue,
                     Integer NO21stMaxHour, Integer NO2AQI, String O3Units, Double O3Mean,
                     Double O31stMaxValue, Integer O31stMaxHour, Integer O3AQI, String SO2Units,
                     Double SO2Mean, Double SO21stMaxValue, Integer SO21stMaxHour, Integer SO2AQI,
                     String COUnits, Double COMean, Double CO1stMaxValue, Integer CO1stMaxHour,
                     Integer COAQI) {
        this.id = id;
        this.stateCode = stateCode;
        this.countyCode = countyCode;
        this.siteNum = siteNum;
        this.address = address;
        this.state = state;
        this.county = county;
        this.city = city;
        this.dateLocal = dateLocal;
        this.NO2Units = NO2Units;
        this.NO2Mean = NO2Mean;
        this.NO21stMaxValue = NO21stMaxValue;
        this.NO21stMaxHour = NO21stMaxHour;
        this.NO2AQI = NO2AQI;
        this.O3Units = O3Units;
        this.O3Mean = O3Mean;
        this.O31stMaxValue = O31stMaxValue;
        this.O31stMaxHour = O31stMaxHour;
        this.O3AQI = O3AQI;
        this.SO2Units = SO2Units;
        this.SO2Mean = SO2Mean;
        this.SO21stMaxValue = SO21stMaxValue;
        this.SO21stMaxHour = SO21stMaxHour;
        this.SO2AQI = SO2AQI;
        this.COUnits = COUnits;
        this.COMean = COMean;
        this.CO1stMaxValue = CO1stMaxValue;
        this.CO1stMaxHour = CO1stMaxHour;
        this.COAQI = COAQI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    public Integer getSiteNum() {
        return siteNum;
    }

    public void setSiteNum(Integer siteNum) {
        this.siteNum = siteNum;
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

    public String getNO2Units() {
        return NO2Units;
    }

    public void setNO2Units(String NO2Units) {
        this.NO2Units = NO2Units;
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

    public Integer getNO21stMaxHour() {
        return NO21stMaxHour;
    }

    public void setNO21stMaxHour(Integer NO21stMaxHour) {
        this.NO21stMaxHour = NO21stMaxHour;
    }

    public Integer getNO2AQI() {
        return NO2AQI;
    }

    public void setNO2AQI(Integer NO2AQI) {
        this.NO2AQI = NO2AQI;
    }

    public String getO3Units() {
        return O3Units;
    }

    public void setO3Units(String o3Units) {
        O3Units = o3Units;
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

    public Integer getO31stMaxHour() {
        return O31stMaxHour;
    }

    public void setO31stMaxHour(Integer o31stMaxHour) {
        O31stMaxHour = o31stMaxHour;
    }

    public Integer getO3AQI() {
        return O3AQI;
    }

    public void setO3AQI(Integer o3AQI) {
        O3AQI = o3AQI;
    }

    public String getSO2Units() {
        return SO2Units;
    }

    public void setSO2Units(String SO2Units) {
        this.SO2Units = SO2Units;
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

    public Integer getSO21stMaxHour() {
        return SO21stMaxHour;
    }

    public void setSO21stMaxHour(Integer SO21stMaxHour) {
        this.SO21stMaxHour = SO21stMaxHour;
    }

    public Integer getSO2AQI() {
        return SO2AQI;
    }

    public void setSO2AQI(Integer SO2AQI) {
        this.SO2AQI = SO2AQI;
    }

    public String getCOUnits() {
        return COUnits;
    }

    public void setCOUnits(String COUnits) {
        this.COUnits = COUnits;
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

    public Integer getCO1stMaxHour() {
        return CO1stMaxHour;
    }

    public void setCO1stMaxHour(Integer CO1stMaxHour) {
        this.CO1stMaxHour = CO1stMaxHour;
    }

    public Integer getCOAQI() {
        return COAQI;
    }

    public void setCOAQI(Integer COAQI) {
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
                .append(id, pollution.id)
                .append(stateCode, pollution.stateCode)
                .append(countyCode, pollution.countyCode)
                .append(siteNum, pollution.siteNum)
                .append(address, pollution.address)
                .append(state, pollution.state)
                .append(county, pollution.county)
                .append(city, pollution.city)
                .append(dateLocal, pollution.dateLocal)
                .append(NO2Units, pollution.NO2Units)
                .append(NO2Mean, pollution.NO2Mean)
                .append(NO21stMaxValue, pollution.NO21stMaxValue)
                .append(NO21stMaxHour, pollution.NO21stMaxHour)
                .append(NO2AQI, pollution.NO2AQI)
                .append(O3Units, pollution.O3Units)
                .append(O3Mean, pollution.O3Mean)
                .append(O31stMaxValue, pollution.O31stMaxValue)
                .append(O31stMaxHour, pollution.O31stMaxHour)
                .append(O3AQI, pollution.O3AQI)
                .append(SO2Units, pollution.SO2Units)
                .append(SO2Mean, pollution.SO2Mean)
                .append(SO21stMaxValue, pollution.SO21stMaxValue)
                .append(SO21stMaxHour, pollution.SO21stMaxHour)
                .append(SO2AQI, pollution.SO2AQI)
                .append(COUnits, pollution.COUnits)
                .append(COMean, pollution.COMean)
                .append(CO1stMaxValue, pollution.CO1stMaxValue)
                .append(CO1stMaxHour, pollution.CO1stMaxHour)
                .append(COAQI, pollution.COAQI)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(stateCode)
                .append(countyCode)
                .append(siteNum)
                .append(address)
                .append(state)
                .append(county)
                .append(city)
                .append(dateLocal)
                .append(NO2Units)
                .append(NO2Mean)
                .append(NO21stMaxValue)
                .append(NO21stMaxHour)
                .append(NO2AQI)
                .append(O3Units)
                .append(O3Mean)
                .append(O31stMaxValue)
                .append(O31stMaxHour)
                .append(O3AQI)
                .append(SO2Units)
                .append(SO2Mean)
                .append(SO21stMaxValue)
                .append(SO21stMaxHour)
                .append(SO2AQI)
                .append(COUnits)
                .append(COMean)
                .append(CO1stMaxValue)
                .append(CO1stMaxHour)
                .append(COAQI)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("stateCode", stateCode)
                .append("countyCode", countyCode)
                .append("siteNum", siteNum)
                .append("address", address)
                .append("state", state)
                .append("county", county)
                .append("city", city)
                .append("dateLocal", dateLocal)
                .append("NO2Units", NO2Units)
                .append("NO2Mean", NO2Mean)
                .append("NO21stMaxValue", NO21stMaxValue)
                .append("NO21stMaxHour", NO21stMaxHour)
                .append("NO2AQI", NO2AQI)
                .append("O3Units", O3Units)
                .append("O3Mean", O3Mean)
                .append("O31stMaxValue", O31stMaxValue)
                .append("O31stMaxHour", O31stMaxHour)
                .append("O3AQI", O3AQI)
                .append("SO2Units", SO2Units)
                .append("SO2Mean", SO2Mean)
                .append("SO21stMaxValue", SO21stMaxValue)
                .append("SO21stMaxHour", SO21stMaxHour)
                .append("SO2AQI", SO2AQI)
                .append("COUnits", COUnits)
                .append("COMean", COMean)
                .append("CO1stMaxValue", CO1stMaxValue)
                .append("CO1stMaxHour", CO1stMaxHour)
                .append("COAQI", COAQI)
                .toString();
    }
}
    