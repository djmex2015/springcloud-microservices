package com.djmex.springcloudtaskintake8;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTaskLauncher
public class SpringCloudTaskIntake8Application {

    private String EXCHANGE_NAME = "amq.topic";
    private String QUEUE_GENERIC_NAME = "amq.algogenerico";
    private String QUEUE_SPECIFIC_NAME = "amq.algo";
    private String ROUTING_KEY = "#";

    static final String topicExchangeName = "input";

    static final String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("#");
//    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    
    
    
    

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTaskIntake8Application.class, args);
    }

}
