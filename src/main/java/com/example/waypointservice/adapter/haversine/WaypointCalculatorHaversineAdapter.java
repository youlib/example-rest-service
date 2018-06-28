package com.example.waypointservice.adapter.haversine;

import com.example.waypointservice.adapter.WaypointCalculatorAdapter;
import com.example.waypointservice.model.Position;
import org.springframework.stereotype.Component;


@Component
public class WaypointCalculatorHaversineAdapter implements WaypointCalculatorAdapter {

    private static final double PI = 3.1415;


    //copyright missing
    public double calculateDistanceBetweenWaypoints(Position start, Position end) {

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
