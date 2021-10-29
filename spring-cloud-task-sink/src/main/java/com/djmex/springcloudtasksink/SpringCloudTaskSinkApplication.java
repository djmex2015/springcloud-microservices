package com.djmex.springcloudtasksink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.cloud.task.launcher.TaskLauncherSink;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTaskLauncher
public class SpringCloudTaskSinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTaskSinkApplication.class, args);
    }

//    @Bean
//    public TaskLauncher taskLauncher() {
//        return (TaskLauncher) new TaskLauncherSink();
//    }

}
