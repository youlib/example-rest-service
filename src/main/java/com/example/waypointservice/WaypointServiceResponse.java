package com.example.waypointservice;

import org.springframework.util.Assert;

/**
 * @author Julia Bogdanou
 */
public class WaypointServiceResponse {

    private double distanceSpeeding;
    private long durationSpeeding;
    private double totalDistance;
    private long totalDuration;

    private WaypointServiceResponse(double distanceSpeeding, long durationSpeeding, double totalDistance, long totalDuration) {
        this.distanceSpeeding = distanceSpeeding;
        this.durationSpeeding = durationSpeeding;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    /**
     * @return the distance speeding in meters
     */
    public double getDistanceSpeeding() {
        return distanceSpeeding;
    }

    /**
     * @return the duration speeding in seconds
     */
    public long getDurationSpeeding() {
        return durationSpeeding;
    }

    /**
     * @return the total distance in meters
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * @return the total duration in seconds
     */
    public long getTotalDuration() {
        return totalDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaypointServiceResponse response = (WaypointServiceResponse) o;

        if (Double.compare(response.distanceSpeeding, distanceSpeeding) != 0) return false;
        if (durationSpeeding != response.durationSpeeding) return false;
        if (Double.compare(response.totalDistance, totalDistance) != 0) return false;
        return totalDuration == response.totalDuration;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(distanceSpeeding);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (durationSpeeding ^ (durationSpeeding >>> 32));
        temp = Double.doubleToLongBits(totalDistance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (totalDuration ^ (totalDuration >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "WaypointServiceResponse{" +
            "distanceSpeeding=" + distanceSpeeding +
            ", durationSpeeding=" + durationSpeeding +
            ", totalDistance=" + totalDistance +
            ", totalDuration=" + totalDuration +
            '}';
    }

    public static class Builder {

        private Double distanceSpeeding;
        private Long durationSpeeding;
        private Double totalDistance;
        private Long totalDuration;

        public Builder setDistanceSpeeding(Double distanceSpeeding) {
            this.distanceSpeeding = distanceSpeeding;
            return this;
        }

        public Builder setDurationSpeeding(Long durationSpeeding) {
            this.durationSpeeding = durationSpeeding;
            return this;
        }

        public Builder setTotalDistance(Double totalDistance) {
            this.totalDistance = totalDistance;
            return this;
        }

        public Builder setTotalDuration(Long totalDuration) {
            this.totalDuration = totalDuration;
            return this;
        }

        public WaypointServiceResponse build() {
            Assert.notNull(totalDuration, "the total duration of the WaypointServiceResponse is null");
            Assert.notNull(totalDistance, "the total distance of the WaypointServiceResponse is null");
            Assert.notNull(distanceSpeeding, "the distance speeding of the WaypointServiceResponse is null");
            Assert.notNull(durationSpeeding, "the duration speeding of the WaypointServiceResponse is null");
            return new WaypointServiceResponse(distanceSpeeding, durationSpeeding, totalDistance, totalDuration);
        }

    }

}
