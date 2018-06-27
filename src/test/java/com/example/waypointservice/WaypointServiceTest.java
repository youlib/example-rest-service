package com.example.waypointservice;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Julia Bogdanou
 */

public class WaypointServiceTest {

    private WaypointService service = new WaypointService();

    @Test
    public void testWaypointService() {

        //all degrees positive values
        WaypointServiceResponse response = service.calculateSpeedingForRoute(WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees());
        Assert.assertTrue(response.equals(WaypointFixtureFactory.generateValidWaypointServiceResponseForPositiveDegrees()));

        //all degrees negative values
        response = service.calculateSpeedingForRoute(WaypointFixtureFactory.generateValidWaypointsWithNegativeDegrees());
        Assert.assertTrue(response.equals(WaypointFixtureFactory.generateValidWaypointServiceResponseForNegativeDegrees()));

        //negative latitude
        response = service.calculateSpeedingForRoute(WaypointFixtureFactory.generateValidWaypointsWithNegativeLatitude());
        Assert.assertTrue(response.equals(WaypointFixtureFactory.generateValidWaypointServiceResponseForNegativeLatitude()));

        //negative longitude
        response = service.calculateSpeedingForRoute(WaypointFixtureFactory.generateValidWaypointsWithNegativeLongitude());
        Assert.assertTrue(response.equals(WaypointFixtureFactory.generateValidWaypointServiceResponseForNegativeLongitude()));

        //crossing Greenwich
        response = service.calculateSpeedingForRoute(WaypointFixtureFactory.generateValidWaypointsThatCrossGreenwichMeridian());
        Assert.assertTrue(response.equals(WaypointFixtureFactory.generateValidWaypointServiceResponseForGreenwichMeridianCrossing()));

    }

}



