package com.ridelr.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RidelrApplication {
    private static final Logger logger = LoggerFactory.getLogger(RidelrApplication.class);

    public static void main(String[] args) {
        logger.info("Entering..");
        System.out.println("Starting..");
        SpringApplication.run(RidelrApplication.class, args);
        System.out.println("Started");
    }
}
