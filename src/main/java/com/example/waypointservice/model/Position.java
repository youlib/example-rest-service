package com.example.waypointservice.model;


import java.util.Objects;

/**
 * @author Julia Bogdanou
 */
public class Position {

    private Double latitude;

    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(latitude, position.latitude) &&
                Objects.equals(longitude, position.longitude);
    }

    @Override
    public int hashCode() {

        return Objects.hash(latitude, longitude);
    }
}

