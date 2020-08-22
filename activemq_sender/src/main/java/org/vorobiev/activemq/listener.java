package org.vorobiev.activemq;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Map;
@Component
public class listener {

    @Autowired
    ContractSM contractSM;

    @JmsListener(destination = "model_out")
    public void  receiveMessageModel(JsonNode jsonNode ) throws JMSException, Exception {
        Request request = new Request();
        request.setId(jsonNode.path("id").asInt());
        request.setStatus(jsonNode.path("status").asText());
        System.out.println("#################################### receive model: "+request.getStatus());
        contractSM.executeSM(request,"model");
    }
    @JmsListener(destination = "back_out")
    public void  receiveMessageBack(JsonNode jsonNode ) throws JMSException,Exception {
        Request request = new Request();
        request.setId(jsonNode.path("id").asInt());
        request.setStatus(jsonNode.path("status").asText());

        System.out.println("#################################### receive back: "+request.getStatus());
        contractSM.executeSM(request,"back");
    }
    @JmsListener(destination = "notify_out")
    public void  receiveMessageNotify(JsonNode jsonNode ) throws JMSException,Exception {
        Request request = new Request();
        request.setId(jsonNode.path("id").asInt());
        request.setStatus(jsonNode.path("status").asText());

        System.out.println("#################################### receive notify: "+request.getStatus());
        contractSM.executeSM(request,"notify");
    }
}