package org.vorobiev.testRest;

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
    public void  receiveMessageModel(Request request) throws JMSException,Exception {
        Request tmp =repoRequest.getOne(request.getId());
        tmp.setStatus(request.getStatus());
        repoRequest.save(tmp);
        System.out.println("#################################### receive model: "+request.getId()+":"+request.getStatus());

    }

}