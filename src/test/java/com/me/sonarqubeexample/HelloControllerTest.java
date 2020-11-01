package com.me.sonarqubeexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @Test
    public void testContextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    void testIndex() {
        assertEquals(controller.GREETING, controller.index());
    }

    @Test
    void testGetName() {
        assertEquals("Hello Dave\n", controller.greet("Dave"));
    }

    @Test
    public void testGreet() {
        assertTrue(controller.greet("Dave").endsWith("Dave\n"));
    }
}