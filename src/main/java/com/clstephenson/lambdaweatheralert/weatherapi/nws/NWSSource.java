package com.clstephenson.lambdaweatheralert.weatherapi.nws;

import com.clstephenson.lambdaweatheralert.ForecastTimePeriod;
import com.clstephenson.lambdaweatheralert.Location;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSource;
import com.clstephenson.lambdaweatheralert.weatherapi.nws.forecast.Forecast;
import com.clstephenson.lambdaweatheralert.weatherapi.nws.forecast.Period;
import com.clstephenson.lambdaweatheralert.weatherapi.nws.point.Point;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class NWSSource implements WeatherSource {

    private static final String API_TARGET_URI = "https://api.weather.gov";
    private static final String API_PATH = "points";
    private final Location location;
    private Client restClient;

    public NWSSource(Location location) {
        this.location = location;
    }

    @Override
    public int getLowTemperatureForTonight() {
        restClient = ClientBuilder.newClient();
        Point point = getPointDataFromAPI();
        Forecast forecast = getForecastDataFromAPI(point.getProperties().getForecast());
        List<Period> periodList = forecast.getProperties().getPeriods();
        Period period = periodList
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(ForecastTimePeriod.TONIGHT.toString()))
                .findFirst()
                .get();
        return period.getTemperature();
    }

    private Point getPointDataFromAPI() {
        return restClient
                .target(API_TARGET_URI)
                .path(String.format("%s/%s,%s", API_PATH, location.getLatitude(), location.getLongitude()))
                .request(MediaType.APPLICATION_JSON)
                .get(Point.class);
    }

    private Forecast getForecastDataFromAPI(String uri) {
        return restClient
                .target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
    }
}
