package com.clstephenson.lambdaweatheralert.weatherapi.nws;

import com.clstephenson.lambdaweatheralert.Location;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSource;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSourceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

class NWSSourceTest {

    private final String LONGITUDE = "-111.91360473632812";
    private final String LATITUDE = "33.70777628973998";
    private WeatherSource weatherSource;

    @BeforeEach
    void setUp() {
        weatherSource = WeatherSourceFactory.getWeatherSourceAtLocation(
                Location.getLocationFromCoordinates(LATITUDE, LONGITUDE)
        );
    }

    @Test
    void getLowTemperature_shouldNotThrowException() {
        Assertions.assertDoesNotThrow(weatherSource::getLowTemperatureForTonight);
    }

    @Test
    void getLowTemperature_shouldReturnIntBetweenNeg50And150() {
        assertThat(weatherSource.getLowTemperatureForTonight(), is(both(greaterThan(-50)).and(lessThan(150))));
    }

    @Test
    void getHighTemperature_shouldNotThrowException() {
        Assertions.assertDoesNotThrow(weatherSource::getHighTempForToday);
    }

    @Test
    void getHighTemperature_shouldReturnIntBetweenNeg50And150() {
        assertThat(weatherSource.getHighTempForToday(), is(both(greaterThan(-50)).and(lessThan(150))));
    }

    @Test
    void getForecastForTodayAndTonight_shouldReturnString_containingSubstringMph() {
        assertThat(weatherSource.getForecastForTodayAndTonight(), containsString("mph"));
    }
}