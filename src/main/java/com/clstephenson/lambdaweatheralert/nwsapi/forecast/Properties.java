
package com.clstephenson.lambdaweatheralert.nwsapi.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private String updated;
    private String units;
    private String forecastGenerator;
    private String generatedAt;
    private String updateTime;
    private String validTimes;
    private List<Period> periods = null;

    /**
     * No args constructor for use in serialization
     */
    public Properties() {
    }

    public Properties(String updated, String units, String forecastGenerator, String generatedAt, String updateTime, String validTimes, List<Period> periods) {
        this.updated = updated;
        this.units = units;
        this.forecastGenerator = forecastGenerator;
        this.generatedAt = generatedAt;
        this.updateTime = updateTime;
        this.validTimes = validTimes;
        this.periods = periods;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getForecastGenerator() {
        return forecastGenerator;
    }

    public void setForecastGenerator(String forecastGenerator) {
        this.forecastGenerator = forecastGenerator;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getValidTimes() {
        return validTimes;
    }

    public void setValidTimes(String validTimes) {
        this.validTimes = validTimes;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

}
