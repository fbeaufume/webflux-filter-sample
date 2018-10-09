package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);

    private static final String HELLO_WORLD = "Hello world!";

    private static final MyBean BEAN = new MyBean("Foo", 42);

    // Status code from the web filter is null.
    @GetMapping("empty1")
    public void empty1() {
    }

    // Status code from the web filter is null.
    @GetMapping("empty2")
    public Mono<Void> empty2() {
        return Mono.empty();
    }

    // Status code from the web filter is 200.
    @GetMapping("empty3")
    public ResponseEntity<Void> empty3() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Status code from the web filter is 200.
    @GetMapping("empty4")
    public Mono<ResponseEntity<Void>> empty4() {
        return Mono.just(new ResponseEntity<>(HttpStatus.OK));
    }

    // Status code from the web filter is null.
    @GetMapping("/hello1")
    public String hello1() {
        return HELLO_WORLD;
    }

    // Status code from the web filter is null.
    @GetMapping("/hello2")
    public Mono<String> hello2() {
        return Mono.just(HELLO_WORLD);
    }

    // Status code from the web filter is 200.
    @GetMapping("/hello3")
    public ResponseEntity<String> hello3() {
        return ResponseEntity.ok(HELLO_WORLD);
    }

    // Status code from the web filter is 200.
    @GetMapping("/hello4")
    public Mono<ResponseEntity<String>> hello4() {
        return Mono.just(ResponseEntity.ok("Hello world!"));
    }

    // Status code from the web filter is null.
    @GetMapping("/bean1")
    public MyBean bean1() {
        return BEAN;
    }

    // Status code from the web filter is null.
    @GetMapping("/bean2")
    public Mono<MyBean> bean2() {
        return Mono.just(BEAN);
    }

    // Status code from the web filter is 200.
    @GetMapping("/bean3")
    public ResponseEntity<MyBean> bean3() {
        return ResponseEntity.ok(BEAN);
    }

    // Status code from the web filter is 200.
    @GetMapping("/bean4")
    public Mono<ResponseEntity<MyBean>> bean4() {
        return Mono.just(ResponseEntity.ok(BEAN));
    }

    // Status code from the web filter is null.
    @GetMapping("/pause1/{duration}")
    public Mono<String> pause1(@PathVariable long duration) {
        LOGGER.info("Pausing for {} msec", duration);
        return Mono.delay(Duration.ofMillis(duration))
                .thenReturn("Done")
                .doFinally(s -> LOGGER.info("Paused for {} msec", duration));
    }

    // Status code from the web filter is 200.
    @GetMapping("/pause2/{duration}")
    public Mono<ResponseEntity<String>> pause2(@PathVariable long duration) {
        LOGGER.info("Pausing for {} msec", duration);
        return Mono.delay(Duration.ofMillis(duration))
                .thenReturn(ResponseEntity.ok("Done"))
                .doFinally(s -> LOGGER.info("Paused for {} msec", duration));
    }
}
