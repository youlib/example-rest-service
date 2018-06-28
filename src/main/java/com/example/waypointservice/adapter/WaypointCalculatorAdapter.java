package com.example.waypointservice.adapter;

import com.example.waypointservice.model.Position;

/**
 * @author Julia Bogdanou
 */
public interface WaypointCalculatorAdapter {

    double calculateDistanceBetweenWaypoints(Position start, Position end);

}
