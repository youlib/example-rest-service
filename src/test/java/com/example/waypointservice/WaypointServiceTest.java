package com.example.waypointservice;

import com.example.waypointservice.DTO.WaypointServiceResponse;
import com.example.waypointservice.adapter.WaypointCalculatorAdapter;
import com.example.waypointservice.model.Position;
import com.example.waypointservice.service.WaypointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Queue;

import static com.example.waypointservice.WaypointFixtureFactory.generateValidWaypointServiceResponseForPositiveDegrees;
import static com.example.waypointservice.WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WaypointServiceTest {

    @Mock
    private WaypointCalculatorAdapter mockAdapter = mock(WaypointCalculatorAdapter.class);

    @InjectMocks
    private WaypointService service;

    @Test
    public void testWaypointServiceWhenAllDegreesArePositiveValues() {

        //expected mock adapter behavior
        when(mockAdapter.calculateDistanceBetweenWaypoints(any(Position.class), any(Position.class)))
                .thenReturn(43.78192568927482d)
                .thenReturn(67.67240657991077d)
                .thenReturn(44.836819340795486d)
                .thenReturn(44.83682778724704d);

        //all degrees positive values
        WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithPositiveDegrees());
        assertTrue(response.equals(generateValidWaypointServiceResponseForPositiveDegrees()));
        verify(mockAdapter, times(4)).calculateDistanceBetweenWaypoints(any(Position.class), any(Position.class));


    }

    //TODO fix tests with mock
    /**
     @Test public void testWaypointServiceWhenAllDegreesAreNegativeValues() {

     //all degrees negative values
     WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithNegativeDegrees());
     assertTrue(response.equals(generateValidWaypointServiceResponseForNegativeDegrees()));


     }

     @Test public void testWaypointServiceWhenLatitudeIsNegative() {
     //negative latitude
     WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithNegativeLatitude());
     assertTrue(response.equals(generateValidWaypointServiceResponseForNegativeLatitude()));


     }

     @Test public void testWaypointServiceWhenLongitudeIsNegative() {
     //negative longitude
     WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsWithNegativeLongitude());
     assertTrue(response.equals(generateValidWaypointServiceResponseForNegativeLongitude()));
     }

     @Test public void testWaypointServiceWhenCrossingGreenwichMeridian() {
     //crossing Greenwich
     WaypointServiceResponse response = service.calculateSpeedingForRoute(generateValidWaypointsThatCrossGreenwichMeridian());
     assertTrue(response.equals(generateValidWaypointServiceResponseForGreenwichMeridianCrossing()));
     }
     **/
}
