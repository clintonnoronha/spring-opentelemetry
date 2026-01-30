package com.example.otel.service;

import io.micrometer.observation.annotation.ObservationKeyValue;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public static final Logger log = LoggerFactory.getLogger(HomeService.class);

    // Needs spring AOP starter dependency for new span id generation
    @Observed(name = "simulate-work")
    public void simulateWork(@ObservationKeyValue(key = "time", value = "millis") long millis) {
        try {
            Thread.sleep(millis);
            log.info("Simulate Work Endpoint Called");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
