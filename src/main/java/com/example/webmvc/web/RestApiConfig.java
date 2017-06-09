package com.example.webmvc.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

@ComponentScan("com.example.webmvc.web")
@EnableSwagger2
public class RestApiConfig {
    @Bean
    public Docket myApi(){
        return new Docket(DocumentationType.SWAGGER_2).select() // 生成 Docket 的选择器对 API 进行筛选
                .apis(RequestHandlerSelectors.any())// 针对 controller 进行筛选
                .paths(PathSelectors.any())// 针对路径进行筛选
                .build()
                .pathMapping("/")// API 的根目录
                .directModelSubstitute(Date.class, String.class)// 输出模型的替换 (把日期以字符串的形式输出)
                .genericModelSubstitutes(ResponseEntity.class) // 针对泛型的处理 (指定其泛型是哪种类型)
                .apiInfo(new ApiInfoBuilder()
                        .title("Spring 中使用 Swagger2 构建 RESTful APIs")
                        .description("Swagger2")
                        .contact("jiangjianbin 开发组 ")
                        .version("2.0")
                        .build());
    }
}
