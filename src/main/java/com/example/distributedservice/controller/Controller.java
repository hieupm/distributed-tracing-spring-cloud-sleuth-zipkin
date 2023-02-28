package com.example.distributedservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/first")
    public ResponseEntity firstService() {

        logger.info("Incoming request from /first ", applicationName);
        String response = restTemplate.getForObject("http://localhost:6089/service/second", String.class);
        return ResponseEntity.ok("Response from /first + " + response);
    }

    @GetMapping("/second")
    public ResponseEntity secondService() {
        logger.info("Incoming request from /second", applicationName);
        return ResponseEntity.ok("response from /second");
    }
}
