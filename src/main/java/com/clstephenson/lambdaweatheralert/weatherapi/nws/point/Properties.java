
package com.clstephenson.lambdaweatheralert.weatherapi.nws.point;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private String id;
    private String type;
    private String cwa;
    private String forecastOffice;
    private int gridX;
    private int gridY;
    private String forecast;
    private String forecastHourly;
    private String forecastGridData;
    private String observationStations;
    private String forecastZone;
    private String county;
    private String fireWeatherZone;
    private String timeZone;
    private String radarStation;

    /**
     * No args constructor for use in serialization
     */
    public Properties() {
    }

    public Properties(String id, String type, String cwa, String forecastOffice, int gridX, int gridY, String forecast, String forecastHourly, String forecastGridData, String observationStations, String forecastZone, String county, String fireWeatherZone, String timeZone, String radarStation) {
        this.id = id;
        this.type = type;
        this.cwa = cwa;
        this.forecastOffice = forecastOffice;
        this.gridX = gridX;
        this.gridY = gridY;
        this.forecast = forecast;
        this.forecastHourly = forecastHourly;
        this.forecastGridData = forecastGridData;
        this.observationStations = observationStations;
        this.forecastZone = forecastZone;
        this.county = county;
        this.fireWeatherZone = fireWeatherZone;
        this.timeZone = timeZone;
        this.radarStation = radarStation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCwa() {
        return cwa;
    }

    public void setCwa(String cwa) {
        this.cwa = cwa;
    }

    public String getForecastOffice() {
        return forecastOffice;
    }

    public void setForecastOffice(String forecastOffice) {
        this.forecastOffice = forecastOffice;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getForecastHourly() {
        return forecastHourly;
    }

    public void setForecastHourly(String forecastHourly) {
        this.forecastHourly = forecastHourly;
    }

    public String getForecastGridData() {
        return forecastGridData;
    }

    public void setForecastGridData(String forecastGridData) {
        this.forecastGridData = forecastGridData;
    }

    public String getObservationStations() {
        return observationStations;
    }

    public void setObservationStations(String observationStations) {
        this.observationStations = observationStations;
    }

    public String getForecastZone() {
        return forecastZone;
    }

    public void setForecastZone(String forecastZone) {
        this.forecastZone = forecastZone;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getFireWeatherZone() {
        return fireWeatherZone;
    }

    public void setFireWeatherZone(String fireWeatherZone) {
        this.fireWeatherZone = fireWeatherZone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getRadarStation() {
        return radarStation;
    }

    public void setRadarStation(String radarStation) {
        this.radarStation = radarStation;
    }

}
