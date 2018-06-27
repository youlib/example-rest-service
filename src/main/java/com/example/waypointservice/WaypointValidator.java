package com.example.waypointservice;

import com.example.waypointservice.json.Waypoint;
import org.springframework.util.Assert;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Julia Bogdanou
 */
public class WaypointValidator {

    public static void validateWaypoints(List<Waypoint> waypoints) {
        Assert.notEmpty(waypoints, "waypoints cannot be null");
        validateWaypointFields(waypoints);
        validateTemporalSequenceOfWaypoints(waypoints);
    }

    private static void validateWaypointFields(List<Waypoint> waypoints) {
        waypoints.stream().forEach(waypoint -> {
            Assert.notNull(waypoint.getTimestamp(), "the timestamp on the waypoint is null");
            waypoint.setLocalDateTime(LocalDateTime.parse(waypoint.getTimestamp(), DateTimeFormatter.ISO_DATE_TIME));
            Assert.notNull(waypoint.getSpeed(), "the speed on the waypoint is null");
            Assert.notNull(waypoint.getSpeedLimit(), "the speed limit on the waypoint is null");
            Assert.notNull(waypoint.getPosition(), "the position on the waypoint is null");
            Assert.notNull(waypoint.getPosition().getLatitude(), "the position's latitude on the waypoint is null");
            Assert.notNull(waypoint.getPosition().getLongitude(), "the position's longtitude on the waypoint is null");
        });
    }

    public static void validateTemporalSequenceOfWaypoints(List<Waypoint> waypoints) throws IllegalArgumentException {
        LocalDateTime currentDateTime = null;
        for (Waypoint waypoint : waypoints) {
            if (currentDateTime == null) {
                //first pass, setting the current dateTime
                currentDateTime = waypoint.getLocalDateTime();
            } else {
                //second etc pass, making sure current dat time is before next one in sequence
                Assert.isTrue(currentDateTime.isBefore(waypoint.getLocalDateTime()), "the timestamp sequence in the waypoints is wrong");
                //picking up the next date time
                currentDateTime = waypoint.getLocalDateTime();
            }
        }
    }
}
