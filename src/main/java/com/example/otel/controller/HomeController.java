package com.example.otel.controller;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home() {
        log.info("Home Endpoint Called");
        return "Hello World!";
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        log.info("Greet Endpoint Called with name: {}", name);
        simulateWork();
        return "Hello, " + name + "!";
    }

    @GetMapping("/compute")
    public String compute() throws InterruptedException {
        log.info("Compute Endpoint Called");
        Thread.sleep(500);
        return "Computation Complete!";
    }

    @Observed(name = "simulate-work-method")
    private void simulateWork() {
        try {
            Thread.sleep(50);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
