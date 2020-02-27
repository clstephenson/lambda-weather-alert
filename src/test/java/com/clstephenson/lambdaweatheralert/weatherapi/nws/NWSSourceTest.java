package com.clstephenson.lambdaweatheralert.weatherapi.nws;

import com.clstephenson.lambdaweatheralert.Location;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSource;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSourceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NWSSourceTest {

    @Test
    void getLowTemperature() {
        String latitude = "33.70777628973998";
        String longitude = "-111.91360473632812";
        WeatherSource weatherSource = WeatherSourceFactory.getWeatherSourceAtLocation(
                Location.getLocationFromCoordinates(latitude, longitude)
        );
        Assertions.assertDoesNotThrow(weatherSource::getLowTemperatureForTonight);
    }
}