package com.example.waypointservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.waypointservice.json.Waypoint;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Julia Bogdanou
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WaypointControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHappySchenario() throws Exception {

        WaypointServiceResponse expectedWaypointServiceResponse = WaypointFixtureFactory.generateValidWaypointServiceResponseForPositiveDegrees();

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees()))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(expectedWaypointServiceResponse)))
        );

    }

    @Test
    public void testInputOfWaypointsWithWrongFields() throws Exception {

        List<Waypoint> waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //null timestamp
        waypoints.get(0).setTimestamp(null);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );

        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //wrong timestamp
        waypoints.get(0).setTimestamp("good luck parsing that");

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );
        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //null position

        waypoints.get(0).setPosition(null);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );
        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //null latitude

        waypoints.get(0).getPosition().setLatitude(null);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );
        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //null longtitude
        waypoints.get(0).getPosition().setLongitude(null);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );
        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //null speed
        waypoints.get(0).setSpeed(null);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );
        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //null speed limit
        waypoints.get(0).setSpeedLimit(null);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );
        //reset fixture
        waypoints = WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees();

        //wrong timestamp sequence
        Collections.shuffle(waypoints);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest())
        );

        //only one waypoint returns 0s
        waypoints = Lists.newArrayList(WaypointFixtureFactory.generateValidWaypointsWithPositiveDegrees().get(0));
        WaypointServiceResponse expectedWaypointServiceResponse = WaypointFixtureFactory.generateWaypointServiceResponse(0, 0, 0, 0);

        assertThat(
            this.mockMvc.perform(post("/speeding")
                .content(asJsonString(waypoints))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(expectedWaypointServiceResponse)))
        );

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


