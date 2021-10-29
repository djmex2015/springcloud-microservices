package com.djmex.springcloudtaskintake8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskProcessor processor;

    @RequestMapping(path = "/task", method = RequestMethod.POST)
    public @ResponseBody String launchTask(@RequestBody String in) {
        processor.publish(in);
        System.out.println("REQUEST MADE");
        return "success";
    }
}
