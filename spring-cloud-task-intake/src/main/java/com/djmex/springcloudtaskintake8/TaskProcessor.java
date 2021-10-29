package com.djmex.springcloudtaskintake8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Service
public class TaskProcessor {

    @Autowired
    private Sink sink;

    public void publish(String payload) {

        String url = "maven://com.djmex:spring-cloud-task-server:jar:0.0.1-SNAPSHOT";
        List<String> input = new ArrayList<>(Arrays.asList(payload.split(",")));
        TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null, null);
        GenericMessage<TaskLaunchRequest> message = new GenericMessage<>(request);
        System.out.println("CREATED TASK REQUEST - MESSAGE: " + message);

//      -------------------------------EXAMPLE 1 ( with RabbitConfiguration ) ------------------------------------------
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpAdmin admin = context.getBean(AmqpAdmin.class);
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        TopicExchange exchange = context.getBean(TopicExchange.class);
        admin.declareExchange(exchange);
        template.setRoutingKey("#");
        template.setExchange("spring-boot-exchange");
//        template.convertAndSend(new Message(message.getPayload().getUri().getBytes()));
//        this.sink.input().send(message);
        Object foo = template.receiveAndConvert("myqueue1");
        System.out.println("FOO: " + foo);
//      -------------------------------EXAMPLE 2  ( without RabbitConfiguration ) ------------------------------------------
//        ConnectionFactory connectionFactory = new CachingConnectionFactory();
//        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
//        admin.declareQueue(new Queue("myqueue"));
//        AmqpTemplate template = new RabbitTemplate(connectionFactory);
//        template.convertAndSend("myqueue", "foo");
//        String foo = (String) template.receiveAndConvert("myqueue1");
//        this.source.output().send(message);
    }
}
