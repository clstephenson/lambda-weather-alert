
package com.clstephenson.lambdaweatheralert.nwsapi.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Period {

    private int number;
    private String name;
    private String startTime;
    private String endTime;
    private boolean isDaytime;
    private int temperature;
    private String temperatureUnit;
    private Object temperatureTrend;
    private String windSpeed;
    private String windDirection;
    private String icon;
    private String shortForecast;
    private String detailedForecast;

    /**
     * No args constructor for use in serialization
     */
    public Period() {
    }

    public Period(int number, String name, String startTime, String endTime, boolean isDaytime, int temperature, String temperatureUnit, Object temperatureTrend, String windSpeed, String windDirection, String icon, String shortForecast, String detailedForecast) {
        this.number = number;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDaytime = isDaytime;
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
        this.temperatureTrend = temperatureTrend;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.icon = icon;
        this.shortForecast = shortForecast;
        this.detailedForecast = detailedForecast;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isIsDaytime() {
        return isDaytime;
    }

    public void setIsDaytime(boolean isDaytime) {
        this.isDaytime = isDaytime;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Object getTemperatureTrend() {
        return temperatureTrend;
    }

    public void setTemperatureTrend(Object temperatureTrend) {
        this.temperatureTrend = temperatureTrend;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

}
