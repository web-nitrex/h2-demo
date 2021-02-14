package com.example.h2demo;

import com.example.h2demo.dao.DAO;
import com.example.h2demo.models.Author;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class H2DemoApplication {

    private static CommandAnalyzer commandAnalyzer;

    public H2DemoApplication(CommandAnalyzer commandAnalyzer) {
        this.commandAnalyzer=commandAnalyzer;
    }

    public static void main(String[] args) {

        SpringApplication.run(H2DemoApplication.class, args);

        commandAnalyzer.run();

    }

}
