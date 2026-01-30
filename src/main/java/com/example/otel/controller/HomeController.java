package com.example.otel.controller;

import com.example.otel.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeService homeService;

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home() {
        log.info("Home Endpoint Called");
        return "Hello World!";
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        log.info("Greet Endpoint Called with name: {}", name);
        if ("null".equals(name))
            homeService.simulateWork(1000);
        else
            homeService.simulateWork(200);
        return "Hello, " + name + "!";
    }

    @GetMapping("/compute")
    public String compute() throws InterruptedException {
        log.info("Compute Endpoint Called");
        Thread.sleep(500);
        return "Computation Complete!";
    }
}
