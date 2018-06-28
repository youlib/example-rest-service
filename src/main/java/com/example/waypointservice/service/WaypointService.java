package com.example.waypointservice.service;

import com.example.waypointservice.DTO.WaypointServiceResponse;
import com.example.waypointservice.adapter.WaypointCalculatorAdapter;
import com.example.waypointservice.controller.WaypointValidator;
import com.example.waypointservice.model.Waypoint;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Bogdanou
 */
@Service
public class WaypointService {

    private static final int ROUND_TO_DECIMALS = 2;


    private WaypointCalculatorAdapter waypointCalculatorAdapter;

    @Autowired
    public WaypointService(@NotNull WaypointCalculatorAdapter waypointCalculatorAdapter) {
        this.waypointCalculatorAdapter = waypointCalculatorAdapter;
    }

    /**
     * Assumptions:
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
            double distance = waypointCalculatorAdapter.calculateDistanceBetweenWaypoints(startWaypoint.getPosition(), endWaypoint.getPosition());

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


}
