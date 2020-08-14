package org.vorobiev.testRest;

import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {

        System.out.println("############################### appStatusMessage:"+System.getProperties().getProperty("appStatusMessage"));
       // System.out.println("############################### DATABASE_PASSWORD:"+System.getenv("DATABASE_PASSWORD") );
        if (System.getenv("MEMCACHED_ENABLED")!=null&&System.getenv("MEMCACHED_ENABLED").equals("0")) {
            System.setProperty("spring.cache.type", "None");
        }
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initialize(RepoRequest repoRequest) {



        return args -> {
            if (repoRequest.count()<10000){


           /* Stream.of("1st test message  ", "2nd test message", "3rd test message").forEach(text -> {

                Request req = new Request(text);

                repoRequest.save(req);
            });*/
            int i=0;



                while(i<10000){


                    Request req = new Request("request N "+Integer.toString(i));
                     i++;
                    repoRequest.save(req);}

/**/
            }
            repoRequest.findAll().forEach(System.out::println);
        };
    }
}