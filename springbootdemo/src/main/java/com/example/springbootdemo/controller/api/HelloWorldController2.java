package com.example.springbootdemo.controller.api;


import com.example.springbootdemo.pojo.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController2 {

    @GetMapping(value="/hello")
    public String test() {
        return "hello";
    }

    @GetMapping(value="/user")
    public Employee user() {
        return new Employee();
    }
}
