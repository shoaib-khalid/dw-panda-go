package com.kalsym.deliveryService;

import com.kalsym.deliveryService.utility.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {

    public static final String VERSION = "1.0-SNAPSHOT";

    public static void main(String... args) {
        System.out.println("Hello spring java...");
        Logger.application.debug("Starting Panda Go Wrapper...");
        SpringApplication.run(Main.class, args);
        Logger.application.info("Started Panda Go Wrapper...");
    }

}