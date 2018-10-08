package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String publicHello() {
        return "Hello world!";
    }

    @GetMapping("/pause/{duration}")
    public Mono<Void> pause2(@PathVariable long duration) {
        LOGGER.info("Pausing for {} msec", duration);
        return (duration > 0 ? Mono.delay(Duration.ofMillis(duration)) : Mono.empty())
                .then()
                .doFinally(s -> LOGGER.info("Paused for {} msec", duration));
    }
}
