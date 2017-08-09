package com.example.webmvc.web;

import com.example.webmvc.tool.swagger.config.SwaggerConfig;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// TODO: 2017/8/9 0009  test环境不能加载swagger报错
@Profile("dev") //只在dev环境下才加载该类

@Import({SwaggerConfig.class})
@EnableSwagger2

@RequestMapping("api")
@Controller
@Api(value = "Swagger启动器")
public class ApiController {
    {
        System.out.println("111111111111111111111111111111111111111111111111111");
    }
}
