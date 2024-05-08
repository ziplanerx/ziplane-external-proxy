package com.ziplane.external.controller;


import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/extproxy/noauth")
@CrossOrigin(origins = {"*", "http://localhost:4200"})
public class HealthCheckController {

    @GetMapping("/health/check")
    public String getHealthCheck() {
        return "ZipLaneRx-External-Proxy Service is running!";
    }

}