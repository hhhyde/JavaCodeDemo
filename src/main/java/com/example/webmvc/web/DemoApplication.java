package com.example.webmvc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
@ComponentScan(value = {"com.example.webmvc.web","com.example.webmvc.cache","com.example.webmvc.dao","com.example.webmvc.service"})
public class DemoApplication {

    private static Set<String> clientSet = new CopyOnWriteArraySet<>();


    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        System.out.println(integer.getAndIncrement());
        System.out.println(integer.getAndIncrement());

//        SpringApplication.run(DemoApplication.class, args);
    }
}

