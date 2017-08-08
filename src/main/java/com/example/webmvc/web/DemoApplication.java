package com.example.webmvc.web;

import com.example.webmvc.tool.swagger.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import({SwaggerConfig.class})
@SpringBootApplication
@EnableSwagger2
@ComponentScan(value = {"com.example.webmvc.web","com.example.webmvc.cache","com.example.webmvc.dao","com.example.webmvc.service"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
