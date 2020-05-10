package org.vorobiev.testRest;

import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        System.out.println("############################### appStatusMessage:"+System.getProperties().getProperty("appStatusMessage"));
       // System.out.println("############################### DATABASE_PASSWORD:"+System.getenv("DATABASE_PASSWORD") );

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initialize(RepoRequest repoRequest) {



        return args -> {
            if (repoRequest.count()==0){
            Stream.of("1st test message  ", "2nd test message", "3rd test message").forEach(text -> {

                Request req = new Request(text);

                repoRequest.save(req);
            });}
            repoRequest.findAll().forEach(System.out::println);
        };
    }
}