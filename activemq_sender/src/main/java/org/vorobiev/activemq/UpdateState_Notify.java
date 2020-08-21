package org.vorobiev.activemq;


import org.springframework.stereotype.Service;


@Service
public class UpdateState_Notify extends UpdateState {


    public UpdateState_Notify() {
        this.actionQueue = "notify_in";
    }


}