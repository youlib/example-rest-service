package com.example.waypointservice;

import com.example.waypointservice.controller.WaypointController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Julia Bogdanou
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContextTest {

    @Autowired
    private WaypointController waypointController;

    @Test
    public void testContext() {
        assertThat(waypointController).isNotNull();

    }
}
