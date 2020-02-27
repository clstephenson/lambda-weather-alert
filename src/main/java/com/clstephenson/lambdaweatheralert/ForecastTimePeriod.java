package com.clstephenson.lambdaweatheralert;

public enum ForecastTimePeriod {

    TODAY ("today"),
    TONIGHT ("tonight"),
    TOMORROW ("tomorrow");

    private final String text;

    ForecastTimePeriod(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
