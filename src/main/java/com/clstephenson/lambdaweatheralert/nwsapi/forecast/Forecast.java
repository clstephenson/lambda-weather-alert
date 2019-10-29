
package com.clstephenson.lambdaweatheralert.nwsapi.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    private String type;
    private Properties properties;

    /**
     * No args constructor for use in serialization
     */
    public Forecast() {
    }

    public Forecast(String type, Properties properties) {
        this.type = type;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
