package me.remind.rest.sandbox.controller;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.greeting.Greeting;
import me.remind.rest.sandbox.greeting.config.GreetingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

import static java.time.LocalDateTime.now;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final String TEMPLATE = "Hello %s, %s";

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final AtomicLong counter = new AtomicLong();

    private final GreetingConfig.GreetingProperties greetingProperties;

    @Autowired
    public HelloController(final GreetingConfig.GreetingProperties greetingProperties) {
        this.greetingProperties = greetingProperties;
    }

    @RequestMapping({"", "/"})
    public Greeting getHelloWorld(@RequestParam(value = "name", defaultValue = "World") String name) {
        final Greeting greeting = greet(name);
        log.info("thats my greeting, {}", greeting);
        return greeting;
    }

    Greeting greet(String name) {
        return new Greeting(
                counter.incrementAndGet(),
                String.format(TEMPLATE, name, DTF.format(now())),
                greetingProperties.getMessage());
    }
}
