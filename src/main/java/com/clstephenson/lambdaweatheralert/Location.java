package com.clstephenson.lambdaweatheralert;

public class Location {

    private final String latitude;
    private final String longitude;

    public static Location getLocationFromCoordinates(String latitude, String longitude) {
        return new Location(latitude, longitude);
    }

    private Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
