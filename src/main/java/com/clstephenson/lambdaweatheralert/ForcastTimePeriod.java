package com.clstephenson.lambdaweatheralert;

public enum ForcastTimePeriod {

    TODAY ("today"),
    TONIGHT ("tonight"),
    TOMORROW ("tomorrow");

    private final String text;

    ForcastTimePeriod(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
