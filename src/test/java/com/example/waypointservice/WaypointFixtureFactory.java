package com.example.waypointservice;

import com.example.waypointservice.DTO.WaypointServiceResponse;
import com.example.waypointservice.model.Position;
import com.example.waypointservice.model.Waypoint;

import java.util.*;

/**
 * @author Julia Bogdanou
 */
public class WaypointFixtureFactory {

    public static List<Waypoint> generateValidWaypointsWithPositiveDegrees() {

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(generateWaypoint("2016-06-21T12:00:00.000Z", 59.334, 18.0667, 6.3889, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:05.000Z", 59.3337, 18.0662, 9.4, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:10.000Z", 59.3331, 18.0664, 11.1, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:15.000Z", 59.3327, 18.0665, 8.32, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:20.000Z", 59.3323, 18.0666, 8.33, 8.33));

        return waypoints;
    }

    public static WaypointServiceResponse generateValidWaypointServiceResponseForPositiveDegrees() {
        return generateWaypointServiceResponse(111.45d, 201.13d, 10l, 20l);
    }

    public static List<Waypoint> generateValidWaypointsWithNegativeDegrees() {

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(generateWaypoint("2016-06-21T12:00:00.000Z", -64.22587, -31.4064, 6.3889, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:05.000Z", -64.2257, -31.40646, 9.4, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:10.000Z", -64.22501, -31.40667, 11.1, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:15.000Z", -64.22484, -31.40672, 8.32, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:20.000Z", -64.224, -31.40701, 8.33, 8.33));

        return waypoints;

    }

    public static WaypointServiceResponse generateValidWaypointServiceResponseForNegativeDegrees() {
        return generateWaypointServiceResponse(96.52d, 210.02d, 10l, 20l);

    }

    public static List<Waypoint> generateValidWaypointsWithNegativeLatitude() {
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(generateWaypoint("2016-06-21T12:00:00.000Z", -8.72941, 53.75922, 6.3889, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:05.000Z", -8.7288, 53.7592, 9.4, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:10.000Z", -8.72754, 53.75915, 11.1, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:15.000Z", -8.72698, 53.75913, 8.32, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:20.000Z", -8.72661, 53.75911, 8.33, 8.33));
        return waypoints;

    }

    public static WaypointServiceResponse generateValidWaypointServiceResponseForNegativeLatitude() {
        return generateWaypointServiceResponse(208.07d, 311.58d, 10l, 20l);
    }

    public static List<Waypoint> generateValidWaypointsWithNegativeLongitude() {
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(generateWaypoint("2016-06-21T12:00:00.000Z", 8.72941, -53.75922, 6.3889, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:05.000Z", 8.7288, -53.7592, 9.4, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:10.000Z", 8.72754, -53.75915, 11.1, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:15.000Z", 8.72698, -53.75913, 8.32, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:20.000Z", 8.72661, -53.75911, 8.33, 8.33));
        return waypoints;

    }

    public static WaypointServiceResponse generateValidWaypointServiceResponseForNegativeLongitude() {
        return generateWaypointServiceResponse(208.07d, 311.58d, 10l, 20l);
    }

    public static List<Waypoint> generateValidWaypointsThatCrossGreenwichMeridian() {
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(generateWaypoint("2016-06-21T12:00:00.000Z", -0.00062, 51.47183, 6.3889, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:05.000Z", -0.00048, 51.47185, 9.4, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:10.000Z", -0.00033, 51.47185, 11.1, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:15.000Z", 9e-05, 51.47188, 8.32, 8.33));
        waypoints.add(generateWaypoint("2016-06-21T12:00:20.000Z", 0.00042, 51.4719, 8.33, 8.33));

        return waypoints;
    }

    public static WaypointServiceResponse generateValidWaypointServiceResponseForGreenwichMeridianCrossing() {
        return generateWaypointServiceResponse(32.4d, 115.98d, 10l, 20l);
    }

    public static Waypoint generateWaypoint(String timestamp, double latitude, double longtitude, double speed, double speedLimit) {
        Position position = generatePosition(latitude, longtitude);

        Waypoint waypoint = new Waypoint();
        waypoint.setTimestamp(timestamp);
        waypoint.setSpeed(speed);
        waypoint.setSpeedLimit(speedLimit);

        waypoint.setPosition(position);

        return waypoint;
    }

    public static Position generatePosition(double latitude, double longtitude) {
        Position position = new Position();
        position.setLatitude(latitude);
        position.setLongitude(longtitude);
        return position;
    }


    public static WaypointServiceResponse generateWaypointServiceResponse(double distanceSpeeding, double totalDistance, long durationSpeeding, long totalDuration) {
        WaypointServiceResponse response = new WaypointServiceResponse.Builder()
                .setDistanceSpeeding(distanceSpeeding)
                .setDurationSpeeding(durationSpeeding)
                .setTotalDistance(totalDistance)
                .setTotalDuration(totalDuration)
                .build();
        return response;
    }

}
