package com.me.sonarqubeexample;

import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class HelloController {

    public static String greeting = String.format("Greetings from Spring Boot!%n");

    @RequestMapping("/")
    public String index() {
        return greeting;
    }

    @RequestMapping(path = "/{name}")
    public String greet(@PathVariable("name") String name) {
        return String.format("Hello %s%n", name);
    }

    @RequestMapping(path = "/hi")
    private String sayHi() {
        String hi = "Hi\n";
        int count = 0;

        return hi;
    }

    @GetMapping(path = "/dayofweek")
    public String getDayOfWeek() {
        String dayOfWeek = LocalDateTime.now().getDayOfWeek().toString();

        return String.format("Today is %s%n", dayOfWeek);
    }
}
