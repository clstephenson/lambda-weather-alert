package com.clstephenson.lambdaweatheralert;

import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSource;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSourceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WeatherSourceFactoryTest {

    @Test
    void whenGetWeatherSource_thenReturnsObjectOfTypeWeatherSource() throws Exception {
        WeatherSource mockWeatherSource = Mockito.mock(WeatherSource.class);
        Location mockLocation = Mockito.mock(Location.class);
        WeatherSource newWeatherSource = WeatherSourceFactory.getWeatherSourceAtLocation(mockLocation);
        Assertions.assertNotNull(newWeatherSource);
    }
}