package com.djmex.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserConfigController {

//    @Value("${value1}")
//    String value1;

    @GetMapping("userConfigInfo")
    public ResponseEntity<?> getUserConfigInfo() {
        return ResponseEntity.ok("${value1}");
    }
}
