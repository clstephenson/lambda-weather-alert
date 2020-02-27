package com.clstephenson.lambdaweatheralert.weatherapi;

import com.clstephenson.lambdaweatheralert.Location;
import com.clstephenson.lambdaweatheralert.weatherapi.nws.NWSSource;

public class WeatherSourceFactory {

    public static <T extends WeatherSource> WeatherSource getWeatherSourceAtLocation(Location location) {
        return new NWSSource(location);
    }

}
