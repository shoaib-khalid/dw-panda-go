package com.kalsym.sample;

import com.kalsym.sample.utility.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {

    public static final String VERSION = "1.0-SNAPSHOT";

    public static void main(String... args) {
        System.out.println("Hello spring java...");
        Logger.application.info("Starting Spring Hello Project...");
        SpringApplication.run(Main.class, args);
        Logger.application.info("Started Spring Hello Project...");
    }

}