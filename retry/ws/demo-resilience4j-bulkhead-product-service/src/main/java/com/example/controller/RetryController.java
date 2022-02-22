package com.example.controller;

import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
public class RetryController {
    @Autowired
    private RetryRegistry registry;

    @PostConstruct
    public void postConstruct() {
        registry.retry("sample-api").getEventPublisher().onRetry(ev -> log.info("messsage: {}", ev));
    }

    @GetMapping("/sample-api")
    @Retry(name = "sample-api")
    public String sampleApi() {
        log.info("Sample Api call received");
        if (true) {
            throw new org.springframework.web.client.HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "success";
    }
}
