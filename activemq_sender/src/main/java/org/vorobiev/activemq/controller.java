package org.vorobiev.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class controller {

    @Autowired
    @Qualifier("JmsTemplateQueue")
    private JmsTemplate jmsQueueTemplate;


    public void sendMessage(String queueName, Request request) {


        jmsQueueTemplate.convertAndSend(queueName,request);

    }




    @GetMapping("/version")
    public ResponseEntity<String> getVersion() throws InterruptedException {
        return new ResponseEntity<String>("{\"version\": \""+"0.01"+"''\"}", HttpStatus.OK);
    }


    @RequestMapping(path = "/message",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public long  postRequest(@RequestBody Request request)
    {
        sendMessage("model_out",request);

        return 0;

    }

    @RequestMapping(path = "/message/{queue}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public long  postRequest(@RequestBody Request request,@PathVariable("queue")  String queue)
    {
        sendMessage(queue,request);

        return 0;

    }


}
