package com.jk;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;



@SpringBootApplication
@ImportResource("classpath:spring-dubbo-consumer.xml")
public class SpringbootAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllApplication.class, args);
    }

    /**
     * ggd和后面的最好一样   比较好理解
     * @return
     */
    @Bean
    public Queue getQueue(){
        return new Queue("ggd3");
    }

}
