package com.shankarstudy.microservices.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")  // Retry 3 times in case of failure if nothing is configured in application properties
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")  // It will break the chain of Microservices connected in case deeper level microservice is down for long time then after some request, it will directly return the fallback default response without calling dependent Microservice
    // @RateLimiter(name = "default")  // Limit the no of API calls in a given time second, e.g. Max 10000 calls in 10s
    @Bulkhead(name = "sample-api")     // How many concurrent calls are allowed
    public String sampleApi() {
        logger.info("Sample api call received");

        // Hardcode some dummy URL to fail it and test circuit breaker
//        ResponseEntity<String> forEntity = new RestTemplate()
//                .getForEntity("http://localhost:8080/some-dummy-url", String.class);

        // return forEntity.getBody();
        return "Sample API";
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
