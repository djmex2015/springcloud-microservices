package com.djmex.springcloudtaskserver;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class SpringCloudTaskServerApplication {
    
    @Bean
    public TaskProcessor getTaskProcessor(){
        return new TaskProcessor();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTaskServerApplication.class, args);
    }

    public class TaskProcessor implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            System.out.println("Args: " + Arrays.asList(args));
        }
    }

}
