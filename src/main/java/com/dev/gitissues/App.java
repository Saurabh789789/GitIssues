package com.dev.gitissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// entry point of program
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(GitController.class, args);
    }
}