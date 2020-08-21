package org.vorobiev.activemq;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.statemachine.config.EnableStateMachine;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
@EnableStateMachine
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}