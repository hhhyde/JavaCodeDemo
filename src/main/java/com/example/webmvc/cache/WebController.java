package com.example.webmvc.cache;

import com.example.webmvc.tool.swagger.annotation.SwaggerIgnore;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@SwaggerIgnore
@Controller
@RequestMapping("web")
@ComponentScan
public class WebController {

    @Value("${demo.hello}")
    private String hello;

    @Autowired
    private CounterService counterService;

    @ResponseBody
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    private String hello() {
        counterService.increment("kejt.hello.count");
        return "hello_" + hello;
    }

    @ResponseBody
    @RequestMapping(value = "file",method = RequestMethod.GET)
    private String file(@ApiParam(value = "车辆模型文件") @RequestParam(value = "file",required = true) MultipartFile  file){
        counterService.increment("kejt.hello.count");
        return "hello_" + hello;
    }

}
