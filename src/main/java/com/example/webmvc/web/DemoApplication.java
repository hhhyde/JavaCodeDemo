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
@ComponentScan("com.example.webmvc.web")
@ComponentScan("com.example.webmvc.cache")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
