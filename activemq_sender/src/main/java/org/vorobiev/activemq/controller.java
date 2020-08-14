package org.vorobiev.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


@RestController
public class controller {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String queueName, String message) {
          jmsTemplate.send(queueName, new MessageCreator() {

            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                return message;
            }
        });
    }

    @RequestMapping(value= "/message/{message} ",method = RequestMethod.POST)
    public long  postRequest(String message)
    {
        sendMessage("testQueue","message");

    }
}
