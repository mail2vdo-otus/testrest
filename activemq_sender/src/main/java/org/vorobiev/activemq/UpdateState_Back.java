package org.vorobiev.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;


@Service
public class UpdateState_Back extends UpdateState {


    public UpdateState_Back() {
        this.actionQueue = "back_in";
    }


}