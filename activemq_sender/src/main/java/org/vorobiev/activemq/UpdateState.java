package org.vorobiev.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;


@Service
public class UpdateState implements Action<String, String> {
    @Autowired
    @Qualifier("JmsTemplateQueue")
    private JmsTemplate jmsQueueTemplate;
    protected String actionQueue ="";
    private String modelQueue = "model_in";


    @Override
    public void execute(StateContext<String, String> context) {
        System.out.println("Source state: " + context.getSource());
        System.out.println("Target state: " + context.getTarget());
        System.out.println("Event: " + context.getEvent());
        MessageHeaders headers = context.getMessageHeaders();
        Request request = (Request)headers.get("request");
        request.setStatus(context.getTarget().getId());

        if (!actionQueue.equals("")) {
            jmsQueueTemplate.convertAndSend(actionQueue, request);
        }

        jmsQueueTemplate.convertAndSend(modelQueue,request);
    }
}