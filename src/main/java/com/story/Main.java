package com.story;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.story.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}