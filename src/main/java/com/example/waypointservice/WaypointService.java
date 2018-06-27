package com.example.waypointservice;

import com.example.waypointservice.json.Position;
import com.example.waypointservice.json.Waypoint;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Bogdanou
 */
@Service
public class WaypointService {

    private static final double PI = 3.1415;
    private static final int ROUND_TO_DECIMALS = 2;

    /**
     * Assumptions:
     * -‘haversine’ formula should suffice, otherwise integration with maps service will be needed,
     * because haversine will cut corners, while, i.e. google maps will draw on predefined road
     * -2 decimal points should be enough for vehicles
     * -Speed remains constant from one waypoint to next, so does speed limit
     * -Result returned in km
     *
     * @param waypoints
     * @return
     */

    public WaypointServiceResponse calculateSpeedingForRoute(List<Waypoint> waypoints) {

        WaypointValidator.validateWaypoints(waypoints);

        Duration durationSpeeding = Duration.ZERO;
        Duration totalDuration = Duration.ZERO;
        double totalDistance = 0;
        double distanceSpeeding = 0;

        LinkedList<Waypoint> waypointQueue = new LinkedList<>(waypoints);

        while (waypointQueue.size() > 1) {
            Waypoint startWaypoint = waypointQueue.removeFirst();
            Waypoint endWaypoint = waypointQueue.peekFirst();
            Duration duration = Duration.between(startWaypoint.getLocalDateTime(), endWaypoint.getLocalDateTime());
            double distance = calculateDistanceBetweenWaypoints(startWaypoint.getPosition(), endWaypoint.getPosition());

            totalDistance = totalDistance + distance;
            totalDuration = totalDuration.plus(duration);
            if (endWaypoint.getSpeed() > endWaypoint.getSpeedLimit()) {
                distanceSpeeding = distanceSpeeding + distance;
                durationSpeeding = durationSpeeding.plus(duration);
            }

        }
        return new WaypointServiceResponse.Builder()
            .setDistanceSpeeding(Precision.round(distanceSpeeding, ROUND_TO_DECIMALS))
            .setDurationSpeeding(durationSpeeding.getSeconds())
            .setTotalDistance(Precision.round(totalDistance, ROUND_TO_DECIMALS))
            .setTotalDuration(totalDuration.getSeconds())
            .build();
    }

    /**
     * Formula from https://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param start
     * @param end
     * @return
     */
    private double calculateDistanceBetweenWaypoints(Position start, Position end) {

//        Haversine formula:	a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
//        c = 2 ⋅ atan2( √a, √(1−a) )
//        d = R ⋅ c
//        where	f is latitude, l is longitude, R is earth’s radius (mean radius = 6,371km);
//        note that angles need to be in radians to pass to trig functions!

        double R = 6371e3; // metres
        double f1 = toRadians(start.getLatitude());
        double f2 = toRadians(end.getLatitude());
        double Df = toRadians(end.getLatitude() - start.getLatitude());
        double Dl = toRadians(end.getLongitude() - start.getLongitude());

        double a = Math.sin(Df / 2) * Math.sin(Df / 2) +
            Math.cos(f1) * Math.cos(f2) *
                Math.sin(Dl / 2) * Math.sin(Dl / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;

    }

    private double toRadians(double degrees) {
        return (degrees / 180) * PI;

    }
}
