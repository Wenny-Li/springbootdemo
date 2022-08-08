package com.example.springbootdemo.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {
 //   @GetMapping(value = "/user")
//    public User getUser() {
//        return new User("愚公要移山", "123456");
//    }
    @ApiOperation("可以指定参数的API")
    @PostMapping("/param")
    public String hello2(@ApiParam("用户名") String name){
        return "hello" + name;
    }
}