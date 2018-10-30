package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.ThreadTest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //rabbitMQ
    @Autowired
    private AmqpTemplate amqpTemplate;

    //redis
    @Autowired
    private RedisTemplate redisTemplate;

    //mongodb
    @Autowired

    private MongoTemplate mongoTemplate;

    /**
     *
     */

    /**
     * mongodb
     * @return
     */
    @RequestMapping("find")//查询
    public List<User> find() {
        List<User> userList = mongoTemplate.findAll(User.class);
        String str=JSON.toJSON(userList).toString();
        System.out.println(str);
        return userList;
    }

    @RequestMapping("save")//新增
    public User save() {
        User user = new User();
        user.setId(2);
        user.setUsername("胖虎");
        user.setPassword("sb666");
        mongoTemplate.save(user);
//也可以使用Repository插入数据，userService.save(user);
        return user;
    }

    /**
     * redis
     */
    @RequestMapping("toError")
    public void toError(){
        redisTemplate.opsForValue().set("error","by / zero");
    }


    /**
     * rabbitMQ测试
     */
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @RequestMapping("send")
    public String send(){
        User user = new User();
        user.setId(1);
        amqpTemplate.convertAndSend("ggd3", JSON.toJSONString(user));
        return "send1111111111111111";
    }

    //第二种  有问题
    /*@RequestMapping("threadSend")
    public String threadSend(){
        String uuid = UUID.randomUUID().toString();
        singleThreadExecutor.execute(new ThreadTest(userService,uuid));
        return "bbb";
    }*/

    /**
     * dubbo测试
     */
    /*@RequestMapping("/test")
    public void toCall(){
        userService.getUserInfo();
    }*/
}
