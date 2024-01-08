package com.app.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static String startedAt;

    public static void  main(String[] args) {
        SpringApplication.run(Application.class, args);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ZonedDateTime now = ZonedDateTime.now();
        startedAt = now.format(dtf);
        logger.info("Application started at {}", startedAt);

    }

    @GetMapping(path = "/status")
    public Map<String, String> statusCheck() {
        return Map.of(
                "status", "Healthy",
                "started_at", startedAt
        );
    }
}
