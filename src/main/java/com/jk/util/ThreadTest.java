package com.jk.util;

import com.jk.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;

/**
 * 多线程做rabbitMQ
 */
public class ThreadTest implements Runnable{

    private AmqpTemplate amqpTemplate;
    private UserService userService;
    private String uuid;



    public ThreadTest(UserService userService,String uuid){
        this.userService = userService;
        this.uuid = uuid;
    }

    public ThreadTest(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void run() {
        send();
    }

    public ThreadTest(){}

    public void send(){
        userService.sendUuid(uuid);
    }
}
