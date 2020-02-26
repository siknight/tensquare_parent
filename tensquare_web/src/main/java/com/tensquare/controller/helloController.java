package com.tensquare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("hello.do")
    public String tohello(){
        return "hello";
    }
}
