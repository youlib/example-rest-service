package com.example.waypointservice.controller;

import com.example.waypointservice.DTO.WaypointServiceResponse;
import com.example.waypointservice.model.Waypoint;
import com.example.waypointservice.service.WaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author Julia Bogdanou
 */

@RestController
public class WaypointController {

    private final WaypointService waypointService;

    @Autowired
    public WaypointController(@NotNull WaypointService waypointService) {
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
