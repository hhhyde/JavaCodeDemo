package com.example.webmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("web")
public class WebController {

    @ResponseBody
    @RequestMapping("hello")
    private String hello() {
        return "hello尽可能432555";
    }

}
