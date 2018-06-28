package com.example.waypointservice;

import com.example.waypointservice.DTO.WaypointServiceResponse;
import com.example.waypointservice.adapter.haversine.WaypointCalculatorHaversineAdapter;
import com.example.waypointservice.service.WaypointService;
import org.junit.Test;

import static com.example.waypointservice.WaypointFixtureFactory.*;
import static org.junit.Assert.assertTrue;

/**
 * @author Julia Bogdanou
 */

public class WaypointServiceIntegrationTest {

    private WaypointService service = new WaypointService(new WaypointCalculatorHaversineAdapter());

    @Test
    public void testWaypointServiceWhenAllDegreesArePositiveValues() {

        //all degrees positive values
        WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithPositiveDegrees());
        assertTrue(response.equals(generateValidWaypointServiceResponseForPositiveDegrees()));


    }

    @Test
    public void testWaypointServiceWhenAllDegreesAreNegativeValues() {

        //all degrees negative values
        WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithNegativeDegrees());
        assertTrue(response.equals(generateValidWaypointServiceResponseForNegativeDegrees()));


    }

    @Test
    public void testWaypointServiceWhenLatitudeIsNegative() {
        //negative latitude
        WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithNegativeLatitude());
        assertTrue(response.equals(generateValidWaypointServiceResponseForNegativeLatitude()));


    }

    @Test
    public void testWaypointServiceWhenLongitudeIsNegative() {
        //negative longitude
        WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithNegativeLongitude());
        assertTrue(response.equals(generateValidWaypointServiceResponseForNegativeLongitude()));
    }

    @Test
    public void testWaypointServiceWhenCrossingGreenwichMeridian() {
        //crossing Greenwich
        WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsThatCrossGreenwichMeridian());
        assertTrue(response.equals(generateValidWaypointServiceResponseForGreenwichMeridianCrossing()));
    }

}



