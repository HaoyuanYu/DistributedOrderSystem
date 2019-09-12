package com.herron.learnspringcloud.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicConfigTestController {

    @Autowired
    private ZuulProperties zuulProperties;

    @GetMapping("/print")
    public Boolean print() {
        return zuulProperties.getRetryable();
    }
}
