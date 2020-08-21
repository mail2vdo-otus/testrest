package org.vorobiev.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class ContractSM {

    @Autowired
    private UpdateState updateState;
    @Autowired
    private UpdateState_Back updateState_back;
    @Autowired
    private UpdateState_Notify updateState_notify;

    @Autowired
    private ApplicationContext appContext;

    public void executeSM(Request request, String event) throws Exception {

        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();


            builder.configureStates()
                .withStates()
                .initial(request.getStatus())
                .end("закрыто")
                .states(
                        new HashSet<String>(Arrays.asList("подтверждено", "уведомление", "исполнено")));



        builder.configureConfiguration()
                .withConfiguration()
                .beanFactory(appContext.getAutowireCapableBeanFactory())
                .autoStartup(true);


        builder.configureTransitions()
                .withExternal()
                .source("введено").target("подтверждено").event("model").action(updateState_back).and()
                .withExternal()
                .source("подтверждено").target("исполнено").event("back").action(updateState_notify).and()
                .withExternal()
                .source("исполнено").target("закрыто").event("notify").action(updateState);

        StateMachine<String, String> sm = builder.build();
        Map<String, Object> requests = new HashMap<>();
        requests.put("request", request);

        MessageHeaders headers = new MessageHeaders(requests);
        Message<String> message = new GenericMessage<>(event, headers);
        sm.sendEvent(message);
        sm.stop();
    }
    }

