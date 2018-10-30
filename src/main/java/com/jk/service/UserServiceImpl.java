package com.jk.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendUuid(String uuid) {
        amqpTemplate.convertAndSend("ggd3",uuid);
    }
}
