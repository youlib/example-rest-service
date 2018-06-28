package com.example.waypointservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Julia Bogdanou
 */
public class Waypoint {

    private LocalDateTime localDateTime;

    private String timestamp;

    private Position position;

    private Double speed;

    @JsonProperty("speed_limit")
    private Double speedLimit;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @JsonIgnore
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Double speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waypoint waypoint = (Waypoint) o;
        return Objects.equals(localDateTime, waypoint.localDateTime) &&
                Objects.equals(timestamp, waypoint.timestamp) &&
                Objects.equals(position, waypoint.position) &&
                Objects.equals(speed, waypoint.speed) &&
                Objects.equals(speedLimit, waypoint.speedLimit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(localDateTime, timestamp, position, speed, speedLimit);
    }
}
