package me.remind.rest.sandbox.greeting.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.remind.rest.sandbox.greeting.config.GreetingConfig.GreetingProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingConfig {

    @ConfigurationProperties(prefix = "hello.world")
    @NoArgsConstructor
    @Data
    public static class GreetingProperties {

        @NotNull
        private String message;
    }
}
