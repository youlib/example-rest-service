package com.example.waypointservice;

import com.example.waypointservice.json.Waypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author Julia Bogdanou
 */

@RestController
public class WaypointController {

    private final WaypointService waypointService;

    @Autowired
    public WaypointController(WaypointService waypointService) {
        this.waypointService = waypointService;
    }

    @RequestMapping(value = "/speeding", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<WaypointServiceResponse> calculateSpeedingForRoute(@RequestBody List<Waypoint> waypoints) {

        //Standard annotated validation won't work in this case, since List is not a bean
        Assert.notEmpty(waypoints, "Waypoints cannot be null or empty");

        WaypointServiceResponse response = waypointService.calculateSpeedingForRoute(waypoints);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class, DateTimeParseException.class})
    @ResponseBody
    public String exceptionHandler(Exception exception) {
        return "Exception, the following input was invalid in the request: " + exception.getLocalizedMessage();
    }

}
