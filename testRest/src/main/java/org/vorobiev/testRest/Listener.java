package org.vorobiev.testRest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class Listener {

    private final RepoRequest repoRequest;

    @Autowired
    public Listener(RepoRequest repoRequest) {
        this.repoRequest = repoRequest;
    }

    @JmsListener(destination = "model_in")
    public void  receiveMessageModel(JsonNode jsonNode) throws JMSException,Exception {
        System.out.println("#################################### receive model: "+jsonNode.toString());

        Request tmp = repoRequest.findById(jsonNode.path("id").asInt()).get();
                //new Request();
        tmp.setId(jsonNode.path("id").asInt());
                //repoRequest.getOne(jsonNode.path("id").asInt());
        tmp.setStatus(jsonNode.path("status").asText());
        repoRequest.save(tmp);
        System.out.println("#################################### receive model: "+tmp.getId()+":"+tmp.getStatus());

    }

}