
package com.clstephenson.lambdaweatheralert.weatherapi.nws.point;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {

    private String id;
    private String type;
    private Properties properties;

    /**
     * No args constructor for use in serialization
     */
    public Point() {
    }

    public Point(String id, String type, Properties properties) {
        this.id = id;
        this.type = type;
        this.properties = properties;
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

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
